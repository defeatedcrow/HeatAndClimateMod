package defeatedcrow.hac.magic;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewel;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.material.entity.OwnableMagicEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;

public class MagicUtil {

	public static List<? extends OwnableMagicEntity> getMagicEntity(ServerLevel level, MagicColor color) {
		List<? extends OwnableMagicEntity> list = level.getEntities(EntityTypeTest.forClass(OwnableMagicEntity.class), checkColor(color));
		return list;
	}

	private static Predicate<Entity> checkColor(MagicColor color) {
		return (e) -> {
			return e instanceof OwnableMagicEntity && ((OwnableMagicEntity) e).getColor() == color;
		};
	}

	public static ArrayList<ItemStack> getCharms(LivingEntity living, CharmType type) {
		ArrayList<ItemStack> ret = Lists.newArrayList();
		if (living != null) {
			if (living instanceof Player) {
				Player player = (Player) living;
				for (int i = 9; i < 18; i++) {
					ItemStack check = player.getInventory().getItem(i);
					if (!check.isEmpty() && check.getItem() instanceof IJewel) {
						IJewel charm = (IJewel) check.getItem();
						if (charm.getType() == MagicType.INVENTORY_TOP && charm.getCharmType().match(type)) {
							boolean b = false;
							for (ItemStack c2 : ret) {
								if (DCItemUtil.isSameItem(check, c2, false)) {
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
					if (!check.isEmpty() && check.getItem() instanceof IJewel) {
						IJewel charm = (IJewel) check.getItem();
						if (charm.getType() == MagicType.INVENTORY_TOP && charm.getCharmType().match(type)) {
							boolean b = false;
							for (ItemStack c2 : ret) {
								if (DCItemUtil.isSameItem(check, c2, false)) {
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
						if (!check.isEmpty() && check.getItem() instanceof IJewel) {
							IJewel charm = (IJewel) check.getItem();
							if (charm.getType() == MagicType.INVENTORY_TOP && charm.getCharmType().match(type)) {
								boolean b = false;
								for (ItemStack c2 : ret) {
									if (DCItemUtil.isSameItem(check, c2, false)) {
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

	public static int hasCharmItem(LivingEntity living, ItemStack item) {
		int count = 0;
		if (living != null && !DCUtil.isEmpty(item)) {
			if (living instanceof Player) {
				Player player = (Player) living;
				for (int i = 9; i < 18; i++) {
					ItemStack check = player.getInventory().getItem(i);
					if (!check.isEmpty() && DCItemUtil.isSameItem(check, item, false)) {
						count += check.getCount();
					}
				}
			} else if (living instanceof AbstractVillager) {
				SimpleContainer inv = ((AbstractVillager) living).getInventory();
				for (int i = 0; i < inv.getContainerSize(); i++) {
					ItemStack check = inv.getItem(i);
					if (!check.isEmpty() && DCItemUtil.isSameItem(check, item, false)) {
						count += check.getCount();
					}
				}
			} else {
				IItemHandler handler = living.getCapability(ForgeCapabilities.ITEM_HANDLER, null).orElse(null);
				if (handler != null) {
					for (int i = 0; i < handler.getSlots(); i++) {
						ItemStack check = handler.getStackInSlot(i);
						if (!check.isEmpty() && DCItemUtil.isSameItem(check, item, false)) {
							count += check.getCount();
						}
					}
				}
			}
		}
		return count;
	}

	public static int hasCharmItem(LivingEntity living, Ingredient target) {
		int count = 0;
		if (living != null && target != null && !target.isEmpty()) {
			if (living instanceof Player) {
				Player player = (Player) living;
				for (int i = 9; i < 18; i++) {
					ItemStack check = player.getInventory().getItem(i);
					if (target.test(check)) {
						count += check.getCount();
					}
				}
			} else if (living instanceof AbstractVillager) {
				SimpleContainer inv = ((AbstractVillager) living).getInventory();
				for (int i = 0; i < inv.getContainerSize(); i++) {
					ItemStack check = inv.getItem(i);
					if (target.test(check)) {
						count += check.getCount();
					}
				}
			} else {
				IItemHandler handler = living.getCapability(ForgeCapabilities.ITEM_HANDLER, null).orElse(null);
				if (handler != null) {
					for (int i = 0; i < handler.getSlots(); i++) {
						ItemStack check = handler.getStackInSlot(i);
						if (target.test(check)) {
							count += check.getCount();
						}
					}
				}
			}
		}
		return count;
	}

	public static float getMagicBooster(LivingEntity living) {
		int count = 0;
		count += hasCharmItem(living, Ingredient.of(TagDC.ItemTag.MAGIC_BOOSTER));
		return count;
	}

}
