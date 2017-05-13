package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDagger extends DCFoodModelBase {

	ModelRenderer part1;
	ModelRenderer part2;
	ModelRenderer blade1;
	ModelRenderer brade2;

	public ModelDagger(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		part1 = new ModelRenderer(this, 12, 0);
		part1.addBox(-1F, -1F, 7F, 2, 2, 4);
		part1.setRotationPoint(0F, 0F, 0F);
		part1.setTextureSize(64, 32);
		part1.mirror = true;
		setRotation(part1, 0F, 0F, 0F);
		part2 = new ModelRenderer(this, 0, 0);
		part2.addBox(-2F, -2F, 6F, 4, 4, 1);
		part2.setRotationPoint(0F, 0F, 0F);
		part2.setTextureSize(64, 32);
		part2.mirror = true;
		setRotation(part2, 0F, 0F, 0F);
		blade1 = new ModelRenderer(this, 0, 8);
		blade1.addBox(0F, -2.5F, -6F, 0, 5, 12);
		blade1.setRotationPoint(0F, 0F, 0F);
		blade1.setTextureSize(64, 32);
		blade1.mirror = true;
		setRotation(blade1, 0F, 0F, 0F);
		brade2 = new ModelRenderer(this, 24, 12);
		brade2.addBox(-2.5F, 0F, -6F, 5, 0, 12);
		brade2.setRotationPoint(0F, 0F, 0F);
		brade2.setTextureSize(64, 32);
		brade2.mirror = true;
		setRotation(brade2, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		part1.render(0.0625F);
		part2.render(0.0625F);
		blade1.render(0.0625F);
		brade2.render(0.0625F);
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
