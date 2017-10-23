package defeatedcrow.hac.machine.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileAdapterPanel extends DCTileEntity {

	// pair
	protected BlockPos pairPos = null;

	public BlockPos getPairPos() {
		return pairPos;
	}

	public void setPairPos(BlockPos pos) {
		pairPos = pos;
	}

	boolean isActive() {
		IBlockState state = this.world.getBlockState(pos);
		if (state != null && state.getBlock() instanceof BlockAdapterPanel) {
			boolean flag = DCState.getBool(state, DCState.POWERED);
			return flag;
		}
		return false;
	}

	EnumSide getSide() {
		IBlockState state = this.world.getBlockState(pos);
		if (state != null && state.getBlock() instanceof BlockAdapterPanel) {
			EnumSide side = DCState.getSide(state, DCState.SIDE);
			return side;
		}
		return null;
	}

	TileEntity targetTile() {
		if (pairPos != null && world.isBlockLoaded(pairPos)) {
			int xrad = (pos.getX() >> 4) - (pairPos.getX() >> 4);
			int zrad = (pos.getZ() >> 4) - (pairPos.getZ() >> 4);
			if (xrad * xrad + zrad * zrad <= 25) {
				return world.getTileEntity(pairPos);
			}
		}
		return null;
	}

	@Override
	protected int getMaxCool() {
		return 5;
	}

	@Override
	public void updateTile() {
		super.updateTile();
		if (!world.isRemote && isActive()) {
			extractItem();
		}
	}

	private boolean extractItem() {
		EnumSide side = getSide();
		if (side != null && targetTile() != null && targetTile() instanceof TileAcceptorPanel) {
			EnumFacing face = getSide().face;
			TileAcceptorPanel panel = (TileAcceptorPanel) targetTile();
			TileEntity tile = world.getTileEntity(pos.offset(face));
			if (tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face.getOpposite())) {
				IItemHandler target = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						face.getOpposite());
				IItemHandler panelInv = panel.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						face.getOpposite());
				if (panelInv != null && target != null) {
					boolean b = false;
					for (int i = 0; i < target.getSlots(); i++) {
						ItemStack item = target.extractItem(i, 1, true);
						if (!DCUtil.isEmpty(item)) {
							ItemStack ins = item.copy();
							ins.setCount(1);
							for (int j = 0; j < panelInv.getSlots(); j++) {
								ItemStack ret = panelInv.insertItem(j, ins, false);
								if (DCUtil.isEmpty(ret)) {
									target.extractItem(i, 1, false);
									panel.targetTile().markDirty();
									tile.markDirty();
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	// NBT

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		setNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		getNBT(tag);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		if (pairPos != null) {
			tag.setInteger("pairX", pairPos.getX());
			tag.setInteger("pairY", pairPos.getY());
			tag.setInteger("pairZ", pairPos.getZ());
			tag.setBoolean("dcs.haspair", true);
		}
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		if (tag.hasKey("dcs.haspair") && tag.getBoolean("dcs.haspair")) {
			int px = tag.getInteger("pairX");
			int py = tag.getInteger("pairY");
			int pz = tag.getInteger("pairZ");
			BlockPos pos = new BlockPos(px, py, pz);
			pairPos = pos;
		}
	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		this.writeToNBT(nbtTagCompound);
		return new SPacketUpdateTileEntity(pos, -50, nbtTagCompound);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return (oldState.getBlock() != newSate.getBlock());
	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	/* inventory handler */

}
