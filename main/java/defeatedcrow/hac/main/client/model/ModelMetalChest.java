package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMetalChest extends DCTileModelBase {
	// fields
	ModelRenderer bottom;
	ModelRenderer back;
	ModelRenderer front;
	ModelRenderer left;
	ModelRenderer right;
	ModelRenderer top;
	ModelRenderer cap;
	ModelRenderer rock;

	public ModelMetalChest() {
		textureWidth = 64;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 48);
		bottom.addBox(-7F, 7F, -7F, 14, 1, 14);
		bottom.setRotationPoint(0F, 16F, 0F);
		bottom.setTextureSize(64, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		back = new ModelRenderer(this, 0, 16);
		back.addBox(-7F, -5F, 6F, 14, 12, 1);
		back.setRotationPoint(0F, 16F, 0F);
		back.setTextureSize(64, 64);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		front = new ModelRenderer(this, 32, 24);
		front.addBox(-7F, -3F, -7F, 14, 10, 1);
		front.setRotationPoint(0F, 16F, 0F);
		front.setTextureSize(64, 64);
		front.mirror = true;
		setRotation(front, 0F, 0F, 0F);
		left = new ModelRenderer(this, 0, 30);
		left.addBox(-6F, -5F, 6F, 12, 12, 1);
		left.setRotationPoint(0F, 16F, 0F);
		left.setTextureSize(64, 64);
		left.mirror = true;
		setRotation(left, 0F, 1.570796F, 0F);
		right = new ModelRenderer(this, 0, 30);
		right.addBox(-6F, -5F, -7F, 12, 12, 1);
		right.setRotationPoint(0F, 16F, 0F);
		right.setTextureSize(64, 64);
		right.mirror = true;
		setRotation(right, 0F, 1.570796F, 0F);
		top = new ModelRenderer(this, 0, 0);
		top.addBox(-7F, -1F, -14F, 14, 1, 14);
		top.setRotationPoint(0F, 11F, 7F);
		top.setTextureSize(64, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		cap = new ModelRenderer(this, 32, 16);
		cap.addBox(-7F, 0F, -14F, 14, 2, 1);
		cap.setRotationPoint(0F, 11F, 7F);
		cap.setTextureSize(64, 64);
		cap.mirror = true;
		setRotation(cap, 0F, 0F, 0F);
		rock = new ModelRenderer(this, 32, 20);
		rock.addBox(-2F, 1F, -15F, 4, 2, 1);
		rock.setRotationPoint(0F, 11F, 7F);
		rock.setTextureSize(64, 64);
		rock.mirror = true;
		setRotation(rock, 0F, 0F, 0F);
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		bottom.render(0.0625F);
		back.render(0.0625F);
		front.render(0.0625F);
		left.render(0.0625F);
		right.render(0.0625F);
		top.render(0.0625F);
		cap.render(0.0625F);
		rock.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {
		float f1 = f * 0.01745329F;

		top.rotateAngleX = f1;
		cap.rotateAngleX = f1;
		rock.rotateAngleX = f1;
	}

}
