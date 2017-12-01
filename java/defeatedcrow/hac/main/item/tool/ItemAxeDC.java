package defeatedcrow.hac.main.item.tool;

import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.item.ItemAxe;

public class ItemAxeDC extends ItemAxe implements ITexturePath {

	private final String tex;

	public ItemAxeDC(ToolMaterial m, String t) {
		super(m, m.getAttackDamage() + 2.0F, -3.0F);
		tex = t;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/axe_" + tex;
	}

}
