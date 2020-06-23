package defeatedcrow.hac.food.block;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.food.gui.ContainerFluidProcessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class TileSkillet extends TileFluidProcessorBase {

	@Override
	public int getProcessTime() {
		if (current != null) {
			DCHeatTier tier = current.getHeat();
			int i = tier.getTier();
			if (i < 0) {
				i = 0;
			}
			int ret = 40 - i * 8;
			if (ret < 4) {
				ret = 4;
			}
			return ret;
		}
		return 40;
	}

	@Override
	public boolean isSuitableClimate() {
		// 問わず
		return current != null && current.getHeat().getTier() > DCHeatTier.HOT.getTier();
	}

	@Override
	public String climateSuitableMassage() {
		if (current == null)
			return "dcs.gui.message.nullclimate";
		else if (current.getHeat().getTier() < DCHeatTier.BOIL.getTier())
			return "dcs.gui.message.pottery.toocold";
		else
			return "dcs.gui.message.suitableclimate";
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerFluidProcessor(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return "dcs_climate:fluidprocessor_steel";
	}

}
