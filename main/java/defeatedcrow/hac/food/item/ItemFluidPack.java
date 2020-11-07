package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.capability.DrinkItemCustomizer;
import defeatedcrow.hac.food.capability.DrinkMilk;
import defeatedcrow.hac.food.capability.DrinkSugar;
import defeatedcrow.hac.food.capability.IDrinkCustomize;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.DCName;
import defeatedcrow.hac.plugin.DCIntegrationCore;
import defeatedcrow.hac.plugin.sd.DCThirstHelperSD;
import defeatedcrow.hac.plugin.tan.DCThirstHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFluidPack extends DCItem {

	private static String[] names = {
		"empty",
		"water",
		"milk",
		"cream",
		"oil",
		"vegi",
		"lemon",
		"mazai",
		"greentea",
		"tea",
		"coffee",
		"stock",
		"ethanol",
		"soy_milk",
		"fuel_oil",
		"sulfuric_acid",
		"nitric_acid",
		"fuel_gas",
		"hydrogen",
		"nitrogen",
		"ammonia",
		"oxygen" };

	public static final String[] FLUIDS = {
		"empty",
		"water",
		"dcs.raw_milk",
		"dcs.milk_cream",
		"dcs.seed_oil",
		"dcs.vegetable_juice",
		"dcs.lemonade",
		"dcs.mazai",
		"dcs.green_tea",
		"dcs.black_tea",
		"dcs.black_coffee",
		"dcs.stock",
		"dcs.ethanol",
		"dcs.soy_milk",
		"dcs.fuel_oil",
		"dcs.sulfuric_acid",
		"dcs.nitric_acid",
		"dcs.fuel_gas",
		"dcs.hydrogen",
		"dcs.nitrogen",
		"dcs.ammonia",
		"dcs.oxygen" };

	public ItemFluidPack() {
		super();
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		if (stack.getItemDamage() == 0)
			return ItemStack.EMPTY;
		return new ItemStack(this);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return stack.getItemDamage() != 0;
	}

	@Override
	public int getMaxMeta() {
		return 21;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = meta;
		if (i > getMaxMeta()) {
			i = getMaxMeta();
		}
		String s = "items/food/pack_" + names[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		int i = stack.getMetadata();
		if (i == 4)
			return 1600;
		else if (i == 12)
			return 1600;
		else if (i == 14)
			return 3200;
		else if (i == 17)
			return 3200;
		else
			return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		if (stack == null)
			return;

		String s = "";
		int i = stack.getItemDamage();
		if (i > getMaxMeta()) {
			i = getMaxMeta();
		}

		Fluid f = FluidRegistry.getFluid(FLUIDS[i]);
		if (f != null) {
			tooltip.add(TextFormatting.BOLD.toString() + DCName.DRINK_CUSTOMIZE.getLocalizedName());
			IDrinkCustomize drink = stack.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
			float durF = 1.0F;
			int ampF = 0;
			int l = 0;
			if (drink != null) {
				durF *= drink.getMilk().effect;
				ampF += drink.getSugar().effect;
				l = drink.getAgingLevel();
				if (l > 0) {
					ampF++;
					durF *= l;
				}
			}

			String mes = "";
			mes += DCName.FLUID.getLocalizedName() + ": " + f.getLocalizedName(new FluidStack(f, 200));
			if (drink != null) {
				String mes2 = "";
				if (drink.getMilk() != DrinkMilk.NONE) {
					mes2 += drink.getMilk().toString();
				}
				if (drink.getSugar() != DrinkSugar.NONE) {
					if (mes2.length() > 1) {
						mes2 += ",";
					}
					mes2 += drink.getSugar().toString();
				}
				if (l > 0) {
					mes += (" (Aged)");
				}
				if (mes2.length() > 1) {
					mes += " (" + mes2 + ")";
				}
			}
			tooltip.add(TextFormatting.YELLOW.toString() + mes);
			tooltip.add(TextFormatting.YELLOW.toString() + DCName.AMOUNT.getLocalizedName() + ": " + 250);
			Fluid milk = FluidRegistry.getFluid("milk");
			if (i == 2 || i == 13 || f == MainInit.tomatoJuice) {
				tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.clear_potion"));
			} else if (f == MainInit.mazai) {
				tooltip.add(TextFormatting.RED.toString() + I18n.format("dcs.tip.danger_drink"));
			} else {
				List<PotionEffect> effects = ItemSilverCup.getPotionEffect(f, durF, ampF);
				if (!effects.isEmpty()) {
					PotionEffect eff = effects.get(0);
					if (eff != null && eff.getPotion() != null) {
						String effName = I18n.format(eff.getEffectName());
						effName += " " + I18n.format("potion.potency." + eff.getAmplifier()).trim();
						effName += " (" + Potion.getPotionDurationString(eff, 1.0F) + ")";
						tooltip.add(TextFormatting.AQUA.toString() + effName);
					}
				}
			}
		} else {
			tooltip.add(TextFormatting.YELLOW.toString() + "Fluid is empty: " + FLUIDS[i]);
		}

		if (FluidRegistry.getFluid("dcs.raw_milk") != null) {
			if (i == 2) {
				tooltip.add(I18n.format("dcs.tip.correct_from_cow"));
			}
		} else {
			if (i == 3) {
				tooltip.add(I18n.format("dcs.tip.correct_from_cow"));
			}
		}
	}

	public static String getFluidName(int meta) {
		if (meta > 21) {
			meta = 21;
		}
		return FLUIDS[meta];
	}

	public static Fluid getFluid(int meta) {
		String name = getFluidName(meta);
		return FluidRegistry.getFluid(name);
	}

	public static int getMetaFromFluid(Fluid fluid) {
		int meta = 0;
		if (fluid == FluidRegistry.WATER) {
			meta = 1;
		} else if (fluid == MainInit.milk || FluidDictionaryDC.matchFluidName(fluid, "milk")) {
			meta = 2;
		} else if (fluid == MainInit.cream) {
			meta = 3;
		} else if (fluid == MainInit.oil) {
			meta = 4;
		} else if (fluid == MainInit.tomatoJuice) {
			meta = 5;
		} else if (fluid == MainInit.lemon) {
			meta = 6;
		} else if (fluid == MainInit.mazai) {
			meta = 7;
		} else if (fluid == MainInit.greenTea) {
			meta = 8;
		} else if (fluid == MainInit.blackTea) {
			meta = 9;
		} else if (fluid == MainInit.coffee) {
			meta = 10;
		} else if (fluid == MainInit.stock) {
			meta = 11;
		} else if (fluid == MainInit.ethanol) {
			meta = 12;
		} else if (fluid == MainInit.soyMilk) {
			meta = 13;
		} else if (fluid == MainInit.fuelOil) {
			meta = 14;
		} else if (fluid == MainInit.sulfuricAcid) {
			meta = 15;
		} else if (fluid == MainInit.nitricAcid) {
			meta = 16;
		} else if (fluid == MainInit.fuelGas) {
			meta = 17;
		} else if (fluid == MainInit.hydrogen) {
			meta = 18;
		} else if (fluid == MainInit.nitrogen) {
			meta = 19;
		} else if (fluid == MainInit.ammonia) {
			meta = 20;
		} else if (fluid == MainInit.oxygen) {
			meta = 21;
		}
		return meta;
	}

	/* 飲用効果 */

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target,
			EnumHand hand) {
		if (!DCUtil.isEmpty(stack) && stack.getMetadata() == 0) {
			if (target instanceof EntityCow) {
				if (!player.world.isRemote) {
					EntityItem drop = new EntityItem(player.world, player.posX, player.posY + 0.5D, player.posZ,
							new ItemStack(this, 1, 2));
					player.world.spawnEntity(drop);
				}
				if (!player.capabilities.isCreativeMode) {
					DCUtil.reduceStackSize(stack, 1);
				}
				return true;
			}
		} else if (this.addEffects(stack, player.world, target)) {
			this.dropContainerItem(player.world, stack, player);
			DCUtil.reduceStackSize(stack, 1);
			return true;
		}
		return super.itemInteractionForEntity(stack, player, target, hand);
	}

	// カラなら飲食できない
	@Override
	public ActionResult<ItemStack> onItemRightClick2(World world, EntityPlayer player, EnumHand hand) {
		if (player != null) {
			ItemStack item = player.getHeldItem(hand);
			/* 水のみ直接汲むことができる */
			if (!DCUtil.isEmpty(item)) {
				RayTraceResult res = this.rayTrace(world, player, true);
				if (item.getItemDamage() == 0) {
					if (res != null && res.typeOfHit == RayTraceResult.Type.BLOCK) {
						BlockPos pos = res.getBlockPos();
						if (world.isBlockModifiable(player, pos) && player.canPlayerEdit(pos
								.offset(res.sideHit), res.sideHit, item)) {
							IBlockState state = world.getBlockState(pos);
							if (state.getBlock() == Blocks.WATER || state.getBlock() == Blocks.FLOWING_WATER) {
								player.playSound(SoundEvents.ITEM_BUCKET_FILL, 0.75F, 1.25F);
								if (!world.isRemote) {
									EntityItem drop = new EntityItem(world, player.posX, player.posY + 0.25D,
											player.posZ, new ItemStack(this, 1, 1));
									world.spawnEntity(drop);
								}
								if (!player.capabilities.isCreativeMode) {
									DCUtil.redAndDel(item, 1);
								}
								return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
							}
						}
					}
				} else {
					ActionResult<ItemStack> ret = super.onItemRightClick2(world, player, hand);
					if (ret.getType() == EnumActionResult.PASS) {
						player.setActiveHand(hand);
						return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
					}
				}
			}
		}
		return super.onItemRightClick2(world, player, hand);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase living) {
		int meta = stack.getMetadata();
		if (living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand
					.nextFloat() * 0.1F + 0.9F);
			this.addEffects(stack, worldIn, living);
			if (!player.capabilities.isCreativeMode) {
				this.dropContainerItem(worldIn, stack, living);
				DCUtil.reduceStackSize(stack, 1);
			}
		}

		return stack;
	}

	public boolean addEffects(ItemStack stack, World world, EntityLivingBase living) {
		if (!world.isRemote && !DCUtil.isEmpty(stack) && living != null) {
			int meta = stack.getMetadata();
			if (meta == 0)
				return false;
			Fluid fluid = getFluid(meta);
			List<PotionEffect> effects = ItemSilverCup.getPotionEffect(fluid, 1F, 1);
			IDrinkCustomize drink = stack.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
			float durF = 1.0F;
			int ampF = 0;
			if (drink != null) {
				durF *= drink.getMilk().effect;
				ampF += drink.getSugar().effect;
				int l = drink.getAgingLevel();
				if (l > 0) {
					ampF += l;
					durF *= l;
				}
			}

			if (living instanceof EntityPlayer && DCIntegrationCore.loadedTaN) {
				DCThirstHelper.onDrink((EntityPlayer) living, fluid);
			}

			if (living instanceof EntityPlayer && DCIntegrationCore.loadedSD) {
				DCThirstHelperSD.drink((EntityPlayer) living, fluid);
			}

			if (meta == 2 || meta == 13 || fluid == MainInit.tomatoJuice) {
				living.clearActivePotions();
				return false;
			} else if (fluid == MainInit.mazai) {
				living.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 2, 0));
				living.heal(30.0F);
				if (world.rand.nextInt(100) == 0) {
					living.attackEntityFrom(DamageSource.GENERIC, 20.0F);
				}
				return false;
			} else if (!effects.isEmpty()) {
				for (PotionEffect get : effects) {
					if (get != null && get.getPotion() != null) {
						Potion por = get.getPotion();
						if (por == null)
							continue;
						int amp = get.getAmplifier() * ampF;
						int dur = MathHelper.ceil(get.getDuration() * durF);
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
		return false;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	public void dropContainerItem(World world, ItemStack food, EntityLivingBase living) {
		if (!world.isRemote && living != null) {
			ItemStack stack = this.getContainerItem(food);
			if (!DCUtil.isEmpty(stack)) {
				EntityItem drop = new EntityItem(world, living.posX, living.posY + 0.25D, living.posZ, stack);
				world.spawnEntity(drop);
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
				return (T) new FluidPaperContDC(cont);
			else if (capability == DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY)
				return (T) new DrinkItemCustomizer(cont);
			else
				return null;
		}
	}

}
