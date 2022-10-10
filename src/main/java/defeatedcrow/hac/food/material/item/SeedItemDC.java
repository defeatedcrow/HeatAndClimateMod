package defeatedcrow.hac.food.material.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.crop.ICropData;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class SeedItemDC extends BlockItemDC {

	final String name;
	private final CropType type;
	private final CropTier tier;
	private final ICropData data;

	public SeedItemDC(CropTier rare, CropType t, Block block, String n) {
		super(block, new Item.Properties().rarity(rare.getRarity()).tab(FoodInit.FOOD));
		name = n;
		type = t;
		tier = rare;
		if (block instanceof ICropData) {
			data = (ICropData) block;
		} else {
			data = null;
		}
	}

	@Override
	public String getRegistryName() {
		return "food/" + name;
	}

	@Override
	public JsonModelDC getItemModel() {
		String name = data == null ? "empty" : data.getSpeciesName(getTier());
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_" + type.toString() + "_" + name));
	}

	public CropType getType() {
		return type;
	}

	public CropTier getTier() {
		return tier;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent com = type.basename().append(": ").append(type.localize());
		if (tier == CropTier.WILD) {
			com.withStyle(ChatFormatting.GRAY);
			com.append(" WILD");
		} else if (tier == CropTier.COMMON) {
			com.withStyle(ChatFormatting.WHITE);
			com.append(" COMMON");
		} else if (tier == CropTier.RARE) {
			com.withStyle(ChatFormatting.YELLOW);
			com.append(" RARE");
		} else if (tier == CropTier.EPIC) {
			com.withStyle(ChatFormatting.AQUA);
			com.append(" EPIC");
		}
		list.add(com);
	}

}
