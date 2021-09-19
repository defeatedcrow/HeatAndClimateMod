package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelNormalChamber_C extends DCTileModelBase {
	// fields
	private final ModelRenderer bone;
	private final ModelRenderer fuel;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;

	public ModelNormalChamber_C() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 8.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -8.0F, -2.0F, -8.0F, 16, 2, 16, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 19, -8.0F, -16.0F, 6.0F, 16, 14, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 36, 6.0F, -16.0F, -8.0F, 2, 14, 14, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 36, -8.0F, -16.0F, -8.0F, 2, 14, 14, 0.0F, false));

		fuel = new ModelRenderer(this);
		fuel.setRotationPoint(0.0F, 8.0F, 0.0F);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		fuel.addChild(cube_r3);
		setRotation(cube_r3, 0.6545F, -2.618F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 38, 30, -1.0F, -7.0F, -3.0F, 2, 2, 6, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		fuel.addChild(cube_r4);
		setRotation(cube_r4, 0.4363F, 1.2217F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 38, 20, -2.0F, -6.0F, -3.0F, 2, 2, 6, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		fuel.addChild(cube_r5);
		setRotation(cube_r5, 0.0873F, -0.2618F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 37, 40, -1.0F, -5.0F, -4.0F, 2, 2, 8, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
		fuel.addChild(cube_r6);
		setRotation(cube_r6, 0.0873F, -1.1345F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 38, 20, 0.0F, -5.5F, -5.0F, 2, 2, 6, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(0.0625F);
		bone.render(0.0625F);
		fuel.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {}

}
