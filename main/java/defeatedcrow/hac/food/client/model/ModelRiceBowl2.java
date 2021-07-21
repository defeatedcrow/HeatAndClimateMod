package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelRiceBowl2 extends DCFoodModelBase {

	private final ModelRenderer bowl;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer rice;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer soup;
	private final ModelRenderer mochi;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;

	public ModelRiceBowl2(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		bowl = new ModelRenderer(this);
		bowl.setRotationPoint(0.0F, 8.0F, 0.0F);
		bowl.cubeList.add(new ModelBox(bowl, 0, 0, -3.0F, -1.0F, -3.0F, 6, 1, 6, 0.0F, false));
		bowl.cubeList.add(new ModelBox(bowl, 25, 0, -4.0F, -2.0F, -4.0F, 8, 1, 8, 0.0F, false));
		bowl.cubeList.add(new ModelBox(bowl, 0, 8, -5.0F, -8.0F, -5.0F, 10, 6, 1, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bowl.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 1.5708F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 16, -4.0F, -8.0F, -5.0F, 8, 6, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bowl.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -1.5708F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 16, -4.0F, -8.0F, -5.0F, 8, 6, 1, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bowl.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 3.1416F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 8, -5.0F, -8.0F, -5.0F, 10, 6, 1, 0.0F, false));

		rice = new ModelRenderer(this);
		rice.setRotationPoint(0.0F, 8.0F, 0.0F);
		rice.cubeList.add(new ModelBox(rice, 23, 10, -3.5F, -6.0F, -3.5F, 7, 4, 7, 0.0F, false));
		rice.cubeList.add(new ModelBox(rice, 25, 22, -3.0F, -7.0F, -3.0F, 6, 1, 6, 0.0F, false));
		rice.cubeList.add(new ModelBox(rice, 45, 11, -2.0F, -8.0F, -2.0F, 4, 1, 4, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		rice.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.2618F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 48, 26, -2.0F, -9.1F, -2.0F, 4, 0, 4, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		rice.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, -2.618F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 50, 22, -2.0F, -9.0F, -1.0F, 4, 1, 2, 0.0F, false));

		soup = new ModelRenderer(this);
		soup.setRotationPoint(0.0F, 8.0F, 0.0F);
		soup.cubeList.add(new ModelBox(soup, 0, 24, -4.0F, -6.5F, -4.0F, 8, 0, 8, 0.0F, false));

		mochi = new ModelRenderer(this);
		mochi.setRotationPoint(0.0F, 8.0F, 0.0F);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, -7.0F, 0.0F);
		mochi.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.1745F, 1.0472F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 52, 17, -2.0F, 0.0F, 1.0F, 2, 1, 3, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, -7.0F, 0.0F);
		mochi.addChild(cube_r7);
		setRotationAngle(cube_r7, -0.5236F, -2.618F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 52, 17, -2.0F, -0.5F, 0.0F, 2, 1, 3, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, -7.0F, 0.0F);
		mochi.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.2618F, 0.0F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 52, 17, -3.0F, 0.0F, 0.0F, 2, 1, 3, 0.0F, false));
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bowl.render(scale);
	}

	public void renderSoup(float scale) {
		soup.render(scale);
	}

	public void renderRice(float scale) {
		rice.render(scale);
	}

	public void renderMochi(float scale) {
		mochi.render(scale);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
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
			mochi.rotateAngleY = (entity.getIndividual() / 32F) * (float) (Math.PI);
		}
	}

}
