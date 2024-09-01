package defeatedcrow.hac.food.material.item;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public abstract class ItemFoodDC extends ItemDC implements IFoodTaste {

	private boolean rawFood = false;

	public ItemFoodDC(int nut, float sat, TagKey<Item> pair) {
		super(new Item.Properties().tab(FoodInit.FOOD).food(new FoodProperties.Builder().nutrition(nut).saturationMod(sat).alwaysEat().build()), pair);
	}

	public ItemFoodDC(Properties prop, TagKey<Item> pair) {
		super(prop, pair);
	}

	public ItemFoodDC setRawFood() {
		rawFood = true;
		return this;
	}

	public boolean isRawFood() {
		return rawFood;
	}

	@Override
	public abstract String getRegistryName();

	@Override
	public List<JsonModelDC> getBlockModel() {
		return Lists.newArrayList();
	}

	@Override
	public abstract JsonModelSimpleDC getItemModel();

	@Override
	public BlockType getDropType() {
		return BlockType.ITEM;
	}

	// @Override
	// public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
	// int taste = getTaste(item) + 3;
	// MutableComponent tasteName = Component.translatable("dcs.tip.foodtaste." + taste);
	// tasteName.withStyle(ChatFormatting.YELLOW);
	// list.add(tasteName);
	// super.appendHoverText(item, level, list, flag);
	// }

	/* FoodDC */

	@Override
	public int getTaste(ItemStack item) {
		if (DCUtil.isEmpty(item))
			return 0;
		if (item.getTag() != null && item.getTag().contains(TagKeyDC.TASTE)) {
			int taste = item.getTag().getInt(TagKeyDC.TASTE);
			taste = Mth.clamp(taste, -2, 2);
			return taste;
		} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR)) {
			if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR5)) {
				return 2;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR4)) {
				return 1;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR3)) {
				return 0;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR2)) {
				return -1;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR1)) {
				return -2;
			}
		}
		return 0;
	}

	@Override
	public void setTaste(ItemStack item, int i) {
		if (!DCUtil.isEmpty(item)) {
			int taste = Mth.clamp(i, -2, 2);
			CompoundTag tag = item.getOrCreateTag();
			tag.putInt(TagKeyDC.TASTE, taste);
			item.setTag(tag);
		}
	}

	@Override
	public int getUseDuration(ItemStack item) {
		int taste = getTaste(item) + 3; // 1 ~ 5
		switch (taste) {
		case 1:
			return 256;
		case 2:
			return 64;
		case 3:
			return 32;
		case 4:
			return 16;
		case 5:
			return 8;
		default:
			return 64;
		}
	}

	// @Override
	// public ItemStack finishUsingItem(ItemStack item, Level level, LivingEntity liv) {
	// int taste = getTaste(item);
	// if (taste == 2 && !level.isClientSide) {
	// liv.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
	// }
	// return super.finishUsingItem(item, level, liv);
	// }

	@Override
	public boolean isSeasoning() {
		return false;
	}

	@Override
	public Rarity getRarity(ItemStack item) {
		if (item.getTag() != null && item.getTag().contains(TagKeyDC.TASTE)) {
			return Rarity.UNCOMMON;
		} else {
			return super.getRarity(item);
		}
	}

}
