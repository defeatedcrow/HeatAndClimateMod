package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.client.base.DCTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.machine.block.TileMonitorBase;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MonitorTESR extends DCTESRBase {

	@Override
	protected DCTileModelBase getModel(int i) {
		return null;
	}

	@Override
	public void render(DCTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		float x1 = 0F;
		float y1 = 0F;
		float z1 = 0F;
		if (te.hasWorld() && te instanceof TileMonitorBase) {
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

			TileMonitorBase tile = (TileMonitorBase) te;
			amo = tile.getCurrentAmount();
			if (amo > 1000000000F) {
				amo /= 1000000000F;
				order = 3;
			} else if (amo > 1000000F) {
				amo /= 1000000F;
				order = 2;
			} else if (amo > 1000F) {
				amo /= 1000F;
				order = 1;
			}

			String sa = tile.amountString();
			String so = "";
			switch (order) {
			case 1:
				so = "k";
				break;
			case 2:
				so = "M";
				break;
			case 3:
				so = "G";
				break;
			default:
				so = "";
			}
			String su = tile.unit();

			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
			GlStateManager.rotate(x1, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(z1, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(y1, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(0.0F, 0.125F, -0.437F);

			FontRenderer font = this.getFontRenderer();
			GlStateManager.scale(0.02F, -0.02F, 0.02F);
			GlStateManager.glNormal3f(0.0F, 0.0F, -0.015F);
			GlStateManager.depthMask(false);

			String s = TextFormatting.BOLD.toString() + sa;
			font.drawString(s, -font.getStringWidth(s) / 2, 0, 0xFFFFFF);
			String s2 = TextFormatting.BOLD.toString() + so + su;
			font.drawString(s2, -font.getStringWidth(s2) / 2, 8, 0xFFFFFF);

			GlStateManager.depthMask(true);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.popMatrix();
		}
	}
}
