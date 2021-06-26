package defeatedcrow.hac.food.client;

import defeatedcrow.hac.food.client.model.ModelBottleA;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BottleDrinkRenderer extends BottleLiquorRenderer {

	protected BottleDrinkRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ModelBottleA getModel(int meta) {
		switch (meta) {
		case 1:
			return MODEL_B;
		default:
			return MODEL_A;
		}
	}

	@Override
	protected boolean[] getBool(int meta) {
		switch (meta) {
		case 1:
			return new boolean[] { true, true, true };
		default:
			return new boolean[] { true, false, false };
		}
	}
}
