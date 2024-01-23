package defeatedcrow.hac.food.material.item;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class EdibleMaterialItem extends ItemFoodDC {

	protected final String name;

	public EdibleMaterialItem(String n, int nut, float sat, TagKey<Item> pair) {
		super(nut, sat, pair);
		name = n;
	}

	@Override
	public String getRegistryName() {
		return "food/" + name;
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/food/" + name));
	}

}
