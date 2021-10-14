package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelShitirinB extends DCTileModelBase {
	// fields
	private final ModelRenderer body;
	private final ModelRenderer side4_r1;
	private final ModelRenderer side3_r1;
	private final ModelRenderer side2_r1;
	private final ModelRenderer gotoku1;
	private final ModelRenderer gotoku2;
	private final ModelRenderer gotoku3;
	private final ModelRenderer charcoal;
	private final ModelRenderer char3_r1;
	private final ModelRenderer char2_r1;
	private final ModelRenderer char1_r1;

	public ModelShitirinB() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 8.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -8.0F, -1.0F, -8.0F, 16, 1, 16, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 33, -7.0F, -13.0F, -7.0F, 14, 0, 14, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 17, -8.0F, -16.0F, -8.0F, 16, 15, 1, 0.0F, false));

		side4_r1 = new ModelRenderer(this);
		side4_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(side4_r1);
		setRotation(side4_r1, 0.0F, 1.5708F, 0.0F);
		side4_r1.cubeList.add(new ModelBox(side4_r1, 34, 17, -7.0F, -16.0F, -8.0F, 14, 15, 1, 0.0F, false));

		side3_r1 = new ModelRenderer(this);
		side3_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(side3_r1);
		setRotation(side3_r1, 0.0F, -1.5708F, 0.0F);
		side3_r1.cubeList.add(new ModelBox(side3_r1, 34, 17, -7.0F, -16.0F, -8.0F, 14, 15, 1, 0.0F, false));

		side2_r1 = new ModelRenderer(this);
		side2_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(side2_r1);
		setRotation(side2_r1, 0.0F, 3.1416F, 0.0F);
		side2_r1.cubeList.add(new ModelBox(side2_r1, 0, 17, -8.0F, -16.0F, -8.0F, 16, 15, 1, 0.0F, false));

		gotoku1 = new ModelRenderer(this);
		gotoku1.setRotationPoint(0.0F, 8.0F, 0.0F);
		gotoku1.cubeList.add(new ModelBox(gotoku1, 0, 0, -0.5F, -16.0F, -4.0F, 1, 3, 1, 0.0F, false));
		gotoku1.cubeList.add(new ModelBox(gotoku1, 0, 4, -0.5F, -16.0F, -3.0F, 1, 1, 1, 0.0F, false));

		gotoku2 = new ModelRenderer(this);
		gotoku2.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(gotoku2, 0.0F, -2.0944F, 0.0F);
		gotoku2.cubeList.add(new ModelBox(gotoku2, 0, 0, -0.5F, -16.0F, -4.0F, 1, 3, 1, 0.0F, false));
		gotoku2.cubeList.add(new ModelBox(gotoku2, 0, 4, -0.5F, -16.0F, -3.0F, 1, 1, 1, 0.0F, false));

		gotoku3 = new ModelRenderer(this);
		gotoku3.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(gotoku3, 0.0F, 2.0944F, 0.0F);
		gotoku3.cubeList.add(new ModelBox(gotoku3, 0, 0, -0.5F, -16.0F, -4.0F, 1, 3, 1, 0.0F, false));
		gotoku3.cubeList.add(new ModelBox(gotoku3, 0, 4, -0.5F, -16.0F, -3.0F, 1, 1, 1, 0.0F, false));

		charcoal = new ModelRenderer(this);
		charcoal.setRotationPoint(0.0F, -5.0F, 0.0F);

		char3_r1 = new ModelRenderer(this);
		char3_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		charcoal.addChild(char3_r1);
		setRotation(char3_r1, -0.0873F, 2.618F, -0.1745F);
		char3_r1.cubeList.add(new ModelBox(char3_r1, 48, 8, 1.0F, -2.0F, -1.0F, 4, 2, 2, 0.0F, false));

		char2_r1 = new ModelRenderer(this);
		char2_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		charcoal.addChild(char2_r1);
		setRotation(char2_r1, 0.0F, 0.7854F, 0.2618F);
		char2_r1.cubeList.add(new ModelBox(char2_r1, 48, 4, 0.0F, -2.0F, -1.0F, 4, 2, 2, 0.0F, false));

		char1_r1 = new ModelRenderer(this);
		char1_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		charcoal.addChild(char1_r1);
		setRotation(char1_r1, -0.2618F, -1.0472F, 0.0F);
		char1_r1.cubeList.add(new ModelBox(char1_r1, 48, 0, 0.0F, -2.0F, 0.0F, 4, 2, 2, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(0.0625F);
		body.render(0.0625F);
		gotoku1.render(0.0625F);
		gotoku2.render(0.0625F);
		gotoku3.render(0.0625F);
		charcoal.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {}

}
