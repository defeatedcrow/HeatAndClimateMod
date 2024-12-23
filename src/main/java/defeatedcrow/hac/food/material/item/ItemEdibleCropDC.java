package defeatedcrow.hac.food.material.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;

public class ItemEdibleCropDC extends ItemCropDC implements IFoodTaste {

	public ItemEdibleCropDC(CropTier rare, CropType t, String s, TagKey<Item> pair) {
		super(new Item.Properties().rarity(rare.getRarity()).tab(FoodInit.AGRI), rare, t, s, pair, false);
	}

	public ItemEdibleCropDC(CropTier rare, CropType t, String s, TagKey<Item> pair, int nut, float sat) {
		super(new Item.Properties().rarity(rare.getRarity()).tab(FoodInit.AGRI).food(new FoodProperties.Builder().nutrition(nut).saturationMod(sat).alwaysEat().build()), rare, t, s, pair, false);
	}

	public TagKey<Item> getFlavorTag() {
		int t = taste > -3 ? taste : tier.getTaste();
		switch (t) {
		case -2:
			return TagDC.ItemTag.HAC_FOOD_FLAVOR1;
		case -1:
			return TagDC.ItemTag.HAC_FOOD_FLAVOR2;
		case 0:
			return TagDC.ItemTag.HAC_FOOD_FLAVOR3;
		case 1:
			return TagDC.ItemTag.HAC_FOOD_FLAVOR4;
		case 2:
			return TagDC.ItemTag.HAC_FOOD_FLAVOR5;
		default:
			return TagDC.ItemTag.HAC_FOOD_FLAVOR3;
		}
	}

	@Override
	public int getTaste(ItemStack item) {
		if (DCUtil.isEmpty(item))
			return 0;
		CompoundTag tag = item.getOrCreateTag();
		if (item.getTag().contains(TagKeyDC.TASTE)) {
			int tagTaste = item.getTag().getInt(TagKeyDC.TASTE);
			tagTaste = Mth.clamp(tagTaste, -2, 2);
			return tagTaste;
		} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR)) {
			int tagTaste = 0;
			if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR5)) {
				tagTaste = 2;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR4)) {
				tagTaste = 1;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR3)) {
				tagTaste = 0;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR2)) {
				tagTaste = -1;
			} else if (item.is(TagDC.ItemTag.HAC_FOOD_FLAVOR1)) {
				tagTaste = -2;
			}
			setTaste(item, tagTaste);
			return tagTaste;
		} else {
			int tagTaste = taste > -3 ? taste : tier.getTaste();
			setTaste(item, tagTaste);
			return tagTaste;
		}
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
	public boolean isSeasoning() {
		return false;
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable BlockGetter level, List<Component> list, boolean flag) {
		if (this.isEdible()) {
			MutableComponent tasteName = Component.translatable("dcs.tip.raw_eaten_crop");
			tasteName.withStyle(ChatFormatting.AQUA);
			list.add(tasteName);
		}
	}

}
