package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItemBlock;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdapterPanel extends DCItemBlock {

	public ItemAdapterPanel(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(TextFormatting.BOLD.toString() + "Tier 3");
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(DCName.HEAT.getLocalizedName() + ": " + TextFormatting.BLUE.toString() + "COOL-");
			if (this.block == MachineInit.adapterPanel) {
				tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
				tooltip.add(DCName.ITEM.getLocalizedName() + DCName.TRANSPORT.getLocalizedName() + ": 1 item/5t");
			} else if (this.block == MachineInit.adapterFluidPanel) {
				tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
				tooltip.add(DCName.FLUID.getLocalizedName() + DCName.TRANSPORT.getLocalizedName() + ": 1000 mB/5t");
			}
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.adapter"));
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}

}
