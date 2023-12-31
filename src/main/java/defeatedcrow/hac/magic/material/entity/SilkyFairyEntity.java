package defeatedcrow.hac.magic.material.entity;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
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

public class SilkyFairyEntity extends OwnableMagicEntity {

	public SilkyFairyEntity(EntityType<? extends OwnableMagicEntity> type, Level level) {
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

	int count = 5;
	int y = 15;

	@Override
	public void tick() {
		if (this.level.isClientSide) {
			if (this.level.random.nextBoolean()) {
				double d0 = this.position().x - 0.05D + this.level.random.nextDouble() * 0.1D;
				double d1 = this.position().y + 0.4D;
				double d2 = this.position().z - 0.05D + this.level.random.nextDouble() * 0.1D;
				level.addParticle(CoreInit.LIGHT_ORB_RED.get(), d0, d1, d2, 0.0D, 0.05D, 0.0D);
			}
		} else if (level instanceof ServerLevel sl) {
			if (count > 0) {
				count--;
			}
			if (y >= 0) {
				Player owner = this.getOwnerPlayer();
				ItemStack tool = new ItemStack(Items.DIAMOND_PICKAXE);
				Map<Enchantment, Integer> map = Maps.newHashMap();
				map.put(Enchantments.SILK_TOUCH, 1);
				EnchantmentHelper.setEnchantments(map, tool);

				SectionPos sp = SectionPos.of(this.blockPosition());
				int minX = sp.minBlockX();
				int maxX = sp.maxBlockX();
				int minZ = sp.minBlockZ();
				int maxZ = sp.maxBlockZ();
				int minY = sp.minBlockY();
				BlockPos.betweenClosedStream(minX, minY + y, minZ, maxX, minY + y, maxZ).filter(p -> sl.getBlockState(p).getMaterial() != Material.AIR).forEach((p2) -> {
					BlockState state = sl.getBlockState(p2);
					if (state.getMaterial().isLiquid()) {
						sl.setBlock(p2, Blocks.AIR.defaultBlockState(), 2);
					} else {
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
				count = 5;
			} else {
				this.discard();
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
					level.setBlock(pos, Blocks.PACKED_ICE.defaultBlockState(), 2);
				}
			}
		}
	}

}
