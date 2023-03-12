package defeatedcrow.hac.magic.material.entity;

import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ArrowGreen extends AbstractArrow {

	private boolean dealtDamage;

	public ArrowGreen(EntityType<? extends ArrowGreen> type, Level level) {
		super(type, level);
	}

	public ArrowGreen(Level level, LivingEntity shooter) {
		super(MagicInit.ARROW_GREEN_ENTITY.get(), shooter, level);
	}

	public ArrowGreen(Level level, double x, double y, double z) {
		super(MagicInit.ARROW_GREEN_ENTITY.get(), x, y, z, level);
	}

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(MagicInit.ARROW_GREEN.get());
	}

	@Override
	public byte getPierceLevel() {
		return 5;
	}

}
