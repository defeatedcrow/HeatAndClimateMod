package defeatedcrow.hac.food.item;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.core.util.DCPotion;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.capability.DrinkItemCustomizer;
import defeatedcrow.hac.food.capability.DrinkMilk;
import defeatedcrow.hac.food.capability.DrinkSugar;
import defeatedcrow.hac.food.capability.IDrinkCustomize;
import defeatedcrow.hac.food.entity.EntityTeaCupSilver;
import defeatedcrow.hac.food.entity.EntityTeaCupWhite;
import defeatedcrow.hac.plugin.DrinkPotionType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
		DrinkMilk milk = DrinkMilk.NONE;
		DrinkSugar sugar = DrinkSugar.NONE;
		IFluidHandler cont = item.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		IDrinkCustomize drink = item.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
		if (cont != null && cont.getTankProperties() != null) {
			f = cont.getTankProperties()[0].getContents();
		}
		if (drink != null) {
			milk = drink.getMilk();
			sugar = drink.getSugar();
		}
		DCEntityBase ret = null;
		switch (i) {
		case 1:
			ret = new EntityTeaCupWhite(world, x, y, z, player).setFluid(f).setCustom(milk, sugar);

			break;
		default:
			ret = new EntityTeaCupSilver(world, x, y, z, player).setFluid(f).setCustom(milk, sugar);
			;
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

	// カラなら飲食できない
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack item, World world, EntityPlayer player, EnumHand hand) {
		IFluidHandler cont = item.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		if (cont != null && cont.getTankProperties() != null) {
			FluidStack f = cont.getTankProperties()[0].getContents();
			if (f != null) {
				return super.onItemRightClick(item, world, player, hand);
			}
		}
		return new ActionResult(EnumActionResult.FAIL, item);
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
			IDrinkCustomize drink = item.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
			if (cont != null && cont.getTankProperties() != null) {
				FluidStack f = cont.getTankProperties()[0].getContents();
				float dirF = 1.0F;
				int ampF = 0;
				if (drink != null) {
					dirF *= drink.getMilk().effect;
					ampF += drink.getSugar().effect;

				}
				if (f != null && f.getFluid() != null) {
					if (f.getFluid() == FoodInit.greenTea) {
						ret.add(new PotionEffect(DCPotion.haste, MathHelper.ceiling_float_int(1200 * dirF), ampF));
					} else if (f.getFluid() == FoodInit.blackTea) {
						ret.add(new PotionEffect(DCPotion.registance, MathHelper.ceiling_float_int(1200 * dirF), ampF));
					} else if (f.getFluid() == FoodInit.coffee) {
						ret.add(new PotionEffect(DCPotion.night_vision, MathHelper.ceiling_float_int(1200 * dirF),
								ampF));
					} else if (f.getFluid() == FoodInit.oil) {
						ret.add(new PotionEffect(DCPotion.speed, MathHelper.ceiling_float_int(1200 * dirF), ampF));
					} else if (f.getFluid() == FluidRegistry.WATER) {
						ret.add(new PotionEffect(DCPotion.regeneration, MathHelper.ceiling_float_int(300 * dirF),
								ampF));
					} else if (f.getFluid() == FluidRegistry.LAVA) {
						ret.add(new PotionEffect(DCPotion.fire_reg, MathHelper.ceiling_float_int(1200 * dirF), ampF));
					} else if (DrinkPotionType.isRegistered(f.getFluid())) {
						Potion potion = DrinkPotionType.getPotion(f.getFluid());
						if (potion != null) {
							ret.add(new PotionEffect(potion, MathHelper.ceiling_float_int(1200 * dirF), ampF));
						}
					} else {
						ret.add(new PotionEffect(DCPotion.regeneration, MathHelper.ceiling_float_int(300 * dirF),
								ampF));
					}
				}
			}
		}
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {

		List<PotionEffect> effects = this.getPotionEffect(stack);
		if (!effects.isEmpty()) {
			PotionEffect eff = effects.get(0);
			if (eff != null && eff.getPotion() != null) {
				tooltip.add(TextFormatting.AQUA.toString() + I18n.translateToLocal(eff.getPotion().getName()));
			}
		}
		IFluidHandler cont = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		IDrinkCustomize drink = stack.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add("Placeable as an Entity");
			if (cont != null && cont.getTankProperties() != null) {
				FluidStack f = cont.getTankProperties()[0].getContents();
				if (f != null && f.getFluid() != null) {
					tooltip.add(TextFormatting.BOLD.toString() + "CONTAINED FLUID");
					tooltip.add("Fluid: " + f.getLocalizedName());
					tooltip.add("Amount: " + f.amount);
				}
				if (drink != null) {
					tooltip.add(TextFormatting.BOLD.toString() + "DRINK CUSTOMIZE");
					tooltip.add("Milk: " + drink.getMilk().toString());
					tooltip.add("Sugar: " + drink.getSugar().toString());
				}
			}

		} else {
			String mes = "";
			if (cont != null && cont.getTankProperties() != null) {
				FluidStack f = cont.getTankProperties()[0].getContents();
				if (f != null && f.getFluid() != null) {
					mes += f.getLocalizedName();
					if (drink != null) {
						if (drink.getMilk() == DrinkMilk.NONE) {
							if (drink.getSugar() == DrinkSugar.NONE) {
								mes += " BLACK";
							} else {
								mes += " " + drink.getSugar().toString();
							}
						} else {
							if (drink.getSugar() == DrinkSugar.NONE) {
								mes += " " + drink.getMilk().toString();
							} else {
								mes += " " + drink.getMilk().toString() + "," + drink.getSugar().toString();
							}
						}
					}
				} else {
					mes += "EMPTY";
				}
				tooltip.add(TextFormatting.BOLD.toString() + mes);
				tooltip.add(TextFormatting.RESET.toString() + I18n.translateToLocal("dcs.tip.shift"));
			}

		}
	}

	/* fluid */

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return this.new CapWrapper(stack);
	}

	private class CapWrapper implements ICapabilityProvider {

		private final ItemStack cont;

		private CapWrapper(ItemStack item) {
			cont = item;
		}

		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
				return true;
			} else if (capability == DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
				return (T) new FluidHandlerItemStack(cont, 200);
			} else if (capability == DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY) {
				return (T) new DrinkItemCustomizer(cont);
			} else {
				return null;
			}
		}
	}

}
