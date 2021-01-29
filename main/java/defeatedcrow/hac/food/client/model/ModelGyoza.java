package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelGyoza extends ModelSteakPlate {

	private final ModelRenderer gyoza1;
	private final ModelRenderer wing1_r1;
	private final ModelRenderer cube1_r1;
	private final ModelRenderer gyoza2;
	private final ModelRenderer wing1_r2;
	private final ModelRenderer cube1_r2;
	private final ModelRenderer gyoza3;
	private final ModelRenderer wing1_r3;
	private final ModelRenderer cube1_r3;
	private final ModelRenderer gyoza4;
	private final ModelRenderer wing1_r4;
	private final ModelRenderer cube1_r4;
	private final ModelRenderer gyoza5;
	private final ModelRenderer wing1_r5;
	private final ModelRenderer cube1_r5;
	private final ModelRenderer gyoza6;
	private final ModelRenderer wing1_r6;
	private final ModelRenderer cube1_r6;
	private final ModelRenderer gyoza7;
	private final ModelRenderer wing1_r7;
	private final ModelRenderer cube1_r7;
	private final ModelRenderer gyoza8;
	private final ModelRenderer wing1_r8;
	private final ModelRenderer cube1_r8;

	public ModelGyoza(boolean baked) {
		super(baked);

		textureWidth = 16;
		textureHeight = 16;

		gyoza1 = new ModelRenderer(this);
		gyoza1.setRotationPoint(0.0F, 0.0F, 0.0F);

		wing1_r1 = new ModelRenderer(this);
		wing1_r1.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza1.addChild(wing1_r1);
		setRotation(wing1_r1, -0.5236F, 0.0F, 0.0F);
		wing1_r1.cubeList.add(new ModelBox(wing1_r1, 0, 0, -6.0F, -3.0F, 0.0F, 5, 1, 0, 0.0F, false));

		cube1_r1 = new ModelRenderer(this);
		cube1_r1.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza1.addChild(cube1_r1);
		setRotation(cube1_r1, -0.4363F, 0.0F, 0.0F);
		cube1_r1.cubeList.add(new ModelBox(cube1_r1, 0, 2, -6.0F, -2.0F, 0.0F, 5, 2, 2, 0.0F, false));

		gyoza2 = new ModelRenderer(this);
		gyoza2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(gyoza2, 0.0F, -0.7854F, 0.0F);

		wing1_r2 = new ModelRenderer(this);
		wing1_r2.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza2.addChild(wing1_r2);
		setRotation(wing1_r2, -0.5236F, 0.0F, 0.0F);
		wing1_r2.cubeList.add(new ModelBox(wing1_r2, 0, 0, -6.0F, -3.0F, 0.0F, 5, 1, 0, 0.0F, false));

		cube1_r2 = new ModelRenderer(this);
		cube1_r2.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza2.addChild(cube1_r2);
		setRotation(cube1_r2, -0.4363F, 0.0F, 0.0F);
		cube1_r2.cubeList.add(new ModelBox(cube1_r2, 0, 2, -6.0F, -2.0F, 0.0F, 5, 2, 2, 0.0F, false));

		gyoza3 = new ModelRenderer(this);
		gyoza3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(gyoza3, 0.0F, -1.5708F, 0.0F);

		wing1_r3 = new ModelRenderer(this);
		wing1_r3.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza3.addChild(wing1_r3);
		setRotation(wing1_r3, -0.5236F, 0.0F, 0.0F);
		wing1_r3.cubeList.add(new ModelBox(wing1_r3, 0, 0, -6.0F, -3.0F, 0.0F, 5, 1, 0, 0.0F, false));

		cube1_r3 = new ModelRenderer(this);
		cube1_r3.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza3.addChild(cube1_r3);
		setRotation(cube1_r3, -0.4363F, 0.0F, 0.0F);
		cube1_r3.cubeList.add(new ModelBox(cube1_r3, 0, 2, -6.0F, -2.0F, 0.0F, 5, 2, 2, 0.0F, false));

		gyoza4 = new ModelRenderer(this);
		gyoza4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(gyoza4, 0.0F, -2.3562F, 0.0F);

		wing1_r4 = new ModelRenderer(this);
		wing1_r4.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza4.addChild(wing1_r4);
		setRotation(wing1_r4, -0.5236F, 0.0F, 0.0F);
		wing1_r4.cubeList.add(new ModelBox(wing1_r4, 0, 0, -6.0F, -3.0F, 0.0F, 5, 1, 0, 0.0F, false));

		cube1_r4 = new ModelRenderer(this);
		cube1_r4.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza4.addChild(cube1_r4);
		setRotation(cube1_r4, -0.4363F, 0.0F, 0.0F);
		cube1_r4.cubeList.add(new ModelBox(cube1_r4, 0, 2, -6.0F, -2.0F, 0.0F, 5, 2, 2, 0.0F, false));

		gyoza5 = new ModelRenderer(this);
		gyoza5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(gyoza5, 0.0F, 3.1416F, 0.0F);

		wing1_r5 = new ModelRenderer(this);
		wing1_r5.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza5.addChild(wing1_r5);
		setRotation(wing1_r5, -0.5236F, 0.0F, 0.0F);
		wing1_r5.cubeList.add(new ModelBox(wing1_r5, 0, 0, -6.0F, -3.0F, 0.0F, 5, 1, 0, 0.0F, false));

		cube1_r5 = new ModelRenderer(this);
		cube1_r5.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza5.addChild(cube1_r5);
		setRotation(cube1_r5, -0.4363F, 0.0F, 0.0F);
		cube1_r5.cubeList.add(new ModelBox(cube1_r5, 0, 2, -6.0F, -2.0F, 0.0F, 5, 2, 2, 0.0F, false));

		gyoza6 = new ModelRenderer(this);
		gyoza6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(gyoza6, 0.0F, 0.7854F, 0.0F);

		wing1_r6 = new ModelRenderer(this);
		wing1_r6.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza6.addChild(wing1_r6);
		setRotation(wing1_r6, -0.5236F, 0.0F, 0.0F);
		wing1_r6.cubeList.add(new ModelBox(wing1_r6, 0, 0, -6.0F, -3.0F, 0.0F, 5, 1, 0, 0.0F, false));

		cube1_r6 = new ModelRenderer(this);
		cube1_r6.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza6.addChild(cube1_r6);
		setRotation(cube1_r6, -0.4363F, 0.0F, 0.0F);
		cube1_r6.cubeList.add(new ModelBox(cube1_r6, 0, 2, -6.0F, -2.0F, 0.0F, 5, 2, 2, 0.0F, false));

		gyoza7 = new ModelRenderer(this);
		gyoza7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(gyoza7, 0.0F, 1.5708F, 0.0F);

		wing1_r7 = new ModelRenderer(this);
		wing1_r7.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza7.addChild(wing1_r7);
		setRotation(wing1_r7, -0.5236F, 0.0F, 0.0F);
		wing1_r7.cubeList.add(new ModelBox(wing1_r7, 0, 0, -6.0F, -3.0F, 0.0F, 5, 1, 0, 0.0F, false));

		cube1_r7 = new ModelRenderer(this);
		cube1_r7.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza7.addChild(cube1_r7);
		setRotation(cube1_r7, -0.4363F, 0.0F, 0.0F);
		cube1_r7.cubeList.add(new ModelBox(cube1_r7, 0, 2, -6.0F, -2.0F, 0.0F, 5, 2, 2, 0.0F, false));

		gyoza8 = new ModelRenderer(this);
		gyoza8.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(gyoza8, 0.0F, 2.3562F, 0.0F);

		wing1_r8 = new ModelRenderer(this);
		wing1_r8.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza8.addChild(wing1_r8);
		setRotation(wing1_r8, -0.5236F, 0.0F, 0.0F);
		wing1_r8.cubeList.add(new ModelBox(wing1_r8, 0, 0, -6.0F, -3.0F, 0.0F, 5, 1, 0, 0.0F, false));

		cube1_r8 = new ModelRenderer(this);
		cube1_r8.setRotationPoint(0.0F, -1.0F, 0.0F);
		gyoza8.addChild(cube1_r8);
		setRotation(cube1_r8, -0.4363F, 0.0F, 0.0F);
		cube1_r8.cubeList.add(new ModelBox(cube1_r8, 0, 2, -6.0F, -2.0F, 0.0F, 5, 2, 2, 0.0F, false));
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		gyoza1.render(scale);
		gyoza2.render(scale);
		gyoza3.render(scale);
		gyoza4.render(scale);
		gyoza5.render(scale);
		gyoza6.render(scale);
		gyoza7.render(scale);
		gyoza8.render(scale);
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
