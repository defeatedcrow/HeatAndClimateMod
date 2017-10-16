package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.block.device.TileWindVane;
import defeatedcrow.hac.main.client.model.ModelWindVane;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class TESRWindVane extends DCTESRBase {

	private final ModelWindVane model = new ModelWindVane();

	@Override
	public void render(DCTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		int type = 0;
		int face = 0;
		float f = 0.0F;
		float rotation = 0.0F;
		float crowR = 0.0F;

		if (te.hasWorld() && te instanceof TileWindVane) {
			int meta = te.getBlockMetadata();
			TileWindVane meter = (TileWindVane) te;
			crowR = meter.lastPower + (meter.windPower - meter.lastPower) * partialTicks;
			crowR *= 2.0F;

			type = meta & 3;
			face = DCUtil.getWorldWind(te.getWorld()).getIndex();
			if (face == 4) {
				f += 0F;
			}
			if (face == 5) {
				f += 180F;
			}
			if (face == 2) {
				f += -90F;
			}
			if (face == 3) {
				f += 90F;
			}

			rotation = meter.lastRot + (meter.rot - meter.lastRot) * partialTicks;

			this.bindTexture(new ResourceLocation(getTexPass(type)));

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(f + crowR - 20.0F, 0.0F, 1.0F, 0.0F);
			model.renderCrow(0.0F);
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
			model.render(0.0F);
			model.renderScrew(rotation);
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

		}
	}

	@Override
	protected String getTexPass(int i) {
		if (ClimateMain.month == 3 && ClimateMain.day == 1)
			return "dcs_climate:textures/tiles/weather_vane_crow.png";
		else if (ClimateMain.month == 4) {
			if (ClimateMain.day < 6)
				return "dcs_climate:textures/tiles/weather_vane_gw.png";
		} else if (ClimateMain.month == 6) {
			if (ClimateMain.day > 3 && ClimateMain.day < 10)
				return "dcs_climate:textures/tiles/weather_vane_swan.png";
		} else if (ClimateMain.month == 9) {
			if (ClimateMain.day > 26 && ClimateMain.day <= 31)
				return "dcs_climate:textures/tiles/weather_vane_bat.png";
		} else if (ClimateMain.month == 11) {
			if (ClimateMain.day > 20 && ClimateMain.day < 27)
				return "dcs_climate:textures/tiles/weather_vane_xmas.png";
		}
		return "dcs_climate:textures/tiles/weather_vane.png";
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return model;
	}

}
