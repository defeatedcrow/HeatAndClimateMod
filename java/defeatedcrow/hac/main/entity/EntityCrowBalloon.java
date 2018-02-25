package defeatedcrow.hac.main.entity;

import java.util.Arrays;
import java.util.List;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityCrowBalloon extends EntityLivingBase {

	public float swing = 0F;
	public float swingPrev = 0F;

	private final ItemStack[] handItems;
	private final ItemStack[] armorItems;

	public EntityCrowBalloon(World worldIn) {
		super(worldIn);
		this.setSize(0.5F, 2.0F);
		this.handItems = new ItemStack[2];
		this.armorItems = new ItemStack[4];
		this.setHealth(30.0F);
	}

	public EntityCrowBalloon(World worldIn, double posX, double posY, double posZ) {
		this(worldIn);
		this.setPosition(posX, posY, posZ);
	}

	public EntityCrowBalloon(World worldIn, double posX, double posY, double posZ, float yaw) {
		this(worldIn);
		this.setPosition(posX, posY, posZ);
		this.rotationYaw = yaw;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		NBTTagList nbttaglist = new NBTTagList();

		for (ItemStack itemstack : this.armorItems) {
			NBTTagCompound nbttagcompound = new NBTTagCompound();

			if (!DCUtil.isEmpty(itemstack)) {
				itemstack.writeToNBT(nbttagcompound);
			}

			nbttaglist.appendTag(nbttagcompound);
		}

		compound.setTag("ArmorItems", nbttaglist);
		NBTTagList nbttaglist1 = new NBTTagList();

		for (ItemStack itemstack1 : this.handItems) {
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();

			if (!DCUtil.isEmpty(itemstack1)) {
				itemstack1.writeToNBT(nbttagcompound1);
			}

			nbttaglist1.appendTag(nbttagcompound1);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		if (compound.hasKey("ArmorItems", 9)) {
			NBTTagList nbttaglist = compound.getTagList("ArmorItems", 10);

			for (int i = 0; i < this.armorItems.length; ++i) {
				this.armorItems[i] = ItemStack.loadItemStackFromNBT(nbttaglist.getCompoundTagAt(i));
			}
		}

		if (compound.hasKey("HandItems", 9)) {
			NBTTagList nbttaglist1 = compound.getTagList("HandItems", 10);

			for (int j = 0; j < this.handItems.length; ++j) {
				this.handItems[j] = ItemStack.loadItemStackFromNBT(nbttaglist1.getCompoundTagAt(j));
			}
		}
	}

	@Override
	public Iterable<ItemStack> getHeldEquipment() {
		return Arrays.<ItemStack>asList(this.handItems);
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		return Arrays.<ItemStack>asList(this.armorItems);
	}

	@Override
	public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
		switch (slotIn.getSlotType()) {
		case HAND:
			return this.handItems[slotIn.getIndex()];
		case ARMOR:
			return this.armorItems[slotIn.getIndex()];
		default:
			return null;
		}
	}

	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
		switch (slotIn.getSlotType()) {
		case HAND:
			this.playEquipSound(stack);
			this.handItems[slotIn.getIndex()] = stack;
			break;
		case ARMOR:
			this.playEquipSound(stack);
			this.armorItems[slotIn.getIndex()] = stack;
		}
	}

	@Override
	public EnumHandSide getPrimaryHand() {
		return EnumHandSide.RIGHT;
	}

	private void dropContents() {
		this.playBrokenSound();

		for (int i = 0; i < this.handItems.length; ++i) {
			ItemStack itemstack = this.handItems[i];

			if (!DCUtil.isEmpty(itemstack)) {
				Block.spawnAsEntity(this.worldObj, (new BlockPos(this)).up(), itemstack);
				this.handItems[i] = null;
			}
		}

		for (int j = 0; j < this.armorItems.length; ++j) {
			ItemStack itemstack1 = this.armorItems[j];

			if (!DCUtil.isEmpty(itemstack1)) {
				Block.spawnAsEntity(this.worldObj, (new BlockPos(this)).up(), itemstack1);
				this.armorItems[j] = null;
			}
		}
	}

	private void playBrokenSound() {
		this.worldObj.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_FIREWORK_BLAST,
				this.getSoundCategory(), 1.0F, 1.0F);
	}

	private void playDamageSound() {
		this.worldObj.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_ARMORSTAND_HIT,
				this.getSoundCategory(), 1.0F, 1.0F);
	}

	private void playParticles() {
		if (this.worldObj instanceof WorldServer) {
			((WorldServer) this.worldObj).spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX,
					this.posY + this.height / 1.5D, this.posZ, 10, (double) (this.width / 4.0F),
					(double) (this.height / 4.0F), (double) (this.width / 4.0F), 0.05D,
					Block.getStateId(Blocks.BEDROCK.getDefaultState()));
		}
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {}

	@Override
	protected void collideWithNearbyEntities() {
		List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());

		for (int i = 0; i < list.size(); ++i) {
			Entity entity = list.get(i);

			if (this.getDistanceSqToEntity(entity) <= 0.2D) {
				entity.applyEntityCollision(this);
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!this.worldObj.isRemote && !this.isDead) {
			if (DamageSource.outOfWorld.equals(source)) {
				this.setDead();
				return false;
			} else if (DamageSource.inWall.equals(source)) {
				this.playBrokenSound();
				this.setDead();
				return false;
			} else if (!this.isEntityInvulnerable(source)) {
				if (source instanceof EntityDamageSource) {
					if (source.getEntity() instanceof EntityPlayer) {
						this.dropContents();
						this.setDead();
						return false;
					} else {
						float count = this.getHealth();
						if (count <= 1.0F) {
							this.dropContents();
							this.setDead();
							return false;
						} else {
							this.setHealth(count - 1.0F);
							this.playDamageSound();
							this.playParticles();
							return false;
						}
					}
				}
			} else
				return false;
		} else
			return false;
		return false;
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();

		if (worldObj.isRemote) {
			swingPrev = swing;
			swing += 4F;
			if (swing > 360F) {
				swing -= 360F;
				swingPrev -= 360F;
			}
		}

		if (this.isInsideOfMaterial(Material.WATER)) {
			this.motionY = 0.05D;
		}

		AxisAlignedBB aabb = this.getEntityBoundingBox().expand(8.0D, 1.0D, 8.0D);
		List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, aabb);

		for (int i = 0; i < list.size(); ++i) {
			Entity entity = list.get(i);

			if (entity instanceof EntityLiving && entity instanceof IMob) {
				EntityLiving liv = (EntityLiving) entity;
				EntityLivingBase target = liv.getAttackTarget();
				liv.setAttackTarget(this);
			}
		}
	}

}
