package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPail_B extends DCTileModelBase {
	// fields
	private final ModelRenderer cap;
	private final ModelRenderer bb_main;

	public ModelPail_B() {
		textureWidth = 64;
		textureHeight = 32;

		cap = new ModelRenderer(this);
		cap.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(cap, 0.0F, 0.0F, -0.4363F);
		cap.cubeList.add(new ModelBox(cap, 30, 25, 0.0F, -15.0F, -1.5F, 3, 2, 3, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 4, 1, -6.0F, -1.0F, -4.0F, 12, 1, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -7.0F, -12.0F, -5.0F, 14, 11, 10, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 21, 2.0F, -14.0F, -4.0F, 4, 2, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 5, 23, 2.0F, -15.0F, -3.0F, 1, 1, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 22, -6.0F, -13.0F, -4.0F, 4, 1, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 2, 21, -4.0F, -14.0F, -4.0F, 2, 1, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 5, 23, -3.0F, -15.0F, -3.0F, 1, 1, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 17, 25, -2.0F, -15.0F, -1.0F, 4, 1, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 30, 25, 3.2F, -15.0F, -1.5F, 3, 2, 3, 0.0F, false));

	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		bb_main.render(0.0625F);
		cap.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {}

}
