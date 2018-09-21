package defeatedcrow.hac.main.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentVenom extends Enchantment {
	public EnchantmentVenom() {
		super(Enchantment.Rarity.RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[] {
				EntityEquipmentSlot.MAINHAND
		});
		this.setName("dcs.venom");
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 10 + 20 * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}

	@Override
	public int getMaxLevel() {
		return 2;
	}

	@Override
	public boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench) && ench != Enchantments.FLAME;
	}
}
