package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PastaRoeEntity extends FoodEntityBase {

	public PastaRoeEntity(World worldIn) {
		super(worldIn);
		this.setSize(0.75F, 0.25F);
	}

	public PastaRoeEntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
		this.setSize(0.75F, 0.25F);
	}

	public PastaRoeEntity(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
		this.setSize(0.75F, 0.25F);
	}

	@Override
	protected ItemStack[] drops() {
		return new ItemStack[] { new ItemStack(FoodInit.pasta, 1, 7), new ItemStack(FoodInit.pasta, 1, 7) };
	}
}