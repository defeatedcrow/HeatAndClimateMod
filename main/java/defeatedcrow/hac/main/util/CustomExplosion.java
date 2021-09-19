package defeatedcrow.hac.main.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

/**
 * AMTからもってきたカスタムエクスプロ―ジョン
 * ・シルクメロン用の着火者、ドロップアイテムを消さない爆発 <br>
 * ・アンカーミサイル用飛行特効 <br>
 * すべての場合でブロックを破壊しない。
 */
public class CustomExplosion extends Explosion {

	private final double expX;
	private final double expY;
	private final double expZ;
	private final Entity bomb;
	private float size;

	private final Random rand = new Random();
	private final World worldObj;
	private final EntityLivingBase igniter;
	private final Type type;
	private Map playerMap = new HashMap();
	private final Vec3d pos;

	public CustomExplosion(World world, Entity source, EntityLivingBase ign, double posX, double posY, double posZ,
			float sizeIn, Type t, boolean smoke) {
		super(world, ign, posX, posY, posZ, sizeIn, false, smoke);
		this.worldObj = world;
		this.type = t;
		this.igniter = ign;

		this.bomb = source;
		this.size = sizeIn;
		this.expX = posX;
		this.expY = posY;
		this.expZ = posZ;
		this.pos = new Vec3d(expX, expY, expZ);
	}

	public void doExplosion() {
		float f = this.size;
		int i = 0;
		int j = 0;
		int k = 0;
		double d5 = this.expX;
		double d6 = this.expY;
		double d7 = this.expZ;

		// エンティティへのダメージ
		float r = size * 2F;
		i = MathHelper.floor(this.expX - r - 1.0D);
		int i2 = MathHelper.floor(this.expX + r + 1.0D);
		j = MathHelper.floor(this.expY - r - 1.0D);
		int j2 = MathHelper.floor(this.expY + r + 1.0D);
		k = MathHelper.floor(this.expZ - r - 1.0D);
		int k2 = MathHelper.floor(this.expZ + r + 1.0D);
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.bomb, new AxisAlignedBB(i, j, k, i2, j2,
				k2));
		ForgeEventFactory.onExplosionDetonate(this.worldObj, this, list, f);
		Vec3d vec3 = new Vec3d(this.expX, this.expY, this.expZ);

		for (int i1 = 0; i1 < list.size(); ++i1) {
			Entity entity = (Entity) list.get(i1);
			if (entity == null || entity.isImmuneToExplosions())
				continue;

			double d4 = entity.getDistance(this.expX, this.expY, this.expZ) / this.size;

			if (d4 <= 1.0D && d4 > 0.0D) {
				double d11 = (1.0D - d4);
				d5 = entity.posX - this.expX;
				d6 = entity.posY - this.expY;
				d7 = entity.posZ - this.expZ;
				d5 *= d11;
				d6 *= d11;
				d7 *= d11;

				boolean flag = true;
				float damage = (float) ((this.size * this.size * 2) * (d11 * d11));
				damage = MathHelper.clamp(4.0F, damage, size * 4F);

				if (entity == this.igniter || entity == this.bomb) {
					flag = false;
				} else if (!(entity instanceof EntityLivingBase)) {
					flag = false;
				} else if (entity instanceof EntityPlayer) {
					if (this.igniter instanceof EntityPlayer) {
						flag = ((EntityPlayer) igniter).canAttackPlayer((EntityPlayer) entity);
					}
				}

				if (this.type == Type.Friends && igniter != null) {
					EntityLivingBase owner = null;
					EntityLivingBase owner2 = null;
					if (igniter instanceof EntityTameable) {
						owner = ((EntityTameable) igniter).getOwner();
						if (owner == entity) {
							flag = false;
						}
					}
					if (entity instanceof EntityTameable) {
						owner2 = ((EntityTameable) entity).getOwner();
						if (owner2 == igniter) {
							flag = false;
						}
					}
				} else if (this.type == Type.Silk) {
					damage *= 0.5F;
				} else if (this.type == Type.Anchor) {
					if (entity instanceof EntityDragon) {
						damage *= 2.0F;
					} else if (!entity.onGround) {
						damage *= 2.0F;
					}
				}

				if (flag) {
					if (entity instanceof IProjectile) {
						entity.setDead();
					} else {
						entity.hurtResistantTime = 0;
						entity.attackEntityFrom(DamageSource.causeExplosionDamage(this), damage);
					}
					entity.motionY += 0.05D;
				}
			}
		}
	}

	/**
	 * Does the second part of the explosion (sound, particles, drop spawn)
	 */
	public void doExplosionEffect(boolean eff, boolean flame) {
		this.worldObj
				.playSound((EntityPlayer) null, this.expX, this.expY, this.expZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.worldObj.rand
						.nextFloat() - this.worldObj.rand.nextFloat()) * 0.1F * size) * 0.7F);

		if (this.size >= 3.0F && eff) {
			this.worldObj
					.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.expX, this.expY, this.expZ, 1.0D, 0.0D, 0.0D, new int[0]);
		} else {
			this.worldObj
					.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.expX, this.expY, this.expZ, 1.0D, 0.0D, 0.0D, new int[0]);
		}

		if (flame) {
			for (BlockPos blockpos1 : this.getAffectedBlockPositions()) {
				if (this.worldObj.getBlockState(blockpos1).getMaterial() == Material.AIR && this.worldObj
						.getBlockState(blockpos1.down()).isFullBlock() && this.rand.nextInt(3) == 0) {
					this.worldObj.setBlockState(blockpos1, Blocks.FIRE.getDefaultState());
				}
			}
		}
	}

	public static enum Type {
		Silk,
		Anchor,
		Friends,
		Normal;
	}

}
