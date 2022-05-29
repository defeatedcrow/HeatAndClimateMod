package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMetalChest_C extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer top;

	public ModelMetalChest_C() {
		textureWidth = 64;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, -1.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -5.0F, -1.0F, -6.0F, 10, 1, 14, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 16, -5.0F, -16.0F, 7.0F, 10, 15, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 26, 16, -5.0F, -16.0F, -6.0F, 10, 15, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 11, 21, 4.0F, -16.0F, -5.0F, 1, 15, 12, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 38, 21, -5.0F, -16.0F, -5.0F, 1, 15, 12, 0.0F, false));

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, -8.0F, 0.0F);
		setRotation(top, -0.7854F, 0.0F, 0.0F);
		top.cubeList.add(new ModelBox(top, 0, 49, -4.0F, -0.5F, -6.0F, 8, 1, 12, 0.0F, false));
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
			top.rotateAngleX = -0.7854F;
		} else {
			top.rotateAngleX = 0F;
		}
	}

}
