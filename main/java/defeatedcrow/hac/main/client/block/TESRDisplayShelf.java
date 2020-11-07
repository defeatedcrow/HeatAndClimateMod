package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.block.build.TileDisplayShelf;
import net.minecraft.client.Minecraft;
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

public class TESRDisplayShelf extends TileEntitySpecialRenderer<TileDisplayShelf> {

	@Override
	public void render(TileDisplayShelf te, double x, double y, double z, float partialTicks, int destroyStage,
			float a) {
		if (te != null) {

			int type = 0;
			int face = 0;
			float f = 0.0F;

			if (te.hasWorld()) {
				int meta = te.getBlockMetadata();

				type = meta & 3;
				face = 5 - (meta >> 2);
				if (face == 2) {
					f = 180F;
				}
				if (face == 3) {
					f = 0F;
				}
				if (face == 4) {
					f = 90F;
				}
				if (face == 5) {
					f = -90F;
				}
			}

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.25F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);

			if (!DCUtil.isEmpty(te.getStackInSlot(0))) {
				renderItem(te.getStackInSlot(0), -0.25F, -0.1875F, 0.125F, te);
			}
			if (!DCUtil.isEmpty(te.getStackInSlot(1))) {
				renderItem(te.getStackInSlot(1), 0F, -0.1875F, 0.35F, te);
			}
			if (!DCUtil.isEmpty(te.getStackInSlot(2))) {
				renderItem(te.getStackInSlot(2), 0.25F, -0.1875F, 0.125F, te);
			}

			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

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
				GlStateManager.translate(x, y + 0.25F, z);
				GlStateManager.scale(0.45F, 0.45F, 0.45F);
			} else {
				GlStateManager.translate(x, y, z);
				GlStateManager.scale(0.6F, 0.6F, 0.6F);
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
