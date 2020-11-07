package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelMonitorTemp extends DCTileModelBase {

	ModelRenderer bottom;

	public ModelMonitorTemp() {
		textureWidth = 16;
		textureHeight = 16;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-6F, -3F, -7.5F, 12, 1, 1);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.setTextureSize(16, 16);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		bottom.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
