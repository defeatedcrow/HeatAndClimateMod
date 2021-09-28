package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFaucet_SUS_SB extends DCTileModelBase {
	// fields
	private final ModelRenderer main;

	public ModelFaucet_SUS_SB() {
		textureWidth = 32;
		textureHeight = 16;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -1.0F, -16.0F, -1.0F, 2, 2, 2, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 9, 0, -2.0F, -14.0F, -2.0F, 4, 1, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 8, -3.5F, -13.0F, -3.5F, 7, 1, 7, 0.0F, false));
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		main.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
	}

}
