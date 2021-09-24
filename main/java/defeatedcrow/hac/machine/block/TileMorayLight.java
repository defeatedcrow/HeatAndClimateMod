package defeatedcrow.hac.machine.block;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.energy.ITorqueProvider;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.item.ItemColorGauntlet2;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileMorayLight extends TileTorqueBase implements ITorqueProvider {

	private int placeDate = 0;
	private int lastDay = 0;
	private int livingDay = 0;
	private UUID ownerID = null;
	private boolean check = false;

	@Override
	public float maxTorque() {
		return 8.0F;
	}

	@Override
	public float getGearTier() {
		return 32.0F;
	}

	public void setOwnerID(UUID id) {
		ownerID = id;
	}

	public UUID getOwnerID() {
		return ownerID;
	}

	public void setDate(int day) {
		placeDate = day;
	}

	public boolean isOwnerID(EntityPlayer player) {
		if (ownerID != null && player != null) {
			return ownerID.equals(player.getUniqueID());
		}
		return false;
	}

	@Override
	public void updateTile() {
		super.updateTile();

		IBlockState state = world.getBlockState(getPos());
		boolean b = DCState.getBool(state, DCState.POWERED);

		if (b) {
			float power = 8.0F;
			if (ownerID != null) {
				EntityPlayer owner = world.getPlayerEntityByUUID(ownerID);
				if (owner != null && MainUtil.getOffhandJewelColor(owner) == MagicColor.RED_BLUE) {
					float eff = MainUtil.magicSuitEff(owner);
					power *= eff;
				}
			}

			this.currentTorque += power;
			// provider
			for (EnumFacing side : getOutputSide()) {
				this.provideTorque(world, getPos().offset(side), side, false);
			}
		}

	}

	@Override
	protected void onServerUpdate() {
		if (ownerID != null) {
			// player検索
			boolean active = false;
			EntityPlayer owner = world.getPlayerEntityByUUID(ownerID);

			int day = DCTimeHelper.getDay(world);
			if (day != lastDay) {
				livingDay = day - placeDate;
				if (CoreConfigDC.enableRealTime && day < lastDay) {
					livingDay = day + (365 - placeDate);
				}
				lastDay = day;
			}

			ItemStack hand = ItemStack.EMPTY;
			if (MainUtil.getOffhandJewelColor(owner) == MagicColor.RED_BLUE) {
				hand = owner.getHeldItem(EnumHand.OFF_HAND);
				active = ItemColorGauntlet2.getActive(hand);
			}

			if (livingDay > 3) {
				active = false;
				if (!DCUtil.isEmpty(hand)) {
					world.setBlockToAir(getPos());
				}
			}

			IBlockState state = world.getBlockState(getPos());
			boolean b = DCState.getBool(state, DCState.POWERED);
			if (active != b) {
				IBlockState newState = state.withProperty(DCState.POWERED, active);
				world.setBlockState(getPos(), newState, 3);
				world.notifyNeighborsOfStateChange(pos, getBlockType(), true);
			}
		}
	}

	@Override
	public List<EnumFacing> getOutputSide() {
		List<EnumFacing> ret = Lists.newArrayList();
		ret.add(getBaseSide());
		return ret;
	}

	@Override
	public float getAmount() {
		return this.getCurrentTorque();
	}

	@Override
	public boolean canProvideTorque(World world, BlockPos outputPos, EnumFacing output) {
		TileEntity tile = world.getTileEntity(outputPos);
		float amo = this.getCurrentTorque();
		if (tile != null && tile instanceof ITorqueReceiver && amo > 0F)
			return ((ITorqueReceiver) tile).canReceiveTorque(amo, output.getOpposite());
		return false;
	}

	@Override
	public float provideTorque(World world, BlockPos outputPos, EnumFacing output, boolean sim) {
		float amo = this.getCurrentTorque();
		if (canProvideTorque(world, outputPos, output)) {
			ITorqueReceiver target = (ITorqueReceiver) world.getTileEntity(outputPos);
			float ret = target.receiveTorque(amo, output, sim);
			return ret;
		}
		return 0;
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		return !isOutputSide(side);
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return getOutputSide().contains(side);
	}

	// nbt

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		tag.setInteger("dcs.placeday", placeDate);
		tag.setInteger("dcs.lastday", lastDay);
		tag.setInteger("dcs.living", livingDay);
		if (ownerID != null)
			tag.setUniqueId("dcs.owner", ownerID);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		this.placeDate = tag.getInteger("dcs.placeday");
		this.lastDay = tag.getInteger("dcs.lastday");
		this.livingDay = tag.getInteger("dcs.livingday");
		this.ownerID = tag.getUniqueId("dcs.owner");
	}

}
