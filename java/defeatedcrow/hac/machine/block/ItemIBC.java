package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCItemBlock;
import defeatedcrow.hac.main.block.fluid.FluidItemBlockWrapper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIBC extends DCItemBlock {

	public ItemIBC(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(TextFormatting.BOLD.toString() + "Tier 2");
		IFluidHandler cont = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		if (cont != null && cont.getTankProperties() != null && cont.getTankProperties().length > 0) {
			FluidStack f = cont.getTankProperties()[0].getContents();
			if (f != null && f.getFluid() != null) {
				tooltip.add(TextFormatting.BOLD.toString() + "CONTAINED FLUID");
				tooltip.add("Fluid: " + f.getLocalizedName());
				tooltip.add("Amount: " + f.amount + " mB");
			} else {
				tooltip.add(TextFormatting.BOLD.toString() + "CONTAINED FLUID");
				tooltip.add("Fluid: Empty");
				tooltip.add("Amount: 0 mB");
			}
		} else {
			tooltip.add(TextFormatting.BOLD.toString() + "CONTAINED FLUID");
			tooltip.add("Fluid: Empty");
			tooltip.add("Amount: 0 mB");
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
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
				return true;
			else
				return false;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
				return (T) new FluidItemBlockWrapper(cont, 128000, "Tank");
			else
				return null;
		}
	}

}
