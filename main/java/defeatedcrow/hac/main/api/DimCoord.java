package defeatedcrow.hac.main.api;

import com.google.common.base.MoreObjects;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class DimCoord {

	public final int dimID;
	public final int x;
	public final int y;
	public final int z;

	public DimCoord(int dim, int xIn, int yIn, int zIn) {
		dimID = dim;
		x = xIn;
		y = yIn;
		z = zIn;
	}

	public DimCoord(int dim, BlockPos pos) {
		dimID = dim;
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
	}

	public DimCoord(int xIn, int yIn, int zIn) {
		dimID = 0;
		x = xIn;
		y = yIn;
		z = zIn;
	}

	public BlockPos getPos() {
		return new BlockPos(x, y, z);
	}

	public int getDim() {
		return dimID;
	}

	public NBTTagCompound setNBT(NBTTagCompound tag) {
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		tag.setInteger("coordx", x);
		tag.setInteger("coordy", y);
		tag.setInteger("coordz", z);
		tag.setInteger("coorddim", dimID);
		return tag;
	}

	public static DimCoord getCoordFromNBT(NBTTagCompound tag) {
		if (tag == null) {
			return null;
		}
		int x = 0;
		int y = 0;
		int z = 0;
		int d = 0;
		if (tag.hasKey("dcs.charm.dim")) {
			d = tag.getInteger("dcs.charm.dim");
			x = tag.getInteger("dcs.charm.x");
			y = tag.getInteger("dcs.charm.y");
			z = tag.getInteger("dcs.charm.z");
		} else if (tag.hasKey("dcs.portal.dim")) {
			d = tag.getInteger("dcs.portal.dim");
			x = tag.getInteger("dcs.portal.x");
			y = tag.getInteger("dcs.portal.y");
			z = tag.getInteger("dcs.portal.z");
		} else {
			x = tag.getInteger("coordx");
			y = tag.getInteger("coordy");
			z = tag.getInteger("coordz");
			d = tag.getInteger("coorddim");
		}
		if (x == 0 && y == 0 && z == 0) {
			return null;
		}
		return new DimCoord(d, x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof DimCoord) {
			DimCoord p = (DimCoord) obj;
			return p.x == x && p.y == y && p.z == z && p.dimID == dimID;
		}
		if (obj != null && obj instanceof Vec3i) {
			Vec3i p = (Vec3i) obj;
			return p.getX() == x && p.getY() == y && p.getZ() == z && dimID == 0;
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return x * 7 + y * 11 + z * 13 + dimID * 17;
	}

	public String toString() {
		return MoreObjects.toStringHelper("DimID and BlockPos").add("dim", dimID).add("x", x).add("y", y).add("z", z)
				.toString();
	}

	public String toString(String name) {
		return MoreObjects.toStringHelper(name).add("dim", dimID).add("x", x).add("y", y).add("z", z)
				.toString();
	}

}
