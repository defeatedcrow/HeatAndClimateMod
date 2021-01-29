package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CookieRenderer extends DCRenderFoodBase<FoodEntityBase> {

	private static RenderItem itemRenderer;

	public CookieRenderer(RenderManager renderManager) {
		super(renderManager);
		itemRenderer = Minecraft.getMinecraft().getRenderItem();
	}

	@Override
	public void doRender(FoodEntityBase entity, double x, double y, double z, float yaw, float partialTicks) {
		float height = entity.height * 0.5F;
		EnumFacing side = entity.getSide();
		boolean baked = !entity.getRaw();

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.0625D, (float) z);

		this.renderItem(entity, baked, 0);
		GlStateManager.popMatrix();
	}

	private void renderItem(FoodEntityBase entity, boolean baked, int i) {
		ItemStack cookie = entity.getDropItem();

		if (!DCUtil.isEmpty(cookie)) {
			EntityItem drop = new EntityItem(entity.world, 0.0D, 0.0D, 0.0D, cookie);
			Item item = cookie.getItem();
			drop.getItem().setCount(1);
			drop.hoverStart = 0.0F;
			GlStateManager.pushMatrix();
			GlStateManager.disableLighting();

			GlStateManager.rotate(360 - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(90F, 1.0F, 0.0F, 0.0F);
			GlStateManager.scale(0.5F, 0.5F, 1.0F);

			GlStateManager.pushAttrib();
			RenderHelper.enableStandardItemLighting();
			this.itemRenderer.renderItem(drop.getItem(), ItemCameraTransforms.TransformType.FIXED);
			RenderHelper.disableStandardItemLighting();
			GlStateManager.popAttrib();

			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
		}
	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return null;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return null;
	}

}
