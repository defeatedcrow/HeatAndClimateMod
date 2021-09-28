package defeatedcrow.hac.main.client.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleWaterDrip extends Particle {

	private float flameScale;

	protected ParticleWaterDrip(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;

		this.particleRed = 0.0F;
		this.particleGreen = 0.0F;
		this.particleBlue = 1.0F;

		this.setParticleTextureIndex(113);
		this.setSize(0.01F, 0.01F);
		this.particleGravity = 0.06F;
		this.particleMaxAge = (int) (64.0D / (Math.random() * 0.8D + 0.2D));
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		this.particleRed = 0.2F;
		this.particleGreen = 0.3F;
		this.particleBlue = 1.0F;

		this.motionY -= (double) this.particleGravity;

		this.setParticleTextureIndex(112);

		this.move(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

		if (this.particleMaxAge-- <= 0) {
			this.setExpired();
		}

		if (this.onGround) {
			this.setExpired();
			this.world.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}

	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		@Override
		public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn,
				double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new ParticleWaterDrip(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		}
	}
}
