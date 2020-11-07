package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.machine.block.TileMonitorTemp;
import defeatedcrow.hac.machine.client.model.ModelMonitorTemp;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MonitorTempTESR extends DCTESRBase {

	private static final DCTileModelBase MODEL = new ModelMonitorTemp();

	@Override
	protected DCTileModelBase getModel(int i) {
		return null;
	}

	@Override
	public void render(DCTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		float x1 = 0F;
		float y1 = 0F;
		float z1 = 0F;
		if (te.hasWorld() && te instanceof TileMonitorTemp) {
			int meta = te.getBlockMetadata() & 7;
			EnumSide face = EnumSide.fromIndex(meta);

			switch (face) {
			case DOWN:
				z1 = 180F;
				x1 = -90F;
				break;
			case UP:
				z1 = 180F;
				x1 = 90F;
				break;
			case NORTH:
				y1 = 0F;
				break;
			case SOUTH:
				y1 = 180F;
				break;
			case EAST:
				y1 = -90F;
				break;
			case WEST:
				y1 = 90F;
				break;
			default:
				break;
			}

			float amo = 0;
			int order = 0;

			TileMonitorTemp tile = (TileMonitorTemp) te;
			amo = tile.getCurrentAmount();

			String sa = tile.amountString(amo, order);
			String sm = tile.amount > 0 ? sa.toLowerCase() : "base";
			String tex = "dcs_climate:textures/gui/icon_" + sm + ".png";
			int color = tile.amount > 0 ? (int) tile.amountMax : 0xFFFFFF;

			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
			GlStateManager.rotate(x1, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(z1, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(y1, 0.0F, 1.0F, 0.0F);

			this.bindTexture(new ResourceLocation(tex));
			MODEL.render(0.0625F);

			GlStateManager.translate(0.0F, 0.1875F, -0.437F);

			FontRenderer font = this.getFontRenderer();
			GlStateManager.scale(0.015F, -0.015F, 0.015F);
			GlStateManager.glNormal3f(0.0F, 0.0F, -0.015F);
			GlStateManager.depthMask(false);

			String s = TextFormatting.BOLD.toString() + sa;
			font.drawString(s, -font.getStringWidth(s) / 2, 4, color);

			GlStateManager.depthMask(true);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.popMatrix();
		}
	}
}
