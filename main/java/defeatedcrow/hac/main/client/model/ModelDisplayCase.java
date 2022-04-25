package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDisplayCase extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer sideA;
	private final ModelRenderer sideB;

	public ModelDisplayCase() {
		textureWidth = 64;
		textureHeight = 32;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -7.0F, -3.0F, -7.0F, 14, 1, 14, 0.0F, false));

		sideA = new ModelRenderer(this);
		sideA.setRotationPoint(0.0F, 8.0F, 0.0F);
		sideA.cubeList.add(new ModelBox(sideA, 0, 17, -8.0F, -3.0F, -7.0F, 1, 1, 14, 0.0F, false));

		sideB = new ModelRenderer(this);
		sideB.setRotationPoint(0.0F, 8.0F, 0.0F);
		sideB.cubeList.add(new ModelBox(sideB, 31, 17, 7.0F, -3.0F, -7.0F, 1, 1, 14, 0.0F, false));
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		main.render(0.0625F);
	}

	public void renderA() {
		sideA.render(0.0625F);
	}

	public void renderB() {
		sideB.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
	}

}
