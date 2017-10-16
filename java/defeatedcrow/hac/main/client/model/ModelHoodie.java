package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.ModelThinBiped;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// ぼうし
@SideOnly(Side.CLIENT)
public class ModelHoodie extends ModelThinBiped {
	ModelRenderer headH;
	ModelRenderer hoodH;
	ModelRenderer bodyH;
	ModelRenderer rightArmH;
	ModelRenderer leftArmH;

	public boolean isSneak = false;
	public boolean isBlocking = false;
	public boolean aimedBow = false;
	public int slot;

	public ModelHoodie(int b) {
		this(0.75F, b);
	}

	public ModelHoodie(float f, int b) {
		this(f, 0.0F, 64, 32, b);
	}

	public ModelHoodie(float f1, float f2, int i3, int i4, int s) {
		super(s);
		slot = s;
		textureWidth = i3;
		textureHeight = i4;
		headH = new ModelRenderer(this, 0, 0);
		headH.addBox(-4F, -8F, -4F, 8, 8, 8, f1);
		headH.setRotationPoint(0F, 0F, 0F);
		headH.setTextureSize(64, 32);
		headH.mirror = true;
		bodyH = new ModelRenderer(this, 0, 16);
		bodyH.addBox(-4F, 0F, -2F, 8, 12, 4, f1);
		bodyH.setRotationPoint(0F, 0F, 0F);
		bodyH.setTextureSize(64, 32);
		bodyH.mirror = true;
		rightArmH = new ModelRenderer(this, 40, 16);
		rightArmH.addBox(-3F, -2F, -2F, 4, 12, 4, f1);
		rightArmH.setRotationPoint(-5F, 2F, 0F);
		rightArmH.setTextureSize(64, 32);
		rightArmH.mirror = true;
		leftArmH = new ModelRenderer(this, 24, 16);
		leftArmH.addBox(-1F, -2F, -2F, 4, 12, 4, f1);
		leftArmH.setRotationPoint(5F, 2F, 0F);
		leftArmH.setTextureSize(64, 32);
		leftArmH.mirror = true;
		hoodH = new ModelRenderer(this, 32, 0);
		hoodH.addBox(-4.5F, -0.5F, -2.5F, 9, 6, 5, f1);
		hoodH.setRotationPoint(0F, 0F, 0F);
		hoodH.setTextureSize(64, 32);
		hoodH.mirror = true;
	}

	@Override
	public void render(Entity ent, float f2, float f3, float f4, float f5, float f6, float f7) {
		this.setRotationAngles(f2, f3, f4, f5, f6, f7, ent);
		GlStateManager.pushMatrix();

		// showModelをここでいじる
		hoodH.showModel = true;
		headH.showModel = true;
		bodyH.showModel = true;
		rightArmH.showModel = true;
		leftArmH.showModel = true;

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
			this.rightArmH.render(f7);
			this.leftArmH.render(f7);
			this.hoodH.render(f7);
		} else {
			if (ent.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}
			this.headH.render(f7);
			this.bodyH.render(f7);
			this.rightArmH.render(f7);
			this.leftArmH.render(f7);
			this.hoodH.render(f7);
		}

		GlStateManager.popMatrix();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		boolean flag = entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getTicksElytraFlying() > 4;
		this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;

		this.headH.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
		if (flag) {
			this.bipedHead.rotateAngleX = -((float) Math.PI / 4F);
		} else {
			this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
		}

		this.bodyH.rotateAngleY = 0.0F;
		this.rightArmH.rotationPointZ = 0.0F;
		this.rightArmH.rotationPointX = -5.0F;
		this.leftArmH.rotationPointZ = 0.0F;
		this.leftArmH.rotationPointX = 5.0F;
		float f = 1.0F;

		if (flag) {
			f = (float) (entity.motionX * entity.motionX + entity.motionY * entity.motionY
					+ entity.motionZ * entity.motionZ);
			f = f / 0.2F;
			f = f * f * f;
		}

		if (f < 1.0F) {
			f = 1.0F;
		}

		this.rightArmH.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount
				* 0.5F / f;
		this.leftArmH.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		this.rightArmH.rotateAngleZ = 0.0F;
		this.leftArmH.rotateAngleZ = 0.0F;

		if (this.isRiding) {
			this.rightArmH.rotateAngleX += -((float) Math.PI / 5F);
			this.leftArmH.rotateAngleX += -((float) Math.PI / 5F);
		}

