package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockAgingBarrel extends DCTileModelBase {

	private final ModelRenderer legs;
	private final ModelRenderer base;
	private final ModelRenderer side1;
	private final ModelRenderer up;
	private final ModelRenderer down;
	private final ModelRenderer side2;
	private final ModelRenderer up2;
	private final ModelRenderer down2;
	private final ModelRenderer side3;
	private final ModelRenderer up3;
	private final ModelRenderer down3;
	private final ModelRenderer side4;
	private final ModelRenderer up4;
	private final ModelRenderer down4;
	private final ModelRenderer side5;
	private final ModelRenderer up5;
	private final ModelRenderer down5;
	private final ModelRenderer side6;
	private final ModelRenderer up6;
	private final ModelRenderer down6;
	private final ModelRenderer side7;
	private final ModelRenderer up7;
	private final ModelRenderer down7;
	private final ModelRenderer side8;
	private final ModelRenderer up8;
	private final ModelRenderer down8;
	private final ModelRenderer side9;
	private final ModelRenderer up9;
	private final ModelRenderer down9;
	private final ModelRenderer side10;
	private final ModelRenderer up10;
	private final ModelRenderer down10;
	private final ModelRenderer side11;
	private final ModelRenderer up11;
	private final ModelRenderer down11;
	private final ModelRenderer side12;
	private final ModelRenderer up12;
	private final ModelRenderer down12;

	public ModelBlockAgingBarrel() {

		textureWidth = 64;
		textureHeight = 32;

		legs = new ModelRenderer(this);
		legs.setRotationPoint(0.0F, 24.0F, 0.0F);
		legs.cubeList.add(new ModelBox(legs, 0, 28, -5.0F, -13.0F, -8.0F, 10, 1, 2, 0.0F, false));
		legs.cubeList.add(new ModelBox(legs, 0, 28, -5.0F, -4.0F, -8.0F, 10, 1, 2, 0.0F, false));

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -6.0F, 0.0F, -6.0F, 12, 0, 12, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 15, -6.0F, -16.0F, -6.0F, 12, 0, 12, 0.0F, false));

		side1 = new ModelRenderer(this);
		side1.setRotationPoint(0.0F, 16.0F, 0.0F);
		side1.cubeList.add(new ModelBox(side1, 0, 9, -2.0F, -3.0F, -8.0F, 4, 6, 1, 0.0F, false));

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -4.0F, 0.0F);
		side1.addChild(up);
		setRotationAngle(up, -0.1745F, 0.0F, 0.0F);
		up.cubeList.add(new ModelBox(up, 0, 0, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		down = new ModelRenderer(this);
		down.setRotationPoint(0.0F, 4.0F, 0.0F);
		side1.addChild(down);
		setRotationAngle(down, 0.1745F, 0.0F, 0.0F);
		down.cubeList.add(new ModelBox(down, 0, 18, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		side2 = new ModelRenderer(this);
		side2.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side2, 0.0F, -0.5236F, 0.0F);
		side2.cubeList.add(new ModelBox(side2, 0, 9, -2.0F, -3.0F, -8.0F, 4, 6, 1, 0.0F, false));

		up2 = new ModelRenderer(this);
		up2.setRotationPoint(0.0F, -4.0F, 0.0F);
		side2.addChild(up2);
		setRotationAngle(up2, -0.1745F, 0.0F, 0.0F);
		up2.cubeList.add(new ModelBox(up2, 0, 0, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		down2 = new ModelRenderer(this);
		down2.setRotationPoint(0.0F, 4.0F, 0.0F);
		side2.addChild(down2);
		setRotationAngle(down2, 0.1745F, 0.0F, 0.0F);
		down2.cubeList.add(new ModelBox(down2, 0, 18, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		side3 = new ModelRenderer(this);
		side3.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side3, 0.0F, -1.0472F, 0.0F);
		side3.cubeList.add(new ModelBox(side3, 0, 9, -2.0F, -3.0F, -8.0F, 4, 6, 1, 0.0F, false));

		up3 = new ModelRenderer(this);
		up3.setRotationPoint(0.0F, -4.0F, 0.0F);
		side3.addChild(up3);
		setRotationAngle(up3, -0.1745F, 0.0F, 0.0F);
		up3.cubeList.add(new ModelBox(up3, 0, 0, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		down3 = new ModelRenderer(this);
		down3.setRotationPoint(0.0F, 4.0F, 0.0F);
		side3.addChild(down3);
		setRotationAngle(down3, 0.1745F, 0.0F, 0.0F);
		down3.cubeList.add(new ModelBox(down3, 0, 18, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		side4 = new ModelRenderer(this);
		side4.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side4, 0.0F, -1.5708F, 0.0F);
		side4.cubeList.add(new ModelBox(side4, 0, 9, -2.0F, -3.0F, -8.0F, 4, 6, 1, 0.0F, false));

		up4 = new ModelRenderer(this);
		up4.setRotationPoint(0.0F, -4.0F, 0.0F);
		side4.addChild(up4);
		setRotationAngle(up4, -0.1745F, 0.0F, 0.0F);
		up4.cubeList.add(new ModelBox(up4, 0, 0, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		down4 = new ModelRenderer(this);
		down4.setRotationPoint(0.0F, 4.0F, 0.0F);
		side4.addChild(down4);
		setRotationAngle(down4, 0.1745F, 0.0F, 0.0F);
		down4.cubeList.add(new ModelBox(down4, 0, 18, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		side5 = new ModelRenderer(this);
		side5.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side5, 0.0F, -2.0944F, 0.0F);
		side5.cubeList.add(new ModelBox(side5, 0, 9, -2.0F, -3.0F, -8.0F, 4, 6, 1, 0.0F, false));

		up5 = new ModelRenderer(this);
		up5.setRotationPoint(0.0F, -4.0F, 0.0F);
		side5.addChild(up5);
		setRotationAngle(up5, -0.1745F, 0.0F, 0.0F);
		up5.cubeList.add(new ModelBox(up5, 0, 0, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		down5 = new ModelRenderer(this);
		down5.setRotationPoint(0.0F, 4.0F, 0.0F);
		side5.addChild(down5);
		setRotationAngle(down5, 0.1745F, 0.0F, 0.0F);
		down5.cubeList.add(new ModelBox(down5, 0, 18, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		side6 = new ModelRenderer(this);
		side6.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side6, 0.0F, -2.618F, 0.0F);
		side6.cubeList.add(new ModelBox(side6, 0, 9, -2.0F, -3.0F, -8.0F, 4, 6, 1, 0.0F, false));

		up6 = new ModelRenderer(this);
		up6.setRotationPoint(0.0F, -4.0F, 0.0F);
		side6.addChild(up6);
		setRotationAngle(up6, -0.1745F, 0.0F, 0.0F);
		up6.cubeList.add(new ModelBox(up6, 0, 0, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		down6 = new ModelRenderer(this);
		down6.setRotationPoint(0.0F, 4.0F, 0.0F);
		side6.addChild(down6);
		setRotationAngle(down6, 0.1745F, 0.0F, 0.0F);
		down6.cubeList.add(new ModelBox(down6, 0, 18, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		side7 = new ModelRenderer(this);
		side7.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side7, 0.0F, 3.1416F, 0.0F);
		side7.cubeList.add(new ModelBox(side7, 0, 9, -2.0F, -3.0F, -8.0F, 4, 6, 1, 0.0F, false));

		up7 = new ModelRenderer(this);
		up7.setRotationPoint(0.0F, -4.0F, 0.0F);
		side7.addChild(up7);
		setRotationAngle(up7, -0.1745F, 0.0F, 0.0F);
		up7.cubeList.add(new ModelBox(up7, 0, 0, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		down7 = new ModelRenderer(this);
		down7.setRotationPoint(0.0F, 4.0F, 0.0F);
		side7.addChild(down7);
		setRotationAngle(down7, 0.1745F, 0.0F, 0.0F);
		down7.cubeList.add(new ModelBox(down7, 0, 18, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		side8 = new ModelRenderer(this);
		side8.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side8, 0.0F, 0.5236F, 0.0F);
		side8.cubeList.add(new ModelBox(side8, 0, 9, -2.0F, -3.0F, -8.0F, 4, 6, 1, 0.0F, false));

		up8 = new ModelRenderer(this);
		up8.setRotationPoint(0.0F, -4.0F, 0.0F);
		side8.addChild(up8);
		setRotationAngle(up8, -0.1745F, 0.0F, 0.0F);
		up8.cubeList.add(new ModelBox(up8, 0, 0, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		down8 = new ModelRenderer(this);
		down8.setRotationPoint(0.0F, 4.0F, 0.0F);
		side8.addChild(down8);
		setRotationAngle(down8, 0.1745F, 0.0F, 0.0F);
		down8.cubeList.add(new ModelBox(down8, 0, 18, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		side9 = new ModelRenderer(this);
		side9.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side9, 0.0F, 1.0472F, 0.0F);
		side9.cubeList.add(new ModelBox(side9, 0, 9, -2.0F, -3.0F, -8.0F, 4, 6, 1, 0.0F, false));

		up9 = new ModelRenderer(this);
		up9.setRotationPoint(0.0F, -4.0F, 0.0F);
		side9.addChild(up9);
		setRotationAngle(up9, -0.1745F, 0.0F, 0.0F);
		up9.cubeList.add(new ModelBox(up9, 0, 0, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		down9 = new ModelRenderer(this);
		down9.setRotationPoint(0.0F, 4.0F, 0.0F);
		side9.addChild(down9);
		setRotationAngle(down9, 0.1745F, 0.0F, 0.0F);
		down9.cubeList.add(new ModelBox(down9, 0, 18, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		side10 = new ModelRenderer(this);
		side10.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side10, 0.0F, 1.5708F, 0.0F);
		side10.cubeList.add(new ModelBox(side10, 0, 9, -2.0F, -3.0F, -8.0F, 4, 6, 1, 0.0F, false));

		up10 = new ModelRenderer(this);
		up10.setRotationPoint(0.0F, -4.0F, 0.0F);
		side10.addChild(up10);
		setRotationAngle(up10, -0.1745F, 0.0F, 0.0F);
		up10.cubeList.add(new ModelBox(up10, 0, 0, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		down10 = new ModelRenderer(this);
		down10.setRotationPoint(0.0F, 4.0F, 0.0F);
		side10.addChild(down10);
		setRotationAngle(down10, 0.1745F, 0.0F, 0.0F);
		down10.cubeList.add(new ModelBox(down10, 0, 18, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		side11 = new ModelRenderer(this);
		side11.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side11, 0.0F, 2.0944F, 0.0F);
		side11.cubeList.add(new ModelBox(side11, 0, 9, -2.0F, -3.0F, -8.0F, 4, 6, 1, 0.0F, false));

		up11 = new ModelRenderer(this);
		up11.setRotationPoint(0.0F, -4.0F, 0.0F);
		side11.addChild(up11);
		setRotationAngle(up11, -0.1745F, 0.0F, 0.0F);
		up11.cubeList.add(new ModelBox(up11, 0, 0, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		down11 = new ModelRenderer(this);
		down11.setRotationPoint(0.0F, 4.0F, 0.0F);
		side11.addChild(down11);
		setRotationAngle(down11, 0.1745F, 0.0F, 0.0F);
		down11.cubeList.add(new ModelBox(down11, 0, 18, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		side12 = new ModelRenderer(this);
		side12.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(side12, 0.0F, 2.618F, 0.0F);
		side12.cubeList.add(new ModelBox(side12, 0, 9, -2.0F, -3.0F, -8.0F, 4, 6, 1, 0.0F, false));

		up12 = new ModelRenderer(this);
		up12.setRotationPoint(0.0F, -4.0F, 0.0F);
		side12.addChild(up12);
		setRotationAngle(up12, -0.1745F, 0.0F, 0.0F);
		up12.cubeList.add(new ModelBox(up12, 0, 0, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));

		down12 = new ModelRenderer(this);
		down12.setRotationPoint(0.0F, 4.0F, 0.0F);
		side12.addChild(down12);
		setRotationAngle(down12, 0.1745F, 0.0F, 0.0F);
		down12.cubeList.add(new ModelBox(down12, 0, 18, -2.0F, -3.0F, -7.5F, 4, 6, 1, 0.0F, false));
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		legs.render(scale);
		base.render(scale);
		side1.render(scale);
		side2.render(scale);
		side3.render(scale);
		side4.render(scale);
		side5.render(scale);
		side6.render(scale);
		side7.render(scale);
		side8.render(scale);
		side9.render(scale);
		side10.render(scale);
		side11.render(scale);
		side12.render(scale);
	}

	private void setRotationAngle(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}

}
