package defeatedcrow.hac.magic.proj;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityProjBarrier extends EntityMobBarrier {

	/* コンストラクタ */

	public EntityProjBarrier(World worldIn) {
		super(worldIn);
		this.setSize(6.0F, 3.0F);
	}

	public EntityProjBarrier(World worldIn, double posX, double posY, double posZ) {
		this(worldIn);
		this.setPosition(posX, posY, posZ);
	}

	public EntityProjBarrier(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		this(worldIn, posX, posY, posZ);
		if (player != null)
			this.rotationYaw = player.rotationYaw;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		// 接触判定
		if (!world.isRemote) {
			List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this,
					this.getEntityBoundingBox().grow(1.0D));
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Entity entity = list.get(i);
					if (entity != null) {
						if (entity instanceof IProjectile) {
							Entity shooter = null;
							if (entity instanceof EntityArrow) {
								EntityArrow arrow = (EntityArrow) entity;
								if (arrow.shootingEntity != null) {
									shooter = arrow.shootingEntity;
								}
							} else if (entity instanceof EntityThrowable) {
								EntityThrowable arrow = (EntityThrowable) entity;
								if (arrow.getThrower() != null) {
									shooter = arrow.getThrower();
								}
							} else if (entity instanceof EntityFireball) {
								EntityFireball arrow = (EntityFireball) entity;
								if (arrow.shootingEntity != null) {
									shooter = arrow.shootingEntity;
								}
							}

							if (shooter != null
									&& (shooter instanceof EntityPlayer || shooter instanceof EntityTameable)) {

							} else {
								entity.setDead();
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void applyEntityCollision(Entity entity) {}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(Entity entity) {
		return null;
	}

}
