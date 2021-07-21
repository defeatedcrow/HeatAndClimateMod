package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.client.model.ModelCurry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CurryRenderer extends DCRenderFoodBase<FoodEntityBase> {

	protected static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/bowl_metal.png");
	protected static final ModelCurry MODEL = new ModelCurry(false);

	public CurryRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(FoodEntityBase entity, double x, double y, double z, float yaw, float partialTicks) {
		// super.doRender(entity, x, y, z, yaw, partialTicks);

		int meta = 0;
		String name = "vegi";
		if (entity != null && !DCUtil.isEmpty(entity.getDropItem())) {
			ItemStack stack = entity.getDropItem().copy();
			meta = stack.getItemDamage();
			name = ((FoodItemBase) stack.getItem()).getNameSuffix()[meta];
		}
		CURRY type = getType(meta);

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.5F, (float) z);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.scale(-1F, -1F, 1F);

		float rotX = -entity.rotationPitch;
		float rotY = entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		this.bindTexture(TEX);
		MODEL.setRotationAngles(0, 0, 0, 0, 0, 0, entity);
		MODEL.render(0.0625F, entity);

		this.bindTexture(getTex(name));
		MODEL.renderSoup(0.0625F);

		if (type == CURRY.FISH) {
			// inner
			this.bindTexture(getTex(name));
			MODEL.renderFish(0.0625F);
		}

		if (type == CURRY.RICE) {
			// inner
			this.bindTexture(getTex(name));
			MODEL.renderRice(0.0625F);
		}

		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return TEX;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return MODEL;
	}

	protected ResourceLocation getTex(String type) {
		String s = "textures/entity/food/curry_" + type + ".png";
		return new ResourceLocation("dcs_climate", s);
	}

	protected CURRY getType(int meta) {
		switch (meta) {
		case 2:
			return CURRY.FISH;
		case 7:
			return CURRY.RICE;
		default:
			return CURRY.NORMAL;
		}
	}

	protected enum CURRY {
		NORMAL,
		SOUP,
		FISH,
		RICE;
	}
}
