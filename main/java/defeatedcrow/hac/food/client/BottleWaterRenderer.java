package defeatedcrow.hac.food.client;

import defeatedcrow.hac.food.client.model.ModelBottleA;
import defeatedcrow.hac.food.item.brewing.ItemRoseWaterBottle;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BottleWaterRenderer extends BottleLiquorRenderer {

	public BottleWaterRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getTex(int meta, boolean isLayer) {
		String name = ItemRoseWaterBottle.getTypeName(meta);
		String s = "textures/entity/food/liquor/" + "" + name;
		if (isLayer) {
			s += "_layer.png";
		} else {
			s += "_glass.png";
		}
		return new ResourceLocation("dcs_climate", s);
	}

	@Override
	protected ModelBottleA getModel(int meta) {
		switch (meta) {
		case 3:
			return MODEL_J; // cola
		default:
			return MODEL_B;
		}
	}

	@Override
	protected boolean[] getBool(int meta) {
		switch (meta) {
		case 1:
			return new boolean[] { true, false, false };
		default:
			return new boolean[] { true, true, true };
		}
	}
}
