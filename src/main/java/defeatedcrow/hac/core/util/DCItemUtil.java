package defeatedcrow.hac.core.util;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.TagKey;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.IReverseTag;

public class DCItemUtil {

	public static boolean containsItem(NonNullList<ItemStack> list, ItemStack item, boolean nbtSensitive) {
		if (list.isEmpty() || item.isEmpty()) {
			return false;
		} else {
			for (ItemStack check : list) {
				if (isSameItem(check, item, nbtSensitive)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isSameItem(ItemStack ins, ItemStack slot, boolean nbtSensitive) {
		if (ins.isEmpty() || slot.isEmpty()) {
			return false;
		} else if (ins.getItem() == slot.getItem()) {
			return !nbtSensitive || ins.tagMatches(ins, slot);
		}
		return false;
	}

	public static boolean canInsert(ItemStack ins, ItemStack slot) {
		if (!ins.isEmpty() && slot.isEmpty()) {
			return true;
		} else {
			return isSameItem(ins, slot, true) && ins.getCount() <= (slot.getMaxStackSize() - slot.getCount());
		}
	}

	public static boolean hasSameTag(ItemStack item, ItemStack check) {
		if (!item.isEmpty() && !check.isEmpty()) {
			IReverseTag<Item> tags = ForgeRegistries.ITEMS.tags().getReverseTag(item.getItem()).orElse(null);
			IReverseTag<Item> tags2 = ForgeRegistries.ITEMS.tags().getReverseTag(check.getItem()).orElse(null);
			if (tags == null || tags2 == null) {
				return false;
			}
			for (TagKey<Item> t : tags.getTagKeys().toList()) {
				if (tags2.containsTag(t))
					return true;
			}
		}
		return false;
	}

	/* Playerの所持チェック */
	public static boolean isPlayerHeldItem(Item item, Player player) {
		if (item == null || player == null)
			return false;

		return isPlayerHeldItem(new ItemStack(item, 1), player);
	}

	public static boolean isPlayerHeldItem(ItemStack item, Player player) {
		if (item.isEmpty() || player == null)
			return false;

		if (!player.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
			if (isSameItem(item, player.getItemBySlot(EquipmentSlot.MAINHAND), false))
				return true;
		}
		if (player.getItemBySlot(EquipmentSlot.OFFHAND) != null) {
			if (isSameItem(item, player.getItemBySlot(EquipmentSlot.OFFHAND), false))
				return true;
		}

		return false;
	}

	public static NonNullList<ItemStack> getCharms(LivingEntity living, CharmType type) {
		NonNullList<ItemStack> ret = NonNullList.create();
		if (living != null) {
			if (living instanceof Player) {
				Player player = (Player) living;
				for (int i = 9; i < 18; i++) {
					ItemStack check = player.getInventory().getItem(i);
					if (!check.isEmpty() && check.getItem() instanceof IJewelCharm) {
						IJewelCharm charm = (IJewelCharm) check.getItem();
						if (type == CharmType.ALL || charm.getCharmType() == type) {
							boolean b = false;
							for (ItemStack c2 : ret) {
								if (isSameItem(check, c2, false)) {
									c2.grow(1);
									b = true;
									break;
								}
							}
							if (!b) {
								ret.add(check.copy());
							}
						}
					}
				}
			} else if (living instanceof AbstractVillager) {
				SimpleContainer inv = ((AbstractVillager) living).getInventory();
				for (int i = 0; i < inv.getContainerSize(); i++) {
					ItemStack check = inv.getItem(i);
					if (!check.isEmpty() && check.getItem() instanceof IJewelCharm) {
						IJewelCharm charm = (IJewelCharm) check.getItem();
						if (type == CharmType.ALL || charm.getCharmType() == type) {
							boolean b = false;
							for (ItemStack c2 : ret) {
								if (isSameItem(check, c2, false)) {
									c2.grow(1);
									b = true;
									break;
								}
							}
							if (!b) {
								ret.add(check.copy());
							}
						}
					}
				}
			} else {
				IItemHandler handler =
						living.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null);
				if (handler != null) {
					for (int i = 0; i < handler.getSlots(); i++) {
						ItemStack check = handler.getStackInSlot(i);
						if (!check.isEmpty() && check.getItem() instanceof IJewelCharm) {
							IJewelCharm charm = (IJewelCharm) check.getItem();
							if (type == CharmType.ALL || charm.getCharmType() == type) {
								boolean b = false;
								for (ItemStack c2 : ret) {
									if (isSameItem(check, c2, false)) {
										c2.grow(1);
										b = true;
										break;
									}
								}
								if (!b) {
									ret.add(check.copy());
								}
							}
						}
					}
				}
			}
		}
		return ret;
	}

	public static boolean hasCharmItem(LivingEntity living, ItemStack item) {
		if (living == null || item.isEmpty())
			return false;
		NonNullList<ItemStack> charms = getCharms(living, CharmType.ALL);
		for (ItemStack check : charms) {
			if (!check.isEmpty() && isSameItem(check, item, false)) {
				return true;
			}
		}
		return false;
	}

	public static float getItemResistantData(ItemStack item, boolean isCold) {
		if (item.isEmpty())
			return 0F;

		float p = 0F;
		if (isCold) {
			p += ClimateAPI.registerArmor.getColdPreventAmount(item);
		} else {
			p += ClimateAPI.registerArmor.getHeatPreventAmount(item);
		}
		if (p == 0F && item.getItem() instanceof ArmorItem) {
			ArmorMaterial mat = ((ArmorItem) item.getItem()).getMaterial();
			if (isCold) {
				p += ClimateAPI.registerMaterial.getColdPreventAmount(mat);
			} else {
				p += ClimateAPI.registerMaterial.getHeatPreventAmount(mat);
			}
			if (!isCold && item.getEnchantmentLevel(Enchantments.FIRE_PROTECTION) > 0) {
				p += item.getEnchantmentLevel(Enchantments.FIRE_PROTECTION) * 1.0F;
			}
		}
		return p;
	}

}
