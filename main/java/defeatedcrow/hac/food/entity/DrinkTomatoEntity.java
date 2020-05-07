package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DrinkTomatoEntity extends FoodEntityBase {

	public DrinkTomatoEntity(World worldIn) {
		super(worldIn);
		this.setSize(0.2F, 0.375F);
	}

	public DrinkTomatoEntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
		this.setSize(0.2F, 0.375F);
	}

	public DrinkTomatoEntity(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
		this.setSize(0.2F, 0.375F);
	}

	@Override
	protected ItemStack[] drops() {
		return new ItemStack[] { new ItemStack(FoodInit.drink, 1, 2), new ItemStack(FoodInit.drink, 1, 2) };
	}
}
