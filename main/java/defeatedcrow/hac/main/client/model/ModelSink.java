package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSink extends DCTileModelBase {
	// fields
	private final ModelRenderer sink2_body;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer sink_stand;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;

	final boolean hasStand;

	public ModelSink(boolean s) {
		hasStand = s;

		textureWidth = 64;
		textureHeight = 64;

		sink2_body = new ModelRenderer(this);
		sink2_body.setRotationPoint(0.0F, 8.0F, 0.0F);
		sink2_body.cubeList.add(new ModelBox(sink2_body, 0, 16, -8.0F, -16.0F, -8.0F, 16, 4, 2, 0.0F, false));
		sink2_body.cubeList.add(new ModelBox(sink2_body, 0, 0, -6.0F, -13.0F, -6.0F, 12, 2, 12, 0.0F, false));
		sink2_body.cubeList.add(new ModelBox(sink2_body, 0, 40, -1.0F, -11.0F, -1.0F, 2, 4, 2, 0.0F, false));
		sink2_body.cubeList.add(new ModelBox(sink2_body, 10, 39, -1.0F, -9.0F, 1.0F, 2, 2, 7, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, 0.0F, 0.0F);
		sink2_body.addChild(cube_r9);
		setRotation(cube_r9, 0.0F, 1.5708F, 0.0F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 36, 16, -6.0F, -16.0F, -8.0F, 12, 4, 2, 0.0F, false));

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, 0.0F, 0.0F);
		sink2_body.addChild(cube_r10);
		setRotation(cube_r10, 0.0F, -1.5708F, 0.0F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 36, 16, -6.0F, -16.0F, -8.0F, 12, 4, 2, 0.0F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, 0.0F, 0.0F);
		sink2_body.addChild(cube_r11);
		setRotation(cube_r11, 0.0F, 3.1416F, 0.0F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 0, 16, -8.0F, -16.0F, -8.0F, 16, 4, 2, 0.0F, false));

		sink_stand = new ModelRenderer(this);
		sink_stand.setRotationPoint(0.0F, 8.0F, 0.0F);
		sink_stand.cubeList.add(new ModelBox(sink_stand, 0, 23, 7.0F, -12.0F, -8.0F, 1, 12, 1, 0.0F, false));
		sink_stand.cubeList.add(new ModelBox(sink_stand, 0, 23, -7.5F, -3.0F, -7.5F, 15, 0, 15, 0.0F, false));

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(0.0F, 0.0F, 0.0F);
		sink_stand.addChild(cube_r12);
		setRotation(cube_r12, 0.0F, 1.5708F, 0.0F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, 0, 23, 7.0F, -12.0F, -8.0F, 1, 12, 1, 0.0F, false));

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(0.0F, 0.0F, 0.0F);
		sink_stand.addChild(cube_r13);
		setRotation(cube_r13, 0.0F, 3.1416F, 0.0F);
		cube_r13.cubeList.add(new ModelBox(cube_r13, 0, 23, 7.0F, -12.0F, -8.0F, 1, 12, 1, 0.0F, false));

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(0.0F, 0.0F, 0.0F);
		sink_stand.addChild(cube_r14);
		setRotation(cube_r14, 0.0F, -1.5708F, 0.0F);
		cube_r14.cubeList.add(new ModelBox(cube_r14, 0, 23, 7.0F, -12.0F, -8.0F, 1, 12, 1, 0.0F, false));

	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		sink2_body.render(0.0625F);
		if (hasStand)
			sink_stand.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {}

}
