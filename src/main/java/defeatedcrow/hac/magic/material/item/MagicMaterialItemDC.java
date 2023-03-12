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

public class MagicMaterialItemDC extends MaterialItemDC implements IColorDC {

	private final MagicColor color;

	public MagicMaterialItemDC(MagicColor c, String s, TagKey<Item> pair) {
		super(MagicInit.MAGIC, s, pair);
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
