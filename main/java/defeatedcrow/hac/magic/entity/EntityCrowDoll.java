package defeatedcrow.hac.magic.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.item.ItemColorGauntlet2;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityCrowDoll extends EntityLivingBase {

	private final NonNullList<ItemStack> handItems;
	private final NonNullList<ItemStack> armorItems;
	protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager
			.<Optional<UUID>>createKey(EntityCrowDoll.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private int livingLimit = 600;

	public EntityCrowDoll(World worldIn) {
		super(worldIn);
		this.setSize(1.0F, 1.0F);
		this.handItems = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
		this.armorItems = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
	}

	public EntityCrowDoll(World worldIn, double posX, double posY, double posZ) {
		this(worldIn);
		this.setPosition(posX, posY, posZ);
	}

	public EntityCrowDoll(World worldIn, double posX, double posY, double posZ, float yaw) {
		this(worldIn);
		this.setPosition(posX, posY, posZ);
		this.rotationYaw = yaw;
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

	public void setlimit(int i) {
		this.livingLimit = i;
	}

	public boolean isOwnerID(EntityPlayer player) {
		if (getOwnerId() != null && player != null) {
			return getOwnerId().equals(player.getUniqueID());
		}
		return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		NBTTagList nbttaglist = new NBTTagList();

		for (ItemStack itemstack : this.armorItems) {
			NBTTagCompound nbttagcompound = new NBTTagCompound();

			if (!itemstack.isEmpty()) {
				itemstack.writeToNBT(nbttagcompound);
			}

			nbttaglist.appendTag(nbttagcompound);
		}

		compound.setTag("ArmorItems", nbttaglist);
		NBTTagList nbttaglist1 = new NBTTagList();

		for (ItemStack itemstack1 : this.handItems) {
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();

			if (!itemstack1.isEmpty()) {
				itemstack1.writeToNBT(nbttagcompound1);
			}

			nbttaglist1.appendTag(nbttagcompound1);
		}

		if (this.getOwnerId() == null) {
			compound.setString("OwnerUUID", "");
		} else {
			compound.setString("OwnerUUID", this.getOwnerId().toString());
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		if (compound.hasKey("ArmorItems", 9)) {
			NBTTagList nbttaglist = compound.getTagList("ArmorItems", 10);

			for (int i = 0; i < this.armorItems.size(); ++i) {
				this.armorItems.set(i, new ItemStack(nbttaglist.getCompoundTagAt(i)));
			}
		}

		if (compound.hasKey("HandItems", 9)) {
			NBTTagList nbttaglist1 = compound.getTagList("HandItems", 10);

			for (int j = 0; j < this.handItems.size(); ++j) {
				this.handItems.set(j, new ItemStack(nbttaglist1.getCompoundTagAt(j)));
			}
		}

		if (compound.hasKey("OwnerUUID", 8)) {
			String s = compound.getString("OwnerUUID");
			if (s != null) {
				this.setOwnerId(UUID.fromString(s));
			}
		}
	}

	@Override
	public Iterable<ItemStack> getHeldEquipment() {
		return this.handItems;
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		return this.armorItems;
	}

	@Override
	public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
		switch (slotIn.getSlotType()) {
		case HAND:
			return this.handItems.get(slotIn.getIndex());
		case ARMOR:
			return this.armorItems.get(slotIn.getIndex());
		default:
			return ItemStack.EMPTY;
		}
	}

	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
		switch (slotIn.getSlotType()) {
		case HAND:
			this.playEquipSound(stack);
			this.handItems.set(slotIn.getIndex(), stack);
			break;
		case ARMOR:
			this.playEquipSound(stack);
			this.armorItems.set(slotIn.getIndex(), stack);
		}
	}

	@Nullable
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.BLOCK_CLOTH_BREAK;
	}

	@Nullable
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_FIREWORK_BLAST;
	}

	@Override
	public EnumHandSide getPrimaryHand() {
		return EnumHandSide.RIGHT;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {}

	@Override
	protected void collideWithNearbyEntities() {
		List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());

		for (int i = 0; i < list.size(); ++i) {
			Entity entity = list.get(i);

			if (this.getDistance(entity) <= 0.2D) {
				entity.applyEntityCollision(this);
			}
		}
	}

	public void dropAllItems() {
		List<ItemStack> list = new ArrayList<>();
		list.addAll(armorItems);
		list.addAll(handItems);
		for (ItemStack item : list) {
			if (!DCUtil.isEmpty(item)) {
				EntityItem drop = new EntityItem(this.world, this.posX, this.posY + 0.125D, this.posZ, item);
				drop.setPickupDelay(40);
				float f = this.rand.nextFloat() * 0.8F;
				float f1 = this.rand.nextFloat() * ((float) Math.PI * 2F);
				drop.motionX = (double) (-MathHelper.sin(f1) * f);
				drop.motionZ = (double) (MathHelper.cos(f1) * f);
				drop.motionY = 0.2D;
				world.spawnEntity(drop);
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) {
			return false;
		} else {
			amount -= 1.0F;
			if (amount <= 0F)
				return false;

			Entity entity = source.getTrueSource();
			if (entity != null && !(entity instanceof EntityPlayer)) {
				amount /= 5.0F;
			}
		}
		return super.attackEntityFrom(source, amount);
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
				.getOffhandJewelColor(player) == MagicColor.GREEN_BLACK) {
			if (!this.getPassengers().isEmpty()) {
				this.getPassengers().get(0).dismountRidingEntity();
			}
			this.setDead();
			return true;
		}
		return false;
	}

	@Override
	public void onEntityUpdate() {

		// 30秒で消える
		if (this.ticksExisted > livingLimit) {
			if (!world.isRemote) {
				dropAllItems();
			}
			this.setDead();
		}

		if (this.isInsideOfMaterial(Material.WATER)) {
			this.motionY = 0.05D;
		}

		AxisAlignedBB aabb = this.getEntityBoundingBox().grow(8.0D, 1.0D, 8.0D);
		List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, aabb);

		for (int i = 0; i < list.size(); ++i) {
			Entity entity = list.get(i);

			if (entity instanceof EntityLiving && entity instanceof IMob) {
				EntityLiving liv = (EntityLiving) entity;
				Set<EntityAITaskEntry> set = liv.targetTasks.taskEntries;
				for (EntityAITaskEntry ai : set) {
					if (ai == null || ai.action == null)
						continue;
					if (ai.action instanceof EntityAITarget) {
						EntityAITarget tai = (EntityAITarget) ai.action;
						tai.resetTask();
					}
				}
				liv.setAttackTarget(this);
				liv.setRevengeTarget(this);
			}
		}
		if (this.collidedHorizontally) {
			this.motionY = 0.2D;
		}
		this.motionX = -MathHelper.sin(this.rotationYaw * 0.017453292F) * MathHelper
				.cos(this.rotationPitch * 0.017453292F);
		this.motionZ = MathHelper.cos(this.rotationYaw * 0.017453292F) * MathHelper
				.cos(this.rotationPitch * 0.017453292F);
		this.motionX *= 0.1D;
		this.motionZ *= 0.1D;
		super.onEntityUpdate();
	}

	public void setDead() {
		if (!world.isRemote) {
			UUID id = this.getOwnerId();
			if (id != null) {
				EntityPlayer owner = world.getPlayerEntityByUUID(id);
				if (owner != null && MainUtil.getOffhandJewelColor(owner) == MagicColor.GREEN_BLACK) {
					ItemStack held = owner.getHeldItemOffhand();
					ItemColorGauntlet2.addCount(held, 3, -1);
				}
			}
		}
		super.setDead();
	}

}
