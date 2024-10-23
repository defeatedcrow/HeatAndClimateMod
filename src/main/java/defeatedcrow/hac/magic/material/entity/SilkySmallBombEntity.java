package defeatedcrow.hac.magic.material.entity;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.network.packet.message.MsgEffectToC;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

public class SilkySmallBombEntity extends OwnableMagicEntity {

	public SilkySmallBombEntity(EntityType<? extends OwnableMagicEntity> type, Level level) {
		super(type, level);
	}

	@Override
	public ItemStack getDropItem() {
		return ItemStack.EMPTY;
	}

	@Override
	public MagicColor getColor() {
		return MagicColor.RED;
	}

	int count = 80;
	int size = 2;
	int y = 4;

	public void setSize(int s) {
		size = s;
		y = s * 2;
	}

	public int getSize() {
		return size;
	}

	public int getCount() {
		return count;
	}

	@Override
	public void tick() {
		// 落下
		if (count > 0) {
			if (!this.isNoGravity()) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
			}

			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
			if (this.onGround) {
				this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
			}

		}
		if (this.level.isClientSide) {
			if (count > 0) {
				count--;
			}
		} else if (level instanceof ServerLevel sl) {
			if (count > 0) {
				if (count == 75)
					MsgEffectToC.sendToClient(sl, this.position(), 66);
				count--;

			} else {
				if (y >= 0) {
					Player owner = this.getOwnerPlayer();
					ItemStack tool = new ItemStack(Items.DIAMOND_PICKAXE);
					Map<Enchantment, Integer> map = Maps.newHashMap();
					map.put(Enchantments.SILK_TOUCH, 1);
					EnchantmentHelper.setEnchantments(map, tool);

					int minX = this.blockPosition().getX() - size;
					int maxX = this.blockPosition().getX() + size;
					int minZ = this.blockPosition().getZ() - size;
					int maxZ = this.blockPosition().getZ() + size;
					int minY = this.blockPosition().getY() - size;
					BlockPos.betweenClosedStream(minX, minY + y, minZ, maxX, minY + y, maxZ)
							.filter(p -> p.getY() > sl.getMinBuildHeight() && p.getY() < sl.getMaxBuildHeight())
							.filter(p -> sl.getBlockState(p).getMaterial() != Material.AIR)
							.filter(p -> !sl.getBlockState(p).hasBlockEntity())
							.forEach((p2) -> {
								BlockState state = sl.getBlockState(p2);
								if (state.getMaterial().isLiquid()) {
									sl.setBlock(p2, Blocks.AIR.defaultBlockState(), 2);
									checkAroundFluid(sl, p2, minX, maxX, minZ, maxZ);
								} else if (!state.is(BlockTags.FEATURES_CANNOT_REPLACE)) {
									checkAroundFluid(sl, p2, minX, maxX, minZ, maxZ);
									BlockEntity be = sl.getBlockEntity(p2);
									LootContext.Builder builder = (new LootContext.Builder(sl))
											.withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(p2))
											.withParameter(LootContextParams.BLOCK_STATE, state)
											.withOptionalParameter(LootContextParams.BLOCK_ENTITY, be)
											.withOptionalParameter(LootContextParams.THIS_ENTITY, owner)
											.withParameter(LootContextParams.TOOL, tool);
									List<ItemStack> list = state.getDrops(builder);
									list.forEach((item) -> {
										Block.popResource(sl, p2, item);
									});
									sl.setBlock(p2, Blocks.AIR.defaultBlockState(), 2);
								}
							});
					y--;
				} else {
					MsgEffectToC.sendToClient(sl, this.position(), 55);
					this.discard();
				}
			}
		}
	}

	private void checkAroundFluid(ServerLevel level, BlockPos pos, int minX, int maxX, int minZ, int maxZ) {
		if (pos.getX() == minX) {
			checkFluid(level, pos.west());
		}
		if (pos.getX() == maxX) {
			checkFluid(level, pos.east());
		}
		if (pos.getZ() == minZ) {
			checkFluid(level, pos.north());
		}
		if (pos.getZ() == maxZ) {
			checkFluid(level, pos.south());
		}
	}

	private void checkFluid(ServerLevel level, BlockPos pos) {
		if (level.isLoaded(pos)) {
			BlockState state = level.getBlockState(pos);
			if (state.getMaterial().isReplaceable() && !state.getFluidState().isEmpty()) {
				if (state.getMaterial() == Material.LAVA) {
					level.setBlock(pos, Blocks.STONE.defaultBlockState(), 2);
				} else {
					level.setBlock(pos, Blocks.GLASS.defaultBlockState(), 2);
				}
			}
		}
	}

}
