package defeatedcrow.hac.machine.client;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.energy.TileTorqueProcessor;

@SideOnly(Side.CLIENT)
public abstract class DCTorqueProcessorTESRBase extends TileEntitySpecialRenderer<TileTorqueProcessor> {

	@Override
	public void renderTileEntityAt(TileTorqueProcessor te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		DCTileModelBase model = this.getModel(te);
		float speed = te.currentSpeed;
		float rot = te.rotation;

		this.bindTexture(new ResourceLocation(getTexPass(0)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		this.render(te, model, rot, speed, partialTicks);

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/stove_fuel.png";
	}

	protected abstract DCTileModelBase getModel(TileTorqueProcessor te);

	public void render(TileTorqueProcessor te, DCTileModelBase model, float rot, float speed, float tick) {
		EnumFacing base = te.getBaseSide().getOpposite();
		EnumFacing face = te.getFaceSide();
		float x = 0F;
		float y = 0F;
		float z = 0F;

		if (face == EnumFacing.NORTH) {
			y = 90F;
		}
		if (face == EnumFacing.SOUTH) {
			y = -90F;
		}
		if (face == EnumFacing.EAST) {
			y = 180F;
		}
		if (face == EnumFacing.WEST) {
			y = 0F;
		}

		switch (base) {
		case DOWN:
			break;
		case UP:
			x = 180F;
			break;
		case NORTH:
			x = 90F;
			break;
		case SOUTH:
			x = 90F;
			z = 180F;
			break;
		case EAST:
			x = 90F;
			z = -90F;
			break;
		case WEST:
			x = 90F;
			z = 90F;
			break;
		default:
			break;
		}

		GlStateManager.rotate(x, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(z, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(y, 0.0F, 1.0F, 0.0F);

		model.render(rot, speed, tick);
	}
}
