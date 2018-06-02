package defeatedcrow.hac.main.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleTempColor extends Particle {

	public static final String TEMP_TEX = new String("dcs_climate:textures/particles/particle_temp.png");
	private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(
			DefaultVertexFormats.POSITION_3F).addElement(DefaultVertexFormats.TEX_2F).addElement(
					DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S).addElement(
							DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);
	private TextureManager textureManager;

	protected ParticleTempColor(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, int colorR,
			int colorG, int colorB) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0D, 0D, 0D);
		this.motionX = 0D;
		this.motionY = 0D;
		this.motionZ = 0D;
		this.particleRed = colorR / 255.0F;
		this.particleGreen = colorG / 255.0F;
		this.particleBlue = colorB / 255.0F;
		this.particleAlpha = 0.85F;
		this.particleMaxAge = 30;
		this.particleScale = 1.0F;

		this.textureManager = Minecraft.getMinecraft().getTextureManager();
	}

	public void setColor(float red, float green, float blue) {
		this.particleRed = red;
		this.particleGreen = green;
		this.particleBlue = blue;
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

		this.textureManager.bindTexture(new ResourceLocation(TEMP_TEX));
		float fu = 0.0F;
		float fU = 1.0F;
		float fv = 0.0F;
		float fV = 1.0F;
		float scale = this.particleScale;
		float fx = (float) (this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX);
		float fy = (float) (this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY);
		float fz = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ);
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.disableLighting();
		RenderHelper.disableStandardItemLighting();
		worldRendererIn.begin(7, VERTEX_FORMAT);
		worldRendererIn.pos(fx - 0.30D, fy + 0.565D, fz + 0.30D).tex(fU, fV).color(this.particleRed, this.particleGreen,
				this.particleBlue, this.particleAlpha).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
		worldRendererIn.pos(fx - 0.30D, fy + 0.565D, fz - 0.30D).tex(fU, fv).color(this.particleRed, this.particleGreen,
				this.particleBlue, this.particleAlpha).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
		worldRendererIn.pos(fx + 0.30D, fy + 0.565D, fz - 0.30D).tex(fu, fv).color(this.particleRed, this.particleGreen,
				this.particleBlue, this.particleAlpha).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
		worldRendererIn.pos(fx + 0.30D, fy + 0.565D, fz + 0.30D).tex(fu, fV).color(this.particleRed, this.particleGreen,
				this.particleBlue, this.particleAlpha).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
		Tessellator.getInstance().draw();
		GlStateManager.enableLighting();
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

		if (this.particleAge >= this.particleMaxAge) {
			this.setExpired();
		}
		this.particleAge++;
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		@Override
		public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn,
				double xSpeedIn, double ySpeedIn, double zSpeedIn, int... i) {
			int[] ints = i;
			if (i != null && i.length >= 3)
				return new ParticleTempColor(worldIn, xCoordIn, yCoordIn, zCoordIn, i[0], i[1], i[2]);
			return new ParticleTempColor(worldIn, xCoordIn, yCoordIn, zCoordIn, 255, 255, 255);
		}
	}

	@Override
	public int getFXLayer() {
		return 3;
	}
}
