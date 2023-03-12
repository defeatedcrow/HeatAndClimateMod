package defeatedcrow.hac.api.magic;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface ICardMagic extends IJewel {

	// active check
	/**
	 * カードが使用可能かどうか。<br>
	 */
	boolean isActive(LivingEntity owner, ItemStack card);

	/**
	 * カード使用後のコスト消費処理。<br>
	 */
	void onConsumeResource(LivingEntity owner, ItemStack card);

	int getMagicCostEXP(ItemStack card);

}
