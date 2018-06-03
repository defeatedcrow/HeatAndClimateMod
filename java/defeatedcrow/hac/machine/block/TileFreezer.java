package defeatedcrow.hac.machine.block;

import java.util.List;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileFreezer extends TileTorqueBase implements ITorqueReceiver {

	protected DCHeatTier current = null;

	protected int lastHeat = 0;

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == getBaseSide().rotateY();
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
	public float maxTorque() {
		return 512.0F;
	}

	@Override
	public float getGearTier() {
		return 64.0F;
	}

	@Override
	public float maxSpeed() {
		return 0.0F;
	}

	@Override
	public void updateTile() {
		super.updateTile();

		// 気候チェック
		if (!world.isRemote) {
			float f = this.prevTorque;
			current = DCHeatTier.NORMAL;
			if (f > 63.0F) {
				current = DCHeatTier.ABSOLUTE;
			} else if (f > 31.0F) {
				current = DCHeatTier.CRYOGENIC;
			} else if (f > 5.0F) {
				current = DCHeatTier.FROSTBITE;
			}
		}
	}

	private int count = 20;

	@Override
	protected void onServerUpdate() {
		if (count > 0) {
			count--;
		} else {
			boolean flag = false;
			if (current != null && current.getID() != lastHeat) {
				flag = true;
				lastHeat = current.getID();
			}

			if (flag) {
				if (!this.hasWorld())
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

		if (tag.hasKey("dcs.heatID")) {
			int ic = tag.getInteger("dcs.heatID");
			DCHeatTier tier = DCHeatTier.getTypeByID(ic);
			if (tier != null) {
				current = tier;
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		if (current != null) {
			tag.setInteger("dcs.heatID", current.getID());
		}
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		if (current != null) {
			tag.setInteger("dcs.heatID", current.getID());
		}
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		if (tag.hasKey("dcs.heatID")) {
			int ic = tag.getInteger("dcs.heatID");
			DCHeatTier tier = DCHeatTier.getTypeByID(ic);
			if (tier != null) {
				current = tier;
			}
		}
	}

}
