package defeatedcrow.hac.main.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleFlameDC extends Particle {

	public static final String FLAME_TEX = new String("dcs_climate:particles/particle_light");

	private float flameScale;

	protected ParticleFlameDC(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.motionX = xSpeedIn;
		this.motionY = ySpeedIn;
		this.motionZ = zSpeedIn;
		this.flameScale = this.particleScale;
		this.particleAlpha = 0.85F;
		this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
		this.particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D));

		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite sprite = texturemap.getAtlasSprite(FLAME_TEX);
		this.setParticleTexture(sprite);
	}

	@Override
	public void moveEntity(double x, double y, double z) {
		this.setEntityBoundingBox(this.getEntityBoundingBox().offset(x, y, z));
		this.resetPositionToBB();
	}

	/**
	 * Renders the particle
	 */
	@Override
	public void renderParticle(VertexBuffer worldRendererIn, Entity entityIn, float partialTicks, float rotationX,
			float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		float f = (this.particleAge + partialTicks) / this.particleMaxAge;
		this.particleScale = this.flameScale * (0.25F + f * f * 0.5F);
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
		super.renderParticle(worldRendererIn, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY,
				rotationXZ);
		GlStateManager.disableBlend();
	}

	@Override
	public int getBrightnessForRender(float p_189214_1_) {
		float f = (this.particleAge + p_189214_1_) / this.particleMaxAge;
		f = MathHelper.clamp_float(f, 0.0F, 1.0F);
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

		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.96D;
		this.motionY *= 0.96D;
		this.motionZ *= 0.96D;

		if (this.isCollided) {
			this.motionX *= 0.7D;
			this.motionZ *= 0.7D;
		}
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		@Override
		public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn,
				double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new ParticleFlameDC(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		}
	}

	@Override
	public int getFXLayer() {
		return 1;
	}
}
