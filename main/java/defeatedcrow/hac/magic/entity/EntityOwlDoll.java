package defeatedcrow.hac.magic.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.magic.item.ItemColorGauntlet2;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityOwlDoll extends DCEntityBase {

	protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager
			.<Optional<UUID>>createKey(EntityOwlDoll.class, DataSerializers.OPTIONAL_UNIQUE_ID);

	public EntityOwlDoll(World worldIn) {
		super(worldIn);
		this.setSize(0.5F, 0.5F);
		this.isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
	}

	@Nullable
	public UUID getOwnerId() {
		return (UUID) ((Optional) this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
	}

	public void setOwnerId(@Nullable UUID id) {
		this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(id));
	}

	public boolean isOwnerID(EntityPlayer player) {
		if (getOwnerId() != null && player != null) {
			return getOwnerId().equals(player.getUniqueID());
		}
		return false;
	}

	@Override
	public double getMountedYOffset() {
		return 0D;
	}

	public EntityOwlDoll(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public EntityOwlDoll(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	protected ItemStack drops() {
		return ItemStack.EMPTY;
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
		return true;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		if (getOwnerId() != null) {
			tag.setUniqueId("dcs.owner", getOwnerId());
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		UUID id = tag.getUniqueId("dcs.owner");
		if (id != null) {
			setOwnerId(id);
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
		if (player.isSneaking() && isOwnerID(player) && MainUtil
				.getOffhandJewelColor(player) == MagicColor.BLACK_WHITE) {
			if (!this.getPassengers().isEmpty()) {
				this.getPassengers().get(0).dismountRidingEntity();
			}
			this.setDead();
			return true;
		}
		return false;
	}

	public void setDead() {
		if (!world.isRemote) {
			UUID id = this.getOwnerId();
			if (id != null) {
				EntityPlayer owner = world.getPlayerEntityByUUID(id);
				if (owner != null && MainUtil.getOffhandJewelColor(owner) == MagicColor.BLACK_WHITE) {
					ItemStack held = owner.getHeldItemOffhand();
					ItemColorGauntlet2.addCount(held, 3, -1);
				}
			}
		}
		super.setDead();
	}

}
