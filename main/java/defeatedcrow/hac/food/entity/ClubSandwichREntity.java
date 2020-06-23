package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ClubSandwichREntity extends FoodEntityBase {

	public ClubSandwichREntity(World worldIn) {
		super(worldIn);
		this.setSize(0.5F, 0.5F);
	}

	public ClubSandwichREntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
		this.setSize(0.5F, 0.5F);
	}

	public ClubSandwichREntity(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
		this.setSize(0.5F, 0.5F);
	}

	@Override
	protected ItemStack[] drops() {
		return new ItemStack[] {
			new ItemStack(FoodInit.clubsandwich, 1, 1),
			new ItemStack(FoodInit.clubsandwich, 1, 1) };
	}
}
