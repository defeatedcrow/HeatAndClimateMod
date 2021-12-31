package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDesktopAccessories_D extends DCTileModelBase {
	// fields
	private final ModelRenderer box;
	private final ModelRenderer part1;
	private final ModelRenderer part2;
	private final ModelRenderer part3;

	public ModelDesktopAccessories_D() {
		textureWidth = 64;
		textureHeight = 32;

		box = new ModelRenderer(this);
		box.setRotationPoint(0.0F, 8.0F, 0.0F);
		box.cubeList.add(new ModelBox(box, 0, 0, -6.0F, -1.0F, -4.0F, 12, 1, 8, 0.0F, false));
		box.cubeList.add(new ModelBox(box, 33, 0, -6.0F, -7.0F, 3.0F, 12, 6, 1, 0.0F, false));
		box.cubeList.add(new ModelBox(box, 0, 10, -1.0F, -6.0F, 0.0F, 1, 5, 3, 0.0F, false));
		box.cubeList.add(new ModelBox(box, 0, 10, 5.0F, -6.0F, 0.0F, 1, 5, 3, 0.0F, false));
		box.cubeList.add(new ModelBox(box, 0, 10, -6.0F, -6.0F, 0.0F, 1, 5, 3, 0.0F, false));
		box.cubeList.add(new ModelBox(box, 33, 0, -6.0F, -4.0F, -1.0F, 12, 3, 1, 0.0F, false));
		box.cubeList.add(new ModelBox(box, 9, 11, -6.0F, -3.0F, -3.0F, 1, 2, 2, 0.0F, false));
		box.cubeList.add(new ModelBox(box, 9, 11, 5.0F, -3.0F, -3.0F, 1, 2, 2, 0.0F, false));
		box.cubeList.add(new ModelBox(box, 33, 0, -6.0F, -2.0F, -4.0F, 12, 1, 1, 0.0F, false));

		part1 = new ModelRenderer(this);
		part1.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(part1, -0.2618F, 0.0F, 0.0F);
		part1.cubeList.add(new ModelBox(part1, 17, 10, 0.5F, -9.0F, 0.0F, 4, 8, 1, 0.0F, false));
		part1.cubeList.add(new ModelBox(part1, 15, 22, -4.0F, -3.0F, -3.0F, 4, 3, 1, 0.0F, false));

		part2 = new ModelRenderer(this);
		part2.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(part2, -0.2618F, 0.0F, 0.1309F);
		part2.cubeList.add(new ModelBox(part2, 29, 10, -6.0F, -11.0F, 1.0F, 5, 10, 0, 0.0F, false));
		part2.cubeList.add(new ModelBox(part2, 0, 21, 3.5F, -4.0F, -3.0F, 1, 4, 1, 0.0F, false));
		part2.cubeList.add(new ModelBox(part2, 5, 22, 2.0F, -3.0F, -3.0F, 1, 3, 1, 0.0F, false));
		part2.cubeList.add(new ModelBox(part2, 10, 22, 0.8F, -2.9F, -3.0F, 1, 3, 1, 0.0F, false));

		part3 = new ModelRenderer(this);
		part3.setRotationPoint(0.0F, 8.0F, 0.0F);
		setRotation(part3, -0.2618F, 0.0F, -0.0873F);
		part3.cubeList.add(new ModelBox(part3, 40, 10, -5.0F, -11.0F, 0.0F, 5, 10, 0, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		box.render(0.0625F);
		part1.render(0.0625F);
		part2.render(0.0625F);
		part3.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
