package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;

public class BeefStickEntity extends FoodEntityBase {

	public BeefStickEntity(World worldIn) {
		super(worldIn);
	}

	public BeefStickEntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public BeefStickEntity(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	protected ItemStack[] drops() {
		return new ItemStack[] {
				new ItemStack(FoodInit.sticks, 1, 6),
				new ItemStack(FoodInit.sticks, 1, 7) };
	}
}
