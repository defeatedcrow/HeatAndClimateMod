package defeatedcrow.hac.machine.block;

import java.util.List;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileHeatExchanger extends TileTorqueBase implements ITorqueReceiver {

	protected IClimate current = null;
	protected int lastClimate = 0;

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side != getBaseSide() && side != getBaseSide().getOpposite();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite();
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
		return 16.0F;
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

		// 気候チェック
		if (!worldObj.isRemote) {
			DCHeatTier heat = getUnderHeat();
			DCHumidity hum = ClimateAPI.calculator.getHumidity(worldObj, pos, 1, false);
			DCAirflow air = ClimateAPI.calculator.getAirflow(worldObj, pos, 1, false);

			int code = (air.getID() << 6) + (hum.getID() << 4) + heat.getID();
			current = ClimateAPI.register.getClimateFromInt(code);
		}
	}

	private int count = 20;

	@Override
	protected void onServerUpdate() {
		if (count > 0) {
			count--;
		} else {
			boolean flag = false;
			if (current != null && current.getClimateInt() != lastClimate) {
				flag = true;
				lastClimate = current.getClimateInt();
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

		if (tag.hasKey("dcs.climateInt")) {
			int ic = tag.getInteger("dcs.climateInt");
			IClimate clm = ClimateAPI.register.getClimateFromInt(ic);
			if (clm != null) {
				current = clm;
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		if (current != null) {
			tag.setInteger("dcs.climateInt", current.getClimateInt());
		}
		return tag;
	}

	private DCHeatTier getUnderHeat() {
		DCHeatTier hot = ClimateAPI.calculator.getAverageTemp(worldObj, getPos().down(), 0, false);

		Block block = worldObj.getBlockState(pos.down()).getBlock();
		int m = block.getMetaFromState(worldObj.getBlockState(pos.down()));
		if (block instanceof IHeatTile) {
			DCHeatTier current = ((IHeatTile) block).getHeatTier(worldObj, pos, pos.down());
			if (current.getTier() != hot.getTier()) {
				hot = current;
			}
		} else if (ClimateAPI.registerBlock.isRegisteredHeat(block, m)) {
			DCHeatTier cur = ClimateAPI.registerBlock.getHeatTier(block, m);
			if (cur.getTier() != hot.getTier()) {
				hot = cur;
			}
		}

		return hot;
	}

}
