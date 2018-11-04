package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.ModelThinBiped;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

@SideOnly(Side.CLIENT)
public class ModelDress extends ModelThinBiped {
	ModelRenderer bodyH;
	ModelRenderer rightarmH;
	ModelRenderer leftarmH;
	ModelRenderer rightlegH;
	ModelRenderer leftlegH;
	ModelRenderer middlelegH;
	ModelRenderer rightleg2H;
	ModelRenderer leftleg2H;
	ModelRenderer body2H;
	ModelRenderer rightarm2H;
	ModelRenderer leftarm2H;

	public boolean isSneak = false;
	public boolean isBlocking = false;
	public boolean aimedBow = false;
	public int slot;
	public boolean isShort = false;

	public ModelDress(int b) {
		this(0.45F, b);
	}

	public ModelDress(float f, int b) {
		this(f, 0.0F, 64, 64, b);
	}

	public ModelDress setShort() {
		isShort = true;
		return this;
	}

	public ModelDress(float f1, float f2, int i3, int i4, int s) {
		super(s);
		slot = s;
		textureWidth = i3;
		textureHeight = i4;
		float f3 = f1 + 0.1F;
		float f4 = f1 - 0.2F;
		bodyH = new ModelRenderer(this, 16, 16);
		bodyH.addBox(-4F, 0F, -2F, 8, 12, 4, f1);
		bodyH.setRotationPoint(0F, 0F, 0F);
		bodyH.setTextureSize(64, 64);
		bodyH.mirror = true;
		rightarmH = new ModelRenderer(this, 0, 16);
		rightarmH.addBox(-3F, -2F, -2F, 4, 12, 4, f4);
		rightarmH.setRotationPoint(-5F, 2F, 0F);
		rightarmH.setTextureSize(64, 64);
		rightarmH.mirror = true;
		leftarmH = new ModelRenderer(this, 40, 16);
		leftarmH.addBox(-1F, -2F, -2F, 4, 12, 4, f4);
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
		rightleg2H.addBox(-3F, -1F, -2.5F, 5, 11, 5, f3);
		rightleg2H.setRotationPoint(-2F, 12F, 0F);
		rightleg2H.setTextureSize(64, 64);
		rightleg2H.mirror = true;
		leftleg2H = new ModelRenderer(this, 20, 48);
		leftleg2H.addBox(-2F, -1F, -2.5F, 5, 11, 5, f3);
		leftleg2H.setRotationPoint(2F, 12F, 0F);
		leftleg2H.setTextureSize(64, 64);
		leftleg2H.mirror = true;
		body2H = new ModelRenderer(this, 0, 0);
		body2H.addBox(-4F, 2F, -2.85F, 8, 5, 5, f3);
		body2H.setRotationPoint(0F, 0F, 0F);
		body2H.setTextureSize(64, 64);
		body2H.mirror = true;
		rightarm2H = new ModelRenderer(this, 30, 0);
		rightarm2H.addBox(-3F, -2F, -2F, 4, 4, 4, f3);
		rightarm2H.setRotationPoint(-5F, 2F, 0F);
		rightarm2H.setTextureSize(64, 64);
		rightarm2H.mirror = true;
		leftarm2H = new ModelRenderer(this, 46, 0);
		leftarm2H.addBox(-1F, -2F, -2F, 4, 4, 4, f3);
		leftarm2H.setRotationPoint(5F, 2F, 0F);
		leftarm2H.setTextureSize(64, 64);
		leftarm2H.mirror = true;
	}

	@Override
	public void render(Entity ent, float f2, float f3, float f4, float f5, float f6, float f7) {
		this.setRotationAngles(f2, f3, f4, f5, f6, f7, ent);
		GlStateManager.pushMatrix();

		// showModelをここでいじる
		bodyH.showModel = true;
		body2H.showModel = true;
		rightarmH.showModel = true;
		leftarmH.showModel = true;
		rightarm2H.showModel = true;
		leftarm2H.showModel = true;

		if (isShort) {
			middlelegH.showModel = false;
			rightlegH.showModel = false;
			leftlegH.showModel = false;
			rightleg2H.showModel = false;
			leftleg2H.showModel = false;
		} else {
			middlelegH.showModel = true;
			rightlegH.showModel = true;
			leftlegH.showModel = true;
			rightleg2H.showModel = true;
			leftleg2H.showModel = true;

		}

		if (this.slot != 1 && ent instanceof EntityLivingBase) {
			IItemHandler handler = ((EntityLivingBase) ent)
					.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
			if (handler != null) {
				if (!DCUtil.isEmpty(handler.getStackInSlot(2))) {
					body2H.showModel = false;
				}
			}
		}

		if (this.isChild) {
			float f = 2.0F;
			GlStateManager.scale(1.5F / f, 1.5F / f, 1.5F / f);
			GlStateManager.translate(0.0F, 16.0F * f7, 0.0F);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(1.0F / f, 1.0F / f, 1.0F / f);
			GlStateManager.translate(0.0F, 24.0F * f7, 0.0F);
			this.bodyH.render(f7);
			this.body2H.render(f7);
			this.rightarmH.render(f7);
			this.leftarmH.render(f7);
			this.rightarm2H.render(f7);
			this.leftarm2H.render(f7);
			this.rightlegH.render(f7);
			this.leftlegH.render(f7);
			this.middlelegH.render(f7);
			this.rightleg2H.render(f7);
			this.leftleg2H.render(f7);
		} else {
			if (ent.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}
			this.bodyH.render(f7);
			this.body2H.render(f7);
			this.rightarmH.render(f7);
			this.leftarmH.render(f7);
			this.rightarm2H.render(f7);
			this.leftarm2H.render(f7);
			this.rightlegH.render(f7);
			this.leftlegH.render(f7);
			this.middlelegH.render(f7);
			this.rightleg2H.render(f7);
			this.leftleg2H.render(f7);
		}

		GlStateManager.popMatrix();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		setAngle(bodyH, this.body);
		setAngle(body2H, this.body);
		setAngle(rightarmH, this.rightArm);
		setAngle(leftarmH, this.leftArm);
		setAngle(rightarm2H, this.rightArm);
		setAngle(leftarm2H, this.leftArm);
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
		this.rightarmH.showModel = invisible;
		this.leftarmH.showModel = invisible;
		this.rightarm2H.showModel = invisible;
		this.leftarm2H.showModel = invisible;
		this.body2H.showModel = invisible;
		rightlegH.showModel = invisible;
		leftlegH.showModel = invisible;
		middlelegH.showModel = invisible;
		rightleg2H.showModel = invisible;
		leftleg2H.showModel = invisible;
	}
}
