package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelKitchenBlock extends DCTileModelBase {
	// fields
	private final ModelRenderer base;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer panel1;
	private final ModelRenderer panel2;
	private final ModelRenderer stove;
	private final ModelRenderer stove2;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer desk;
	private final ModelRenderer desk2;
	private final ModelRenderer sink;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;

	Type type;
	Shape shape;

	public ModelKitchenBlock(Type t, Shape s) {
		type = t;
		shape = s;
		textureWidth = 64;
		textureHeight = 64;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 8.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -8.0F, -12.0F, -8.0F, 16, 12, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 14, -7.0F, -2.0F, -7.0F, 14, 1, 14, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 14, -7.0F, -12.0F, -7.0F, 14, 1, 14, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		base.addChild(cube_r1);
		setRotation(cube_r1, 0.0F, 1.5708F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 34, 0, -7.0F, -12.0F, -8.0F, 14, 12, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		base.addChild(cube_r2);
		setRotation(cube_r2, 0.0F, -1.5708F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 34, 0, -7.0F, -12.0F, -8.0F, 14, 12, 1, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		base.addChild(cube_r3);
		setRotation(cube_r3, 0.0F, 3.1416F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 0, -8.0F, -12.0F, -8.0F, 16, 12, 1, 0.0F, false));

		panel1 = new ModelRenderer(this);
		panel1.setRotationPoint(0.0F, 8.0F, 0.0F);
		panel1.cubeList.add(new ModelBox(panel1, 0, 30, -8.01F, -11.8F, -8.5F, 8, 11, 1, 0.0F, false));
		panel1.cubeList.add(new ModelBox(panel1, 0, 30, 0.01F, -11.8F, -8.5F, 8, 11, 1, 0.0F, false));

		panel2 = new ModelRenderer(this);
		panel2.setRotationPoint(0.0F, 8.0F, 0.0F);
		panel2.cubeList.add(new ModelBox(panel2, 19, 30, -8.0F, -11.0F, -9.0F, 16, 1, 1, 0.0F, false));
		panel2.cubeList.add(new ModelBox(panel2, 19, 30, -8.0F, -16.0F, -9.0F, 16, 1, 1, 0.0F, false));

		stove = new ModelRenderer(this);
		stove.setRotationPoint(0.0F, 8.0F, 0.0F);
		stove.cubeList.add(new ModelBox(stove, 0, 47, -8.0F, -15.0F, -7.0F, 16, 3, 14, 0.0F, false));
		stove.cubeList.add(new ModelBox(stove, 0, 43, -4.0F, -15.5F, -4.0F, 8, 1, 1, 0.0F, false));
		stove.cubeList.add(new ModelBox(stove, 0, 43, -4.0F, -15.5F, 3.0F, 8, 1, 1, 0.0F, false));
		stove.cubeList.add(new ModelBox(stove, 0, 46, 3.0F, -15.5F, -3.0F, 1, 1, 6, 0.0F, false));
		stove.cubeList.add(new ModelBox(stove, 0, 46, -4.0F, -15.5F, -3.0F, 1, 1, 6, 0.0F, false));
		stove.cubeList.add(new ModelBox(stove, 0, 43, 2.0F, -16.0F, -0.5F, 3, 1, 1, 0.0F, false));
		stove.cubeList.add(new ModelBox(stove, 0, 43, -5.0F, -16.0F, -0.5F, 3, 1, 1, 0.0F, false));
		stove.cubeList.add(new ModelBox(stove, 3, 49, -0.5F, -16.0F, 2.0F, 1, 1, 3, 0.0F, false));
		stove.cubeList.add(new ModelBox(stove, 3, 49, -0.5F, -16.0F, -5.0F, 1, 1, 3, 0.0F, false));
		stove.cubeList.add(new ModelBox(stove, 49, 47, -6.0F, -14.5F, -7.5F, 2, 2, 1, 0.0F, false));
		stove.cubeList.add(new ModelBox(stove, 49, 51, 1.0F, -14.5F, -7.5F, 6, 2, 1, 0.0F, false));

		stove2 = new ModelRenderer(this);
		stove2.setRotationPoint(0.0F, 8.0F, 0.0F);
		stove2.cubeList.add(new ModelBox(stove2, 19, 33, -8.0F, -16.0F, -8.0F, 16, 1, 2, 0.0F, false));
		stove2.cubeList.add(new ModelBox(stove2, 19, 30, -8.0F, -16.0F, 7.0F, 16, 1, 1, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		stove2.addChild(cube_r4);
		setRotation(cube_r4, 0.0F, 1.5708F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 19, 38, -7.0F, -16.0F, 7.0F, 13, 1, 1, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		stove2.addChild(cube_r5);
		setRotation(cube_r5, 0.0F, -1.5708F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 19, 38, -6.0F, -16.0F, 7.0F, 13, 1, 1, 0.0F, false));

		desk = new ModelRenderer(this);
		desk.setRotationPoint(0.0F, 8.0F, 0.0F);
		desk.cubeList.add(new ModelBox(desk, 0, 44, -8.0F, -16.0F, -8.0F, 16, 4, 16, 0.0F, false));

		desk2 = new ModelRenderer(this);
		desk2.setRotationPoint(0.0F, 8.0F, 0.0F);
		desk2.cubeList.add(new ModelBox(desk2, 19, 30, -7.5F, -15.0F, -8.5F, 15, 3, 1, 0.0F, false));

		sink = new ModelRenderer(this);
		sink.setRotationPoint(0.0F, 8.0F, 0.0F);
		sink.cubeList.add(new ModelBox(sink, 0, 43, -8.0F, -16.0F, -8.0F, 16, 4, 2, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
		sink.addChild(cube_r6);
		setRotation(cube_r6, 0.0F, 1.5708F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 50, -6.0F, -16.0F, -8.0F, 12, 4, 2, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 0.0F, 0.0F);
		sink.addChild(cube_r7);
		setRotation(cube_r7, 0.0F, -1.5708F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 0, 50, -6.0F, -16.0F, -8.0F, 12, 4, 2, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, 0.0F, 0.0F);
		sink.addChild(cube_r8);
		setRotation(cube_r8, 0.0F, 3.1416F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 0, 43, -8.0F, -16.0F, -8.0F, 16, 4, 2, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		base.render(0.0625F);
		if (shape == Shape.ISLAND) {
			panel2.render(0.0625F);
		} else {
			panel1.render(0.0625F);
		}
		if (type == Type.STOVE) {
			stove.render(0.0625F);
			if (shape == Shape.ISLAND) {
				stove2.render(0.0625F);
			}
		}
		if (type == Type.DESK) {
			desk.render(0.0625F);
			if (shape == Shape.NORMAL) {
				desk2.render(0.0625F);
			}
		}
		if (type == Type.SINK) {
			sink.render(0.0625F);
		}

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {}

	public static enum Type {
		STOVE,
		DESK,
		SINK;
	}

	public static enum Shape {
		NORMAL,
		ISLAND;
	}

}
