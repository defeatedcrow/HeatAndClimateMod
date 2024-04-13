package defeatedcrow.hac.machine.energy;

import defeatedcrow.hac.api.machine.FaceIO;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;

public class SidedEnergySupplyer extends SidedEnergyTankDC {

	public SidedEnergySupplyer(BlockEntity t, int cap) {
		this(t, cap, 32, 0);
	}

	public SidedEnergySupplyer(BlockEntity t, int cap, int rate) {
		this(t, cap, rate, 0);
	}

	public SidedEnergySupplyer(BlockEntity t, int cap, int rate, int energy) {
		super(t, cap, rate, energy);
		this.setAllFases(FaceIO.OUTPUT);
	}

	@Override
	public int receiveEnergy(int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public boolean canReceive() {
		return false;
	}

	@Override
	public FaceIO switchFace(Direction dir) {
		int i = dir.get3DDataValue();
		FaceIO f = FaceIO.NONE;
		if (i >= 0 && i < 6 && i < faces.size()) {
			f = faces.get(i);
		}
		FaceIO next = f == FaceIO.NONE ? FaceIO.OUTPUT : FaceIO.NONE;
		faces.set(i, next);
		return next;
	}

	@Override
	public FaceIO setFace(Direction dir, FaceIO face) {
		if (face == FaceIO.PIPE || face == FaceIO.INPUT)
			face = FaceIO.OUTPUT;
		return super.setFace(dir, face);
	}

}
