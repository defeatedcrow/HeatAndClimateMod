package defeatedcrow.hac.food.client;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.food.block.TileIncubator;
import defeatedcrow.hac.food.client.model.ModelBlockIncubator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRIncubator extends DCLockableTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/incubator.png";
	private static final ModelBlockIncubator MODEL = new ModelBlockIncubator();

	@Override
	public void render(DCLockableTE te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		int type = 0;
		int face = 0;
		float f = 0.0F;
		boolean open = false;
		DCHeatTier tier = DCHeatTier.WARM;
		if (te instanceof TileIncubator) {
			TileIncubator tile = (TileIncubator) te;
			open = tile.isOpen;
			if (tile.getClimate() != null) {
				tier = tile.getClimate().getHeat();
			}
		}

		if (te.hasWorld()) {
			int meta = te.getBlockMetadata();

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
		}

		this.bindTexture(new ResourceLocation(getTexPass(type)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
		MODEL.setDoorRotation(open);
		MODEL.render(0.0625F);

		this.bindTexture(new ResourceLocation("dcs_climate:textures/gui/icon_" + tier.toString()
				.toLowerCase() + ".png"));
		MODEL.renderPanel(0.0625F);

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	@Override
	protected String getTexPass(int i) {
		return TEX;
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return MODEL;
	}
}
