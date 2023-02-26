package defeatedcrow.hac.core.material.item;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class MetalItemDC extends ItemDC {

	final String name;
	private String domain = "main";

	public MetalItemDC(Rarity rare, String s, TagKey<Item> pair) {
		super(new Item.Properties().rarity(rare).tab(CoreInit.CORE), pair);
		name = s;
	}

	public MetalItemDC setDomain(String s) {
		domain = s;
		return this;
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/metal/" + name));
	}

}
