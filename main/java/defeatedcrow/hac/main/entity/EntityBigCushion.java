package defeatedcrow.hac.main.entity;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityBigCushion extends DCEntityBase {

	public EntityBigCushion(World worldIn) {
		super(worldIn);
		this.setSize(1.0F, 0.4F);
	}

	@Override
	public double getMountedYOffset() {
		return 0.2D;
	}

	public EntityBigCushion(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
		this.setSize(1.0F, 0.4F);
	}

	public EntityBigCushion(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
		this.setSize(1.0F, 0.4F);
	}

	@Override
	protected ItemStack drops() {
		return new ItemStack(MainInit.cushionGray, 1, 0);
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
			player.startRiding(this);
			return true;
		}
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) {
			return false;
		} else if (source.isFireDamage() || source.isMagicDamage()) {
			return false;
		} else if (source instanceof EntityDamageSource) {
			this.markVelocityChanged();
			this.dropAndDeath(null);
		}
		return false;
	}

	@Override
	protected void collideWithNearbyEntities() {
		List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox()
				.grow(0.25D), EntitySelectors.IS_STANDALONE);

		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); ++i) {
				Entity entity = list.get(i);
				this.collideWithEntity(entity);
			}
		}
	}

	@Override
	protected void collideWithEntity(Entity entity) {
		if (!entity.world.isRemote)
			if (!entity.isPassenger(this) && entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)) {
				if (this.getPassengers().isEmpty()) {
					entity.startRiding(this);
				} else {
					if (this.getPassengers().get(0) == null || this.getPassengers().get(0).isDead) {
						this.removePassengers();
					}
					this.applyEntityCollision(entity);
					this.markVelocityChanged();
				}
			}
	}

}
