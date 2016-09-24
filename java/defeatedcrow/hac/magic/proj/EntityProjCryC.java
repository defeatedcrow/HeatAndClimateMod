package defeatedcrow.hac.magic.proj;

import java.util.List;

import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.client.particle.ParticleFallingStar;
import defeatedcrow.hac.main.client.particle.ParticleShock;

public class EntityProjCryC extends EntityMagicProjBase {

	public EntityProjCryC(World worldIn) {
		super(worldIn);
	}

	public EntityProjCryC(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjCryC(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerMagic, 1, 7);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// Gravity付与

	@Override
	protected boolean onGroundHit() {
		BlockPos pos = this.getPosition();
		int x1 = MathHelper.floor_double(this.posX - 8.0D);
		int x2 = MathHelper.floor_double(this.posX + 8.0D);
		int y1 = MathHelper.floor_double(this.posY - 4.0D);
		int y2 = MathHelper.floor_double(this.posY + 4.0D);
		int z1 = MathHelper.floor_double(this.posZ - 8.0D);
		int z2 = MathHelper.floor_double(this.posZ + 8.0D);
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(x1, y1, z1, x2, y2, z2));
		Vec3d vec3 = new Vec3d(this.posX, this.posY, this.posZ);

		for (int i = 0; i < list.size(); ++i) {
			Entity entity = (Entity) list.get(i);
			if (entity.isEntityEqual(shootingEntity)) {
				continue;
			}
			if (entity != null && entity instanceof EntityLivingBase) {
				EntityLivingBase liv = (EntityLivingBase) entity;
				if (liv.isPotionActive(MobEffects.LEVITATION)) {
					PotionEffect eff = liv.getActivePotionEffect(MobEffects.LEVITATION);
					int dur = eff.getDuration();
					int amp = eff.getAmplifier();
					liv.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, dur + 120, amp));
				} else {
					liv.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 120, 1));
				}
			}
		}

		this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
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

		double x1 = (double) posX + rand.nextDouble() - 0.5D;
		double y1 = (double) posY + rand.nextDouble() - 0.5D;
		double z1 = (double) posZ + rand.nextDouble() - 0.5D;
		Particle shock = new ParticleShock.Factory().getEntityFX(0, worldObj, x1, y1, z1, 0D, 0D, 0D, new int[0]);
		shock.setRBGColorF(0.65F, 0.95F, 0.95F);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(shock);

		int i = 0;
		while (i < 3) {
			double x = (double) posX + rand.nextDouble();
			double y = (double) posY + 0.25D + rand.nextDouble();
			double z = (double) posZ + rand.nextDouble();

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
