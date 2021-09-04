package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelButterflySpecimen extends DCTileModelBase {

	private final ModelRenderer main;
	private final ModelRenderer butterfly;
	private final ModelRenderer glass;

	public ModelButterflySpecimen() {

		textureWidth = 64;
		textureHeight = 32;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 8.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -7.0F, -15.0F, -8.0F, 14, 14, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 16, 6.0F, -14.0F, -7.0F, 1, 12, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 11, 16, -7.0F, -14.0F, -7.0F, 1, 12, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 22, 27, -7.0F, -2.0F, -7.0F, 14, 1, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 22, 21, -7.0F, -15.0F, -7.0F, 14, 1, 4, 0.0F, false));

		butterfly = new ModelRenderer(this);
		butterfly.setRotationPoint(0.0F, 8.0F, 0.0F);
		butterfly.cubeList.add(new ModelBox(butterfly, 31, 1, -6.0F, -14.0F, -6.0F, 12, 12, 0, 0.0F, false));

		glass = new ModelRenderer(this);
		glass.setRotationPoint(0.0F, 8.0F, 0.0F);
		glass.cubeList.add(new ModelBox(glass, 31, 1, -6.0F, -14.0F, -4.0F, 12, 12, 0, 0.0F, false));
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	public void renderButterfly(float scale) {
		butterfly.render(scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		main.render(scale);
		glass.render(scale);
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

}
