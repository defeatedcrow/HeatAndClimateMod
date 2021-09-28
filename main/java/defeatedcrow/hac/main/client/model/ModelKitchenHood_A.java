package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelKitchenHood_A extends DCTileModelBase {
	// fields
	private final ModelRenderer main1;
	private final ModelRenderer main2;

	public ModelKitchenHood_A() {
		textureWidth = 64;
		textureHeight = 64;

		main1 = new ModelRenderer(this);
		main1.setRotationPoint(0.0F, 8.0F, 0.0F);
		main1.cubeList.add(new ModelBox(main1, 0, 0, -8.0F, -2.0F, -8.0F, 16, 2, 16, 0.0F, false));

		main2 = new ModelRenderer(this);
		main2.setRotationPoint(0.0F, 8.0F, 0.0F);
		main2.cubeList.add(new ModelBox(main2, 0, 19, -4.0F, -16.0F, -4.0F, 8, 14, 8, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		main2.render(0.0625F);
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
