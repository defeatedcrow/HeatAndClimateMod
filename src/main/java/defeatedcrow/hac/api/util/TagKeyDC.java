package defeatedcrow.hac.api.util;

public class TagKeyDC {

	public static final String TASTE = "dcs.taste";
	public static final String AGE = "dcs.age";
	public static final String MAX_AGE = "dcs.max_age";
	public static final String OWNER_NAME = "dcs.owner_name";
	public static final String OWNER_UUID = "dcs.owner_uuid";
	public static final String ITEM = "dcs.item";
	public static final String FLUID = "dcs.fluid";
	public static final String ENERGY = "dcs.energy";
	public static final String CAPACITY = "dcs.cap";
	public static final String HEAD = "dcs.head";
	public static final String FACE_IO = "dcs.face_io";
	public static final String LAST_PROGRESS = "dcs.last_progress";
	public static final String CURRENT_PROGRESS = "dcs.current_progress";
	public static final String MAX_PROGRESS = "dcs.max_progress";
	public static final String HEAT_TIER = "dcs.heat";
	public static final String HUMIDITY = "dcs.humidity";
	public static final String AIRFLOW = "dcs.airflow";

	public static final String getTankKey(int id) {
		return "dcs.tank_" + id;
	}

}
