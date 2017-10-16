package defeatedcrow.hac.main.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCution extends Entity {

	/* コンストラクタ */

	public EntityCution(World worldIn) {
		super(worldIn);
		this.setSize(0.5F, 0.1F);
	}

	public EntityCution(World worldIn, double posX, double posY, double posZ) {
		this(worldIn);
		this.setPosition(posX, posY, posZ);
	}

	public EntityCution(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		this(worldIn, posX, posY, posZ);
		if (player != null)
			this.rotationYaw = player.rotationYaw;
	}

	int count = 20;

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (world.isRemote) {
			if (count > 0) {
				count--;
			} else {
				if (!this.isBeingRidden()) {
					this.setDead();
				} else {
					count = 20;
				}
			}
		}
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}

}
