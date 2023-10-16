package defeatedcrow.hac.machine.energy;

import defeatedcrow.hac.api.util.TagKeyDC;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.energy.IEnergyStorage;

public class SidedEnergyTankDC implements ISidedMachine, IEnergyStorage {
	protected int energy;
	protected int capacity;
	protected int flowRate;
	protected NonNullList<FaceIO> faces = NonNullList.of(FaceIO.OUTPUT, FaceIO.INPUT, FaceIO.PIPE, FaceIO.PIPE, FaceIO.PIPE, FaceIO.PIPE);

	public SidedEnergyTankDC(int cap) {
		this(cap, 32, 0);
	}

	public SidedEnergyTankDC(int cap, int rate) {
		this(cap, rate, 0);
	}

	public SidedEnergyTankDC(int cap, int rate, int energy) {
		this.capacity = cap;
		this.flowRate = rate;
		this.energy = Math.max(0, Math.min(capacity, energy));
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		if (!canReceive())
			return 0;

		int energyReceived = Math.min(capacity - energy, Math.min(this.flowRate, maxReceive));
		if (!simulate)
			energy += energyReceived;
		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		if (!canExtract())
			return 0;

		int energyExtracted = Math.min(energy, Math.min(this.flowRate, maxExtract));
		if (!simulate)
			energy -= energyExtracted;
		return energyExtracted;
	}

	@Override
	public int getEnergyStored() {
		return energy;
	}

	@Override
	public int getMaxEnergyStored() {
		return capacity;
	}

	@Override
	public boolean canExtract() {
		return this.flowRate > 0;
	}

	@Override
	public boolean canReceive() {
		return this.flowRate > 0;
	}

	public void readFromNBT(CompoundTag nbt) {
		this.energy = nbt.getInt(TagKeyDC.ENERGY);
		for (int i = 0; i < 6; i++) {
			if (nbt.contains(TagKeyDC.FACE_IO + i)) {
				int id = nbt.getInt(TagKeyDC.FACE_IO + i);
				faces.set(i, FaceIO.getIO(id));
			}
		}
	}

	public CompoundTag writeToNBT(CompoundTag nbt) {

		nbt.putInt(TagKeyDC.ENERGY, energy);
		for (int i = 0; i < 6; i++) {
			int id = faces.get(i).getID();
			nbt.putInt(TagKeyDC.FACE_IO + i, energy);
		}
		return nbt;
	}

	@Override
	public FaceIO getface(Direction dir) {
		int i = dir.get3DDataValue();
		if (i >= 0 && i < 6) {
			return faces.get(i);
		}
		return FaceIO.INPUT;
	}

	@Override
	public void setface(Direction dir, FaceIO face) {
		int i = dir.get3DDataValue();
		if (i >= 0 && i < 6) {
			faces.set(i, face);
		}
	}

	@Override
	public NonNullList<FaceIO> getfaces() {
		return faces;
	}

}
