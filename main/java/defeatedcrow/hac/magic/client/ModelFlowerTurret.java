package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFlowerTurret extends DCTileModelBase {

	private final ModelRenderer leaf1;
	private final ModelRenderer leaf1_4_r1;
	private final ModelRenderer leaf1_2_r1;
	private final ModelRenderer leaf1_1_r1;
	private final ModelRenderer leaf2;
	private final ModelRenderer leaf1_4_r2;
	private final ModelRenderer leaf1_2_r2;
	private final ModelRenderer leaf1_1_r2;
	private final ModelRenderer leaf3;
	private final ModelRenderer leaf1_4_r3;
	private final ModelRenderer leaf1_2_r3;
	private final ModelRenderer leaf1_1_r3;
	private final ModelRenderer flower;
	private final ModelRenderer petal4_2_r1;
	private final ModelRenderer petal4_1_r1;
	private final ModelRenderer petal3_2_r1;
	private final ModelRenderer petal3_1_r1;
	private final ModelRenderer petal2_2_r1;
	private final ModelRenderer petal2_1_r1;
	private final ModelRenderer petal1_2_r1;
	private final ModelRenderer petal1_1_r1;
	private final ModelRenderer stem;
	private final ModelRenderer stem3_r1;
	private final ModelRenderer stem2_r1;

	public ModelFlowerTurret() {

		textureWidth = 64;
		textureHeight = 32;

		leaf1 = new ModelRenderer(this);
		leaf1.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaf1.cubeList.add(new ModelBox(leaf1, 30, 0, -10.0F, -2.0F, -2.0F, 2, 0, 4, 0.0F, false));

		leaf1_4_r1 = new ModelRenderer(this);
		leaf1_4_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaf1.addChild(leaf1_4_r1);
		setRotationAngle(leaf1_4_r1, 0.0F, 0.0F, -0.2182F);
		leaf1_4_r1.cubeList.add(new ModelBox(leaf1_4_r1, 38, 0, -11.0F, -4.0F, -1.0F, 2, 0, 2, 0.0F, false));

		leaf1_2_r1 = new ModelRenderer(this);
		leaf1_2_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaf1.addChild(leaf1_2_r1);
		setRotationAngle(leaf1_2_r1, 0.0F, 0.0F, 0.1745F);
		leaf1_2_r1.cubeList.add(new ModelBox(leaf1_2_r1, 14, 0, -8.5F, -0.5F, -3.0F, 6, 0, 6, 0.0F, false));

		leaf1_1_r1 = new ModelRenderer(this);
		leaf1_1_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaf1.addChild(leaf1_1_r1);
		setRotationAngle(leaf1_1_r1, 0.0F, 0.0F, 0.3491F);
		leaf1_1_r1.cubeList.add(new ModelBox(leaf1_1_r1, 10, 0, -3.0F, 0.0F, -2.0F, 2, 0, 4, 0.0F, false));

		leaf2 = new ModelRenderer(this);
		leaf2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(leaf2, 0.0F, -2.0944F, 0.0F);
		leaf2.cubeList.add(new ModelBox(leaf2, 30, 0, -10.0F, -2.0F, -2.0F, 2, 0, 4, 0.0F, false));

		leaf1_4_r2 = new ModelRenderer(this);
		leaf1_4_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaf2.addChild(leaf1_4_r2);
		setRotationAngle(leaf1_4_r2, 0.0F, 0.0F, -0.2182F);
		leaf1_4_r2.cubeList.add(new ModelBox(leaf1_4_r2, 38, 0, -11.0F, -4.0F, -1.0F, 2, 0, 2, 0.0F, false));

		leaf1_2_r2 = new ModelRenderer(this);
		leaf1_2_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaf2.addChild(leaf1_2_r2);
		setRotationAngle(leaf1_2_r2, 0.0F, 0.0F, 0.1745F);
		leaf1_2_r2.cubeList.add(new ModelBox(leaf1_2_r2, 14, 0, -8.5F, -0.5F, -3.0F, 6, 0, 6, 0.0F, false));

		leaf1_1_r2 = new ModelRenderer(this);
		leaf1_1_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaf2.addChild(leaf1_1_r2);
		setRotationAngle(leaf1_1_r2, 0.0F, 0.0F, 0.3491F);
		leaf1_1_r2.cubeList.add(new ModelBox(leaf1_1_r2, 10, 0, -3.0F, 0.0F, -2.0F, 2, 0, 4, 0.0F, false));

		leaf3 = new ModelRenderer(this);
		leaf3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(leaf3, 0.0F, 2.0944F, 0.0F);
		leaf3.cubeList.add(new ModelBox(leaf3, 30, 0, -10.0F, -2.0F, -2.0F, 2, 0, 4, 0.0F, false));

		leaf1_4_r3 = new ModelRenderer(this);
		leaf1_4_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaf3.addChild(leaf1_4_r3);
		setRotationAngle(leaf1_4_r3, 0.0F, 0.0F, -0.2182F);
		leaf1_4_r3.cubeList.add(new ModelBox(leaf1_4_r3, 38, 0, -11.0F, -4.0F, -1.0F, 2, 0, 2, 0.0F, false));

		leaf1_2_r3 = new ModelRenderer(this);
		leaf1_2_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaf3.addChild(leaf1_2_r3);
		setRotationAngle(leaf1_2_r3, 0.0F, 0.0F, 0.1745F);
		leaf1_2_r3.cubeList.add(new ModelBox(leaf1_2_r3, 14, 0, -8.5F, -0.5F, -3.0F, 6, 0, 6, 0.0F, false));

		leaf1_1_r3 = new ModelRenderer(this);
		leaf1_1_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		leaf3.addChild(leaf1_1_r3);
		setRotationAngle(leaf1_1_r3, 0.0F, 0.0F, 0.3491F);
		leaf1_1_r3.cubeList.add(new ModelBox(leaf1_1_r3, 10, 0, -3.0F, 0.0F, -2.0F, 2, 0, 4, 0.0F, false));

		flower = new ModelRenderer(this);
		flower.setRotationPoint(0.0F, -11.0F, 0.0F);
		flower.cubeList.add(new ModelBox(flower, 46, 0, -1.5F, -1.5F, -3.0F, 3, 3, 3, 0.0F, false));

		petal4_2_r1 = new ModelRenderer(this);
		petal4_2_r1.setRotationPoint(0.0F, 0.0F, -1.0F);
		flower.addChild(petal4_2_r1);
		setRotationAngle(petal4_2_r1, 0.6109F, 0.0F, -0.7854F);
		petal4_2_r1.cubeList.add(new ModelBox(petal4_2_r1, 12, 12, -2.5F, -9.0F, 0.5F, 5, 6, 1, 0.0F, false));

		petal4_1_r1 = new ModelRenderer(this);
		petal4_1_r1.setRotationPoint(0.0F, 0.0F, -1.0F);
		flower.addChild(petal4_1_r1);
		setRotationAngle(petal4_1_r1, 0.2618F, 0.0F, -0.7854F);
		petal4_1_r1.cubeList.add(new ModelBox(petal4_1_r1, 0, 16, -2.0F, -3.0F, -0.5F, 4, 2, 1, 0.0F, false));

		petal3_2_r1 = new ModelRenderer(this);
		petal3_2_r1.setRotationPoint(0.0F, 0.0F, -1.0F);
		flower.addChild(petal3_2_r1);
		setRotationAngle(petal3_2_r1, 0.6109F, 0.0F, -2.3562F);
		petal3_2_r1.cubeList.add(new ModelBox(petal3_2_r1, 12, 12, -2.5F, -9.0F, 0.5F, 5, 6, 1, 0.0F, false));

		petal3_1_r1 = new ModelRenderer(this);
		petal3_1_r1.setRotationPoint(0.0F, 0.0F, -1.0F);
		flower.addChild(petal3_1_r1);
		setRotationAngle(petal3_1_r1, 0.2618F, 0.0F, -2.3562F);
		petal3_1_r1.cubeList.add(new ModelBox(petal3_1_r1, 0, 16, -2.0F, -3.0F, -0.5F, 4, 2, 1, 0.0F, false));

		petal2_2_r1 = new ModelRenderer(this);
		petal2_2_r1.setRotationPoint(0.0F, 0.0F, -1.0F);
		flower.addChild(petal2_2_r1);
		setRotationAngle(petal2_2_r1, 0.6109F, 0.0F, 2.3562F);
		petal2_2_r1.cubeList.add(new ModelBox(petal2_2_r1, 12, 12, -2.5F, -9.0F, 0.5F, 5, 6, 1, 0.0F, false));

		petal2_1_r1 = new ModelRenderer(this);
		petal2_1_r1.setRotationPoint(0.0F, 0.0F, -1.0F);
		flower.addChild(petal2_1_r1);
		setRotationAngle(petal2_1_r1, 0.2618F, 0.0F, 2.3562F);
		petal2_1_r1.cubeList.add(new ModelBox(petal2_1_r1, 0, 16, -2.0F, -3.0F, -0.5F, 4, 2, 1, 0.0F, false));

		petal1_2_r1 = new ModelRenderer(this);
		petal1_2_r1.setRotationPoint(0.0F, 0.0F, -1.0F);
		flower.addChild(petal1_2_r1);
		setRotationAngle(petal1_2_r1, 0.6109F, 0.0F, 0.7854F);
		petal1_2_r1.cubeList.add(new ModelBox(petal1_2_r1, 12, 12, -2.5F, -9.0F, 0.5F, 5, 6, 1, 0.0F, false));

		petal1_1_r1 = new ModelRenderer(this);
		petal1_1_r1.setRotationPoint(0.0F, 0.0F, -1.0F);
		flower.addChild(petal1_1_r1);
		setRotationAngle(petal1_1_r1, 0.2618F, 0.0F, 0.7854F);
		petal1_1_r1.cubeList.add(new ModelBox(petal1_1_r1, 0, 16, -2.0F, -3.0F, -0.5F, 4, 2, 1, 0.0F, false));

		stem = new ModelRenderer(this);
		stem.setRotationPoint(0.0F, 0.0F, 0.0F);
		stem.cubeList.add(new ModelBox(stem, 0, 0, -1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F, false));

		stem3_r1 = new ModelRenderer(this);
		stem3_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		stem.addChild(stem3_r1);
		setRotationAngle(stem3_r1, 0.3054F, 0.0F, 0.0F);
		stem3_r1.cubeList.add(new ModelBox(stem3_r1, 0, 0, -1.0F, -10.5F, 3.0F, 2, 4, 2, 0.0F, false));

		stem2_r1 = new ModelRenderer(this);
		stem2_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		stem.addChild(stem2_r1);
		setRotationAngle(stem2_r1, -0.3491F, 0.0F, 0.0F);
		stem2_r1.cubeList.add(new ModelBox(stem2_r1, 0, 0, -1.0F, -7.5F, -2.0F, 2, 4, 2, 0.0F, false));
	}

	@Override
	public void render(float rot) {
		render(null, rot, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		leaf1.render(scale);
		leaf2.render(scale);
		leaf3.render(scale);
		flower.render(scale);
		stem.render(scale);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scaleFactor, entity);

		float p = (float) Math.PI / 180F;
		float fh = headYaw;
		float fp = 180 - headPitch;
		stem.rotateAngleY = (180 + fh) * p;
		flower.rotateAngleY = fh * p;
		flower.rotateAngleX = fp * p;
	}

}
