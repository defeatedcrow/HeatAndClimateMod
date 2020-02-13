package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBedFuton extends DCTileModelBase {
	// fields
	public ModelRenderer matress;
	public ModelRenderer blanket;
	public ModelRenderer pillow;

	public ModelBedFuton() {
		textureWidth = 128;
		textureHeight = 64;

		matress = new ModelRenderer(this, 0, 40);
		matress.addBox(-7F, 5F, -8F, 30, 3, 16, 0F);
		matress.setRotationPoint(0F, 0F, 0F);
		matress.rotateAngleX = 0F;
		matress.rotateAngleY = 1.570796F;
		matress.rotateAngleZ = 0F;
		matress.mirror = false;
		blanket = new ModelRenderer(this, 46, 0);
		blanket.addBox(-0.5F, 3F, -8.5F, 24, 4, 17, 0F);
		blanket.setRotationPoint(0F, 0F, 0F);
		blanket.rotateAngleX = 0F;
		blanket.rotateAngleY = 1.570796F;
		blanket.rotateAngleZ = 0F;
		blanket.mirror = false;
		pillow = new ModelRenderer(this, 6, 18);
		pillow.addBox(-4F, 2.8F, 1F, 8, 3, 5, 0F);
		pillow.setRotationPoint(0F, 0F, 0F);
		pillow.rotateAngleX = 0.1396263F;
		pillow.rotateAngleY = 0F;
		pillow.rotateAngleZ = 0F;
		pillow.mirror = false;
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		matress.render(0.0625F);
		blanket.render(0.0625F);
		pillow.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}