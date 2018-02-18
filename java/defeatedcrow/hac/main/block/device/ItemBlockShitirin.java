package defeatedcrow.hac.main.block.device;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockShitirin extends DCItemBlock {

	public ItemBlockShitirin(Block block) {
		super(block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(TextFormatting.BOLD.toString() + "Tier 1");
		tooltip.add(I18n.translateToLocal("dcs.tip.shitirin.description"));
	}

}
