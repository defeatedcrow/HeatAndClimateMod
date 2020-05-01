package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelLotusBud1 extends DCTileModelBase {

	public ModelRenderer stem1;
	public ModelRenderer stem2;
	public ModelRenderer stem3;

	public ModelLotusBud1() {

		textureWidth = 64;
		textureHeight = 32;

		stem1 = new ModelRenderer(this, 0, 0);
		stem1.addBox(-4F, 6F, -4F, 1, 2, 1, 0F);
		stem1.setRotationPoint(0F, 0F, 0F);
		stem1.rotateAngleX = 0F;
		stem1.rotateAngleY = 0F;
		stem1.rotateAngleZ = 0F;
		stem1.mirror = false;
		stem2 = new ModelRenderer(this, 5, 0);
		stem2.addBox(-1F, 6F, 3F, 1, 2, 1, 0F);
		stem2.setRotationPoint(0F, 0F, 0F);
		stem2.rotateAngleX = 0F;
		stem2.rotateAngleY = 0F;
		stem2.rotateAngleZ = 0F;
		stem2.mirror = false;
		stem3 = new ModelRenderer(this, 10, 0);
		stem3.addBox(3F, 7F, 0F, 1, 1, 1, 0F);
		stem3.setRotationPoint(0F, 0F, 0F);
		stem3.rotateAngleX = 0F;
		stem3.rotateAngleY = 0F;
		stem3.rotateAngleZ = 0F;
		stem3.mirror = false;
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		stem1.render(scale);
		stem2.render(scale);
		stem3.render(scale);
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

	public void setRotationAngles(int num1, int num2) {}

}
