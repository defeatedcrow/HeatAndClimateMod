package defeatedcrow.hac.api.damage;

import java.util.HashMap;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * 気候ダメージへの耐性を防具Itemごとに登録
 */
public interface IArmorItemRegister {

	HashMap<Item, Float> getHeatMap();

	HashMap<Item, Float> getColdMap();

	void registerMaterial(ItemStack item, float heat, float cold);

	float getHeatPreventAmount(ItemStack item);

	float getColdPreventAmount(ItemStack item);

}
