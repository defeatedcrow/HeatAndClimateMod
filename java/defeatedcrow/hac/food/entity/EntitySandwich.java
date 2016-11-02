package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySandwich extends FoodEntityBase {

	public EntitySandwich(World worldIn) {
		super(worldIn);
	}

	public EntitySandwich(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public EntitySandwich(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	protected ItemStack[] drops() {
		return new ItemStack[] {
				new ItemStack(FoodInit.sandwich, 1, 0),
				new ItemStack(FoodInit.sandwich, 1, 0) };
	}
}
