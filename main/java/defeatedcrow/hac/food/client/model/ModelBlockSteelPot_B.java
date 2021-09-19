package defeatedcrow.hac.food.client.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockSteelPot_B extends ModelBlockSteelPot_A {
	// fields
	private final ModelRenderer top;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bb_main;

	public ModelBlockSteelPot_B() {
		textureWidth = 64;
		textureHeight = 32;

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, 8.0F, 0.0F);
		top.cubeList.add(new ModelBox(top, 0, 19, -5.0F, -8.0F, -5.0F, 10, 1, 10, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 31, 20, -1.0F, -9.5F, -1.0F, 2, 1, 2, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 31, 24, -0.5F, -9.0F, -0.5F, 1, 1, 1, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 8.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 11, -4.0F, -7.0F, -5.0F, 8, 6, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(bone2, 0.0F, -1.5708F, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 0, 11, -4.0F, -7.0F, -5.0F, 8, 6, 1, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(bone3, 0.0F, 3.1416F, 0.0F);
		bone3.cubeList.add(new ModelBox(bone3, 0, 11, -4.0F, -7.0F, -5.0F, 8, 6, 1, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(bone4, 0.0F, 1.5708F, 0.0F);
		bone4.cubeList.add(new ModelBox(bone4, 0, 11, -4.0F, -7.0F, -5.0F, 8, 6, 1, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, -1.0F, -4.0F, 8, 1, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 34, 1, -7.0F, -6.0F, -1.5F, 2, 1, 3, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 34, 1, 5.0F, -6.0F, -1.5F, 2, 1, 3, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		bone.render(0.0625F);
		bone2.render(0.0625F);
		bone3.render(0.0625F);
		bone4.render(0.0625F);
		bb_main.render(0.0625F);
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
