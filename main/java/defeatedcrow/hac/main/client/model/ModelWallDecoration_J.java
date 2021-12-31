package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWallDecoration_J extends DCTileModelBase {
	// fields
	private final ModelRenderer base;
	private final ModelRenderer bottles;

	public ModelWallDecoration_J() {
		textureWidth = 64;
		textureHeight = 64;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 8.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -7.0F, -1.0F, 1.8F, 14, 1, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 0, -7.0F, -9.0F, 1.8F, 14, 1, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 41, 0, 7.1F, -16.0F, 2.0F, 0, 16, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 41, 0, -7.1F, -16.0F, 2.0F, 0, 16, 6, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 1, 8, -7.0F, -16.0F, 7.9F, 14, 16, 0, 0.0F, false));

		bottles = new ModelRenderer(this);
		bottles.setRotationPoint(0.0F, 8.0F, 0.0F);
		bottles.cubeList.add(new ModelBox(bottles, 0, 38, -6.0F, -4.5F, 2.3F, 3, 3, 5, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 0, 47, -1.5F, -4.5F, 2.3F, 3, 3, 5, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 0, 56, 3.0F, -4.5F, 2.3F, 3, 3, 5, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 0, 25, -6.5F, -7.0F, 1.8F, 4, 6, 6, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 1, 25, -5.0F, -7.0F, 0.8F, 1, 1, 1, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 15, 25, -5.0F, -7.0F, -0.2F, 1, 4, 1, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 15, 25, 4.0F, -7.0F, -0.2F, 1, 4, 1, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 1, 25, 4.0F, -7.0F, 0.8F, 1, 1, 1, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 0, 25, 2.5F, -7.0F, 1.8F, 4, 6, 6, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 0, 25, -2.0F, -7.0F, 1.8F, 4, 6, 6, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 1, 25, -0.5F, -7.0F, 0.8F, 1, 1, 1, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 15, 25, -0.5F, -7.0F, -0.2F, 1, 4, 1, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 21, 25, -7.0F, -14.0F, 3.0F, 3, 5, 3, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 21, 34, -6.5F, -15.0F, 3.5F, 2, 1, 2, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 21, 45, -6.45F, -13.25F, 3.5F, 2, 4, 2, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 34, 25, -3.7F, -14.0F, 3.0F, 3, 5, 3, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 30, 45, -3.2F, -13.25F, 3.5F, 2, 4, 2, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 33, 34, -3.2F, -15.0F, 3.5F, 2, 1, 2, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 21, 38, -0.2F, -13.0F, 3.0F, 2, 4, 2, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 30, 38, 2.1F, -13.0F, 3.0F, 2, 4, 2, 0.0F, false));
		bottles.cubeList.add(new ModelBox(bottles, 39, 38, 4.4F, -13.0F, 3.0F, 2, 4, 2, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		base.render(0.0625F);
		bottles.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
