package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.food.client.model.ModelBlockAgingBarrel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRAgingBarrel extends DCLockableTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/barrel_aging.png";
	private static final ModelBlockAgingBarrel MODEL = new ModelBlockAgingBarrel();

	@Override
	public void render(DCLockableTE te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		int type = 0;
		int face = 0;
		float f = 0.0F;
		float oX = 0F;
		float oZ = 0F;

		if (te.hasWorld()) {
			int meta = te.getBlockMetadata();

			type = meta & 3;
			face = 5 - (meta >> 2);
			if (face == 2) {
				f = 0F;
				oZ = 1F;
			}
			if (face == 3) {
				f = 180F;
				oZ = -1F;
			}
			if (face == 4) {
				f = -90F;
				oX = 1F;
			}
			if (face == 5) {
				f = 90F;
				oX = -1F;
			}
		}

		DCTileModelBase model = this.getModel(type);

		this.bindTexture(new ResourceLocation(getTexPass(type)));

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x + 0.5F + oX, (float) y + 0.5F, (float) z + 0.5F + oZ);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(90F, 1.0F, 0.0F, 0.0F);

		this.render(model, 0.0F);
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
