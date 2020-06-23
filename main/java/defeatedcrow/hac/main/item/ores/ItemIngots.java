package defeatedcrow.hac.main.item.ores;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIngots extends DCItem {

	private final int maxMeta;

	private static String[] names = {
		"copper",
		"zinc",
		"nickel",
		"silver",
		"brass",
		"steel",
		"nickelsilver",
		"magnet",
		"tin",
		"bronze",
		"sus",
		"titanium",
		"aluminium",
		"bismuth",
		"bscco",
		"lead",
		"manganese",
		"toolsteel",
		"mangalloy" };

	public ItemIngots(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	/*
	 * 0: 銅
	 * 1: 亜鉛
	 * 2: ニッケル
	 * 3: 銀
	 * 4: 真鍮
	 * 5: 鋼
	 * 6: 洋白
	 * 7: 磁石
	 * 8: 錫
	 * 9: 青銅
	 * 10: SUS
	 * 11: チタン
	 * 12: アルミニウム
	 * 13: ビスマス
	 * 14: BSCCO
	 * 15: 鉛
	 * 16: マンガン
	 * 17: 工具鋼
	 * 18: 高マンガン鋼
	 */
	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		MathHelper.clamp(meta, 0, maxMeta);
		String s = "items/ores/ingot_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, World world, List<String> tooltip) {
		int i = stack.getItemDamage();
		if (i >= 0 && i <= maxMeta) {
			int tier = Metal.VALUES[i].tier;
			TextFormatting color = TextFormatting.YELLOW;
			if (tier > 2) {
				color = TextFormatting.GOLD;
			}
			if (tier > 1) {
				tooltip.add(color.toString() + "Tier " + Metal.VALUES[i].tier);
			} else {
				tooltip.add("Tier " + Metal.VALUES[i].tier);
			}
		}
	}

	public enum Metal {
		COPPER("copper", 1),
		ZINC("zinc", 1),
		NICKEL("nickel", 2),
		SILVER("silver", 2),
		BRASS("brass", 1),
		STEEL("steel", 2),
		NICKELSILVER("nickelsilver", 2),
		MAGNET("magnet", 3),
		TIN("tin", 1),
		BRONZE("bronze", 1),
		SUS("sus", 3),
		TITANIUM("titanium", 3),
		ALUMINIUM("aluminium", 2),
		BISMUTH("bismuth", 1),
		BSCCO("bscco", 3),
		LEAD("lead", 1),
		MANGANESE("manganese", 3),
		TOOLSTEEL("toolsteel", 3),
		MANGALLOY("mangalloy", 3);

		public String name;
		public int tier;

		private Metal(String s, int i) {
			name = s;
			tier = i;
		}

		public static final Metal[] VALUES = {
			COPPER,
			ZINC,
			NICKEL,
			SILVER,
			BRASS,
			STEEL,
			NICKELSILVER,
			MAGNET,
			TIN,
			BRONZE,
			SUS,
			TITANIUM,
			ALUMINIUM,
			BISMUTH,
			BSCCO,
			LEAD,
			MANGANESE,
			TOOLSTEEL,
			MANGALLOY };
	}

}
