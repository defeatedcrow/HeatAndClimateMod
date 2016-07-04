package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;

public class SquareBreadEntity extends FoodEntityBase {

	public SquareBreadEntity(World worldIn) {
		super(worldIn);
	}

	public SquareBreadEntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public SquareBreadEntity(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	protected ItemStack[] drops() {
		return new ItemStack[] {
				new ItemStack(FoodInit.bread, 1, 2),
				new ItemStack(FoodInit.bread, 1, 3) };
	}
}
