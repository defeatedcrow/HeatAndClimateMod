package defeatedcrow.hac.main.item.tool;

import net.minecraft.item.ItemSword;
import defeatedcrow.hac.core.base.ITexturePath;

public class ItemSwordDC extends ItemSword implements ITexturePath {

	private final String tex;

	public ItemSwordDC(ToolMaterial m, String t) {
		super(m);
		tex = t;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/sword_" + tex;
	}

}
