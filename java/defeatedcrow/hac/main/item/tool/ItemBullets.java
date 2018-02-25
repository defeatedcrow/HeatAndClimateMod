package defeatedcrow.hac.main.item.tool;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import net.minecraft.util.math.MathHelper;

public class ItemBullets extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"bolt",
			"normal",
			"shot",
			"silver",
			"ghost",
			"light",
			"extinction",
			"crow"
	};

	public ItemBullets(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/misc/cartridge_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	public static Type getType(int meta) {
		meta = MathHelper.clamp_int(meta, 0, 7);
		return values[meta];
	}

	public static Type[] values = {
			Type.BOLT,
			Type.NORMAL,
			Type.SHOT,
			Type.SILVER,
			Type.GHOST,
			Type.LIGHT,
			Type.EXTINCTION,
			Type.CROW
	};

	public enum Type {
		BOLT,
		NORMAL,
		SILVER,
		SHOT,
		GHOST,
		LIGHT,
		EXTINCTION,
		CROW;
	}

}
