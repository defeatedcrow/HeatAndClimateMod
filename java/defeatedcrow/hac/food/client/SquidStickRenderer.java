package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.client.model.ModelStickBase;
import defeatedcrow.hac.food.entity.SquidStickEntity;
import defeatedcrow.hac.main.MainInit;
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
public class SquidStickRenderer extends DCRenderFoodBase<SquidStickEntity> {

	private static final ResourceLocation RAW_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/stick_base.png");
	private static final ResourceLocation BAKED_TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/stick_base.png");
	private static final ModelStickBase RAW_MODEL = new ModelStickBase(false);
	private static final ModelStickBase BAKED_MODEL = new ModelStickBase(true);

	private static RenderItem itemRenderer;
	private static final ItemStack RAW_ITEM = new ItemStack(FoodInit.meat, 1, 2);
	private static final ItemStack COOKED_ITEM = new ItemStack(MainInit.bakedApple, 1, 4);

	public SquidStickRenderer(RenderManager renderManager) {
		super(renderManager);
		itemRenderer = Minecraft.getMinecraft().getRenderItem();
	}

	@Override
	public void doRender(SquidStickEntity entity, double x, double y, double z, float yaw, float partialTicks) {
		float height = entity.height * 0.5F;
		EnumFacing side = entity.getSide();
		boolean baked = !entity.getRaw();

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.65D, (float) z);

		this.renderItem(entity, baked, 0);
		this.renderItem(entity, baked, 1);
		GlStateManager.popMatrix();

		super.doRender(entity, x, y, z, yaw, partialTicks);
	}

	private void renderItem(SquidStickEntity entity, boolean baked, int i) {
		ItemStack fish = baked ? COOKED_ITEM : RAW_ITEM;

		if (!DCUtil.isEmpty(fish)) {
			EntityItem drop = new EntityItem(entity.worldObj, 0.0D, 0.0D, 0.0D, fish);
			Item item = drop.getEntityItem().getItem();
			drop.getEntityItem().stackSize = 1;
			drop.hoverStart = 0.0F;
			GlStateManager.pushMatrix();
			GlStateManager.disableLighting();

			GlStateManager.scale(0.8F, 0.8F, 0.8F);
			GlStateManager.rotate(360F - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(-45F, 0.0F, 0.0F, 1.0F);
			GlStateManager.translate(0F, 0F, i * 0.042F - 0.021F);

			GlStateManager.pushAttrib();
			RenderHelper.enableStandardItemLighting();
			this.itemRenderer.renderItem(drop.getEntityItem(), ItemCameraTransforms.TransformType.FIXED);
			RenderHelper.disableStandardItemLighting();
			GlStateManager.popAttrib();

			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
		}
	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return baked ? BAKED_TEX : RAW_TEX;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return baked ? BAKED_MODEL : RAW_MODEL;
	}

}
