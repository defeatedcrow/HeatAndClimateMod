package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class VillagerChestTile extends DoubleChestDC implements IRenderBlockData {

	public VillagerChestTile(BlockPos pos, BlockState state) {
		super(BuildInit.VILLAGER_CHEST_TILE.get(), pos, state);
	}

	// tick
	public static void serverTick(Level level, BlockPos pos, BlockState state, VillagerChestTile tile) {
		if (tile.onTickProcess(level, pos, state)) {
			BlockEntity.setChanged(level, pos, state);
		}
	}

	private int getConfiscationLine(ItemStack item) {
		return DCUtil.isEmpty(item) ? 0 : item.getMaxStackSize() / 2;
	}

	private int[] getSlots() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
	}

	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (isActive(level, pos, state)) {
			AABB aabb = new AABB(pos);
			List<Villager> villagers = getLevel().getEntitiesOfClass(Villager.class, aabb.inflate(8.0D, 2.0D, 8.0D), EntitySelector.ENTITY_STILL_ALIVE);
			for (Villager target : villagers) {
				if (target.getInventory() != null) {
					for (int i = 0; i < target.getInventory().getContainerSize(); i++) {
						ItemStack item = target.getInventory().getItem(i).copy();
						if (!DCUtil.isEmpty(item)) {
							int check = item.getCount() - getConfiscationLine(item);
							if (check > 0) {
								item.setCount(check);
								int k = this.getInventory().canInsertResult(item, getSlots());
								if (k > 0) {
									this.getInventory().insertResult(item, getSlots());
									target.getInventory().getItem(i).split(k);
									target.getInventory().setChanged();
									this.setChanged();
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	int count = 19;

	public boolean isActive(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
			return false;
		} else {
			count = 19;
			return true;
		}
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		return NORMAL;
	}

	public static final EntityRenderData NORMAL = new EntityRenderData("tile/villager_chest", 1F, -0.5F);

	// anim

	protected final ChestLidController chestLidController = new ChestLidController();

	public static void lidAnimateTick(Level level, BlockPos pos, BlockState state, VillagerChestTile tile) {
		tile.chestLidController.tickLid();
	}

	@Override
	public float getOpenNess(float f) {
		return this.chestLidController.getOpenness(f);
	}

	@Override
	public boolean triggerEvent(int i1, int i2) {
		if (i1 == 1) {
			this.chestLidController.shouldBeOpen(i2 > 0);
			return true;
		} else {
			return super.triggerEvent(i1, i2);
		}
	}
}
