package defeatedcrow.hac.food.client.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockPotteryPot_B extends ModelBlockPotteryPot {
	// fields
	private final ModelRenderer side1;
	private final ModelRenderer side2;
	private final ModelRenderer side3;
	private final ModelRenderer side4;
	private final ModelRenderer top;
	private final ModelRenderer bb_main;

	public ModelBlockPotteryPot_B() {
		textureWidth = 64;
		textureHeight = 32;

		side1 = new ModelRenderer(this);
		side1.setRotationPoint(0.0F, 11.0F, 0.0F);
		side1.cubeList.add(new ModelBox(side1, 0, 12, -6.0F, -11.0F, -6.0F, 12, 10, 1, 0.0F, false));
		side1.cubeList.add(new ModelBox(side1, 0, 25, -8.0F, -8.0F, -8.0F, 16, 1, 2, 0.0F, false));

		side2 = new ModelRenderer(this);
		side2.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(side2, 0.0F, -1.5708F, 0.0F);
		side2.cubeList.add(new ModelBox(side2, 1, 12, -5.0F, -11.0F, -6.0F, 10, 10, 1, 0.0F, false));
		side2.cubeList.add(new ModelBox(side2, 0, 25, -6.0F, -8.0F, -8.0F, 12, 1, 2, 0.0F, false));

		side3 = new ModelRenderer(this);
		side3.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(side3, 0.0F, 3.1416F, 0.0F);
		side3.cubeList.add(new ModelBox(side3, 0, 12, -6.0F, -11.0F, -6.0F, 12, 10, 1, 0.0F, false));
		side3.cubeList.add(new ModelBox(side3, 0, 25, -8.0F, -8.0F, -8.0F, 16, 1, 2, 0.0F, false));

		side4 = new ModelRenderer(this);
		side4.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(side4, 0.0F, 1.5708F, 0.0F);
		side4.cubeList.add(new ModelBox(side4, 1, 12, -5.0F, -11.0F, -6.0F, 10, 10, 1, 0.0F, false));
		side4.cubeList.add(new ModelBox(side4, 0, 25, -6.0F, -8.0F, -8.0F, 12, 1, 2, 0.0F, false));

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(top, 0.0F, -1.5708F, 0.0F);
		top.cubeList.add(new ModelBox(top, 27, 12, -7.0F, -12.0F, -7.0F, 14, 1, 3, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 27, 16, -7.0F, -12.0F, -4.0F, 14, 1, 3, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 27, 21, -7.0F, -12.0F, -1.0F, 14, 1, 2, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 27, 12, -7.0F, -12.0F, 1.0F, 14, 1, 3, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 27, 16, -7.0F, -12.0F, 4.0F, 14, 1, 3, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 32, 4, -6.0F, -14.0F, -4.0F, 12, 2, 2, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 32, 4, -6.0F, -14.0F, 2.0F, 12, 2, 2, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 11.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -5.0F, -1.0F, -5.0F, 10, 1, 10, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		bb_main.render(0.0625F);
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
