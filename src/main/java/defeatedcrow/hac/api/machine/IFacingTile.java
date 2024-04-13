package defeatedcrow.hac.api.machine;

import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;

public interface IFacingTile {

	FaceIO getFace(Direction dir);

	FaceIO switchFace(Direction dir);

	FaceIO setFace(Direction dir, FaceIO face);

	NonNullList<FaceIO> getFaces();

}
