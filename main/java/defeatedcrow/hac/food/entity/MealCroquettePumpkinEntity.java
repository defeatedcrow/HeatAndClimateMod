package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MealCroquettePumpkinEntity extends FoodEntityBase {

	public MealCroquettePumpkinEntity(World worldIn) {
		super(worldIn);
	}

	public MealCroquettePumpkinEntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public MealCroquettePumpkinEntity(World worldIn, double posX, double posY, double posZ,
			@Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	protected ItemStack[] drops() {
		return new ItemStack[] {
				new ItemStack(FoodInit.snack, 1, 3),
				new ItemStack(FoodInit.snack, 1, 3)
		};
	}
}
