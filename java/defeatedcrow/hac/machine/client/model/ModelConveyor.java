package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelConveyor extends DCTileModelBase {
	// fields
	ModelRenderer belt;
	ModelRenderer side1;
	ModelRenderer side2;

	public ModelConveyor() {
		textureWidth = 64;
		textureHeight = 32;

		belt = new ModelRenderer(this, 0, 0);
		belt.addBox(-8F, -1F, -6F, 16, 2, 12);
		belt.setRotationPoint(0F, 0F, 0F);
		belt.setTextureSize(64, 32);
		belt.mirror = true;
		setRotation(belt, 0F, 0F, 0F);
		side1 = new ModelRenderer(this, 0, 16);
		side1.addBox(-8F, -2F, -7F, 16, 4, 1);
		side1.setRotationPoint(0F, 0F, 0F);
		side1.setTextureSize(64, 32);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		side2 = new ModelRenderer(this, 0, 16);
		side2.addBox(-8F, -2F, 6F, 16, 4, 1);
		side2.setRotationPoint(0F, 0F, 0F);
		side2.setTextureSize(64, 32);
		side2.mirror = true;
		setRotation(side2, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		belt.render(0.0625F);
		side1.render(0.0625F);
		side2.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
