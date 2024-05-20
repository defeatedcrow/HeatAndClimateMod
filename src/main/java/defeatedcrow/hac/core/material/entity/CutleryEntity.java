package defeatedcrow.hac.core.material.entity;

import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class CutleryEntity extends ObjectEntityBaseDC {

	public CutleryEntity(EntityType<? extends FoodEntityBase> type, Level level) {
		super(type, level);
	}

}
