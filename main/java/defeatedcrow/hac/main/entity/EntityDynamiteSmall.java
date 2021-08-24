package defeatedcrow.hac.main.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityDynamiteSmall extends EntityDynamite {

	public EntityDynamiteSmall(World worldIn) {
		super(worldIn);
	}

	public EntityDynamiteSmall(World worldIn, double x, double y, double z, EntityLivingBase igniter) {
		super(worldIn, x, y, z, igniter);
	}

	@Override
	public float getPower() {
		return 1.5F;
	}

	@Override
	public int getRange() {
		return 2;
	}

	public boolean canDestroyBlock() {
		return true;
	}

}
