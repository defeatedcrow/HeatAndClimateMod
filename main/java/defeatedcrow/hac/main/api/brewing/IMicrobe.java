package defeatedcrow.hac.main.api.brewing;

import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import net.minecraft.item.Item;

public interface IMicrobe {

	public Item getMicrobeItem();

	public String getName();

	public boolean getGramStaining();

	public int getIncubationDays();

	public EnumMicrobeType getType();

	public int getChance(EnumHabitat habitat);

	public List<EnumMedium> getMediums();

	public List<DCHeatTier> getHeats();

	public List<DCHumidity> getHums();

	public List<DCAirflow> getAirs();

}
