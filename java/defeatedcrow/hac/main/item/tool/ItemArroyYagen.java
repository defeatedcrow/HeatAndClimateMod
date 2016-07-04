package defeatedcrow.hac.main.item.tool;

import defeatedcrow.hac.core.ClimateCore;

public class ItemArroyYagen extends ItemStoneYagen {

	public ItemArroyYagen() {
		super();
		this.setMaxDamage(640);
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/tool/yagen_brass";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
