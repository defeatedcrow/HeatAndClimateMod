package defeatedcrow.hac.machine.block;

import java.util.List;

import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileCatapult extends TileTorqueBase implements ITorqueReceiver {

	protected int rad = 0;

	protected float pow = 1.0F;
	protected float lastP = 1.0F;

	public int getRad() {
		return rad;
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side != EnumFacing.DOWN && side != EnumFacing.UP;
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return false;
	}

	@Override
	public boolean canReceiveTorque(float amount, EnumFacing side) {
		if (this.currentTorque >= this.maxTorque())
			return false;
		return this.isInputSide(side.getOpposite());
	}

	@Override
	public float receiveTorque(float amount, EnumFacing side, boolean sim) {
		float f = maxTorque() - currentTorque;
		float ret = Math.min(amount, f);
		if (!sim) {
			currentTorque += ret;
		}
		return ret;
	}

	@Override
	public float getGearTier() {
		return 32.0F;
	}

	@Override
	public float maxSpeed() {
		return 0.0F;
	}

	@Override
	public float maxTorque() {
		return 128.0F;
	}

	@Override
	public void updateTile() {
		super.updateTile();

		float f = this.prevTorque;
		float p = 1.0F + (f * 0.3125F * 0.5F);
		pow = p;
	}

	private int count = 20;

	@Override
	protected void onServerUpdate() {
		if (count > 0) {
			count--;
		} else {
			boolean flag = false;
			if (pow != lastP) {
				flag = true;
				lastP = pow;
			}

			if (flag) {
				if (!this.hasWorldObj())
					return;
				@SuppressWarnings("unchecked")
				List<EntityPlayer> list = this.getWorld().playerEntities;
				for (EntityPlayer player : list) {
					if (player instanceof EntityPlayerMP) {
						((EntityPlayerMP) player).connection.sendPacket(this.getUpdatePacket());
					}
				}
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		rad = tag.getInteger("dcs.rad");
		pow = tag.getFloat("dcs.pow");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setInteger("dcs.rad", rad);
		tag.setFloat("dcs.pow", pow);
		return tag;
	}

}
