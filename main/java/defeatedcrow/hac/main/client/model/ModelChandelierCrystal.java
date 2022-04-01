package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelChandelierCrystal extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer part;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer light;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer light2;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;

	public ModelChandelierCrystal() {
		textureWidth = 64;
		textureHeight = 32;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 36, 0, -1.0F, -16.0F, -1.0F, 2, 2, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 22, 1, -2.0F, -10.0F, -2.0F, 4, 1, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 59, 3, -1.0F, -14.0F, 0.0F, 2, 10, 0, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 59, 1, 0.0F, -14.0F, -1.0F, 0, 10, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 0, -3.5F, -4.0F, -3.5F, 7, 1, 7, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 8, -3.0F, -3.0F, -3.0F, 6, 2, 6, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 16, -2.5F, -1.5F, -2.5F, 5, 1, 5, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 22, -2.0F, -1.0F, -2.0F, 4, 1, 4, 0.0F, false));

		part = new ModelRenderer(this);
		part.setRotationPoint(0.0F, 8.0F, 0.0F);
		part.cubeList.add(new ModelBox(part, 34, 7, -14.0F, -7.0F, 0.0F, 12, 6, 0, 0.0F, false));
		part.cubeList.add(new ModelBox(part, 30, 19, -13.0F, -7.0F, -2.0F, 4, 0, 4, 0.0F, false));
		part.cubeList.add(new ModelBox(part, 43, 19, -12.0F, -8.0F, -1.0F, 2, 1, 2, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -10.0F, 0.0F);
		part.addChild(cube_r1);
		setRotation(cube_r1, 0.0F, -0.4363F, -0.3491F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, -1, 30, -12.0F, 0.0F, -0.5F, 10, 0, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		part.addChild(cube_r2);
		setRotation(cube_r2, 0.0F, -1.5708F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, -1, 28, -5.0F, -6.5F, 10.0F, 10, 0, 1, 0.0F, false));

		light = new ModelRenderer(this);
		light.setRotationPoint(0.0F, 8.0F, 0.0F);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 3.0F, 0.0F);
		light.addChild(cube_r3);
		setRotation(cube_r3, -0.7854F, -1.5708F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 39, 14, -0.5F, 0.0F, 0.0F, 1, 2, 2, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 3.0F, 0.0F);
		light.addChild(cube_r4);
		setRotation(cube_r4, -0.7854F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 39, 14, -0.5F, 0.0F, 0.0F, 1, 2, 2, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 1.5F, 0.0F);
		light.addChild(cube_r5);
		setRotation(cube_r5, -0.7854F, -1.5708F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 34, 14, -0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
		light.addChild(cube_r6);
		setRotation(cube_r6, -0.7854F, 0.0F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 34, 14, -0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F, false));

		light2 = new ModelRenderer(this);
		light2.setRotationPoint(0.0F, 8.0F, 0.0F);
		light2.cubeList.add(new ModelBox(light2, 24, 25, -12.0F, -11.5F, -1.0F, 2, 3, 2, 0.0F, false));
		light2.cubeList.add(new ModelBox(light2, 44, 25, -12.5F, -12.0F, -1.0F, 3, 4, 2, 0.0F, false));
		light2.cubeList.add(new ModelBox(light2, 33, 24, -12.0F, -12.0F, -1.5F, 2, 4, 3, 0.0F, false));
		light2.cubeList.add(new ModelBox(light2, 46, 15, -9.0F, -1.0F, 0.0F, 8, 3, 0, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(-9.0F, -1.0F, 0.0F);
		light2.addChild(cube_r7);
		setRotation(cube_r7, -0.7854F, 0.0F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 39, 14, -2.0F, 1.0F, 1.0F, 1, 2, 2, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(-9.0F, -1.0F, 0.0F);
		light2.addChild(cube_r8);
		setRotation(cube_r8, -0.7854F, -1.5708F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 39, 14, -0.5F, 0.0F, 2.0F, 1, 2, 2, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(-9.0F, -2.5F, 0.0F);
		light2.addChild(cube_r9);
		setRotation(cube_r9, -0.7854F, -1.5708F, 0.0F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 34, 14, -0.5F, 0.0F, 2.0F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		main.render(0.0625F);
	}

	public void renderPart() {
		part.render(0.0625F);
	}

	public void renderLight() {
		light.render(0.0625F);
	}

	public void renderLightPart() {
		light2.render(0.0625F);
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
