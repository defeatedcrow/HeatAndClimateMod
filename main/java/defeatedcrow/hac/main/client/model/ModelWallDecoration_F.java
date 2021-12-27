package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWallDecoration_F extends DCTileModelBase {
	// fields
	private final ModelRenderer CD;
	private final ModelRenderer bb_main;

	public ModelWallDecoration_F() {
		textureWidth = 64;
		textureHeight = 32;

		CD = new ModelRenderer(this);
		CD.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(CD, -0.1309F, 0.0F, 0.0F);
		CD.cubeList.add(new ModelBox(CD, 0, 8, -7.5F, -8.8F, 6.0F, 7, 7, 1, 0.0F, false));
		CD.cubeList.add(new ModelBox(CD, 17, 8, 0.5F, -8.8F, 6.0F, 7, 7, 1, 0.0F, false));
		CD.cubeList.add(new ModelBox(CD, 0, 17, -7.5F, -16.8F, 5.0F, 7, 7, 1, 0.0F, false));
		CD.cubeList.add(new ModelBox(CD, 17, 17, 0.5F, -16.8F, 5.0F, 7, 7, 1, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -2.0F, 5.0F, 16, 2, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -10.0F, 5.0F, 16, 2, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 4, -8.0F, -1.0F, 6.0F, 16, 1, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 4, -8.0F, -9.0F, 6.0F, 16, 1, 2, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		CD.render(0.0625F);
		bb_main.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
