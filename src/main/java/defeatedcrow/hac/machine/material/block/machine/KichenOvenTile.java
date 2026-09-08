package defeatedcrow.hac.machine.material.block.machine;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.block.InventoryDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.client.gui.KichenOvenMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class KichenOvenTile extends KichenStoveTile {

	private final RecipeManager.CachedCheck<Container, SmeltingRecipe> quickCheck;

	public KichenOvenTile(BlockPos pos, BlockState state) {
		super(MachineInit.KICHEN_OVEN_TILE.get(), pos, state);
		this.quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);
	}

	public final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return KichenOvenTile.this.currentProgress;
			case 1:
				return KichenOvenTile.this.totalProgress;
			case 2:
				return KichenOvenTile.this.currentProg[0];
			case 3:
				return KichenOvenTile.this.totalProg[0];
			case 4:
				return KichenOvenTile.this.currentProg[1];
			case 5:
				return KichenOvenTile.this.totalProg[1];
			case 6:
				return KichenOvenTile.this.currentProg[2];
			case 7:
				return KichenOvenTile.this.totalProg[2];
			default:
				return 0;
			}
		}

		@Override
		public void set(int id, int data) {
			switch (id) {
			case 0:
				KichenOvenTile.this.currentProgress = data;
				break;
			case 1:
				KichenOvenTile.this.totalProgress = data;
				break;
			case 2:
				KichenOvenTile.this.currentProg[0] = data;
				break;
			case 3:
				KichenOvenTile.this.totalProg[0] = data;
				break;
			case 4:
				KichenOvenTile.this.currentProg[1] = data;
				break;
			case 5:
				KichenOvenTile.this.totalProg[1] = data;
				break;
			case 6:
				KichenOvenTile.this.currentProg[2] = data;
				break;
			case 7:
				KichenOvenTile.this.totalProg[2] = data;
				break;
			}
		}

		@Override
		public int getCount() {
			return 8;
		}
	};

	public int[] currentProg = new int[3];
	public int[] totalProg = new int[3];
	int count = 10;
	protected InventoryDC dummy = new InventoryDC(1, this);

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
		} else {
			count = 10;

			if (DCState.getBool(getBlockState(), DCState.LIT)) {
				for (int i = 0; i < 3; i++) {
					int slot = i * 2 + 2;
					ItemStack copy = this.inventory.getItem(slot);
					if (!DCUtil.isEmpty(copy)) {
						dummy.setItem(0, copy);
						SmeltingRecipe recipe = quickCheck.getRecipeFor(dummy, getLevel()).orElse(null);
						if (recipe != null) {
							if (totalProg[i] > 0) {
								if (currentProg[i] > totalProg[i]) {
									ItemStack output = recipe.assemble(dummy, getLevel().registryAccess());
									if (!DCUtil.isEmpty(output) && this.inventory.canInsertResult(output, slot + 1, slot + 1) > 0) {
										this.inventory.insertResult(output, slot + 1, slot + 1);
										this.getInventory().removeItem(slot, 1);
										this.setChanged();
										reset(i);
									}
								} else {
									currentProg[i]++;
								}
							} else {
								currentProg[i] = 0;
								totalProg[i] = 5;
							}
						} else {
							reset(i);
						}
					} else {
						reset(i);
					}
				}
			}
		}
		return super.onTickProcess(level, pos, state);
	}

	public void reset(int num) {
		this.totalProg[num] = 0;
		this.currentProg[num] = 0;
	}

	@Override
	public int getContainerSize() {
		return 8;
	}

	@Override
	protected int[] getTopSlots() {
		return new int[] { 2, 4, 6 };
	}

	@Override
	protected int[] getBottomSlots() {
		return new int[] { 1, 3, 5, 7 };
	}

	@Override
	protected int[] getSideSlots() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new KichenOvenMenu(MachineInit.KICHEN_OVEN_MENU.get(), i, inv, this, this.dataAccess);
	}

	// nbt

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		for (int i = 0; i < 3; i++) {
			currentProg[i] = tag.getInt(TagKeyDC.CURRENT_PROGRESS + i);
			totalProg[i] = tag.getInt(TagKeyDC.MAX_PROGRESS + i);
		}
	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);
		for (int i = 0; i < 3; i++) {
			tag.putInt(TagKeyDC.CURRENT_PROGRESS + i, currentProg[i]);
			tag.putInt(TagKeyDC.MAX_PROGRESS + i, totalProg[i]);
		}
	}

	public EntityRenderData getRenderData(BlockState block) {
		if (block.getBlock() == MachineInit.KICHEN_OVEN_WOOD.get())
			return DCState.getBool(block, DCState.LIT) ? WOOD_ON : WOOD_OFF;
		if (block.getBlock() == MachineInit.KICHEN_OVEN_BLACK.get())
			return DCState.getBool(block, DCState.LIT) ? BLACK_ON : BLACK_OFF;
		if (block.getBlock() == MachineInit.KICHEN_OVEN_LAB.get())
			return DCState.getBool(block, DCState.LIT) ? LAB_ON : LAB_OFF;
		return LAB_OFF;
	}

	public static final EntityRenderData WOOD_OFF = new EntityRenderData("tile/kitchen_oven_wood_off", 1F, -0.5F);
	public static final EntityRenderData WOOD_ON = new EntityRenderData("tile/kitchen_oven_wood_on", 1F, -0.5F);
	public static final EntityRenderData BLACK_OFF = new EntityRenderData("tile/kitchen_oven_black_off", 1F, -0.5F);
	public static final EntityRenderData BLACK_ON = new EntityRenderData("tile/kitchen_oven_black_on", 1F, -0.5F);
	public static final EntityRenderData LAB_OFF = new EntityRenderData("tile/kitchen_oven_lab_off", 1F, -0.5F);
	public static final EntityRenderData LAB_ON = new EntityRenderData("tile/kitchen_oven_lab_on", 1F, -0.5F);

}
