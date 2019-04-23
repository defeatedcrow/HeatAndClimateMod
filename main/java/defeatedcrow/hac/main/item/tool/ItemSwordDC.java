package defeatedcrow.hac.main.item.tool;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ItemSwordDC extends ItemSword implements ITexturePath {

	private final float attackDam;
	private final String tex;
	private final boolean isShort;
	private boolean isToolsteel = false;

	public ItemSwordDC(ToolMaterial m, String t, boolean b) {
		super(m);
		tex = t;
		this.attackDam = 1.0F + m.getAttackDamage();
		this.isShort = b;
	}

	public ItemSwordDC setToolsteel() {
		isToolsteel = true;
		return this;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/sword_" + tex;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

		if (isToolsteel) {
			if (slot == EntityEquipmentSlot.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(
						ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDam + 4.0F, 0));
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(
						ATTACK_SPEED_MODIFIER, "Weapon modifier", -0.2D, 0));
			}
		} else if (isShort) {
			if (slot == EntityEquipmentSlot.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(
						ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDam, 0));
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(
						ATTACK_SPEED_MODIFIER, "Weapon modifier", 0.0D, 0));
			}
		} else {
			if (slot == EntityEquipmentSlot.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(
						ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDam + 4.0F, 0));
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(
						ATTACK_SPEED_MODIFIER, "Weapon modifier", -3.0D, 0));
			}
		}

		return multimap;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (isToolsteel) {
			if (target.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)) {
				IItemHandler handler = target
						.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
				for (int i = 0; i < handler.getSlots(); i++) {
					if (DCUtil.isEmpty(handler.getStackInSlot(i))) {
						int dam = 10 + target.world.rand.nextInt(10);
						handler.getStackInSlot(i).damageItem(dam, target);
					}
				}
			}
		}
		stack.damageItem(1, attacker);
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		if (isToolsteel)
			tooltip.add(I18n.format("dcs.tip.toolsteel.sword"));
	}

}
