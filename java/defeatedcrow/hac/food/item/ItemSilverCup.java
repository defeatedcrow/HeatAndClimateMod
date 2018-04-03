package defeatedcrow.hac.food.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.capability.DrinkItemCustomizer;
import defeatedcrow.hac.food.capability.DrinkMilk;
import defeatedcrow.hac.food.capability.DrinkSugar;
import defeatedcrow.hac.food.capability.IDrinkCustomize;
import defeatedcrow.hac.food.entity.EntityTeaCupSilver;
import defeatedcrow.hac.food.entity.EntityTeaCupWhite;
import defeatedcrow.hac.food.entity.EntityTumbler;
import defeatedcrow.hac.plugin.DrinkPotionType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
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
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
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
		return 2;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 1);
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
				"white",
				"glass"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FluidStack f = null;
		DrinkMilk milk = DrinkMilk.NONE;
		DrinkSugar sugar = DrinkSugar.NONE;
		IFluidHandlerItem cont = item.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		IDrinkCustomize drink = item.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
		if (cont != null && cont.getTankProperties() != null && cont.getTankProperties().length > 0) {
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
		case 2:
			ret = new EntityTumbler(world, x, y, z, player).setFluid(f).setCustom(milk, sugar);
			break;
		default:
			ret = new EntityTeaCupSilver(world, x, y, z, player).setFluid(f).setCustom(milk, sugar);
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
		return 0.25F;
	}

	@Override
	public ItemStack getFoodContainerItem(ItemStack item) {
		ItemStack ret = new ItemStack(this, 1, item.getItemDamage());
		return ret;
	}

	// カラなら飲食できない
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (player != null) {
			ItemStack item = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(item)) {
				IFluidHandlerItem cont = item.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
				if (cont != null && cont.getTankProperties() != null && cont.getTankProperties().length > 0) {
					FluidStack f = cont.getTankProperties()[0].getContents();
					if (f != null)
						return super.onItemRightClick(world, player, hand);
				}
			}
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, item);
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, ItemStack.EMPTY);
	}

	// potion の取得方法が違う
	@Override
	public boolean addEffects(ItemStack stack, World world, EntityLivingBase living) {
		if (!world.isRemote && !DCUtil.isEmpty(stack)) {
			if (stack.getItem() != this)
				return false;
			else {
				IFluidHandlerItem cont = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY,
						null);
				IDrinkCustomize drink = stack.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
				if (cont != null && cont.getTankProperties() != null && cont.getTankProperties().length > 0) {
					FluidStack f = cont.getTankProperties()[0].getContents();
					float dirF = 1.0F;
					int ampF = 0;

					if (drink != null) {
						dirF *= drink.getMilk().effect;
						ampF += drink.getSugar().effect;
					}
					if (f != null && f.getFluid() != null) {
						Fluid milk = FluidRegistry.getFluid("milk");
						if ((milk != null && f.getFluid() == milk) || f.getFluid() == FoodInit.tomatoJuice) {
							living.clearActivePotions();
						} else {
							List<PotionEffect> effects = this.getPotionEffect(f.getFluid(), dirF, ampF);
							if (effects.isEmpty())
								return false;
							for (PotionEffect get : effects) {
								if (get != null && get.getPotion() != null) {
									Potion por = get.getPotion();
									if (por == null) {
										continue;
									}
									int amp = get.getAmplifier();
									int dur = get.getDuration();
									if (living.isPotionActive(get.getPotion())) {
										PotionEffect check = living.getActivePotionEffect(por);
										dur += check.getDuration();
									}
									living.addPotionEffect(new PotionEffect(get.getPotion(), dur, amp));
								}
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	public static List<PotionEffect> getPotionEffect(Fluid fluid, float dirF, int ampF) {
		List<PotionEffect> ret = new ArrayList<PotionEffect>();
		if (fluid != null) {
			if (fluid == FoodInit.greenTea) {
				ret.add(new PotionEffect(MobEffects.HASTE, MathHelper.ceil(1200 * dirF), ampF));
			} else if (fluid == FoodInit.blackTea) {
				ret.add(new PotionEffect(MobEffects.RESISTANCE, MathHelper.ceil(1200 * dirF), ampF));
			} else if (fluid == FoodInit.coffee) {
				ret.add(new PotionEffect(MobEffects.NIGHT_VISION, MathHelper.ceil(1200 * dirF), ampF));
			} else if (fluid == FoodInit.oil) {
				ret.add(new PotionEffect(MobEffects.SPEED, MathHelper.ceil(1200 * dirF), ampF));
			} else if (fluid == FoodInit.stock) {
				ret.add(new PotionEffect(MobEffects.FIRE_RESISTANCE, MathHelper.ceil(1200 * (dirF + ampF)), 0));
			} else if (fluid == FoodInit.blackLiquor) {
				ret.add(new PotionEffect(MobEffects.POISON, MathHelper.ceil(300 * dirF), ampF));
			} else if (fluid == FoodInit.lemon) {
				ret.add(new PotionEffect(MobEffects.JUMP_BOOST, MathHelper.ceil(1200 * dirF), ampF));
			} else if (fluid == FluidRegistry.WATER) {
				ret.add(new PotionEffect(MobEffects.REGENERATION, MathHelper.ceil(300 * dirF), ampF));
			} else if (fluid == FluidRegistry.LAVA) {
				ret.add(new PotionEffect(MobEffects.FIRE_RESISTANCE, MathHelper.ceil(1200 * dirF), ampF));
			} else if (DrinkPotionType.isRegistered(fluid)) {
				Potion potion = DrinkPotionType.getPotion(fluid);
				if (potion != null) {
					float duration = potion.isBadEffect() ? 600 * dirF : 1200 * dirF;
					ret.add(new PotionEffect(potion, MathHelper.ceil(duration), ampF));
				}
			} else {
				ret.add(new PotionEffect(MobEffects.REGENERATION, MathHelper.ceil(300 * dirF), ampF));
			}
		}
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {

		IFluidHandlerItem cont = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		IDrinkCustomize drink = stack.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);

		if (cont != null && cont.getTankProperties() != null && drink != null && cont.getTankProperties().length > 0) {
			FluidStack f = cont.getTankProperties()[0].getContents();
			float dirF = 1.0F + drink.getMilk().effect;
			int ampF = drink.getSugar().effect;
			if (f != null && f.getFluid() != null) {
				Fluid milk = FluidRegistry.getFluid("milk");
				if ((milk != null && f.getFluid() == milk) || f.getFluid() == FoodInit.tomatoJuice) {
					tooltip.add(TextFormatting.AQUA.toString() + "clear all potion effects.");
				} else {
					List<PotionEffect> effects = this.getPotionEffect(f.getFluid(), dirF, ampF);
					if (!effects.isEmpty()) {
						PotionEffect eff = effects.get(0);
						if (eff != null && eff.getPotion() != null) {
							String effName = I18n.translateToLocal(eff.getEffectName());
							effName += " " + I18n.translateToLocal("potion.potency." + eff.getAmplifier()).trim();
							effName += " (" + Potion.getPotionDurationString(eff, 1.0F) + ")";
							tooltip.add(TextFormatting.AQUA.toString() + effName);
						}
					}
				}
				if (ClimateCore.proxy.isShiftKeyDown()) {
					tooltip.add("Placeable as an Entity");
					tooltip.add(TextFormatting.BOLD.toString() + "= CONTAINED FLUID =");
					tooltip.add("Fluid: " + f.getLocalizedName());
					tooltip.add("Amount: " + f.amount);

					tooltip.add(TextFormatting.BOLD.toString() + "= DRINK CUSTOMIZE =");
					tooltip.add("Milk: " + drink.getMilk().toString());
					tooltip.add("Sugar: " + drink.getSugar().toString());
				} else {
					String mes = "";
					mes += f.getLocalizedName();
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
					tooltip.add(TextFormatting.BOLD.toString() + mes);
					tooltip.add(TextFormatting.RESET.toString() + I18n.translateToLocal("dcs.tip.shift"));
				}
			} else {
				tooltip.add(TextFormatting.BOLD.toString() + "EMPTY");
			}
		} else {
			tooltip.add(TextFormatting.BOLD.toString() + "EMPTY");
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
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY)
				return true;
			else if (capability == DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY)
				return true;
			else
				return false;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY)
				return (T) new FluidHandlerItemStack(cont, 200);
			else if (capability == DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY)
				return (T) new DrinkItemCustomizer(cont);
			else
				return null;
		}
	}

}
