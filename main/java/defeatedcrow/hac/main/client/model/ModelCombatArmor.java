package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.ModelThinBiped;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// ぼうし
@SideOnly(Side.CLIENT)
public class ModelCombatArmor extends ModelThinBiped {
	private final ModelRenderer head2;
	private final ModelRenderer body2;

	public boolean isSneak = false;
	public boolean isBlocking = false;
	public boolean aimedBow = false;
	public int slot;

	public ModelCombatArmor(int b) {
		this(0.6F, b);
	}

	public ModelCombatArmor(float f, int b) {
		this(f, 0.0F, 64, 64, b);
	}

	public ModelCombatArmor(float f1, float f2, int i3, int i4, int s) {
		super(s);
		this.leftArmPose = ModelBiped.ArmPose.EMPTY;
		this.rightArmPose = ModelBiped.ArmPose.EMPTY;
		slot = s;
		textureWidth = i3;
		textureHeight = i4;
		head2 = new ModelRenderer(this);
		head2.setRotationPoint(0.0F, 0.0F + f2, 0.0F);
		head2.cubeList.add(new ModelBox(head2, 0, 17, -4.0F, -8.0F, -4.0F, 8, 8, 8, f1, false));
		head2.cubeList.add(new ModelBox(head2, 0, 0, -4.5F, -8.2F, -4.5F, 9, 8, 9, f1, false));

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(0.0F, 0.0F + f2, 0.0F);
		body2.cubeList.add(new ModelBox(body2, 37, 0, -4.0F, 0.0F, -2.0F, 8, 12, 4, f1, false));
		body2.cubeList.add(new ModelBox(body2, 40, 20, -2.5F, 5.0F, -3.5F, 2, 4, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 33, 20, -5.0F, 5.0F, -3.5F, 2, 4, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 47, 20, 0.5F, 5.0F, -3.5F, 2, 4, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 54, 20, 3.0F, 5.0F, -3.5F, 2, 4, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 40, 26, -5.5F, 5.0F, -1.5F, 1, 4, 3, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 49, 26, 4.5F, 5.0F, -1.5F, 1, 4, 3, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 33, 27, 1.5F, 1.0F, -3.5F, 2, 3, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 0, 34, -3.5F, 2.0F, 2.5F, 7, 8, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 17, 34, -6.0F, 10.0F, -2.5F, 2, 4, 5, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 32, 34, 4.0F, 10.0F, -2.5F, 2, 4, 5, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 0, 44, -2.5F, 10.0F, -3.8F, 5, 4, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 37, 17, -1.5F, 9.0F, -3.5F, 3, 1, 1, 0.0F, false));

	}

	@Override
	public void render(Entity ent, float f2, float f3, float f4, float f5, float f6, float f7) {

		this.setRotationAngles(f2, f3, f4, f5, f6, f7, ent);
		GlStateManager.pushMatrix();

		// showModelをここでいじる
		head2.showModel = this.slot == 0;
		body2.showModel = this.slot != 0;

		if (this.isChild) {
			float f = 2.0F;
			GlStateManager.scale(1.5F / f, 1.5F / f, 1.5F / f);
			GlStateManager.translate(0.0F, 16.0F * f7, 0.0F);
			this.head2.render(f7);
			this.body2.render(f7);
		} else {
			if (ent.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}

			this.head2.render(f7);
			this.body2.render(f7);
		}

		GlStateManager.popMatrix();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		setAngle(head2, this.bipedHead);
		setAngle(body2, this.bipedBody);
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
		this.head2.showModel = invisible;
		this.body2.showModel = invisible;
	}

	@Override
	public void postRenderArm(float scale, EnumHandSide side) {
		this.getArmForSide(side).postRender(scale);
	}

	@Override
	protected ModelRenderer getArmForSide(EnumHandSide side) {
		return side == EnumHandSide.LEFT ? this.bipedLeftArm : this.bipedRightArm;
	}

	@Override
	protected EnumHandSide getMainHand(Entity entityIn) {
		return entityIn instanceof EntityLivingBase ? ((EntityLivingBase) entityIn).getPrimaryHand() :
				EnumHandSide.RIGHT;
	}
}
