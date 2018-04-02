package defeatedcrow.hac.main.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.main.MainInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityBigCushionBrown extends EntityBigCushion {

	public EntityBigCushionBrown(World worldIn) {
		super(worldIn);
	}

	public EntityBigCushionBrown(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public EntityBigCushionBrown(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	protected ItemStack drops() {
		return new ItemStack(MainInit.cushionGray, 1, 1);
	}

}
