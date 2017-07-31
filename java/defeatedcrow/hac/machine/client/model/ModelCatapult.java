package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCatapult extends DCTileModelBase {
	// fields
	public ModelRenderer base;
	public ModelRenderer sideL;
	public ModelRenderer sideR;
	public ModelRenderer shattle;
	public ModelRenderer shattle2;

	public ModelCatapult() {
		textureWidth = 64;
		textureHeight = 64;

		this.base = new ModelRenderer(this, 0, 0);
		this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.base.addBox(-7.0F, -1.0F, -8.0F, 14, 1, 16);
		this.setRotation(this.base, 0.0F, 0.0F, 0.0F);
		this.sideL = new ModelRenderer(this, 0, 18);
		this.sideL.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.sideL.addBox(7.0F, -2.0F, -8.0F, 1, 2, 16);
		this.setRotation(this.sideL, 0.0F, 0.0F, 0.0F);
		this.sideR = new ModelRenderer(this, 0, 18);
		this.sideR.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.sideR.addBox(-8.0F, -2.0F, -8.0F, 1, 2, 16);
		this.setRotation(this.sideR, 0.0F, 0.0F, 0.0F);
		this.shattle = new ModelRenderer(this, 0, 18);
		this.shattle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shattle.addBox(-1.0F, -2.0F, -8.0F, 2, 1, 4);
		this.setRotation(this.shattle, 0.0F, 0.0F, 0.0F);
		this.shattle2 = new ModelRenderer(this, 20, 18);
		this.shattle2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shattle2.addBox(-0.5F, -1.5F, -7.0F, 1, 1, 14);
		this.setRotation(this.shattle2, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		base.render(0.0625F);
		sideL.render(0.0625F);
		sideR.render(0.0625F);
		shattle.render(0.0625F);
		shattle2.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		base.rotateAngleX = f * 3.141592F / 180.0F;
		sideL.rotateAngleX = f * 3.141592F / 180.0F;
		sideR.rotateAngleX = f * 3.141592F / 180.0F;
		shattle.rotateAngleX = f * 3.141592F / 180.0F;
		shattle2.rotateAngleX = f * 3.141592F / 180.0F;
	}

}
