package defeatedcrow.hac.magic.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelOwlDoll extends ModelBase {

	private final ModelRenderer body;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer head;

	public ModelOwlDoll() {
		super();

		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 8.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -3.5F, -10.0F, -3.0F, 7, 9, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 21, 0, -2.0F, -1.0F, -2.0F, 1, 1, 3, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 21, 0, 1.0F, -1.0F, -2.0F, 1, 1, 3, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(cube_r1);
		setRotation(cube_r1, 0.0873F, 0.0F, -0.0873F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 42, 11, 4.0F, -9.0F, -2.0F, 1, 8, 5, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(cube_r2);
		setRotation(cube_r2, 0.0873F, 0.0F, 0.0873F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 29, 11, -5.0F, -9.0F, -2.0F, 1, 8, 5, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -3.0F, 0.0F);
		setRotation(head, 0.0873F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 29, 0, -3.5F, -3.0F, -3.5F, 7, 4, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 50, 3, -1.0F, 0.0F, -4.0F, 2, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 9, 17, -2.5F, -2.0F, -4.5F, 2, 2, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 17, -3.0F, -2.5F, -5.5F, 3, 3, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		body.render(scale);
		head.render(scale);
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
		float p = (float) Math.PI / 180F;
		float fh = netHeadYaw;
		float fp = 180 - headPitch;
		body.rotateAngleY = (180 + fh) * p;
		head.rotateAngleY = (180 + fh) * p;
	}

}
