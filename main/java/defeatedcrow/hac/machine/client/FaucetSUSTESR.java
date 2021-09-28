package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.machine.client.model.ModelFaucet_SUS_LA;
import defeatedcrow.hac.machine.client.model.ModelFaucet_SUS_LB;
import defeatedcrow.hac.machine.client.model.ModelFaucet_SUS_LC;
import defeatedcrow.hac.machine.client.model.ModelFaucet_SUS_LD;
import defeatedcrow.hac.machine.client.model.ModelFaucet_SUS_SA;
import defeatedcrow.hac.machine.client.model.ModelFaucet_SUS_SB;
import defeatedcrow.hac.main.api.IColorChangeTile;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FaucetSUSTESR extends DCTESRBase {

	private static final DCTileModelBase MODEL_LA = new ModelFaucet_SUS_LA();
	private static final DCTileModelBase MODEL_LB = new ModelFaucet_SUS_LB();
	private static final DCTileModelBase MODEL_LC = new ModelFaucet_SUS_LC();
	private static final DCTileModelBase MODEL_LD = new ModelFaucet_SUS_LD();
	private static final DCTileModelBase MODEL_SA = new ModelFaucet_SUS_SA();
	private static final DCTileModelBase MODEL_SB = new ModelFaucet_SUS_SB();

	@Override
	protected DCTileModelBase getModel(int i) {
		return null;
	}

	@Override
	public void render(DCTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		float x1 = 0F;
		float y1 = 0F;
		float z1 = 0F;
		boolean power = false;
		if (te.hasWorld()) {
			int meta = te.getBlockMetadata() & 7;
			EnumSide face = EnumSide.fromIndex(meta);
			power = te.getBlockMetadata() > 7;

			int color = 0;
			if (te instanceof IColorChangeTile)
				color = ((IColorChangeTile) te).getColor();

			if (face == EnumSide.DOWN || face == EnumSide.UP) {

				DCTileModelBase model = this.getModel_S(color);
				this.bindTexture(new ResourceLocation(getTexPass_S(color)));
				float vulve = power ? 90F : 0F;

				GlStateManager.pushMatrix();
				GlStateManager.enableRescaleNormal();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
				GlStateManager.scale(1.0F, -1.0F, -1.0F);

				model.render(vulve, 0F, a);
				GlStateManager.disableRescaleNormal();
				GlStateManager.popMatrix();
			} else {
				switch (face) {
				case NORTH:
					y1 = 0F;
					break;
				case SOUTH:
					y1 = 180F;
					break;
				case EAST:
					y1 = 90F;
					break;
				case WEST:
					y1 = -90F;
					break;
				default:
					break;
				}
				DCTileModelBase model = this.getModel_L(color);
				this.bindTexture(new ResourceLocation(getTexPass_L(color)));
				float vulve = power ? -90F : 0F;

				GlStateManager.pushMatrix();
				GlStateManager.enableRescaleNormal();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
				GlStateManager.scale(1.0F, -1.0F, -1.0F);

				GlStateManager.rotate(y1, 0.0F, 1.0F, 0.0F);
				model.render(vulve, 0F, a);
				GlStateManager.disableRescaleNormal();
				GlStateManager.popMatrix();
			}
		}
	}

	protected DCTileModelBase getModel_L(int i) {
		switch (i) {
		case 0:
			return MODEL_LA;
		case 1:
			return MODEL_LB;
		case 2:
			return MODEL_LC;
		case 3:
			return MODEL_LC;
		case 4:
			return MODEL_LD;
		}
		return MODEL_LA;
	}

	protected DCTileModelBase getModel_S(int i) {
		switch (i) {
		case 0:
			return MODEL_SA;
		case 1:
			return MODEL_SA;
		case 2:
			return MODEL_SB;
		}
		return MODEL_SA;
	}

	protected String getTexPass_L(int i) {
		switch (i) {
		case 0:
			return "dcs_climate:textures/tiles/faucet_sus_la.png";
		case 1:
			return "dcs_climate:textures/tiles/faucet_sus_lb.png";
		case 2:
			return "dcs_climate:textures/tiles/faucet_sus_lc.png";
		case 3:
			return "dcs_climate:textures/tiles/faucet_sus_ld.png";
		case 4:
			return "dcs_climate:textures/tiles/faucet_sus_le.png";
		}
		return "dcs_climate:textures/tiles/faucet_sus_la.png";
	}

	protected String getTexPass_S(int i) {
		switch (i) {
		case 0:
			return "dcs_climate:textures/tiles/faucet_sus_sa.png";
		case 1:
			return "dcs_climate:textures/tiles/faucet_sus_sb.png";
		case 2:
			return "dcs_climate:textures/tiles/faucet_sus_sc.png";
		}
		return "dcs_climate:textures/tiles/faucet_sus_sa.png";
	}
}
