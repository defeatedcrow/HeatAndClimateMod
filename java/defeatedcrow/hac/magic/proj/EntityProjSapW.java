package defeatedcrow.hac.magic.proj;

import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.entity.EntityProjBase;
import defeatedcrow.hac.main.util.CustomExplosion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityProjSapW extends EntityProjBase {

	public EntityProjSapW(World worldIn) {
		super(worldIn);
	}

	public EntityProjSapW(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjSapW(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerMagic, 1, 1);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// 爆発発生

	@Override
	protected boolean onGroundHit() {
		EntityLivingBase ign = null;
		if (this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
			ign = (EntityLivingBase) this.shootingEntity;
		}
		CustomExplosion explosion = new CustomExplosion(worldObj, this, ign, posX, posY, posZ, 5F,
				CustomExplosion.Type.Silk, true);
		explosion.doExplosion();
		this.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 1.8F / (this.rand.nextFloat() * 0.2F + 0.9F));
		this.setDead();
		return true;
	}

	@Override
	protected boolean onEntityHit(Entity entity) {
		setStart(true);
		this.inGround = true;
		return true;
	}

	@Override
	protected void onGroundClient() {
		// DCLogger.debugLog("on client");
		worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX, posY, posZ, 1.0D, 0.0D, 0.0D, new int[0]);
	}

}
