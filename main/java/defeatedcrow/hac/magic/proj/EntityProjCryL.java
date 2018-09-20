package defeatedcrow.hac.magic.proj;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.client.particle.ParticleFallingStar;
import defeatedcrow.hac.main.client.particle.ParticleShock;
import defeatedcrow.hac.main.entity.EntityProjBase;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityProjCryL extends EntityProjBase {

	public EntityProjCryL(World worldIn) {
		super(worldIn);
	}

	public EntityProjCryL(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjCryL(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	@Override
	public ItemStack getDropStack() {
		return new ItemStack(MagicInit.daggerMagic, 1, 8);
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// 混乱付与

	@Override
	protected boolean onGroundHit() {
		BlockPos pos = this.getPosition();
		int x1 = MathHelper.floor(this.posX - 8.0D);
		int x2 = MathHelper.floor(this.posX + 8.0D);
		int y1 = MathHelper.floor(this.posY - 4.0D);
		int y2 = MathHelper.floor(this.posY + 4.0D);
		int z1 = MathHelper.floor(this.posZ - 8.0D);
		int z2 = MathHelper.floor(this.posZ + 8.0D);
		List list = this.world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(x1, y1, z1, x2, y2, z2));
		Vec3d vec3 = new Vec3d(this.posX, this.posY, this.posZ);

		List<EntityLiving> livs = new ArrayList<EntityLiving>();

		for (int i = 0; i < list.size(); ++i) {
			Entity entity = (Entity) list.get(i);
			if (entity.isEntityEqual(shootingEntity)) {
				continue;
			}
			if (entity != null && entity instanceof EntityLiving) {
				EntityLiving liv = (EntityLiving) entity;
				livs.add(liv);
			}
		}

		if (livs.size() > 0) {
			for (int j = 0; j < livs.size(); ++j) {
				EntityLiving enemy = livs.get(j);
				int n = j + 1;
				if (n >= livs.size()) {
					n = 0;
				}
				EntityLiving attacker = livs.get(n);
				if (enemy != null && attacker != null) {
					attacker.setAttackTarget(enemy);
				}
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
		shock.setRBGColorF(0.65F, 0.25F, 0.75F);
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
			star.setRBGColorF(0.65F, 0.25F, 0.75F);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(star);

			i++;
		}
	}

}
