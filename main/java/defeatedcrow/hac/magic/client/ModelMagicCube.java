package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMagicCube extends DCTileModelBase {

	private final ModelRenderer main;
	private final ModelRenderer cube_r1;
	private final ModelRenderer in1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer in2;
	private final ModelRenderer cube_r3;

	public ModelMagicCube() {

		textureWidth = 32;
		textureHeight = 16;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 0.0F, 0.0F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -8.0F, 0.0F);
		main.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.7854F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, false));

		in1 = new ModelRenderer(this);
		in1.setRotationPoint(0.0F, 0.0F, 0.0F);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, -8.0F, 0.0F);
		in1.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.7854F, 0.0F, 0.7854F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 12, 6, -2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F, false));

		in2 = new ModelRenderer(this);
		in2.setRotationPoint(0.0F, 0.0F, 0.0F);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, -8.0F, 0.0F);
		in2.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.7854F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 10, -1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F, false));
	}

	@Override
	public void render(float rot) {
		render(null, rot, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		main.render(scale);
		in1.render(scale);
		in2.render(scale);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		float rot = limbSwing;
		float f2 = (float) (rot * Math.PI / 180F);// f * 0.01745329F;

		main.rotateAngleY = -f2;
		in1.rotateAngleY = f2;
		in2.rotateAngleY = f2;
	}

}
