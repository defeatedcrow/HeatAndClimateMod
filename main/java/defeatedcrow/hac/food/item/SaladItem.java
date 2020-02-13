package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.SaladGreenEntity;
import defeatedcrow.hac.food.entity.SaladLotusrootEntity;
import defeatedcrow.hac.food.entity.SaladPotatoEntity;
import defeatedcrow.hac.food.entity.SaladSalmonEntity;
import defeatedcrow.hac.food.entity.SaladTofuEntity;
import defeatedcrow.hac.food.entity.SaladWalnutEntity;
import defeatedcrow.hac.food.entity.SimmeredBeansEntity;
import defeatedcrow.hac.food.entity.SimmeredGomokuEntity;
import defeatedcrow.hac.food.entity.SimmeredNattoEntity;
import defeatedcrow.hac.food.entity.SimmeredPumpkinEntity;
import defeatedcrow.hac.food.entity.SimmeredSoyEntity;
import defeatedcrow.hac.food.entity.SimmeredSpinachEntity;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SaladItem extends FoodItemBase {

	public SaladItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 11;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 11);
		String s = "items/food/" + this.getNameSuffix()[i];
		if (i < 2 || i > 8) {
			s += "salad_" + this.getNameSuffix()[i];
		} else {
			s += "simmered_" + this.getNameSuffix()[i];
		}
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
			"green",
			"potato",
			"lotusroot",
			"soy",
			"gomoku",
			"spinach",
			"beans",
			"natto",
			"pumpkin",
			"salmon",
			"tofu",
			"walnut" };
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new SaladGreenEntity(world, x, y, z, player);
		if (i == 1) {
			ret = new SaladPotatoEntity(world, x, y, z, player);
		}
		if (i == 2) {
			ret = new SaladLotusrootEntity(world, x, y, z, player);
		}
		if (i == 3) {
			ret = new SimmeredSoyEntity(world, x, y, z, player);
		}
		if (i == 4) {
			ret = new SimmeredGomokuEntity(world, x, y, z, player);
		}
		if (i == 5) {
			ret = new SimmeredSpinachEntity(world, x, y, z, player);
		}
		if (i == 6) {
			ret = new SimmeredBeansEntity(world, x, y, z, player);
		}
		if (i == 7) {
			ret = new SimmeredNattoEntity(world, x, y, z, player);
		}
		if (i == 8) {
			ret = new SimmeredPumpkinEntity(world, x, y, z, player);
		}
		if (i == 9) {
			ret = new SaladSalmonEntity(world, x, y, z, player);
		}
		if (i == 10) {
			ret = new SaladTofuEntity(world, x, y, z, player);
		}
		if (i == 11) {
			ret = new SaladWalnutEntity(world, x, y, z, player);
		}
		ret.setIndividual(world.rand.nextInt(32));
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return meta == 1 || meta == 4 ? 8 : 6;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.25F;
	}

	@Override
	public boolean addEffects(ItemStack stack, World worldIn, EntityLivingBase living) {
		if (!worldIn.isRemote && stack != null) {
			List<PotionEffect> rem = Lists.newArrayList();
			rem.addAll(living.getActivePotionEffects());
			for (PotionEffect eff : rem) {
				if (eff != null && eff.getPotion().isBadEffect())
					living.removeActivePotionEffect(eff.getPotion());
			}
			return true;
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.clear_potion"));
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());
	}

}
