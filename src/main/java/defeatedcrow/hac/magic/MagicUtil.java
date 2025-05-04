package defeatedcrow.hac.magic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewel;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.api.magic.SearchPlayerCharmEvent;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.material.entity.MagicPictureEntity;
import defeatedcrow.hac.magic.material.entity.OwnableMagicEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.eventbus.api.Event.Result;
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

	public static List<? extends MagicPictureEntity> getPictureEntity(ServerLevel level) {
		List<? extends MagicPictureEntity> list = level.getEntities(EntityTypeTest.forClass(MagicPictureEntity.class), Entity::isAlive);
		return list;
	}

	public static Optional<? extends MagicPictureEntity> isPictureEntityInWorld(ServerLevel level, MagicColor color) {
		return level.getEntities(EntityTypeTest.forClass(MagicPictureEntity.class), checkColor(color)).stream().findAny();
	}

	public static ArrayList<ItemStack> getCharms(LivingEntity living, CharmType type) {
		ArrayList<ItemStack> ret = Lists.newArrayList();
		if (living != null) {
			if (living instanceof Player) {
				Player player = (Player) living;
				for (int i = 9; i < 18; i++) {
					ItemStack check = player.getInventory().getItem(i);
					if (isCharmItem(check, type)) {
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
			} else if (living instanceof AbstractVillager) {
				SimpleContainer inv = ((AbstractVillager) living).getInventory();
				for (int i = 0; i < inv.getContainerSize(); i++) {
					ItemStack check = inv.getItem(i);
					if (isCharmItem(check, type)) {
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
			} else {
				IItemHandler handler = living.getCapability(ForgeCapabilities.ITEM_HANDLER, null).orElse(null);
				if (handler != null) {
					for (int i = 0; i < handler.getSlots(); i++) {
						ItemStack check = handler.getStackInSlot(i);
						if (isCharmItem(check, type)) {
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

			SearchPlayerCharmEvent event = new SearchPlayerCharmEvent(living, type, ret);
			List<ItemStack> post = event.result();
			if (event.hasResult() && event.getResult() == Result.ALLOW) {
				ret.clear();
				ret.addAll(post);
			}
		}
		return ret;
	}

	public static ItemStack getHandCharms(LivingEntity living, CharmType type) {
		if (living != null) {
			ItemStack off = living.getOffhandItem();
			if (isHandCharmItem(off, type)) {
				return off.copy();
			}
		}
		return ItemStack.EMPTY;
	}

	private static boolean isCharmItem(ItemStack check, CharmType type) {
		if (!check.isEmpty()) {
			if (type == null) {
				return true;
			} else if (check.getItem() instanceof IJewel) {
				IJewel charm = (IJewel) check.getItem();
				if (charm.getMagicType() == MagicType.INVENTORY_TOP && charm.getCharmType().match(type)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isHandCharmItem(ItemStack check, CharmType type) {
		if (!check.isEmpty()) {
			if (type == null) {
				return true;
			} else if (check.getItem() instanceof IJewel) {
				IJewel charm = (IJewel) check.getItem();
				if (charm.getMagicType() == MagicType.OFFHAND && charm.getCharmType().match(type)) {
					return true;
				}
			}
		}
		return false;
	}

	public static int hasCharmItem(LivingEntity living, ItemStack item) {
		int count = 0;
		if (living != null && !DCUtil.isEmpty(item) && item.getItem() instanceof IJewel jewel) {
			ArrayList<ItemStack> ret = getCharms(living, jewel.getCharmType());
			if (!ret.isEmpty()) {
				for (int i = 0; i < ret.size(); i++) {
					ItemStack check = ret.get(i);
					if (!check.isEmpty() && DCItemUtil.isSameItem(check, item, false)) {
						count += check.getCount();
					}
				}
			}
		}
		return count;
	}

	public static boolean hasHandCharms(LivingEntity living, ItemStack item) {
		ArrayList<ItemStack> ret = Lists.newArrayList();
		if (living != null && !item.isEmpty() && item.getItem() instanceof IJewelCharm charm) {
			if (charm.isActive(living, item)) {
				ItemStack off = living.getOffhandItem();
				if (!off.isEmpty() && DCItemUtil.isSameItem(off, item, false)) {
					return true;
				}
			}
		}
		return false;
	}

	public static int hasCharmItem(LivingEntity living, Ingredient target) {
		int count = 0;
		if (living != null && target != null && !target.isEmpty()) {
			ArrayList<ItemStack> ret = getCharms(living, null);
			if (!ret.isEmpty()) {
				for (int i = 0; i < ret.size(); i++) {
					ItemStack check = ret.get(i);
					if (target.test(check)) {
						count += check.getCount();
					}
				}
			}
		}
		return count;
	}

	public static float getMagicBooster(LivingEntity living) {
		float count = 1.0F;
		float affinity = 1.0F;
		count += hasCharmItem(living, Ingredient.of(TagDC.ItemTag.MAGIC_BOOSTER));
		// armorInvWrapper
		IItemHandler handler = living.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.NORTH).orElse(null);
		if (handler != null) {
			for (int i = 0; i < handler.getSlots(); i++) {
				ItemStack check = handler.getStackInSlot(i);
				if (check.getItem() instanceof ArmorItem armor) {
					ArmorMaterial mat = armor.getMaterial();
					int a = mat.getEnchantmentValue();
					float aff = a / 10F;
					affinity *= aff;
				}
			}
		}
		if (affinity < 0.5F)
			affinity = 0.5F;
		return count * affinity;
	}

	public static List<MagicColor> getBiomeColor(Holder<Biome> biome) {
		ArrayList<MagicColor> ret = Lists.newArrayList();
		if (biome.is(TagDC.BiomeTag.WHITE_BIOME)) {
			ret.add(MagicColor.WHITE);
		}
		if (biome.is(TagDC.BiomeTag.BLUE_BIOME)) {
			ret.add(MagicColor.BLUE);
		}
		if (biome.is(TagDC.BiomeTag.BLACK_BIOME)) {
			ret.add(MagicColor.BLACK);
		}
		if (biome.is(TagDC.BiomeTag.RED_BIOME)) {
			ret.add(MagicColor.RED);
		}
		if (biome.is(TagDC.BiomeTag.GREEN_BIOME)) {
			ret.add(MagicColor.GREEN);
		}
		return ret;
	}

}
