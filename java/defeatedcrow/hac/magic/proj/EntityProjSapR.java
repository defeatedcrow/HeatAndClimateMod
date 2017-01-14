package defeatedcrow.hac.magic.proj;

import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.client.particle.ParticleFallingStar;
import defeatedcrow.hac.main.client.particle.ParticleShock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityProjSapR extends EntityMagicProjBase {

	public EntityProjSapR(World worldIn) {
		super(worldIn);
	}

	public EntityProjSapR(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjSapR(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerMagic, 1, 5);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// 炎をばらまく

	@Override
	protected boolean onGroundHit() {
		BlockPos pos = this.getPosition();
		// 獄炎
		int r = 2 + rand.nextInt(3);
		int i = 0;
		int count = 0;
		while (count < r) {
			int x = -3 + rand.nextInt(7);
			int z = -3 + rand.nextInt(7);
			BlockPos p2 = pos.add(x, 0, z);
			IBlockState s2 = worldObj.getBlockState(p2);

			if (s2.getBlock().isReplaceable(worldObj, p2)) {
				for (int y = 1; y < 5; y++) {
					BlockPos p3 = p2.down(y);
					if (!worldObj.getBlockState(p3).getBlock().isReplaceable(worldObj, p3)) {
						worldObj.setBlockState(p3.up(), MagicInit.infernalFlame.getDefaultState());
						count++;
						break;
					}
				}
			} else {
				for (int y = 1; y < 5; y++) {
					BlockPos p3 = p2.up(y);
					if (worldObj.getBlockState(p3).getBlock().isReplaceable(worldObj, p3)) {
						worldObj.setBlockState(p3, MagicInit.infernalFlame.getDefaultState());
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

		this.playSound(SoundEvents.ENTITY_GENERIC_BURN, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
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
		if (!this.worldObj.isRemote && (this.isInWater() || this.isInLava())) {
			setStart(true);
		}
		super.onUpdate();
	}

	@Override
	protected void onGroundClient() {

		double x1 = posX + rand.nextDouble() - 0.5D;
		double y1 = posY + rand.nextDouble() - 0.5D;
		double z1 = posZ + rand.nextDouble() - 0.5D;
		Particle shock = new ParticleShock.Factory().createParticle(0, worldObj, x1, y1, z1, 0D, 0D, 0D, new int[0]);
		shock.setRBGColorF(1F, 0.75F, 0.75F);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(shock);

		int i = 0;
		while (i < 3) {
			double x = posX + rand.nextDouble();
			double y = posY + 0.25D + rand.nextDouble();
			double z = posZ + rand.nextDouble();

			double fx = 0.5D * rand.nextDouble() - 0.25D;
			double fy = 0.5D + rand.nextDouble() * 0.25D;
			double fz = 0.5D * rand.nextDouble() - 0.25D;

			Particle star = new ParticleFallingStar.Factory().createParticle(0, worldObj, x, y, z, fx, fy, fz,
					new int[0]);
			star.setRBGColorF(1F, 0.75F, 0.75F);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(star);

			i++;
		}
	}

}
