package defeatedcrow.hac.main.item.tool;

import net.minecraft.item.ItemSpade;
import defeatedcrow.hac.core.base.ITexturePath;

public class ItemSpadeDC extends ItemSpade implements ITexturePath {

	private final String tex;

	public ItemSpadeDC(ToolMaterial m, String t) {
		super(m);
		tex = t;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/spade_" + tex;
	}

}
