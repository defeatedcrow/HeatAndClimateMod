package defeatedcrow.hac.core.climate.register;

public class ParamArmorItem {
	public final String itemName;
	public final float heatResistance;
	public final float coldResistance;

	public ParamArmorItem(String item, float heat, float cold) {
		itemName = item;
		heatResistance = heat;
		coldResistance = cold;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof ParamArmorItem) {
			ParamArmorItem target = (ParamArmorItem) obj;
			return itemName.equals(target.itemName) && heatResistance == target.heatResistance && coldResistance == target.coldResistance;
		}
		return false;
	}
}
