package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBottleC extends ModelBottleA {

	private final ModelRenderer inner;
	private final ModelRenderer bb_main;

	public ModelBottleC(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		inner = new ModelRenderer(this);
		inner.setRotationPoint(0.0F, 8.0F, 0.0F);
		inner.cubeList.add(new ModelBox(inner, 33, 0, -2.0F, -7.0F, -4.5F, 4, 6, 9, 0.0F, false));
		inner.cubeList.add(new ModelBox(inner, 33, 16, -2.0F, -8.0F, -2.0F, 4, 1, 4, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.5F, -8.0F, -5.5F, 5, 8, 11, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 20, -2.0F, -9.0F, -2.5F, 4, 1, 5, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 19, 24, -2.0F, -13.0F, -2.0F, 4, 3, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 27, -1.5F, -10.0F, -1.5F, 3, 1, 3, 0.0F, false));
	}

	@Override
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		inner.render(scale);
	}

	@Override
	public void renderGlass(float scale) {
		bb_main.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}

	@Override
	public float scale() {
		return 0.85F;
	}

	@Override
	public float offset() {
		return 0.45F;
	}

}
