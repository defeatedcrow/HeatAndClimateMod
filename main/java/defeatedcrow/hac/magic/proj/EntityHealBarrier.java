package defeatedcrow.hac.magic.proj;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityHealBarrier extends EntityMobBarrier {

	/* コンストラクタ */

	public EntityHealBarrier(World worldIn) {
		super(worldIn);
		this.setSize(8.0F, 3.0F);
	}

	public EntityHealBarrier(World worldIn, double posX, double posY, double posZ) {
		this(worldIn);
		this.setPosition(posX, posY, posZ);
	}

	public EntityHealBarrier(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		this(worldIn, posX, posY, posZ);
		if (player != null) {
			this.rotationYaw = player.rotationYaw;
		}
	}

	int count = 20;

	/* 特定種のEntityには異なる結果を */
	@Override
	public void applyEntityCollision(Entity entity) {
		if (count <= 0) {
			count = 20;

			if (entity != null && entity instanceof EntityLivingBase) {
				EntityLivingBase liv = (EntityLivingBase) entity;
				if (!liv.isPotionActive(MobEffects.REGENERATION)) {
					liv.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 0));
				}
				if (liv.isPotionActive(MobEffects.POISON)) {
					liv.removePotionEffect(MobEffects.POISON);
				}
				if (liv.isPotionActive(MobEffects.WITHER)) {
					liv.removePotionEffect(MobEffects.WITHER);
				}
				if (liv.isPotionActive(MobEffects.NAUSEA)) {
					liv.removePotionEffect(MobEffects.NAUSEA);
				}
				if (liv.isPotionActive(MobEffects.BLINDNESS)) {
					liv.removePotionEffect(MobEffects.BLINDNESS);
				}
			}
		} else {
			count--;
		}
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(Entity entity) {
		if (entity != null && entity instanceof EntityLivingBase)
			return this.getEntityBoundingBox();
		return null;
	}

}
