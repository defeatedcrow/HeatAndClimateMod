package defeatedcrow.hac.core.material.item.tool;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.item.ItemDC;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public abstract class CraftingItemDC extends ItemDC {

	final String name;
	private String domain = "main";

	public CraftingItemDC(String n, Item.Properties prop, TagKey<Item> pair) {
		super(prop, pair);
		name = n;
	}

	public CraftingItemDC setDomain(String s) {
		domain = s;
		return this;
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("minecraft:item/handheld", ImmutableMap.of("layer0", "dcs_climate:item/tool/" + name)));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.empty();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/handheld", ImmutableMap.of("layer0", "dcs_climate:item/tool/" + name));
	}

}
