package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelLampGas extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer side1;
	private final ModelRenderer side2;
	private final ModelRenderer side3;
	private final ModelRenderer side4;
	private final ModelRenderer side5;
	private final ModelRenderer side6;
	private final ModelRenderer side7;
	private final ModelRenderer side8;
	private final ModelRenderer light;
	private final ModelRenderer bar;

	public ModelLampGas() {
		textureWidth = 64;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 24.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 37, 5, -2.0F, -2.0F, -2.0F, 4, 2, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 40, -4.0F, -3.0F, -4.0F, 8, 1, 8, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 0, -6.0F, -15.0F, -6.0F, 12, 1, 12, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 14, -4.0F, -16.0F, -4.0F, 8, 1, 8, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 28, 15, -2.0F, -18.0F, -2.0F, 4, 2, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 37, 0, -0.5F, -6.0F, -0.5F, 1, 3, 1, 0.0F, false));

		side1 = new ModelRenderer(this);
		side1.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(side1, 0.1745F, 0.0F, 0.1745F);
		side1.cubeList.add(new ModelBox(side1, 0, 24, 3.0F, -14.0F, -4.0F, 1, 14, 1, 0.0F, false));

		side2 = new ModelRenderer(this);
		side2.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(side2, 0.1745F, 0.0F, -0.1745F);
		side2.cubeList.add(new ModelBox(side2, 5, 24, -4.0F, -14.0F, -4.0F, 1, 14, 1, 0.0F, false));

		side3 = new ModelRenderer(this);
		side3.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(side3, -0.1745F, 0.0F, -0.1745F);
		side3.cubeList.add(new ModelBox(side3, 10, 24, -4.0F, -14.0F, 3.0F, 1, 14, 1, 0.0F, false));

		side4 = new ModelRenderer(this);
		side4.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(side4, -0.1745F, 0.0F, 0.1745F);
		side4.cubeList.add(new ModelBox(side4, 15, 24, 3.0F, -14.0F, 3.0F, 1, 14, 1, 0.0F, false));

		side5 = new ModelRenderer(this);
		side5.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(side5, 0.1745F, 0.0F, 0.0F);
		side5.cubeList.add(new ModelBox(side5, 23, 31, -4.0F, -6.5F, -3.5F, 8, 6, 0, 0.0F, false));
		side5.cubeList.add(new ModelBox(side5, 21, 24, -5.0F, -12.5F, -3.5F, 10, 6, 0, 0.0F, false));

		side6 = new ModelRenderer(this);
		side6.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(side6, 0.1745F, -1.5708F, 0.0F);
		side6.cubeList.add(new ModelBox(side6, 23, 31, -4.0F, -6.5F, -3.5F, 8, 6, 0, 0.0F, false));
		side6.cubeList.add(new ModelBox(side6, 21, 24, -5.0F, -12.5F, -3.5F, 10, 6, 0, 0.0F, false));

		side7 = new ModelRenderer(this);
		side7.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(side7, 0.1745F, 3.1416F, 0.0F);
		side7.cubeList.add(new ModelBox(side7, 23, 31, -4.0F, -6.5F, -3.5F, 8, 6, 0, 0.0F, false));
		side7.cubeList.add(new ModelBox(side7, 21, 24, -5.0F, -12.5F, -3.5F, 10, 6, 0, 0.0F, false));

		side8 = new ModelRenderer(this);
		side8.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(side8, 0.1745F, 1.5708F, 0.0F);
		side8.cubeList.add(new ModelBox(side8, 23, 31, -4.0F, -6.5F, -3.5F, 8, 6, 0, 0.0F, false));
		side8.cubeList.add(new ModelBox(side8, 21, 24, -5.0F, -12.5F, -3.5F, 10, 6, 0, 0.0F, false));

		light = new ModelRenderer(this);
		light.setRotationPoint(0.0F, 24.0F, 0.0F);
		light.cubeList.add(new ModelBox(light, 46, 14, -2.0F, -12.0F, -2.0F, 4, 6, 4, 0.0F, false));

		bar = new ModelRenderer(this);
		bar.setRotationPoint(0.0F, 24.0F, 0.0F);
		bar.cubeList.add(new ModelBox(bar, 34, 35, -0.5F, -0.5F, -6.0F, 1, 1, 13, 0.0F, false));
		bar.cubeList.add(new ModelBox(bar, 34, 44, -1.0F, -1.0F, 7.0F, 2, 2, 1, 0.0F, false));
		bar.cubeList.add(new ModelBox(bar, 34, 38, 0.5F, 0.5F, -5.0F, 0, 6, 13, 0.0F, false));
	}

	@Override
	public void render(float f) {
		main.render(0.0625F);
		side1.render(0.0625F);
		side2.render(0.0625F);
		side3.render(0.0625F);
		side4.render(0.0625F);
	}

	public void renderLamp() {
		light.render(0.0625F);
	}

	public void renderBar() {
		bar.render(0.0625F);
	}

	public void renderGlass() {
		side5.render(0.0625F);
		side6.render(0.0625F);
		side7.render(0.0625F);
		side8.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {}

}
