package defeatedcrow.hac.magic.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEXPGem extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"exp"
	};

	public ItemEXPGem() {
		super();
		maxMeta = 0;
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
		String s = "items/magic/gem_exp";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (player != null) {
			ItemStack stack = player.getHeldItem(hand);
			if (!player.capabilities.isCreativeMode) {
				DCUtil.reduceStackSize(stack, 1);
			}
			if (!world.isRemote) {
				player.addExperienceLevel(10);
			}
			return new ActionResult(EnumActionResult.SUCCESS, stack);
		} else {
			return super.onItemRightClick(world, player, hand);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		if (!DCUtil.isEmpty(stack)) {
			int m = stack.getItemDamage();
			if (m == 0) {
				tooltip.add(I18n.translateToLocal("dcs.tip.exp_gem.description"));
				tooltip.add("10 Level");
			}
		}
	}

}
