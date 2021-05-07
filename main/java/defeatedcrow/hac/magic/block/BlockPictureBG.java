package defeatedcrow.hac.magic.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPictureBG extends BlockPictureBase {

	public BlockPictureBG(String s) {
		super(s);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TilePictureBG();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.color_picture.bg"));
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.color_picture.all"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.color_picture.bg"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.color_picture.bg2"));
			tooltip.add(TextFormatting.GRAY.toString() + TextFormatting.BOLD.toString() + "============");
			tooltip.add(TextFormatting.GRAY.toString() + I18n.format("dcs.comment.flavor.color_picture.bg"));
		} else {
			tooltip.add(TextFormatting.RESET.toString() + I18n.format("dcs.tip.shift"));
		}
	}

}
