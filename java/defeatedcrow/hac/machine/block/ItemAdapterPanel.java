package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCItemBlock;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
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
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(TextFormatting.BOLD.toString() + "Tier 3");
		tooltip.add(TextFormatting.AQUA.toString() + "Require cooling");
		tooltip.add(TextFormatting.AQUA.toString() + "Require pairing between sender and acceptor");
	}

}
