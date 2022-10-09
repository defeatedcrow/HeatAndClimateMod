package defeatedcrow.hac.core.material.item;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ItemGemDC extends ItemDC {

	final String name;
	private String domain = "main";

	public ItemGemDC(Rarity rare, String s) {
		super(new Item.Properties().rarity(rare).tab(CoreInit.CORE));
		name = s;
	}

	public ItemGemDC setDomain(String s) {
		domain = s;
		return this;
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/gem/" + name)));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.empty();
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/gem/" + name));
	}

}
