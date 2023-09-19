package defeatedcrow.hac.machine.material.fluid;

import net.minecraft.core.Direction;

public interface IFluidTankTileDC {

	int getTanks();

	DCTank getTank(int id);

	DCTank getTank(Direction dir);

}
