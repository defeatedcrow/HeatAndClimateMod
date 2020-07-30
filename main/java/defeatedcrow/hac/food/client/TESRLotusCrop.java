package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.food.block.crop.TileEntityLotus;
import defeatedcrow.hac.food.client.model.ModelLotusBud1;
import defeatedcrow.hac.food.client.model.ModelLotusBud2;
import defeatedcrow.hac.food.client.model.ModelLotusFlower;
import defeatedcrow.hac.food.client.model.ModelLotusLeaves1;
import defeatedcrow.hac.food.client.model.ModelLotusLeaves2;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRLotusCrop extends TileEntitySpecialRenderer<TileEntityLotus> {

	private static final ModelLotusBud1 MODEL1 = new ModelLotusBud1();
	private static final ModelLotusBud2 MODEL2 = new ModelLotusBud2();
	private static final ModelLotusLeaves1 MODEL3 = new ModelLotusLeaves1();
	private static final ModelLotusLeaves2 MODEL4 = new ModelLotusLeaves2();
	private static final ModelLotusFlower MODEL5 = new ModelLotusFlower();
	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate", "textures/tiles/crop_lotus.png");
	private static final ResourceLocation FLOWER1 = new ResourceLocation("dcs_climate",
			"textures/blocks/crop/lotus_flower.png");
	private static final ResourceLocation FLOWER2 = new ResourceLocation("dcs_climate",
			"textures/blocks/crop/lotus_black.png");
	private static final ResourceLocation BUD1 = new ResourceLocation("dcs_climate",
			"textures/blocks/crop/lotus_bud.png");
	private static final ResourceLocation BUD2 = new ResourceLocation("dcs_climate",
			"textures/blocks/crop/lotus_bud_black.png");
	private static final ResourceLocation SEED = new ResourceLocation("dcs_climate",
			"textures/blocks/crop/lotus_seed.png");
	private static final ResourceLocation DEAD = new ResourceLocation("dcs_climate",
			"textures/tiles/crop_lotus_dead.png");

	@Override
	public void render(TileEntityLotus te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		int r1 = 3;
		int r2 = 15;
		int meta = 0;
		boolean black = false;
		DCTileModelBase model = null;

		if (te.hasWorld()) {
			r1 = te.rand1;
			r2 = te.rand2;
			meta = te.getBlockMetadata() & 7;
			black = te.getBlockMetadata() > 7;
		}

		switch (meta) {
		case 0:
			model = MODEL1;
			this.bindTexture(TEX);
			break;
		case 1:
			model = MODEL2;
			this.bindTexture(TEX);
			break;
		case 2:
			model = MODEL3;
			this.bindTexture(TEX);
			break;
		case 3:
			model = MODEL4;
			this.bindTexture(TEX);
			break;
		case 4:
		case 5:
		case 6:
			model = MODEL5;
			this.bindTexture(TEX);
			break;
		case 7:
			model = MODEL4;
			this.bindTexture(DEAD);
			break;
		default:
			model = MODEL1;
			this.bindTexture(TEX);
		}

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		model.setRotationAngles(r1 + r2);
		model.render(0.0625F);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		if (r1 == r2) {
			r2 += 2;
		}
		float fx1 = 0.5F;
		float fz1 = 0.5F;
		float fx2 = 0.5F;
		float fz2 = 0.5F;
		if (r1 > 7) {
			fz1 = 0.15F + ((r1 & 7) * 0.1F);
		} else {
			fx1 = 0.15F + ((r1 & 7) * 0.1F);
		}
		if (r2 > 7) {
			fz2 = 0.15F + ((r2 & 7) * 0.1F);
		} else {
			fx2 = 0.15F + ((r2 & 7) * 0.1F);
		}

		if (meta > 2) {

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 1F - fx2, (float) y + 1.5F, (float) z + 1F - fz2);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);

			MODEL5.setRotationAngles(r1 + r2);
			MODEL5.renderLeaf(0.0625F);

			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
		}

		if (meta > 3 && meta < 7) {

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + fx1, (float) y + 1.5F, (float) z + fz1);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);

			MODEL5.renderStem1(0.0625F);

			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

			if (meta > 4) {
				GlStateManager.pushMatrix();
				GlStateManager.enableRescaleNormal();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.translate((float) x + fx2, (float) y + 1.5F, (float) z + fz2);
				GlStateManager.scale(1.0F, -1.0F, -1.0F);

				MODEL5.renderStem2(0.0625F);

				GlStateManager.disableRescaleNormal();
				GlStateManager.popMatrix();
			}

			if (meta == 4) {
				if (black) {
					renderCross((float) x + fx1, (float) y + 2.05F, (float) z + fz1, BUD2);
				} else {
					renderCross((float) x + fx1, (float) y + 2.05F, (float) z + fz1, BUD1);
				}
			} else if (meta == 5) {
				if (black) {
					renderCross((float) x + fx1, (float) y + 2.05F, (float) z + fz1, FLOWER2);
					renderCross((float) x + fx2, (float) y + 1.85F, (float) z + fz2, BUD2);
				} else {
					renderCross((float) x + fx1, (float) y + 2.05F, (float) z + fz1, FLOWER1);
					renderCross((float) x + fx2, (float) y + 1.85F, (float) z + fz2, BUD1);
				}
			} else if (meta == 6) {
				if (black) {
					renderCross((float) x + fx1, (float) y + 2.05F, (float) z + fz1, SEED);
					renderCross((float) x + fx2, (float) y + 1.85F, (float) z + fz2, FLOWER2);
				} else {
					renderCross((float) x + fx1, (float) y + 2.05F, (float) z + fz1, SEED);
					renderCross((float) x + fx2, (float) y + 1.85F, (float) z + fz2, FLOWER1);
				}
			}

		}
	}

	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/crop_lotus.png";
	}

	private void renderCross(double x, double y, double z, ResourceLocation tex) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		int i = 0;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

		this.bindTexture(tex);
		float uMin = 0F;
		float vMin = 0F;
		float uMax = 1F;
		float vMax = 1F;
		float f = 0.4F;

		vertexbuffer.pos(f, 0F, f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, 0F, -f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f, 0.75F, -f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, 0.75F, f).tex(uMax, vMin).endVertex();

		vertexbuffer.pos(-f, 0F, -f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(f, 0F, f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(f, 0.75F, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(-f, 0.75F, -f).tex(uMax, vMin).endVertex();

		vertexbuffer.pos(f, 0F, -f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(-f, 0F, f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(-f, 0.75F, f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(f, 0.75F, -f).tex(uMax, vMin).endVertex();

		vertexbuffer.pos(-f, 0F, f).tex(uMax, vMax).endVertex();
		vertexbuffer.pos(f, 0F, -f).tex(uMin, vMax).endVertex();
		vertexbuffer.pos(f, 0.8F, -f).tex(uMin, vMin).endVertex();
		vertexbuffer.pos(-f, 0.8F, f).tex(uMax, vMin).endVertex();

		tessellator.draw();
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
	}
}
