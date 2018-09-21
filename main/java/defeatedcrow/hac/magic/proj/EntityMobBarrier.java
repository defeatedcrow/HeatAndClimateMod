package defeatedcrow.hac.magic.proj;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityMobBarrier extends Entity {

	private int totalAge = 0;
	private int maxAge = 2400;
	private final Random rand = new Random();

	/* コンストラクタ */

	public EntityMobBarrier(World worldIn) {
		super(worldIn);
		this.setSize(6.0F, 3.0F);
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
	}

	/* 特定種のEntityには異なる結果を */
	@Override
	public void applyEntityCollision(Entity entity) {
		if (entity != null) {
			if (entity instanceof IMob) {
				entity.setDead();
			}
		}
	}

	@Override
	public boolean canBePushed() {
		return true;
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
		if (entity != null && entity instanceof IMob) {
			return this.getEntityBoundingBox();
		}
		return null;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox() {
		return null;
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		this.totalAge = tag.getInteger("dcs.entityage");
		this.maxAge = tag.getInteger("dcs.maxage");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		tag.setInteger("dcs.entityage", totalAge);
		tag.setInteger("dcs.maxage", maxAge);
	}

	public int getTotalAge() {
		return this.totalAge;
	}

	public void setMaxAge(int i) {
		this.maxAge = i;
	}

}
