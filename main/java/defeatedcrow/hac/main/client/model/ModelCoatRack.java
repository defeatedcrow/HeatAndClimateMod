package defeatedcrow.hac.main.client.model;

import net.minecraft.client.model.ModelArmorStandArmor;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCoatRack extends ModelArmorStandArmor {
	private final ModelRenderer bb_main;

	public ModelCoatRack(float f) {
		this();
	}

	public ModelCoatRack() {
		super(0F, 32, 64);

		this.bipedHead.showModel = false;
		this.bipedBody.showModel = false;
		this.bipedRightArm.showModel = false;
		this.bipedLeftArm.showModel = false;
		this.bipedRightLeg.showModel = false;
		this.bipedLeftLeg.showModel = false;
		this.bipedHeadwear.showModel = false;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, -1.0F, -1.0F, 8, 1, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 10, -0.5F, -32.0F, 2.5F, 1, 31, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 5, 10, -0.5F, -31.0F, -0.5F, 1, 1, 3, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 5, 10, -0.5F, -27.0F, -0.5F, 1, 1, 3, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 5, 15, -6.0F, -28.0F, 0.0F, 12, 8, 0, 0.0F, false));
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

		if (entityIn instanceof EntityArmorStand) {
			EntityArmorStand entityarmorstand = (EntityArmorStand) entityIn;
			this.bb_main.rotateAngleX = 0.017453292F * entityarmorstand.getBodyRotation().getX();
			this.bb_main.rotateAngleY = 0.017453292F * entityarmorstand.getBodyRotation().getY();
			this.bb_main.rotateAngleZ = 0.017453292F * entityarmorstand.getBodyRotation().getZ();
		}
	}

	@Override
	public void render(Entity entity, float f0, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f0, f1, f2, f3, f4, f5);
		GlStateManager.pushMatrix();

		if (this.isChild) {
			float f = 2.0F;
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * f5, 0.0F);
			this.bb_main.render(f5);
		} else {
			if (entity.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}
			this.bb_main.render(f5);
		}

		GlStateManager.popMatrix();
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void postRenderArm(float scale, EnumHandSide side) {
		ModelRenderer modelrenderer = this.getArmForSide(side);
		boolean flag = modelrenderer.showModel;
		modelrenderer.showModel = true;
		super.postRenderArm(scale, side);
		modelrenderer.showModel = flag;
	}
}
