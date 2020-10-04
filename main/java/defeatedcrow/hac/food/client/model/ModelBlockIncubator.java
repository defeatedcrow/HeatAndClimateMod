package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockIncubator extends DCTileModelBase {

	private final ModelRenderer bone;
	private final ModelRenderer bb_main;

	private final ModelRenderer heat;

	public ModelBlockIncubator() {

		textureWidth = 128;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(8.0F, 16.0F, -7.0F);
		setRotation(bone, 0.0F, -0.5236F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 18, -16.0F, -6.0F, -1.1F, 16, 12, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 38, 20, -7.0F, -5.0F, -2.0F, 6, 5, 1, 0.0F, false));

		heat = new ModelRenderer(this, 0, 0);
		heat.addBox(-6.0F, -4.5F, -2.5F, 4, 1, 1, 0F);
		heat.setRotationPoint(8.0F, 16.0F, -7.0F);
		setRotation(heat, 0F, 0F, 0F);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -2.0F, -6.0F, 16, 2, 14, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 64, 0, -8.0F, -3.0F, -7.0F, 16, 1, 15, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 64, 0, -8.0F, -14.0F, -7.0F, 16, 1, 15, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 96, 18, 7.0F, -13.0F, -7.0F, 1, 10, 15, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 64, 18, -8.0F, -13.0F, -7.0F, 1, 10, 15, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 64, 46, -7.0F, -13.0F, 5.0F, 14, 10, 3, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 46, -7.0F, -8.0F, -5.0F, 14, 1, 10, 0.0F, false));
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	public void renderPanel(float scale) {
		heat.render(scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bone.render(scale);
		bb_main.render(scale);
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

	public void setDoorRotation(boolean open) {
		if (open) {
			bone.rotateAngleY = -1.5F;
			heat.rotateAngleY = -1.5F;
		} else {
			bone.rotateAngleY = 0F;
			heat.rotateAngleY = 0F;
		}
	}

}
