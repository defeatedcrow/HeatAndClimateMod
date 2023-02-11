package defeatedcrow.hac.food.client;

import defeatedcrow.hac.food.client.entity.BreadRoundRenderer;
import defeatedcrow.hac.food.client.model.BreadRoundModel;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.entity.BreadRoundItem;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class FoodClientProxy {

	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(BreadRoundItem.BREAD_ROUND_RAW.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_ROUND_BAKED.getLayerLocation(), BreadRoundModel::createBodyLayer);
	}

	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(FoodInit.BREAD_ROUND.get(), BreadRoundRenderer::new);
	}

}
