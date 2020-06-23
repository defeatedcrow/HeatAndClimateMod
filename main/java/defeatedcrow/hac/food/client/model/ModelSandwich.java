package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSandwich extends DCFoodModelBase {

	ModelRenderer basketU;
	ModelRenderer basketF;
	ModelRenderer basketB;
	ModelRenderer basketL;
	ModelRenderer basketR;

	ModelRenderer bread11;
	ModelRenderer bread12;
	ModelRenderer bread21;
	ModelRenderer bread22;
	ModelRenderer bread31;
	ModelRenderer bread32;

	ModelRenderer apple1;
	ModelRenderer apple2;
	ModelRenderer apple3;
	ModelRenderer egg1;
	ModelRenderer egg2;
	ModelRenderer egg3;
	ModelRenderer cassis1;
	ModelRenderer cassis2;
	ModelRenderer cassis3;
	ModelRenderer liver1;
	ModelRenderer liver2;
	ModelRenderer liver3;
	ModelRenderer salmon1;
	ModelRenderer salmon2;
	ModelRenderer salmon3;

	public ModelSandwich(boolean baked) {
		super(baked);
		textureWidth = 64;
		textureHeight = 32;

		basketU = new ModelRenderer(this, 0, 0);
		basketU.addBox(-6F, 7F, -4F, 12, 1, 8);
		basketU.setRotationPoint(0F, -8F, 0F);
		basketU.setTextureSize(64, 32);
		basketU.mirror = true;
		setRotation(basketU, 0F, 0F, 0F);
		basketF = new ModelRenderer(this, 0, 10);
		basketF.addBox(-6F, 3F, -4F, 12, 4, 1);
		basketF.setRotationPoint(0F, -8F, 0F);
		basketF.setTextureSize(64, 32);
		basketF.mirror = true;
		setRotation(basketF, 0F, 0F, 0F);
		basketB = new ModelRenderer(this, 0, 10);
		basketB.addBox(-6F, 3F, -4F, 12, 4, 1);
		basketB.setRotationPoint(0F, -8F, 0F);
		basketB.setTextureSize(64, 32);
		basketB.mirror = true;
		setRotation(basketB, 0F, 3.141593F, 0F);
		basketL = new ModelRenderer(this, 40, 0);
		basketL.addBox(-6F, 3F, -3F, 1, 4, 6);
		basketL.setRotationPoint(0F, -8F, 0F);
		basketL.setTextureSize(64, 32);
		basketL.mirror = true;
		setRotation(basketL, 0F, 0F, 0F);
		basketR = new ModelRenderer(this, 40, 0);
		basketR.addBox(-6F, 3F, -3F, 1, 4, 6);
		basketR.setRotationPoint(0F, -8F, 0F);
		basketR.setTextureSize(64, 32);
		basketR.mirror = true;
		setRotation(basketR, 0F, 3.141593F, 0F);
		bread11 = new ModelRenderer(this, 0, 16);
		bread11.addBox(-4.5F, 2.5F, -2.5F, 1, 3, 5);
		bread11.setRotationPoint(0F, -8F, 0F);
		bread11.setTextureSize(64, 32);
		bread11.mirror = true;
		setRotation(bread11, 0F, -0.0698132F, -0.0872665F);
		bread12 = new ModelRenderer(this, 0, 16);
		bread12.addBox(-3F, 2.5F, -2.5F, 1, 3, 5);
		bread12.setRotationPoint(0F, -8F, 0F);
		bread12.setTextureSize(64, 32);
		bread12.mirror = true;
		setRotation(bread12, 0F, -0.0698132F, -0.122173F);
		bread21 = new ModelRenderer(this, 0, 16);
		bread21.addBox(-1.5F, 3F, -2.5F, 1, 3, 5);
		bread21.setRotationPoint(0F, -8F, 0F);
		bread21.setTextureSize(64, 32);
		bread21.mirror = true;
		setRotation(bread21, 0F, 0F, -0.122173F);
		bread22 = new ModelRenderer(this, 0, 16);
		bread22.addBox(0F, 3F, -2.5F, 1, 3, 5);
		bread22.setRotationPoint(0F, -8F, 0F);
		bread22.setTextureSize(64, 32);
		bread22.mirror = true;
		setRotation(bread22, 0F, 0F, -0.122173F);
		bread31 = new ModelRenderer(this, 0, 16);
		bread31.addBox(2F, 3F, -2.5F, 1, 3, 5);
		bread31.setRotationPoint(0F, -8F, 0F);
		bread31.setTextureSize(64, 32);
		bread31.mirror = true;
		setRotation(bread31, 0F, 0F, 0.0349066F);
		bread32 = new ModelRenderer(this, 0, 16);
		bread32.addBox(3.5F, 3F, -2.5F, 1, 3, 5);
		bread32.setRotationPoint(0F, -8F, 0F);
		bread32.setTextureSize(64, 32);
		bread32.mirror = true;
		setRotation(bread32, 0F, 0F, 0F);

		apple1 = new ModelRenderer(this, 0, 24);
		apple1.addBox(-3.5F, 3F, -2F, 1, 3, 4);
		apple1.setRotationPoint(0F, -8F, 0F);
		apple1.setTextureSize(64, 32);
		apple1.mirror = true;
		setRotation(apple1, 0F, -0.0698132F, 0F);
		apple2 = new ModelRenderer(this, 0, 24);
		apple2.addBox(-0.5F, 3.5F, -2F, 1, 3, 4);
		apple2.setRotationPoint(0F, -8F, 0F);
		apple2.setTextureSize(64, 32);
		apple2.mirror = true;
		setRotation(apple2, 0F, 0F, 0F);
		apple3 = new ModelRenderer(this, 0, 24);
		apple3.addBox(3F, 3.5F, -2F, 1, 3, 4);
		apple3.setRotationPoint(0F, -8F, 0F);
		apple3.setTextureSize(64, 32);
		apple3.mirror = true;
		setRotation(apple3, 0F, 0F, 0F);

		egg1 = new ModelRenderer(this, 10, 24);
		egg1.addBox(-3.5F, 3F, -2F, 1, 3, 4);
		egg1.setRotationPoint(0F, -8F, 0F);
		egg1.setTextureSize(64, 32);
		egg1.mirror = true;
		setRotation(egg1, 0F, -0.0698132F, 0F);
		egg2 = new ModelRenderer(this, 10, 24);
		egg2.addBox(-0.5F, 3.5F, -2F, 1, 3, 4);
		egg2.setRotationPoint(0F, -8F, 0F);
		egg2.setTextureSize(64, 32);
		egg2.mirror = true;
		setRotation(egg2, 0F, 0F, 0F);
		egg3 = new ModelRenderer(this, 10, 24);
		egg3.addBox(3F, 3.5F, -2F, 1, 3, 4);
		egg3.setRotationPoint(0F, -8F, 0F);
		egg3.setTextureSize(64, 32);
		egg3.mirror = true;
		setRotation(egg3, 0F, 0F, 0F);

		cassis1 = new ModelRenderer(this, 20, 24);
		cassis1.addBox(-3.5F, 3F, -2F, 1, 3, 4);
		cassis1.setRotationPoint(0F, -8F, 0F);
		cassis1.setTextureSize(64, 32);
		cassis1.mirror = true;
		setRotation(cassis1, 0F, -0.0698132F, 0F);
		cassis2 = new ModelRenderer(this, 20, 24);
		cassis2.addBox(-0.5F, 3.5F, -2F, 1, 3, 4);
		cassis2.setRotationPoint(0F, -8F, 0F);
		cassis2.setTextureSize(64, 32);
		cassis2.mirror = true;
		setRotation(cassis2, 0F, 0F, 0F);
		cassis3 = new ModelRenderer(this, 20, 24);
		cassis3.addBox(3F, 3.5F, -2F, 1, 3, 4);
		cassis3.setRotationPoint(0F, -8F, 0F);
		cassis3.setTextureSize(64, 32);
		cassis3.mirror = true;
		setRotation(cassis3, 0F, 0F, 0F);

		liver1 = new ModelRenderer(this, 30, 24);
		liver1.addBox(-3.5F, 3F, -2F, 1, 3, 4);
		liver1.setRotationPoint(0F, -8F, 0F);
		liver1.setTextureSize(64, 32);
		liver1.mirror = true;
		setRotation(liver1, 0F, -0.0698132F, 0F);
		liver2 = new ModelRenderer(this, 30, 24);
		liver2.addBox(-0.5F, 3.5F, -2F, 1, 3, 4);
		liver2.setRotationPoint(0F, -8F, 0F);
		liver2.setTextureSize(64, 32);
		liver2.mirror = true;
		setRotation(liver2, 0F, 0F, 0F);
		liver3 = new ModelRenderer(this, 30, 24);
		liver3.addBox(3F, 3.5F, -2F, 1, 3, 4);
		liver3.setRotationPoint(0F, -8F, 0F);
		liver3.setTextureSize(64, 32);
		liver3.mirror = true;
		setRotation(liver3, 0F, 0F, 0F);

		salmon1 = new ModelRenderer(this, 40, 24);
		salmon1.addBox(-3.5F, 3F, -2F, 1, 3, 4);
		salmon1.setRotationPoint(0F, -8F, 0F);
		salmon1.setTextureSize(64, 32);
		salmon1.mirror = true;
		setRotation(salmon1, 0F, -0.0698132F, 0F);
		salmon2 = new ModelRenderer(this, 40, 24);
		salmon2.addBox(-0.5F, 3.5F, -2F, 1, 3, 4);
		salmon2.setRotationPoint(0F, -8F, 0F);
		salmon2.setTextureSize(64, 32);
		salmon2.mirror = true;
		setRotation(salmon2, 0F, 0F, 0F);
		salmon3 = new ModelRenderer(this, 40, 24);
		salmon3.addBox(3F, 3.5F, -2F, 1, 3, 4);
		salmon3.setRotationPoint(0F, -8F, 0F);
		salmon3.setTextureSize(64, 32);
		salmon3.mirror = true;
		setRotation(salmon3, 0F, 0F, 0F);
	}

	public void render(float scale, int meta) {
		setIndividualRotation(null);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale, meta);
	}

	private void setIndividualRotation(FoodEntityBase entity) {}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale, int meta) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		basketU.render(0.0625F);
		basketF.render(0.0625F);
		basketB.render(0.0625F);
		basketL.render(0.0625F);
		basketR.render(0.0625F);
		bread11.render(0.0625F);
		bread12.render(0.0625F);
		bread21.render(0.0625F);
		bread22.render(0.0625F);
		bread31.render(0.0625F);
		bread32.render(0.0625F);

		if (meta == 0) {
			apple1.render(0.0625F);
			apple2.render(0.0625F);
			apple3.render(0.0625F);
		}
		if (meta == 1) {
			egg1.render(0.0625F);
			egg2.render(0.0625F);
			egg3.render(0.0625F);
		}
		if (meta == 2) {
			cassis1.render(0.0625F);
			cassis2.render(0.0625F);
			cassis3.render(0.0625F);
		}
		if (meta == 3) {
			liver1.render(0.0625F);
			liver2.render(0.0625F);
			liver3.render(0.0625F);
		}
		if (meta == 4) {
			salmon1.render(0.0625F);
			salmon2.render(0.0625F);
			salmon3.render(0.0625F);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {

	}

}
