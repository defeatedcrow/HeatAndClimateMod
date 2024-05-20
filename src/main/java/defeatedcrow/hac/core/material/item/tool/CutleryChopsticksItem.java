package defeatedcrow.hac.core.material.item.tool;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class CutleryChopsticksItem extends CutleryItem {

	public CutleryChopsticksItem(String n) {
		super(n);
	}

	@Override
	public EntityType<?> getType() {
		return CoreInit.CUTLERY_CHOPSTICKS.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		return CHOPSTICKS;
	}

	public static final EntityRenderData CHOPSTICKS = new EntityRenderData("cutlery_chopsticks", 0.5F, 0F);
}
