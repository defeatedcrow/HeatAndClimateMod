package defeatedcrow.hac.magic.material.item.arrow;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.magic.material.entity.ArrowBlack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlackArrowItem extends BaseArrowItem {

	public BlackArrowItem() {
		super(MagicColor.BLACK, "black", null);
	}

	@Override
	public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity living) {
		ArrowBlack arrow = new ArrowBlack(level, living);
		arrow.setBaseDamage(4.0F);
		return arrow;
	}

}
