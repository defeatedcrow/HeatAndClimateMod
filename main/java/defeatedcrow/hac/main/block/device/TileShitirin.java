package defeatedcrow.hac.main.block.device;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.main.client.gui.ContainerNormalChamber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;

public class TileShitirin extends TileNormalChamber {

	private int lastTier = 0;
	private int lastBurn = 0;

	@Override
	public void updateTile() {
		super.updateTile();
		if (!getWorld().isRemote) {

			DCAirflow air = DCAirflow.TIGHT;
			if (current != null) {
				air = current.getAirflow();
			}

			if (air.getID() > 0) {
				this.currentClimate = DCHeatTier.OVEN.getID();
			} else {
				this.currentClimate = DCHeatTier.BOIL.getID();
			}

			if (BlockShitirin.isLit(getWorld(), getPos()) != this.isActive()) {
				BlockShitirin.changeLitState(getWorld(), getPos(), isActive());
			}
		}
	}

	/* === 燃焼判定 === */

	@Override
	protected void onServerUpdate() {
		if (this.currentBurnTime > 0 && BlockShitirin.isPower(getWorld(), getPos())) {
			this.currentBurnTime--;
		}
	}

	@Override
	public int getFuel(ItemStack item) {
		return getBurnTime(item) * 4;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

	@Override
	public String getName() {
		return "dcs.gui.device.shitirin";
	}

	@Override
	public boolean isSuitableClimate() {
		return currentClimate > DCHeatTier.HOT.getID();
	}

	@Override
	public List<String> climateSuitableMassage() {
		List<String> list = new ArrayList<String>();
		if (isSuitableClimate()) {
			list.add(I18n.translateToLocal("dcs.gui.message.suitable"));
		} else {
			list.add(I18n.translateToLocal("dcs.gui.message.require.airflow"));
			list.add(I18n.translateToLocal("dcs.gui.message.require.airflow2"));
		}
		return list;
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerNormalChamber(this, playerInventory);
	}

}
