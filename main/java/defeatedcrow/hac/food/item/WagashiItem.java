package defeatedcrow.hac.food.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.WagashiButterEntity;
import defeatedcrow.hac.food.entity.WagashiIsobeEntity;
import defeatedcrow.hac.food.entity.WagashiKinakoEntity;
import defeatedcrow.hac.food.entity.WagashiKurimanjuEntity;
import defeatedcrow.hac.food.entity.WagashiKurumiEntity;
import defeatedcrow.hac.food.entity.WagashiNerikiriEntity;
import defeatedcrow.hac.food.entity.WagashiStrawberryEntity;
import defeatedcrow.hac.food.entity.WagashiZundaEntity;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WagashiItem extends FoodItemBase {

	public WagashiItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 7;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 7);
		String s = "items/food/wagashi_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/wagashi_" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
			"kinako",
			"isobe",
			"zunda",
			"butter",
			"strawberry",
			"kurumi",
			"kurimanju",
			"nerikiri",
			"abekawa" };
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new WagashiKinakoEntity(world, x, y, z, player);
		switch (i) {
		case 1:
			ret = new WagashiIsobeEntity(world, x, y, z, player);
			break;
		case 2:
			ret = new WagashiZundaEntity(world, x, y, z, player);
			break;
		case 3:
			ret = new WagashiButterEntity(world, x, y, z, player);
			break;
		case 4:
			ret = new WagashiStrawberryEntity(world, x, y, z, player);
			break;
		case 5:
			ret = new WagashiKurumiEntity(world, x, y, z, player);
			break;
		case 6:
			ret = new WagashiKurimanjuEntity(world, x, y, z, player);
			break;
		case 7:
			ret = new WagashiNerikiriEntity(world, x, y, z, player);
			break;
		}

		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return 6;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.3F;
	}

	@Override
	public List<PotionEffect> getPotionEffect(int meta) {
		List<PotionEffect> ret = new ArrayList<PotionEffect>();
		ret.add(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());
		String effName = I18n.format(MobEffects.INSTANT_HEALTH.getName());
		tooltip.add(TextFormatting.AQUA.toString() + effName);
	}

}
