package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.ModelThinBiped;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkirtSilk extends ModelThinBiped {
	private final ModelRenderer RightLegH;
	private final ModelRenderer LeftLegH;
	private final ModelRenderer BodyH;
	private final ModelRenderer l6_r1;
	private final ModelRenderer l5_r1;
	private final ModelRenderer l4_r1;
	private final ModelRenderer l3_r1;
	private final ModelRenderer l2_r1;
	private final ModelRenderer l1_r1;
	private final ModelRenderer u6_r1;
	private final ModelRenderer u5_r1;
	private final ModelRenderer u4_r1;
	private final ModelRenderer u3_r1;
	private final ModelRenderer u2_r1;
	private final ModelRenderer u1_r1;
	private final ModelRenderer Ribbon;

	public boolean isSneak = false;
	public boolean isBlocking = false;
	public boolean aimedBow = false;
	public int slot;

	public ModelSkirtSilk(int b) {
		this(0.45F, b);
	}

	public ModelSkirtSilk(float f, int b) {
		this(f, 0.0F, 64, 64, b);
	}

	public ModelSkirtSilk(float f1, float f2, int i3, int i4, int s) {
		super(s);
		slot = s;
		textureWidth = i3;
		textureHeight = i4;

		RightLegH = new ModelRenderer(this);
		RightLegH.setRotationPoint(-1.9F, 12.0F, 0.0F);
		setRotationAngle(RightLegH, 0.192F, 0.0F, 0.0349F);
		RightLegH.cubeList.add(new ModelBox(RightLegH, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, f1, false));

		LeftLegH = new ModelRenderer(this);
		LeftLegH.setRotationPoint(1.9F, 12.0F, 0.0F);
		setRotationAngle(LeftLegH, -0.1745F, 0.0F, -0.0349F);
		LeftLegH.cubeList.add(new ModelBox(LeftLegH, 16, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, f1, false));

		BodyH = new ModelRenderer(this);
		BodyH.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyH.cubeList.add(new ModelBox(BodyH, 0, 0, -4.0F, 7.0F, -2.0F, 8, 5, 4, f1, false));

		l6_r1 = new ModelRenderer(this);
		l6_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		BodyH.addChild(l6_r1);
		setRotationAngle(l6_r1, 0.5236F, 0.5236F, 0.0F);
		l6_r1.cubeList.add(new ModelBox(l6_r1, 27, 0, -2.5F, -14.5F, 0F, 4, 1, 7, f1, false));

		l5_r1 = new ModelRenderer(this);
		l5_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		BodyH.addChild(l5_r1);
		setRotationAngle(l5_r1, 0.5236F, 1.5708F, 0.0F);
		l5_r1.cubeList.add(new ModelBox(l5_r1, 27, 0, -2.0F, -14.5F, 0F, 4, 1, 7, f1, false));

		l4_r1 = new ModelRenderer(this);
		l4_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		BodyH.addChild(l4_r1);
		setRotationAngle(l4_r1, 0.5236F, 2.618F, 0.0F);
		l4_r1.cubeList.add(new ModelBox(l4_r1, 27, 0, -2.0F, -14.5F, 0F, 4, 1, 7, f1, false));

		l3_r1 = new ModelRenderer(this);
		l3_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		BodyH.addChild(l3_r1);
		setRotationAngle(l3_r1, 0.5236F, -2.618F, 0.0F);
		l3_r1.cubeList.add(new ModelBox(l3_r1, 27, 0, -2.0F, -14.5F, 0F, 4, 1, 7, f1, false));

		l2_r1 = new ModelRenderer(this);
		l2_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		BodyH.addChild(l2_r1);
		setRotationAngle(l2_r1, 0.5236F, -1.5708F, 0.0F);
		l2_r1.cubeList.add(new ModelBox(l2_r1, 27, 0, -2.0F, -14.5F, 0F, 4, 1, 7, f1, false));

		l1_r1 = new ModelRenderer(this);
		l1_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		BodyH.addChild(l1_r1);
		setRotationAngle(l1_r1, 0.5236F, -0.5236F, 0.0F);
		l1_r1.cubeList.add(new ModelBox(l1_r1, 27, 0, -1.5F, -14.5F, 0F, 4, 1, 7, f1, false));

		u6_r1 = new ModelRenderer(this);
		u6_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		BodyH.addChild(u6_r1);
		setRotationAngle(u6_r1, 0.5236F, 1.0472F, 0.0F);
		u6_r1.cubeList.add(new ModelBox(u6_r1, 28, 9, -2.0F, -14.0F, 0F, 4, 1, 6, f1, false));

		u5_r1 = new ModelRenderer(this);
		u5_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		BodyH.addChild(u5_r1);
		setRotationAngle(u5_r1, 0.5236F, 2.0944F, 0.0F);
		u5_r1.cubeList.add(new ModelBox(u5_r1, 28, 9, -2.0F, -14.0F, 0F, 4, 1, 6, f1, false));

		u4_r1 = new ModelRenderer(this);
		u4_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		BodyH.addChild(u4_r1);
		setRotationAngle(u4_r1, 0.5236F, 3.1416F, 0.0F);
		u4_r1.cubeList.add(new ModelBox(u4_r1, 28, 9, -2.0F, -14.0F, 0F, 4, 1, 6, f1, false));

		u3_r1 = new ModelRenderer(this);
		u3_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		BodyH.addChild(u3_r1);
		setRotationAngle(u3_r1, 0.5236F, -2.0944F, 0.0F);
		u3_r1.cubeList.add(new ModelBox(u3_r1, 28, 9, -2.0F, -14.0F, 0F, 4, 1, 6, f1, false));

		u2_r1 = new ModelRenderer(this);
		u2_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		BodyH.addChild(u2_r1);
		setRotationAngle(u2_r1, 0.5236F, -1.0472F, 0.0F);
		u2_r1.cubeList.add(new ModelBox(u2_r1, 28, 9, -2.0F, -14.0F, 0F, 4, 1, 6, f1, false));

		u1_r1 = new ModelRenderer(this);
		u1_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		BodyH.addChild(u1_r1);
		setRotationAngle(u1_r1, 0.5236F, 0.0F, 0.0F);
		u1_r1.cubeList.add(new ModelBox(u1_r1, 28, 9, -2.0F, -14.0F, 0F, 4, 1, 6, f1, false));

		Ribbon = new ModelRenderer(this);
		Ribbon.setRotationPoint(0.0F, 0.0F, 0.0F);
		Ribbon.cubeList.add(new ModelBox(Ribbon, 51, 0, -2.0F, 8.0F, 2.0F, 4, 3, 2, f1, false));
		Ribbon.cubeList.add(new ModelBox(Ribbon, 51, 7, 1.0F, 7.0F, 2.0F, 3, 3, 1, f1, false));
		Ribbon.cubeList.add(new ModelBox(Ribbon, 51, 12, -4.0F, 7.0F, 2.0F, 3, 3, 1, f1, false));
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void render(Entity ent, float f2, float f3, float f4, float f5, float f6, float f7) {
		this.setRotationAngles(f2, f3, f4, f5, f6, f7, ent);
		GlStateManager.pushMatrix();

		// showModelをここでいじる
		BodyH.showModel = true;
		RightLegH.showModel = true;
		LeftLegH.showModel = true;
		Ribbon.showModel = true;

		if (this.isChild) {
			float f = 2.0F;
			GlStateManager.scale(1.5F / f, 1.5F / f, 1.5F / f);
			GlStateManager.translate(0.0F, 16.0F * f7, 0.0F);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(1.0F / f, 1.0F / f, 1.0F / f);
			GlStateManager.translate(0.0F, 24.0F * f7, 0.0F);
			this.BodyH.render(f7);
			this.RightLegH.render(f7);
			this.LeftLegH.render(f7);
			this.Ribbon.render(f7);
		} else {
			if (ent.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}
			this.BodyH.render(f7);
			this.RightLegH.render(f7);
			this.LeftLegH.render(f7);
			this.Ribbon.render(f7);
		}

		GlStateManager.popMatrix();
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		setAngle(BodyH, this.body);
		setAngle(Ribbon, this.body);
		setAngle(RightLegH, this.rightLeg);
		setAngle(LeftLegH, this.leftLeg);

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
		this.BodyH.showModel = invisible;
		RightLegH.showModel = invisible;
		LeftLegH.showModel = invisible;
		Ribbon.showModel = invisible;
	}
}
