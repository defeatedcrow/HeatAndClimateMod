package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelYoukan extends DCFoodModelBase {

	private final ModelRenderer youkan;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer yatsuhashi;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;

	public ModelYoukan(boolean baked) {
		super(baked);

		textureWidth = 32;
		textureHeight = 16;

		youkan = new ModelRenderer(this);
		youkan.setRotationPoint(0.0F, 3.0F, 0.0F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		youkan.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.2618F, -0.0436F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 9, -2.0F, -2.2F, -4.0F, 7, 1, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 1.5F);
		youkan.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -1.0472F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 0, -1.0F, -5.0F, -3.5F, 2, 3, 5, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, -1.5F);
		youkan.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, -1.0472F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 0, -1.0F, -5.0F, -0.5F, 2, 3, 5, 0.0F, false));

		yatsuhashi = new ModelRenderer(this);
		yatsuhashi.setRotationPoint(0.0F, 3.0F, 0.0F);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		yatsuhashi.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.1745F, 0.2618F, -0.8727F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 15, 0, 2.3F, -1.5F, -2.0F, 1, 2, 4, 0.0F, false));
		cube_r4.cubeList.add(new ModelBox(cube_r4, 15, 0, -0.7F, -5.0F, -2.5F, 1, 2, 4, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		yatsuhashi.addChild(cube_r5);
		setRotationAngle(cube_r5, -1.309F, -0.5236F, 0.5236F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 0, -3.0F, -3.0F, -3.0F, 6, 6, 1, 0.0F, false));
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 0, -6.0F, -5.0F, 0.0F, 6, 6, 1, 0.0F, false));
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		youkan.render(scale);
	}

	public void renderYatsuhashi(float scale) {
		yatsuhashi.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
	}

}
