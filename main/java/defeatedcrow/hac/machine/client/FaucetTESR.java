package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.machine.client.model.ModelFaucet_L;
import defeatedcrow.hac.machine.client.model.ModelFaucet_S;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FaucetTESR extends DCTESRBase {

	private static final DCTileModelBase MODEL1 = new ModelFaucet_S();
	private static final DCTileModelBase MODEL2 = new ModelFaucet_L();

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

			if (face == EnumSide.DOWN || face == EnumSide.UP) {

				this.bindTexture(new ResourceLocation(getTexPass(0)));
				float vulve = power ? -90F : 0F;

				GlStateManager.pushMatrix();
				GlStateManager.enableRescaleNormal();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
				GlStateManager.scale(1.0F, -1.0F, -1.0F);

				MODEL1.render(vulve, 0F, a);
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
				this.bindTexture(new ResourceLocation(getTexPass(1)));
				float vulve = power ? 0F : -90F;

				GlStateManager.pushMatrix();
				GlStateManager.enableRescaleNormal();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
				GlStateManager.scale(1.0F, -1.0F, -1.0F);

				GlStateManager.rotate(y1, 0.0F, 1.0F, 0.0F);
				MODEL2.render(vulve, 0F, a);
				GlStateManager.disableRescaleNormal();
				GlStateManager.popMatrix();
			}
		}
	}

	@Override
	protected String getTexPass(int i) {
		if (i == 0)
			return "dcs_climate:textures/tiles/faucet_s.png";
		else
			return "dcs_climate:textures/tiles/faucet_l.png";
	}
}
