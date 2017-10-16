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
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
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
		super(world, source, posX, posY, posZ, sizeIn, false, smoke);
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
		this.size *= 2.0F;
		i = MathHelper.floor(this.expX - this.size - 1.0D);
		int i2 = MathHelper.floor(this.expX + this.size + 1.0D);
		j = MathHelper.floor(this.expY - this.size - 1.0D);
		int j2 = MathHelper.floor(this.expY + this.size + 1.0D);
		k = MathHelper.floor(this.expZ - this.size - 1.0D);
		int k2 = MathHelper.floor(this.expZ + this.size + 1.0D);
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.bomb,
				new AxisAlignedBB(i, j, k, i2, j2, k2));
		Vec3d vec3 = new Vec3d(this.expX, this.expY, this.expZ);

		for (int i1 = 0; i1 < list.size(); ++i1) {
			Entity entity = (Entity) list.get(i1);
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
				float damage = (int) ((this.size * this.size * 2) * (d11 * d11));
				damage = Math.max(damage, 15.0F);

				if (this.type == Type.Silk) {
					if (entity instanceof EntityItem || entity instanceof IProjectile || entity == this.bomb) {
						flag = false;
					} else if (entity instanceof EntityLivingBase) {
						if (this.igniter != null) {
							EntityLivingBase living = (EntityLivingBase) entity;
							flag = !(living == this.igniter);
						}
					} else if (entity instanceof EntityBoat || entity instanceof EntityMinecart) {
						flag = false;
					} else if (entity instanceof EntityPlayer) {
						flag = false;
					}
				} else if (this.type == Type.Anchor) {
					if (entity instanceof EntityLivingBase) {
						if (this.igniter != null) {
							EntityLivingBase living = (EntityLivingBase) entity;
							flag = !(living == this.igniter);
						}
					} else if (entity instanceof EntityBoat || entity instanceof EntityMinecart) {
						flag = false;
					} else if (entity instanceof EntityPlayer) {
						flag = false;
					} else if (entity instanceof EntityDragon) {
						damage *= 2.0F;
					} else if (!entity.onGround) {
						damage *= 10.0F;
					}
				} else {
					if (entity instanceof EntityLivingBase) {
						if (this.igniter != null) {
							EntityLivingBase living = (EntityLivingBase) entity;
							flag = !(living == this.igniter);
						}
					}
				}

				if (flag) {
					if (entity instanceof IProjectile) {
						entity.setDead();
					} else {
						entity.attackEntityFrom(DamageSource.causeExplosionDamage(this), damage);
					}
					entity.motionY += 0.3D;
				}
			}
		}
	}

	/**
	 * Does the second part of the explosion (sound, particles, drop spawn)
	 */
	public void doExplosionEffect(boolean eff, boolean flame) {
		this.worldObj.playSound((EntityPlayer) null, this.expX, this.expY, this.expZ,
				SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F,
				(1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);

		if (this.size >= 2.0F && eff) {
			this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.expX, this.expY, this.expZ, 1.0D, 0.0D,
					0.0D, new int[0]);
		} else {
			this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.expX, this.expY, this.expZ, 1.0D, 0.0D,
					0.0D, new int[0]);
		}

		// if (eff) {
		// for (BlockPos blockpos : this.getAffectedBlockPositions()) {
		// IBlockState iblockstate = this.worldObj.getBlockState(blockpos);
		// Block block = iblockstate.getBlock();
		//
		// if (eff) {
		// double d0 = blockpos.getX() + this.worldObj.rand.nextFloat();
		// double d1 = blockpos.getY() + this.worldObj.rand.nextFloat();
		// double d2 = blockpos.getZ() + this.worldObj.rand.nextFloat();
		// double d3 = d0 - this.expX;
		// double d4 = d1 - this.expY;
		// double d5 = d2 - this.expZ;
		// double d6 = MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
		// d3 = d3 / d6;
		// d4 = d4 / d6;
		// d5 = d5 / d6;
		// double d7 = 0.5D / (d6 / this.size + 0.1D);
		// d7 = d7 * (this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
		// d3 = d3 * d7;
		// d4 = d4 * d7;
		// d5 = d5 * d7;
		// this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + this.expX) / 2.0D,
		// (d1 + this.expY) / 2.0D, (d2 + this.expZ) / 2.0D, d3, d4, d5, new int[0]);
		// this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5, new
		// int[0]);
		// }
		// }
		// }

		if (flame) {
			for (BlockPos blockpos1 : this.getAffectedBlockPositions()) {
				if (this.worldObj.getBlockState(blockpos1).getMaterial() == Material.AIR
						&& this.worldObj.getBlockState(blockpos1.down()).isFullBlock() && this.rand.nextInt(3) == 0) {
					this.worldObj.setBlockState(blockpos1, Blocks.FIRE.getDefaultState());
				}
			}
		}
	}

	public static enum Type {
		Silk,
		Anchor,
		Normal;
	}

}
