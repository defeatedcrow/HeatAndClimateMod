package defeatedcrow.hac.core.material.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public abstract class ItemDC extends Item implements IJsonDataDC, IItemDC {

	final TagKey<Item> tag;

	public ItemDC(Properties prop, TagKey<Item> pair) {
		super(prop);
		tag = pair;
	}

	@Override
	public abstract String getRegistryName();

	@Override
	public List<JsonModelDC> getBlockModel() {
		return Lists.newArrayList();
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public boolean requireStateJson() {
		return false;
	}

	@Override
	public abstract JsonModelSimpleDC getItemModel();

	@Override
	public BlockType getDropType() {
		return BlockType.ITEM;
	}

	@Override
	public TagKey<Item> getPairTag() {
		return tag == null ? TagDC.ItemTag.DUMMY : tag;
	}

	public void advTooltipText(ItemStack item, @Nullable Level level, List<Component> list) {}

	@Override
	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
		if (!DCUtil.isEmpty(stack)) {
			if (stack.is(TagDC.ItemTag.PLANT_OIL)) {
				return 800;
			}
			if (stack.is(FoodInit.BIOMASS_BRIQUET.get())) {
				return 1600;
			}
		}
		return -1;
	}

}
