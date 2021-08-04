package defeatedcrow.hac.food.item.brewing;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.capability.DrinkMilk;
import defeatedcrow.hac.food.capability.DrinkSugar;
import defeatedcrow.hac.food.capability.IDrinkCustomize;
import defeatedcrow.hac.food.entity.LiquorBottleEntity;
import defeatedcrow.hac.food.entity.WaterBottleEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class ItemRoseWaterBottle extends ItemLiquorBottle {

	public static String[] names2 = {
			"rose_water",
			"tonic_water",
			"lemon_squash",
			"cola"
	};

	public static final String[] FLUIDS2 = {
			"dcs.rose_water",
			"dcs.tonic_water",
			"dcs.lemon_squash",
			"dcs.cola"
	};

	public ItemRoseWaterBottle() {
		super();
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(FoodInit.liquorBottle);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public int getMaxMeta() {
		return 3;
	}

	@Override
	public String[] getNameSuffix() {
		return names2;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		DrinkMilk milk = DrinkMilk.NONE;
		DrinkSugar sugar = DrinkSugar.NONE;
		int aging = 0;
		IDrinkCustomize drink = item.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
		if (drink != null) {
			milk = drink.getMilk();
			sugar = drink.getSugar();
			aging = drink.getAgingLevel();
		}
		LiquorBottleEntity ret = new WaterBottleEntity(world, x, y, z, player).setCustom(milk, sugar, aging)
				.setMetadata(i);
		return ret;
	}

	/* 飲用効果 */

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 0;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target,
			EnumHand hand) {
		if (!DCUtil.isEmpty(stack)) {
			if (this.addEffects(stack, player.world, target)) {
				this.dropContainerItem(player.world, stack, player);
				DCUtil.reduceStackSize(stack, 1);
				return true;
			}
		}
		return super.itemInteractionForEntity(stack, player, target, hand);
	}

	// カラなら飲食できない
	@Override
	public ActionResult<ItemStack> onItemRightClick2(World world, EntityPlayer player, EnumHand hand) {
		if (player != null) {
			ItemStack item = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(item)) {
				player.setActiveHand(hand);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
			}
		}
		return super.onItemRightClick2(world, player, hand);
	}

	/* fluid */
	@Override
	protected Fluid getFluidLocal(int meta) {
		String name = getFluidName(meta);
		return FluidRegistry.getFluid(name);
	}

	public static String getFluidName(int meta) {
		if (meta > 3) {
			meta = 3;
		}
		return FLUIDS2[meta];
	}

	public static String getTypeName(int meta) {
		if (meta > 3) {
			meta = 3;
		}
		return names2[meta];
	}

	public static Fluid getFluid(int meta) {
		String name = getFluidName(meta);
		return FluidRegistry.getFluid(name);
	}

	public static int getMetaFromFluid(Fluid fluid) {
		int meta = 0;
		if (fluid == FoodInit.roseWater) {
			meta = 0;
		} else if (fluid == FoodInit.tonic) {
			meta = 1;
		} else if (fluid == FoodInit.lemon_squash) {
			meta = 2;
		} else if (fluid == FoodInit.cola) {
			meta = 3;
		}
		return meta;
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
