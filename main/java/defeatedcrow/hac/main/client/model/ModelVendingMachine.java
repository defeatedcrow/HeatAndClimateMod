package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelVendingMachine extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer light;
	private final ModelRenderer button1;
	private final ModelRenderer button2;
	private final ModelRenderer button3;
	private final ModelRenderer button4;

	public ModelVendingMachine() {
		textureWidth = 128;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -8.0F, -16.0F, 0.0F, 16, 16, 8, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 49, 6, -8.0F, -16.0F, -0.3F, 16, 16, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 24, -8.0F, -32.0F, 0.0F, 16, 16, 8, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 49, 31, -8.0F, -32.0F, -0.3F, 16, 16, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 84, 1, -6.5F, -18.0F, 0.0F, 13, 2, 6, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 84, 1, -6.5F, -25.0F, 0.0F, 13, 2, 6, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 84, 2, -6.5F, -31.0F, 0.0F, 13, 1, 6, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 84, 10, 6.5F, -31.0F, 0.0F, 1, 15, 6, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 84, 10, -7.5F, -31.0F, 0.0F, 1, 15, 6, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 50, -8.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 50, 5.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 50, -8.0F, 0.0F, 5.0F, 3, 1, 3, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 50, 5.0F, 0.0F, 5.0F, 3, 1, 3, 0.0F, false));

		light = new ModelRenderer(this);
		light.setRotationPoint(0.0F, 8.0F, 0.0F);
		light.cubeList.add(new ModelBox(light, 84, 33, -7.0F, -30.0F, 6.0F, 14, 13, 1, 0.0F, false));

		button1 = new ModelRenderer(this);
		button1.setRotationPoint(0.0F, 8.0F, 0.0F);
		button1.cubeList.add(new ModelBox(button1, 100, 11, -4.0F, -17.2F, -0.5F, 2, 1, 1, 0.0F, false));

		button2 = new ModelRenderer(this);
		button2.setRotationPoint(0.0F, 8.0F, 0.0F);
		button2.cubeList.add(new ModelBox(button2, 100, 11, 2.0F, -17.2F, -0.5F, 2, 1, 1, 0.0F, false));

		button3 = new ModelRenderer(this);
		button3.setRotationPoint(0.0F, 8.0F, 0.0F);
		button3.cubeList.add(new ModelBox(button3, 100, 11, -4.0F, -24.2F, -0.5F, 2, 1, 1, 0.0F, false));

		button4 = new ModelRenderer(this);
		button4.setRotationPoint(0.0F, 8.0F, 0.0F);
		button4.cubeList.add(new ModelBox(button4, 100, 11, 2.0F, -24.2F, -0.5F, 2, 1, 1, 0.0F, false));
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		main.render(0.0625F);
	}

	public void light() {
		light.render(0.0625F);
	}

	public void buttonA() {
		button1.render(0.0625F);
	}

	public void buttonB() {
		button2.render(0.0625F);
	}

	public void buttonC() {
		button3.render(0.0625F);
	}

	public void buttonD() {
		button4.render(0.0625F);
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
