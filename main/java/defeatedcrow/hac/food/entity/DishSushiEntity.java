package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DishSushiEntity extends FoodEntityBase {

	public DishSushiEntity(World worldIn) {
		super(worldIn);
	}

	public DishSushiEntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public DishSushiEntity(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	protected ItemStack[] drops() {
		return new ItemStack[] { new ItemStack(FoodInit.dishSq, 1, 3), new ItemStack(FoodInit.dishSq, 1, 3) };
	}
}
