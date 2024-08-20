package defeatedcrow.hac.api.material;

import defeatedcrow.hac.api.util.TagKeyDC;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;

public interface IPosLinkTile {

	IPosLinkTile.Type getLinkType();

	default boolean canLink(Level level, BlockPos pos, IPosLinkTile.Type type) {
		return type == getLinkType();
	}

	void setLinkPos(BlockPos pos);

	BlockPos getLinkPos();

	default void writePosToTag(CompoundTag tag) {
		if (getLinkPos() != null) {
			tag.putInt(TagKeyDC.POS_X, getLinkPos().getX());
			tag.putInt(TagKeyDC.POS_Y, getLinkPos().getY());
			tag.putInt(TagKeyDC.POS_Z, getLinkPos().getZ());
		}
	}

	default void loadPosFromTag(CompoundTag tag) {
		int x = tag.getInt(TagKeyDC.POS_X);
		int y = tag.getInt(TagKeyDC.POS_Y);
		int z = tag.getInt(TagKeyDC.POS_Z);
		BlockPos p = new BlockPos(x, y, z);
		setLinkPos(p);
	}

	public enum Type {
		HEAT,
		HUM,
		AIR,
		ENERGY,
		ITEM,
		FLUID;
	}

}
