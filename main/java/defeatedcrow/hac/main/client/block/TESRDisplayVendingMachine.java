package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.IColorChangeTile;
import defeatedcrow.hac.main.block.build.TileDisplayVendingMachine;
import defeatedcrow.hac.main.client.model.ModelVendingMachine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TESRDisplayVendingMachine extends TileEntitySpecialRenderer<TileDisplayVendingMachine> {

	private static final String TEX1 = "dcs_climate:textures/tiles/vending_machine_red.png";
	private static final String TEX2 = "dcs_climate:textures/tiles/vending_machine_blue.png";
	private static final String TEX3 = "dcs_climate:textures/tiles/vending_machine_yellow.png";
	private static final String TEX4 = "dcs_climate:textures/tiles/vending_machine_white.png";
	private final ModelVendingMachine model = new ModelVendingMachine();

	@Override
	public void render(TileDisplayVendingMachine te, double x, double y, double z, float partialTicks, int destroyStage,
			float a) {
		if (te != null) {

			int type = 0;
			int face = 0;
			float f = 0.0F;

			if (te.hasWorld()) {
				int meta = te.getBlockMetadata();
				IBlockState block = te.getWorld().getBlockState(te.getPos());

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

			int color = 0;
			if (te instanceof IColorChangeTile)
				color = ((IColorChangeTile) te).getColor();

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.55F, (float) z + 0.5F);
			GlStateManager.scale(0.975F, -0.975F, -0.975F);
			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);

			this.bindTexture(new ResourceLocation(getTexPass(color)));
			model.render(0.0F, 0.0F, 0.0F);

			GlStateManager.disableLighting();
			int i = 15728880;
			int j = i % 65536;
			int k = i / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);

			model.buttonA();
			model.buttonB();
			model.buttonC();
			model.buttonD();
			model.light();

			GlStateManager.enableLighting();
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);

			if (!DCUtil.isEmpty(te.getStackInSlot(0))) {
				renderItem(te.getStackInSlot(0).copy(), -0.2F, -0.175F, 0.125F, te);
			}

			if (!DCUtil.isEmpty(te.getStackInSlot(1))) {
				renderItem(te.getStackInSlot(1).copy(), 0.2F, -0.175F, 0.125F, te);
			}

			if (!DCUtil.isEmpty(te.getStackInSlot(2))) {
				renderItem(te.getStackInSlot(2).copy(), -0.2F, 0.25F, 0.125F, te);
			}

			if (!DCUtil.isEmpty(te.getStackInSlot(3))) {
				renderItem(te.getStackInSlot(3).copy(), 0.2F, 0.25F, 0.125F, te);
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
				GlStateManager.translate(x, y, z);
				GlStateManager.scale(0.3F, 0.3F, 0.3F);
			} else {
				GlStateManager.translate(x, y, z);
				GlStateManager.scale(0.25F, 0.25F, 0.25F);
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

	protected String getTexPass(int i) {
		switch (i) {
		case 0:
			return TEX1;
		case 1:
			return TEX2;
		case 2:
			return TEX3;
		case 3:
			return TEX4;
		}
		return TEX1;
	}
}
