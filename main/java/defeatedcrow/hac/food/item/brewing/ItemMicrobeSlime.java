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

public class ItemMicrobeSlime extends MicrobeItem implements IMicrobe {

	public ItemMicrobeSlime() {
		super();
	}

	@Override
	public Item getMicrobeItem() {
		return FoodInit.slimeMold;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/food/brewing/mold_slime";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String getName() {
		return "slime";
	}

	@Override
	public boolean getGramStaining() {
		return true;
	}

	@Override
	public int getIncubationDays() {
		return 5;
	}

	@Override
	public EnumMicrobeType getType() {
		return EnumMicrobeType.FUNGI;
	}

	@Override
	public int getChance(EnumHabitat habitat) {
		switch (habitat) {
		case ANIMAL:
			return 0;
		case CROP:
			return 5;
		case FLOWER:
			return 0;
		case SOIL:
			return 20;
		case WATER:
			return 5;
		default:
			return 0;
		}
	}

	@Override
	public List<EnumMedium> getMediums() {
		return ImmutableList.of(EnumMedium.BACTERIA);
	}

	@Override
	public List<DCHeatTier> getHeats() {
		return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL);
	}

	@Override
	public List<DCHumidity> getHums() {
		return ImmutableList.of(DCHumidity.WET, DCHumidity.UNDERWATER);
	}

	@Override
	public List<DCAirflow> getAirs() {
		return ImmutableList.of(DCAirflow.TIGHT, DCAirflow.NORMAL, DCAirflow.FLOW);
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

}
