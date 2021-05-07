package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.block.build.TileDisplayStand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;

public class TESRDisplayStand extends TileEntitySpecialRenderer<TileDisplayStand> {

	@Override
	public void render(TileDisplayStand te, double x, double y, double z, float partialTicks, int destroyStage,
			float a) {
		if (te != null) {

			float x1 = -90F;
			float y1 = 0F;
			float z1 = 0F;

			int type = 0;
			int face = 0;
			float f = 0.0F;
			ItemStack in = ItemStack.EMPTY;

			if (te.hasWorld()) {
				int meta = te.getBlockMetadata();

				type = meta & 3;
				face = 5 - (meta >> 2);
				if (face == 2) {
					f = 180F;
					y1 = 180F;
				}
				if (face == 3) {
					f = 0F;
					y1 = 0F;
				}
				if (face == 4) {
					f = 90F;
					y1 = -90F;
				}
				if (face == 5) {
					f = -90F;
					y1 = 90F;
				}

			}

			if (!DCUtil.isEmpty(te.getStackInSlot(0))) {
				in = te.getStackInSlot(0);

				GlStateManager.pushMatrix();
				GlStateManager.enableRescaleNormal();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.translate((float) x + 0.5F, (float) y + 0.4F, (float) z + 0.5F);
				GlStateManager.scale(1.0F, -1.0F, -1.0F);
				GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);

				renderItem(in, 0F, 0F, 0.125F, te);

				GlStateManager.disableRescaleNormal();
				GlStateManager.popMatrix();

				GlStateManager.pushMatrix();
				GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
				GlStateManager.rotate(y1, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(x1, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(z1, 0.0F, 0.0F, 1.0F);
				GlStateManager.translate(0.0F, -0.225F, -0.35F);

				FontRenderer font = this.getFontRenderer();
				GlStateManager.scale(0.005F, -0.0075F, 0.0075F);
				GlStateManager.glNormal3f(0.0F, 0.0F, -0.015F);
				GlStateManager.depthMask(false);

				String s = TextFormatting.BOLD.toString() + in.getDisplayName();
				font.drawString(s, -font.getStringWidth(s) / 2, 0, 0x000000);
				String s2 = TextFormatting.BOLD.toString() + te.getDate();
				font.drawString(s2, -font.getStringWidth(s2) / 2, 10, 0x000000);

				GlStateManager.depthMask(true);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.popMatrix();

			}
		}
	}

	protected void renderItem(ItemStack stack, float x, float y, float z, TileEntity te) {
		RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();

		if (!DCUtil.isEmpty(stack)) {
			stack.setCount(1);
			EntityItem drop = new EntityItem(te.getWorld(), 0.0D, 0.0D, 0.0D, stack);
			Item item = stack.getItem();

			drop.hoverStart = 0.0F;
			GlStateManager.pushMatrix();
			GlStateManager.disableLighting();
			if (item instanceof ItemBlock) {
				GlStateManager.translate(x, y + 0.125F, z);
				GlStateManager.scale(0.75F, 0.75F, 0.75F);
			} else {
				GlStateManager.translate(x, y, z);
				GlStateManager.scale(0.75F, 0.75F, 0.75F);
			}

			GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);

			GlStateManager.pushAttrib();
			RenderHelper.enableStandardItemLighting();
			itemRenderer.renderItem(drop.getItem(), ItemCameraTransforms.TransformType.FIXED);
			RenderHelper.disableStandardItemLighting();
			GlStateManager.popAttrib();

			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
		}
	}
}
