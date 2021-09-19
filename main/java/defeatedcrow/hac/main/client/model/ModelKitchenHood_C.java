package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelKitchenHood_C extends DCTileModelBase {
	// fields
	private final ModelRenderer main1;
	private final ModelRenderer cube_r1;

	public ModelKitchenHood_C() {
		textureWidth = 64;
		textureHeight = 64;

		main1 = new ModelRenderer(this);
		main1.setRotationPoint(0.0F, 8.0F, 0.0F);
		main1.cubeList.add(new ModelBox(main1, 0, 19, -8.0F, -16.0F, 0.0F, 16, 16, 8, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		main1.addChild(cube_r1);
		setRotation(cube_r1, -0.5236F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -8.0F, -10.0F, -8.0F, 16, 10, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 12, -7.0F, -10.0F, -7.0F, 14, 1, 2, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 35, 0, 6.9F, -10.0F, -7.0F, 1, 10, 7, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 35, 0, -7.9F, -10.0F, -7.0F, 1, 10, 7, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		main1.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {}

}
