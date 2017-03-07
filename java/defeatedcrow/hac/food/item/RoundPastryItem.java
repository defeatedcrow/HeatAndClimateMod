package defeatedcrow.hac.food.item;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.AppleTartEntity;
import defeatedcrow.hac.food.entity.LemonTartEntity;
import defeatedcrow.hac.food.entity.PotatoQuicheEntity;
import defeatedcrow.hac.food.entity.SpinachQuicheEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RoundPastryItem extends FoodItemBase {

	public RoundPastryItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 7;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp_int(0, meta, 1);
		String s = "items/food/tart_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"apple_raw",
				"apple_baked",
				"lemon_raw",
				"lemon_baked",
				"spinach_raw",
				"spinach_baked",
				"potato_raw",
				"potato_baked"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = null;
		switch (i) {
		case 0:
		case 1:
			ret = new AppleTartEntity(world, x, y, z, player);
			break;
		case 2:
		case 3:
			ret = new LemonTartEntity(world, x, y, z, player);
			break;
		case 4:
		case 5:
			ret = new SpinachQuicheEntity(world, x, y, z, player);
			break;
		case 6:
		case 7:
			ret = new PotatoQuicheEntity(world, x, y, z, player);
			break;
		default:
			ret = new AppleTartEntity(world, x, y, z, player);
		}

		if (ret != null && (i & 1) == 0) {
			ret.setRAW(true);
		}
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		switch (meta) {
		case 1:
		case 3:
		case 5:
		case 7:
			return 10;
		default:
			return 1;
		}
	}

	@Override
	public float getSaturation(int meta) {
		return (meta & 1) == 0 ? 0F : 0.8F;
	}

	@Override
	public List<PotionEffect> getPotionEffect(int meta) {
		List<PotionEffect> ret = new ArrayList<PotionEffect>();
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("Placeable as an Entity");
	}

}
