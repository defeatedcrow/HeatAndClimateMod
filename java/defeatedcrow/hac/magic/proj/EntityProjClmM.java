package defeatedcrow.hac.magic.proj;

import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.client.particle.ParticleFallingStar;
import defeatedcrow.hac.main.client.particle.ParticleShock;
import defeatedcrow.hac.main.entity.EntityProjBase;
import defeatedcrow.hac.main.worldgen.WorldGenWindmill;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityProjClmM extends EntityProjBase {

	public EntityProjClmM(World worldIn) {
		super(worldIn);
	}

	public EntityProjClmM(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjClmM(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerMagic, 1, 15);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// もうちょっと考える

	@Override
	protected boolean onGroundHit() {
		Chunk chunk = world.getChunkFromBlockCoords(this.getPosition());
		WorldGenWindmill gen = new WorldGenWindmill(true);
		gen.setForcePos(this.getPosition().getX(), this.getPosition().getZ());
		if (gen.generateWindmill(rand, chunk.x, chunk.z, world, world.provider.createChunkGenerator(),
				world.getChunkProvider())) {
			this.setDead();
		} else {
			this.dropAndDeath();
		}
		this.playSound(SoundEvents.ENTITY_FIREWORK_TWINKLE, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

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
		if (!this.world.isRemote && (this.isInWater() || this.isInLava())) {
			setStart(true);
		}
		super.onUpdate();
	}

	@Override
	protected void onGroundClient() {

		double x1 = posX + rand.nextDouble() - 0.5D;
		double y1 = posY + rand.nextDouble() - 0.5D;
		double z1 = posZ + rand.nextDouble() - 0.5D;
		Particle shock = new ParticleShock.Factory().createParticle(0, world, x1, y1, z1, 0D, 0D, 0D, new int[0]);
		shock.setRBGColorF(0.95F, 0.95F, 0.95F);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(shock);

		int i = 0;
		while (i < 3) {
			double x = posX + rand.nextDouble();
			double y = posY + 0.25D + rand.nextDouble();
			double z = posZ + rand.nextDouble();

			double fx = 0.5D * rand.nextDouble() - 0.25D;
			double fy = 0.5D + rand.nextDouble() * 0.25D;
			double fz = 0.5D * rand.nextDouble() - 0.25D;

			Particle star = new ParticleFallingStar.Factory().createParticle(0, world, x, y, z, fx, fy, fz, new int[0]);
			star.setRBGColorF(0.95F, 0.95F, 0.95F);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(star);

			i++;
		}
	}

}
