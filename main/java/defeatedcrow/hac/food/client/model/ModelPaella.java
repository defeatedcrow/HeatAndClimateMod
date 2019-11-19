package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPaella extends ModelSteakPlate {

	public ModelRenderer sauce;
	public ModelRenderer prawn1;
	public ModelRenderer prawn2;
	public ModelRenderer prawn3;
	public ModelRenderer c1;
	public ModelRenderer c2;
	public ModelRenderer c3;

	public ModelPaella(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		sauce = new ModelRenderer(this, 0, 0);
		sauce.addBox(-6F, 0F, -5F, 12, 1, 10, 0F);
		sauce.setRotationPoint(0F, -1F, 0F);
		sauce.rotateAngleX = 0F;
		sauce.rotateAngleY = 0F;
		sauce.rotateAngleZ = 0F;
		sauce.mirror = false;
		prawn1 = new ModelRenderer(this, 0, 12);
		prawn1.addBox(-1F, 0F, -1F, 3, 1, 3, 0F);
		prawn1.setRotationPoint(-3F, -2F, 1F);
		prawn1.rotateAngleX = 0.08726646F;
		prawn1.rotateAngleY = 0F;
		prawn1.rotateAngleZ = 0F;
		prawn1.mirror = false;
		prawn2 = new ModelRenderer(this, 0, 17);
		prawn2.addBox(-1F, 0F, -1F, 3, 1, 3, 0F);
		prawn2.setRotationPoint(3F, -2F, 0F);
		prawn2.rotateAngleX = 0.1047198F;
		prawn2.rotateAngleY = 0F;
		prawn2.rotateAngleZ = 0F;
		prawn2.mirror = false;
		prawn3 = new ModelRenderer(this, 0, 22);
		prawn3.addBox(-1F, 0F, -1F, 3, 1, 3, 0F);
		prawn3.setRotationPoint(-1F, -2F, -3F);
		prawn3.rotateAngleX = -0.1047198F;
		prawn3.rotateAngleY = 0F;
		prawn3.rotateAngleZ = 0F;
		prawn3.mirror = false;
		c1 = new ModelRenderer(this, 14, 12);
		c1.addBox(-1F, -0.5F, 1F, 2, 1, 3, 0F);
		c1.setRotationPoint(0F, -1F, 0F);
		c1.rotateAngleX = 0.1396263F;
		c1.rotateAngleY = 0F;
		c1.rotateAngleZ = 0F;
		c1.mirror = false;
		c2 = new ModelRenderer(this, 14, 17);
		c2.addBox(-1F, -0.5F, 1.5F, 2, 1, 3, 0F);
		c2.setRotationPoint(0F, -1F, 0F);
		c2.rotateAngleX = 0.1396263F;
		c2.rotateAngleY = 2.094395F;
		c2.rotateAngleZ = 0F;
		c2.mirror = false;
		c3 = new ModelRenderer(this, 14, 22);
		c3.addBox(-1F, -0.5F, 2F, 2, 1, 3, 0F);
		c3.setRotationPoint(0F, -1F, 0F);
		c3.rotateAngleX = 0.1396263F;
		c3.rotateAngleY = -2.094395F;
		c3.rotateAngleZ = 0F;
		c3.mirror = false;
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		sauce.render(scale);
		prawn1.render(scale);
		prawn2.render(scale);
		prawn3.render(scale);
		c1.render(scale);
		c2.render(scale);
		c3.render(scale);
	}

	@Override
	public void setIndividualRotation(FoodEntityBase entity) {
		if (entity != null) {
			int i = entity.getIndividual();
			prawn1.rotateAngleY = 0.05F * (i & 3) * (float) (Math.PI);
			prawn2.rotateAngleY = -0.05F * (i & 3) * (float) (Math.PI);
			prawn3.rotateAngleY = 0.05F * (i & 3) * (float) (Math.PI);
			c1.rotateAngleY = 0.05F * (i & 12) * (float) (Math.PI);
			c2.rotateAngleY = 2.094395F + 0.05F * (i & 12) * (float) (Math.PI);
			c3.rotateAngleY = -2.094395F + 0.05F * (i & 12) * (float) (Math.PI);
		}
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
