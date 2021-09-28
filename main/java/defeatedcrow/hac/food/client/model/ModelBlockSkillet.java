package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockSkillet extends DCTileModelBase {
	// fields
	public ModelRenderer bottom;
	public ModelRenderer side1;
	public ModelRenderer side2;
	public ModelRenderer side3;
	public ModelRenderer side4;
	public ModelRenderer handle;

	public ModelBlockSkillet() {
		textureWidth = 64;
		textureHeight = 32;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-4F, 0F, -2F, 8, 1, 8, 0F);
		bottom.setRotationPoint(0F, 7F, 0F);
		bottom.rotateAngleX = 0F;
		bottom.rotateAngleY = 0F;
		bottom.rotateAngleZ = 0F;
		bottom.mirror = false;
		side1 = new ModelRenderer(this, 0, 10);
		side1.addBox(-4F, 0F, 0F, 8, 3, 1, 0F);
		side1.setRotationPoint(0F, 5F, -3F);
		side1.rotateAngleX = 0.3490658F;
		side1.rotateAngleY = 0F;
		side1.rotateAngleZ = 0F;
		side1.mirror = false;
		side2 = new ModelRenderer(this, 0, 10);
		side2.addBox(-4F, 0F, 0F, 8, 3, 1, 0F);
		side2.setRotationPoint(0F, 5F, 7F);
		side2.rotateAngleX = 0.3490658F;
		side2.rotateAngleY = 3.141593F;
		side2.rotateAngleZ = 0F;
		side2.mirror = false;
		side3 = new ModelRenderer(this, 0, 10);
		side3.addBox(-4F, 0F, 0F, 8, 3, 1, 0F);
		side3.setRotationPoint(-5F, 5F, 2F);
		side3.rotateAngleX = 0.3490658F;
		side3.rotateAngleY = 1.570796F;
		side3.rotateAngleZ = 0F;
		side3.mirror = false;
		side4 = new ModelRenderer(this, 0, 10);
		side4.addBox(-4F, 0F, 0F, 8, 3, 1, 0F);
		side4.setRotationPoint(5F, 5F, 2F);
		side4.rotateAngleX = 0.3490658F;
		side4.rotateAngleY = -1.570796F;
		side4.rotateAngleZ = 0F;
		side4.mirror = false;
		handle = new ModelRenderer(this, 33, 0);
		handle.addBox(-1F, 0F, -10F, 2, 1, 8, 0F);
		handle.setRotationPoint(0F, 4.7F, 0F);
		handle.rotateAngleX = 0F;
		handle.rotateAngleY = 0F;
		handle.rotateAngleZ = 0F;
		handle.mirror = false;
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		bottom.render(0.0625F);
		side1.render(0.0625F);
		side2.render(0.0625F);
		side3.render(0.0625F);
		side4.render(0.0625F);
		handle.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
