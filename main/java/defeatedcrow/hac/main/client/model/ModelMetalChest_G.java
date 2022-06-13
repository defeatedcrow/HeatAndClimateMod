package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMetalChest_G extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer top1;

	public ModelMetalChest_G() {
		textureWidth = 128;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -8.0F, -1.0F, -8.0F, 16, 1, 16, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 18, -7.5F, -15.0F, -7.5F, 15, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 18, -7.5F, -15.0F, 6.5F, 15, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 34, 6.5F, -15.0F, -6.5F, 1, 14, 13, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 34, -7.5F, -15.0F, -6.5F, 1, 14, 13, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 29, 34, 6.0F, -14.0F, -8.0F, 2, 13, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 47, 34, -8.0F, -14.0F, -8.0F, 2, 13, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 56, 34, -8.0F, -14.0F, 6.0F, 2, 13, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 38, 34, 6.0F, -14.0F, 6.0F, 2, 13, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 29, 50, 2.0F, -12.0F, -7.8F, 2, 11, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 29, 50, -4.0F, -12.0F, -7.8F, 2, 11, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 50, 50, -7.8F, -12.0F, -4.0F, 1, 11, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 50, 50, -7.8F, -12.0F, 2.0F, 1, 11, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 36, 50, -4.0F, -12.0F, 6.8F, 2, 11, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 36, 50, 2.0F, -12.0F, 6.8F, 2, 11, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 43, 50, 6.8F, -12.0F, 2.0F, 1, 11, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 43, 50, 6.8F, -12.0F, -4.0F, 1, 11, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 86, 0, -6.0F, -14.0F, -7.8F, 12, 2, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 86, 0, -6.0F, -14.0F, 6.8F, 12, 2, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 99, 5, 6.8F, -14.0F, -6.0F, 1, 2, 12, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 99, 5, -7.8F, -14.0F, -6.0F, 1, 2, 12, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 1, 1, 3.0F, -16.3F, 6.3F, 2, 3, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 1, 1, -5.0F, -16.3F, 6.3F, 2, 3, 2, 0.0F, false));

		top1 = new ModelRenderer(this);
		top1.setRotationPoint(0.0F, -7.0F, 7.0F);
		setRotation(top1, -0.5236F, 0.0F, 0.0F);
		top1.cubeList.add(new ModelBox(top1, 71, 47, -7.0F, -0.5F, -14.0F, 14, 1, 14, 0.0F, false));
		top1.cubeList.add(new ModelBox(top1, 51, 0, -8.0F, -1.0F, 0.0F, 16, 2, 1, 0.0F, false));
		top1.cubeList.add(new ModelBox(top1, 66, 4, 7.0F, -1.0F, -14.0F, 1, 2, 14, 0.0F, false));
		top1.cubeList.add(new ModelBox(top1, 51, 0, -8.0F, -1.0F, -15.0F, 16, 2, 1, 0.0F, false));
		top1.cubeList.add(new ModelBox(top1, 66, 4, -8.0F, -1.0F, -14.0F, 1, 2, 14, 0.0F, false));
		top1.cubeList.add(new ModelBox(top1, 51, 4, -6.0F, -1.0F, -4.0F, 12, 1, 2, 0.0F, false));
		top1.cubeList.add(new ModelBox(top1, 51, 4, -6.0F, -1.0F, -8.0F, 12, 1, 2, 0.0F, false));
		top1.cubeList.add(new ModelBox(top1, 51, 4, -6.0F, -1.0F, -12.0F, 12, 1, 2, 0.0F, false));
		top1.cubeList.add(new ModelBox(top1, 1, 1, 3.0F, -1.3F, -15.3F, 2, 3, 2, 0.0F, false));
		top1.cubeList.add(new ModelBox(top1, 1, 1, -5.0F, -1.3F, -15.3F, 2, 3, 2, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		main.render(0.0625F);
		top1.render(0.0625F);
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
		} else {
			top1.rotateAngleX = 0F;
		}
	}

}
