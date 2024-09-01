package defeatedcrow.hac.food.material.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemCropDC extends ItemDC {

	final String name;
	protected String domain = "food";
	protected int taste = -5;
	protected boolean poisonous = false;

	protected final CropType type;
	protected final CropTier tier;

	public ItemCropDC(CropTier rare, CropType t, String s, TagKey<Item> pair, boolean poison) {
		super(new Item.Properties().rarity(rare.getRarity()).tab(FoodInit.AGRI), pair);
		name = s;
		type = t;
		tier = rare;
		poisonous = poison;
	}

	public ItemCropDC(Item.Properties prop, CropTier rare, CropType t, String s, TagKey<Item> pair, boolean poison) {
		super(prop, pair);
		name = s;
		type = t;
		tier = rare;
		poisonous = poison;
	}

	public boolean isPoisonous() {
		return poisonous;
	}

	public ItemCropDC setDomain(String s) {
		domain = s;
		return this;
	}

	public ItemCropDC taste(int i) {
		taste = i;
		return this;
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/" + name));
	}

	public CropType getType() {
		return type;
	}

	public CropTier getTier() {
		return tier;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (poisonous) {
			MutableComponent tasteName = Component.translatable("dcs.tip.not_eaten_crop");
			tasteName.withStyle(ChatFormatting.RED);
			list.add(tasteName);
		}
		super.appendHoverText(item, level, list, flag);
	}

}
