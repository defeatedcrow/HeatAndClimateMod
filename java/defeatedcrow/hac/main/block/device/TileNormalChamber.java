package defeatedcrow.hac.main.block.device;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.client.gui.ContainerNormalChamber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class TileNormalChamber extends TileChamberBase {

	private int lastTier = 0;
	private int lastBurn = 0;

	@Override
	public void updateTile() {
		if (!getWorld().isRemote) {

			DCAirflow air = DCAirflow.TIGHT;
			if (current != null) {
				air = current.getAirflow();
			}

			if (air.getID() == 3) {
				this.currentClimate = DCHeatTier.SMELTING.getID();
			} else if (air.getID() == 2) {
				this.currentClimate = DCHeatTier.KILN.getID();
			} else {
				this.currentClimate = DCHeatTier.OVEN.getID();
			}

			if (this.currentBurnTime == 0) {
				if (this.getStackInSlot(0) != null && getBurnTime(this.getStackInSlot(0)) > 0) {
					ItemStack cont = this.getStackInSlot(0).getItem().getContainerItem(this.getStackInSlot(0));
					if (cont == null && FluidContainerRegistry.isFilledContainer(this.getStackInSlot(0))) {
						cont = FluidContainerRegistry.drainFluidContainer(this.getStackInSlot(0).copy());
					}
					if (cont == null) {
						cont = new ItemStack(MainInit.miscDust, 1, 5); // 灰が出る
					}
					boolean flag = false;
					if (this.canInsertResult(cont) > 0) {
						this.currentBurnTime = getBurnTime(this.getStackInSlot(0));
						this.maxBurnTime = getBurnTime(this.getStackInSlot(0));
						this.decrStackSize(0, 1);
						this.insertResult(cont);
						this.markDirty();
						// DCLogger.debugLog("burntime " + this.currentBurnTime + ", " +
						// this.maxBurnTime);
					}
				}
			}

			if (BlockNormalChamber.isLit(getWorld(), getPos()) != this.isActive()) {
				BlockNormalChamber.changeLitState(getWorld(), getPos(), isActive());
			}
		}
		super.updateTile();
	}

	@Override
	public void onTickUpdate() {
	}

	@Override
	protected void onServerUpdate() {
		if (this.currentBurnTime > 0) {
			this.currentBurnTime--;
		}
	}

	public static int getBurnTime(ItemStack item) {
		int i = TileEntityFurnace.getItemBurnTime(item);
		int ret = i / 4;
		if (ret > 0 && ret < 50)
			ret = 50;
		return ret;
	}

	protected int canInsertResult(ItemStack item) {
		int ret = 0;
		if (item == null || item.getItem() == null)
			return 0;
		for (int i = 1; i < this.getSizeInventory(); i++) {
			if (this.getStackInSlot(i) == null) {
				ret = item.stackSize;
			} else {
				ret = this.isItemStackable(item, this.getStackInSlot(i));
			}
			if (ret > 0) {
				return ret;
			}
		}
		return 0;
	}

	/** itemの減少数を返す */
	protected int insertResult(ItemStack item) {
		if (item == null || item.getItem() == null)
			return 0;
		for (int i = 1; i < this.getSizeInventory(); i++) {
			if (this.getStackInSlot(i) == null) {
				this.incrStackInSlot(i, item.copy());
				return item.stackSize;
			} else {
				int size = this.isItemStackable(item, this.getStackInSlot(i));
				if (this.isItemStackable(item, this.getStackInSlot(i)) > 0) {
					this.getStackInSlot(i).stackSize += size;
					return size;
				}
			}
		}
		return 0;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	@Override
	public String getGuiID() {
		return "dcs_climate:normal_chamber";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerNormalChamber(this, playerInventory);
	}

}
