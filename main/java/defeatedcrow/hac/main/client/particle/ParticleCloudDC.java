package defeatedcrow.hac.main.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleCloudDC extends Particle {

	public static final String CLOUD_TEX = new String("dcs_climate:particles/particle_cloud_anm");

	private float flameScale;

	protected ParticleCloudDC(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.motionX = xSpeedIn;
		this.motionY = 0.05D + ySpeedIn;
		this.motionZ = zSpeedIn;
		this.flameScale = this.particleScale;
		this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
		this.particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D)) + 4;

		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite sprite = texturemap.getAtlasSprite(CLOUD_TEX);
		this.setParticleTexture(sprite);
	}

	protected void setColor(int color) {
		int[] colors = getColor(color);
		this.particleRed = colors[0] / 255.0F;
		this.particleGreen = colors[1] / 255.0F;
		this.particleBlue = colors[2] / 255.0F;
	}

	public int[] getColor(int color) {
		int r = (color >> 16) & 255;
		int g = (color >> 8) & 255;
		int b = color & 255;
		return new int[] { r, g, b };
	}

	@Override
	public void move(double x, double y, double z) {
		this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
		this.resetPositionToBB();
	}

	/**
	 * Renders the particle
	 */
	@Override
	public void renderParticle(BufferBuilder worldRendererIn, Entity entityIn, float partialTicks, float rotationX,
			float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		float f = (this.particleAge + partialTicks) / this.particleMaxAge;
		this.particleScale = this.flameScale * (2.0F + f * 0.5F);
		super.renderParticle(worldRendererIn, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
	}

	@Override
	public int getBrightnessForRender(float p_189214_1_) {
		float f = (this.particleAge + p_189214_1_) / this.particleMaxAge;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		int i = super.getBrightnessForRender(p_189214_1_);
		int j = i & 255;
		int k = i >> 16 & 255;
		j = j + (int) (f * 15.0F * 16.0F);

		if (j > 240) {
			j = 240;
		}

		return j | k << 16;
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setExpired();
		}

		this.move(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9D;
		this.motionY *= 0.9D;
		this.motionZ *= 0.9D;
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		@Override
		public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn,
				double xSpeedIn, double ySpeedIn, double zSpeedIn, int... ints) {
			ParticleCloudDC ret = new ParticleCloudDC(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn,
					zSpeedIn);
			if (ints != null && ints.length > 0) {
				ret.setColor(ints[0]);
			}
			return ret;
		}
	}

	@Override
	public int getFXLayer() {
		return 1;
	}
}
