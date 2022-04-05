package defeatedcrow.hac.main.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
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
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityCrowBalloon extends EntityLivingBase {

	public float swing = 0F;
	public float swingPrev = 0F;

	private final NonNullList<ItemStack> handItems;
	private final NonNullList<ItemStack> armorItems;

	public EntityCrowBalloon(World worldIn) {
		super(worldIn);
		this.setSize(0.5F, 2.0F);
		this.handItems = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
		this.armorItems = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
		this.setHealth(40.0F);
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
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
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

	@Override
	public EnumHandSide getPrimaryHand() {
		return EnumHandSide.RIGHT;
	}

	private void dropContents() {
		this.playBrokenSound();

		for (int i = 0; i < this.handItems.size(); ++i) {
			ItemStack itemstack = this.handItems.get(i);

			if (!itemstack.isEmpty()) {
				Block.spawnAsEntity(this.world, (new BlockPos(this)).up(), itemstack);
				this.handItems.set(i, ItemStack.EMPTY);
			}
		}

		for (int j = 0; j < this.armorItems.size(); ++j) {
			ItemStack itemstack1 = this.armorItems.get(j);

			if (!itemstack1.isEmpty()) {
				Block.spawnAsEntity(this.world, (new BlockPos(this)).up(), itemstack1);
				this.armorItems.set(j, ItemStack.EMPTY);
			}
		}
	}

	private void playBrokenSound() {
		this.world
				.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_FIREWORK_BLAST, this
						.getSoundCategory(), 1.0F, 1.0F);
	}

	private void playDamageSound() {
		this.world
				.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_ARMORSTAND_HIT, this
						.getSoundCategory(), 1.0F, 1.0F);
	}

	private void playParticles() {
		if (this.world instanceof WorldServer) {
			((WorldServer) this.world)
					.spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY + this.height / 1.5D, this.posZ, 10, (double) (this.width / 4.0F), (double) (this.height / 4.0F), (double) (this.width / 4.0F), 0.05D, Block
							.getStateId(Blocks.BEDROCK.getDefaultState()));
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
		List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());

		for (int i = 0; i < list.size(); ++i) {
			Entity entity = list.get(i);

			if (this.getDistance(entity) <= 0.2D) {
				entity.applyEntityCollision(this);
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!this.world.isRemote && !this.isDead) {
			if (DamageSource.OUT_OF_WORLD.equals(source)) {
				this.setDead();
				return false;
			} else if (DamageSource.IN_WALL.equals(source)) {
				this.playBrokenSound();
				this.setDead();
				return false;
			} else if (!this.isEntityInvulnerable(source)) {
				if (source instanceof EntityDamageSource) {
					if (source.getTrueSource() instanceof EntityPlayer) {
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
			} else {
				return false;
			}
		} else {
			return false;
		}
		return false;
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();

		if (world.isRemote) {
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

		AxisAlignedBB aabb = this.getEntityBoundingBox().grow(8.0D, 1.0D, 8.0D);
		List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, aabb);

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
