package defeatedcrow.hac.core.material.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class FishingEnchantment extends Enchantment {

	public FishingEnchantment(EquipmentSlot... slots) {
		super(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.FISHING_ROD, slots);
	}

	@Override
	public int getMinCost(int i) {
		return 15 + (i - 1) * 9;
	}

	@Override
	public int getMaxCost(int i) {
		return super.getMinCost(i) + 50;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public boolean checkCompatibility(Enchantment target) {
		return super.checkCompatibility(target) && !(target instanceof FishingEnchantment);
	}

}
