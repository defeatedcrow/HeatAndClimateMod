package defeatedcrow.hac.magic.material.entity;

import javax.annotation.Nonnull;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.util.TagKeyDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class MagicPictureEntity extends OwnableMagicEntity {

	private Direction direction = Direction.NORTH;
	private static final EntityDataAccessor<Integer> DATA_ROTATION = SynchedEntityData.defineId(MagicPictureEntity.class, EntityDataSerializers.INT);

	public MagicPictureEntity(EntityType<? extends OwnableMagicEntity> type, Level level) {
		super(type, level);
	}

	public MagicPictureEntity(EntityType<? extends OwnableMagicEntity> type, MagicColor c, Level level, BlockPos pos, Direction dir) {
		super(type, level);
	}

	public abstract Item getItem();

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(DATA_ROTATION, 0);
	}

	public int getRotation() {
		return this.getEntityData().get(DATA_ROTATION);
	}

	public void setRotation(int dir3D) {
		this.setRotation(dir3D, true);
	}

	private void setRotation(int dir3D, boolean flag) {
		this.getEntityData().set(DATA_ROTATION, dir3D % 8);
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	@Nonnull
	public ItemStack getDropItem() {
		return new ItemStack(getItem());
	}

	@Override
	protected void onRemoved() {
		Vec3 pos = this.getEyePosition();
		if (!level.isClientSide && !getDropItem().isEmpty()) {
			ItemEntity drop = new ItemEntity(level, pos.x, pos.y + 0.1D, pos.z, getDropItem().copy());
			level.addFreshEntity(drop);
		}
	}

	@Override
	public void thunderHit(ServerLevel level, LightningBolt hit) {}

	@Override
	public void refreshDimensions() {}

	// NBT
	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		tag.putByte(TagKeyDC.DIRECTION, (byte) this.direction.get3DDataValue());
		super.addAdditionalSaveData(tag);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		if (tag.contains(TagKeyDC.DIRECTION)) {
			Direction dir = Direction.from3DDataValue(tag.getByte(TagKeyDC.DIRECTION));
		}
		super.readAdditionalSaveData(tag);
	}

}
