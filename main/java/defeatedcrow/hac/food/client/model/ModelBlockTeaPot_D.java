package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockTeaPot_D extends DCTileModelBase {
	// fields
	private final ModelRenderer bb_main;
	private final ModelRenderer cube_r1;

	public ModelBlockTeaPot_D() {
		textureWidth = 32;
		textureHeight = 32;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 26, -2.5F, -1.0F, -2.5F, 5, 1, 5, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 2, -2.5F, -5.5F, -2.5F, 5, 1, 5, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 2, -0.5F, -6.5F, -0.5F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 25, -3.0F, -1.5F, -3.0F, 6, 1, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 8, -3.0F, -5.0F, -3.0F, 6, 2, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 16, -3.5F, -3.0F, -3.5F, 7, 2, 7, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 20, 0, 0.0F, -9.0F, -3.0F, 0, 4, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 22, 0, -0.5F, -9.5F, -2.0F, 1, 1, 4, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r1);
		setRotation(cube_r1, 0.5236F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 28, 11, -0.5F, -7.0F, -2.0F, 1, 4, 1, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		bb_main.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
