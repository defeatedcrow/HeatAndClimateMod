package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelLampCarbide extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer lamp;
	private final ModelRenderer glass;
	private final ModelRenderer bar;

	public ModelLampCarbide() {
		textureWidth = 64;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 24.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -4.0F, -1.0F, -4.0F, 8, 1, 8, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 33, 0, -3.5F, -4.0F, -3.5F, 7, 3, 7, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 10, -2.5F, -5.0F, -2.5F, 5, 1, 5, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 17, -3.5F, -6.0F, -3.5F, 7, 1, 7, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 26, -3.5F, -14.0F, -3.5F, 7, 1, 7, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 23, 11, -2.5F, -16.0F, -2.5F, 5, 2, 5, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 45, 14, -1.0F, -17.0F, -1.0F, 2, 2, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 36, -6.0F, -19.0F, 0.0F, 12, 15, 0, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 25, 36, -4.0F, -13.0F, -4.0F, 8, 7, 8, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 0, -1.0F, -7.0F, -0.5F, 2, 1, 1, 0.0F, false));

		lamp = new ModelRenderer(this);
		lamp.setRotationPoint(0.0F, 24.0F, 0.0F);
		lamp.cubeList.add(new ModelBox(lamp, 29, 19, -1.0F, -9.5F, -1.0F, 2, 2, 2, 0.0F, false));

		glass = new ModelRenderer(this);
		glass.setRotationPoint(0.0F, 24.0F, 0.0F);
		glass.cubeList.add(new ModelBox(glass, 32, 20, -3.0F, -13.0F, -3.0F, 6, 7, 6, 0.0F, false));

		bar = new ModelRenderer(this);
		bar.setRotationPoint(0.0F, 24.0F, 0.0F);
		bar.cubeList.add(new ModelBox(bar, 0, 52, -0.5F, -18.0F, -2.0F, 1, 1, 9, 0.0F, false));
		bar.cubeList.add(new ModelBox(bar, 0, 56, -1.0F, -18.5F, 7.0F, 2, 2, 1, 0.0F, false));
	}

	@Override
	public void render(float f) {
		main.render(0.0625F);
	}

	public void renderLamp() {
		lamp.render(0.0625F);
	}

	public void renderGlass() {
		glass.render(0.0625F);
	}

	public void renderBar() {
		bar.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {}

}
