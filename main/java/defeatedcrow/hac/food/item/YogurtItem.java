package defeatedcrow.hac.food.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.YogurtPlainEntity;
import defeatedcrow.hac.food.entity.YogurtSPEntity;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class YogurtItem extends FoodItemBase {

	public YogurtItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 1;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(meta, 0, 1);
		String s = "items/food/yogurt_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = { "plain", "special" };
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new YogurtPlainEntity(world, x, y, z, player);
		if (i == 1) {
			ret = new YogurtSPEntity(world, x, y, z, player);
		}
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return 6;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.2F;
	}

	@Override
	public List<PotionEffect> getPotionEffect(int meta) {
		List<PotionEffect> ret = new ArrayList<PotionEffect>();
		if (meta == 1) {
			ret.add(new PotionEffect(MainInit.immunity, 3600, 0));
		} else {
			ret.add(new PotionEffect(MainInit.digestive, 1200, 0));
		}
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());

		if (stack.getItemDamage() == 0) {
			String effName2 = I18n.format(MainInit.digestive.getName());
			tooltip.add(TextFormatting.AQUA.toString() + effName2);
		} else {
			String effName = I18n.format(MainInit.immunity.getName());
			tooltip.add(TextFormatting.AQUA.toString() + effName);
		}

	}

}
