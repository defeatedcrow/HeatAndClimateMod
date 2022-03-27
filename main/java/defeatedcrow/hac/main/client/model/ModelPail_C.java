package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPail_C extends DCTileModelBase {
	// fields
	private final ModelRenderer L1;
	private final ModelRenderer L2;
	private final ModelRenderer bb_main;

	public ModelPail_C() {
		textureWidth = 64;
		textureHeight = 32;

		L1 = new ModelRenderer(this);
		L1.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(L1, 0.3491F, 0.0F, 0.0F);
		L1.cubeList.add(new ModelBox(L1, 46, 0, -7.7F, -6.0F, 2.0F, 1, 6, 1, 0.0F, false));
		L1.cubeList.add(new ModelBox(L1, 46, 0, 6.7F, -6.0F, 2.0F, 1, 6, 1, 0.0F, false));

		L2 = new ModelRenderer(this);
		L2.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(L2, -0.3491F, 0.0F, 0.0F);
		L2.cubeList.add(new ModelBox(L2, 46, 0, -7.7F, -6.0F, -3.0F, 1, 6, 1, 0.0F, false));
		L2.cubeList.add(new ModelBox(L2, 46, 0, 6.7F, -6.0F, -3.0F, 1, 6, 1, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -7.5F, -16.0F, -3.5F, 15, 10, 7, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 46, 0, 7.0F, -16.5F, -0.5F, 1, 11, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 46, 0, -8.0F, -16.5F, -0.5F, 1, 11, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 18, -7.0F, -6.5F, -0.5F, 14, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 18, -7.0F, -16.5F, -0.5F, 14, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 21, -8.0F, -1.0F, -5.0F, 2, 1, 10, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 21, 6.0F, -1.0F, -5.0F, 2, 1, 10, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, -6.5F, -2.0F, 1, 2, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 1, 21, 3.0F, -16.5F, -3.0F, 2, 1, 2, 0.0F, false));

	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		bb_main.render(0.0625F);
		L1.render(0.0625F);
		L2.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {}

}
