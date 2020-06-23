package defeatedcrow.hac.main.entity;

import defeatedcrow.hac.main.util.CustomExplosion;
import net.minecraft.block.material.Material;
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
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTableList;

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
				if (!this.world.isRemote && count == 10) {
					count = 3;
					CustomExplosion explosion = new CustomExplosion(world, this, placer, posX, posY, posZ, getPower(),
							CustomExplosion.Type.Silk, true);
					explosion.doExplosion();
					this.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 1.2F / (this.rand
							.nextFloat() * 0.2F + 0.9F));
					doBlockDestroy(getRange(), explosion);
				} else {
					if (this.getPower() > 2.0F)
						world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX, posY, posZ, 1.0D, 0.0D, 0.0D, new int[0]);
					else
						world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, posX, posY, posZ, 1.0D, 0.0D, 0.0D, new int[0]);
				}
			}

			return true;
		}
	}

	protected void doBlockDestroy(int range, Explosion exp) {
		BlockPos pos = this.getPosition();
		BlockPos min = pos.add(-range, -range, -range);
		BlockPos max = pos.add(range, range, range);
		Iterable<BlockPos> list = BlockPos.getAllInBox(min, max);
		for (BlockPos p : list) {
			IBlockState state = world.getBlockState(p);
			if (p.getY() > 1 && !world.isAirBlock(p)) {
				if (state.getMaterial() == Material.WATER) {
					if (world.rand.nextInt(8) == 0) {
						LootContext.Builder lootcontext$builder = new LootContext.Builder((WorldServer) this.world);
						lootcontext$builder.withLuck(0.0F);

						for (ItemStack itemstack : this.world.getLootTableManager()
								.getLootTableFromLocation(LootTableList.GAMEPLAY_FISHING)
								.generateLootForPools(this.rand, lootcontext$builder.build())) {
							double d0 = p.getX() + 0.5D;
							double d1 = p.getY() + 0.5D;
							double d2 = p.getZ() + 0.5D;
							EntityItem entityitem = new EntityItem(this.world, d0, d1, d2, itemstack);
							entityitem.motionY = 0.1D;
							world.spawnEntity(entityitem);
						}
					}

				} else if (state != null && state.getBlock()
						.getExplosionResistance(world, p, getPlacer(), exp) <= 60.0F * range && this
								.canExplosionDestroyBlock(exp, world, pos, world.getBlockState(pos), range)) {
					if (isSilk() && state.getBlock().canSilkHarvest(world, p, state, null)) {
						ItemStack item = new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state));
						EntityItem drop = new EntityItem(world, p.getX() + 0.5D, p.getY() + 0.5D, p.getZ() + 0.5D,
								item);
						world.spawnEntity(drop);
					} else {
						state.getBlock().dropBlockAsItem(world, p, state, 0);
					}
					world.setBlockToAir(p);
				}
			}
		}
	}

	public boolean isSilk() {
		return false;
	}

	public float getPower() {
		return 4.0F;
	}

	public int getRange() {
		return 4;
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}

}
