package defeatedcrow.hac.magic.material.item;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.magic.material.entity.ArrowWhite;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WhiteArrowItem extends BaseArrowItem {

	public WhiteArrowItem() {
		super(MagicColor.WHITE, "white", null);
	}

	@Override
	public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity living) {
		ArrowWhite arrow = new ArrowWhite(level, living);
		return arrow;
	}

}
