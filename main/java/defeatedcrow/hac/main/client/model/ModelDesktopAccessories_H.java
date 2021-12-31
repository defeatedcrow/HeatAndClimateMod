package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDesktopAccessories_H extends DCTileModelBase {
	// fields
	private final ModelRenderer body;
	private final ModelRenderer main;

	public ModelDesktopAccessories_H() {
		textureWidth = 64;
		textureHeight = 16;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(body, 0.1745F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -7.0F, -2.0F, -5.0F, 14, 1, 10, 0.0F, false));

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, 5.0F, -2.0F, 3.0F, 1, 2, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 0, -6.0F, -2.0F, 3.0F, 1, 2, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 39, 0, 5.0F, -2.0F, 5.0F, 2, 2, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 48, 0, 5.5F, -6.0F, 5.5F, 1, 6, 1, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		body.render(0.0625F);
		main.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
