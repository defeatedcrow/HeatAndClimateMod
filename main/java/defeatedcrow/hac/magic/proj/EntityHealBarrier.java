package defeatedcrow.hac.magic.proj;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityHealBarrier extends EntityMobBarrier {

	/* コンストラクタ */

	public EntityHealBarrier(World worldIn) {
		super(worldIn);
		this.setSize(1.0F, 1.0F);
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

	protected int cooltime() {
		return 20;
	}

	/* 特定種のEntityには異なる結果を */
	protected void collideWithEntity(Entity entity) {
		if (entity != null && entity instanceof EntityLivingBase) {
			EntityLivingBase liv = (EntityLivingBase) entity;
			if (!liv.isPotionActive(MobEffects.REGENERATION)) {
				liv.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
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
	}

	protected int[] color() {
		return new int[] {
				255,
				225,
				50
		};
	}

}
