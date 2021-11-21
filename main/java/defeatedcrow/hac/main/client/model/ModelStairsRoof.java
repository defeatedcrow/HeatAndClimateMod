package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelStairsRoof extends DCTileModelBase {

	private boolean hasTop;
	private final ModelRenderer roofbase2;
	private final ModelRenderer top;

	public ModelStairsRoof(boolean t) {
		hasTop = t;
		textureWidth = 64;
		textureHeight = 32;

		roofbase2 = new ModelRenderer(this);
		roofbase2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(roofbase2, 0.7854F, 0.0F, 0.0F);
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 23, -8.0F, -1.0F, -12.0F, 16, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 21, -8.0F, -1.0F, -10.0F, 16, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 19, -8.0F, -1.0F, -8.0F, 16, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 17, -8.0F, -1.0F, -6.0F, 16, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 15, -8.0F, -1.0F, -4.0F, 16, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 13, -8.0F, -1.0F, -2.0F, 16, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 11, -8.0F, -1.0F, 0.0F, 16, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 9, -8.0F, -1.0F, 2.0F, 16, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 7, -8.0F, -1.0F, 4.0F, 16, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 5, -8.0F, -1.0F, 6.0F, 16, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 3, -8.0F, -1.0F, 8.0F, 16, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 1, -8.0F, -1.0F, 10.0F, 16, 1, 2, 0.0F, false));

		top = new ModelRenderer(this);
		top.setRotationPoint(-8.0F, -7.0F, 8.0F);
		setRotationAngle(top, 0.7854F, 0.0F, 0.0F);
		top.cubeList.add(new ModelBox(top, 0, 26, -0.5F, -2.0F, 0.0F, 17, 2, 2, 0.0F, false));
	}

	@Override
	public void render(float f5) {
		roofbase2.render(f5);
		if (hasTop)
			top.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}
