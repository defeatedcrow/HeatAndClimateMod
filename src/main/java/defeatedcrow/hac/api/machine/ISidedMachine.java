package defeatedcrow.hac.api.machine;

import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraftforge.energy.IEnergyStorage;

public interface ISidedMachine extends IEnergyStorage {

	int getFlowRate();

	FaceIO getFace(Direction dir);

	FaceIO switchFace(Direction dir);

	FaceIO setFace(Direction dir, FaceIO face);

	NonNullList<FaceIO> getFaces();

}
