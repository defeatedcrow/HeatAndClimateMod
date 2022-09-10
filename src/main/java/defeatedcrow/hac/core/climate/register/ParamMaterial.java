package defeatedcrow.hac.core.climate.register;

import net.minecraft.world.item.ArmorMaterial;

public class ParamMaterial {
	public final String materialName;
	public final float heatResistance;
	public final float coldResistance;

	public ParamMaterial(ArmorMaterial mat, float heat, float cold) {
		materialName = mat.getName();
		heatResistance = heat;
		coldResistance = cold;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof ParamMaterial) {
			ParamMaterial target = (ParamMaterial) obj;
			return materialName.equals(target.materialName) && heatResistance == target.heatResistance && coldResistance == target.coldResistance;
		}
		return false;
	}
}
