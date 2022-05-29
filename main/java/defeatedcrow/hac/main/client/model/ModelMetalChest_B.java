package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMetalChest_B extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer huta;
	private final ModelRenderer band;

	public ModelMetalChest_B() {
		textureWidth = 64;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -7.0F, -1.0F, -4.0F, 14, 1, 11, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 37, 13, 6.0F, -4.0F, -4.0F, 1, 3, 11, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 4, 13, -7.0F, -4.0F, -4.0F, 1, 3, 11, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 19, 18, -6.0F, -4.0F, 6.0F, 12, 3, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 19, 13, -6.0F, -4.0F, -4.0F, 12, 3, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 17, 52, -7.0F, -4.0F, 7.0F, 14, 4, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 3, 30, -6.0F, 0.1F, -5.0F, 2, 0, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 3, 35, 4.0F, 0.1F, -5.0F, 2, 0, 1, 0.0F, false));

		huta = new ModelRenderer(this);
		huta.setRotationPoint(0.0F, 4.0F, 8.0F);
		setRotation(huta, -1.0472F, 0.0F, 0.0F);
		huta.cubeList.add(new ModelBox(huta, 0, 30, -8.0F, -1.0F, -13.0F, 16, 1, 13, 0.0F, false));
		huta.cubeList.add(new ModelBox(huta, 0, 46, -8.0F, 0.0F, -13.0F, 1, 4, 13, 0.0F, false));
		huta.cubeList.add(new ModelBox(huta, 36, 46, 7.0F, 0.0F, -13.0F, 1, 4, 13, 0.0F, false));
		huta.cubeList.add(new ModelBox(huta, 17, 46, -7.0F, 0.0F, -13.0F, 14, 4, 1, 0.0F, false));
		huta.cubeList.add(new ModelBox(huta, 40, 1, -4.0F, 1.0F, -16.0F, 8, 2, 1, 0.0F, false));
		huta.cubeList.add(new ModelBox(huta, 2, 6, -3.0F, 1.5F, -15.0F, 1, 1, 2, 0.0F, false));
		huta.cubeList.add(new ModelBox(huta, 2, 1, 2.0F, 1.5F, -15.0F, 1, 1, 2, 0.0F, false));

		band = new ModelRenderer(this);
		band.setRotationPoint(0.0F, 8.0F, -5.2F);
		setRotation(band, -1.5708F, 0.0F, 0.0F);
		band.cubeList.add(new ModelBox(band, 1, 31, -6.0F, -0.2F, -2.9F, 2, 0, 3, 0.0F, false));
		band.cubeList.add(new ModelBox(band, 1, 36, 4.0F, -0.2F, -2.9F, 2, 0, 3, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		main.render(0.0625F);
		band.render(0.0625F);
		huta.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {

		if (f != 0F) {
			huta.rotateAngleX = -1.0472F;
			band.rotateAngleX = 0F;
		} else {
			huta.rotateAngleX = 0F;
			band.rotateAngleX = -1.5708F;
		}
	}

}
