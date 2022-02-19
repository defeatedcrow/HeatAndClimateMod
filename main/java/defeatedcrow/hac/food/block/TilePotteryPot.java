package defeatedcrow.hac.food.block;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.food.gui.ContainerFluidProcessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TilePotteryPot extends TileFluidProcessorBase {

	@Override
	public int getProcessTime() {
		if (current != null) {
			DCHeatTier tier = current.getHeat();
			int i = tier.getTier();
			if (i < 0) {
				i *= -1;
			}
			return 100 - i * 10;
		}
		return 100;
	}

	@Override
	public boolean isSuitableClimate() {
		// potteryは高温に耐えられない
		return current != null && current.getHeat().getTier() < DCHeatTier.SMELTING.getTier() && current.getHeat()
				.getTier() > DCHeatTier.FROSTBITE.getTier();
	}

	@Override
	public String climateSuitableMassage() {
		if (current == null)
			return "dcs.gui.message.nullclimate";
		else {
			if (current.getHeat().getTier() > DCHeatTier.KILN.getTier())
				return "dcs.gui.message.pottery.toohot";
			else if (current.getHeat().getTier() < DCHeatTier.COLD.getTier())
				return "dcs.gui.message.pottery.toocold";
			else
				return "dcs.gui.message.suitableclimate";
		}
	}

	@Override
	public int maxColor() {
		return 4;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.util.math.AxisAlignedBB getRenderBoundingBox() {
		net.minecraft.util.math.AxisAlignedBB bb = INFINITE_EXTENT_AABB;
		bb = new net.minecraft.util.math.AxisAlignedBB(getPos().add(0, 0, 0), getPos().add(1, 1, 1));
		return bb;
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerFluidProcessor(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return "dcs_climate:fluidprocessor_pottery";
	}

}
