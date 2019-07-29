package defeatedcrow.hac.food.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.DrinkGingerEntity;
import defeatedcrow.hac.food.entity.DrinkKuzuEntity;
import defeatedcrow.hac.food.entity.DrinkTomatoEntity;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DrinkItem extends FoodItemBase {

	public DrinkItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 2;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 2);
		String s = "items/food/drink_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = { "ginger", "kuzu", "tomato" };
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new DrinkGingerEntity(world, x, y, z, player);
		if (i == 1) {
			ret = new DrinkKuzuEntity(world, x, y, z, player);
		}
		if (i == 2) {
			ret = new DrinkTomatoEntity(world, x, y, z, player);
		}
		return ret;
	}

	@Override
	public List<PotionEffect> getPotionEffect(int meta) {
		List<PotionEffect> ret = new ArrayList<PotionEffect>();
		ret.add(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
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
	}

}
