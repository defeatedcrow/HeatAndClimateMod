package defeatedcrow.hac.main.client.block;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.block.device.TileThermometer;
import defeatedcrow.hac.main.client.model.ModelThermoMeter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class TESRThermometer extends DCTESRBase {

	private final ModelThermoMeter model = new ModelThermoMeter();

	@Override
	public void renderTileEntityAt(DCTileEntity te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		int type = 0;
		int face = 0;
		float f = 0.0F;
		float[] points = new float[10];

		if (te.hasWorldObj() && te instanceof TileThermometer) {
			int meta = te.getBlockMetadata();
			TileThermometer meter = (TileThermometer) te;

			type = meta & 3;
			face = 5 - (meta >> 2);
			if (face == 2) {
				f = 0F;
			}
			if (face == 3) {
				f = 180F;
			}
			if (face == 4) {
				f = -90F;
			}
			if (face == 5) {
				f = 90F;
			}

			for (int id = 1; id < 11; id++) {
				int i = id - 1;
				points[i] = meter.lastFloats[id] + (meter.floats[id] - meter.lastFloats[id]) * partialTicks;
			}

			this.bindTexture(new ResourceLocation(getTexPass(type)));

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
			model.renderBase(0.0F, points);
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
			model.renderWater(0.0F);
			GL11.glDisable(GL11.GL_BLEND);
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
			model.renderGlass(0.0F);
			GL11.glDisable(GL11.GL_BLEND);
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

		}
	}

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/thermometer_glass.png";
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return model;
	}

}
