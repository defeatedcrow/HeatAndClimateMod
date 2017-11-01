package defeatedcrow.hac.main.block.device;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.fluid.DCFluidUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.client.gui.ContainerNormalChamber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;

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
				if (!DCUtil.isEmpty(getStackInSlot(0)) && getFuel(this.getStackInSlot(0)) > 0) {
					ItemStack copy = getStackInSlot(0).copy();
					ItemStack cont = copy.getItem().getContainerItem(copy);
					if (DCUtil.isEmpty(cont)) {
						cont = DCFluidUtil.getEmptyCont(copy);
					}
					if (DCUtil.isEmpty(cont)) {
						cont = new ItemStack(MainInit.miscDust, 1, 5); // 灰が出る
					}
					boolean flag = false;
					if (this.canInsertResult(cont) > 0) {
						this.currentBurnTime = getFuel(copy);
						this.maxBurnTime = getFuel(copy);
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
	public void onTickUpdate() {}

	@Override
	protected void onServerUpdate() {
		if (this.currentBurnTime > 0) {
			this.currentBurnTime--;
		}
	}

	@Override
	public int getFuel(ItemStack item) {
		return getBurnTime(item);
	}

	protected int canInsertResult(ItemStack item) {
		int ret = 0;
		if (DCUtil.isEmpty(item))
			return 0;
		for (int i = 1; i < this.getSizeInventory(); i++) {
			if (DCUtil.isEmpty(getStackInSlot(i))) {
				ret = item.stackSize;
			} else {
				ret = this.isItemStackable(item, this.getStackInSlot(i));
			}
			if (ret > 0)
				return ret;
		}
		return 0;
	}

	/** itemの減少数を返す */
	protected int insertResult(ItemStack item) {
		if (DCUtil.isEmpty(item))
			return 0;
		for (int i = 1; i < this.getSizeInventory(); i++) {
			if (DCUtil.isEmpty(getStackInSlot(i))) {
				this.incrStackInSlot(i, item.copy());
				return item.stackSize;
			} else {
				int size = this.isItemStackable(item, this.getStackInSlot(i));
				if (size > 0) {
					DCUtil.addStackSize(getStackInSlot(i), size);
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

	@Override
	public boolean isSuitableClimate() {
		return currentClimate == DCHeatTier.SMELTING.getID();
	}

	@Override
	public List<String> climateSuitableMassage() {
		List<String> list = new ArrayList<String>();
		if (isSuitableClimate()) {
			list.add(I18n.translateToLocal("dcs.gui.message.suitable"));
		} else {
			list.add(I18n.translateToLocal("dcs.gui.message.require.wind"));
			list.add(I18n.translateToLocal("dcs.gui.message.require.wind2"));
		}
		return list;
	}

}
