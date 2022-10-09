package defeatedcrow.hac.food.material.item;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class SeedItemDC extends BlockItemDC {

	final String name;
	private final CropType type;
	private final CropTier tier;

	public SeedItemDC(CropTier rare, CropType t, Block block, String n) {
		super(block, new Item.Properties().rarity(rare.getRarity()).tab(FoodInit.FOOD));
		name = n;
		type = t;
		tier = rare;
	}

	@Override
	public String getRegistryName() {
		return "food/" + name;
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_" + type.toString() + "_" + tier.toString()));
	}

	public CropType getType() {
		return type;
	}

	public CropTier getTier() {
		return tier;
	}

}
