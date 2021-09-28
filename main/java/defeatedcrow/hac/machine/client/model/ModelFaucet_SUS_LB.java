package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFaucet_SUS_LB extends DCTileModelBase {
	// fields
	private final ModelRenderer handle;
	private final ModelRenderer cube_r1;
	private final ModelRenderer faucet;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;

	public ModelFaucet_SUS_LB() {
		textureWidth = 32;
		textureHeight = 32;

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 1.0F, 7.0F);
		setRotation(handle, -0.1745F, 0.0F, 0.0F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		handle.addChild(cube_r1);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 16, -1.0F, -1.0F, -3.0F, 2, 1, 4, 0.0F, false));

		faucet = new ModelRenderer(this);
		faucet.setRotationPoint(0.0F, 8.0F, 0.0F);
		faucet.cubeList.add(new ModelBox(faucet, 19, 0, -1.0F, -7.0F, 6.0F, 2, 6, 2, 0.0F, false));
		faucet.cubeList.add(new ModelBox(faucet, 1, 0, -3.0F, -1.0F, 6.0F, 6, 1, 2, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 7.0F);
		faucet.addChild(cube_r2);
		setRotation(cube_r2, -0.3491F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 22, 9, -1.0F, -3.75F, -9.3F, 2, 1, 2, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 7.0F);
		faucet.addChild(cube_r3);
		setRotation(cube_r3, -0.5236F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 5, -1.0F, -3.0F, -10.0F, 2, 1, 8, 0.0F, false));
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
		float rot = -10F + f * 0.5F;
		float f2 = (float) (rot * Math.PI / 180F);// f * 0.01745329F;

		handle.rotateAngleX = f2;
	}

}
