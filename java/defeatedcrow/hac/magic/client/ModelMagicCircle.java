package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.magic.proj.EntityMobBarrier;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMagicCircle extends DCFoodModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;

	public ModelMagicCircle(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-7.5F, -2F, -7.5F, 15, 4, 15);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 64);
		Shape1.mirror = true;
		setRotation(Shape1, -0.0349066F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 24);
		Shape2.addBox(-8F, -2F, -8F, 16, 4, 16);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(64, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0523599F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		Shape1.render(scale);
		Shape2.render(scale);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		if (entity != null && entity instanceof EntityMobBarrier) {
			EntityMobBarrier barrier = (EntityMobBarrier) entity;
			int age = barrier.getTotalAge();
			if (age > 120) {
				age -= 120;
			}
			float r = age * 3.1415F / 120.0F;
			Shape1.rotateAngleY = r;
			Shape2.rotateAngleY = -r;
		}
	}

}
