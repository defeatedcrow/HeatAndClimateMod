package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPail_D extends DCTileModelBase {
	// fields
	private final ModelRenderer glass;
	private final ModelRenderer bb_main;

	public ModelPail_D() {
		textureWidth = 64;
		textureHeight = 32;

		glass = new ModelRenderer(this);
		glass.setRotationPoint(0.0F, 8.0F, 0.0F);
		glass.cubeList.add(new ModelBox(glass, 0, 12, -4.5F, -15.0F, -4.5F, 9, 11, 9, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -5.0F, -1.0F, -5.0F, 10, 1, 10, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 32, 2, -3.0F, -2.0F, -3.0F, 6, 1, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 36, 4, -2.0F, -3.0F, -2.0F, 4, 1, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 4, 2, -4.0F, -4.0F, -4.0F, 8, 1, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 32, 12, -3.0F, -16.0F, -3.0F, 6, 1, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -0.5F, -4.5F, -5.0F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -0.5F, -5.0F, -6.0F, 1, 2, 1, 0.0F, false));

	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		bb_main.render(0.0625F);
	}

	public void render(float f, float speed, float tick) {
		glass.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {}

}
