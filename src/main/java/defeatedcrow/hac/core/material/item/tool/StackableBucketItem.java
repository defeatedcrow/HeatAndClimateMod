package defeatedcrow.hac.core.material.item.tool;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.fluid.FluidItemWrapperStackable;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

public class StackableBucketItem extends CraftingItemDC {

	private String domain = "main";

	public StackableBucketItem(String n, TagKey<Item> pair) {
		super(n, new Item.Properties().tab(CoreInit.MACHINE), pair);
	}

	static Supplier<Item> getSup() {
		return () -> CoreInit.CALABASH_BUCKET.get();
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
		return new ItemStack(getSup().get());
	}

	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		return true;
	}

	// bucketlike
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack held = player.getItemInHand(hand);
		if (!DCUtil.isEmpty(held)) {
			ItemStack copy = held.copy();
			copy.setCount(1);
			IFluidHandlerItem handler = FluidUtil.getFluidHandler(copy).map(h -> {
				return h;
			}).orElse(null);
			if (handler == null)
				return InteractionResultHolder.pass(held);
			FluidStack fluid = handler.getFluidInTank(1);
			boolean isEmpty = fluid.isEmpty() || fluid.getFluid() == Fluids.EMPTY;

			BlockHitResult hitresult = getPlayerPOVHitResult(level, player, isEmpty ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
			if (hitresult.getType() == HitResult.Type.MISS) {
				return InteractionResultHolder.pass(held);
			} else if (hitresult.getType() != HitResult.Type.BLOCK) {
				return InteractionResultHolder.pass(held);
			} else {
				BlockPos pos = hitresult.getBlockPos();
				Direction dir = hitresult.getDirection();
				BlockPos p2 = pos.relative(dir);
				if (level.mayInteract(player, pos) && player.mayUseItemAt(p2, dir, held)) {
					if (isEmpty) {
						BlockState state = level.getBlockState(pos);
						FluidState flow = state.getFluidState();
						if (state.getBlock() instanceof BucketPickup && flow.getType() != Fluids.EMPTY && flow.isSource()) {
							BucketPickup target = (BucketPickup) state.getBlock();
							FluidStack fill = new FluidStack(flow.getType(), 1000);
							if (handler.fill(fill, FluidAction.SIMULATE) == 1000) {
								player.awardStat(Stats.ITEM_USED.get(this));
								flow.getType().getPickupSound().ifPresent((action) -> {
									player.playSound(action, 1.0F, 1.0F);
								});
								handler.fill(fill, FluidAction.EXECUTE);
								target.pickupBlock(level, pos, state);
								level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);

								if (!player.getAbilities().instabuild) {
									held.shrink(1);
									if (held.isEmpty())
										return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, copy);
								}
								if (!player.getInventory().add(copy))
									player.drop(copy, false);
								return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, held);
							}
						}
						return InteractionResultHolder.fail(held);
					} else if (fluid.getAmount() == 1000) {
						BlockState state = level.getBlockState(pos);
						BlockPos p3 = canBlockContainFluid(level, pos, state, fluid) ? pos : p2;
						if (this.emptyContents(player, level, p3, hitresult, fluid)) {
							if (player instanceof ServerPlayer) {
								CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, p3, held);
							}
							handler.drain(1000, FluidAction.EXECUTE);

							player.awardStat(Stats.ITEM_USED.get(this));
							if (!player.getAbilities().instabuild) {
								held.shrink(1);
								if (held.isEmpty())
									return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, copy);
							}
							if (!player.getInventory().add(copy))
								player.drop(copy, false);
							return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, held);
						}
					}
				}
			}
		}
		return InteractionResultHolder.fail(held);
	}

	private boolean canBlockContainFluid(Level level, BlockPos pos, BlockState state, FluidStack fluid) {
		return state.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer) state.getBlock()).canPlaceLiquid(level, pos, state, fluid.getFluid());
	}

	public boolean emptyContents(@Nullable Player player, Level level, BlockPos pos, @Nullable BlockHitResult hitResult, FluidStack fluid) {
		if (!(fluid.getFluid() instanceof FlowingFluid)) {
			return false;
		} else {
			BlockState state = level.getBlockState(pos);
			Block block = state.getBlock();
			Material material = state.getMaterial();
			boolean flag = state.canBeReplaced(fluid.getFluid());
			boolean flag1 = state.isAir() || flag || block instanceof LiquidBlockContainer && ((LiquidBlockContainer) block).canPlaceLiquid(level, pos, state, fluid.getFluid());
			if (!flag1) {
				return hitResult != null && this.emptyContents(player, level, hitResult.getBlockPos().relative(hitResult.getDirection()), (BlockHitResult) null, fluid);
			} else if (level.dimensionType().ultraWarm() && fluid.getFluid().is(FluidTags.WATER)) {
				// netherの蒸発処理
				int i = pos.getX();
				int j = pos.getY();
				int k = pos.getZ();
				level.playSound(player, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);
				for (int l = 0; l < 8; ++l) {
					level.addParticle(ParticleTypes.LARGE_SMOKE, i + Math.random(), j + Math.random(), k + Math.random(), 0.0D, 0.0D, 0.0D);
				}
				return true;
			} else if (block instanceof LiquidBlockContainer && ((LiquidBlockContainer) block).canPlaceLiquid(level, pos, state, fluid.getFluid())) {
				((LiquidBlockContainer) block).placeLiquid(level, pos, state, ((FlowingFluid) fluid.getFluid()).getSource(false));
				fluid.getFluid().getPickupSound().ifPresent((action) -> {
					player.playSound(action, 1.0F, 1.0F);
				});
				return true;
			} else {
				if (!level.isClientSide && flag && !material.isLiquid()) {
					level.destroyBlock(pos, true);
				}

				if (!level.setBlock(pos, fluid.getFluid().defaultFluidState().createLegacyBlock(), 11) && !state.getFluidState().isSource()) {
					return false;
				} else {
					fluid.getFluid().getPickupSound().ifPresent((action) -> {
						player.playSound(action, 1.0F, 1.0F);
					});
					return true;
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (!DCUtil.isEmpty(stack) && stack.hasTag()) {
			if (stack.getTag().contains(TagKeyDC.getTankKey(1), 10)) {
				CompoundTag tankTag = stack.getTag().getCompound(TagKeyDC.getTankKey(1));
				FluidStack fluid = FluidStack.loadFluidStackFromNBT(tankTag);
				if (fluid.isEmpty()) {
					MutableComponent com = Component.literal("EMPTY").withStyle(ChatFormatting.GRAY);
					list.add(com);
				} else {
					MutableComponent com = Component.literal("FLUID: ");
					com.append(fluid.getDisplayName()).append(" " + fluid.getAmount() + "mB").withStyle(ChatFormatting.AQUA);
					list.add(com);
				}
			}
		}
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag tag) {
		return new FluidItemWrapperStackable(stack, false);
	}

}
