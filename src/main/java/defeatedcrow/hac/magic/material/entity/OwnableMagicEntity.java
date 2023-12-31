package defeatedcrow.hac.magic.material.entity;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.material.IItemDropEntity;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class OwnableMagicEntity extends Entity implements IItemDropEntity, OwnableEntity {

	public static final UUID EMPTY_OWNER = UUID.fromString("613A8757-2068-4EA5-8EB1-5CB9A41111BF");
	protected String ownerName = "NO OWNER";
	private Component name;

	protected static final EntityDataAccessor<Optional<UUID>> OWNER = SynchedEntityData.defineId(OwnableMagicEntity.class, EntityDataSerializers.OPTIONAL_UUID);
	protected static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(OwnableMagicEntity.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Boolean> LOCK = SynchedEntityData.defineId(OwnableMagicEntity.class, EntityDataSerializers.BOOLEAN);

	public OwnableMagicEntity(EntityType<? extends OwnableMagicEntity> type, Level level) {
		super(type, level);
	}

	@Override
	protected Entity.MovementEmission getMovementEmission() {
		return Entity.MovementEmission.NONE;
	}

	@Override
	protected void defineSynchedData() {
		this.entityData.define(AGE, 0);
		this.entityData.define(LOCK, false);
		this.entityData.define(OWNER, Optional.of(EMPTY_OWNER));
	}

	@Override
	@Nonnull
	public abstract ItemStack getDropItem();

	public abstract MagicColor getColor();

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean isPickable() {
		return !this.isRemoved();
	}

	protected int getMaxAge() {
		return 0;
	}

	@Override
	public void tick() {
		if (this.isRemoved()) {
			this.discard();
		}

		super.tick();

		int age = this.getAge();
		if (getMaxAge() > 0 && getMaxAge() < age) {
			this.onRemoved();
			this.discard();
		}

		int day = DCTimeHelper.getDay(level);
		int hour = DCTimeHelper.currentTime(level);
		if (age < day && hour >= 6) {
			this.setAge(day);
			this.onDayCount(day);
		}

	}

	protected void onRemoved() {}

	protected void onDayCount(int day) {}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (player.isCrouching()) {
			if (this.getLocked() && !this.isOwnerOrOP(player)) {
				return InteractionResult.SUCCESS;
			}
			this.discard();
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public boolean hurt(DamageSource source, float damage) {
		return super.hurt(source, damage);
	}

	@Override
	public boolean fireImmune() {
		return true;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		tag.putInt(TagKeyDC.AGE, this.getAge());
		tag.putBoolean(TagKeyDC.LOCK, this.getLocked());
		if (this.getOwner() != null) {
			tag.putUUID(TagKeyDC.OWNER_UUID, this.getOwnerUUID());
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		if (tag.contains(TagKeyDC.AGE)) {
			this.setAge(tag.getInt(TagKeyDC.AGE));
		}
		if (tag.contains(TagKeyDC.LOCK)) {
			this.setLocked(tag.getBoolean(TagKeyDC.LOCK));
		}
		UUID uuid = EMPTY_OWNER;
		if (tag.hasUUID(TagKeyDC.OWNER_UUID)) {
			uuid = tag.getUUID(TagKeyDC.OWNER_UUID);
		} else {
			String s = tag.getString(TagKeyDC.OWNER_UUID);
			uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
		}
		this.setOwnerUUID(uuid);
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this);
	}

	@Override
	@Nullable
	public UUID getOwnerUUID() {
		return this.getEntityData().get(OWNER).orElse(EMPTY_OWNER);
	}

	public void setOwnerUUID(@Nullable UUID id) {
		if (id == null) {
			id = EMPTY_OWNER;
		}
		this.getEntityData().set(OWNER, Optional.of(id));
	}

	public boolean isOwnerOrOP(Player player) {
		if (player == null || getOwnerUUID().equals(EMPTY_OWNER))
			return false;
		if (getOwnerUUID().equals(player.getUUID()))
			return true;
		else
			return ClimateCore.proxy.isOP(player);
	}

	public int getAge() {
		return this.getEntityData().get(AGE).intValue();
	}

	public void setAge(int i) {
		this.getEntityData().set(AGE, i);
	}

	public boolean getLocked() {
		return this.getEntityData().get(LOCK).booleanValue();
	}

	public void setLocked(boolean b) {
		this.getEntityData().set(LOCK, Boolean.valueOf(b));
	}

	public Player getOwnerPlayer() {
		try {
			UUID uuid = this.getOwnerUUID();
			return uuid == null ? null : this.level.getPlayerByUUID(uuid);
		} catch (IllegalArgumentException exception) {
			return null;
		}
	}

	@Override
	public Entity getOwner() {
		return getOwnerPlayer();
	}

}
