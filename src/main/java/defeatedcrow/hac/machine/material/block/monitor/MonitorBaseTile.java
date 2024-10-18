package defeatedcrow.hac.machine.material.block.monitor;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.block.OwnableBaseTileDC;
import defeatedcrow.hac.core.network.packet.message.IIntReceiver;
import defeatedcrow.hac.core.network.packet.message.MsgTileSimpleIntegerToC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class MonitorBaseTile extends OwnableBaseTileDC implements IIntReceiver {

	protected BlockPos pairPos = BlockPos.ZERO;
	public Direction side = Direction.UP;

	public MonitorBaseTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	public int amount = 0;
	public int amountMax = 0;
	public int last = 0;

	public String unit() {
		return "";
	}

	public boolean hasUnit() {
		return false;
	}

	public String amountString(int amo, int order) {
		return amo == 0 ? "---" : String.valueOf(amo);
	}

	public BlockPos getPairPos() {
		return pairPos;
	}

	public void setPairPos(BlockPos pos) {
		pairPos = pos;
	}

	public Direction getSide() {
		return side;
	}

	public void setSide(Direction d) {
		side = d;
	}

	public int getCurrentAmount() {
		return amount;
	}

	public int getMaxAmount() {
		return amountMax;
	}

	public float getGauge() {
		return amountMax < 1F ? 0 : amount / amountMax;
	}

	boolean isActive(Level level, BlockPos pos, BlockState state) {
		return true;
	}

	public BlockEntity targetTile() {
		if (pairPos != null && getLevel().isLoaded(pairPos)) {
			return getLevel().getBlockEntity(pairPos);
		}
		return null;
	}

	@Override
	public void receiveInteger(int i) {
		amount = i;
	}

	// tick
	public static void serverTick(Level level, BlockPos pos, BlockState state, MonitorBaseTile tile) {
		if (tile.isActive(level, pos, state)) {
			tile.onTickProcess(level, pos, state);
		}
	}

	private boolean next = false;

	int count = 4;

	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
		} else {
			count = 4;
			if (!updateAmount()) {
				amount = 0;
				amountMax = 0;
			}
			if (last != amount) {
				last = amount;
				if (level instanceof ServerLevel)
					MsgTileSimpleIntegerToC.sendToClient((ServerLevel) level, pos, amount);

				updateState();
			}
		}
		return false;
	}

	abstract boolean updateAmount();

	abstract boolean updateState();

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		loadCoordTag(tag);
	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);
		tag.putInt(TagKeyDC.POS_X, pairPos.getX());
		tag.putInt(TagKeyDC.POS_Y, pairPos.getY());
		tag.putInt(TagKeyDC.POS_Z, pairPos.getZ());
		tag.putInt(TagKeyDC.DIRECTION, side.get3DDataValue());
	}

	public void loadCoordTag(CompoundTag tag) {
		int x = 0;
		if (tag.contains(TagKeyDC.POS_X))
			x = tag.getInt(TagKeyDC.POS_X);
		int y = 0;
		if (tag.contains(TagKeyDC.POS_Y))
			y = tag.getInt(TagKeyDC.POS_Y);
		int z = 0;
		if (tag.contains(TagKeyDC.POS_Z))
			z = tag.getInt(TagKeyDC.POS_Z);
		pairPos = new BlockPos(x, y, z);
		int dir = -1;
		if (tag.contains(TagKeyDC.DIRECTION))
			dir = tag.getInt(TagKeyDC.DIRECTION);
		if (dir >= 0) {
			side = Direction.from3DDataValue(dir);
		}
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.monitor.with_owner", this.ownerName) : Component.translatable("dcs.container.monitor");
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
