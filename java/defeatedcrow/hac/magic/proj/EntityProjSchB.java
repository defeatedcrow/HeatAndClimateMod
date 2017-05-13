package defeatedcrow.hac.magic.proj;

import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.client.particle.ParticleFallingStar;
import defeatedcrow.hac.main.client.particle.ParticleShock;
import defeatedcrow.hac.main.entity.EntityProjBase;
import defeatedcrow.hac.main.util.CustomExplosion;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityProjSchB extends EntityProjBase {

	public EntityProjSchB(World worldIn) {
		super(worldIn);
	}

	public EntityProjSchB(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjSchB(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerMagic, 1, 12);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// 雷雨を呼ぶ

	@Override
	protected boolean onGroundHit() {
		if (this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
			CustomExplosion explosion = new CustomExplosion(worldObj, this, (EntityLivingBase) shootingEntity, posX,
					posY + 0.25D, posZ, 3F, CustomExplosion.Type.Silk, true);
			explosion.doExplosion();
		}

		worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, posX, posY - 0.25D, posZ, false));
		WorldInfo worldinfo = worldObj.getWorldInfo();
		worldinfo.setRainTime(6000);
		worldinfo.setThunderTime(6000);
		worldinfo.setRaining(true);
		worldinfo.setThundering(true);
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
	public void onUpdate() {
		if (!this.worldObj.isRemote && (this.isInWater() || this.isInLava())) {
			setStart(true);
		}
		super.onUpdate();
	}

	@Override
	protected void onGroundClient() {

		double x1 = posX + rand.nextDouble() - 0.5D;
		double y1 = posY + rand.nextDouble() - 0.5D;
		double z1 = posZ + rand.nextDouble() - 0.5D;
		Particle shock = new ParticleShock.Factory().createParticle(0, worldObj, x1, y1, z1, 0D, 0D, 0D, new int[0]);
		shock.setRBGColorF(0.65F, 0.95F, 0.95F);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(shock);

		int i = 0;
		while (i < 3) {
			double x = posX + rand.nextDouble();
			double y = posY + 0.25D + rand.nextDouble();
			double z = posZ + rand.nextDouble();

			double fx = 0.5D * rand.nextDouble() - 0.25D;
			double fy = 0.5D + rand.nextDouble() * 0.25D;
			double fz = 0.5D * rand.nextDouble() - 0.25D;

			Particle star = new ParticleFallingStar.Factory().createParticle(0, worldObj, x, y, z, fx, fy, fz,
					new int[0]);
			star.setRBGColorF(0.65F, 0.95F, 0.95F);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(star);

			i++;
		}
	}

}
