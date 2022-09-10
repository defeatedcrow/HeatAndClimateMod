package defeatedcrow.hac.core.climate.register;

import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;

public class ParamBlock {
	public final String blockName;
	public final  String property;
	public final  List<String> values;
	public final  DCHeatTier heat;
	public final  DCHumidity humidity;
	public final  DCAirflow airflow;

	public ParamBlock(String key, String prop, List<String> val, DCHeatTier tier, DCHumidity hum, DCAirflow air) {
		blockName = key;
		property = prop;
		values = val;
		heat = tier;
		humidity = hum;
		airflow = air;
	}
}
