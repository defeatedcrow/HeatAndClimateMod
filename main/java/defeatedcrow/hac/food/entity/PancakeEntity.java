package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class PancakeEntity extends FoodEntityBase {

	private static final DataParameter<Boolean> MOLD = EntityDataManager.<Boolean> createKey(PancakeEntity.class,
			DataSerializers.BOOLEAN);

	public PancakeEntity(World worldIn) {
		super(worldIn);
	}

	public PancakeEntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public PancakeEntity(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	protected ItemStack[] drops() {
		return new ItemStack[] {
				new ItemStack(FoodInit.bread, 1, 2),
				new ItemStack(FoodInit.bread, 1, 3) };
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(MOLD, Boolean.valueOf(false));
		super.entityInit();
	}

	public void setMOLD(boolean b) {
		this.dataManager.set(MOLD, b);
	}

	public boolean getMOLD() {
		return this.dataManager.get(MOLD);
	}
}
