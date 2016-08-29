package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import defeatedcrow.hac.food.FoodInit;

public class EntityTeaCupWhite extends EntityTeaCupSilver {

	public EntityTeaCupWhite(World worldIn) {
		super(worldIn);
	}

	public EntityTeaCupWhite(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public EntityTeaCupWhite(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	protected ItemStack drops() {
		ItemStack drop = new ItemStack(FoodInit.cupSilver, 1, 1);
		IFluidHandler cont = drop.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		Fluid f = FluidRegistry.getFluid(getFluidName());
		int amo = getAmount();
		if (cont != null && f != null && amo > 0) {
			cont.fill(new FluidStack(f, amo), true);
		}
		return drop;
	}
}
