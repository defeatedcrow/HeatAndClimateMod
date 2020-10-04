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

public class ItemMicrobeBacillus extends MicrobeItem implements IMicrobe {

	public ItemMicrobeBacillus() {
		super();
	}

	@Override
	public Item getMicrobeItem() {
		return FoodInit.bacillus;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/food/brewing/bacilli_bacillus";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String getName() {
		return "bacillus";
	}

	@Override
	public boolean getGramStaining() {
		return true;
	}

	@Override
	public int getIncubationDays() {
		return 2;
	}

	@Override
	public EnumMicrobeType getType() {
		return EnumMicrobeType.BACILLI;
	}

	@Override
	public int getChance(EnumHabitat habitat) {
		switch (habitat) {
		case ANIMAL:
			return 10;
		case CROP:
			return 40;
		case FLOWER:
			return 10;
		case SOIL:
			return 10;
		case WATER:
			return 15;
		default:
			return 0;
		}
	}

	@Override
	public List<EnumMedium> getMediums() {
		return ImmutableList.of(EnumMedium.SIMPLE, EnumMedium.STANDARD);
	}

	@Override
	public List<DCHeatTier> getHeats() {
		return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
	}

	@Override
	public List<DCHumidity> getHums() {
		return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getAirs() {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

}
