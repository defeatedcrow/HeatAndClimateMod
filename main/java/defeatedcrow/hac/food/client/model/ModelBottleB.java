package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBottleB extends ModelBottleA {

	private final ModelRenderer bb_main2;
	private final ModelRenderer bb_inner;

	public ModelBottleB(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		bb_main2 = new ModelRenderer(this);
		bb_main2.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main2.cubeList.add(new ModelBox(bb_main2, 0, 0, -2.5F, -10.0F, -2.5F, 5, 10, 5, 0.0F, false));
		bb_main2.cubeList.add(new ModelBox(bb_main2, 0, 16, -2.0F, -11.0F, -2.0F, 4, 1, 4, 0.0F, false));
		bb_main2.cubeList.add(new ModelBox(bb_main2, 0, 22, -1.5F, -12.0F, -1.5F, 3, 1, 3, 0.0F, false));
		bb_main2.cubeList.add(new ModelBox(bb_main2, 13, 22, -1.0F, -16.0F, -1.0F, 2, 4, 2, 0.0F, false));

		bb_inner = new ModelRenderer(this);
		bb_inner.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_inner.cubeList.add(new ModelBox(bb_main2, 32, 0, -2.0F, -9.0F, -2.0F, 4, 8, 4, 0.0F, false));
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
	public void renderGlass(float scale) {
		bb_main2.render(scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		bb_inner.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}

	@Override
	public float scale() {
		return 1.0F;
	}

	@Override
	public float offset() {
		return 0.5F;
	}

}
