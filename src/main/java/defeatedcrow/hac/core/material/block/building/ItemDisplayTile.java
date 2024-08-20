package defeatedcrow.hac.core.material.block.building;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.material.IDisplayTile;
import defeatedcrow.hac.core.material.block.InventoryDC;
import defeatedcrow.hac.core.material.block.OwnableBaseTileDC;
import defeatedcrow.hac.core.network.packet.message.MsgTileDisplayItemToC;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public abstract class ItemDisplayTile extends OwnableBaseTileDC implements WorldlyContainer, IDisplayTile {

	public ItemDisplayTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	int count = 9;
	private NonNullList<ItemStack> display = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);

	@Override
	public ItemStack getDisplay(int s) {
		return display.get(s);
	}

	@Override
	public void setDisplay(int s, ItemStack item) {
		display.set(s, item);
	}

	// tick
	public static void serverTick(Level level, BlockPos pos, BlockState state, ItemDisplayTile tile) {
		tile.onTickProcess(level, pos, state);
	}

	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
			return false;
		} else {
			count = 9;
			boolean lit = false;
			for (int i = 0; i < getContainerSize(); i++) {
				if (!DCUtil.isEmpty(getDisplay(i)) && getDisplay(i).getItem() instanceof BlockItem blockitem) {
					Block block = blockitem.getBlock();
					if (block.defaultBlockState().getLightEmission(level, pos) > 0) {
						lit = true;
					}
				}

				if (!DCItemUtil.isSameItem(getDisplay(i), inventory.getItem(i), false, true)) {
					setDisplay(i, inventory.getItem(i).copy());
					MsgTileDisplayItemToC.sendToClient((ServerLevel) level, pos, getDisplay(i), i);
				}
			}
			changeLitState(level, pos, lit);
		}
		return false;
	}

	protected void changeLitState(Level level, BlockPos pos, boolean lit) {}

	public InventoryDC inventory = new InventoryDC(getContainerSize(), this);

	@Override
	public int getContainerSize() {
		return 5;
	}

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
	public int[] getSlotsForFace(Direction dir) {
		return new int[] { 0, 1, 2, 3, 4 };
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
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithoutMetadata();
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		inventory.readFromNBT(tag);
		if (getContainerSize() == 1) {
			if (tag.contains("Display")) {
				CompoundTag dispTag = tag.getCompound("Display");
				display.set(0, ItemStack.of(dispTag));
			}
		} else {
			for (int i = 0; i < getContainerSize(); i++) {
				if (tag.contains("Display_" + i)) {
					CompoundTag dispTag = tag.getCompound("Display_" + i);
					display.set(i, ItemStack.of(dispTag));
				}
			}
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		inventory.writeToNBT(tag);

		if (getContainerSize() == 1) {
			CompoundTag dispTag = new CompoundTag();
			getDisplay(0).save(dispTag);
			tag.put("Display", dispTag);
		} else {
			for (int i = 0; i < getContainerSize(); i++) {
				CompoundTag dispTag = new CompoundTag();
				getDisplay(i).save(dispTag);
				tag.put("Display_" + i, dispTag);
			}
		}
	}

	private LazyOptional<?> itemHandler = LazyOptional.of(() -> createUnSidedHandler());

	protected IItemHandler createUnSidedHandler() {
		return new InvWrapper(this);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
		if (!this.isLocked() && !this.remove && cap == ForgeCapabilities.ITEM_HANDLER)
			return itemHandler.cast();
		return super.getCapability(cap, side);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		itemHandler.invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		itemHandler = LazyOptional.of(() -> createUnSidedHandler());
	}

}
