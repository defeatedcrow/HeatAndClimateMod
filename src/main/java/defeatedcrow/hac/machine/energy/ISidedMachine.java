package defeatedcrow.hac.machine.energy;

import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;

public interface ISidedMachine {

	FaceIO getface(Direction dir);

	void setface(Direction dir, FaceIO face);

	NonNullList<FaceIO> getfaces();

}
