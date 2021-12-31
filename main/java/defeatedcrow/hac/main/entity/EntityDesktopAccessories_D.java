package defeatedcrow.hac.main.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityDesktopAccessories_D extends DCEntityBase {

	public EntityDesktopAccessories_D(World worldIn) {
		super(worldIn);
		this.setSize(0.2F, 0.5F);
	}

	@Override
	public double getMountedYOffset() {
		return 0.2D;
	}

	public EntityDesktopAccessories_D(World worldIn, double posX, double posY, double posZ) {
		this(worldIn);
		this.setPosition(posX, posY, posZ);
	}

	public EntityDesktopAccessories_D(World worldIn, double posX, double posY, double posZ,
			@Nullable EntityPlayer player) {
		this(worldIn, posX, posY, posZ);
		if (player != null)
			this.rotationYaw = player.rotationYaw;
	}

	@Override
	protected ItemStack drops() {
		return new ItemStack(MainInit.desktopAccessories, 1, 3);
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		if (world.isRemote) {
			return true;
		}
		if (player == null) {
			return true;
		}
		if (MainUtil.isHeldItem(new ItemStack(MainInit.colorChanger), player)) {
			Entity change = new EntityDesktopAccessories_E(player.getEntityWorld());
			change.copyLocationAndAnglesFrom(this);
			if (world.spawnEntity(change)) {
				this.setDead();
			}
			return true;
		} else if (DCUtil.isEmpty(player.getActiveItemStack())) {
			this.dropAndDeath(player.getPosition());
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

}
