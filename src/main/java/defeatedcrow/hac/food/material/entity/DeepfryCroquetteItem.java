package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class DeepfryCroquetteItem extends ItemEntityFood {

	public DeepfryCroquetteItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, true, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.FRY_CROQUETTE.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.DEEPFRY_CROQUETTE_POTATO.get())
			return POTATO;
		if (item == FoodInit.DEEPFRY_CROQUETTE_PUMPKIN.get())
			return PUMPKIN;
		if (item == FoodInit.DEEPFRY_CROQUETTE_CORN.get())
			return CORN;
		if (item == FoodInit.DEEPFRY_CROQUETTE_CRAB.get())
			return CRAB;
		if (item == FoodInit.DEEPFRY_CROQUETTE_FISH.get())
			return FISH;
		return POTATO;
	}

	public static final EntityRenderData POTATO = new EntityRenderData("food/deepfry_croquette_potato", 1F, 0F);
	public static final EntityRenderData PUMPKIN = new EntityRenderData("food/deepfry_croquette_pumpkin", 1F, 0F);
	public static final EntityRenderData CORN = new EntityRenderData("food/deepfry_croquette_corn", 1F, 0F);
	public static final EntityRenderData CRAB = new EntityRenderData("food/deepfry_croquette_crab", 1F, 0F);
	public static final EntityRenderData FISH = new EntityRenderData("food/deepfry_croquette_fish", 1F, 0F);

}
