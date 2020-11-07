package defeatedcrow.hac.food.item.brewing;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEssentialOil extends DCItem {

	private final int maxMeta;

	private static String[] names = { "rose", "citrus", "mint", "birch", "lotus", "lavender" };

	public ItemEssentialOil() {
		super();
		maxMeta = 5;
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
		String s = "items/food/brewing/oil_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick2(World world, EntityPlayer player, EnumHand hand) {
		if (player != null) {
			ItemStack stack = player.getHeldItem(hand);
			if (!world.isRemote && !DCUtil.isEmpty(stack)) {
				EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(player.world);
				cloud.copyLocationAndAnglesFrom(player);
				cloud.setOwner(player);
				cloud.setRadius(5.0F);
				cloud.setRadiusOnUse(-1.0F);
				cloud.setWaitTime(10);
				cloud.setDuration(3600);
				cloud.setRadiusPerTick(-cloud.getRadius() / cloud.getDuration());
				cloud.setPotion(getType(stack.getItemDamage()));

				player.world.spawnEntity(cloud);
			}

			if (!player.capabilities.isCreativeMode) {
				DCUtil.reduceStackSize(stack, 1);
			}
			return new ActionResult(EnumActionResult.SUCCESS, stack);
		} else {
			return super.onItemRightClick(world, player, hand);
		}
	}

	public PotionType getType(int meta) {
		String name = "dcs.essence_" + meta;
		return new PotionType(name, new PotionEffect[] { getEffect(meta) });
	}

	public PotionEffect getEffect(int meta) {

		switch (meta) {
		case 0:
			return new PotionEffect(MainInit.wideMining, 14400);
		case 1:
			return new PotionEffect(MainInit.immunity, 14400);
		case 2:
			return new PotionEffect(MainInit.ocean, 14400);
		case 3:
			return new PotionEffect(MainInit.reflexion, 14400);
		case 4:
			return new PotionEffect(MainInit.absorptionEXP, 14400);
		case 5:
			return new PotionEffect(MainInit.warp, 14400);
		}
		return new PotionEffect(MainInit.wideMining, 14400);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		if (!DCUtil.isEmpty(stack)) {
			int m = stack.getItemDamage();
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.essencial_oil"));
			PotionEffect eff = getEffect(m);
			if (eff != null && eff.getPotion() != null) {
				String effName = I18n.format(eff.getEffectName());
				effName += " " + I18n.format("potion.potency." + eff.getAmplifier()).trim();
				effName += " (" + Potion.getPotionDurationString(eff, 0.25F) + ")";
				tooltip.add(TextFormatting.AQUA.toString() + effName);
			}
		}
	}

}
