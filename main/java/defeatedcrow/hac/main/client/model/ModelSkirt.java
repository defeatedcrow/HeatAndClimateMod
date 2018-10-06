package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.ModelThinBiped;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkirt extends ModelThinBiped {
	ModelRenderer bodyH;
	ModelRenderer rightlegH;
	ModelRenderer leftlegH;
	// ModelRenderer middlelegH;
	ModelRenderer rightleg2H;
	ModelRenderer leftleg2H;
	ModelRenderer rightleg3H;
	ModelRenderer leftleg3H;
	ModelRenderer backH;

	public boolean isSneak = false;
	public boolean isBlocking = false;
	public boolean aimedBow = false;
	public int slot;

	public ModelSkirt(int b) {
		this(0.4F, b);
	}

	public ModelSkirt(float f, int b) {
		this(f, 0.0F, 64, 64, b);
	}

	public ModelSkirt(float f1, float f2, int i3, int i4, int s) {
		super(s);
		slot = s;
		textureWidth = i3;
		textureHeight = i4;
		backH = new ModelRenderer(this, 32, 0);
		backH.addBox(-4F, 8F, -3F, 8, 6, 0, f1);
		backH.setRotationPoint(0F, 0F, 0F);
		backH.setTextureSize(64, 64);
		backH.mirror = true;
		bodyH = new ModelRenderer(this, 0, 0);
		bodyH.addBox(-4F, 10F, -2F, 8, 2, 4, f1);
		bodyH.setRotationPoint(0F, 0F, 0F);
		bodyH.setTextureSize(64, 64);
		bodyH.mirror = true;
		rightlegH = new ModelRenderer(this, 0, 25);
		rightlegH.addBox(-1.8F, 0F, -3F, 4, 11, 6, f1);
		rightlegH.setRotationPoint(-2F, 12F, 0F);
		rightlegH.setTextureSize(64, 64);
		rightlegH.mirror = true;
		leftlegH = new ModelRenderer(this, 21, 25);
		leftlegH.addBox(-2.2F, 0F, -3F, 4, 11, 6, f1);
		leftlegH.setRotationPoint(2F, 12F, 0F);
		leftlegH.setTextureSize(64, 64);
		leftlegH.mirror = true;
		rightleg2H = new ModelRenderer(this, 0, 6);
		rightleg2H.addBox(-2.9F, -1F, -3.5F, 5, 12, 7, f1);
		rightleg2H.setRotationPoint(-2F, 12F, 0F);
		rightleg2H.setTextureSize(64, 64);
		rightleg2H.mirror = true;
		leftleg2H = new ModelRenderer(this, 24, 6);
		leftleg2H.addBox(-2.1F, -1F, -3.5F, 5, 12, 7, f1);
		leftleg2H.setRotationPoint(2F, 12F, 0F);
		leftleg2H.setTextureSize(64, 64);
		leftleg2H.mirror = true;
		rightleg3H = new ModelRenderer(this, 0, 43);
		rightleg3H.addBox(-2F, 0F, -2F, 4, 12, 4, f1);
		rightleg3H.setRotationPoint(-2F, 12F, 0F);
		rightleg3H.setTextureSize(64, 64);
		rightleg3H.mirror = true;
		leftleg3H = new ModelRenderer(this, 16, 43);
		leftleg3H.addBox(-2F, 0F, -2F, 4, 12, 4, f1);
		leftleg3H.setRotationPoint(2F, 12F, 0F);
		leftleg3H.setTextureSize(64, 64);
		leftleg3H.mirror = true;

		// middlelegH = new ModelRenderer(this, 16, 32);
		// middlelegH.addBox(-2F, 0F, -2F, 4, 12, 4, f1);
		// middlelegH.setRotationPoint(0F, 12F, 0F);
		// middlelegH.setTextureSize(64, 64);
		// middlelegH.mirror = true;
	}

	@Override
	public void render(Entity ent, float f2, float f3, float f4, float f5, float f6, float f7) {
		this.setRotationAngles(f2, f3, f4, f5, f6, f7, ent);
		GlStateManager.pushMatrix();

		// showModelをここでいじる
		bodyH.showModel = true;
		backH.showModel = true;
		rightlegH.showModel = true;
		leftlegH.showModel = true;
		rightleg2H.showModel = true;
		leftleg2H.showModel = true;
		rightleg3H.showModel = true;
		leftleg3H.showModel = true;
		// middlelegH.showModel = true;

		if (this.isChild) {
			float f = 2.0F;
			GlStateManager.scale(1.5F / f, 1.5F / f, 1.5F / f);
			GlStateManager.translate(0.0F, 16.0F * f7, 0.0F);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(1.0F / f, 1.0F / f, 1.0F / f);
			GlStateManager.translate(0.0F, 24.0F * f7, 0.0F);
			this.bodyH.render(f7);
			this.rightlegH.render(f7);
			this.leftlegH.render(f7);
			// this.middlelegH.render(f7);
			this.rightleg2H.render(f7);
			this.leftleg2H.render(f7);
			this.rightleg3H.render(f7);
			this.leftleg3H.render(f7);
			this.backH.render(f7);
		} else {
			if (ent.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}
			this.bodyH.render(f7);
			this.rightlegH.render(f7);
			this.leftlegH.render(f7);
			// this.middlelegH.render(f7);
			this.rightleg2H.render(f7);
			this.leftleg2H.render(f7);
			this.rightleg3H.render(f7);
			this.leftleg3H.render(f7);
			this.backH.render(f7);
		}

		GlStateManager.popMatrix();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		setAngle(bodyH, this.body);
		setAngle(backH, this.body);
		setAngle(rightlegH, this.rightLeg);
		setAngle(leftlegH, this.leftLeg);
		// setAngle(middlelegH, this.leftLeg);
		setAngle(rightleg2H, this.rightLeg);
		setAngle(leftleg2H, this.leftLeg);
		setAngle(rightleg3H, this.rightLeg);
		setAngle(leftleg3H, this.leftLeg);

		if (this.isRiding) {
			rightlegH.rotateAngleX = rightLeg.rotateAngleX;
			leftlegH.rotateAngleX = leftLeg.rotateAngleX;
			// middlelegH.rotateAngleX = leftLeg.rotateAngleX;
			rightlegH.rotateAngleY = 0F;
			leftlegH.rotateAngleY = 0F;
			// middlelegH.rotateAngleY = 0F;
		} else {
			rightlegH.rotateAngleX = rightLeg.rotateAngleX * 0.5F;
			leftlegH.rotateAngleX = leftLeg.rotateAngleX * 0.5F;
			// middlelegH.rotateAngleX = 0F;
			// middlelegH.rotateAngleY = -leftLeg.rotateAngleX;
		}

		// middlelegH.rotationPointX = 0F;
		backH.rotateAngleX = bodyH.rotateAngleX + 0.75F;
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
		this.bodyH.showModel = invisible;
		this.backH.showModel = invisible;
		rightlegH.showModel = invisible;
		leftlegH.showModel = invisible;
		// middlelegH.showModel = invisible;
		rightleg2H.showModel = invisible;
		leftleg2H.showModel = invisible;
		rightleg3H.showModel = invisible;
		leftleg3H.showModel = invisible;
	}
}
