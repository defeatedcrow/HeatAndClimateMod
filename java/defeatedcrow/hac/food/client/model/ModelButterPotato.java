package defeatedcrow.hac.food.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelButterPotato extends ModelSteakPlate {

	ModelRenderer potato1;
	ModelRenderer potato2;
	ModelRenderer potato3;
	ModelRenderer potato4;
	ModelRenderer butter1;
	ModelRenderer butter2;

	public ModelButterPotato(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		potato1 = new ModelRenderer(this, 0, 0);
		potato1.addBox(-4F, -4F, -4F, 4, 3, 3);
		potato1.setRotationPoint(0F, -1F, 0F);
		potato1.setTextureSize(64, 32);
		potato1.mirror = true;
		setRotation(potato1, 0.3490659F, 0.5235988F, 0F);
		potato2 = new ModelRenderer(this, 15, 0);
		potato2.addBox(-4F, -3F, -2F, 4, 3, 3);
		potato2.setRotationPoint(0F, -1F, 0F);
		potato2.setTextureSize(64, 32);
		potato2.mirror = true;
		setRotation(potato2, -0.5235988F, 0.5235988F, 0F);
		potato3 = new ModelRenderer(this, 0, 8);
		potato3.addBox(0.5F, -3F, -2F, 4, 3, 3);
		potato3.setRotationPoint(0F, -1F, 0F);
		potato3.setTextureSize(64, 32);
		potato3.mirror = true;
		setRotation(potato3, 0.5235988F, 0.418879F, 0F);
		potato4 = new ModelRenderer(this, 15, 8);
		potato4.addBox(0.5F, -4F, 0F, 4, 3, 3);
		potato4.setRotationPoint(0F, -1F, 0F);
		potato4.setTextureSize(64, 32);
		potato4.mirror = true;
		setRotation(potato4, -0.5235988F, 0.4363323F, 0F);
		butter1 = new ModelRenderer(this, 0, 15);
		butter1.addBox(-3F, -3F, -2F, 2, 1, 1);
		butter1.setRotationPoint(0F, -1F, 0F);
		butter1.setTextureSize(64, 32);
		butter1.mirror = true;
		setRotation(butter1, 0F, 0.4886922F, 0F);
		butter2 = new ModelRenderer(this, 0, 15);
		butter2.addBox(1.5F, -2.5F, 0F, 2, 1, 1);
		butter2.setRotationPoint(0F, -1F, 0F);
		butter2.setTextureSize(64, 32);
		butter2.mirror = true;
		setRotation(butter2, 0F, 0.418879F, 0F);
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		potato1.render(scale);
		potato2.render(scale);
		potato3.render(scale);
		potato4.render(scale);
		butter1.render(scale);
		butter2.render(scale);
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
