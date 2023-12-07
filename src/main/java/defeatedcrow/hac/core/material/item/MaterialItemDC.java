package defeatedcrow.hac.core.material.item;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class MaterialItemDC extends ItemDC {

	protected final String name;
	protected String domain = "main";

	public MaterialItemDC(String s, TagKey<Item> pair) {
		super(new Item.Properties().tab(CoreInit.CORE), pair);
		name = s;
	}

	public MaterialItemDC(CreativeModeTab tab, String s, TagKey<Item> pair) {
		super(new Item.Properties().tab(tab), pair);
		name = s;
	}

	public MaterialItemDC(Properties prop, String s, TagKey<Item> pair) {
		super(prop, pair);
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
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/" + domain + "/" + name));
	}

}
