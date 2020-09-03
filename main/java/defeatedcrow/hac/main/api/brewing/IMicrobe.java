package defeatedcrow.hac.main.api.brewing;

import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;

public interface IMicrobe {

	public String getName();

	public int getSpeed();

	public EnumMicrobeType getType();

	public int getChance(EnumHabitat habitat);

	public List<EnumMedium> getMediums();

	public List<DCHeatTier> getHeats();

	public List<DCHumidity> hetHums();

	public List<DCAirflow> getAirs();

}
