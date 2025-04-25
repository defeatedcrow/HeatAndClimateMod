package defeatedcrow.hac.machine.material.block.machine;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.block.OwnableBaseTileDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.item.SapType;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class SpileCupTile extends OwnableBaseTileDC implements WorldlyContainer {

	public SpileCupTile(BlockPos pos, BlockState state) {
		super(MachineInit.SPILE_TILE.get(), pos, state);
	}

	public SpileCupInventory inventory = new SpileCupInventory();

	@Override
	public boolean isEmpty() {
		return inventory.isEmpty();
	}

	@Override
	public ItemStack getItem(int s) {
		return inventory.getItem(s);
	}

	@Override
	public ItemStack removeItem(int s, int count) {
		return inventory.removeItem(s, count);
	}

	@Override
	public ItemStack removeItemNoUpdate(int s) {
		return inventory.removeItemNoUpdate(s);
	}

	@Override
	public void setItem(int s, ItemStack item) {
		inventory.setItem(s, item);
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
		inventory.clearContent();
	}

	@Override
	public boolean canPlaceItemThroughFace(int s, ItemStack stack, @Nullable Direction dir) {
		return !this.isLocked() && this.canPlaceItem(s, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int s, ItemStack stack, Direction dir) {
		return !this.isLocked();
	}

	@Override
	public int getContainerSize() {
		return 1;
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.simple.with_owner", this.ownerName) : Component.translatable("dcs.container.simple");
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return null;
	}

	@Override
	public boolean hasMenu() {
		return false;
	}

	protected final int[] slots = { 0 };

	@Override
	public int[] getSlotsForFace(Direction dir) {
		return slots;
	}

	LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER) {
			return handlers[1].cast();
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

	private class SpileCupInventory implements Container {

		public SpileCupInventory() {}

		public void updateSapData(SapType type, int stage) {
			BlockState next = getBlockState().setValue(SpileCupBlock.TYPE, type).setValue(DCState.STAGE5, stage);
			getLevel().setBlock(getBlockPos(), next, 3);
		}

		@Override
		public void clearContent() {
			updateSapData(SapType.SWEET, 0);
		}

		@Override
		public int getContainerSize() {
			return 1;
		}

		@Override
		public boolean isEmpty() {
			return DCState.getInt(getBlockState(), DCState.STAGE5) <= 0;
		}

		@Override
		public ItemStack getItem(int s) {
			SapType type = getBlockState().getValue(SpileCupBlock.TYPE);
			int count = DCState.getInt(getBlockState(), DCState.STAGE5);
			if (type != null) {
				ItemStack stack = new ItemStack(type.getDrop(), count);
				return stack;
			}
			return ItemStack.EMPTY;
		}

		@Override
		public ItemStack removeItem(int s, int count) {
			ItemStack sap = getItem(s);
			if (DCUtil.isEmpty(sap))
				return ItemStack.EMPTY;
			if (count > 0 && sap.getCount() >= count) {
				int num = sap.getCount() - count;
				BlockState next = getBlockState().setValue(DCState.STAGE5, num);
				getLevel().setBlock(getBlockPos(), next, 3);
				return sap.split(count);
			} else {
				clearContent();
				return ItemStack.EMPTY;
			}
		}

		@Override
		public ItemStack removeItemNoUpdate(int s) {
			return ItemStack.EMPTY;
		}

		@Override
		public void setItem(int s, ItemStack item) {
			if (s != 0) {
				return;
			} else {
				if (DCUtil.isEmpty(item)) {
					clearContent();
				} else {
					SapType type = SapType.getFromItem(item.getItem());
					if (type != null) {
						int num = item.getCount();
						if (num > 4)
							num = 4;
						updateSapData(type, num);
					}
				}
			}
		}

		@Override
		public void setChanged() {
			getLevel().getBlockEntity(getBlockPos()).setChanged();
		}

		@Override
		public boolean stillValid(Player player) {
			return true;
		}

		@Override
		public boolean canPlaceItem(int s, ItemStack item) {
			if (DCUtil.isEmpty(item)) {
				return false;
			} else {
				return SapType.getFromItem(item.getItem()) != null;
			}
		}

	}

}
