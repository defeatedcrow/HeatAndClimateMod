package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockTeaPot_E extends DCTileModelBase {
	// fields
	private final ModelRenderer body;
	private final ModelRenderer bottle;
	private final ModelRenderer glass;

	public ModelBlockTeaPot_E() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 8.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -7.0F, -1.0F, -7.0F, 14, 1, 14, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 17, -7.0F, -16.0F, -7.0F, 14, 6, 14, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 38, -7.0F, -10.0F, 0.0F, 14, 9, 7, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 43, 0, 1.5F, -10.0F, -5.0F, 4, 1, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 43, 6, 2.5F, -9.5F, -4.0F, 2, 1, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 52, 7, 3.0F, -9.0F, -3.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 43, 17, -4.5F, -10.0F, -5.0F, 4, 1, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 43, 23, -4.0F, -9.0F, -4.0F, 3, 3, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 59, 23, -6.5F, -9.0F, -1.0F, 1, 4, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 54, 23, -4.0F, -6.0F, -3.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 54, 23, -2.0F, -6.0F, -3.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 13, 56, -3.5F, -14.0F, -7.5F, 2, 2, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 13, 56, -6.5F, -14.0F, -7.5F, 2, 2, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 8, 56, -3.0F, -14.0F, -8.0F, 1, 2, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 8, 56, -6.0F, -14.0F, -8.0F, 1, 2, 1, 0.0F, false));

		bottle = new ModelRenderer(this);
		bottle.setRotationPoint(0.0F, 8.0F, 0.0F);
		bottle.cubeList.add(new ModelBox(bottle, 14, 56, 0.5F, -8.0F, -6.5F, 6, 2, 6, 0.0F, false));
		bottle.cubeList.add(new ModelBox(bottle, 33, 55, 2.5F, -7.0F, -7.5F, 2, 5, 1, 0.0F, false));

		glass = new ModelRenderer(this);
		glass.setRotationPoint(0.0F, 8.0F, 0.0F);
		glass.cubeList.add(new ModelBox(glass, 40, 53, 0.5F, -6.0F, -6.5F, 6, 5, 6, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		body.render(0.0625F);
		bottle.render(0.0625F);
		glass.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
