package defeatedcrow.hac.api.machine;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public interface IFluidPipe extends IFluidHandler {

	int getFlowRate();

	int getFluidAmount();

	FaceIO getFace(Direction dir);

	FaceIO switchFace(Direction dir);

	FaceIO setFace(Direction dir, FaceIO face);

	NonNullList<FaceIO> getFaces();

	int fill(FluidStack resource, FluidAction action, Direction dir);

	@NotNull
	FluidStack drain(FluidStack resource, FluidAction action, Direction dir);

	@NotNull
	FluidStack drain(int maxDrain, FluidAction action, Direction dir);

}
