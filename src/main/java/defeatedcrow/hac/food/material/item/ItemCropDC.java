package defeatedcrow.hac.food.material.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemCropDC extends ItemDC implements IFoodTaste {

	final String name;
	private String domain = "food";
	private int taste = -5;

	private final CropType type;
	private final CropTier tier;

	public ItemCropDC(CropTier rare, CropType t, String s, TagKey<Item> pair) {
		super(new Item.Properties().rarity(rare.getRarity()).tab(FoodInit.AGRI), pair);
		name = s;
		type = t;
		tier = rare;
	}

	public ItemCropDC(CropTier rare, CropType t, String s, TagKey<Item> pair, int nut, float sat) {
		super(new Item.Properties().rarity(rare.getRarity()).tab(FoodInit.AGRI).food(new FoodProperties.Builder().nutrition(nut).saturationMod(sat).alwaysEat().build()), pair);
		name = s;
		type = t;
		tier = rare;
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
	public int getTaste(ItemStack item) {
		return taste > -3 ? taste : tier.getTaste();
	}

	@Override
	public void setTaste(ItemStack item, int i) {}

	@Override
	public boolean isSeasoning() {
		return false;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (this.isEdible()) {
			MutableComponent tasteName = Component.translatable("dcs.tip.raw_eaten_crop");
			tasteName.withStyle(ChatFormatting.AQUA);
			list.add(tasteName);
		}
		int taste = getTaste(item) + 3;
		MutableComponent tasteName = Component.translatable("dcs.tip.foodtaste." + taste);
		tasteName.withStyle(ChatFormatting.YELLOW);
		list.add(tasteName);
		super.appendHoverText(item, level, list, flag);
	}

}
