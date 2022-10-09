package defeatedcrow.hac.food.material.item;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;

public class ItemCropDC extends ItemDC {

	final String name;
	private String domain = "food";

	private final CropType type;
	private final CropTier tier;

	public ItemCropDC(CropTier rare, CropType t, String s) {
		super(new Item.Properties().rarity(rare.getRarity()).tab(FoodInit.FOOD));
		name = s;
		type = t;
		tier = rare;
	}

	public ItemCropDC setDomain(String s) {
		domain = s;
		return this;
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/" + name)));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.empty();
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/" + name));
	}

	public CropType getType() {
		return type;
	}

	public CropTier getTier() {
		return tier;
	}

}
