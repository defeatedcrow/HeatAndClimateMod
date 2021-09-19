package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFaucet_SUS_LC extends DCTileModelBase {
	// fields
	private final ModelRenderer handle;
	private final ModelRenderer cube_r1;
	private final ModelRenderer faucet;

	public ModelFaucet_SUS_LC() {
		textureWidth = 32;
		textureHeight = 16;

		handle = new ModelRenderer(this);
		handle.setRotationPoint(2.5F, 8.0F, 7.0F);
		setRotation(handle, 0.0F, -1.5708F, 0.0F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-2.5F, 0.0F, -7.0F);
		handle.addChild(cube_r1);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 21, 6, 2.0F, -2.5F, 2.5F, 1, 1, 4, 0.0F, false));

		faucet = new ModelRenderer(this);
		faucet.setRotationPoint(0.0F, 8.0F, 0.0F);
		faucet.cubeList.add(new ModelBox(faucet, 21, 0, -0.5F, -9.0F, 1.5F, 1, 4, 1, 0.0F, false));
		faucet.cubeList.add(new ModelBox(faucet, 0, 4, -0.5F, -10.0F, 1.5F, 1, 1, 6, 0.0F, false));
		faucet.cubeList.add(new ModelBox(faucet, 16, 0, -0.5F, -9.0F, 6.5F, 1, 8, 1, 0.0F, false));
		faucet.cubeList.add(new ModelBox(faucet, 0, 0, -1.0F, -1.0F, 6.0F, 4, 1, 2, 0.0F, false));
		faucet.cubeList.add(new ModelBox(faucet, 0, 5, 2.0F, -3.0F, 6.5F, 1, 2, 1, 0.0F, false));
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		handle.render(0.0625F);
		faucet.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
		float rot = -90F - f;
		float f2 = (float) (rot * Math.PI / 180F);// f * 0.01745329F;

		handle.rotateAngleY = f2;
	}

}
