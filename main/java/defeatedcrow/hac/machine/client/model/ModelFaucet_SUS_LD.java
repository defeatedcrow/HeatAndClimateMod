package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFaucet_SUS_LD extends DCTileModelBase {
	// fields
	private final ModelRenderer main;
	private final ModelRenderer head;

	public ModelFaucet_SUS_LD() {
		textureWidth = 32;
		textureHeight = 16;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 4.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -1.5F, -10.0F, 7.0F, 3, 3, 1, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 9, 0, -0.5F, -9.0F, 4.0F, 1, 1, 3, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -4.0F, 4.5F);
		setRotation(head, 0.9599F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 9, 0, -0.5F, -1.0F, -2.5F, 1, 1, 3, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 18, 0, -1.5F, -2.0F, -4.5F, 3, 3, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 18, 6, -2.5F, -3.0F, -5.5F, 5, 5, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 5, -3.5F, -4.0F, -6.5F, 7, 7, 1, 0.0F, false));
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		main.render(0.0625F);
		head.render(0.0625F);
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
