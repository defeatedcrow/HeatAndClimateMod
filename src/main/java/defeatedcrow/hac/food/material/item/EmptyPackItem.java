package defeatedcrow.hac.food.material.item;

import java.util.List;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

public class EmptyPackItem extends MaterialItemDC {

	public EmptyPackItem(CreativeModeTab tab, String s, TagKey<Item> pair) {
		super(tab, s, pair);
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent st = Component.translatable("dcs.tip.empty_pack").withStyle(ChatFormatting.GRAY);
		list.add(st);
		super.appendHoverText(item, level, list, flag);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		BlockHitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
		if (hitresult.getType() == HitResult.Type.MISS) {
			return InteractionResultHolder.pass(itemstack);
		} else if (hitresult.getType() != HitResult.Type.BLOCK) {
			return InteractionResultHolder.pass(itemstack);
		} else {
			BlockPos pos = hitresult.getBlockPos();
			Direction dir = hitresult.getDirection();
			BlockPos pos1 = pos.relative(dir);
			if (level.mayInteract(player, pos) && player.mayUseItemAt(pos1, dir, itemstack)) {
				BlockState state = level.getBlockState(pos);
				if (state.getFluidState().is(Fluids.WATER) && state.getFluidState().isSource()) {
					player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
					ItemStack ret = ItemUtils.createFilledResult(itemstack, player, new ItemStack(FoodInit.FOOD_WATER.get()), false);
					return InteractionResultHolder.sidedSuccess(ret.copy(), level.isClientSide());
				} else if (state.getFluidState().is(CoreInit.SPARKLING.getStillFluid().get())) {
					player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
					ItemStack ret = ItemUtils.createFilledResult(itemstack, player, new ItemStack(FoodInit.FOOD_SPARKLING.get()), false);
					return InteractionResultHolder.sidedSuccess(ret.copy(), level.isClientSide());
				}
			}
		}
		return InteractionResultHolder.fail(itemstack);
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (entity instanceof Cow || entity instanceof Llama || entity instanceof Goat) {
			if (entity.level.isClientSide)
				return InteractionResult.SUCCESS;
			BlockPos pos = new BlockPos(entity.getX(), entity.getY(), entity.getZ());
			if (!((Animal) entity).isBaby()) {
				player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
				ItemStack ret = ItemUtils.createFilledResult(itemstack, player, new ItemStack(FoodInit.FOOD_MILK.get()), false);
			}
			return net.minecraft.world.InteractionResult.SUCCESS;
		}
		return net.minecraft.world.InteractionResult.PASS;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag tag) {
		return new EmptyPackWrapper(stack);
	}

	// Fill Only
	public class EmptyPackWrapper implements IFluidHandlerItem, ICapabilityProvider {

		@NotNull
		protected ItemStack container;

		public EmptyPackWrapper(@NotNull ItemStack cont) {
			container = cont;
		}

		@Override
		public @NotNull ItemStack getContainer() {
			return container;
		}

		@Override
		public int getTanks() {
			return 1;
		}

		@Override
		public @NotNull FluidStack getFluidInTank(int i) {
			return FluidStack.EMPTY;
		}

		@Override
		public int getTankCapacity(int tank) {
			return 250;
		}

		@Override
		public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
			if (!stack.isEmpty()) {
				for (FluidPackItem.Type type : FluidPackItem.TYPES) {
					if (stack.getFluid().isSame(type.fluid().get().getFluid())) {
						return true;
					}
				}
			}
			return false;
		}

		@Override
		public int fill(FluidStack fill, FluidAction action) {
			if (!isFluidValid(1, fill)) {
				return 0;
			}
			if (250 > fill.getAmount()) {
				return 0;
			}
			if (action.execute()) {
				for (FluidPackItem.Type type : FluidPackItem.TYPES) {
					if (fill.getFluid().isSame(type.fluid().get().getFluid())) {
						container = new ItemStack(type.cont().get());
					}
				}
			}
			return 250;
		}

		@Override
		public @NotNull FluidStack drain(FluidStack drain, FluidAction action) {
			return FluidStack.EMPTY;
		}

		@Override
		public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
			return FluidStack.EMPTY;
		}

		private final LazyOptional<IFluidHandlerItem> holder = LazyOptional.of(() -> this);

		@Override
		@NotNull
		public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing) {
			return ForgeCapabilities.FLUID_HANDLER_ITEM.orEmpty(capability, holder);
		}

	}

}
