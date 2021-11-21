package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelStairsRoof_Inner extends DCTileModelBase {

	private boolean hasTop;
	private final ModelRenderer roofbase1;
	private final ModelRenderer roofbase2;
	private final ModelRenderer top;
	private final ModelRenderer top2;
	private final ModelRenderer top3;

	public ModelStairsRoof_Inner(boolean t) {
		hasTop = t;
		textureWidth = 64;
		textureHeight = 32;

		roofbase1 = new ModelRenderer(this);
		roofbase1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(roofbase1, 0.7854F, 0.0F, 0.0F);
		roofbase1.cubeList.add(new ModelBox(roofbase1, 15, 23, 7.0F, -1.0F, -12.0F, 1, 1, 2, 0.0F, false));
		roofbase1.cubeList.add(new ModelBox(roofbase1, 14, 21, 6.0F, -1.0F, -10.0F, 2, 1, 2, 0.0F, false));
		roofbase1.cubeList.add(new ModelBox(roofbase1, 13, 19, 5.0F, -1.0F, -8.0F, 3, 1, 2, 0.0F, false));
		roofbase1.cubeList.add(new ModelBox(roofbase1, 12, 17, 4.0F, -1.0F, -6.0F, 4, 1, 2, 0.0F, false));
		roofbase1.cubeList.add(new ModelBox(roofbase1, 10, 15, 2.0F, -1.0F, -4.0F, 6, 1, 2, 0.0F, false));
		roofbase1.cubeList.add(new ModelBox(roofbase1, 9, 13, 1.0F, -1.0F, -2.0F, 7, 1, 2, 0.0F, false));
		roofbase1.cubeList.add(new ModelBox(roofbase1, 8, 11, 0.0F, -1.0F, 0.0F, 8, 1, 2, 0.0F, false));
		roofbase1.cubeList.add(new ModelBox(roofbase1, 6, 9, -2.0F, -1.0F, 2.0F, 10, 1, 2, 0.0F, false));
		roofbase1.cubeList.add(new ModelBox(roofbase1, 5, 7, -3.0F, -1.0F, 4.0F, 11, 1, 2, 0.0F, false));
		roofbase1.cubeList.add(new ModelBox(roofbase1, 4, 5, -4.0F, -1.0F, 6.0F, 12, 1, 2, 0.0F, false));
		roofbase1.cubeList.add(new ModelBox(roofbase1, 2, 3, -6.0F, -1.0F, 8.0F, 14, 1, 2, 0.0F, false));
		roofbase1.cubeList.add(new ModelBox(roofbase1, 0, 1, -8.0F, -1.0F, 10.0F, 16, 1, 2, 0.0F, false));

		roofbase2 = new ModelRenderer(this);
		roofbase2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(roofbase2, 0.7854F, -1.5708F, 0.0F);
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 23, -8.0F, -1.0F, -12.0F, 1, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 21, -8.0F, -1.0F, -10.0F, 2, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 19, -8.0F, -1.0F, -8.0F, 3, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 17, -8.0F, -1.0F, -6.0F, 4, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 15, -8.0F, -1.0F, -4.0F, 6, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 13, -8.0F, -1.0F, -2.0F, 7, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 11, -8.0F, -1.0F, 0.0F, 8, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 9, -8.0F, -1.0F, 2.0F, 10, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 7, -8.0F, -1.0F, 4.0F, 11, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 5, -8.0F, -1.0F, 6.0F, 12, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 3, -8.0F, -1.0F, 8.0F, 14, 1, 2, 0.0F, false));
		roofbase2.cubeList.add(new ModelBox(roofbase2, 0, 1, -8.0F, -1.0F, 10.0F, 16, 1, 2, 0.0F, false));

		top = new ModelRenderer(this);
		top.setRotationPoint(-8.0F, -7.0F, 8.0F);
		setRotationAngle(top, 0.5236F, 0.6109F, 0.7854F);
		top.cubeList.add(new ModelBox(top, 0, 26, -2.0F, -2.5F, -1.0F, 29, 2, 2, 0.0F, false));

		top2 = new ModelRenderer(this);
		top2.setRotationPoint(-8.0F, -7.0F, 8.0F);
		setRotationAngle(top2, 0.7854F, 0.0F, 0.0F);
		top2.cubeList.add(new ModelBox(top2, 0, 26, -0.5F, -2.0F, 0.0F, 17, 2, 2, 0.0F, false));

		top3 = new ModelRenderer(this);
		top3.setRotationPoint(-8.0F, -7.0F, 8.0F);
		setRotationAngle(top3, 0.7854F, 1.5708F, 0.0F);
		top3.cubeList.add(new ModelBox(top3, 0, 26, -0.5F, -2.0F, 0.0F, 17, 2, 2, 0.0F, false));
	}

	@Override
	public void render(float f5) {
		roofbase1.render(f5);
		roofbase2.render(f5);
		top.render(f5);
		if (hasTop) {
			top2.render(f5);
			top3.render(f5);
		}
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}
