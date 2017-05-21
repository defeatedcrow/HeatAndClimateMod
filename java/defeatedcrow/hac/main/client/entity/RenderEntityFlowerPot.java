package defeatedcrow.hac.main.client.entity;

import defeatedcrow.hac.main.client.model.ModelFlowerPot;
import defeatedcrow.hac.main.entity.EntityFlowerPot;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityFlowerPot extends Render<EntityFlowerPot> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/flower_pot_white.png");
	private static final ResourceLocation BROWN_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/flower_pot_brown.png");
	private static final ModelFlowerPot MODEL = new ModelFlowerPot(false);

	public RenderEntityFlowerPot(RenderManager renderManager) {
		super(renderManager);
		this.shadowSize = 0.2F;
		this.shadowOpaque = 0.2F;
	}

	@Override
	public void doRender(EntityFlowerPot entity, double x, double y, double z, float yaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.translate((float) x, (float) y + 0.0625D, (float) z);
		GlStateManager.scale(-0.5F, -0.5F, 0.5F);
		boolean b = entity.getColor();
		if (b) {
			this.bindTexture(BROWN_TEX);
		} else {
			this.bindTexture(TEX);
		}

		float rotX = -entity.rotationPitch;
		float rotY = 180 - entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		MODEL.render(0.0625F, null);

		RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();

		ItemStack flower = entity.getFlower();
		if (flower != null && entity.isFlower(flower)) {
			Block block = ((ItemBlock) flower.getItem()).getBlock();
			int meta = flower.getItemDamage();
			renderCrossBlock(block, meta, x, y, z, yaw, partialTicks);
		}

		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFlowerPot entity) {
		return TEX;
	}

	private void renderCrossBlock(Block block, int meta, double x, double y, double z, float yaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.translate(x, y + 0.2F, z);
		GlStateManager.rotate(yaw, 0.0F, 1.0F, 0.0F);
		this.renderManager.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		ModelManager manager = dispatcher.getBlockModelShapes().getModelManager();
		IBakedModel model;

		IBlockState state = block.getStateFromMeta(meta);
		if (block instanceof BlockDoublePlant) {
			state.withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER);
		}
		model = manager.getBlockModelShapes().getModelForState(state);

		if (block == Blocks.CACTUS) {
			GlStateManager.translate(-0.075F, 0.0F, -0.075F);
			GlStateManager.scale(0.15F, 0.5F, 0.15F);
		} else {
			GlStateManager.translate(-0.25F, 0.0F, -0.25F);
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
		}
		float f = 1.0F;
		float f1 = 1.0F;
		float f2 = 1.0F;
		MapColor mapcolor = state.getMapColor();
		if (mapcolor != null) {
			int i = mapcolor.colorValue;
			f = (i >> 16 & 255) / 255.0F;
			f1 = (i >> 8 & 255) / 255.0F;
			f2 = (i & 255) / 255.0F;
		}
		dispatcher.getBlockModelRenderer().renderModelBrightnessColor(model, 1.0F, f, f1, f2);
		RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();

	}

}
