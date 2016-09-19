package defeatedcrow.hac.magic.proj;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import defeatedcrow.hac.api.damage.DamageSourceClimate;
import defeatedcrow.hac.magic.MagicInit;

public class EntityProjChalB extends EntityMagicProjBase {

	public EntityProjChalB(World worldIn) {
		super(worldIn);
	}

	public EntityProjChalB(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjChalB(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerMagic, 1, 2);
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
			if (liv.isImmuneToFire()) {
				f *= 2.0F;
			}
		}
		return f;
	}

	@Override
	protected DamageSource getHitSource(Entity target) {
		return DamageSourceClimate.climateColdDamage;
	}
}
