package defeatedcrow.hac.food.client.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockPotteryPot_A extends ModelBlockPotteryPot {
	// fields
	private final ModelRenderer side1;
	private final ModelRenderer side2;
	private final ModelRenderer side3;
	private final ModelRenderer side4;
	private final ModelRenderer top;

	public ModelBlockPotteryPot_A() {
		textureWidth = 64;
		textureHeight = 64;

		side1 = new ModelRenderer(this);
		side1.setRotationPoint(0.0F, 8.0F, 0.0F);
		side1.cubeList.add(new ModelBox(side1, 0, 0, -4.0F, -2.0F, -4.0F, 8, 2, 8, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 0, 10, -4.5F, -6.0F, -4.5F, 9, 4, 1, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 0, 16, -5.0F, -12.0F, -5.0F, 10, 6, 1, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 0, 24, -4.0F, -13.0F, -4.0F, 8, 1, 1, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 0, 27, -3.0F, -14.0F, -3.0F, 6, 1, 1, 0.0F, false));

		side2 = new ModelRenderer(this);
		side2.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(side2, 0.0F, 3.1416F, 0.0F);
		side2.cubeList.add(new ModelBox(side2, 0, 10, -4.5F, -6.0F, -4.5F, 9, 4, 1, 0.0F, false));
		side2.cubeList.add(new ModelBox(side2, 0, 16, -5.0F, -12.0F, -5.0F, 10, 6, 1, 0.0F, false));
		side2.cubeList.add(new ModelBox(side2, 0, 24, -4.0F, -13.0F, -4.0F, 8, 1, 1, 0.0F, false));
		side2.cubeList.add(new ModelBox(side2, 0, 27, -3.0F, -14.0F, -3.0F, 6, 1, 1, 0.0F, false));

		side3 = new ModelRenderer(this);
		side3.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(side3, 0.0F, -1.5708F, 0.0F);
		side3.cubeList.add(new ModelBox(side3, 21, 10, -3.5F, -6.0F, -4.5F, 7, 4, 1, 0.0F, false));
		side3.cubeList.add(new ModelBox(side3, 23, 16, -4.0F, -12.0F, -5.0F, 8, 6, 1, 0.0F, false));
		side3.cubeList.add(new ModelBox(side3, 19, 24, -3.0F, -13.0F, -4.0F, 6, 1, 1, 0.0F, false));
		side3.cubeList.add(new ModelBox(side3, 15, 27, -2.0F, -14.0F, -3.0F, 4, 1, 1, 0.0F, false));

		side4 = new ModelRenderer(this);
		side4.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(side4, 0.0F, 1.5708F, 0.0F);
		side4.cubeList.add(new ModelBox(side4, 21, 10, -3.5F, -6.0F, -4.5F, 7, 4, 1, 0.0F, false));
		side4.cubeList.add(new ModelBox(side4, 23, 16, -4.0F, -12.0F, -5.0F, 8, 6, 1, 0.0F, false));
		side4.cubeList.add(new ModelBox(side4, 19, 24, -3.0F, -13.0F, -4.0F, 6, 1, 1, 0.0F, false));
		side4.cubeList.add(new ModelBox(side4, 15, 27, -2.0F, -14.0F, -3.0F, 4, 1, 1, 0.0F, false));

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, 8.0F, 0.0F);
		top.cubeList.add(new ModelBox(top, 0, 30, -4.0F, -14.5F, -4.0F, 8, 1, 8, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 25, 32, -1.0F, -16.0F, -1.0F, 2, 2, 2, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		side1.render(0.0625F);
		side2.render(0.0625F);
		side3.render(0.0625F);
		side4.render(0.0625F);
	}

	public void renderCap(float f) {
		top.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
