package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockBrewingWood extends DCTileModelBase {

	private final ModelRenderer base;
	private final ModelRenderer side1;
	private final ModelRenderer side2;
	private final ModelRenderer side3;
	private final ModelRenderer side4;
	private final ModelRenderer side5;
	private final ModelRenderer side6;
	private final ModelRenderer side7;
	private final ModelRenderer side8;

	public ModelBlockBrewingWood() {

		textureWidth = 64;
		textureHeight = 32;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 3, 0, -6.0F, -1.0F, -6.0F, 12, 0, 12, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 3, 15, -6.0F, -16.0F, -6.0F, 12, 0, 12, 0.0F, false));

		side1 = new ModelRenderer(this);
		side1.setRotationPoint(0.0F, 16.0F, 0.0F);
		side1.cubeList.add(new ModelBox(side1, 0, 0, -3.0F, -8.0F, -7.0F, 6, 16, 1, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 42, 0, -3.0F, -5.0F, -7.5F, 6, 1, 1, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 42, 3, -3.0F, 4.0F, -7.5F, 6, 1, 1, 0.0F, false));

		side2 = new ModelRenderer(this);
		side2.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side2, 0.0F, -0.7854F, 0.0F);
		side2.cubeList.add(new ModelBox(side2, 0, 0, -3.0F, -8.0F, -7.0F, 6, 16, 1, 0.0F, false));
		side2.cubeList.add(new ModelBox(side2, 42, 0, -3.0F, -5.0F, -7.5F, 6, 1, 1, 0.0F, false));
		side2.cubeList.add(new ModelBox(side2, 42, 3, -3.0F, 4.0F, -7.5F, 6, 1, 1, 0.0F, false));

		side3 = new ModelRenderer(this);
		side3.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side3, 0.0F, -1.5708F, 0.0F);
		side3.cubeList.add(new ModelBox(side3, 0, 0, -3.0F, -8.0F, -7.0F, 6, 16, 1, 0.0F, false));
		side3.cubeList.add(new ModelBox(side3, 42, 0, -3.0F, -5.0F, -7.5F, 6, 1, 1, 0.0F, false));
		side3.cubeList.add(new ModelBox(side3, 42, 3, -3.0F, 4.0F, -7.5F, 6, 1, 1, 0.0F, false));

		side4 = new ModelRenderer(this);
		side4.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side4, 0.0F, -2.3562F, 0.0F);
		side4.cubeList.add(new ModelBox(side4, 0, 0, -3.0F, -8.0F, -7.0F, 6, 16, 1, 0.0F, false));
		side4.cubeList.add(new ModelBox(side4, 42, 0, -3.0F, -5.0F, -7.5F, 6, 1, 1, 0.0F, false));
		side4.cubeList.add(new ModelBox(side4, 42, 3, -3.0F, 4.0F, -7.5F, 6, 1, 1, 0.0F, false));

		side5 = new ModelRenderer(this);
		side5.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side5, 0.0F, 3.1416F, 0.0F);
		side5.cubeList.add(new ModelBox(side5, 0, 0, -3.0F, -8.0F, -7.0F, 6, 16, 1, 0.0F, false));
		side5.cubeList.add(new ModelBox(side5, 42, 0, -3.0F, -5.0F, -7.5F, 6, 1, 1, 0.0F, false));
		side5.cubeList.add(new ModelBox(side5, 42, 3, -3.0F, 4.0F, -7.5F, 6, 1, 1, 0.0F, false));

		side6 = new ModelRenderer(this);
		side6.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side6, 0.0F, 0.7854F, 0.0F);
		side6.cubeList.add(new ModelBox(side6, 0, 0, -3.0F, -8.0F, -7.0F, 6, 16, 1, 0.0F, false));
		side6.cubeList.add(new ModelBox(side6, 42, 0, -3.0F, -5.0F, -7.5F, 6, 1, 1, 0.0F, false));
		side6.cubeList.add(new ModelBox(side6, 42, 3, -3.0F, 4.0F, -7.5F, 6, 1, 1, 0.0F, false));

		side7 = new ModelRenderer(this);
		side7.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side7, 0.0F, 1.5708F, 0.0F);
		side7.cubeList.add(new ModelBox(side7, 0, 0, -3.0F, -8.0F, -7.0F, 6, 16, 1, 0.0F, false));
		side7.cubeList.add(new ModelBox(side7, 42, 0, -3.0F, -5.0F, -7.5F, 6, 1, 1, 0.0F, false));
		side7.cubeList.add(new ModelBox(side7, 42, 3, -3.0F, 4.0F, -7.5F, 6, 1, 1, 0.0F, false));

		side8 = new ModelRenderer(this);
		side8.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side8, 0.0F, 2.3562F, 0.0F);
		side8.cubeList.add(new ModelBox(side8, 0, 0, -3.0F, -8.0F, -7.0F, 6, 16, 1, 0.0F, false));
		side8.cubeList.add(new ModelBox(side8, 42, 0, -3.0F, -5.0F, -7.5F, 6, 1, 1, 0.0F, false));
		side8.cubeList.add(new ModelBox(side8, 42, 3, -3.0F, 4.0F, -7.5F, 6, 1, 1, 0.0F, false));
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		base.render(scale);
		side1.render(scale);
		side2.render(scale);
		side3.render(scale);
		side4.render(scale);
		side5.render(scale);
		side6.render(scale);
		side7.render(scale);
		side8.render(scale);
	}

	private void setRotationAngle(ModelRenderer model, float x, float y, float z) {
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
