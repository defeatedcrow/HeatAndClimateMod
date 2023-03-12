package defeatedcrow.hac.magic.material.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ArrowBlue extends AbstractArrow {

	private boolean dealtDamage;

	public ArrowBlue(EntityType<? extends ArrowBlue> type, Level level) {
		super(type, level);
	}

	public ArrowBlue(Level level, LivingEntity shooter) {
		super(MagicInit.ARROW_BLUE_ENTITY.get(), shooter, level);
	}

	public ArrowBlue(Level level, double x, double y, double z) {
		super(MagicInit.ARROW_BLUE_ENTITY.get(), x, y, z, level);
	}

	@Override
	public void tick() {
		if (this.inGroundTime > 0 || this.getY() < 0 || this.isInLava() || this.isInWater()) {
			this.dealtDamage = true;
		}

		if ((this.dealtDamage || this.isNoPhysics())) {
			boolean flag = false;
			BlockPos pos = new BlockPos(Mth.floor(this.xOld), Mth.floor(this.yOld), Mth.floor(this.zOld));
			if (level.getBlockState(pos).getMaterial().isReplaceable()) {
				if (this.isInLava())
					level.setBlock(pos, Blocks.STONE.defaultBlockState(), 3);
				else
					level.setBlock(pos, Blocks.PACKED_ICE.defaultBlockState(), 3);
				flag = true;
			}
			if (flag) {
				this.discard();
			} else {
				if (!this.isAcceptibleReturnOwner()) {
					dropItem(this.getEyePosition());
				} else {
					dropItem(getOwner().getEyePosition());
				}
				this.discard();
			}
		}

		super.tick();
		if (this.level.isClientSide && !this.inGround) {
			this.level.addParticle(ParticleTypes.INSTANT_EFFECT, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
		}

	}

	public void dropItem(Vec3 pos) {
		if (!level.isClientSide && !getPickupItem().isEmpty()) {
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
	@Nullable
	protected EntityHitResult findHitEntity(Vec3 vec, Vec3 vec2) {
		return null;
	}

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(MagicInit.ARROW_BLUE.get());
	}

}
