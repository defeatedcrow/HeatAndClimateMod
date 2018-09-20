package defeatedcrow.hac.machine.item.plating;

import defeatedcrow.hac.main.MainInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;

public class ItemPlatingChrome extends ItemPlatingBase {

	@Override
	public int getMaxMeta() {
		return 9;
	}

	public ItemPlatingChrome() {
		super();
	}

	@Override
	public String getItemName(int meta) {
		return Platings.getName(meta);
	}

	@Override
	public Enchantment[] getEnchantments(int meta) {
		return Platings.getEnchantments(meta);
	}

	public static enum Platings {
		CHROME(0, "chrome", Enchantments.EFFICIENCY, Enchantments.SHARPNESS, Enchantments.POWER),
		NICKEL(1, "nickel", Enchantments.AQUA_AFFINITY, Enchantments.KNOCKBACK, Enchantments.PUNCH),
		ZINC(2, "zinc", Enchantments.UNBREAKING, Enchantments.PROTECTION),
		SILVER(3, "silver", Enchantments.PROJECTILE_PROTECTION, Enchantments.SMITE),
		BISMUTH(4, "bismuth", Enchantments.FORTUNE, Enchantments.LOOTING, Enchantments.LUCK_OF_THE_SEA),
		BLAZE(5, "blaze", Enchantments.BLAST_PROTECTION, Enchantments.FIRE_ASPECT, Enchantments.FLAME),
		POISON(6, "poison", Enchantments.THORNS, MainInit.venom),
		BORAX(7, "borax", Enchantments.BANE_OF_ARTHROPODS, Enchantments.FIRE_PROTECTION),
		PLASTIC(8, "plastic", Enchantments.SILK_TOUCH, Enchantments.RESPIRATION, Enchantments.DEPTH_STRIDER),
		LURE(9, "lure", Enchantments.LURE);

		private final String name;
		private final int id;
		private final Enchantment[] enthants;

		private Platings(int i, String n, Enchantment... enchantments) {
			id = i;
			name = n;
			enthants = enchantments;
		}

		public static final Platings[] VALUE = {
				CHROME,
				NICKEL,
				ZINC,
				SILVER,
				BISMUTH,
				BLAZE,
				POISON,
				BORAX,
				PLASTIC,
				LURE
		};

		public static String getName(int meta) {
			meta = Math.max(meta, 0);
			meta = Math.min(meta, 9);
			return VALUE[meta].name;
		}

		public static Enchantment[] getEnchantments(int meta) {
			meta = Math.max(meta, 0);
			meta = Math.min(meta, 9);
			return VALUE[meta].enthants;
		}

	}
}
