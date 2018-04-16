package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
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
			"ethanol"
	};

	public static final String[] FLUIDS = {
			"empty",
			"water",
			"milk",
			"dcs.milk_cream",
			"dcs.seed_oil",
			"dcs.vegetable_juice",
			"dcs.lemonade",
			"dcs.mazai",
			"dcs.green_tea",
			"dcs.black_tea",
			"dcs.black_coffee",
			"dcs.stock",
			"dcs.ethanol"
	};

	public ItemFluidPack() {
		super();
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		if (stack.getItemDamage() == 0)
			return null;
		return new ItemStack(this);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return stack.getItemDamage() != 0;
	}

	@Override
	public int getMaxMeta() {
		return 12;
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

	public static int getItemBurnTime(ItemStack stack) {
		int i = stack.getMetadata();
		if (i == 4)
			return 1600;
		else if (i == 12)
			return 1600;
		else
			return 0;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new FluidPaperContDC(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		if (stack == null)
			return;

		String s = "";
		int i = stack.getItemDamage();
		if (i > 12) {
			i = 12;
		}

		Fluid f = FluidRegistry.getFluid(FLUIDS[i]);
		if (f != null) {
			tooltip.add(TextFormatting.YELLOW.toString() + "Fluid: " + f.getLocalizedName(new FluidStack(f, 200)));
			tooltip.add(TextFormatting.YELLOW.toString() + "Amount: " + 250);
			Fluid milk = FluidRegistry.getFluid("milk");
			if ((milk != null && f == milk) || f == FoodInit.tomatoJuice) {
				tooltip.add(TextFormatting.AQUA.toString() + "clear all potion effects.");
			} else if (f == FoodInit.mazai) {
				tooltip.add(TextFormatting.RED.toString() + "Powerful but dangerous!");
			} else {
				List<PotionEffect> effects = ItemSilverCup.getPotionEffect(f, 1F, 0);
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
		} else {
			tooltip.add(TextFormatting.YELLOW.toString() + "Fluid is empty: " + FLUIDS[i]);
		}
	}

	public static String getFluidName(int meta) {
		if (meta > 12) {
			meta = 12;
		}
		return FLUIDS[meta];
	}

	public static Fluid getFluid(int meta) {
		String name = getFluidName(meta);
		return FluidRegistry.getFluid(name);
	}

	/* 飲用効果 */

	// カラなら飲食できない
	@Override
	public ActionResult<ItemStack> onItemRightClick2(World world, EntityPlayer player, EnumHand hand) {
		if (player != null) {
			ItemStack item = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(item) && item.getItemDamage() > 0) {
				player.setActiveHand(hand);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
			}
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, item);
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, null);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase living) {
		int meta = stack.getMetadata();
		if (living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ,
					SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F,
					worldIn.rand.nextFloat() * 0.1F + 0.9F);
			this.addEffects(stack, worldIn, living);
			this.dropContainerItem(worldIn, stack, living);
			DCUtil.reduceStackSize(stack, 1);
		}

		return stack;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target,
			EnumHand hand) {
		if (this.addEffects(stack, player.worldObj, target)) {
			this.dropContainerItem(player.worldObj, stack, player);
			DCUtil.reduceStackSize(stack, 1);
			return true;
		}
		return super.itemInteractionForEntity(stack, player, target, hand);
	}

	public boolean addEffects(ItemStack stack, World worldIn, EntityLivingBase living) {
		if (!worldIn.isRemote && stack != null) {
			int meta = stack.getMetadata();
			Fluid fluid = getFluid(meta);
			List<PotionEffect> effects = ItemSilverCup.getPotionEffect(fluid, 1F, 1);
			if (meta == 2 || fluid == FoodInit.tomatoJuice) {
				living.clearActivePotions();
			} else if (effects.isEmpty())
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
				world.spawnEntityInWorld(drop);
			}
		}
	}

}
