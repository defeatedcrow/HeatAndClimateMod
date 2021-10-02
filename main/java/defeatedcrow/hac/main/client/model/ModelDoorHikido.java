package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDoorHikido extends DCTileModelBase {
	// fields
	private final ModelRenderer husuma;
	private final ModelRenderer half;
	private final ModelRenderer bb_main;

	public ModelDoorHikido() {
		textureWidth = 64;
		textureHeight = 64;

		husuma = new ModelRenderer(this);
		husuma.setRotationPoint(0.0F, 8.0F, 0.0F);
		husuma.cubeList.add(new ModelBox(husuma, 0, 0, -8.0F, -32.0F, -1.0F, 16, 32, 1, 0.0F, false));

		half = new ModelRenderer(this);
		half.setRotationPoint(0.0F, 8.0F, 0.0F);
		half.cubeList.add(new ModelBox(half, 0, 0, -8.0F, -16.0F, -1.0F, 16, 16, 1, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 34, -8.0F, -0.25F, -1.5F, 16, 1, 3, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		husuma.render(f);
		bb_main.render(f);
	}

	public void renderHalf(float f) {
		setRotationAngles(f);
		half.render(f);
		bb_main.render(f);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void open() {
		half.rotationPointX = 15.5F;
		husuma.rotationPointX = 15.5F;
	}

	public void close() {
		half.rotationPointX = 0F;
		husuma.rotationPointX = 0F;
	}

	@Override
	public void setRotationAngles(float f) {}

}
