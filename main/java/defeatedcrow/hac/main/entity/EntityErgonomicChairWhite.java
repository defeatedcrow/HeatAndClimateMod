package defeatedcrow.hac.main.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.main.MainInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityErgonomicChairWhite extends EntityErgonomicChair {

	public EntityErgonomicChairWhite(World worldIn) {
		super(worldIn);
	}

	@Override
	public int getColor() {
		return 1;
	}

	public EntityErgonomicChairWhite(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public EntityErgonomicChairWhite(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	protected ItemStack drops() {
		return new ItemStack(MainInit.ergonomicChair, 1, 1);
	}

}
