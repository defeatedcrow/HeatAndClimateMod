package defeatedcrow.hac.food.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.core.util.DCPotion;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.entity.EntityTeaCupSilver;
import defeatedcrow.hac.food.entity.EntityTeaCupWhite;

public class ItemSilverCup extends FoodItemBase {

	public ItemSilverCup(boolean isWolfFood) {
		super(isWolfFood);
		this.setMaxStackSize(1);
	}

	@Override
	public int getMaxMeta() {
		return 1;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp_int(0, meta, 1);
		String s = "items/food/cup_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"silver",
				"white" };
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FluidStack f = null;
		IFluidHandler cont = item.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		if (cont != null && cont.getTankProperties() != null) {
			f = cont.getTankProperties()[0].getContents();
		}
		DCEntityBase ret = null;
		switch (i) {
		case 1:
			ret = new EntityTeaCupWhite(world, x, y, z, player).setFluid(f);

			break;
		default:
			ret = new EntityTeaCupSilver(world, x, y, z, player).setFluid(f);
			break;
		}

		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return 1;
	}

	@Override
	public float getSaturation(int meta) {
		return (meta & 1) == 0 ? 0F : 0.5F;
	}

	@Override
	public ItemStack getFoodContainerItem(ItemStack item) {
		ItemStack ret = new ItemStack(this, 1, item.getItemDamage());
		return ret;
	}

	// potion の取得方法が違う
	@Override
	public boolean addEffects(ItemStack stack, World worldIn, EntityLivingBase living) {
		if (!worldIn.isRemote && stack != null) {
			List<PotionEffect> effects = this.getPotionEffect(stack);
			if (effects.isEmpty())
				return false;
			for (PotionEffect get : effects) {
				if (get != null && get.getPotion() != null) {
					Potion por = get.getPotion();
					if (por == null)
						continue;
					int amp = get.getAmplifier();
					int dur = get.getDuration();
					if (living.isPotionActive(get.getPotion())) {
						PotionEffect check = living.getActivePotionEffect(por);
						dur += check.getDuration();
					}
					living.addPotionEffect(new PotionEffect(get.getPotion(), dur, amp));
				}
			}
			return true;
		}
		return false;
	}

	public List<PotionEffect> getPotionEffect(ItemStack item) {
		List<PotionEffect> ret = new ArrayList<PotionEffect>();
		if (item == null || item.getItem() == null || item.getItem() != this) {
			return ret;
		} else {
			IFluidHandler cont = item.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
			if (cont != null && cont.getTankProperties() != null) {
				FluidStack f = cont.getTankProperties()[0].getContents();
				if (f != null && f.getFluid() != null) {
					if (f.getFluid() == FoodInit.greenTea) {
						ret.add(new PotionEffect(DCPotion.haste, 1200, 0));
					} else if (f.getFluid() == FoodInit.blackTea) {
						ret.add(new PotionEffect(DCPotion.registance, 1200, 0));
					} else if (f.getFluid() == FoodInit.coffee) {
						ret.add(new PotionEffect(DCPotion.night_vision, 1200, 0));
					} else if (f.getFluid() == FoodInit.oil) {
						ret.add(new PotionEffect(DCPotion.speed, 1200, 0));
					} else if (f.getFluid() == FluidRegistry.WATER) {
						ret.add(new PotionEffect(DCPotion.regeneration, 300, 0));
					} else if (f.getFluid() == FluidRegistry.LAVA) {
						ret.add(new PotionEffect(DCPotion.fire_reg, 1200, 0));
					} else {
						ret.add(new PotionEffect(DCPotion.regeneration, 1200, 0));
					}
				}
			}
		}
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("Placeable as an Entity");
		IFluidHandler cont = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		if (cont != null && cont.getTankProperties() != null) {
			FluidStack f = cont.getTankProperties()[0].getContents();
			if (f != null && f.getFluid() != null) {
				tooltip.add("Fluid: " + f.getLocalizedName());
				tooltip.add("Amount: " + f.amount);
			}
		}
	}

	/* fluid */

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new FluidHandlerItemStack(stack, 200);
	}

}
