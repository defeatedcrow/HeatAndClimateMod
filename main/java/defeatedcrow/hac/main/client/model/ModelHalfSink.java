package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHalfSink extends DCTileModelBase {
	// fields
	private final ModelRenderer body;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;

	public ModelHalfSink() {
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 8.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 17, -8.0F, -4.0F, -8.0F, 16, 4, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -7.0F, -1.0F, -7.0F, 14, 1, 14, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(cube_r1);
		setRotation(cube_r1, 0.0F, 1.5708F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 34, 17, -7.0F, -4.0F, -8.0F, 14, 4, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(cube_r2);
		setRotation(cube_r2, 0.0F, -1.5708F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 34, 17, -7.0F, -4.0F, -8.0F, 14, 4, 1, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(cube_r3);
		setRotation(cube_r3, 0.0F, 3.1416F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 17, -8.0F, -4.0F, -8.0F, 16, 4, 1, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		body.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {}

}
