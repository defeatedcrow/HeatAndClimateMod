package defeatedcrow.hac.machine.material.block.machine;

import java.util.Optional;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IDisplayTile;
import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.core.network.packet.message.MsgTileDisplayItemToC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.machine.client.gui.CookingPotMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class CookingPotTile extends FermentationJarTile implements IDisplayTile {

	public CookingPotTile(BlockPos pos, BlockState state) {
		super(MachineInit.COOKING_POT_TILE.get(), pos, state);
		totalProgress = maxProgressTime();
	}

	/* inventory */

	public final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return CookingPotTile.this.currentProgress;
			case 1:
				return CookingPotTile.this.totalProgress;
			default:
				return 0;
			}
		}

		@Override
		public void set(int id, int data) {
			switch (id) {
			case 0:
				CookingPotTile.this.currentProgress = data;
				break;
			case 1:
				CookingPotTile.this.totalProgress = data;
				break;
			}

		}

		@Override
		public int getCount() {
			return 2;
		}
	};

	@Override
	public int getContainerSize() {
		return 12;
	}

	@Override
	protected int[] getTopSlots() {
		return new int[] { 0, 1, 2, 3, 4, 5, 8, 10 };
	}

	@Override
	protected int[] getBottomSlots() {
		return new int[] { 6, 7, 9, 11 };
	}

	@Override
	protected int[] getSideSlots() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	}

	@Override
	protected int maxInSlot() {
		return 5;
	}

	@Override
	protected int maxProgressTime() {
		return 60;
	}

	/* DeviceRecipe */
	@Override
	public boolean continueProcess(Level level, BlockPos pos, BlockState state) {
		// priority check
		if (recipe != null) {
			NonNullList<ItemStack> inputs = this.inventory.getSizedList(0, maxInSlot());
			Optional<IDeviceRecipe> check = DCRecipes.getCookingRecipe(currentClimate, inputs, inputTank.getFluid());

			if (check.isPresent() && check.get().getPriority() == recipe.getPriority()) {
				boolean result = inventory.canInsertResult(recipe.getOutput(), maxInSlot() + 1, maxInSlot() + 2) > 0 && outputTank.fill(recipe.getOutputFluid(), FluidAction.SIMULATE) >= recipe
						.getOutputFluid()
						.getAmount();
				if (recipe.getSecondaryRate() > 0 && inventory.canInsertResult(recipe.getSecondaryOutput(), maxInSlot() + 1, maxInSlot() + 2) == 0) {
					result = false;
				}
				return result;
			}
		}
		return false;
	}

	@Override
	public boolean startProcess(Level level, BlockPos pos, BlockState state) {
		NonNullList<ItemStack> inputs = this.inventory.getSizedList(0, maxInSlot());
		Optional<IDeviceRecipe> check = DCRecipes.getCookingRecipe(currentClimate, inputs, inputTank.getFluid());
		if (check.isPresent()) {
			recipe = check.get();
			this.totalProgress = maxProgressTime();
			return true;
		}
		return false;
	}

	int count = 4;
	private ItemStack display = ItemStack.EMPTY;

	@Override
	public ItemStack getDisplay(int s) {
		return display;
	}

	@Override
	public void setDisplay(int s, ItemStack item) {
		display = item;
	}

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		super.onTickProcess(level, pos, state);
		if (count > 0) {
			count--;
			return false;
		} else {
			count = 4;

			if (!DCItemUtil.isSameItem(display, inventory.getItem(maxInSlot() + 1), false)) {
				display = inventory.getItem(maxInSlot() + 1).copy();
				MsgTileDisplayItemToC.sendToClient((ServerLevel) level, pos, display, 0);
			}
			return false;
		}

	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new CookingPotMenu(MachineInit.POT_MENU.get(), i, inv, this, this.dataAccess);
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		if (block == MachineInit.COOKING_POT_BLUE.get())
			return BLUE;
		if (block == MachineInit.COOKING_POT_BLACK.get())
			return BLACK;
		if (block == MachineInit.COOKING_POT_RED.get())
			return RED;
		if (block == MachineInit.COOKING_POT_GREEN.get())
			return GREEN;
		if (block == MachineInit.COOKING_POT_WHITE.get())
			return WHITE;
		if (block == MachineInit.COOKING_POT_NORMAL.get())
			return NORMAL;
		return NORMAL;
	}

	public static final EntityRenderData WHITE = new EntityRenderData("tile/steel_pot_white", 1F, -0.5F);
	public static final EntityRenderData BLUE = new EntityRenderData("tile/steel_pot_blue", 1F, -0.5F);
	public static final EntityRenderData BLACK = new EntityRenderData("tile/steel_pot_black", 1F, -0.5F);
	public static final EntityRenderData RED = new EntityRenderData("tile/steel_pot_red", 1F, -0.5F);
	public static final EntityRenderData GREEN = new EntityRenderData("tile/steel_pot_green", 1F, -0.5F);
	public static final EntityRenderData NORMAL = new EntityRenderData("tile/steel_pot_normal", 1F, -0.5F);

}
