package defeatedcrow.hac.food.material.item;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemCropDC extends ItemDC implements IFoodTaste {

	final String name;
	private String domain = "food";

	private final CropType type;
	private final CropTier tier;

	public ItemCropDC(CropTier rare, CropType t, String s, TagKey<Item> pair) {
		super(new Item.Properties().rarity(rare.getRarity()).tab(FoodInit.AGRI), pair);
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

	@Override
	public int getTaste(ItemStack item) {
		return tier.getTaste();
	}

	@Override
	public void setTaste(ItemStack item, int i) {}

	@Override
	public boolean isSeasoning() {
		return false;
	}

}
