package defeatedcrow.hac.main.item.tool;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemSwordDC extends ItemSword implements ITexturePath {

	private final float attackDam;
	private final String tex;
	private final boolean isShort;

	public ItemSwordDC(ToolMaterial m, String t, boolean b) {
		super(m);
		tex = t;
		this.attackDam = 1.0F + m.getDamageVsEntity();
		this.isShort = b;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/sword_" + tex;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

		if (isShort) {
			if (slot == EntityEquipmentSlot.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
						new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDam, 0));
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
						new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -1.0D, 0));
			}
		} else {
			if (slot == EntityEquipmentSlot.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
						new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDam + 4.0F, 0));
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
						new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -3.0D, 0));
			}
		}

		return multimap;
	}

}
