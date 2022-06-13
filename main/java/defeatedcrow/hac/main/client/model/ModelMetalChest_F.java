package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMetalChest_F extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer top1;
	private final ModelRenderer top2;

	public ModelMetalChest_F() {
		textureWidth = 128;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -8.0F, -1.0F, -8.0F, 16, 1, 16, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 18, -7.5F, -15.0F, -7.5F, 15, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 33, 18, -7.5F, -15.0F, 6.5F, 15, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 34, 6.5F, -15.0F, -6.5F, 1, 14, 13, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 29, 34, -7.5F, -15.0F, -6.5F, 1, 14, 13, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 51, 0, -8.0F, -16.0F, -8.0F, 16, 1, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 51, 0, -8.0F, -16.0F, 7.0F, 16, 1, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 66, 3, 7.0F, -16.0F, -7.0F, 1, 1, 14, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 66, 3, -8.0F, -16.0F, -7.0F, 1, 1, 14, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 63, 46, 7.0F, -15.0F, -8.0F, 1, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 58, 46, -8.0F, -15.0F, -8.0F, 1, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 73, 46, -8.0F, -15.0F, 7.0F, 1, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 68, 46, 7.0F, -15.0F, 7.0F, 1, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 78, 46, 4.0F, -15.0F, -8.0F, 1, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 78, 46, -5.0F, -15.0F, -8.0F, 1, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 88, 46, -8.0F, -15.0F, -5.0F, 1, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 88, 46, -8.0F, -15.0F, 4.0F, 1, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 93, 46, -5.0F, -15.0F, 7.0F, 1, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 93, 46, 4.0F, -15.0F, 7.0F, 1, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 83, 46, 7.0F, -15.0F, 4.0F, 1, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 83, 46, 7.0F, -15.0F, -5.0F, 1, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 66, 20, -7.0F, -13.0F, -7.9F, 14, 1, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 66, 20, -7.0F, -13.0F, 6.9F, 14, 1, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 66, 23, 6.9F, -13.0F, -7.0F, 1, 1, 14, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 66, 23, -7.9F, -13.0F, -7.0F, 1, 1, 14, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 72, 23, 6.9F, -15.0F, -0.5F, 1, 2, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 72, 27, -7.9F, -15.0F, -0.5F, 1, 2, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 67, 23, -0.5F, -15.0F, -7.9F, 1, 2, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 67, 27, -0.5F, -15.0F, 6.9F, 1, 2, 1, 0.0F, false));

		top1 = new ModelRenderer(this);
		top1.setRotationPoint(0.0F, -7.0F, 7.0F);
		setRotation(top1, -0.3491F, 0.0F, 0.0F);
		top1.cubeList.add(new ModelBox(top1, 84, 7, -7.0F, -1.0F, -7.0F, 14, 1, 7, 0.0F, false));

		top2 = new ModelRenderer(this);
		top2.setRotationPoint(0.0F, -7.0F, -7.0F);
		setRotation(top2, 0.3491F, 0.0F, 0.0F);
		top2.cubeList.add(new ModelBox(top2, 83, 23, -7.0F, -1.0F, 0.0F, 14, 1, 7, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		main.render(0.0625F);
		top1.render(0.0625F);
		top2.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {

		if (f != 0F) {
			top1.rotateAngleX = -0.5236F;
			top2.rotateAngleX = 0.5236F;
		} else {
			top1.rotateAngleX = 0F;
			top2.rotateAngleX = 0F;
		}
	}

}
