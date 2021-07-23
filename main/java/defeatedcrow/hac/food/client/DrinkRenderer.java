package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelDrinkGinger;
import defeatedcrow.hac.food.client.model.ModelDrinkKuzu;
import defeatedcrow.hac.food.client.model.ModelDrinkTomato;
import defeatedcrow.hac.food.entity.DrinkEntity;
import defeatedcrow.hac.food.item.DrinkItem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DrinkRenderer extends DCRenderFoodBase<DrinkEntity> {

	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/drink_ginger.png");
	private static final ModelDrinkGinger MODEL1 = new ModelDrinkGinger(false);
	private static final ModelDrinkKuzu MODEL2 = new ModelDrinkKuzu(false);
	private static final ModelDrinkTomato MODEL3 = new ModelDrinkTomato(false);

	public DrinkRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(DrinkEntity entity, double x, double y, double z, float yaw, float partialTicks) {
		// super.doRender(entity, x, y, z, yaw, partialTicks);

		int meta = entity.getMeta();
		DCFoodModelBase model = getModel(meta);

		this.bindTexture(getTex(meta));

		float rotX = -entity.rotationPitch;
		float rotY = 180F - entity.rotationYaw;
		float rotZ = 0F;

		if (model == MODEL1) {

			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x, (float) y, (float) z);
			GlStateManager.scale(-0.5F, -0.5F, 0.5F);

			GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

			MODEL1.render(0.0625F, entity);

			GlStateManager.popMatrix();

			if (meta == 6 || meta == 8) {

				GlStateManager.pushMatrix();
				GlStateManager.translate((float) x, (float) y, (float) z);
				GlStateManager.scale(-0.5F, -0.5F, 0.5F);

				GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

				MODEL1.renderSoup(0.0625F);

				GlStateManager.popMatrix();

			} else {

				GlStateManager.pushMatrix();
				GlStateManager.translate((float) x, (float) y, (float) z);
				GlStateManager.enableBlend();
				GlStateManager
						.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
				GlStateManager.scale(-0.5F, -0.5F, 0.5F);

				GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

				GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);

				MODEL1.renderSoup(0.0625F);

				GlStateManager.disableBlend();
				GlStateManager.popMatrix();

			}

			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x, (float) y, (float) z);
			GlStateManager.enableBlend();
			GlStateManager
					.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			GlStateManager.scale(-0.5F, -0.5F, 0.5F);

			GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);

			MODEL1.renderGlass(0.0625F);

			GlStateManager.disableBlend();
			GlStateManager.popMatrix();

		} else if (model == MODEL2) {

			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x, (float) y + 0.125F, (float) z);
			GlStateManager.scale(-0.5F, -0.5F, 0.5F);

			GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

			MODEL2.render(0.0625F, entity);
			MODEL2.renderSoup(0.0625F);

			GlStateManager.popMatrix();

		} else if (model == MODEL3) {

			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x, (float) y + 0.125F, (float) z);
			GlStateManager.scale(-0.75F, -0.75F, 0.75F);

			GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

			MODEL3.render(0.0625F, entity);

			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x, (float) y + 0.125F, (float) z);
			GlStateManager.enableBlend();
			GlStateManager
					.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			GlStateManager.scale(-0.75F, -0.75F, 0.75F);

			GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
			MODEL3.renderGlass(0.0625F);

			GlStateManager.disableBlend();
			GlStateManager.popMatrix();

		}

	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return TEX;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return MODEL1;
	}

	protected int meta() {
		return 0;
	}

	protected ResourceLocation getTex(int meta) {
		String name = DrinkItem.getTypeName(meta);
		String s = "textures/entity/food/drink_" + name + ".png";
		return new ResourceLocation("dcs_climate", s);
	}

	protected DCFoodModelBase getModel(int meta) {
		switch (meta) {
		case 1:
		case 5:
		case 7:
			return MODEL2; // kuzu
		case 2:
			return MODEL3; // tomato
		default:
			return MODEL1;
		}
	}
}
