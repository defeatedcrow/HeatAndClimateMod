package defeatedcrow.hac.magic.material.item.arrow;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.magic.material.entity.ArrowRed;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RedArrowItem extends BaseArrowItem {

	public RedArrowItem() {
		super(MagicColor.RED, "red", null);
	}

	@Override
	public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity living) {
		ArrowRed arrow = new ArrowRed(level, living);
		return arrow;
	}

}
