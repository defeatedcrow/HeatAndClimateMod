package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockBrewing extends DCTileModelBase {

	private final ModelRenderer pipe;
	private final ModelRenderer body;

	public ModelBlockBrewing() {

		textureWidth = 128;
		textureHeight = 64;

		pipe = new ModelRenderer(this);
		pipe.setRotationPoint(0.0F, 24.0F, 0.0F);
		pipe.cubeList.add(new ModelBox(pipe, 100, 0, -1.0F, -32.0F, -1.0F, 2, 3, 2, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 74, 7, -1.0F, -1.0F, -1.0F, 2, 1, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 70, 6, -2.0F, -2.0F, -2.0F, 4, 1, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 66, 4, -3.0F, -3.0F, -3.0F, 6, 1, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 62, 2, -4.0F, -4.0F, -4.0F, 8, 1, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 58, 0, -5.0F, -5.0F, -5.0F, 10, 1, 10, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -7.0F, -24.0F, -7.0F, 14, 19, 14, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -7.0F, -5.0F, -7.0F, 1, 5, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -7.0F, -5.0F, 6.0F, 1, 5, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, 6.0F, -5.0F, -7.0F, 1, 5, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, 6.0F, -5.0F, 6.0F, 1, 5, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 58, 14, -4.0F, -28.0F, -4.0F, 8, 4, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 88, 18, -5.0F, -29.0F, -5.0F, 10, 1, 10, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 92, 15, -4.5F, -30.0F, -4.5F, 1, 3, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 92, 16, -4.5F, -30.0F, 3.5F, 1, 3, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 92, 16, 3.5F, -30.0F, -4.5F, 1, 3, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 92, 16, 3.5F, -30.0F, 3.5F, 1, 3, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 45, 0, -2.5F, -13.0F, -8.0F, 5, 5, 1, 0.0F, false));
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		body.render(scale);
	}

	public void renderPipe(float scale) {
		pipe.render(scale);
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
