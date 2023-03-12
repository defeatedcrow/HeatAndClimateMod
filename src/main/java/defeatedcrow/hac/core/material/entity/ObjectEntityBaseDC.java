package defeatedcrow.hac.core.material.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.material.IItemDropEntity;
import defeatedcrow.hac.api.util.TagKeyDC;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ObjectEntityBaseDC extends Entity implements IItemDropEntity {

	private static final EntityDataAccessor<ItemStack> ITEM = SynchedEntityData.defineId(ObjectEntityBaseDC.class, EntityDataSerializers.ITEM_STACK);
	private int age;
	@Nullable
	private UUID owner;

	public ObjectEntityBaseDC(EntityType<? extends ObjectEntityBaseDC> type, Level level) {
		super(type, level);
	}

	@Override
	protected Entity.MovementEmission getMovementEmission() {
		return Entity.MovementEmission.EVENTS;
	}

	@Override
	protected void defineSynchedData() {
		this.getEntityData().define(ITEM, ItemStack.EMPTY);
	}

	@Override
	public ItemStack getDropItem() {
		return getItem().isEmpty() ? ItemStack.EMPTY : getItem().copy();
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean isPushable() {
		return true;
	}

	@Override
	public boolean isPickable() {
		return !this.isRemoved();
	}

	@Override
	public void tick() {
		if (this.getItem().isEmpty() || this.isRemoved()) {
			this.discard();
		}
		super.tick();
		this.xo = this.getX();
		this.yo = this.getY();
		this.zo = this.getZ();
		Vec3 vec3 = this.getDeltaMovement();
		float f = this.getEyeHeight() - 0.2F;

		// 液体には浮かぶ
		net.minecraftforge.fluids.FluidType fluidType = this.getMaxHeightFluidType();
		if (!fluidType.isAir() && !fluidType.isVanilla() && this.getFluidTypeHeight(fluidType) > f) {
			this.setUnderFluidMovement();
		} else if (this.isInWater() && this.getFluidHeight(FluidTags.WATER) > f) {
			this.setUnderFluidMovement();
		} else if (this.isInLava() && this.getFluidHeight(FluidTags.LAVA) > f) {
			this.setUnderFluidMovement();
		} else if (!this.isNoGravity()) {
			this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
		}

		// 落下
		if (!this.onGround || this.getDeltaMovement().horizontalDistanceSqr() > 0.001F) {
			this.move(MoverType.SELF, this.getDeltaMovement());
			float f1 = 0.98F;
			if (this.onGround) {
				f1 = level.getBlockState(new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ())).getFriction(level, new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ()), this) * 0.98F;
			}

			this.setDeltaMovement(this.getDeltaMovement().multiply(f1, 0.98D, f1));
			if (this.onGround) {
				Vec3 vec31 = this.getDeltaMovement();
				if (vec31.y < 0.0D) {
					this.setDeltaMovement(vec31.multiply(1.0D, -0.5D, 1.0D));
				}
			}
		}

		if (this.age < 32768) {
			++this.age;
		}
	}

	private void setUnderFluidMovement() {
		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.x * 0.96F, vec3.y + (vec3.y < 0.06F ? 5.0E-4F : 0.0F), vec3.z * 0.96F);
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (!this.isAlive()) {
			return super.interact(player, hand);
		} else if (player != null) {
			this.dropItem(player.position());
			this.kill();
			return InteractionResult.SUCCESS;
		} else {
			this.dropItem(position());
			this.kill();
			return InteractionResult.SUCCESS;
		}
	}

	@Override
	public boolean hurt(DamageSource source, float damage) {
		if (level.isClientSide || this.isRemoved())
			return false;
		if (this.isInvulnerableTo(source)) {
			return false;
		} else if (!source.isExplosion() && !this.getItem().isEmpty()) {
			this.dropItem(position());
			this.kill();
			this.markHurt();
			return true;
		} else {
			return super.hurt(source, damage);
		}
	}

	public void dropItem(Vec3 pos) {
		if (!level.isClientSide && !getItem().isEmpty()) {
			ItemEntity drop = new ItemEntity(level, pos.x, pos.y + 0.1D, pos.z, getItem().copy());
			level.addFreshEntity(drop);
		}
	}

	@Override
	public boolean fireImmune() {
		return true;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		tag.putShort(TagKeyDC.AGE, (short) this.age);
		if (this.getOwner() != null) {
			tag.putUUID(TagKeyDC.OWNER_UUID, this.getOwner());
		}

		if (!this.getItem().isEmpty()) {
			tag.put(TagKeyDC.ITEM, this.getItem().save(new CompoundTag()));
		}

	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		this.age = tag.getShort(TagKeyDC.AGE);
		if (tag.hasUUID(TagKeyDC.OWNER_UUID)) {
			this.owner = tag.getUUID(TagKeyDC.OWNER_UUID);
		}

		CompoundTag compoundtag = tag.getCompound(TagKeyDC.ITEM);
		this.setItem(ItemStack.of(compoundtag));
		if (this.getItem().isEmpty()) {
			this.discard();
		}

	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this);
	}

	public ItemStack getItem() {
		return this.getEntityData().get(ITEM);
	}

	public void setItem(ItemStack item) {
		this.getEntityData().set(ITEM, item);
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

}
