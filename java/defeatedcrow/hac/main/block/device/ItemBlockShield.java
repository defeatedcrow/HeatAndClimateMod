package defeatedcrow.hac.main.block.device;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCItemBlock;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockShield extends DCItemBlock {

	public ItemBlockShield(Block block) {
		super(block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		String name = "NONE";
		if (stack.hasTagCompound()) {
			NBTTagCompound tag = stack.getTagCompound();
			String owner = tag.getString("owner");
			if (owner != null) {
				name = owner;
			}
		}
		tooltip.add(TextFormatting.BOLD.toString() + "Owner: " + name);
		tooltip.add(I18n.translateToLocal("dcs.tip.acv_shield"));
	}

}
