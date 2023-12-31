package defeatedcrow.hac.magic.material.item;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.magic.IColorDC;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class MagicMaterialItemDC extends MaterialItemDC implements IColorDC {

	private final MagicColor color;

	public MagicMaterialItemDC(MagicColor c, String s, Rarity rare, TagKey<Item> pair) {
		super(new Item.Properties().tab(MagicInit.MAGIC).rarity(rare), s, pair);
		color = c;
	}

	public MagicMaterialItemDC(Properties prop, MagicColor c, String s, TagKey<Item> pair) {
		super(prop, s, pair);
		color = c;
	}

	@Override
	public MagicColor getColor() {
		return color;
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/magic/" + name));
	}
}
