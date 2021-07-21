package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCutlery extends DCFoodModelBase {

	private final ModelRenderer chopsticks;
	private final ModelRenderer cube2_r1;
	private final ModelRenderer cutlery;
	private final ModelRenderer cube6_r1;
	private final ModelRenderer cube5_r1;
	private final ModelRenderer spoon;
	private final ModelRenderer spoon_r1;
	private final ModelRenderer fork;
	private final ModelRenderer fork2_r1;
	private final ModelRenderer fork1_r1;

	public ModelCutlery(boolean baked) {
		super(baked);

		textureWidth = 32;
		textureHeight = 16;

		chopsticks = new ModelRenderer(this);
		chopsticks.setRotationPoint(0.0F, 8.0F, 0.0F);
		chopsticks.cubeList.add(new ModelBox(chopsticks, 0, 3, -6.0F, -1.0F, -2.0F, 1, 1, 4, 0.0F, false));

		cube2_r1 = new ModelRenderer(this);
		cube2_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		chopsticks.addChild(cube2_r1);
		setRotationAngle(cube2_r1, 0.0F, 0.0F, 0.0873F);
		cube2_r1.cubeList.add(new ModelBox(cube2_r1, 0, 0, -7.0F, -1.5F, -1.25F, 15, 1, 1, 0.0F, false));
		cube2_r1.cubeList.add(new ModelBox(cube2_r1, 0, 0, -7.0F, -1.5F, 0.25F, 15, 1, 1, 0.0F, false));

		cutlery = new ModelRenderer(this);
		cutlery.setRotationPoint(0.0F, 8.0F, -1.0F);
		cutlery.cubeList.add(new ModelBox(cutlery, 0, 0, -3.0F, -1.0F, 2.5F, 5, 1, 1, 0.0F, false));

		cube6_r1 = new ModelRenderer(this);
		cube6_r1.setRotationPoint(0.0F, -1.0F, 0.0F);
		cutlery.addChild(cube6_r1);
		setRotationAngle(cube6_r1, -0.1745F, 0.0F, 0.0F);
		cube6_r1.cubeList.add(new ModelBox(cube6_r1, 2, 8, -1.0F, -1.5F, 2.5F, 1, 1, 2, 0.0F, false));

		cube5_r1 = new ModelRenderer(this);
		cube5_r1.setRotationPoint(0.0F, -1.0F, 0.0F);
		cutlery.addChild(cube5_r1);
		setRotationAngle(cube5_r1, 0.1745F, 0.0F, 0.0F);
		cube5_r1.cubeList.add(new ModelBox(cube5_r1, 0, 3, -1.0F, -0.5F, -6.0F, 1, 1, 9, 0.0F, false));

		spoon = new ModelRenderer(this);
		spoon.setRotationPoint(0.0F, 8.0F, 0.0F);

		spoon_r1 = new ModelRenderer(this);
		spoon_r1.setRotationPoint(0.0F, -1.0F, -1.0F);
		spoon.addChild(spoon_r1);
		setRotationAngle(spoon_r1, 0.1745F, 0.0F, 0.0F);
		spoon_r1.cubeList.add(new ModelBox(spoon_r1, 18, 3, -2.0F, 0.0F, 4.5F, 3, 1, 4, 0.0F, false));

		fork = new ModelRenderer(this);
		fork.setRotationPoint(0.0F, 8.0F, 0.0F);

		fork2_r1 = new ModelRenderer(this);
		fork2_r1.setRotationPoint(0.0F, -1.0F, -1.0F);
		fork.addChild(fork2_r1);
		setRotationAngle(fork2_r1, 0.2182F, 0.0F, 0.0F);
		fork2_r1.cubeList.add(new ModelBox(fork2_r1, 20, 9, -2.0F, 0.5F, 6.5F, 3, 0, 3, 0.0F, false));

		fork1_r1 = new ModelRenderer(this);
		fork1_r1.setRotationPoint(0.0F, -1.0F, -1.0F);
		fork.addChild(fork1_r1);
		setRotationAngle(fork1_r1, 0.1745F, 0.0F, 0.0F);
		fork1_r1.cubeList.add(new ModelBox(fork1_r1, 21, 13, -2.0F, 0.0F, 4.5F, 3, 1, 2, 0.0F, false));
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	public void setIndividualRotation(FoodEntityBase entity) {}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		chopsticks.render(scale);
	}

	public void renderCutlery(float scale) {
		cutlery.render(scale);
	}

	public void renderSpoon(float scale) {
		spoon.render(scale);
	}

	public void renderFork(float scale) {
		fork.render(scale);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}

}
