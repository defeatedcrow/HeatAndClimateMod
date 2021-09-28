package defeatedcrow.hac.magic.proj;

import defeatedcrow.hac.main.entity.EntityProjBase;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageMagicParticle;
import defeatedcrow.hac.main.util.CustomExplosion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityProjDogSpit extends EntityProjBase {

	public float damage = 10.0F;
	public float range = 4.0F;

	public EntityProjDogSpit(World worldIn) {
		super(worldIn);
	}

	public EntityProjDogSpit(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjDogSpit(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	public EntityProjDogSpit setExplodeRange(float f) {
		range = f;
		return this;
	}

	@Override
	public ItemStack getDropStack() {
		return ItemStack.EMPTY;
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// 爆発発生

	@Override
	protected boolean onGroundHit() {
		EntityLivingBase ign = null;
		if (this.shootingEntity instanceof EntityLivingBase) {
			ign = (EntityLivingBase) this.shootingEntity;
		}
		CustomExplosion explosion = new CustomExplosion(world, this, ign, posX, posY, posZ, range,
				CustomExplosion.Type.Friends, false);
		explosion.doExplosion();
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
	protected void onHitEffect() {
		DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(posX, posY, posZ,
				(byte) EnumParticleTypes.EXPLOSION_LARGE.getParticleID(), 1.0F, 0.0F, 0.0F));
	}

	// no gravity
	@Override
	public boolean hasNoGravity() {
		return true;
	}

	@Override
	public boolean isInWater() {
		return false;
	}

}
