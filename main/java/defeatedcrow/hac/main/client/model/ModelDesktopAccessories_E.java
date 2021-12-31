package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDesktopAccessories_E extends DCTileModelBase {
	// fields
	private final ModelRenderer body;

	public ModelDesktopAccessories_E() {
		textureWidth = 64;
		textureHeight = 16;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 8.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -8.0F, -1.0F, -3.0F, 16, 1, 7, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -7.65F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -7.05F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -7.65F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -7.65F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -7.65F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -7.05F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -7.65F, -1.3F, -2.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -6.55F, -1.3F, -2.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 46, 0, -7.55F, -1.3F, -1.6F, 2, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -5.95F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -6.55F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -5.95F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -6.55F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -5.45F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -4.85F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -5.45F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -4.85F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -5.45F, -1.3F, -1.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -5.45F, -1.3F, -2.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -4.35F, -1.3F, -2.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -4.35F, -1.3F, -1.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -3.75F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -4.35F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -3.75F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -4.35F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -3.25F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -2.65F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -3.25F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -2.65F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -3.25F, -1.3F, -1.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 40, 3, -3.25F, -1.3F, -2.7F, 5, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -2.15F, -1.3F, -1.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -1.55F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -2.15F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -1.55F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -2.15F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -1.05F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -0.45F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -1.05F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -0.45F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, -1.05F, -1.3F, -1.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 0.05F, -1.3F, -1.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 0.65F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 0.05F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 0.65F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 0.05F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 1.15F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 1.75F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 1.15F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 1.75F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 1.15F, -1.3F, -1.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 1.85F, -1.3F, -2.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 2.25F, -1.3F, -2.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 2.25F, -1.3F, -1.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 2.85F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 2.25F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 2.85F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 2.25F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 3.35F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 3.95F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 3.35F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 3.95F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 3.35F, -1.3F, -1.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 3.35F, -1.3F, -2.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 4.45F, -1.3F, -2.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 4.45F, -1.3F, -1.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 5.05F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 4.45F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 5.05F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 4.45F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 5.55F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 6.15F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 6.65F, -1.3F, 1.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 5.55F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 6.15F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 46, 0, 5.55F, -1.3F, -1.6F, 2, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 5.55F, -1.3F, -2.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 6.65F, -1.3F, -2.7F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 6.65F, -1.3F, -0.5F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 6.65F, -1.3F, 0.6F, 1, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 41, 0, 6.65F, -1.3F, 2.8F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		body.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
