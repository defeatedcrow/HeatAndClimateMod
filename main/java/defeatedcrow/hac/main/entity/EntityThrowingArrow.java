package defeatedcrow.hac.main.entity;

import defeatedcrow.hac.main.MainInit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityThrowingArrow extends EntityBulletDC {

	public EntityThrowingArrow(World worldIn) {
		super(worldIn);
	}

	public EntityThrowingArrow(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityThrowingArrow(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		this.damage = 6.0D;
	}

	@Override
	public double getDamage() {
		return this.damage;
	}

	@Override
	public double getGravity() {
		return 0.1D;
	}

	@Override
	public boolean isDropable() {
		return true;
	}

	@Override
	public ItemStack getDrop() {
		return new ItemStack(MainInit.throwingArrow);
	}

	@Override
	public BulletType getBulletType() {
		return BulletType.ARROW;
	}
}
