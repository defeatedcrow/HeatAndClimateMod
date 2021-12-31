package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDesktopAccessories_A extends DCTileModelBase {
	// fields
	private final ModelRenderer stand;
	private final ModelRenderer books;
	private final ModelRenderer book2;
	private final ModelRenderer cube_r1;

	public ModelDesktopAccessories_A() {
		textureWidth = 64;
		textureHeight = 64;

		stand = new ModelRenderer(this);
		stand.setRotationPoint(0.0F, 8.0F, 0.0F);
		stand.cubeList.add(new ModelBox(stand, 0, 0, -8.0F, -1.0F, 0.0F, 16, 1, 8, 0.0F, false));
		stand.cubeList.add(new ModelBox(stand, 46, 1, -8.0F, -6.0F, 0.0F, 1, 5, 8, 0.0F, false));
		stand.cubeList.add(new ModelBox(stand, 46, 1, -0.5F, -6.0F, 0.0F, 1, 5, 8, 0.0F, false));
		stand.cubeList.add(new ModelBox(stand, 46, 1, 7.0F, -6.0F, 0.0F, 1, 5, 8, 0.0F, false));

		books = new ModelRenderer(this);
		books.setRotationPoint(0.0F, 8.0F, 0.0F);
		books.cubeList.add(new ModelBox(books, 0, 13, -7.0F, -11.0F, 0.0F, 2, 10, 7, 0.0F, false));
		books.cubeList.add(new ModelBox(books, 0, 30, -4.8F, -11.0F, 0.0F, 2, 10, 7, 0.0F, false));
		books.cubeList.add(new ModelBox(books, 0, 47, -2.6F, -11.0F, 0.0F, 2, 10, 7, 0.0F, false));
		books.cubeList.add(new ModelBox(books, 18, 16, 0.5F, -9.0F, 0.0F, 1, 8, 6, 0.0F, false));
		books.cubeList.add(new ModelBox(books, 32, 16, 1.5F, -9.0F, 0.5F, 1, 8, 6, 0.0F, false));
		books.cubeList.add(new ModelBox(books, 19, 47, 2.5F, -11.0F, 0.0F, 1, 10, 7, 0.0F, false));
		books.cubeList.add(new ModelBox(books, 19, 30, 6.0F, -11.0F, 0.0F, 1, 10, 7, 0.0F, false));

		book2 = new ModelRenderer(this);
		book2.setRotationPoint(0.0F, 8.0F, 0.0F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		book2.addChild(cube_r1);
		setRotation(cube_r1, 0.0F, 0.0F, -0.1396F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 36, 47, 5.0F, -10.3F, 0.0F, 1, 10, 7, 0.0F, false));
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		stand.render(0.0625F);
		books.render(0.0625F);
		book2.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
