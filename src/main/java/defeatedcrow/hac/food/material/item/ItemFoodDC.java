package defeatedcrow.hac.food.material.item;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import org.apache.commons.compress.utils.Lists;

import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.item.ItemDC;
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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public abstract class ItemFoodDC extends ItemDC implements IFoodTaste {

	private boolean rawFood = false;

	public ItemFoodDC(int nut, float sat, TagKey<Item> pair) {
		super(new Item.Properties().tab(FoodInit.FOOD).food(new FoodProperties.Builder().nutrition(nut).saturationMod(sat).alwaysEat().build()), pair);
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
	public Optional<String[]> getModelNameSuffix() {
		return Optional.empty();
	}

	@Override
	public abstract JsonModelSimpleDC getItemModel();

	@Override
	public BlockType getDropType() {
		return BlockType.ITEM;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		int taste = getTaste(item) + 3;
		MutableComponent tasteName = Component.translatable("dcs.tip.foodtaste." + taste);
		tasteName.withStyle(ChatFormatting.YELLOW);
		list.add(tasteName);
		if (getPairTag() != null && flag.isAdvanced())
			list.add(Component.literal("Tag: " + getPairTag().location().toString()));
	}

	/* FoodDC */

	@Override
	public int getTaste(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.getItem() instanceof IFoodTaste && item.getTagElement(TagKeyDC.TASTE) != null) {
			int taste = item.getTag().getInt(TagKeyDC.TASTE);
			taste = Mth.clamp(-2, taste, 2);
			return taste;
		}
		return 2;
	}

	@Override
	public void setTaste(ItemStack item, int i) {
		if (!DCUtil.isEmpty(item)) {
			int taste = Mth.clamp(-2, i, 2);
			CompoundTag tag = item.getOrCreateTag();
			tag.putInt(TagKeyDC.TASTE, taste);
			item.setTag(tag);
		}
	}

	@Override
	public int getUseDuration(ItemStack item) {
		int taste = getTaste(item) + 3;
		int dur = 128 / (taste * 2);
		return dur;
	}

	@Override
	public boolean isSeasoning() {
		return false;
	}

}
