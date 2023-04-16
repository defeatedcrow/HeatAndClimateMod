package defeatedcrow.hac.magic.material.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.IColorDC;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public abstract class BaseArrowItem extends ArrowItem implements IJsonDataDC, IItemDC, IColorDC {

	final TagKey<Item> tag;
	protected final String name;
	private final MagicColor color;

	public BaseArrowItem(MagicColor c, String s, TagKey<Item> pair) {
		super(new Item.Properties().tab(MagicInit.MAGIC));
		tag = pair;
		color = c;
		name = s;
	}

	@Override
	public MagicColor getColor() {
		return color;
	}

	@Override
	public String getRegistryName() {
		return "magic/arrow_" + name;
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/magic/arrow_" + name));
	}

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
	public BlockType getDropType() {
		return BlockType.ITEM;
	}

	@Override
	public TagKey<Item> getPairTag() {
		return tag == null ? TagDC.ItemTag.DUMMY : tag;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent tier = Component.translatable(color + " " + item.getRarity());
		tier.withStyle(color.chatColor);
		list.add(tier);
		MutableComponent itemName = Component.translatable("dcs.tip.magic_arrow.name." + color.toString());
		itemName.withStyle(color.chatColor).withStyle(ChatFormatting.ITALIC);
		list.add(itemName);
		if (ClimateCore.proxy.keyShiftPushed()) {
			MutableComponent itemTip = Component.translatable("dcs.tip.magic_arrow.desc." + color.toString());
			list.add(itemTip);
		}
		super.appendHoverText(item, level, list, flag);
	}

}
