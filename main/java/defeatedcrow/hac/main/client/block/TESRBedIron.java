package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.block.build.TileBedDC;
import defeatedcrow.hac.main.client.model.ModelBedStandard;
import net.minecraft.block.BlockBed;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRBedIron extends TileEntitySpecialRenderer<TileBedDC> {

	private static final DCTileModelBase MODEL = new ModelBedStandard();

	@Override
	public void render(TileBedDC te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {

		float face = 0.0F;
		if (te.hasWorld()) {
			int meta = te.getBlockMetadata();
			if (BlockBed.isHeadPiece(meta)) {
				EnumFacing facing = EnumFacing.getHorizontal(meta);
				face = facing.getOpposite().getHorizontalAngle();

				this.bindTexture(new ResourceLocation(getTexPass(0)));

				GlStateManager.pushMatrix();
				GlStateManager.enableRescaleNormal();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
				GlStateManager.scale(1, -1, -1);

				GlStateManager.rotate(face, 0.0F, 1.0F, 0.0F);
				getModel(0).render(partialTicks, 0, 0);
				GlStateManager.disableRescaleNormal();
				GlStateManager.popMatrix();
			}

		}
	}

	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/bed_iron.png";
	}

	protected DCTileModelBase getModel(int i) {
		return MODEL;
	}
}
