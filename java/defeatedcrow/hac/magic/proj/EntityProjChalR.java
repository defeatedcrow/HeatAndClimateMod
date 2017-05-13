package defeatedcrow.hac.magic.proj;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.entity.EntityProjBase;

public class EntityProjChalR extends EntityProjBase {

	public EntityProjChalR(World worldIn) {
		super(worldIn);
	}

	public EntityProjChalR(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjChalR(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerMagic, 1, 4);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// Hit時ノーマルダメージ

	@Override
	protected float getHitDamage(Entity target, float speed) {
		float f = 15.0F + this.worldObj.rand.nextFloat();
		if (target != null && target instanceof EntityLivingBase) {
			EntityLivingBase liv = (EntityLivingBase) target;
			if (liv.isImmuneToFire() || liv.isPotionActive(MobEffects.FIRE_RESISTANCE)) {
				f /= 2.0F;
			}
			if (liv.isEntityUndead()) {
				f *= 2.0F;
			}
		}
		return f;
	}

	@Override
	protected DamageSource getHitSource(Entity target) {
		return DamageSource.inFire;
	}

}
