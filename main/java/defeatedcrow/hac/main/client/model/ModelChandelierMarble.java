package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelChandelierMarble extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer flower;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;

	public ModelChandelierMarble() {
		textureWidth = 64;
		textureHeight = 32;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -3.0F, -16.0F, -3.0F, 6, 2, 6, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 9, -2.0F, -14.0F, -2.0F, 4, 1, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 18, 9, -0.5F, -13.0F, -0.5F, 1, 1, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 16, 13, -1.0F, -12.0F, -1.0F, 2, 2, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 18, 9, -0.5F, -10.0F, -0.5F, 1, 1, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 15, -2.0F, -9.0F, -2.0F, 4, 2, 4, 0.0F, false));

		flower = new ModelRenderer(this);
		flower.setRotationPoint(0.0F, -2.0F, -2.0F);
		setRotation(flower, -0.3491F, 0.0F, 0.0F);
		flower.cubeList.add(new ModelBox(flower, 0, 16, 0.0F, -2.0F, -7.0F, 0, 5, 8, 0.0F, false));
		flower.cubeList.add(new ModelBox(flower, 27, 1, -1.5F, 2.0F, -8.0F, 3, 1, 3, 0.0F, false));
		flower.cubeList.add(new ModelBox(flower, 41, 1, -1.0F, 3.0F, -7.5F, 2, 2, 2, 0.0F, false));
		flower.cubeList.add(new ModelBox(flower, 36, 9, -1.5F, 3.0F, -9.0F, 3, 3, 1, 0.0F, false));
		flower.cubeList.add(new ModelBox(flower, 54, 9, -1.5F, 3.0F, -5.0F, 3, 3, 1, 0.0F, false));
		flower.cubeList.add(new ModelBox(flower, 27, 7, 1.5F, 3.0F, -8.0F, 1, 3, 3, 0.0F, false));
		flower.cubeList.add(new ModelBox(flower, 45, 7, -2.5F, 3.0F, -8.0F, 1, 3, 3, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 2.0F, -6.5F);
		flower.addChild(cube_r1);
		setRotation(cube_r1, 0.8727F, 0.7854F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 45, 18, -1.5F, 4.0F, -1.5F, 3, 2, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 2.0F, -6.5F);
		flower.addChild(cube_r2);
		setRotation(cube_r2, 0.8727F, -0.7854F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 27, 18, -1.5F, 4.0F, -1.5F, 3, 2, 1, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 2.0F, -6.5F);
		flower.addChild(cube_r3);
		setRotation(cube_r3, -0.8727F, -0.7854F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 45, 14, -1.5F, 4.0F, 0.5F, 3, 2, 1, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 2.0F, -6.5F);
		flower.addChild(cube_r4);
		setRotation(cube_r4, -0.8727F, 0.7854F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 27, 14, -1.5F, 4.0F, 0.5F, 3, 2, 1, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 2.0F, -6.5F);
		flower.addChild(cube_r5);
		setRotation(cube_r5, 0.0F, 0.0F, -0.7854F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 36, 22, -1.5F, 4.0F, -1.5F, 1, 2, 3, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 2.0F, -6.5F);
		flower.addChild(cube_r6);
		setRotation(cube_r6, 0.0F, 0.0F, 0.7854F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 27, 22, 0.5F, 4.0F, -1.5F, 1, 2, 3, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 2.0F, -6.5F);
		flower.addChild(cube_r7);
		setRotation(cube_r7, 0.7854F, 0.0F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 36, 18, -1.5F, 4.0F, -1.5F, 3, 2, 1, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, 2.0F, -6.5F);
		flower.addChild(cube_r8);
		setRotation(cube_r8, -0.7854F, 0.0F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 36, 14, -1.5F, 4.0F, 0.5F, 3, 2, 1, 0.0F, false));
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		main.render(0.0625F);
	}

	public void renderFlower() {
		flower.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
	}

}
