package defeatedcrow.hac.main.item.tool;

import net.minecraft.item.ItemAxe;
import defeatedcrow.hac.core.base.ITexturePath;

public class ItemAxeDC extends ItemAxe implements ITexturePath {

	private final String tex;

	public ItemAxeDC(ToolMaterial m, String t) {
		super(m, m.getDamageVsEntity() - 1.0F, -3.0F);
		tex = t;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/axe_" + tex;
	}

}
