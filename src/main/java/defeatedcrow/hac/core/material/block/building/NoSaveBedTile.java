package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.BuildInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class NoSaveBedTile extends BlockEntity {

	private BlockPos sleepPlayerPos = BlockPos.ZERO;

	public NoSaveBedTile(BlockPos pos, BlockState state) {
		super(BuildInit.NO_SAVE_BED_TILE.get(), pos, state);
	}

	public NoSaveBedTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	public void setSleepPos(BlockPos pos) {
		sleepPlayerPos = pos;
	}

	public BlockPos getSleepPos() {
		return sleepPlayerPos;
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
		loadTag(tag);
	}

	public void loadTag(CompoundTag tag) {
		if (tag.contains(TagKeyDC.POS_X)) {
			int x = tag.getInt(TagKeyDC.POS_X);
			int y = tag.getInt(TagKeyDC.POS_Y);
			int z = tag.getInt(TagKeyDC.POS_Z);
			BlockPos p = new BlockPos(x, y, z);
			setSleepPos(p);
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		writeTag(tag);
	}

	public void writeTag(CompoundTag tag) {
		if (sleepPlayerPos != BlockPos.ZERO) {
			tag.putInt(TagKeyDC.POS_X, sleepPlayerPos.getX());
			tag.putInt(TagKeyDC.POS_Y, sleepPlayerPos.getY());
			tag.putInt(TagKeyDC.POS_Z, sleepPlayerPos.getZ());
		}
	}

}
