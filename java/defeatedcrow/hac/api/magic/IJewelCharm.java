package defeatedcrow.hac.api.magic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public interface IJewelCharm {

	CharmType getType(int meta);

	float reduceHeat(int meta);

	float reduceCold(int meta);

	void formLivingEffect(EntityPlayer player, ItemStack charm);

	float reduceDamage(DamageSource source, ItemStack charm);

}
