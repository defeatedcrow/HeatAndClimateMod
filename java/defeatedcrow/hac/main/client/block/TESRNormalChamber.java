package defeatedcrow.hac.main.client.block;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.main.block.device.TileNormalChamber;

@SideOnly(Side.CLIENT)
public class TESRNormalChamber extends TileEntitySpecialRenderer<TileNormalChamber> {

	@Override
	public void renderTileEntityAt(TileNormalChamber te, double x, double y, double z, float partialTicks, int destroyStage) {
		int type = 0;
		int face = 0;
		float f = 0.0F;

		if (te.hasWorldObj()) {
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

		ModelNormalChamber model = new ModelNormalChamber();

		this.bindTexture(new ResourceLocation(getTexPass(type)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
		model.render(0);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	protected String getTexPass(int i) {
		if (i == 1) {
			return "dcs_climate:textures/tiles/normal_chamber_lit.png";
		}
		return "dcs_climate:textures/tiles/normal_chamber.png";
	}

}
