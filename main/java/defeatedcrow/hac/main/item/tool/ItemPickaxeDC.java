package defeatedcrow.hac.main.item.tool;

import net.minecraft.item.ItemPickaxe;
import defeatedcrow.hac.core.base.ITexturePath;

public class ItemPickaxeDC extends ItemPickaxe implements ITexturePath {

	private final String tex;

	public ItemPickaxeDC(ToolMaterial m, String t) {
		super(m);
		tex = t;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/pickaxe_" + tex;
	}

}
