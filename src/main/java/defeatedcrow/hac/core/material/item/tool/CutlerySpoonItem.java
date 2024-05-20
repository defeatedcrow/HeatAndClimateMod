package defeatedcrow.hac.core.material.item.tool;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class CutlerySpoonItem extends CutleryItem {

	public CutlerySpoonItem(String n) {
		super(n);
	}

	@Override
	public EntityType<?> getType() {
		return CoreInit.CUTLERY_SPOON.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		return SPOON;
	}

	public static final EntityRenderData SPOON = new EntityRenderData("cutlery_spoon", 0.5F, 0F);
}
