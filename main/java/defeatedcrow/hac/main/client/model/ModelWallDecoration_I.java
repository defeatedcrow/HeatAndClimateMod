package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWallDecoration_I extends DCTileModelBase {
	// fields
	private final ModelRenderer bb_main;

	public ModelWallDecoration_I() {
		textureWidth = 64;
		textureHeight = 32;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -12.0F, -16.0F, 5.0F, 24, 16, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 18, 3.0F, -12.0F, 6.0F, 1, 8, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 18, -4.0F, -12.0F, 6.0F, 1, 8, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 5, 18, -5.0F, -9.0F, 7.0F, 10, 2, 1, 0.0F, false));
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