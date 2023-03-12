package defeatedcrow.hac.magic.material.item;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.magic.material.entity.ArrowBlue;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlueArrowItem extends BaseArrowItem {

	public BlueArrowItem() {
		super(MagicColor.BLUE, "blue", null);
	}

	@Override
	public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity living) {
		ArrowBlue arrow = new ArrowBlue(level, living);
		return arrow;
	}

}
