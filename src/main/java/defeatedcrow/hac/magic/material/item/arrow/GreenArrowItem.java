package defeatedcrow.hac.magic.material.item.arrow;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.magic.material.entity.ArrowGreen;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GreenArrowItem extends BaseArrowItem {

	public GreenArrowItem() {
		super(MagicColor.GREEN, "green", null);
	}

	@Override
	public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity living) {
		ArrowGreen arrow = new ArrowGreen(level, living);
		arrow.setBaseDamage(4.0F);
		return arrow;
	}

}
