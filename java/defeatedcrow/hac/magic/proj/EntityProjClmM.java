package defeatedcrow.hac.magic.proj;

import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.client.FMLClientHandler;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.client.particle.ParticleFallingStar;
import defeatedcrow.hac.main.client.particle.ParticleShock;

public class EntityProjClmM extends EntityMagicProjBase {

	public EntityProjClmM(World worldIn) {
		super(worldIn);
	}

	public EntityProjClmM(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjClmM(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerMagic, 1, 13);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// もうちょっと考える

	@Override
	protected boolean onGroundHit() {
		BlockPos min = new BlockPos(posX - 4, posY, posZ - 4);
		BlockPos max = new BlockPos(posX + 4, posY, posZ + 4);
		Iterable<BlockPos> itr = min.getAllInBox(min, max);
		Chunk chunk = worldObj.getChunkFromBlockCoords(this.getPosition());

		Biome biome = worldObj.getBiomeForCoordsBody(getPosition());

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

		double x1 = (double) posX + rand.nextDouble() - 0.5D;
		double y1 = (double) posY + rand.nextDouble() - 0.5D;
		double z1 = (double) posZ + rand.nextDouble() - 0.5D;
		Particle shock = new ParticleShock.Factory().getEntityFX(0, worldObj, x1, y1, z1, 0D, 0D, 0D, new int[0]);
		shock.setRBGColorF(0.95F, 0.95F, 0.95F);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(shock);

		int i = 0;
		while (i < 3) {
			double x = (double) posX + rand.nextDouble();
			double y = (double) posY + 0.25D + rand.nextDouble();
			double z = (double) posZ + rand.nextDouble();

			double fx = 0.5D * rand.nextDouble() - 0.25D;
			double fy = 0.5D + rand.nextDouble() * 0.25D;
			double fz = 0.5D * rand.nextDouble() - 0.25D;

			Particle star = new ParticleFallingStar.Factory().getEntityFX(0, worldObj, x, y, z, fx, fy, fz, new int[0]);
			star.setRBGColorF(0.95F, 0.95F, 0.95F);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(star);

			i++;
		}
	}

}
