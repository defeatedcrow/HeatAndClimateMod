package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelHopperChest extends DCTileModelBase {
	private final ModelRenderer hopper_A;
	private final ModelRenderer hopper_B;
	private final ModelRenderer chest_top;
	private final ModelRenderer bb_main;

	public ModelHopperChest() {
		textureWidth = 64;
		textureHeight = 64;

		hopper_A = new ModelRenderer(this);
		hopper_A.setRotationPoint(0.0F, 8.0F, 0.0F);
		hopper_A.cubeList.add(new ModelBox(hopper_A, 0, 0, -2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F, false));

		hopper_B = new ModelRenderer(this);
		hopper_B.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotationAngle(hopper_B, 0.0F, -1.5708F, 0.0F);
		hopper_B.cubeList.add(new ModelBox(hopper_B, 0, 18, -2.0F, -4.0F, -1.5F, 4, 4, 10, 0.0F, false));

		chest_top = new ModelRenderer(this);
		chest_top.setRotationPoint(0.0F, -5.0F, -7.0F);
		setRotationAngle(chest_top, 1.0472F, 0.0F, 0.0F);
		chest_top.cubeList.add(new ModelBox(chest_top, 0, 46, -7.0F, -3.0F, 0.0F, 14, 3, 1, 0.0F, false));
		chest_top.cubeList.add(new ModelBox(chest_top, 0, 46, -7.0F, -3.0F, 13.0F, 14, 3, 1, 0.0F, false));
		chest_top.cubeList.add(new ModelBox(chest_top, 37, 47, 6.0F, -3.0F, 1.0F, 1, 3, 12, 0.0F, false));
		chest_top.cubeList.add(new ModelBox(chest_top, 37, 47, -7.0F, -3.0F, 1.0F, 1, 3, 12, 0.0F, false));
		chest_top.cubeList.add(new ModelBox(chest_top, 0, 51, -6.0F, -3.0F, 1.0F, 12, 1, 12, 0.0F, false));
		chest_top.cubeList.add(new ModelBox(chest_top, 0, 51, -1.0F, -1.0F, 14.0F, 2, 2, 1, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 8, 4, -6.0F, -2.0F, -5.0F, 12, 1, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 2, 0, -7.5F, -4.0F, -7.5F, 15, 2, 15, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -5.0F, -8.0F, 16, 1, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 36, -7.0F, -13.0F, -7.0F, 14, 8, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 36, -7.0F, -13.0F, 6.0F, 14, 8, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 37, 26, 6.0F, -13.0F, -6.0F, 1, 8, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 37, 26, -7.0F, -13.0F, -6.0F, 1, 8, 12, 0.0F, false));
	}

	@Override
	public void render(float f5) {
		setRotationAngles(f5);
		chest_top.render(0.0625F);
		bb_main.render(0.0625F);
	}

	public void renderOutputA() {
		hopper_A.render(0.0625F);
	}

	public void renderOutputB(float f) {
		float f1 = f * 0.01745329F;
		hopper_B.rotateAngleY = f1;
		hopper_B.render(0.0625F);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {
		float f1 = f * 0.01745329F;
		chest_top.rotateAngleX = f1;
	}

}
