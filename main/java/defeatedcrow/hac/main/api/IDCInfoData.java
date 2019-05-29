package defeatedcrow.hac.main.api;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface IDCInfoData {

	public List<ItemStack> getInputs();

	public List<ItemStack> getOutputs();

	public List<ItemStack> getMachines();

	public List<FluidStack> getInputFluid();

	public String getTitle();

	public String getDisc();

}
