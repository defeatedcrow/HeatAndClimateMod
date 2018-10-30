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
public class ModelOvercoat extends ModelThinBiped {
	ModelRenderer headH;
	ModelRenderer bodyH;
	ModelRenderer rightarmH;
	ModelRenderer leftarmH;
	ModelRenderer rightlegH;
	ModelRenderer leftlegH;
	ModelRenderer middlelegH;
	ModelRenderer rightleg2H;
	ModelRenderer leftleg2H;
	ModelRenderer backH;

	public boolean isSneak = false;
	public boolean isBlocking = false;
	public boolean aimedBow = false;
	public int slot;
	public boolean isShort = false;

	public ModelOvercoat(int b) {
		this(0.6F, b);
	}

	public ModelOvercoat(float f, int b) {
		this(f, 0.0F, 64, 64, b);
		if (b == 1) {
			isShort = true;
		}
	}

	public ModelOvercoat(float f1, float f2, int i3, int i4, int s) {
		super(s);
		slot = s;
		textureWidth = i3;
		textureHeight = i4;
		headH = new ModelRenderer(this, 0, 0);
		headH.addBox(-4F, -8F, -4F, 8, 8, 8, f1);
		headH.setRotationPoint(0F, 0F, 0F);
		headH.setTextureSize(64, 64);
		headH.mirror = true;
		bodyH = new ModelRenderer(this, 16, 16);
		bodyH.addBox(-4F, 0F, -2F, 8, 12, 4, f1);
		bodyH.setRotationPoint(0F, 0F, 0F);
		bodyH.setTextureSize(64, 64);
		bodyH.mirror = true;
		rightarmH = new ModelRenderer(this, 0, 16);
		rightarmH.addBox(-3F, -2F, -2F, 4, 12, 4, f1);
		rightarmH.setRotationPoint(-5F, 2F, 0F);
		rightarmH.setTextureSize(64, 64);
		rightarmH.mirror = true;
		leftarmH = new ModelRenderer(this, 40, 16);
		leftarmH.addBox(-1F, -2F, -2F, 4, 12, 4, f1);
		leftarmH.setRotationPoint(5F, 2F, 0F);
		leftarmH.setTextureSize(64, 64);
		leftarmH.mirror = true;
		rightlegH = new ModelRenderer(this, 0, 32);
		rightlegH.addBox(-2F, 0F, -2F, 4, 12, 4, f1);
		rightlegH.setRotationPoint(-2F, 12F, 0F);
		rightlegH.setTextureSize(64, 64);
		rightlegH.mirror = true;
		leftlegH = new ModelRenderer(this, 16, 32);
		leftlegH.addBox(-2F, 0F, -2F, 4, 12, 4, f1);
		leftlegH.setRotationPoint(2F, 12F, 0F);
		leftlegH.setTextureSize(64, 64);
		leftlegH.mirror = true;
		middlelegH = new ModelRenderer(this, 16, 32);
		middlelegH.addBox(-2F, 0F, -2F, 4, 12, 4, f1);
		middlelegH.setRotationPoint(0F, 12F, 0F);
		middlelegH.setTextureSize(64, 64);
		middlelegH.mirror = true;
		rightleg2H = new ModelRenderer(this, 0, 48);
		rightleg2H.addBox(-3F, -1F, -2.5F, 5, 11, 5, f1);
		rightleg2H.setRotationPoint(-2F, 12F, 0F);
		rightleg2H.setTextureSize(64, 64);
		rightleg2H.mirror = true;
		leftleg2H = new ModelRenderer(this, 20, 48);
		leftleg2H.addBox(-2F, -1F, -2.5F, 5, 11, 5, f1);
		leftleg2H.setRotationPoint(2F, 12F, 0F);
		leftleg2H.setTextureSize(64, 64);
		leftleg2H.mirror = true;
		backH = new ModelRenderer(this, 32, 0);
		backH.addBox(-4F, 9F, -3F, 8, 6, 0, f1);
		backH.setRotationPoint(0F, 0F, 0F);
		backH.setTextureSize(64, 64);
		backH.mirror = true;
	}

	@Override
	public void render(Entity ent, float f2, float f3, float f4, float f5, float f6, float f7) {
		this.setRotationAngles(f2, f3, f4, f5, f6, f7, ent);
		GlStateManager.pushMatrix();

		// showModelをここでいじる
		headH.showModel = true;
		bodyH.showModel = true;
		backH.showModel = true;
		rightarmH.showModel = true;
		leftarmH.showModel = true;
		rightlegH.showModel = true;
		leftlegH.showModel = true;
		rightleg2H.showModel = true;
		leftleg2H.showModel = true;
		if (isShort) {
			middlelegH.showModel = false;
		} else {
			middlelegH.showModel = true;

		}

		if (this.isChild) {
			float f = 2.0F;
			GlStateManager.scale(1.5F / f, 1.5F / f, 1.5F / f);
			GlStateManager.translate(0.0F, 16.0F * f7, 0.0F);
			this.headH.render(f7);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(1.0F / f, 1.0F / f, 1.0F / f);
			GlStateManager.translate(0.0F, 24.0F * f7, 0.0F);
			this.bodyH.render(f7);
			this.rightarmH.render(f7);
			this.leftarmH.render(f7);
			this.rightlegH.render(f7);
			this.leftlegH.render(f7);
			this.middlelegH.render(f7);
			this.rightleg2H.render(f7);
			this.leftleg2H.render(f7);
			this.backH.render(f7);
		} else {
			if (ent.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}
			this.headH.render(f7);
			this.bodyH.render(f7);
			this.rightarmH.render(f7);
			this.leftarmH.render(f7);
			this.rightlegH.render(f7);
			this.leftlegH.render(f7);
			this.middlelegH.render(f7);
			this.rightleg2H.render(f7);
			this.leftleg2H.render(f7);
			this.backH.render(f7);
		}

		GlStateManager.popMatrix();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		setAngle(headH, this.head);
		setAngle(bodyH, this.body);
		setAngle(backH, this.body);
		setAngle(rightarmH, this.rightArm);
		setAngle(leftarmH, this.leftArm);
		setAngle(rightlegH, this.rightLeg);
		setAngle(leftlegH, this.leftLeg);
		setAngle(middlelegH, this.leftLeg);
		setAngle(rightleg2H, this.rightLeg);
		setAngle(leftleg2H, this.leftLeg);

		if (this.isRiding) {
			rightlegH.rotateAngleX = rightLeg.rotateAngleX;
			leftlegH.rotateAngleX = leftLeg.rotateAngleX;
			middlelegH.rotateAngleX = leftLeg.rotateAngleX;
			rightlegH.rotateAngleY = 0F;
			leftlegH.rotateAngleY = 0F;
			middlelegH.rotateAngleY = 0F;
		} else {
			rightlegH.rotateAngleX = rightLeg.rotateAngleX * 0.5F;
			leftlegH.rotateAngleX = leftLeg.rotateAngleX * 0.5F;
			middlelegH.rotateAngleX = 0F;
			middlelegH.rotateAngleY = -leftLeg.rotateAngleX;
		}

		middlelegH.rotationPointX = 0F;
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
		this.headH.showModel = invisible;
		this.bodyH.showModel = invisible;
		this.rightarmH.showModel = invisible;
		this.leftarmH.showModel = invisible;
		this.backH.showModel = invisible;
		rightlegH.showModel = invisible;
		leftlegH.showModel = invisible;
		middlelegH.showModel = invisible;
		rightleg2H.showModel = invisible;
		leftleg2H.showModel = invisible;
	}
}
