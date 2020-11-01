package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelYogurt extends DCFoodModelBase {

	private final ModelRenderer glass;
	private final ModelRenderer yogurt;

	public ModelYogurt(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		glass = new ModelRenderer(this);
		glass.setRotationPoint(0.0F, 3.0F, 0.0F);
		glass.cubeList.add(new ModelBox(glass, 0, 0, -3.0F, -1.0F, -3.0F, 6, 1, 6, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 10, 16, -1.0F, -3.0F, -1.0F, 2, 2, 2, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 0, 0, -3.0F, -4.0F, -3.0F, 6, 1, 6, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 0, 9, -4.0F, -8.0F, -4.0F, 8, 5, 1, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 0, 9, -4.0F, -8.0F, 3.0F, 8, 5, 1, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 0, 16, 3.0F, -8.0F, -3.0F, 1, 5, 6, 0.0F, false));
		glass.cubeList.add(new ModelBox(glass, 0, 16, -4.0F, -8.0F, -3.0F, 1, 5, 6, 0.0F, false));

		yogurt = new ModelRenderer(this);
		yogurt.setRotationPoint(0.0F, 3.0F, 0.0F);
		yogurt.cubeList.add(new ModelBox(yogurt, 32, 0, -3.0F, -7.0F, -3.0F, 6, 3, 6, 0.0F, false));
		yogurt.cubeList.add(new ModelBox(yogurt, 32, 12, -1.0F, -8.0F, 0.0F, 1, 1, 1, 0.0F, false));
		yogurt.cubeList.add(new ModelBox(yogurt, 32, 12, 0.0F, -8.0F, -1.0F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		yogurt.render(0.0625F);
	}

	public void renderGlass() {
		glass.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}

}
