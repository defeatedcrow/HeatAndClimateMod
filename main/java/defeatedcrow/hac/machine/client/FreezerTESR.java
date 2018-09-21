package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.client.base.DCTorqueTESRBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.machine.client.model.ModelFreezer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FreezerTESR extends DCTorqueTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/freezer_sus.png";
	private static final ModelFreezer MODEL = new ModelFreezer();

	@Override
	public void render(TileTorqueBase te, DCTileModelBase model, float rot, float speed, float tick) {
		EnumFacing base = te.getBaseSide();
		EnumFacing face = te.getFaceSide();
		float x = 0F;
		float y = 0F;
		float z = 0F;

		switch (base) {
		case DOWN:
			x = 90F;
			break;
		case UP:
			x = -90F;
			break;
		case NORTH:
			y = 180F;
			break;
		case SOUTH:
			break;
		case EAST:
			y = -90F;
			break;
		case WEST:
			y = 90F;
			break;
		default:
			break;
		}

		GlStateManager.rotate(x, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(z, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(y, 0.0F, 1.0F, 0.0F);
	}

	@Override
	public void render(TileTorqueBase te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		DCTileModelBase model = this.getModel(te);
		if (model == null)
			return;
		float speed = te.currentSpeed;
		float rot = te.prevRotation + (te.currentRotation - te.prevRotation) * partialTicks;

		this.bindTexture(new ResourceLocation(getTexPass(0)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		this.render(te, model, rot, speed, partialTicks);
		model.render(rot, speed, partialTicks);

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	@Override
	protected String getTexPass(int i) {
		return TEX;
	}

	@Override
	protected DCTileModelBase getModel(TileTorqueBase te) {
		return MODEL;
	}
}
