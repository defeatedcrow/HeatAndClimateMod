package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFaucet_SUS_LA extends DCTileModelBase {
	// fields
	private final ModelRenderer handle;
	private final ModelRenderer bb_main;

	public ModelFaucet_SUS_LA() {
		textureWidth = 32;
		textureHeight = 32;

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, -3.0F, 3.0F);
		handle.cubeList.add(new ModelBox(handle, 9, 23, -2.5F, -1.5F, -0.5F, 5, 1, 1, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.0F, -10.0F, 7.0F, 4, 4, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 8, -1.0F, -9.0F, -1.0F, 2, 2, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 19, -1.0F, -9.0F, -3.0F, 2, 6, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 15, 8, -1.5F, -9.5F, 1.0F, 3, 3, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 9, 19, -1.0F, -10.5F, 2.0F, 2, 1, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 18, 19, -0.5F, -11.5F, 2.5F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		handle.render(0.0625F);
		bb_main.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
		float rot = f;
		float f2 = (float) (rot * Math.PI / 180F);// f * 0.01745329F;

		handle.rotateAngleY = f2;
	}

}
