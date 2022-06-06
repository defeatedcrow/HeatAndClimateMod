package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMetalChest_E extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer side2_r1;
	private final ModelRenderer top;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;

	public ModelMetalChest_E() {
		textureWidth = 128;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -8.0F, -1.0F, -8.0F, 16, 1, 16, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 18, -8.0F, -9.0F, -8.0F, 16, 8, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 35, 18, -8.0F, -9.0F, 7.0F, 16, 8, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 70, 5, 7.0F, -9.0F, -7.0F, 1, 8, 14, 0.0F, false));

		side2_r1 = new ModelRenderer(this);
		side2_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		main.addChild(side2_r1);
		setRotation(side2_r1, 0.0F, 3.1416F, 0.0F);
		side2_r1.cubeList.add(new ModelBox(side2_r1, 70, 5, 7.0F, -9.0F, -7.0F, 1, 8, 14, 0.0F, false));

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, -1.0F, 7.0F);
		setRotation(top, -0.2618F, 0.0F, 0.0F);
		top.cubeList.add(new ModelBox(top, 0, 43, -8.0F, -7.0F, -10.5F, 16, 1, 7, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 70, 29, 6.8F, -2.0F, -14.5F, 1, 2, 15, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 70, 29, -7.8F, -2.0F, -14.5F, 1, 2, 15, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 71, 30, 6.8F, -4.0F, -14.0F, 1, 2, 14, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 71, 30, -7.8F, -4.0F, -14.0F, 1, 2, 14, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 72, 32, 6.8F, -5.0F, -13.5F, 1, 1, 13, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 72, 32, -7.8F, -5.0F, -13.5F, 1, 1, 13, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 75, 35, 6.8F, -6.0F, -12.1F, 1, 1, 10, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 75, 35, -7.8F, -6.0F, -12.1F, 1, 1, 10, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 0, -2.0F, -1.0F, -16.0F, 4, 2, 1, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, -7.0F);
		top.addChild(cube_r1);
		setRotation(cube_r1, 1.0472F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 35, 37, -8.0F, -0.5F, 6.8F, 16, 4, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, -7.0F);
		top.addChild(cube_r2);
		setRotation(cube_r2, 0.2618F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 35, 30, -8.0F, -3.0F, 7.0F, 16, 5, 1, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, -7.0F);
		top.addChild(cube_r3);
		setRotation(cube_r3, -1.0472F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 37, -8.0F, -0.5F, -7.8F, 16, 4, 1, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, -7.0F);
		top.addChild(cube_r4);
		setRotation(cube_r4, -0.2618F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 30, -8.0F, -3.0F, -8.0F, 16, 5, 1, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		main.render(0.0625F);
		top.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {

		if (f != 0F) {
			top.rotateAngleX = -1.0472F;
		} else {
			top.rotateAngleX = 0F;
		}
	}

}
