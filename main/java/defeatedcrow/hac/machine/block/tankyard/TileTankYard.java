package defeatedcrow.hac.machine.block.tankyard;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.base.ITagGetter;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.fluid.FluidIDRegisterDC;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageSingleTank;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TileTankYard extends DCTileEntity implements ITagGetter {

	private int limit = 200000;
	private DCTank inT;

	private boolean updateRequest = false;
	private boolean forcedUpdate = false;
	private boolean lastMulti;
	private int lastInT = 0;
	private int count = 20;

	public int width = 1;
	public int height = 1;
	public boolean multi;

	public void setRequest(boolean force) {
		updateRequest = true;
		forcedUpdate = force;
	}

	public void setTankYard(int w, int h) {
		width = w;
		height = h;
		int i = w * w * h;
		if (i > 5000) {
			i = 5000;
		}
		// 上限10億mB
		limit = 200000 * i;
		FluidStack fluid = null;
		if (!inT.isEmpty()) {
			fluid = inT.getFluid().copy();
		}
		inT = new DCTank(limit).setFluid(fluid);
		DCLogger.debugInfoLog("Created new tank! w:" + w + " h:" + h + " limit:" + limit);
	}

	public boolean startYard() {
		// 幅決定
		int w = 0;
		int h = 0;
		if (!forcedUpdate && width > 0 && height > 1) {
			w = (width - 1) / 2;
			h = height - 1;
		} else {
			while (world.getBlockState(pos.add(w + 1, 0, w + 1)).getBlock() == MachineInit.tankYardPart) {
				w++;
			}
			while (world.getBlockState(pos.add(w, h + 1, w)).getBlock() == MachineInit.tankYardPart) {
				h++;
			}
		}

		DCLogger.debugInfoLog("width:" + w + " height:" + h);
		if (w == 0 && h == 0) {
			setTankYard(1, 1);
			return true;
		}

		for (BlockPos p : BlockPos.MutableBlockPos.getAllInBox(new BlockPos(pos.add(-w, 0, -w)), new BlockPos(pos
				.add(w, h, w)))) {
			if (!world.isBlockLoaded(p))
				continue;
			if (p.equals(pos))
				continue;
			else if (p.getX() == pos.getX() - w || p.getX() == pos.getX() + w || p.getZ() == pos.getZ() - w || p
					.getZ() == pos.getZ() + w || p.getY() == pos.getY() || p.getY() == pos.getY() + h) {
				if (world.getBlockState(p).getBlock() != MachineInit.tankYardPart)
					return false;
			} else {
				if (!world.isAirBlock(p))
					return false;
			}
		}

		forcedUpdate = false;
		setTankYard((w * 2) + 1, h + 1);
		return true;
	}

	public void updateTankYard(boolean flag) {
		if (width > 0 && height > 1) {
			int w = (width - 1) / 2;
			int h = height - 1;
			for (BlockPos p : BlockPos.MutableBlockPos.getAllInBox(new BlockPos(pos.add(-w, 0, -w)), new BlockPos(pos
					.add(w, h, w)))) {
				if (!world.isBlockLoaded(p))
					continue;
				if (p.equals(pos))
					continue;
				TileEntity te = world.getTileEntity(p);
				IBlockState state = world.getBlockState(p);
				IBlockState next = state;
				if (te instanceof TileYardPart && state.getBlock() == MachineInit.tankYardPart) {
					if (flag) {
						((TileYardPart) te).parent = pos;
						int i = 1;
						if (width >= 3 && height >= 2) {
							if (p.getX() == pos.getX() - w && p.getZ() == pos.getZ() - w) {
								next = state.withProperty(DCState.FACING, EnumFacing.NORTH)
										.withProperty(DCState.TYPE4, 2);
							} else if (p.getX() == pos.getX() + w && p.getZ() == pos.getZ() + w) {
								next = state.withProperty(DCState.FACING, EnumFacing.SOUTH)
										.withProperty(DCState.TYPE4, 2);
							} else if (p.getX() == pos.getX() - w && p.getZ() == pos.getZ() + w) {
								next = state.withProperty(DCState.FACING, EnumFacing.EAST)
										.withProperty(DCState.TYPE4, 2);
							} else if (p.getX() == pos.getX() + w && p.getZ() == pos.getZ() - w) {
								next = state.withProperty(DCState.FACING, EnumFacing.WEST)
										.withProperty(DCState.TYPE4, 2);
							} else if (p.getY() > pos.getY() && p.getY() < pos.getY() + h) {
								if (p.getX() == pos.getX() || p.getZ() == pos.getZ()) {
									next = state.withProperty(DCState.TYPE4, 3);
								}
							}
						}
						world.setBlockState(p, next, 3);
						world.notifyNeighborsOfStateChange(pos, state.getBlock(), true);
					} else {
						((TileYardPart) te).parent = null;
						next = state.withProperty(DCState.TYPE4, 0);
						world.setBlockState(p, next, 3);
						world.notifyNeighborsOfStateChange(pos, state.getBlock(), true);
					}
				}
			}
		}
	}

	public int getLimit() {
		return limit;
	}

	public DCTank getTank() {
		if (inT == null) {
			inT = new DCTank(limit);
		}
		return inT;
	}

	@Override
	protected void onServerUpdate() {
		if (updateRequest) {
			DCLogger.debugInfoLog("start checking");
			multi = startYard();
			DCLogger.debugInfoLog("checking: " + multi);
			updateRequest = false;
			return;
		}
		if (multi != lastMulti) {
			updateTankYard(multi);
			if (!this.hasWorld())
				return;
			List<EntityPlayer> list = this.getWorld().playerEntities;
			for (EntityPlayer player : list) {
				if (player instanceof EntityPlayerMP) {
					((EntityPlayerMP) player).connection.sendPacket(this.getUpdatePacket());
				}
			}
			lastMulti = multi;
			return;
		}

		if (count > 0) {
			count--;
		} else {
			boolean flag = false;
			if (FluidIDRegisterDC.getID(getTank().getFluidType()) + getTank().getFluidAmount() != lastInT) {
				flag = true;
				lastInT = FluidIDRegisterDC.getID(getTank().getFluidType()) + getTank().getFluidAmount();
			}

			if (flag) {
				String name = getTank().isEmpty() ? "empty" : getTank().getFluidType().getName();
				DCMainPacket.INSTANCE.sendToAll(new MessageSingleTank(pos, name, getTank().getFluidAmount()));
			}
			count = 20;
		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) getTank();
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.setNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		this.getNBT(tag);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		tag.setInteger("width", width);
		tag.setInteger("height", height);
		tag.setBoolean("multi", multi);
		tag.setInteger("limit", limit);
		getTank().writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		width = tag.getInteger("width");
		height = tag.getInteger("height");
		multi = tag.getBoolean("multi");
		limit = tag.getInteger("limit");
		inT = getTank().readFromNBT(tag, "Tank");
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
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return (oldState.getBlock() != newSate.getBlock());
	}

}
