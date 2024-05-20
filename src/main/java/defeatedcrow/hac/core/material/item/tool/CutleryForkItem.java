package defeatedcrow.hac.core.material.item.tool;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class CutleryForkItem extends CutleryItem {

	public CutleryForkItem(String n) {
		super(n);
	}

	@Override
	public EntityType<?> getType() {
		return CoreInit.CUTLERY_FORK.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		return FORK;
	}

	public static final EntityRenderData FORK = new EntityRenderData("cutlery_spoon", 0.5F, 0F);
}
