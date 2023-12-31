package defeatedcrow.hac.magic.material.item.arrow;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.magic.material.entity.ArrowTracking;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TrackingArrowItem extends BaseArrowItem {

	public TrackingArrowItem() {
		super(MagicColor.GREEN, "tracking", null);
	}

	@Override
	public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity living) {
		ArrowTracking arrow = new ArrowTracking(level, living);
		arrow.setBaseDamage(4.0F);
		return arrow;
	}

}
