package defeatedcrow.hac.core.material.block.building;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.material.IDisplayTile;
import defeatedcrow.hac.core.material.block.InventoryDC;
import defeatedcrow.hac.core.network.packet.message.MsgTileDisplayItemToC;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public abstract class SingleItemDisplay extends BlockEntity implements WorldlyContainer, IDisplayTile {

	public SingleItemDisplay(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	int count = 9;
	private ItemStack display = ItemStack.EMPTY;

	@Override
	public ItemStack getDisplay() {
		return display;
	}

	@Override
	public void setDisplay(ItemStack item) {
		display = item;
	}

	// tick
	public static void serverTick(Level level, BlockPos pos, BlockState state, SingleItemDisplay tile) {
		tile.onTickProcess(level, pos, state);
	}

	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
			return false;
		} else {
			count = 9;

			if (!DCItemUtil.isSameItem(display, inventory.getItem(0), false, true)) {
				display = inventory.getItem(0).copy();
				MsgTileDisplayItemToC.sendToClient((ServerLevel) level, pos, display);
			}

			boolean lit = false;
			if (!DCUtil.isEmpty(display) && display.getItem() instanceof BlockItem blockitem) {
				Block block = blockitem.getBlock();
				if (block.defaultBlockState().getLightEmission(level, pos) > 0) {
					lit = true;
				}
			}
			changeLitState(level, pos, lit);
		}
		return false;
	}

	protected void changeLitState(Level level, BlockPos pos, boolean lit) {}

	public InventoryDC inventory = new InventoryDC(1, this);

	@Override
	public int getContainerSize() {
		return 1;
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
			return true;
		}
	}

	@Override
	public void clearContent() {
		inventory.clearContent();
	}

	@Override
	public int[] getSlotsForFace(Direction dir) {
		return new int[] { 1 };
	}

	@Override
	public boolean canPlaceItemThroughFace(int s, ItemStack stack, @Nullable Direction dir) {
		return !true;
	}

	@Override
	public boolean canTakeItemThroughFace(int s, ItemStack stack, Direction dir) {
		return true;
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
		if (tag.contains("Display")) {
			CompoundTag dispTag = tag.getCompound("Display");
			display = ItemStack.of(dispTag);
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		inventory.writeToNBT(tag);
		CompoundTag dispTag = new CompoundTag();
		display.save(dispTag);
		tag.put("Display", dispTag);
	}

	private LazyOptional<?> itemHandler = LazyOptional.of(() -> createUnSidedHandler());

	protected IItemHandler createUnSidedHandler() {
		return new InvWrapper(this);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
		if (!this.remove && cap == ForgeCapabilities.ITEM_HANDLER)
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
