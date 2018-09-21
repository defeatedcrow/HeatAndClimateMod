package defeatedcrow.hac.main.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentRobber extends Enchantment {
	public EnchantmentRobber() {
		super(Enchantment.Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {
				EntityEquipmentSlot.MAINHAND
		});
		this.setName("dcs.robber");
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 15 + 5 * (enchantmentLevel);
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return super.getMinEnchantability(enchantmentLevel) + 10;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}
}
