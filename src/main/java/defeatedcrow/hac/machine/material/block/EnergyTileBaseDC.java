package defeatedcrow.hac.machine.material.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.machine.IFacingTile;
import defeatedcrow.hac.api.machine.ISidedMachine;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.block.OwnableBaseTileDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.energy.SidedEnergyTankDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;

public abstract class EnergyTileBaseDC extends OwnableBaseTileDC implements IFacingTile {

	public EnergyTileBaseDC(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	// tick
	public static void serverTick(Level level, BlockPos pos, BlockState state, EnergyTileBaseDC tile) {
		tile.onTickProcess(level, pos, state);
	}

	public boolean isActive(Level level, BlockPos pos, BlockState state) {
		return !DCState.getBool(state, DCState.POWERED);
	}

	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {

		if (this.isActive(level, pos, state)) {
			for (Direction dir : DCUtil.PipeScanList) {
				Direction opposite = dir.getOpposite();
				BlockPos p2 = this.getBlockPos().relative(dir);
				if (getEnergyHandler().getFace(dir) == FaceIO.OUTPUT) {
					BlockEntity targetEntity = this.getLevel().getBlockEntity(p2);
					if (targetEntity != null) {
						targetEntity.getCapability(ForgeCapabilities.ENERGY, opposite).ifPresent(handler -> {
							if (handler instanceof ISidedMachine sided) {
								if (!sided.getFace(opposite).canReceive())
									return;
							}
							if (handler.canReceive()) {
								int amo = Math.min(getEnergyHandler().getEnergyStored(), getEnergyHandler().getFlowRate());
								int drain = getEnergyHandler().extractEnergy(amo, true);
								int i = handler.receiveEnergy(drain, true);
								if (i > 0) {
									handler.receiveEnergy(drain, false);
									getEnergyHandler().extractEnergy(i, false);
								}
							}
						});
					}
				} else if (getEnergyHandler().getFace(dir) == FaceIO.PIPE) {
					BlockEntity targetEntity = this.getLevel().getBlockEntity(p2);
					if (targetEntity != null) {
						targetEntity.getCapability(ForgeCapabilities.ENERGY, opposite).ifPresent(handler -> {
							if (handler instanceof ISidedMachine sided) {
								if (sided.canReceive() && sided.getFace(opposite) == FaceIO.INPUT) {
									// 空きがあれば流れる
									int amo = Math.min(getEnergyHandler().getEnergyStored(), getEnergyHandler().getFlowRate());
									int drain = getEnergyHandler().extractEnergy(amo, true);
									int i = sided.receiveEnergy(drain, true);
									if (i > 0) {
										sided.receiveEnergy(drain, false);
										getEnergyHandler().extractEnergy(i, false);
									}
								} else if (sided.canReceive() && sided.getFace(opposite) == FaceIO.PIPE) {
									// 空きが多い方から少ない方へ
									int cap1 = getEnergyHandler().getMaxEnergyStored() - getEnergyHandler().getEnergyStored();
									int cap2 = sided.getMaxEnergyStored() - sided.getEnergyStored();
									if (cap2 > cap1) {
										int amo = Math.min((cap2 - cap1) / 2, getEnergyHandler().getFlowRate());
										amo = Math.max(amo, 1);
										int ret = getEnergyHandler().extractEnergy(amo, true);
										int i = sided.receiveEnergy(ret, true);
										if (i > 0) {
											sided.receiveEnergy(i, false);
											getEnergyHandler().extractEnergy(i, false);
										}
									}
								}
							} else if (handler.canReceive()) {
								int amo = Math.min(getEnergyHandler().getEnergyStored(), getEnergyHandler().getFlowRate());
								int drain = getEnergyHandler().extractEnergy(amo, true);
								int i = handler.receiveEnergy(drain, true);
								if (i > 0) {
									handler.receiveEnergy(drain, false);
									getEnergyHandler().extractEnergy(i, false);
								}
							}
						});
					}
				}
			}
		}
		return false;
	}

	public int getStoredEnergyLevel() {
		float f = getEnergyHandler().getEnergyStored() * 8F / getEnergyHandler().getMaxEnergyStored();
		return Mth.ceil(f);
	}

	@Override
	public FaceIO getFace(Direction dir) {
		return getEnergyHandler().getFace(dir);
	}

	@Override
	public FaceIO switchFace(Direction dir) {
		return getEnergyHandler().switchFace(dir);
	}

	@Override
	public FaceIO setFace(Direction dir, FaceIO face) {
		return getEnergyHandler().setFace(dir, face);
	}

	@Override
	public NonNullList<FaceIO> getFaces() {
		return getEnergyHandler().getFaces();
	}

	// nbt

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);

		getEnergyHandler().readFromNBT(tag);

	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);

		getEnergyHandler().writeToNBT(tag);
	}

	// caps

	private LazyOptional<? extends ISidedMachine> energyHandler = LazyOptional.of(() -> getEnergyHandler());

	public abstract SidedEnergyTankDC getEnergyHandler();

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && capability == ForgeCapabilities.ENERGY) {
			if (facing == null) {
				return energyHandler.cast();
			} else if (getEnergyHandler().getFace(facing) != FaceIO.NONE) {
				return energyHandler.cast();
			} else {
				return LazyOptional.empty();
			}
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();

		energyHandler.invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		energyHandler = LazyOptional.of(() -> getEnergyHandler());
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.battery.with_owner", this.ownerName) : Component.translatable("dcs.container.battery");
	}

}
