package defeatedcrow.hac.main.entity;

import defeatedcrow.hac.main.util.CustomExplosion;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityDynamite extends Entity {

	private EntityLivingBase placer;
	private int count = 10;

	public EntityDynamite(World worldIn) {
		super(worldIn);
		this.preventEntitySpawning = true;
		this.setSize(1.0F, 1.0F);
	}

	public EntityDynamite(World worldIn, double x, double y, double z, EntityLivingBase igniter) {
		this(worldIn);
		this.setPosition(x, y, z);
		placer = igniter;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	public EntityLivingBase getPlacer() {
		return this.placer;
	}

	@Override
	public void onUpdate() {
		if (count < 10) {
			if (count > 0) {
				count--;
			} else {
				this.setDead();
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source))
			return false;
		else {
			if (!this.isDead) {
				if (!this.worldObj.isRemote && count == 10) {
					count = 3;

					if (!this.worldObj.isRemote) {
						CustomExplosion explosion = new CustomExplosion(worldObj, this, placer, posX, posY, posZ, 4.0F,
								CustomExplosion.Type.Silk, true);
						explosion.doExplosion();
						this.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F,
								1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
						doBlockDestroy(3);
					}
				} else {
					worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX, posY, posZ, 1.0D, 0.0D, 0.0D,
							new int[0]);
				}
			}

			return true;
		}
	}

	protected void doBlockDestroy(int range) {
		BlockPos pos = this.getPosition();
		BlockPos min = pos.add(-range, -range, -range);
		BlockPos max = pos.add(range, range, range);
		Iterable<BlockPos> list = BlockPos.getAllInBox(min, max);
		for (BlockPos p : list) {
			IBlockState state = worldObj.getBlockState(p);
			if (!worldObj.isAirBlock(p)) {
				if (isSilk() && state.getBlock().canSilkHarvest(worldObj, p, state, null)) {
					ItemStack item = new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state));
					EntityItem drop = new EntityItem(worldObj, p.getX() + 0.5D, p.getY() + 0.5D, p.getZ() + 0.5D, item);
					worldObj.spawnEntityInWorld(drop);
				} else {
					state.getBlock().dropBlockAsItem(worldObj, p, state, 0);
				}
				worldObj.setBlockToAir(p);
			}
		}
	}

	public boolean isSilk() {
		return false;
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}

}
