package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDesktopAccessories_B extends DCTileModelBase {
	// fields
	private final ModelRenderer bb_main;

	public ModelDesktopAccessories_B() {
		textureWidth = 32;
		textureHeight = 16;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -5.5F, -1.0F, -4.0F, 5, 1, 7, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 0.5F, -1.0F, -4.0F, 5, 1, 7, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 8, -0.5F, -0.7F, -4.0F, 1, 1, 7, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		bb_main.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
