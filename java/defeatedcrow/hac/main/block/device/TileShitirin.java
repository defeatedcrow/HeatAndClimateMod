package defeatedcrow.hac.main.block.device;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fluids.FluidContainerRegistry;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.client.gui.ContainerNormalChamber;

public class TileShitirin extends TileNormalChamber {

	private int lastTier = 0;
	private int lastBurn = 0;

	@Override
	public void updateTile() {
		if (!getWorld().isRemote) {

			DCAirflow air = DCAirflow.TIGHT;
			if (current != null) {
				air = current.getAirflow();
			}

			if (air.getID() > 0) {
				this.currentClimate = 5;
			} else {
				this.currentClimate = 4;
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
					}
				}
			}

			if (BlockShitirin.isLit(getWorld(), getPos()) != this.isActive()) {
				BlockShitirin.changeLitState(getWorld(), getPos(), isActive());
			}
		}
		super.updateTile();
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

	@Override
	public String getName() {
		return "dcs.gui.device.shitirin";
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerNormalChamber(this, playerInventory);
	}

}
