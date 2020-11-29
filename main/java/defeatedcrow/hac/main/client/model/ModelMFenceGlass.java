package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMFenceGlass extends DCTileModelBase {
	// fields
	private final ModelRenderer under;
	private final ModelRenderer middle;
	private final ModelRenderer upper;
	private final ModelRenderer normal;
	private final ModelRenderer bb_main;

	public ModelMFenceGlass() {
		textureWidth = 64;
		textureHeight = 32;

		upper = new ModelRenderer(this);
		upper.setRotationPoint(0.0F, -16.0F, 0.0F);
		upper.cubeList.add(new ModelBox(upper, 0, 18, -7.0F, 14.0F, -8.0F, 14, 1, 1, 0.0F, false));
		upper.cubeList.add(new ModelBox(upper, 32, 0, -7.0F, 0.0F, -7.5F, 14, 14, 0, 0.0F, false));

		middle = new ModelRenderer(this);
		middle.setRotationPoint(0.0F, -16.0F, 0.0F);
		middle.cubeList.add(new ModelBox(middle, 32, 0, -7.0F, 0.0F, -7.5F, 14, 16, 0, 0.0F, false));

		under = new ModelRenderer(this);
		under.setRotationPoint(0.0F, -16.0F, 0.0F);
		under.cubeList.add(new ModelBox(under, 0, 18, -7.0F, 0.0F, -8.0F, 14, 1, 1, 0.0F, false));
		under.cubeList.add(new ModelBox(under, 32, 1, -7.0F, 1.0F, -7.5F, 14, 15, 0, 0.0F, false));

		normal = new ModelRenderer(this);
		normal.setRotationPoint(0.0F, -16.0F, 0.0F);
		normal.cubeList.add(new ModelBox(normal, 0, 18, -7.0F, 0.0F, -8.0F, 14, 1, 1, 0.0F, false));
		normal.cubeList.add(new ModelBox(normal, 0, 18, -7.0F, 14.0F, -8.0F, 14, 1, 1, 0.0F, false));
		normal.cubeList.add(new ModelBox(normal, 32, 1, -7.0F, 1.0F, -7.5F, 14, 13, 0, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, -16.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 7.0F, 0.0F, -8.0F, 1, 16, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, 0.0F, -8.0F, 1, 16, 1, 0.0F, false));
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		bb_main.render(0.0625F);
	}

	public void renderUnder() {
		under.render(0.0625F);
	}

	public void renderMiddle() {
		middle.render(0.0625F);
	}

	public void renderUpper() {
		upper.render(0.0625F);
	}

	public void renderNormal() {
		normal.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
	}

}