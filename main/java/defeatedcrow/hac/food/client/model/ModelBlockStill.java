package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockStill extends DCTileModelBase {

	private final ModelRenderer base;
	private final ModelRenderer side;
	private final ModelRenderer cap;

	public ModelBlockStill() {

		textureWidth = 128;
		textureHeight = 64;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -8.0F, -2.0F, -8.0F, 16, 2, 16, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 20, -7.0F, -6.0F, -7.0F, 14, 4, 14, 0.0F, true));
		base.cubeList.add(new ModelBox(base, 0, 41, -6.0F, -7.0F, -6.0F, 12, 1, 12, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 45, 21, -5.0F, -8.0F, -5.0F, 10, 1, 10, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 38, 40, -3.0F, -12.0F, -3.0F, 6, 4, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 58, 36, -2.5F, -14.0F, -2.5F, 5, 2, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 52, 54, -1.5F, -19.0F, -1.5F, 3, 5, 3, 0.0F, false));

		side = new ModelRenderer(this);
		side.setRotationPoint(0.0F, 7.0F, 0.0F);
		setRotationAngle(side, 0.5236F, -1.5708F, 0.0F);
		side.cubeList.add(new ModelBox(side, 82, 0, -1.0F, -2.0F, -12.0F, 2, 2, 12, 0.0F, false));

		cap = new ModelRenderer(this);
		cap.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(cap, -0.7854F, 0.0F, 0.0F);
		cap.cubeList.add(new ModelBox(cap, 83, 18, -1.5F, -3.0F, -10.0F, 3, 3, 2, 0.0F, false));
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		base.render(scale);
		side.render(scale);
		cap.render(scale);
	}

	private void setRotationAngle(ModelRenderer model, float x, float y, float z) {
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
