package defeatedcrow.hac.machine.material.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.block.InventoryDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public abstract class EnergyProcessTile extends EnergyTileBaseDC implements WorldlyContainer {

	public EnergyProcessTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	// menu

	public int lastRate = 0;
	public int currentRate = 0;
	public int lastProgress = 0;
	public int currentProgress = 0;
	public int totalProgress = 0;

	public IDeviceRecipe recipe = null;

	public final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return EnergyProcessTile.this.currentProgress;
			case 1:
				return EnergyProcessTile.this.currentRate;
			case 2:
				return EnergyProcessTile.this.getEnergyHandler().getEnergyStored();
			default:
				return 0;
			}
		}

		@Override
		public void set(int id, int data) {
			switch (id) {
			case 0:
				EnergyProcessTile.this.currentProgress = data;
				break;
			case 1:
				EnergyProcessTile.this.currentRate = data;
				break;
			case 2:
				EnergyProcessTile.this.clientEnergy = data;
				break;
			}

		}

		@Override
		public int getCount() {
			return 3;
		}
	};

	// client side gui data
	public int clientEnergy = 0;

	@Override
	public boolean isActive(Level level, BlockPos pos, BlockState state) {
		return !DCState.getBool(state, DCState.POWERED);
	}

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (isActive(level, pos, state)) {
			if (isInProcess()) {
				if (continueProcess(level, pos, state)) {
					if (currentProgress >= totalProgress && finishProcess(level, pos, state)) {
						consumeInputs();
						resetProcess();
						setChanged(level, pos, state);
					} else {
						lastProgress = currentProgress;
						currentProgress += consumeEnergy();
					}
				} else {
					resetProcess();
				}
			} else {
				if (startProcess(level, pos, state)) {
					setChanged(level, pos, state);
				}
			}
		}

		return false;
	}

	public abstract boolean isInProcess();

	public abstract boolean continueProcess(Level level, BlockPos pos, BlockState state);

	public abstract boolean finishProcess(Level level, BlockPos pos, BlockState state);

	public abstract boolean consumeInputs();

	public abstract int consumeEnergy();

	public abstract boolean resetProcess();

	public abstract boolean startProcess(Level level, BlockPos pos, BlockState state);

	public Direction getFront() {
		Direction front = DCState.getFace(getBlockState(), DCState.FACING);
		return front == null ? Direction.NORTH : front;
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

	/* Inventory */

	public InventoryDC inventory = new InventoryDC(this.getContainerSize(), this);

	public InventoryDC getInventory() {
		return inventory;
	}

	@Override
	public abstract int getContainerSize();

	protected abstract int[] getTopSlots();

	protected abstract int[] getBottomSlots();

	protected abstract int[] getSideSlots();

	protected boolean include(int[] ii, int s) {
		if (ii != null && ii.length > 0) {
			for (int i : ii) {
				if (i == s)
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean canPlaceItem(int s, ItemStack stack) {
		return !include(getBottomSlots(), s);
	}

	@Override
	public int[] getSlotsForFace(Direction dir) {
		if (dir == Direction.DOWN) {
			return getBottomSlots();
		} else {
			return dir == Direction.UP ? getTopSlots() : getSideSlots();
		}
	}

	@Override
	public boolean canPlaceItemThroughFace(int s, ItemStack stack, @Nullable Direction dir) {
		return this.canPlaceItem(s, stack) && !this.isLocked();
	}

	@Override
	public boolean canTakeItemThroughFace(int s, ItemStack stack, Direction dir) {
		return dir != Direction.UP && !include(getTopSlots(), s) && !this.isLocked();
	}

	@Override
	public boolean isEmpty() {
		return getInventory().isEmpty();
	}

	@Override
	public ItemStack getItem(int s) {
		return getInventory().getItem(s);
	}

	@Override
	public ItemStack removeItem(int s, int count) {
		return getInventory().removeItem(s, count);
	}

	@Override
	public ItemStack removeItemNoUpdate(int s) {
		return getInventory().removeItemNoUpdate(s);
	}

	@Override
	public void setItem(int s, ItemStack item) {
		getInventory().setItem(s, item);
	}

	@Override
	public boolean stillValid(Player player) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return canOpen(player);
		}
	}

	@Override
	public void clearContent() {
		getInventory().clearContent();
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithoutMetadata();
	}

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		getInventory().readFromNBT(tag);

		lastProgress = tag.getInt(TagKeyDC.LAST_PROGRESS);
		currentProgress = tag.getInt(TagKeyDC.CURRENT_PROGRESS);
		totalProgress = tag.getInt(TagKeyDC.MAX_PROGRESS);
	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);
		getInventory().writeToNBT(tag);

		tag.putInt(TagKeyDC.LAST_PROGRESS, lastProgress);
		tag.putInt(TagKeyDC.CURRENT_PROGRESS, currentProgress);
		tag.putInt(TagKeyDC.MAX_PROGRESS, totalProgress);
	}

	LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER) {
			if (facing == Direction.UP)
				return handlers[0].cast();
			else if (facing == Direction.DOWN)
				return handlers[1].cast();
			else
				return handlers[2].cast();
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		for (int x = 0; x < handlers.length; x++)
			handlers[x].invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.device.with_owner", this.ownerName) : Component.translatable("dcs.container.device");
	}

}
