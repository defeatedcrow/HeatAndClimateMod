package defeatedcrow.hac.magic.proj;

import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.client.particle.ParticleFallingStar;
import defeatedcrow.hac.main.client.particle.ParticleShock;

public class EntityProjLapS extends EntityMagicProjBase {

	public EntityProjLapS(World worldIn) {
		super(worldIn);
	}

	public EntityProjLapS(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjLapS(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerMagic, 1, 10);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// Gravity付与

	@Override
	protected boolean onGroundHit() {
		BlockPos pos = this.getPosition();
		EntityProjBarrier circle = new EntityProjBarrier(worldObj);
		circle.setPosition(this.posX, this.posY - 0.5D, this.posZ);
		worldObj.spawnEntityInWorld(circle);

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
		shock.setRBGColorF(0.65F, 0.25F, 0.65F);
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
			star.setRBGColorF(0.65F, 0.25F, 0.65F);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(star);

			i++;
		}
	}

}