		this.rightArmH.rotateAngleY = 0.0F;
		this.rightArmH.rotateAngleZ = 0.0F;

		switch (this.leftArmPose) {
		case EMPTY:
			this.leftArmH.rotateAngleY = 0.0F;
			break;
		case BLOCK:
			this.leftArmH.rotateAngleX = this.leftArmH.rotateAngleX * 0.5F - 0.9424779F;
			this.leftArmH.rotateAngleY = 0.5235988F;
			break;
		case ITEM:
			this.leftArmH.rotateAngleX = this.leftArmH.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
			this.leftArmH.rotateAngleY = 0.0F;
		default:
			this.leftArmH.rotateAngleY = 0.0F;
			break;
		}

		switch (this.rightArmPose) {
		case EMPTY:
			this.rightArmH.rotateAngleY = 0.0F;
			break;
		case BLOCK:
			this.rightArmH.rotateAngleX = this.rightArmH.rotateAngleX * 0.5F - 0.9424779F;
			this.rightArmH.rotateAngleY = -0.5235988F;
			break;
		case ITEM:
			this.rightArmH.rotateAngleX = this.rightArmH.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
			this.rightArmH.rotateAngleY = 0.0F;
		default:
			this.leftArmH.rotateAngleY = 0.0F;
			break;
		}

		if (this.swingProgress > 0.0F) {
			EnumHandSide enumhandside = this.getMainHand(entity);
			ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
			this.getArmForSide(enumhandside.opposite());
			float f1 = this.swingProgress;
			this.bodyH.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

			if (enumhandside == EnumHandSide.LEFT) {
				this.bodyH.rotateAngleY *= -1.0F;
			}

			this.rightArmH.rotationPointZ = MathHelper.sin(this.bodyH.rotateAngleY) * 5.0F;
			this.rightArmH.rotationPointX = -MathHelper.cos(this.bodyH.rotateAngleY) * 5.0F;
			this.leftArmH.rotationPointZ = -MathHelper.sin(this.bodyH.rotateAngleY) * 5.0F;
			this.leftArmH.rotationPointX = MathHelper.cos(this.bodyH.rotateAngleY) * 5.0F;
			this.rightArmH.rotateAngleY += this.bodyH.rotateAngleY;
			this.leftArmH.rotateAngleY += this.bodyH.rotateAngleY;
			this.leftArmH.rotateAngleX += this.bodyH.rotateAngleY;
			f1 = 1.0F - this.swingProgress;
			f1 = f1 * f1;
			f1 = f1 * f1;
			f1 = 1.0F - f1;
			float f2 = MathHelper.sin(f1 * (float) Math.PI);
			float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.headH.rotateAngleX - 0.7F) * 0.75F;
			modelrenderer.rotateAngleX = (float) (modelrenderer.rotateAngleX - (f2 * 1.2D + f3));
			modelrenderer.rotateAngleY += this.bodyH.rotateAngleY * 2.0F;
			modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
		}

		if (this.isSneak) {
			this.bodyH.rotateAngleX = 0.5F;
			this.rightArmH.rotateAngleX += 0.4F;
			this.leftArmH.rotateAngleX += 0.4F;
			this.headH.rotationPointY = 1.0F;
		} else {
			this.bodyH.rotateAngleX = 0.0F;
			this.headH.rotationPointY = 0.0F;
		}

		this.rightArmH.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.leftArmH.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.rightArmH.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.leftArmH.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

		if (this.rightArmPose == ModelBiped.ArmPose.BOW_AND_ARROW) {
			this.rightArmH.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
			this.leftArmH.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY + 0.4F;
			this.rightArmH.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
			this.leftArmH.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
		} else if (this.leftArmPose == ModelBiped.ArmPose.BOW_AND_ARROW) {
			this.rightArmH.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
			this.leftArmH.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
			this.rightArmH.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
			this.leftArmH.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
		}
		this.hoodH.rotateAngleX = bodyH.rotateAngleX;
		this.hoodH.rotateAngleY = bodyH.rotateAngleY;
		this.hoodH.rotateAngleY = bodyH.rotateAngleY;
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
		this.rightArmH.showModel = invisible;
		this.leftArmH.showModel = invisible;
		this.hoodH.showModel = invisible;
	}
}
