package defeatedcrow.hac.core.material.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.TagKeyDC;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class MerodyEntity extends Entity {

	private static final EntityDataAccessor<Integer> MAX_AGE = SynchedEntityData.defineId(MerodyEntity.class, EntityDataSerializers.INT);
	private int age;
	@Nullable
	private UUID owner;

	public MerodyEntity(EntityType<? extends MerodyEntity> type, Level level) {
		super(type, level);
	}

	@Override
	protected Entity.MovementEmission getMovementEmission() {
		return Entity.MovementEmission.NONE;
	}

	@Override
	protected void defineSynchedData() {
		this.entityData.define(MAX_AGE, 0);
	}

	@Override
	public double getPassengersRidingOffset() {
		return this.getBbHeight() - 0.4D;
	}

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
		return false;
	}

	private int interval = 3;

	@Override
	public void tick() {
		if (this.isRemoved()) {
			this.discard();
		}

		if (interval > 0) {
			interval--;
		} else {
			interval = 3;
		}

		super.tick();

		if (this.age < 32768) {
			++this.age;
		}
		if (this.getMaxAge() > 0 && this.getMaxAge() < this.age) {
			this.discard();
		}
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (!this.isAlive()) {
			return super.interact(player, hand);
		} else {
			if (!this.getPassengers().isEmpty()) {
				this.ejectPassengers();
			}
			return InteractionResult.SUCCESS;
		}
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
		tag.putShort(TagKeyDC.AGE, (short) this.age);
		tag.putInt(TagKeyDC.MAX_AGE, this.getMaxAge());
		if (this.getOwner() != null) {
			tag.putUUID(TagKeyDC.OWNER_UUID, this.getOwner());
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		this.age = tag.getShort(TagKeyDC.AGE);
		this.setMaxAge(tag.getInt(TagKeyDC.MAX_AGE));
		if (tag.hasUUID(TagKeyDC.OWNER_UUID)) {
			this.owner = tag.getUUID(TagKeyDC.OWNER_UUID);
		}
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this);
	}

	@Nullable
	public UUID getOwner() {
		return this.owner;
	}

	public void setOwner(@Nullable UUID id) {
		this.owner = id;
	}

	public int getAge() {
		return this.age;
	}

	public int getMaxAge() {
		return this.getEntityData().get(MAX_AGE).intValue();
	}

	public void setMaxAge(int i) {
		this.getEntityData().set(MAX_AGE, i);
	}

}
