package defeatedcrow.hac.main.block.fluid;

import java.util.ArrayList;
import java.util.List;

public class FluidUtil {

	private FluidUtil() {

	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/fluid/";
		list.add(b + "blacktea_still");
		list.add(b + "greentea_still");
		list.add(b + "coffee_still");
		list.add(b + "seedoil_still");
		list.add(b + "cream_still");
		list.add(b + "black_liquor_still");
		list.add(b + "hotspring_still");
		list.add(b + "vegetable_still");
		list.add(b + "stock_still");
		list.add(b + "hydrogen_still");
		list.add(b + "ammonia_still");
		list.add(b + "fuel_gas_still");
		list.add(b + "fuel_oil_still");
		list.add(b + "nitric_acid_still");
		list.add(b + "sulfuric_acid_still");
		return list;
	}

}
