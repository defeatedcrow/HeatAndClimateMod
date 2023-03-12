package defeatedcrow.hac.core.material.item;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.core.json.JsonModelDC;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

// CreativeTabに表示しないアイテム
public class NoTabItemDC extends ItemDC {

	final String name;
	private String domain = "main";

	public NoTabItemDC(String s) {
		super(new Item.Properties(), null);
		name = s;
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/" + name));
	}

	@Override
	protected boolean allowedIn(CreativeModeTab tab) {
		return false;
	}

}
