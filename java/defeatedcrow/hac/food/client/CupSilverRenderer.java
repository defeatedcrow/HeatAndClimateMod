package defeatedcrow.hac.food.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.food.client.model.ModelTeaCup;
import defeatedcrow.hac.food.entity.EntityTeaCupSilver;

@SideOnly(Side.CLIENT)
public class CupSilverRenderer extends DCEntityRenderBase<EntityTeaCupSilver> {

	private static final ResourceLocation RAW_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/teacup_silver.png");
	private static final ModelTeaCup MODEL = new ModelTeaCup(false);

	public CupSilverRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityTeaCupSilver entity, double x, double y, double z, float yaw, float partialTicks) {
		super.doRender(entity, x, y, z, yaw, partialTicks);
		Fluid f = FluidRegistry.getFluid(entity.getFluidName());
		if (f != null) {
			renderFluid(f, x, y, z, entity.rotationYaw, partialTicks);
		}
	}

	@Override
	protected ResourceLocation getFoodTexture() {
		return RAW_TEX;
	}

	@Override
	protected DCFoodModelBase getEntityModel() {
		return MODEL;
	}

	private void renderFluid(Fluid fluid, double x, double y, double z, float r, float partialTicks) {
		GlStateManager.disableLighting();
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite(fluid.getStill().toString());
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		float f2 = 0.45F;
		float f = 0.18F;
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		GlStateManager.rotate(-r, 0.0F, 1.0F, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		int i = 0;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		float uMin = textureatlassprite.getMinU();
		float vMin = textureatlassprite.getMinV();
		float uMax = textureatlassprite.getMaxU();
		float vMax = textureatlassprite.getMaxV();

		vertexbuffer.pos(f, f2, -f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, f2, -f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f, f2, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, f2, f).tex(uMax, vMin).endVertex();

		GlStateManager.rotate(0.0F, 0.0F, 0.0F, 0.0F);
		tessellator.draw();
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
	}
}
