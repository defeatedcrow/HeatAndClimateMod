package defeatedcrow.hac.magic.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCEntityBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityMagicCushion extends DCEntityBase {

	public int maxLivingTime = 60;

	public EntityMagicCushion(World worldIn) {
		super(worldIn);
		this.setSize(0.5F, 0.5F);
		this.isImmuneToFire = true;
	}

	@Override
	public double getMountedYOffset() {
		return 0.125D;
	}

	public EntityMagicCushion(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public EntityMagicCushion(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	public void setMaxLivingTime(int i) {
		maxLivingTime = i;
	}

	@Override
	protected ItemStack drops() {
		return ItemStack.EMPTY;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.getTotalAge() >= maxLivingTime) {
			this.setDead();
		} else if (this.getTotalAge() > 10) {
			if (this.getPassengers().isEmpty() || this.getPassengers().get(0) == null || this.getPassengers()
					.get(0).isDead) {
				this.removePassengers();
				this.setDead();
			}
		}
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		if (world.isRemote) {
			return true;
		}
		if (player == null) {
			return true;
		}
		if (!player.isSneaking()) {
			if (!this.getPassengers().isEmpty()) {
				this.getPassengers().get(0).dismountRidingEntity();
			}
			this.setDead();
			return true;
		}
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return false;
	}

	@Override
	protected void collideWithNearbyEntities() {

	}

	@Override
	protected void collideWithEntity(Entity entity) {}

	@Override
	public boolean isCollectable(@Nullable ItemStack item) {
		return false;
	}

	@Override
	protected boolean isFallable() {
		return false;
	}

}
