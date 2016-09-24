package defeatedcrow.hac.magic.proj;

import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.client.particle.ParticleFallingStar;
import defeatedcrow.hac.main.client.particle.ParticleShock;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityProjSchC extends EntityMagicProjBase {

	public EntityProjSchC(World worldIn) {
		super(worldIn);
	}

	public EntityProjSchC(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjSchC(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerMagic, 1, 13);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// ワープ

	@Override
	protected boolean onGroundHit() {
		if (this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
			EntityLivingBase liv = (EntityLivingBase) this.shootingEntity;
			liv.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60, 4));
			liv.setPositionAndUpdate(posX, posY + 0.25D, posZ);
			liv.fallDistance = 0.0F;
			this.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.8F / (this.rand.nextFloat() * 0.2F + 0.9F));
		}
		this.dropAndDeath();
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
		Particle shock = new ParticleShock.Factory().getEntityFX(0, worldObj, x1, y1, z1, 0D, 0D, 0D, new int[0]);
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

			Particle star = new ParticleFallingStar.Factory().getEntityFX(0, worldObj, x, y, z, fx, fy, fz, new int[0]);
			star.setRBGColorF(0.65F, 0.95F, 0.95F);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(star);

			i++;
		}
	}

}
