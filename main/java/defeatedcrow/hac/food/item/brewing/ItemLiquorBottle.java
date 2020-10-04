package defeatedcrow.hac.food.item.brewing;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.item.ItemSilverCup;
import defeatedcrow.hac.main.util.DCName;
import defeatedcrow.hac.plugin.DCIntegrationCore;
import defeatedcrow.hac.plugin.tan.DCThirstHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
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

public class ItemLiquorBottle extends DCItem {

	private static String[] names = { "empty", "beer", "wine" };

	public static final String[] FLUIDS = { "empty", "dcs.beer", "dcs.wine" };

	public ItemLiquorBottle() {
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
		return 2;
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
		String s = "items/food/brewing/bottle_" + names[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		int i = stack.getMetadata();
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
			float durF = 1.0F;
			int ampF = 0;
			String mes = "";
			mes += DCName.FLUID.getLocalizedName() + ": " + f.getLocalizedName(new FluidStack(f, 500));
			tooltip.add(TextFormatting.YELLOW.toString() + mes);
			tooltip.add(TextFormatting.YELLOW.toString() + DCName.AMOUNT.getLocalizedName() + ": " + 500);
			Fluid milk = FluidRegistry.getFluid("milk");
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
		} else {
			tooltip.add(TextFormatting.YELLOW.toString() + "Fluid is empty: " + FLUIDS[i]);
		}
	}

	public static String getFluidName(int meta) {
		if (meta > 2) {
			meta = 2;
		}
		return FLUIDS[meta];
	}

	public static Fluid getFluid(int meta) {
		String name = getFluidName(meta);
		return FluidRegistry.getFluid(name);
	}

	public static int getMetaFromFluid(Fluid fluid) {
		int meta = 0;
		if (fluid == FoodInit.beer) {
			meta = 1;
		} else if (fluid == FoodInit.wine) {
			meta = 2;
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
				ActionResult<ItemStack> ret = super.onItemRightClick2(world, player, hand);
				if (ret.getType() == EnumActionResult.PASS) {
					player.setActiveHand(hand);
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
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
			float durF = 1.0F;
			int ampF = 0;

			if (living instanceof EntityPlayer && DCIntegrationCore.loadedTaN) {
				DCThirstHelper.onDrink((EntityPlayer) living, fluid);
			}

			if (!effects.isEmpty()) {
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
			else
				return false;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY)
				return (T) new FluidBottleContDC(cont);
			else
				return null;
		}
	}

}
