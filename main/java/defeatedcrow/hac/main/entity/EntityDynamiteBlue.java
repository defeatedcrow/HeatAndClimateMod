package defeatedcrow.hac.main.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityDynamiteBlue extends EntityDynamite {

	public EntityDynamiteBlue(World worldIn) {
		super(worldIn);
	}

	public EntityDynamiteBlue(World worldIn, double x, double y, double z, EntityLivingBase igniter) {
		super(worldIn, x, y, z, igniter);
	}

	@Override
	public boolean isSilk() {
		return true;
	}

	@Override
	public int getRange() {
		return 4;
	}

}
