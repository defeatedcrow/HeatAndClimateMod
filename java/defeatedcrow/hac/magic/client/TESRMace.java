package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.magic.block.BlockMace;
import defeatedcrow.hac.magic.block.TileMaceBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRMace extends TileEntitySpecialRenderer<TileMaceBase> {

	private final ResourceLocation TEX = new ResourceLocation(ClimateCore.PACKAGE_ID,
			"textures/entity/magic/magicmace_light.png");
	private static final ModelMace MODEL = new ModelMace();

	@Override
	public void renderTileEntityAt(TileMaceBase te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		if (te != null) {
			this.bindTexture(getTex());
			IBlockState state = te.getWorld().getBlockState(te.getPos());
			if (state != null && state.getBlock() instanceof BlockMace) {

				EnumFacing face = DCState.getFace(state, DCState.FACING);
				float yi = 0F;

				if (face == EnumFacing.NORTH) {
					yi = 0F;
				}
				if (face == EnumFacing.SOUTH) {
					yi = 180F;
				}
				if (face == EnumFacing.EAST) {
					yi = -90F;
				}
				if (face == EnumFacing.WEST) {
					yi = 90F;
				}

				GlStateManager.pushMatrix();
				GlStateManager.enableRescaleNormal();
				GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
				GlStateManager.scale(1.0F, -1.0F, -1.0F);

				GlStateManager.rotate(yi, 0.0F, 1.0F, 0.0F);
				MODEL.render(0.0625F * 1.0F);
				GlStateManager.disableRescaleNormal();
				GlStateManager.popMatrix();

				GlStateManager.pushMatrix();
				GlStateManager.enableRescaleNormal();
				GlStateManager.disableLighting();
				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
						GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
						GlStateManager.DestFactor.ZERO);

				int i = 15728880;
				int j = i % 65536;
				int k = i / 65536;
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);

				GlStateManager.color(1.0F, 1.0F, 1.0F, 0.85F);
				GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
				GlStateManager.scale(1.0F, -1.0F, -1.0F);

				GlStateManager.rotate(yi, 0.0F, 1.0F, 0.0F);
				MODEL.renderClear(0.0625F * 1.0F);
				GlStateManager.enableLighting();
				GlStateManager.disableBlend();
				GlStateManager.disableRescaleNormal();
				GlStateManager.popMatrix();
			}
		}
	}

	protected ResourceLocation getTex() {
		return TEX;
	}
}
