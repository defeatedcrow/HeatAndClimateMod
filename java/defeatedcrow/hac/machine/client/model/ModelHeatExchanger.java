package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHeatExchanger extends DCTileModelBase {
	// fields
	ModelRenderer front;
	ModelRenderer left;
	ModelRenderer right;
	ModelRenderer up;
	ModelRenderer down;
	ModelRenderer pipe1;
	ModelRenderer pipe2;
	ModelRenderer pipe3;
	ModelRenderer panel1;
	ModelRenderer panel2;
	ModelRenderer pane3;
	ModelRenderer pane4;
	ModelRenderer panel5;
	ModelRenderer panel6;
	ModelRenderer panel7;
	ModelRenderer panel8;
	ModelRenderer panel9;
	ModelRenderer panel10;

	public ModelHeatExchanger() {
		textureWidth = 128;
		textureHeight = 64;

		front = new ModelRenderer(this, 0, 0);
		front.addBox(-8F, -8F, -8F, 16, 16, 6);
		front.setRotationPoint(0F, 0F, 0F);
		front.setTextureSize(128, 64);
		front.mirror = true;
		setRotation(front, 0F, 0F, 0F);
		left = new ModelRenderer(this, 46, 0);
		left.addBox(4F, -8F, -2F, 4, 16, 10);
		left.setRotationPoint(0F, 0F, 0F);
		left.setTextureSize(128, 64);
		left.mirror = true;
		setRotation(left, 0F, 0F, 0F);
		right = new ModelRenderer(this, 76, 0);
		right.addBox(-8F, -8F, -2F, 1, 16, 10);
		right.setRotationPoint(0F, 0F, 0F);
		right.setTextureSize(128, 64);
		right.mirror = true;
		setRotation(right, 0F, 0F, 0F);
		up = new ModelRenderer(this, 0, 24);
		up.addBox(-7F, -8F, -2F, 11, 1, 10);
		up.setRotationPoint(0F, 0F, 0F);
		up.setTextureSize(128, 64);
		up.mirror = true;
		setRotation(up, 0F, 0F, 0F);
		down = new ModelRenderer(this, 0, 35);
		down.addBox(-7F, 7F, -2F, 11, 1, 10);
		down.setRotationPoint(0F, 0F, 0F);
		down.setTextureSize(128, 64);
		down.mirror = true;
		setRotation(down, 0F, 0F, 0F);
		pipe1 = new ModelRenderer(this, 46, 52);
		pipe1.addBox(-7F, -4F, 2F, 11, 2, 2);
		pipe1.setRotationPoint(0F, 0F, 0F);
		pipe1.setTextureSize(128, 64);
		pipe1.mirror = true;
		setRotation(pipe1, 0F, 0F, 0F);
		pipe2 = new ModelRenderer(this, 46, 52);
		pipe2.addBox(-7F, 0F, 2F, 11, 2, 2);
		pipe2.setRotationPoint(0F, 0F, 0F);
		pipe2.setTextureSize(128, 64);
		pipe2.mirror = true;
		setRotation(pipe2, 0F, 0F, 0F);
		pipe3 = new ModelRenderer(this, 46, 52);
		pipe3.addBox(-7F, 4F, 2F, 11, 2, 2);
		pipe3.setRotationPoint(0F, 0F, 0F);
		pipe3.setTextureSize(128, 64);
		pipe3.mirror = true;
		setRotation(pipe3, 0F, 0F, 0F);
		panel1 = new ModelRenderer(this, 46, 28);
		panel1.addBox(3F, -7F, -1F, 0, 14, 9);
		panel1.setRotationPoint(0F, 0F, 0F);
		panel1.setTextureSize(128, 64);
		panel1.mirror = true;
		setRotation(panel1, 0F, 0F, 0F);
		panel2 = new ModelRenderer(this, 46, 28);
		panel2.addBox(2F, -7F, -1F, 0, 14, 9);
		panel2.setRotationPoint(0F, 0F, 0F);
		panel2.setTextureSize(128, 64);
		panel2.mirror = true;
		setRotation(panel2, 0F, 0F, 0F);
		pane3 = new ModelRenderer(this, 46, 28);
		pane3.addBox(1F, -7F, -1F, 0, 14, 9);
		pane3.setRotationPoint(0F, 0F, 0F);
		pane3.setTextureSize(128, 64);
		pane3.mirror = true;
		setRotation(pane3, 0F, 0F, 0F);
		pane4 = new ModelRenderer(this, 46, 28);
		pane4.addBox(0F, -7F, -1F, 0, 14, 9);
		pane4.setRotationPoint(0F, 0F, 0F);
		pane4.setTextureSize(128, 64);
		pane4.mirror = true;
		setRotation(pane4, 0F, 0F, 0F);
		panel5 = new ModelRenderer(this, 46, 28);
		panel5.addBox(-1F, -7F, -1F, 0, 14, 9);
		panel5.setRotationPoint(0F, 0F, 0F);
		panel5.setTextureSize(128, 64);
		panel5.mirror = true;
		setRotation(panel5, 0F, 0F, 0F);
		panel6 = new ModelRenderer(this, 46, 28);
		panel6.addBox(-2F, -7F, -1F, 0, 14, 9);
		panel6.setRotationPoint(0F, 0F, 0F);
		panel6.setTextureSize(128, 64);
		panel6.mirror = true;
		setRotation(panel6, 0F, 0F, 0F);
		panel7 = new ModelRenderer(this, 46, 28);
		panel7.addBox(-3F, -7F, -1F, 0, 14, 9);
		panel7.setRotationPoint(0F, 0F, 0F);
		panel7.setTextureSize(128, 64);
		panel7.mirror = true;
		setRotation(panel7, 0F, 0F, 0F);
		panel8 = new ModelRenderer(this, 46, 28);
		panel8.addBox(-4F, -7F, -1F, 0, 14, 9);
		panel8.setRotationPoint(0F, 0F, 0F);
		panel8.setTextureSize(128, 64);
		panel8.mirror = true;
		setRotation(panel8, 0F, 0F, 0F);
		panel9 = new ModelRenderer(this, 46, 28);
		panel9.addBox(-5F, -7F, -1F, 0, 14, 9);
		panel9.setRotationPoint(0F, 0F, 0F);
		panel9.setTextureSize(128, 64);
		panel9.mirror = true;
		setRotation(panel9, 0F, 0F, 0F);
		panel10 = new ModelRenderer(this, 46, 28);
		panel10.addBox(-6F, -7F, -1F, 0, 14, 9);
		panel10.setRotationPoint(0F, 0F, 0F);
		panel10.setTextureSize(128, 64);
		panel10.mirror = true;
		setRotation(panel10, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		front.render(0.0625F);
		left.render(0.0625F);
		right.render(0.0625F);
		up.render(0.0625F);
		down.render(0.0625F);
		pipe1.render(0.0625F);
		pipe2.render(0.0625F);
		pipe3.render(0.0625F);
		panel1.render(0.0625F);
		panel2.render(0.0625F);
		pane3.render(0.0625F);
		pane4.render(0.0625F);
		panel5.render(0.0625F);
		panel6.render(0.0625F);
		panel7.render(0.0625F);
		panel8.render(0.0625F);
		panel9.render(0.0625F);
		panel10.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
