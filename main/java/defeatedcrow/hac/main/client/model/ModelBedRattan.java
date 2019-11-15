package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBedRattan extends DCTileModelBase {
	// fields
	public ModelRenderer leg11;
	public ModelRenderer leg12;
	public ModelRenderer matress;
	public ModelRenderer headboard;
	public ModelRenderer blanket;
	public ModelRenderer pillow;

	public ModelBedRattan() {
		textureWidth = 128;
		textureHeight = 64;

		leg11 = new ModelRenderer(this, 0, 0);
		leg11.addBox(-8F, -4F, 7F, 1, 12, 1, 0F);
		leg11.setRotationPoint(0F, 0F, 0F);
		leg11.rotateAngleX = 0F;
		leg11.rotateAngleY = 0F;
		leg11.rotateAngleZ = 0F;
		leg11.mirror = false;
		leg12 = new ModelRenderer(this, 0, 0);
		leg12.addBox(7F, -4F, 7F, 1, 12, 1, 0F);
		leg12.setRotationPoint(0F, 0F, 0F);
		leg12.rotateAngleX = 0F;
		leg12.rotateAngleY = 0F;
		leg12.rotateAngleZ = 0F;
		leg12.mirror = false;
		matress = new ModelRenderer(this, 0, 40);
		matress.addBox(-7F, 2F, -8F, 30, 6, 16, 0F);
		matress.setRotationPoint(0F, 0F, 0F);
		matress.rotateAngleX = 0F;
		matress.rotateAngleY = 1.570796F;
		matress.rotateAngleZ = 0F;
		matress.mirror = false;
		headboard = new ModelRenderer(this, 6, 0);
		headboard.addBox(-7F, -4F, 7.5F, 14, 12, 0, 0F);
		headboard.setRotationPoint(0F, 0F, 0F);
		headboard.rotateAngleX = 0F;
		headboard.rotateAngleY = 0F;
		headboard.rotateAngleZ = 0F;
		headboard.mirror = false;
		blanket = new ModelRenderer(this, 46, 0);
		blanket.addBox(-0.5F, 0F, -8.5F, 24, 6, 17, 0F);
		blanket.setRotationPoint(0F, 0F, 0F);
		blanket.rotateAngleX = 0F;
		blanket.rotateAngleY = 1.570796F;
		blanket.rotateAngleZ = 0F;
		blanket.mirror = false;
		pillow = new ModelRenderer(this, 6, 18);
		pillow.addBox(-4F, -0.2F, 1F, 8, 3, 6, 0F);
		pillow.setRotationPoint(0F, 0F, 0F);
		pillow.rotateAngleX = 0.1396263F;
		pillow.rotateAngleY = 0F;
		pillow.rotateAngleZ = 0F;
		pillow.mirror = false;
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		leg11.render(0.0625F);
		leg12.render(0.0625F);
		matress.render(0.0625F);
		headboard.render(0.0625F);
		blanket.render(0.0625F);
		pillow.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}