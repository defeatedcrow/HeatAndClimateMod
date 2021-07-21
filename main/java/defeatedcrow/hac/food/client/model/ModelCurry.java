package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCurry extends DCFoodModelBase {

	private final ModelRenderer bowl;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer soup;
	private final ModelRenderer gu;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer rice;
	private final ModelRenderer cube_r8;
	private final ModelRenderer bb_main;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;

	public ModelCurry(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		bowl = new ModelRenderer(this);
		bowl.setRotationPoint(0.0F, 8.0F, 0.0F);
		bowl.cubeList.add(new ModelBox(bowl, 37, 3, -3.0F, -1.0F, -3.0F, 6, 1, 6, 0.0F, false));
		bowl.cubeList.add(new ModelBox(bowl, 0, 0, -6.0F, -2.0F, -6.0F, 12, 1, 12, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bowl.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.4363F, 1.5708F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 15, -6.5F, -6.0F, 4.0F, 13, 2, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bowl.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.4363F, 3.1416F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 15, -6.5F, -6.0F, 4.0F, 13, 2, 1, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bowl.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.4363F, -1.5708F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 15, -6.5F, -6.0F, 4.0F, 13, 2, 1, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		bowl.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.4363F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 15, -6.5F, -6.0F, 4.0F, 13, 2, 1, 0.0F, false));

		soup = new ModelRenderer(this);
		soup.setRotationPoint(0.0F, 8.0F, 0.0F);
		soup.cubeList.add(new ModelBox(soup, 0, 0, -6.0F, -3.0F, -6.0F, 12, 0, 12, 0.0F, false));

		gu = new ModelRenderer(this);
		gu.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotationAngle(gu, 0.0F, 0.7854F, 0.0F);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		gu.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.0873F, 0.0F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 50, 20, -2.0F, -4.0F, 2.0F, 3, 1, 2, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
		gu.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.0436F, 0.0F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 48, 23, -2.5F, -4.0F, -0.5F, 4, 1, 3, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 0.0F, 0.0F);
		gu.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0436F, 0.0F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 46, 27, -3.0F, -4.0F, -4.0F, 5, 1, 4, 0.0F, false));

		rice = new ModelRenderer(this);
		rice.setRotationPoint(0.0F, 8.0F, 0.0F);
		rice.cubeList.add(new ModelBox(rice, 0, 20, 0.0F, -3.5F, -5.0F, 6, 1, 10, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, 0.0F, 0.0F);
		rice.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, -0.1309F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 30, 21, -6.0F, -3.5F, -5.0F, 7, 1, 10, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.1309F, -2.0944F, 0.0F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 52, 8, -4.0F, -3.0F, 2.0F, 2, 1, 3, 0.0F, false));

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r10);
		setRotationAngle(cube_r10, -0.2618F, -2.3562F, 0.1745F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 52, 12, -1.0F, -3.5F, -1.0F, 2, 1, 3, 0.0F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.1309F, 1.5708F, 0.0F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 52, 4, -3.0F, -3.0F, 2.0F, 2, 1, 3, 0.0F, false));

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r12);
		setRotationAngle(cube_r12, -0.1745F, -0.7854F, 0.0F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, 52, 0, -1.0F, -4.0F, 2.5F, 2, 1, 3, 0.0F, false));

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r13);
		setRotationAngle(cube_r13, -0.1745F, 2.5307F, 0.0F);
		cube_r13.cubeList.add(new ModelBox(cube_r13, 38, 8, -3.0F, -4.0F, 2.0F, 3, 1, 3, 0.0F, false));

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r14);
		setRotationAngle(cube_r14, -0.0873F, -1.5708F, 0.0F);
		cube_r14.cubeList.add(new ModelBox(cube_r14, 38, 4, -3.0F, -3.5F, 1.5F, 3, 1, 3, 0.0F, false));

		cube_r15 = new ModelRenderer(this);
		cube_r15.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r15);
		setRotationAngle(cube_r15, -0.2618F, 0.5236F, 0.0F);
		cube_r15.cubeList.add(new ModelBox(cube_r15, 38, 0, -3.0F, -3.5F, -1.0F, 3, 1, 3, 0.0F, false));
	}

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
		bowl.render(scale);
	}

	public void renderSoup(float scale) {
		soup.render(scale);
		bb_main.render(scale);
	}

	public void renderFish(float scale) {
		gu.render(scale);
	}

	public void renderRice(float scale) {
		rice.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
		if (entity instanceof FoodEntityBase) {
			setIndividualRotation((FoodEntityBase) entity);
		}
	}

	public void setIndividualRotation(FoodEntityBase entity) {
		if (entity != null) {
			float f1 = -2.0944F;
			cube_r9.rotateAngleY = f1 + (entity.getIndividual() / 16F) * (float) (Math.PI);
			float f2 = -2.3562F;
			cube_r10.rotateAngleY = f2 + (entity.getIndividual() / 16F) * (float) (Math.PI);
			float f3 = 1.5708F;
			cube_r11.rotateAngleY = f3 + (entity.getIndividual() / 16F) * (float) (Math.PI);
			float f4 = -0.7854F;
			cube_r12.rotateAngleY = f4 + (entity.getIndividual() / 16F) * (float) (Math.PI);
			float f5 = 2.5307F;
			cube_r13.rotateAngleY = f5 + (entity.getIndividual() / 8F) * (float) (Math.PI);
			float f6 = -1.5708F;
			cube_r14.rotateAngleY = f6 + (entity.getIndividual() / 8F) * (float) (Math.PI);
			float f7 = 0.5236F;
			cube_r15.rotateAngleY = f7 + (entity.getIndividual() / 8F) * (float) (Math.PI);
		}
	}

}
