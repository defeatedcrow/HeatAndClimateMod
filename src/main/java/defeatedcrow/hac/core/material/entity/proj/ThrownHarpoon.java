package defeatedcrow.hac.core.material.entity.proj;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.tool.HarpoonItem;
import defeatedcrow.hac.core.util.TierDC;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ThrownHarpoon extends AbstractArrow {

	private static final EntityDataAccessor<ItemStack> ITEM = SynchedEntityData.defineId(ThrownHarpoon.class, EntityDataSerializers.ITEM_STACK);
	private boolean dealtDamage;

	public ThrownHarpoon(EntityType<? extends ThrownHarpoon> type, Level level) {
		super(type, level);
		l = 0;
	}

	public ThrownHarpoon(Level level, LivingEntity owner, ItemStack stack) {
		super(CoreInit.HARPOON.get(), owner, level);
		this.setItem(stack.copy());
		l = 0;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(ITEM, ItemStack.EMPTY);
	}

	private int l = 0;

	@Override
	public void tick() {
		if (this.inGroundTime > 2) {
			this.dealtDamage = true;
		}

		Entity entity = this.getOwner();
		if (!getItem().isEmpty() && getItem().getItem() instanceof HarpoonItem harpoon) {
			if ((this.dealtDamage || this.isNoPhysics()) && entity != null) {
				if (harpoon.tier.getLevel() > 0 && pickup != AbstractArrow.Pickup.DISALLOWED) {
					if (!this.isAcceptibleReturnOwner()) {
						dropItem(this.getEyePosition());
					} else {
						dropItem(entity.getEyePosition());
					}
				}
				this.discard();
			}

			if (harpoon.tier == TierDC.BAMBOO) {
				if (l > 10)
					this.dealtDamage = true;
			}
		}
		l++;
		super.tick();
	}

	public void dropItem(Vec3 pos) {
		if (!level.isClientSide && !getItem().isEmpty()) {
			ItemEntity drop = new ItemEntity(level, pos.x, pos.y + 0.1D, pos.z, this.getPickupItem());
			level.addFreshEntity(drop);
		}
	}

	private boolean isAcceptibleReturnOwner() {
		Entity entity = this.getOwner();
		if (entity != null && entity.isAlive()) {
			return !entity.isSpectator();
		} else {
			return false;
		}
	}

	@Override
	protected ItemStack getPickupItem() {
		return getItem().isEmpty() ? new ItemStack(CoreInit.HARPOON_FLINT.get()) : getItem().copy();
	}

	public boolean isFoil() {
		return false;
	}

	@Override
	@Nullable
	protected EntityHitResult findHitEntity(Vec3 vec, Vec3 vec2) {
		return this.dealtDamage ? null : super.findHitEntity(vec, vec2);
	}

	@Override
	protected void onHitEntity(EntityHitResult res) {
		Entity entity = res.getEntity();
		float f = 5.0F;
		if (!getItem().isEmpty() && getItem().getItem() instanceof HarpoonItem harpoon) {
			f += harpoon.tier.getAttackDamageBonus();
			if (entity instanceof LivingEntity livingentity) {
				float f2 = 1F;
				f2 += EnchantmentHelper.getDamageBonus(getItem(), livingentity.getMobType());
				f *= f2;
			}

			Entity owner = this.getOwner();
			DamageSource damagesource = DamageSource.trident(this, owner == null ? this : owner);

			SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
			if (entity.hurt(damagesource, f)) {
				if (entity.getType() == EntityType.ENDERMAN) {
					return;
				}

				if (entity instanceof LivingEntity) {
					LivingEntity liv = (LivingEntity) entity;
					// ワープさせる
					if (this.isAcceptibleReturnOwner()) {
						liv.teleportTo(owner.getX(), owner.getY() + 0.015D, owner.getZ());
						if (this.level.isClientSide) {
							liv.yOld = liv.getY();
						}
					}

					if (owner instanceof LivingEntity) {
						EnchantmentHelper.doPostHurtEffects(liv, owner);
						EnchantmentHelper.doPostDamageEffects((LivingEntity) owner, liv);
					}
					this.doPostHurtEffects(liv);
				}
			}

			// 貫通
			if (!isPiercing()) {
				this.dealtDamage = true;
			}

			float f1 = 1.0F;
			this.playSound(soundevent, f1, 1.0F);
		}
	}

	public boolean isPiercing() {
		return EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PIERCING, getItem()) > 0;
	}

	public boolean isChanneling() {
		return EnchantmentHelper.hasChanneling(getItem());
	}

	@Override
	protected boolean tryPickup(Player player) {
		return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
	}

	@Override
	protected SoundEvent getDefaultHitGroundSoundEvent() {
		return SoundEvents.TRIDENT_HIT_GROUND;
	}

	@Override
	public void playerTouch(Player player) {
		if (this.ownedBy(player) || this.getOwner() == null) {
			super.playerTouch(player);
		}

	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.dealtDamage = tag.getBoolean("DealtDamage");
		if (!this.getItem().isEmpty()) {
			tag.put(TagKeyDC.ITEM, this.getItem().save(new CompoundTag()));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("DealtDamage", this.dealtDamage);
		CompoundTag compoundtag = tag.getCompound(TagKeyDC.ITEM);
		this.setItem(ItemStack.of(compoundtag));
		if (this.getItem().isEmpty()) {
			this.discard();
		}
	}

	public ItemStack getItem() {
		return this.getEntityData().get(ITEM);
	}

	public void setItem(ItemStack item) {
		this.getEntityData().set(ITEM, item);
	}

	@Override
	public void tickDespawn() {
		if (!getItem().isEmpty() && getItem().getItem() instanceof HarpoonItem harpoon) {
			if (pickup == AbstractArrow.Pickup.DISALLOWED)
				super.tickDespawn();
		}
	}

	@Override
	protected float getWaterInertia() {
		return 0.99F;
	}

	@Override
	public boolean shouldRender(double p_37588_, double p_37589_, double p_37590_) {
		return true;
	}
}
