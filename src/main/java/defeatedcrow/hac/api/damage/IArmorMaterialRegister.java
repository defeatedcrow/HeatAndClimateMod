package defeatedcrow.hac.api.damage;

import net.minecraft.world.item.ArmorMaterial;

/**
 * 気候ダメージへの耐性をArmorMaterialごとに登録するためのもの。
 */
public interface IArmorMaterialRegister {

	void registerMaterial(ArmorMaterial material, float heat, float cold);

	float getHeatPreventAmount(ArmorMaterial material);

	float getColdPreventAmount(ArmorMaterial material);

}
