package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFaucet_SUS_SA extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer valve;
	private final ModelRenderer cube_r1;

	public ModelFaucet_SUS_SA() {
		textureWidth = 32;
		textureHeight = 16;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -1.5F, -5.0F, -1.5F, 3, 5, 3, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 13, 0, -2.0F, -11.0F, -2.0F, 4, 6, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 0, -1.5F, -16.0F, -1.5F, 3, 5, 3, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 13, 11, -0.5F, -8.5F, -3.5F, 1, 1, 2, 0.0F, false));

		valve = new ModelRenderer(this);
		valve.setRotationPoint(0.0F, 0.0F, -2.0F);
		setRotation(valve, 0.0F, 0.0F, 1.5708F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		valve.addChild(cube_r1);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 12, 0.5F, -0.5F, -1.5F, 5, 1, 1, 0.0F, false));
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		main.render(0.0625F);
		valve.render(0.0625F);
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

		valve.rotateAngleZ = f2;
	}

}
