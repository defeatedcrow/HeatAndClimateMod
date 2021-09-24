package defeatedcrow.hac.magic.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCrowDoll extends ModelBase {

	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer arrow;
	private final ModelRenderer cube_r1;

	public ModelCrowDoll(boolean baked) {
		super();

		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 22.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -4.0F, -4.0F, -4.0F, 8, 4, 9, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 35, 5, -3.5F, -5.0F, -4.0F, 7, 1, 7, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 14, -3.0F, -7.0F, -3.5F, 6, 2, 5, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 23, 15, -2.5F, -9.0F, -3.0F, 5, 2, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 49, 17, -1.5F, -3.0F, 4.0F, 3, 1, 3, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 42, 19, -1.0F, -7.0F, -4.5F, 2, 1, 1, 0.0F, false));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-2.0F, 21.0F, 0.0F);
		leg1.cubeList.add(new ModelBox(leg1, 27, 0, -1.0F, 0.0F, -0.5F, 1, 3, 1, 0.0F, false));
		leg1.cubeList.add(new ModelBox(leg1, 32, 2, -1.0F, 2.0F, -1.5F, 1, 1, 1, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(3.0F, 21.0F, 0.0F);
		leg2.cubeList.add(new ModelBox(leg2, 27, 0, -1.0F, 0.0F, -0.5F, 1, 3, 1, 0.0F, false));
		leg2.cubeList.add(new ModelBox(leg2, 32, 2, -1.0F, 2.0F, -1.5F, 1, 1, 1, 0.0F, false));

		arrow = new ModelRenderer(this);
		arrow.setRotationPoint(0.0F, 17.0F, 0.0F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		arrow.addChild(cube_r1);
		setRotation(cube_r1, -0.3491F, 0.0F, -0.7854F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 22, 1.0F, -3.0F, 0.0F, 10, 5, 0, 0.0F, false));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		body.render(scale);
		leg1.render(scale);
		leg2.render(scale);
		arrow.render(scale);
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

}
