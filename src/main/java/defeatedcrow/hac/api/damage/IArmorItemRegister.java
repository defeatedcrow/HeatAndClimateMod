package defeatedcrow.hac.api.damage;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * 気候ダメージへの耐性を防具Itemごとに登録
 */
public interface IArmorItemRegister {

	void registerArmorItem(Item item, float heat, float cold);

	float getHeatPreventAmount(ItemStack item);

	float getColdPreventAmount(ItemStack item);

}
