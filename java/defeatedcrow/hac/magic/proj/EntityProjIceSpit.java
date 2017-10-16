package defeatedcrow.hac.magic.proj;

import defeatedcrow.hac.api.damage.DamageSourceClimate;
import defeatedcrow.hac.main.entity.EntityProjBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityProjIceSpit extends EntityProjBase {

	public EntityProjIceSpit(World worldIn) {
		super(worldIn);
	}

	public EntityProjIceSpit(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjIceSpit(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return ItemStack.EMPTY;
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// Hit時ノーマルダメージ

	@Override
	protected float getHitDamage(Entity target, float speed) {
		float f = 8.0F + this.world.rand.nextFloat();
		if (target != null && target instanceof EntityLivingBase) {
			EntityLivingBase liv = (EntityLivingBase) target;
			if (liv.isImmuneToFire()) {
				f *= 2.0F;
			}
		}
		return f;
	}

	@Override
	protected DamageSource getHitSource(Entity target) {
		return DamageSourceClimate.climateColdDamage;
	}

	// 氷をばらまく

	@Override
	protected boolean onGroundHit() {
		BlockPos pos = this.getPosition();

		// 水の凍結
		BlockPos min = new BlockPos(pos.add(-3, -2, -3));
		BlockPos max = new BlockPos(pos.add(3, 2, 3));
		Iterable<BlockPos> itr = pos.getAllInBox(min, max);
		for (BlockPos p1 : itr) {
			double d1 = Math.sqrt(pos.distanceSq(p1.getX(), p1.getY(), p1.getZ()));
			if (p1.getY() > 1 && p1.getY() < world.getActualHeight() && d1 <= 5.0D) {
				Block b = world.getBlockState(p1).getBlock();
				if (b == Blocks.WATER || b == Blocks.FLOWING_WATER) {
					if (b.getMetaFromState(world.getBlockState(p1)) == 0) {
						world.setBlockState(p1, Blocks.ICE.getDefaultState());
					} else {
						world.setBlockToAir(p1);
					}
				} else if (b == Blocks.LAVA || b == Blocks.FLOWING_LAVA) {
					if (b.getMetaFromState(world.getBlockState(p1)) == 0) {
						world.setBlockState(p1, Blocks.OBSIDIAN.getDefaultState());
					} else {
						world.setBlockToAir(p1);
					}
				}
			}
		}

		this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
		this.setDead();
		return true;
	}

	// no gravity
	@Override
	public boolean hasNoGravity() {
		return true;
	}

}
