package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import defeatedcrow.hac.food.client.model.ModelBottleA;
import defeatedcrow.hac.food.client.model.ModelBottleB;
import defeatedcrow.hac.food.client.model.ModelBottleC;
import defeatedcrow.hac.food.client.model.ModelBottleD;
import defeatedcrow.hac.food.client.model.ModelBottleE;
import defeatedcrow.hac.food.client.model.ModelBottleF;
import defeatedcrow.hac.food.client.model.ModelBottleJ;
import defeatedcrow.hac.food.entity.LiquorBottleEntity;
import defeatedcrow.hac.food.item.brewing.ItemLiquorBottle;
import defeatedcrow.hac.main.config.MainCoreConfig;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BottleLiquorRenderer extends DCRenderFoodBase<LiquorBottleEntity> {

	protected static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/food/bottle_empty.png");
	protected static final ModelBottleA MODEL_A = new ModelBottleA(false);
	protected static final ModelBottleA MODEL_B = new ModelBottleB(false);
	protected static final ModelBottleA MODEL_C = new ModelBottleC(false);
	protected static final ModelBottleA MODEL_D = new ModelBottleD(false);
	protected static final ModelBottleA MODEL_E = new ModelBottleE(false);
	protected static final ModelBottleA MODEL_F = new ModelBottleF(false);
	protected static final ModelBottleA MODEL_J = new ModelBottleJ(false);

	public BottleLiquorRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(LiquorBottleEntity entity, double x, double y, double z, float yaw, float partialTicks) {
		// super.doRender(entity, x, y, z, yaw, partialTicks);

		int meta = entity.getMeta();
		boolean[] b = getBool(meta);
		ModelBottleA model = getModel(meta);
		float s = model.scale();
		float oy = model.offset();
		float f = 0F;
		if (model == MODEL_C || model == MODEL_D || model == MODEL_E || model == MODEL_F) {
			f = -90.0F;
		}

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + oy, (float) z);
		GlStateManager.enableBlend();
		GlStateManager
				.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.scale(-s, -s, s);

		float rotX = -entity.rotationPitch;
		float rotY = f + entity.rotationYaw;
		float rotZ = 0F;

		GlStateManager.rotate(rotY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotZ, 0.0F, 0.0F, 1.0F);

		if (b[0]) {
			// layer
			this.bindTexture(getTex(meta, true));
			model.renderGlass(0.0625F);
		}

		GlStateManager.scale(0.99F, 0.99F, 0.99F);

		if (b[1]) {
			// inner
			this.bindTexture(getTex(meta, false));
			model.render(0.0625F, entity);
		}

		if (b[2]) {
			// glass
			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
			this.bindTexture(getTex(meta, false));
			model.renderGlass(0.0625F);
		}

		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getFoodTexture(boolean baked) {
		return TEX;
	}

	@Override
	protected DCFoodModelBase getEntityModel(boolean baked) {
		return MODEL_A;
	}

	protected ResourceLocation getTex(int meta, boolean isLayer) {
		String name = ItemLiquorBottle.getTypeName(meta);
		String s = "textures/entity/food/liquor/" + "" + name;
		if (MainCoreConfig.bottle_texture) {
			s = "textures/entity/food/liquor_fixed/" + "" + name;
		}
		if (isLayer) {
			s += "_layer.png";
		} else {

			s += "_glass.png";
		}
		return new ResourceLocation("dcs_climate", s);
	}

	protected ModelBottleA getModel(int meta) {
		switch (meta) {
		case 6:
			return MODEL_C; // brandy
		case 10:
		case 11:
			return MODEL_D; // rum
		case 14:
			return MODEL_F; // nether
		case 15:
			return MODEL_E; // ender
		case 2:
		case 5:
		case 7:
		case 9:
		case 12:
		case 13:
		case 16:
		case 17:
			return MODEL_B;
		default:
			return MODEL_A;
		}
	}

	protected boolean[] getBool(int meta) {
		switch (meta) {
		case 6:
		case 10:
		case 11:
		case 14:
		case 15:
		case 5:
		case 7:
		case 9:
		case 12:
		case 13:
		case 16:
			return new boolean[] {
					true,
					true,
					true
			};
		default:
			return new boolean[] {
					true,
					false,
					false
			};
		}
	}
}
