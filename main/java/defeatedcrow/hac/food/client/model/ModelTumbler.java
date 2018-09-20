package defeatedcrow.hac.food.client.model;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTumbler extends DCFoodModelBase {

	public ModelRenderer bottom;
	public ModelRenderer front;
	public ModelRenderer back;
	public ModelRenderer left;
	public ModelRenderer right;

	public ModelTumbler(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		this.bottom = new ModelRenderer(this, 0, 0);
		this.bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bottom.addBox(-2.5F, -0.5F, -2.5F, 5, 2, 5);
		this.front = new ModelRenderer(this, 0, 8);
		this.front.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.front.addBox(-3.5F, -10.0F, -3.5F, 7, 12, 1);
		this.back = new ModelRenderer(this, 0, 8);
		this.back.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.back.addBox(-3.5F, -10.0F, 2.5F, 7, 12, 1);
		this.left = new ModelRenderer(this, 18, 4);
		this.left.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left.addBox(2.5F, -10.0F, -2.5F, 1, 12, 5);
		this.right = new ModelRenderer(this, 18, 4);
		this.right.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right.addBox(-3.5F, -10.0F, -2.5F, 1, 12, 5);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.45F);
		this.bottom.render(scale);
		this.front.render(scale);
		this.back.render(scale);
		this.left.render(scale);
		this.right.render(scale);
		GlStateManager.disableBlend();
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
