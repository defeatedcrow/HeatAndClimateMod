package defeatedcrow.hac.food.item.brewing;

import java.util.List;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.api.brewing.EnumHabitat;
import defeatedcrow.hac.main.api.brewing.EnumMedium;
import defeatedcrow.hac.main.api.brewing.EnumMicrobeType;
import defeatedcrow.hac.main.api.brewing.IMicrobe;
import net.minecraft.item.Item;

public class ItemMicrobeYeast extends MicrobeItem implements IMicrobe {

	public ItemMicrobeYeast() {
		super();
	}

	@Override
	public Item getMicrobeItem() {
		return FoodInit.beerYeast;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/food/brewing/yeast_beer";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String getName() {
		return "yeast";
	}

	@Override
	public boolean getGramStaining() {
		return true;
	}

	@Override
	public int getIncubationDays() {
		return 3;
	}

	@Override
	public EnumMicrobeType getType() {
		return EnumMicrobeType.YEAST;
	}

	@Override
	public int getChance(EnumHabitat habitat) {
		switch (habitat) {
		case ANIMAL:
			return 0;
		case CROP:
			return 0;
		case FLOWER:
			return 40;
		case SOIL:
			return 15;
		case WATER:
			return 0;
		default:
			return 0;
		}
	}

	@Override
	public List<EnumMedium> getMediums() {
		return ImmutableList.of(EnumMedium.SIMPLE, EnumMedium.STANDARD, EnumMedium.POTATO);
	}

	@Override
	public List<DCHeatTier> getHeats() {
		return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
	}

	@Override
	public List<DCHumidity> getHums() {
		return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getAirs() {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW);
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

}
