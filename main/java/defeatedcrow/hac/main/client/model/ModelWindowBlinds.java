package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWindowBlinds extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer top_slat;
	private final ModelRenderer slats;
	private final ModelRenderer slat15_r1;
	private final ModelRenderer slat14_r1;
	private final ModelRenderer slat13_r1;
	private final ModelRenderer slat12_r1;
	private final ModelRenderer slat11_r1;
	private final ModelRenderer slat10_r1;
	private final ModelRenderer slat9_r1;
	private final ModelRenderer slat8_r1;
	private final ModelRenderer slat7_r1;
	private final ModelRenderer slat6_r1;
	private final ModelRenderer slat5_r1;
	private final ModelRenderer slat4_r1;
	private final ModelRenderer slat3_r1;
	private final ModelRenderer slat2_r1;
	private final ModelRenderer slat1_r1;
	private final ModelRenderer bb_main;

	private final boolean open;

	public ModelWindowBlinds(boolean o) {
		open = o;
		textureWidth = 64;
		textureHeight = 64;

		textureWidth = 64;
		textureHeight = 32;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 2, -8.0F, -16.0F, 7.0F, 16, 1, 1, 0.0F, false));

		top_slat = new ModelRenderer(this);
		top_slat.setRotationPoint(0.0F, -7.5F, 7.5F);
		setRotation(top_slat, -0.0873F, 0.0F, 0.0F);
		top_slat.cubeList.add(new ModelBox(top_slat, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slats = new ModelRenderer(this);
		slats.setRotationPoint(0.0F, 8.0F, 0.0F);

		slat15_r1 = new ModelRenderer(this);
		slat15_r1.setRotationPoint(0.0F, -0.5F, 7.5F);
		slats.addChild(slat15_r1);
		setRotation(slat15_r1, -0.0873F, 0.0F, 0.0F);
		slat15_r1.cubeList.add(new ModelBox(slat15_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat14_r1 = new ModelRenderer(this);
		slat14_r1.setRotationPoint(0.0F, -1.5F, 7.5F);
		slats.addChild(slat14_r1);
		setRotation(slat14_r1, -0.0873F, 0.0F, 0.0F);
		slat14_r1.cubeList.add(new ModelBox(slat14_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat13_r1 = new ModelRenderer(this);
		slat13_r1.setRotationPoint(0.0F, -2.5F, 7.5F);
		slats.addChild(slat13_r1);
		setRotation(slat13_r1, -0.0873F, 0.0F, 0.0F);
		slat13_r1.cubeList.add(new ModelBox(slat13_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat12_r1 = new ModelRenderer(this);
		slat12_r1.setRotationPoint(0.0F, -3.5F, 7.5F);
		slats.addChild(slat12_r1);
		setRotation(slat12_r1, -0.0873F, 0.0F, 0.0F);
		slat12_r1.cubeList.add(new ModelBox(slat12_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat11_r1 = new ModelRenderer(this);
		slat11_r1.setRotationPoint(0.0F, -4.5F, 7.5F);
		slats.addChild(slat11_r1);
		setRotation(slat11_r1, -0.0873F, 0.0F, 0.0F);
		slat11_r1.cubeList.add(new ModelBox(slat11_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat10_r1 = new ModelRenderer(this);
		slat10_r1.setRotationPoint(0.0F, -5.5F, 7.5F);
		slats.addChild(slat10_r1);
		setRotation(slat10_r1, -0.0873F, 0.0F, 0.0F);
		slat10_r1.cubeList.add(new ModelBox(slat10_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat9_r1 = new ModelRenderer(this);
		slat9_r1.setRotationPoint(0.0F, -6.5F, 7.5F);
		slats.addChild(slat9_r1);
		setRotation(slat9_r1, -0.0873F, 0.0F, 0.0F);
		slat9_r1.cubeList.add(new ModelBox(slat9_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat8_r1 = new ModelRenderer(this);
		slat8_r1.setRotationPoint(0.0F, -7.5F, 7.5F);
		slats.addChild(slat8_r1);
		setRotation(slat8_r1, -0.0873F, 0.0F, 0.0F);
		slat8_r1.cubeList.add(new ModelBox(slat8_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat7_r1 = new ModelRenderer(this);
		slat7_r1.setRotationPoint(0.0F, -8.5F, 7.5F);
		slats.addChild(slat7_r1);
		setRotation(slat7_r1, -0.0873F, 0.0F, 0.0F);
		slat7_r1.cubeList.add(new ModelBox(slat7_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat6_r1 = new ModelRenderer(this);
		slat6_r1.setRotationPoint(0.0F, -9.5F, 7.5F);
		slats.addChild(slat6_r1);
		setRotation(slat6_r1, -0.0873F, 0.0F, 0.0F);
		slat6_r1.cubeList.add(new ModelBox(slat6_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat5_r1 = new ModelRenderer(this);
		slat5_r1.setRotationPoint(0.0F, -10.5F, 7.5F);
		slats.addChild(slat5_r1);
		setRotation(slat5_r1, -0.0873F, 0.0F, 0.0F);
		slat5_r1.cubeList.add(new ModelBox(slat5_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat4_r1 = new ModelRenderer(this);
		slat4_r1.setRotationPoint(0.0F, -11.5F, 7.5F);
		slats.addChild(slat4_r1);
		setRotation(slat4_r1, -0.0873F, 0.0F, 0.0F);
		slat4_r1.cubeList.add(new ModelBox(slat4_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat3_r1 = new ModelRenderer(this);
		slat3_r1.setRotationPoint(0.0F, -12.5F, 7.5F);
		slats.addChild(slat3_r1);
		setRotation(slat3_r1, -0.0873F, 0.0F, 0.0F);
		slat3_r1.cubeList.add(new ModelBox(slat3_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat2_r1 = new ModelRenderer(this);
		slat2_r1.setRotationPoint(0.0F, -13.5F, 7.5F);
		slats.addChild(slat2_r1);
		setRotation(slat2_r1, -0.0873F, 0.0F, 0.0F);
		slat2_r1.cubeList.add(new ModelBox(slat2_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		slat1_r1 = new ModelRenderer(this);
		slat1_r1.setRotationPoint(0.0F, -14.5F, 7.5F);
		slats.addChild(slat1_r1);
		setRotation(slat1_r1, -0.0873F, 0.0F, 0.0F);
		slat1_r1.cubeList.add(new ModelBox(slat1_r1, 0, 0, -8.0F, -0.5F, 0.0F, 16, 1, 0, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 8.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 5, -8.0F, -16.0F, 7.3F, 16, 16, 0, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		slats.render(0.0625F);
		bb_main.render(0.0625F);
	}

	public void renderTop() {
		main.render(0.0625F);
	}

	public void renderBottom() {
		top_slat.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {
		if (open) {
			slat1_r1.rotateAngleX = -1.3095F;
			slat2_r1.rotateAngleX = -1.3095F;
			slat3_r1.rotateAngleX = -1.3095F;
			slat4_r1.rotateAngleX = -1.3095F;
			slat5_r1.rotateAngleX = -1.3095F;
			slat6_r1.rotateAngleX = -1.3095F;
			slat7_r1.rotateAngleX = -1.3095F;
			slat8_r1.rotateAngleX = -1.3095F;
			slat9_r1.rotateAngleX = -1.3095F;
			slat10_r1.rotateAngleX = -1.3095F;
			slat11_r1.rotateAngleX = -1.3095F;
			slat12_r1.rotateAngleX = -1.3095F;
			slat13_r1.rotateAngleX = -1.3095F;
			slat14_r1.rotateAngleX = -1.3095F;
			slat15_r1.rotateAngleX = -1.3095F;
			top_slat.rotateAngleX = -1.3095F;
		} else {
			slat1_r1.rotateAngleX = -0.0873F;
			slat2_r1.rotateAngleX = -0.0873F;
			slat3_r1.rotateAngleX = -0.0873F;
			slat4_r1.rotateAngleX = -0.0873F;
			slat5_r1.rotateAngleX = -0.0873F;
			slat6_r1.rotateAngleX = -0.0873F;
			slat7_r1.rotateAngleX = -0.0873F;
			slat8_r1.rotateAngleX = -0.0873F;
			slat9_r1.rotateAngleX = -0.0873F;
			slat10_r1.rotateAngleX = -0.0873F;
			slat11_r1.rotateAngleX = -0.0873F;
			slat12_r1.rotateAngleX = -0.0873F;
			slat13_r1.rotateAngleX = -0.0873F;
			slat14_r1.rotateAngleX = -0.0873F;
			slat15_r1.rotateAngleX = -0.0873F;
			top_slat.rotateAngleX = -0.0873F;
		}
	}

}
