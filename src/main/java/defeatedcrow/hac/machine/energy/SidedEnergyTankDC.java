package defeatedcrow.hac.machine.energy;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.machine.IFacingTile;
import defeatedcrow.hac.api.machine.ISidedMachine;
import defeatedcrow.hac.api.util.TagKeyDC;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

public class SidedEnergyTankDC implements ISidedMachine, IFacingTile, ICapabilityProvider {
	protected int energy;
	protected int capacity;
	protected int flowRate;
	protected final NonNullList<FaceIO> faces = NonNullList.withSize(6, FaceIO.PIPE);
	final BlockEntity tile;

	public SidedEnergyTankDC(BlockEntity t, int cap) {
		this(t, cap, 32, 0);
	}

	public SidedEnergyTankDC(BlockEntity t, int cap, int rate) {
		this(t, cap, rate, 0);
	}

	public SidedEnergyTankDC(BlockEntity t, int cap, int rate, int energy) {
		this.capacity = cap;
		this.flowRate = rate;
		this.energy = Math.max(0, Math.min(capacity, energy));
		tile = t;
	}

	public SidedEnergyTankDC setAllFases(FaceIO f) {
		for (int i = 0; i < faces.size(); i++) {
			faces.set(i, f);
		}
		return this;
	}

	@Override
	public int getFlowRate() {
		return flowRate;
	}

	public int generateEnergy(int receive) {
		int energyReceived = Math.min(capacity - energy, receive);
		energy += energyReceived;
		return energyReceived;
	}

	public int consumeEnergy(int consume) {
		int energyExtracted = Math.min(energy, consume);
		energy -= energyExtracted;
		return energyExtracted;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		if (!canReceive())
			return 0;

		int energyReceived = Math.min(capacity - energy, Math.min(flowRate, maxReceive));
		if (!simulate)
			energy += energyReceived;
		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		if (!canExtract())
			return 0;

		int energyExtracted = Math.min(energy, Math.min(flowRate, maxExtract));
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
		return this.flowRate > 0 && energy > 0;
	}

	@Override
	public boolean canReceive() {
		return this.flowRate > 0 && energy < capacity;
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
			if (faces.size() <= i) {
				faces.add(i, FaceIO.NONE);
			}
			int id = faces.get(i).getID();
			nbt.putInt(TagKeyDC.FACE_IO + i, id);
		}
		return nbt;
	}

	@Override
	public FaceIO getFace(Direction dir) {
		int i = dir.get3DDataValue();
		if (i >= 0 && i < 6) {
			if (faces.size() <= i) {
				faces.add(i, FaceIO.NONE);
			}
			return faces.get(i);
		}
		return FaceIO.NONE;
	}

	@Override
	public FaceIO switchFace(Direction dir) {
		int i = dir.get3DDataValue();
		FaceIO f = FaceIO.NONE;
		if (i >= 0 && i < 6 && i < faces.size()) {
			f = faces.get(i);
		}
		FaceIO next = FaceIO.getIO(f.getID() + 1);
		faces.set(i, next);
		return next;
	}

	@Override
	public FaceIO setFace(Direction dir, FaceIO face) {
		int i = dir.get3DDataValue();
		if (i >= 0 && i < 6) {
			faces.set(i, face);
		}
		return face;
	}

	@Override
	public NonNullList<FaceIO> getFaces() {
		return faces;
	}

	private final LazyOptional<IEnergyStorage> holder = LazyOptional.of(() -> this);

	@Override
	@NotNull
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing) {
		return ForgeCapabilities.ENERGY.orEmpty(capability, holder);
	}

}
