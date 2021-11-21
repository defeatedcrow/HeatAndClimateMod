package defeatedcrow.hac.magic.proj;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.main.client.particle.ParticleBlinkRise;
import defeatedcrow.hac.main.entity.EntityDummyWeather;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageMagicParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMobBarrier extends EntityDummyWeather {

	private static final DataParameter<Float> RANGE = EntityDataManager
			.<Float>createKey(EntityMobBarrier.class, DataSerializers.FLOAT);

	protected int totalAge = 0;
	protected int maxAge = 2400 * 20;
	protected final Random rand = new Random();
	protected float range = 8.0F;

	/* コンストラクタ */

	public EntityMobBarrier(World worldIn) {
		super(worldIn);
		this.setSize(1.0F, 1.0F);
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(RANGE, Float.valueOf(8.0F));
	}

	public EntityMobBarrier(World worldIn, double posX, double posY, double posZ) {
		this(worldIn);
		this.setPosition(posX, posY, posZ);
	}

	public EntityMobBarrier(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		this(worldIn, posX, posY, posZ);
		if (player != null)
			this.rotationYaw = player.rotationYaw;
	}

	/* Tick処理 */
	/* update処理 */

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.totalAge > maxAge) {
			if (!this.world.isRemote) {
				this.setDead();
			}
		} else {
			this.totalAge++;
		}

		if (!world.isRemote) {
			doBarrierEffect();
		} else {
			addParticle();
		}
	}

	int count = 0;

	protected int cooltime() {
		return 0;
	}

	protected void doBarrierEffect() {
		if (count <= 0) {
			count = cooltime();
			// バリア処理
			List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this
					.getEntityBoundingBox().grow(range), EntitySelectors.IS_STANDALONE);

			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); ++i) {
					Entity entity = list.get(i);
					if (entity != null && entity.getDistance(this) <= range)
						this.collideWithEntity(entity);
				}
			}
		} else {
			count--;
		}
	}

	protected void collideWithEntity(Entity entity) {
		if (entity instanceof IMob) {
			DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(entity.posX, entity.posY + 1.0D, entity.posZ));
			entity.setDead();
		}
	}

	protected int[] color() {
		return new int[] {
				144,
				50,
				200
		};
	}

	@SideOnly(Side.CLIENT)
	protected void addParticle() {
		double time = (world.getWorldInfo().getWorldTime() % 60D);
		double r = time * 360D / 60D;
		double rad = r * Math.PI / 180D;

		double px = this.posX + (Math.cos(rad) * range);
		double py = this.posY;
		double pz = this.posZ + (Math.sin(rad) * range);
		Particle p = new ParticleBlinkRise.Factory()
				.createParticle(0, world, px, py, pz, 0.0D, 0.2D, 0.0D, color());
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(p);

		double px2 = this.posX - (Math.cos(rad) * range);
		double pz2 = this.posZ - (Math.sin(rad) * range);
		Particle p2 = new ParticleBlinkRise.Factory()
				.createParticle(0, world, px2, py + 0.5D, pz2, 0.0D, 0.2D, 0.0D, color());
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(p2);
	}

	/* 特定種のEntityには異なる結果を */
	@Override
	public void applyEntityCollision(Entity entity) {}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected boolean isFallable() {
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source != null && source.getTrueSource() != null) {
			if (source.getTrueSource() instanceof EntityPlayer) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(Entity entity) {
		return null;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox() {
		return null;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		this.totalAge = tag.getInteger("dcs.entityage");
		this.maxAge = tag.getInteger("dcs.maxage");
		this.range = tag.getInteger("dcs.range");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		tag.setInteger("dcs.entityage", totalAge);
		tag.setInteger("dcs.maxage", maxAge);
		tag.setFloat("dcs.range", range);
	}

	public int getTotalAge() {
		return this.totalAge;
	}

	public void setMaxAge(int i) {
		this.maxAge = i;
	}

	public void setRange(float age) {
		this.dataManager.set(RANGE, range);
	}

	public float getRange() {
		return this.dataManager.get(RANGE);
	}

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return this.getEntityBoundingBox().grow(8D, 1D, 8D);
	}

}
