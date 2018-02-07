package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.ModelThinBiped;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// ぼうし
@SideOnly(Side.CLIENT)
public class ModelWoolWear extends ModelThinBiped {
	ModelRenderer boots1;
	ModelRenderer boots2;
	ModelRenderer boots3;
	ModelRenderer boots4;
	ModelRenderer wool1;
	ModelRenderer wool2;
	ModelRenderer hat;
	ModelRenderer rightarm;
	ModelRenderer leftarm;

	public ModelWoolWear(int b) {
		this(0.65F, b);
	}

	public ModelWoolWear(float f, int b) {
		this(f, 0.0F, 64, 32, b);
	}

	public ModelWoolWear(float f1, float f2, int i3, int i4, int s) {
		super(s);
		slot = s;
		textureWidth = i3;
		textureHeight = i4;
		boots1 = new ModelRenderer(this, 0, 0);
		boots1.addBox(-2F, 8F, -2F, 4, 4, 4, f1);
		boots1.setRotationPoint(-2F, 12F, 0F);

		boots2 = new ModelRenderer(this, 0, 8);
		boots2.addBox(-2F, 8F, -2F, 4, 4, 4, f1);
		boots2.setRotationPoint(2F, 12F, 0F);

		boots3 = new ModelRenderer(this, 16, 0);
		boots3.addBox(-2F, 10F, -3F, 4, 2, 1, f1);
		boots3.setRotationPoint(-2F, 12F, 0F);

		boots4 = new ModelRenderer(this, 16, 0);
		boots4.addBox(-2F, 10F, -3F, 4, 2, 1, f1);
		boots4.setRotationPoint(2F, 12F, 0F);

		wool1 = new ModelRenderer(this, 16, 3);
		wool1.addBox(-2.5F, 6F, -2.5F, 5, 2, 5, f1);
		wool1.setRotationPoint(-2F, 12F, 0F);

		wool2 = new ModelRenderer(this, 16, 3);
		wool2.addBox(-2.5F, 6F, -2.5F, 5, 2, 5, f1);
		wool2.setRotationPoint(2F, 12F, 0F);

		hat = new ModelRenderer(this, 0, 18);
		hat.addBox(-4F, -9F, -4F, 8, 4, 8, f1);
		hat.setRotationPoint(0F, 0F, 0F);

		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-3F, 7F, -2F, 4, 3, 4, f1);
		rightarm.setRotationPoint(-5F, 2F, 0F);

		leftarm = new ModelRenderer(this, 40, 24);
		leftarm.addBox(-1F, 7F, -2F, 4, 3, 4, f1);
		leftarm.setRotationPoint(5F, 2F, 0F);

	}

	@Override
	public void render(Entity ent, float f2, float f3, float f4, float f5, float f6, float f7) {
		this.setRotationAngles(f2, f3, f4, f5, f6, f7, ent);
		GlStateManager.pushMatrix();

		// showModelをここでいじる
		if (slot == 0) {
			boots1.showModel = true;
			boots2.showModel = true;
			boots3.showModel = true;
			boots4.showModel = true;
			wool1.showModel = true;
			wool2.showModel = true;
		} else {
			hat.showModel = true;
			rightarm.showModel = true;
			leftarm.showModel = true;
		}

		if (this.isChild) {
			float f = 2.0F;
			GlStateManager.scale(1.5F / f, 1.5F / f, 1.5F / f);
			GlStateManager.translate(0.0F, 16.0F * f7, 0.0F);
			this.hat.render(f7);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(1.0F / f, 1.0F / f, 1.0F / f);
			GlStateManager.translate(0.0F, 24.0F * f7, 0.0F);
			this.rightarm.render(f7);
			this.leftarm.render(f7);
			this.boots1.render(f7);
			this.boots2.render(f7);
			this.boots3.render(f7);
			this.boots4.render(f7);
			this.wool1.render(f7);
			this.wool2.render(f7);
		} else {
			if (ent.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}
			this.hat.render(f7);
			this.rightarm.render(f7);
			this.leftarm.render(f7);
			this.boots1.render(f7);
			this.boots2.render(f7);
			this.boots3.render(f7);
			this.boots4.render(f7);
			this.wool1.render(f7);
			this.wool2.render(f7);
		}

		GlStateManager.popMatrix();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		setAngle(hat, this.bipedHead);
		setAngle(rightarm, this.bipedRightArm);
		setAngle(leftarm, this.bipedLeftArm);
		setAngle(boots1, this.bipedRightLeg);
		setAngle(boots2, this.bipedLeftLeg);
		setAngle(boots3, this.bipedRightLeg);
		setAngle(boots4, this.bipedLeftLeg);
		setAngle(wool1, this.bipedRightLeg);
		setAngle(wool2, this.bipedLeftLeg);
	}

	@Override
	public void setModelAttributes(ModelBase model) {
		super.setModelAttributes(model);

		if (model instanceof ModelBiped) {
			ModelBiped modelbiped = (ModelBiped) model;
			this.leftArmPose = modelbiped.leftArmPose;
			this.rightArmPose = modelbiped.rightArmPose;
			this.isSneak = modelbiped.isSneak;
			this.isChild = modelbiped.isChild;
			this.isRiding = modelbiped.isRiding;
			this.swingProgress = modelbiped.swingProgress;
		}
	}

	@Override
	public void setVisible(boolean invisible) {
		super.setVisible(invisible);
		this.hat.showModel = invisible;
		this.boots1.showModel = invisible;
		this.boots2.showModel = invisible;
		this.boots3.showModel = invisible;
		this.boots4.showModel = invisible;
		this.rightarm.showModel = invisible;
		this.leftarm.showModel = invisible;
		wool1.showModel = invisible;
		wool2.showModel = invisible;
	}
}
