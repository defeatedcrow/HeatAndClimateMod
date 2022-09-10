package defeatedcrow.hac.core.climate.register;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import defeatedcrow.hac.api.damage.IArmorMaterialRegister;
import defeatedcrow.hac.core.DCLogger;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;

public class ArmorMaterialRegister implements IArmorMaterialRegister {

	public static List<ParamMaterial> regList;

	public ArmorMaterialRegister() {
		regList = Lists.newArrayList();
		init();
	}

	private void init() {
		registerMaterial(ArmorMaterials.LEATHER, 2.0F, 2.0F);
		registerMaterial(ArmorMaterials.DIAMOND, 1.0F, 1.0F);
		registerMaterial(ArmorMaterials.TURTLE, 1.0F, 1.0F);
		registerMaterial(ArmorMaterials.NETHERITE, 3.0F, 0.0F);
	}

	@Override
	public void registerMaterial(ArmorMaterial material, float heat, float cold) {
		if (material == null)
			return;
		ParamMaterial reg = new ParamMaterial(material, heat, cold);
		if (!regList.contains(reg)) {
			regList.add(reg);
			DCLogger.infoLog("Registered armor material: " + material + "... heat " + heat + " / cold " + cold);
		}
	}

	@Override
	public float getHeatPreventAmount(ArmorMaterial material) {
		Optional<ParamMaterial> ret = regList.stream().filter((
				p) -> p.materialName.equals(material.getName())).findAny();
		return ret.map(p -> p.heatResistance).orElse(0.25F);
	}

	@Override
	public float getColdPreventAmount(ArmorMaterial material) {
		Optional<ParamMaterial> ret = regList.stream().filter((
				p) -> p.materialName.equals(material.getName())).findAny();
		return ret.map(p -> p.coldResistance).orElse(0.25F);
	}

}
