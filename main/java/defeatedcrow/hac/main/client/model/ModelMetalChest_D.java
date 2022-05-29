package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMetalChest_D extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer door;

	public ModelMetalChest_D() {
		textureWidth = 64;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -8.0F, -1.0F, -8.0F, 16, 1, 16, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 0, -8.0F, -16.0F, -8.0F, 16, 1, 16, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 18, -8.0F, -15.0F, 7.0F, 16, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 25, 19, 7.0F, -15.0F, -8.0F, 1, 14, 15, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 25, 19, -8.0F, -15.0F, -8.0F, 1, 14, 15, 0.0F, false));

		door = new ModelRenderer(this);
		door.setRotationPoint(7.0F, 0.0F, -8.0F);
		setRotation(door, 0.0F, -0.5236F, 0.0F);
		door.cubeList.add(new ModelBox(door, 1, 49, -14.0F, -7.0F, -0.5F, 14, 14, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 32, 49, -13.0F, -2.0F, -1.0F, 2, 4, 1, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		main.render(0.0625F);
		door.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {

		if (f != 0F) {
			door.rotateAngleY = -0.5236F;
		} else {
			door.rotateAngleY = 0F;
		}
	}

}
