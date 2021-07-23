package defeatedcrow.hac.food.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.DrinkEntity;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DrinkItem extends FoodItemBase {

	public static String[] names = {
			"ginger",
			"kuzu",
			"tomato",
			"apple",
			"grape",
			"chai",
			"mulled_wine",
			"amazake",
			"eggnog"
	};

	public DrinkItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 8;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 8);
		String s = "items/food/drink_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	public static String getTypeName(int meta) {
		if (meta > 8) {
			meta = 8;
		}
		return names[meta];
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		DrinkEntity ret = new DrinkEntity(world, x, y, z, player)
				.setMetadata(i);
		return ret;
	}

	@Override
	public List<PotionEffect> getPotionEffect(int meta) {
		List<PotionEffect> ret = new ArrayList<PotionEffect>();
		if (meta > 5) {
			ret.add(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1));
		} else {
			ret.add(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
		}
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return 2;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.2F;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());
		if (stack == null)
			return;

		int i = stack.getItemDamage();
		if (i < 6) {
			String effName = I18n.format(MobEffects.INSTANT_HEALTH.getName());
			tooltip.add(TextFormatting.AQUA.toString() + effName);
		} else {
			String effName = I18n.format(MobEffects.INSTANT_HEALTH.getName());
			tooltip.add(TextFormatting.AQUA.toString() + effName + " II");
		}

	}

}
