package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDesktopAccessories_C extends DCTileModelBase {
	// fields
	private final ModelRenderer body;
	private final ModelRenderer pen;
	private final ModelRenderer cube_r1;

	public ModelDesktopAccessories_C() {
		textureWidth = 16;
		textureHeight = 16;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 8.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -1.0F, -1.0F, -2.0F, 2, 1, 2, 0.0F, false));

		pen = new ModelRenderer(this);
		pen.setRotationPoint(0.0F, 8.0F, 0.0F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		pen.addChild(cube_r1);
		setRotation(cube_r1, -0.4363F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 9, 0, -0.5F, -8.0F, -2.0F, 1, 8, 1, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		body.render(0.0625F);
		pen.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
