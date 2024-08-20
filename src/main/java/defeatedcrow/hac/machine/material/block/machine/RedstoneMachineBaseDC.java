package defeatedcrow.hac.machine.material.block.machine;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.machine.IFacingTile;
import defeatedcrow.hac.api.machine.ISidedMachine;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.block.OwnableBaseTileDC;
import defeatedcrow.hac.machine.energy.SidedEnergyReceiver;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;

/**
 * 汎用マシンベースクラス<br>
 * - Owner登録あり、Facingあり<br>
 * - 内容のTick同期なし<br>
 */
public abstract class RedstoneMachineBaseDC extends OwnableBaseTileDC implements IFacingTile {

	public RedstoneMachineBaseDC(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	public int getStoredEnergyLevel() {
		float f = getEnergyHandler().getEnergyStored() * 8F / getEnergyHandler().getMaxEnergyStored();
		return Mth.ceil(f);
	}

	@Override
	public FaceIO getFace(Direction dir) {
		return FaceIO.INPUT;
	}

	@Override
	public FaceIO switchFace(Direction dir) {
		return FaceIO.INPUT;
	}

	@Override
	public FaceIO setFace(Direction dir, FaceIO face) {
		return FaceIO.INPUT;
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

	public abstract SidedEnergyReceiver getEnergyHandler();

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
		return this.hasOwner() ? Component.translatable("dcs.container.machine.with_owner", this.ownerName) : Component.translatable("dcs.container.machine");
	}

	public static int getEnergyOutputSignal(BlockEntity tile) {
		if (tile instanceof RedstoneMachineBaseDC machine) {
			float f = machine.getEnergyHandler().getEnergyStored() / machine.getEnergyHandler().getMaxEnergyStored();
			return Mth.floor(f * 14.0F) + (machine.getEnergyHandler().getEnergyStored() > 0 ? 1 : 0);
		} else {
			return 0;
		}
	}

	public Direction getFront() {
		Direction front = DCState.getFace(getBlockState(), DCState.DIRECTION);
		return front == null ? Direction.UP : front;
	}

	public Direction getBack() {
		return Rotation.CLOCKWISE_180.rotate(getFront());
	}

	public Direction getRight() {
		return Rotation.CLOCKWISE_90.rotate(getFront());
	}

	public Direction getLeft() {
		return Rotation.COUNTERCLOCKWISE_90.rotate(getFront());
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return null;
	}

	@Override
	public boolean hasMenu() {
		return false;
	}

}
