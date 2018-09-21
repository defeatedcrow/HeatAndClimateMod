package defeatedcrow.hac.magic.proj;

import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.client.particle.ParticleFallingStar;
import defeatedcrow.hac.main.client.particle.ParticleShock;
import defeatedcrow.hac.main.entity.EntityProjBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityProjSapB extends EntityProjBase {

	public EntityProjSapB(World worldIn) {
		super(worldIn);
	}

	public EntityProjSapB(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjSapB(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerMagic, 1, 3);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// 氷をばらまく

	@Override
	protected boolean onGroundHit() {
		BlockPos pos = this.getPosition();

		// 水の凍結
		BlockPos min = new BlockPos(pos.add(-5, -3, -5));
		BlockPos max = new BlockPos(pos.add(5, 3, 5));
		Iterable<BlockPos> itr = pos.getAllInBox(min, max);
		for (BlockPos p1 : itr) {
			double d1 = Math.sqrt(pos.distanceSq(p1.getX(), p1.getY(), p1.getZ()));
			if (p1.getY() > 1 && p1.getY() < world.getActualHeight() && d1 <= 5.0D) {
				Block b = world.getBlockState(p1).getBlock();
				if ((b == Blocks.WATER || b == Blocks.FLOWING_WATER)
						&& b.getMetaFromState(world.getBlockState(p1)) == 0) {
					world.setBlockState(p1, Blocks.ICE.getDefaultState());
				} else if ((b == Blocks.LAVA || b == Blocks.FLOWING_LAVA)
						&& b.getMetaFromState(world.getBlockState(p1)) == 0) {
					world.setBlockState(p1, Blocks.OBSIDIAN.getDefaultState());
				} else if (b.isSideSolid(world.getBlockState(p1), world, p1, EnumFacing.UP)
						&& world.isAirBlock(p1.up())) {
					if (world.rand.nextInt(3) != 0) {
						world.setBlockState(p1.up(), Blocks.SNOW_LAYER.getDefaultState());
					}
				}
			}
		}

		// 氷柱
		int r = 2 + rand.nextInt(3);
		int i = 0;
		int count = 0;
		while (count < r) {
			int x = -3 + rand.nextInt(7);
			int z = -3 + rand.nextInt(7);
			BlockPos p2 = pos.add(x, 0, z);
			IBlockState s2 = world.getBlockState(p2);

			if (s2.getBlock().isReplaceable(world, p2)) {
				for (int y = 1; y < 5; y++) {
					BlockPos p3 = p2.down(y);
					if (!world.getBlockState(p3).getBlock().isReplaceable(world, p3)) {
						world.setBlockState(p3.up(), MagicInit.clusterIce.getDefaultState());
						count++;
						break;
					}
				}
			} else {
				for (int y = 1; y < 5; y++) {
					BlockPos p3 = p2.up(y);
					if (world.getBlockState(p3).getBlock().isReplaceable(world, p3)) {
						world.setBlockState(p3, MagicInit.clusterIce.getDefaultState());
						count++;
						break;
					}
				}
			}
			i++;
			if (i > 10) {
				break;
			}
		}

		this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
		this.setDead();
		return true;
	}

	@Override
	protected boolean onEntityHit(Entity entity) {
		setStart(true);
		this.inGround = true;
		return true;
	}

	@Override
	public void onUpdate() {
		if (!this.world.isRemote && (this.isInWater() || this.isInLava())) {
			setStart(true);
		}
		super.onUpdate();
	}

	@Override
	protected void onGroundClient() {

		double x1 = posX + rand.nextDouble() - 0.5D;
		double y1 = posY + rand.nextDouble() - 0.5D;
		double z1 = posZ + rand.nextDouble() - 0.5D;
		Particle shock = new ParticleShock.Factory().createParticle(0, world, x1, y1, z1, 0D, 0D, 0D, new int[0]);
		shock.setRBGColorF(0.65F, 0.95F, 1F);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(shock);

		int i = 0;
		while (i < 3) {
			double x = posX + rand.nextDouble();
			double y = posY + 0.25D + rand.nextDouble();
			double z = posZ + rand.nextDouble();

			double fx = 0.5D * rand.nextDouble() - 0.25D;
			double fy = 0.5D + rand.nextDouble() * 0.25D;
			double fz = 0.5D * rand.nextDouble() - 0.25D;

			Particle star = new ParticleFallingStar.Factory().createParticle(0, world, x, y, z, fx, fy, fz, new int[0]);
			star.setRBGColorF(0.65F, 0.95F, 1F);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(star);

			i++;
		}
	}

}
