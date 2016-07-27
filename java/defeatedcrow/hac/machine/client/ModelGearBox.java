package defeatedcrow.hac.machine.client;

import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCTileModelBase;

@SideOnly(Side.CLIENT)
public class ModelGearBox extends DCTileModelBase {
	// fields
	ModelRenderer box;
	ModelRenderer pad;

	public ModelGearBox() {
		textureWidth = 64;
		textureHeight = 64;

		box = new ModelRenderer(this, 0, 0);
		box.addBox(-8F, -7F, -8F, 16, 15, 16);
		box.setRotationPoint(0F, 0F, 0F);
		box.setTextureSize(64, 32);
		box.mirror = true;
		setRotation(box, 0F, 0F, 0F);
		pad = new ModelRenderer(this, 0, 32);
		pad.addBox(-3F, -8F, -3F, 6, 1, 6);
		pad.setRotationPoint(0F, 0F, 0F);
		pad.setTextureSize(64, 64);
		pad.mirror = true;
		setRotation(pad, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		box.render(0.0625F);
		pad.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
