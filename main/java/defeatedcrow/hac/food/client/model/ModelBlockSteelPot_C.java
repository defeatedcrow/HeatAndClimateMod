package defeatedcrow.hac.food.client.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockSteelPot_C extends ModelBlockSteelPot_A {
	// fields
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer top;
	private final ModelRenderer bb_main;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;

	public ModelBlockSteelPot_C() {
		textureWidth = 64;
		textureHeight = 32;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 8.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 20, -7.0F, -6.0F, -7.0F, 14, 5, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 15, -8.0F, -7.0F, -8.0F, 16, 2, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(bone2, 0.0F, -1.5708F, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 2, 20, -6.0F, -6.0F, -7.0F, 12, 5, 1, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 2, 15, -7.0F, -7.0F, -8.0F, 14, 2, 1, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(bone3, 0.0F, 3.1416F, 0.0F);
		bone3.cubeList.add(new ModelBox(bone3, 0, 20, -7.0F, -6.0F, -7.0F, 14, 5, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 15, -8.0F, -7.0F, -8.0F, 16, 2, 1, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(bone4, 0.0F, 1.5708F, 0.0F);
		bone4.cubeList.add(new ModelBox(bone4, 2, 20, -6.0F, -6.0F, -7.0F, 12, 5, 1, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 2, 15, -7.0F, -7.0F, -8.0F, 14, 2, 1, 0.0F, false));

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, 8.0F, 0.0F);
		top.cubeList.add(new ModelBox(top, 23, 21, -5.0F, -8.0F, -5.0F, 10, 1, 10, 0.0F, false));
		top.cubeList.add(new ModelBox(top, 36, 15, -2.0F, -9.0F, -2.0F, 4, 1, 4, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -6.0F, -1.0F, -6.0F, 12, 1, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -9.0F, -7.0F, -2.0F, 1, 1, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 8.0F, -7.0F, -2.0F, 1, 1, 4, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(cube_r1, 0.0F, 1.5708F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 2, 28, -5.0F, -7.0F, -6.0F, 10, 1, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(cube_r2, 0.0F, 3.1416F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 28, -6.0F, -7.0F, -6.0F, 12, 1, 1, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(cube_r3, 0.0F, -1.5708F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 2, 28, -5.0F, -7.0F, -6.0F, 10, 1, 1, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(cube_r4, 0.0F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 28, -6.0F, -7.0F, -6.0F, 12, 1, 1, 0.0F, false));
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
		cube_r1.render(0.0625F);
		cube_r2.render(0.0625F);
		cube_r3.render(0.0625F);
		cube_r4.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
