package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelChickenRice extends DCFoodModelBase {

	private final ModelRenderer bowl;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer rice;
	private final ModelRenderer meat;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer tomato_r1;
	private final ModelRenderer meat4_r1;
	private final ModelRenderer meat3_r1;
	private final ModelRenderer meat2_r1;
	private final ModelRenderer meat1_r1;

	public ModelChickenRice(boolean baked) {
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

		rice = new ModelRenderer(this);
		rice.setRotationPoint(0.0F, 8.0F, 0.0F);
		rice.cubeList.add(new ModelBox(rice, 0, 19, -5.0F, -4.0F, -1.0F, 8, 2, 6, 0.0F, false));

		meat = new ModelRenderer(this);
		meat.setRotationPoint(0.0F, 8.0F, 0.0F);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(5.0F, -2.0F, 0.0F);
		meat.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.4363F, -0.3491F, 0.8727F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 30, 23, -2.0F, -3.0F, -1.0F, 1, 4, 2, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(5.0F, -2.0F, 0.0F);
		meat.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.7854F, -0.4363F, 0.9599F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 30, 23, -1.0F, -4.0F, 0.0F, 1, 4, 2, 0.0F, false));

		tomato_r1 = new ModelRenderer(this);
		tomato_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		meat.addChild(tomato_r1);
		setRotationAngle(tomato_r1, 0.0F, -0.2182F, 0.0F);
		tomato_r1.cubeList.add(new ModelBox(tomato_r1, 37, 25, 4.0F, -4.0F, 2.0F, 2, 2, 2, 0.0F, false));

		meat4_r1 = new ModelRenderer(this);
		meat4_r1.setRotationPoint(-2.0F, -9.0F, 10.0F);
		meat.addChild(meat4_r1);
		setRotationAngle(meat4_r1, 0.3491F, -0.3491F, 0.3491F);
		meat4_r1.cubeList.add(new ModelBox(meat4_r1, 47, 23, 1.0F, -2.0F, -17.5F, 2, 1, 6, 0.0F, false));

		meat3_r1 = new ModelRenderer(this);
		meat3_r1.setRotationPoint(-2.0F, -9.0F, 10.0F);
		meat.addChild(meat3_r1);
		setRotationAngle(meat3_r1, 0.3927F, -0.2618F, 0.3491F);
		meat3_r1.cubeList.add(new ModelBox(meat3_r1, 30, 15, 0.0F, -1.5F, -17.3F, 2, 1, 6, 0.0F, false));

		meat2_r1 = new ModelRenderer(this);
		meat2_r1.setRotationPoint(-2.0F, -9.0F, 10.0F);
		meat.addChild(meat2_r1);
		setRotationAngle(meat2_r1, 0.4363F, -0.0873F, 0.3491F);
		meat2_r1.cubeList.add(new ModelBox(meat2_r1, 30, 15, 0.0F, -1.0F, -17.0F, 2, 1, 6, 0.0F, false));

		meat1_r1 = new ModelRenderer(this);
		meat1_r1.setRotationPoint(-2.0F, -9.0F, 10.0F);
		meat.addChild(meat1_r1);
		setRotationAngle(meat1_r1, 0.5236F, 0.0873F, 0.3491F);
		meat1_r1.cubeList.add(new ModelBox(meat1_r1, 47, 15, 0.0F, -1.5F, -17.0F, 2, 1, 6, 0.0F, false));
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
		rice.render(scale);
		meat.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
	}

}
