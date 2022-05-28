package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelErgonomicChair extends DCFoodModelBase {

	private final ModelRenderer main;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;

	public ModelErgonomicChair(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 28, 20, -1.0F, -3.0F, -1.0F, 2, 2, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 23, 20, -0.5F, -6.0F, -0.5F, 1, 3, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 9, 12, 4.5F, -10.0F, -1.0F, 1, 3, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 9, 12, -5.5F, -10.0F, -1.0F, 1, 3, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 16, 4.0F, -11.0F, -3.0F, 2, 1, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 16, -6.0F, -11.0F, -3.0F, 2, 1, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 25, -3.0F, -15.2F, 4.7F, 6, 1, 1, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -5.0F, 0.0F);
		main.addChild(cube_r1);
		setRotation(cube_r1, -0.0873F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -4.0F, -12.0F, 3.0F, 8, 5, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 19, 2, -4.0F, -2.5F, -6.0F, 8, 1, 8, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, -5.0F, 0.0F);
		main.addChild(cube_r2);
		setRotation(cube_r2, -0.1745F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 7, -4.0F, -7.0F, 2.0F, 8, 3, 1, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 15, 18, -3.0F, -10.0F, 3.0F, 1, 8, 1, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 15, 18, 2.0F, -10.0F, 3.0F, 1, 8, 1, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 22, -3.0F, -2.0F, 3.0F, 6, 1, 1, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, -8.0F, 0.0F);
		main.addChild(cube_r3);
		setRotation(cube_r3, -0.1745F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 23, 12, -3.0F, 2.0F, -5.0F, 6, 1, 6, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, -5.0F, 0.0F);
		main.addChild(cube_r4);
		setRotation(cube_r4, 0.1309F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 16, 12, -1.0F, -1.0F, 0.0F, 2, 1, 4, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, -5.0F, 0.0F);
		main.addChild(cube_r5);
		setRotation(cube_r5, 0.0F, 0.0F, 0.5236F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 13, -5.5F, 0.0F, -1.0F, 3, 1, 1, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, -5.0F, 0.0F);
		main.addChild(cube_r6);
		setRotation(cube_r6, 0.0F, 0.0F, -0.5236F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 13, 2.5F, 0.0F, -1.0F, 3, 1, 1, 0.0F, false));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(leg1, 0.0F, -0.7854F, 0.0F);
		leg1.cubeList.add(new ModelBox(leg1, 37, 20, -5.0F, -2.0F, -0.5F, 4, 1, 1, 0.0F, false));
		leg1.cubeList.add(new ModelBox(leg1, 37, 23, -4.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(leg2, 0.0F, 0.7854F, 0.0F);
		leg2.cubeList.add(new ModelBox(leg2, 37, 20, -5.0F, -2.0F, -0.5F, 4, 1, 1, 0.0F, false));
		leg2.cubeList.add(new ModelBox(leg2, 37, 23, -4.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(leg3, 0.0F, -2.3562F, 0.0F);
		leg3.cubeList.add(new ModelBox(leg3, 37, 20, -5.0F, -2.0F, -0.5F, 4, 1, 1, 0.0F, false));
		leg3.cubeList.add(new ModelBox(leg3, 37, 23, -4.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F, false));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(leg4, 0.0F, 2.3562F, 0.0F);
		leg4.cubeList.add(new ModelBox(leg4, 37, 20, -5.0F, -2.0F, -0.5F, 4, 1, 1, 0.0F, false));
		leg4.cubeList.add(new ModelBox(leg4, 37, 23, -4.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		main.render(scale);
		leg1.render(scale);
		leg2.render(scale);
		leg3.render(scale);
		leg4.render(scale);
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
