package defeatedcrow.hac.core.event;

import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.material.ITierItem;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.OwnableBaseTileDC;
import defeatedcrow.hac.core.material.block.building.SlabWoodDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.block.crops.LeavesCropBlockDC;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.monitor.MonitorBaseTile;
import defeatedcrow.hac.magic.MagicUtil;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.BlockEvent.FarmlandTrampleEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlockEventDC {

	@SubscribeEvent
	public static void onFarmLand(FarmlandTrampleEvent event) {
		Entity entity = event.getEntity();
		if (entity == null || !entity.isAlive())
			return;

		if (entity instanceof LivingEntity living) {
			if (living.hasEffect(CoreInit.BIRD.get())) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void onDig(PlayerEvent.BreakSpeed event) {
		Player player = event.getEntity();
		float f = event.getOriginalSpeed();
		if (player == null || !player.isAlive())
			return;

		int count = MagicUtil.hasCharmItem(player, new ItemStack(MagicInit.PENDANT_SILVER_RED.get()));
		if (count > 0) {
			f += 0.5F * count;
		}

		if (player.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) && player.hasEffect(CoreInit.FISH.get()) && !EnchantmentHelper.hasAquaAffinity(player)) {
			f *= 2.5F;
		}

		if (!player.isOnGround() && !player.isInWaterOrRain() && player.hasEffect(CoreInit.BIRD.get())) {
			f *= 2.5F;
		}

		event.setNewSpeed(f);
	}

	// on break
	@SubscribeEvent
	public static void onBreakBlock(BlockEvent.BreakEvent event) {
		Player player = event.getPlayer();
		BlockState state = event.getState();
		BlockPos pos = event.getPos();
		Level level = player.level;
		ItemStack held = player.getMainHandItem();
		BlockEntity tile = level.getBlockEntity(pos);

		if (tile instanceof OwnableBaseTileDC cont) {
			if (cont.hasOwner() && !cont.isOwner(player) && cont.isLocked()) {
				event.setCanceled(true);
				return;
			}
		}

		if (level.isClientSide || player.isCrouching() || player.isSpectator() || !level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS))
			return;

		if (!DCUtil.isEmpty(held) && held.is(TagDC.ItemTag.SCYTHES)) {
			int range = 2;
			if (held.getItem() instanceof ITierItem tool) {
				range = tool.getTier().getBreakRange();
			} else if (held.getItem() instanceof TieredItem tool) {
				range = tool.getTier().getLevel();
			}
			boolean b = false;
			BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
			// 上から削る
			for (int y = range; y > -range - 1; y--) {
				for (int x = -range; x < range + 1; x++) {
					for (int z = -range; z < range + 1; z++) {
						mpos.set(pos.offset(x, y, z));
						BlockState target = level.getBlockState(mpos);
						ItemStack copy = held.copy();
						if (level.getBlockEntity(mpos) != null || !target.canHarvestBlock(level, mpos, player))
							continue;

						if (target.is(TagDC.BlockTag.SCYTHE_BREAKABLE) || target.is(BlockTags.FLOWERS) || target.is(TagDC.BlockTag.WEED)
								|| target.getBlock() instanceof IClimateCrop) {
							if (canHarvestCrop(level, mpos, target) && target.onDestroyedByPlayer(level, mpos, player, true, level.getFluidState(mpos))) {
								target.getBlock().destroy(level, mpos, target);
								target.getBlock().playerDestroy(level, player, mpos, target, null, copy);
								b = true;
							}
						}
						if (target.is(TagDC.BlockTag.BAMBOO_SHOOT)) {
							level.setBlock(mpos, Blocks.AIR.defaultBlockState(), 2);
							Vec3 v3 = Vec3.atCenterOf(mpos);
							ItemEntity drop = new ItemEntity(level, v3.x, v3.y, v3.z, new ItemStack(FoodInit.FOOD_BAMBOO_SHOOT.get()));
							drop.setDefaultPickUpDelay();
							level.addFreshEntity(drop);
							b = true;
						}
					}
				}
			}

			if (b) {
				event.setCanceled(true);
			}
		}
	}

	private static boolean canHarvestCrop(Level level, BlockPos pos, BlockState state) {
		if (state.getBlock() instanceof IClimateCrop climateCrop) {
			return climateCrop.canHarvest(state) || state.getBlock() instanceof LeavesCropBlockDC;
		} else if (state.getBlock() instanceof StemBlock) {
			return false;
		} else if (state.getBlock() instanceof CropBlock crop) {
			return !crop.isValidBonemealTarget(level, pos, state, level.isClientSide);
		} else {
			return true;
		}
	}

	@SubscribeEvent
	public static void onClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getEntity() != null && event.getHitVec() != null) {
			BlockPos pos = event.getPos();
			if (event.getEntity().isCrouching() && event.getItemStack().is(TagDC.ItemTag.CRAFT_DRIVER)) {
				DCLogger.debugInfoLog("pos " + pos.toShortString());
				BlockState state = event.getLevel().getBlockState(pos);
				if (isMirrorTarget(state)) {
					BlockState next = state.mirror(Mirror.FRONT_BACK);
					event.getLevel().setBlock(pos, next, 3);
					event.getEntity().swing(event.getHand(), true);
					event.setUseItem(Result.ALLOW);
					event.setCanceled(true);
				} else if (isRotateTarget(state)) {
					BlockState next = state.rotate(Rotation.CLOCKWISE_90);
					event.getLevel().setBlock(pos, next, 3);
					event.getEntity().swing(event.getHand(), true);
					event.setUseItem(Result.ALLOW);
					event.setCanceled(true);
				}
			} else if (event.getEntity().isCrouching() && event.getItemStack().is(Items.NAME_TAG) && event.getLevel().getBlockEntity(pos) instanceof OwnableBaseTileDC ownable) {
				if (event.getEntity() instanceof ServerPlayer sp) {
					if (sp.getMainHandItem().is(Items.NAME_TAG) && ClimateCore.proxy.isOP(sp)) {
						Component name = sp.getMainHandItem().getHoverName();
						Player target = ClimateCore.proxy.getPlayer(sp.getLevel(), name.getString());
						if (target != null) {
							ownable.setOwner(target.getUUID());
							ownable.setOwnerName(target.getScoreboardName());
							sp.sendSystemMessage(Component.translatable("dcs.tip.container.ownable_register", name.getString()));
						}
					}
				}
				event.setUseItem(Result.ALLOW);
				event.setCanceled(true);
			} else if (event.getItemStack().is(MachineInit.MEMORY_COORD.get())) {
				CompoundTag tag = event.getItemStack().getOrCreateTag();
				BlockEntity target = event.getLevel().getBlockEntity(pos);
				if (tag.contains(TagKeyDC.POS_X)) {
					if (target instanceof MonitorBaseTile monitor) {
						monitor.loadCoordTag(tag);
						event.getEntity().swing(event.getHand(), true);
						event.setUseItem(Result.ALLOW);
						event.setCanceled(true);
					}
				} else if (event.getLevel() instanceof ServerLevel serverLevel) {
					BlockPos p = pos.relative(event.getFace().getOpposite());
					tag.putInt(TagKeyDC.DIRECTION, event.getFace().getOpposite().get3DDataValue());
					tag.putInt(TagKeyDC.POS_X, p.getX());
					tag.putInt(TagKeyDC.POS_Y, p.getY());
					tag.putInt(TagKeyDC.POS_Z, p.getZ());
					event.getItemStack().setTag(tag);

					if (event.getEntity() instanceof ServerPlayer sp) {
						MutableComponent mes = Component.translatable("dcs.tip.coodinate");
						MutableComponent mes2 = Component.literal(" X:" + p.getX());
						mes2.append(Component.literal(" Y:" + p.getY()));
						mes2.append(Component.literal(" Z:" + p.getZ()));
						sp.sendSystemMessage(mes);
						sp.sendSystemMessage(mes2);
					}

					event.getEntity().swing(event.getHand(), true);
					event.setUseItem(Result.ALLOW);
					event.setCanceled(true);
				}
			}
		}
	}

	private static boolean isRotateTarget(BlockState state) {
		return state.is(BlockTags.STAIRS) || state.hasProperty(DCState.FACING);
	}

	private static boolean isMirrorTarget(BlockState state) {
		return state.is(TagDC.BlockTag.HOPPER) || state.getBlock() instanceof SlabWoodDC;
	}

}
