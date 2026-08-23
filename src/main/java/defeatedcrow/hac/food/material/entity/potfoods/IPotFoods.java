package defeatedcrow.hac.food.material.entity.potfoods;

import defeatedcrow.hac.api.material.EntityRenderData;
import net.minecraft.world.item.Item;

public interface IPotFoods {

	EntityRenderData getPotTexture(Item item);

	LayerType getPotLayerModel(Item item);

	public static enum LayerType {
		LAYER,
		FISH,
		POT;
	}

}
