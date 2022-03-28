package defeatedcrow.hac.main.item.tool;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.IColorChangeTile;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorChanger extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"silver"
	};

	public ItemColorChanger() {
		super();
		maxMeta = 0;
		this.setFull3D();
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (!DCUtil.isEmpty(stack)) {
			TileEntity tile = world.getTileEntity(pos);
			if (player.canPlayerEdit(pos, facing, stack) && tile instanceof IColorChangeTile) {
				IColorChangeTile target = (IColorChangeTile) tile;
				target.rotateColor();
				DCLogger.debugInfoLog("color" + target.getColor());
				world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, itemRand
						.nextFloat() * 0.4F + 0.8F);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/tool/color_changer";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, World world, List<String> tooltip) {
		if (stack != null) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.color_changer"));
		}
	}

}
