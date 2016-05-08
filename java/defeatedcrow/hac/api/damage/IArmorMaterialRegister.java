package defeatedcrow.hac.api.damage;

import java.util.HashMap;

import net.minecraft.item.ItemArmor.ArmorMaterial;

/**
 * 気候ダメージへの耐性をArmorMaterialごとに登録するためのもの。
 */
public interface IArmorMaterialRegister {

	HashMap<ArmorMaterial, Float> getArmorMap();

	void RegisterMaterial(ArmorMaterial material, float amount);

	float getPreventAmount(ArmorMaterial material);

}
