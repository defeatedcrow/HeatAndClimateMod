package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.capability.DrinkMilk;
import defeatedcrow.hac.food.capability.DrinkSugar;
import defeatedcrow.hac.food.capability.IDrinkCustomize;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

public class EntityTumbler extends EntityTeaCupSilver {

	public EntityTumbler(World worldIn) {
		super(worldIn);
		this.setSize(0.375F, 0.5F);
	}

	public EntityTumbler(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
		this.setSize(0.375F, 0.5F);
	}

	public EntityTumbler(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
		this.setSize(0.375F, 0.5F);
	}

	@Override
	protected ItemStack drops() {
		ItemStack drop = new ItemStack(FoodInit.cupSilver, 1, 2);
		IFluidHandlerItem cont = drop.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		IDrinkCustomize drink = drop.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
		Fluid f = FluidRegistry.getFluid(getFluidName());
		int amo = getAmount();
		if (cont != null && f != null && amo > 0) {
			cont.fill(new FluidStack(f, amo), true);
		}
		if (drink != null) {
			drink.setMilk(DrinkMilk.getFromId(getMilk()));
			drink.setSugar(DrinkSugar.getFromId(getSugar()));
		}
		return drop;
	}
}
