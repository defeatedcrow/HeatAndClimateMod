package defeatedcrow.hac.core.util;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.core.material.tag.TagDC;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
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
				IItemHandler handler = living.getCapability(ForgeCapabilities.ITEM_HANDLER, null).orElse(null);
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

	public static ArrayList<ItemStack> getProcessedList(Object obj) {
		ArrayList<ItemStack> ret = Lists.newArrayList();
		if (obj == null) {
			return ret;
		}
		if (obj instanceof String) {
			ret.addAll(getOres((String) obj));
		} else if (obj instanceof TagKey<?>) {
			Registry.ITEM.getTagOrEmpty((TagKey<Item>) obj).forEach(holder -> ret.add(new ItemStack(holder)));
		} else if (obj instanceof List && !((List) obj).isEmpty()) {
			ret.addAll((List<ItemStack>) obj);
		} else if (obj instanceof ItemStack) {
			if (!((ItemStack) obj).isEmpty())
				ret.add(((ItemStack) obj).copy());
		} else if (obj instanceof Item) {
			ret.add(new ItemStack((Item) obj));
		} else if (obj instanceof Block) {
			ret.add(new ItemStack((Block) obj));
		} else {
			throw new IllegalArgumentException("Unknown Object passed to recipe!");
		}
		return ret;
	}

	public static List<ItemStack> getOres(String str) {
		List<ItemStack> ret = new ArrayList<>();
		if (str == null)
			return ret;
		ResourceLocation tagname = new ResourceLocation(str);
		ForgeRegistries.ITEMS.tags().getTagNames().filter((r) -> r.location().equals(tagname) || r.location().toString().contains(str)).forEach(key -> {
			Registry.ITEM.getTagOrEmpty(key).forEach(holder -> ret.add(new ItemStack(holder)));
		});
		return ret;
	}

	public static List<ItemStack> getOres(String domain, String name) {
		List<ItemStack> ret = new ArrayList<>();
		if (domain == null || name == null)
			return ret;
		ResourceLocation tagname = new ResourceLocation(domain, name);
		ForgeRegistries.ITEMS.tags().getTagNames().filter((r) -> r.location().equals(tagname) || r.location().toString().contains(name)).forEach(key -> {
			Registry.ITEM.getTagOrEmpty(key).forEach(holder -> ret.add(new ItemStack(holder)));
		});
		return ret;
	}

	public static TagKey<Item> getTag(String domain, String name) {
		List<ItemStack> ret = new ArrayList<>();
		if (domain == null || name == null)
			return TagDC.ItemTag.DUMMY;
		ResourceLocation tagname = new ResourceLocation(domain, name);
		return ForgeRegistries.ITEMS.tags().getTagNames().filter((r) -> r.location().equals(tagname) || r.location().toString().contains(name)).findAny().orElse(TagDC.ItemTag.DUMMY);
	}

}
