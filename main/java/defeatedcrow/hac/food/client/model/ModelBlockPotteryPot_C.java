package defeatedcrow.hac.food.client.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockPotteryPot_C extends ModelBlockPotteryPot {
	// fields
	private final ModelRenderer side1;
	private final ModelRenderer side2;
	private final ModelRenderer side3;
	private final ModelRenderer side4;
	private final ModelRenderer top;

	public ModelBlockPotteryPot_C() {
		textureWidth = 64;
		textureHeight = 64;

		side1 = new ModelRenderer(this);
		side1.setRotationPoint(0.0F, 8.0F, 0.0F);
		side1.cubeList.add(new ModelBox(side1, 0, 0, -6.0F, -2.0F, -6.0F, 12, 2, 12, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 0, 16, -6.0F, -8.0F, -6.0F, 12, 6, 1, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 0, 24, -6.5F, -3.0F, -6.5F, 13, 1, 1, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 0, 24, -6.5F, -5.0F, -6.5F, 13, 1, 1, 0.0F, false));

		side2 = new ModelRenderer(this);
		side2.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(side2, 0.0F, 3.1416F, 0.0F);
		side2.cubeList.add(new ModelBox(side2, 0, 16, -6.0F, -8.0F, -6.0F, 12, 6, 1, 0.0F, false));
		side2.cubeList.add(new ModelBox(side2, 0, 24, -6.5F, -3.0F, -6.5F, 13, 1, 1, 0.0F, false));
		side2.cubeList.add(new ModelBox(side2, 0, 24, -6.5F, -5.0F, -6.5F, 13, 1, 1, 0.0F, false));

		side3 = new ModelRenderer(this);
		side3.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(side3, 0.0F, -1.5708F, 0.0F);
		side3.cubeList.add(new ModelBox(side3, 27, 16, -5.0F, -8.0F, -6.0F, 10, 6, 1, 0.0F, false));
		side3.cubeList.add(new ModelBox(side3, 29, 24, -5.5F, -3.0F, -6.5F, 11, 1, 1, 0.0F, false));
		side3.cubeList.add(new ModelBox(side3, 29, 24, -5.5F, -5.0F, -6.5F, 11, 1, 1, 0.0F, false));

		side4 = new ModelRenderer(this);
		side4.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(side4, 0.0F, 1.5708F, 0.0F);
		side4.cubeList.add(new ModelBox(side4, 27, 16, -5.0F, -8.0F, -6.0F, 10, 6, 1, 0.0F, false));
		side4.cubeList.add(new ModelBox(side4, 29, 24, -5.5F, -3.0F, -6.5F, 11, 1, 1, 0.0F, false));
		side4.cubeList.add(new ModelBox(side4, 29, 24, -5.5F, -5.0F, -6.5F, 11, 1, 1, 0.0F, false));

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, 8.0F, 0.0F);
		top.cubeList.add(new ModelBox(top, 0, 28, -7.0F, -9.0F, -7.0F, 14, 1, 14, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 44, -3.0F, -11.0F, -5.0F, 1, 2, 10, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 0, 44, 2.0F, -11.0F, -5.0F, 1, 2, 10, 0.0F, false));
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
