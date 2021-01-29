package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CookieCrowEntity extends FoodEntityBase {

	public CookieCrowEntity(World worldIn) {
		super(worldIn);
		this.setSize(0.3F, 0.1F);
	}

	public CookieCrowEntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
		this.setSize(0.3F, 0.1F);
	}

	public CookieCrowEntity(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
		this.setSize(0.3F, 0.1F);
	}

	@Override
	protected ItemStack[] drops() {
		return new ItemStack[] { new ItemStack(FoodInit.cookie, 1, 0), new ItemStack(FoodInit.cookie, 1, 1) };
	}

	@Override
	public void setRAW(boolean b) {
		if (getRaw() && b == false && world.rand.nextInt(2) == 0) {
			FoodEntityBase ret = new CookieUnagiEntity(world, this.posX, this.posY, this.posZ);
			ret.rotationYaw = this.rotationYaw;
			world.spawnEntity(ret);
			this.setDead();
		} else {
			super.setRAW(b);
		}
	}
}
