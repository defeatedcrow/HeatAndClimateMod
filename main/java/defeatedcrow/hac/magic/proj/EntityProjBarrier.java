package defeatedcrow.hac.magic.proj;

import javax.annotation.Nullable;

import defeatedcrow.hac.main.entity.EntityBulletDC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.world.World;

public class EntityProjBarrier extends EntityMobBarrier {

	/* コンストラクタ */

	public EntityProjBarrier(World worldIn) {
		super(worldIn);
		this.setSize(1.0F, 1.0F);
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

	protected void collideWithEntity(Entity entity) {
		boolean flag = false;
		Entity shooter = null;
		if (entity instanceof EntityBulletDC) {
			EntityBulletDC arrow = (EntityBulletDC) entity;
			if (arrow.shootingEntity != null) {
				shooter = arrow.shootingEntity;
			}
			flag = true;
		} else if (entity instanceof EntityArrow) {
			EntityArrow arrow = (EntityArrow) entity;
			if (arrow.shootingEntity != null) {
				shooter = arrow.shootingEntity;
			}
			flag = true;
		} else if (entity instanceof EntityThrowable) {
			EntityThrowable arrow = (EntityThrowable) entity;
			if (arrow.getThrower() != null) {
				shooter = arrow.getThrower();
			}
			flag = true;
		} else if (entity instanceof EntityFireball) {
			EntityFireball arrow = (EntityFireball) entity;
			if (arrow.shootingEntity != null) {
				shooter = arrow.shootingEntity;
			}
			flag = true;
		} else if (entity instanceof IProjectile) {
			flag = true;
		}

		if (flag) {
			if (shooter instanceof IMob || shooter == null) {
				entity.setDead();
			}
		}
	}

	protected int[] color() {
		return new int[] {
				50,
				160,
				255
		};
	}

}
