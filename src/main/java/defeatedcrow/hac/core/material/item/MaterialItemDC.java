package defeatedcrow.hac.core.material.item;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class MaterialItemDC extends ItemDC {

	final String name;
	private String domain = "main";

	public MaterialItemDC(String s, TagKey<Item> pair) {
		super(new Item.Properties().tab(CoreInit.CORE), pair);
		name = s;
	}

	public MaterialItemDC(Rarity rare, String s, TagKey<Item> pair) {
		super(new Item.Properties().rarity(rare).tab(CoreInit.CORE), pair);
		name = s;
	}

	public MaterialItemDC setDomain(String s) {
		domain = s;
		return this;
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/material/" + name)));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.empty();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/material/" + name));
	}

}
