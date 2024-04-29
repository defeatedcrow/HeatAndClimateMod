package defeatedcrow.hac.machine.material.block.transport;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.machine.IFluidPipe;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.network.packet.message.MsgTileFaceIOToC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCFluidUtil;
import defeatedcrow.hac.machine.material.fluid.DCHeadTank;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class FluidPipeAlloyTile extends FluidPipeTileBaseDC {

	public FluidPipeAlloyTile(BlockPos pos, BlockState state) {
		super(MachineInit.PIPE_BRASS_TILE.get(), pos, state);
	}

	int count = 9;

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
		} else {
			count = 9;
			updateConnect(level, pos, state);
		}

		if (this.isActive(level, pos, state)) {
			for (Direction dir : DCUtil.PipeScanList) {
				Direction opposite = dir.getOpposite();
				BlockPos p2 = this.getBlockPos().relative(dir);
				if (getFluidHandler().getFace(dir) == FaceIO.OUTPUT) {
					BlockEntity targetEntity = getLevel().getBlockEntity(p2);
					if (targetEntity != null) {
						targetEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, opposite).ifPresent(handler -> {
							if (handler instanceof IFluidPipe sided) {
								if (sided.getFace(opposite).canReceive()) {
									int amo = Math.min(getFluidHandler().getFluidAmount(), getFluidHandler().getFlowRate());
									FluidStack drain = getFluidHandler().drain(amo, FluidAction.SIMULATE, dir);
									if (!drain.isEmpty()) {
										int ret = sided.fill(drain, FluidAction.EXECUTE, opposite);
										getFluidHandler().drain(ret, FluidAction.EXECUTE, dir);
									}
								}
							} else if (handler != null) {
								int amo = Math.min(getFluidHandler().getFluidAmount(), getFluidHandler().getFlowRate());
								FluidStack drain = getFluidHandler().drain(amo, FluidAction.SIMULATE, dir);
								if (!drain.isEmpty()) {
									int ret = handler.fill(drain, FluidAction.EXECUTE);
									getFluidHandler().drain(ret, FluidAction.EXECUTE, dir);
								}
							}
						});
					}
				} else if (getFluidHandler().getFace(dir) == FaceIO.PIPE) {
					BlockEntity targetEntity = getLevel().getBlockEntity(p2);
					if (targetEntity != null) {
						targetEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, opposite).ifPresent(handler -> {
							if (handler instanceof IFluidPipe sided) {
								if (sided.getFace(opposite) == FaceIO.INPUT) {
									// 空きがあれば流れる
									int amo = Math.min(getFluidHandler().getFluidAmount(), getFluidHandler().getFlowRate());
									FluidStack drain = getFluidHandler().drain(amo, FluidAction.SIMULATE, dir);
									if (!drain.isEmpty()) {
										int ret = sided.fill(drain, FluidAction.SIMULATE, opposite);
										if (ret > 0) {
											sided.fill(drain, FluidAction.EXECUTE, opposite);
											getFluidHandler().drain(ret, FluidAction.EXECUTE, dir);
										}
									}
								} else if (sided.getFace(opposite) == FaceIO.PIPE) {
									// 空きが多い方から少ない方へ
									int cap1 = getFluidHandler().getCapacity() - getFluidHandler().getFluidAmount();
									int cap2 = sided.getTankCapacity(0) - sided.getFluidAmount();
									if (cap2 > cap1) {
										int amo = Math.min((cap2 - cap1) / 2, getFluidHandler().getFlowRate());
										amo = Math.max(amo, 1);
										FluidStack drain = getFluidHandler().drain(amo, FluidAction.SIMULATE, dir);
										int ret = sided.fill(drain, FluidAction.SIMULATE, opposite);
										if (ret > 0) {
											sided.fill(drain, FluidAction.EXECUTE, opposite);
											getFluidHandler().drain(ret, FluidAction.EXECUTE, dir);
										}
									}
								}
							} else if (handler != null) {
								int amo = Math.min(getFluidHandler().getFluidAmount(), getFluidHandler().getFlowRate());
								FluidStack drain = getFluidHandler().drain(amo, FluidAction.SIMULATE, dir);
								if (!drain.isEmpty()) {
									int ret = handler.fill(drain, FluidAction.SIMULATE);
									if (ret > 0) {
										handler.fill(drain, FluidAction.EXECUTE);
										getFluidHandler().drain(ret, FluidAction.EXECUTE, dir);
									}
								}
							}
						});
					}
				} else if (dir != Direction.DOWN && getFluidHandler().getFace(dir) == FaceIO.INPUT) {
					BlockState targetState = getLevel().getBlockState(p2);
					BlockEntity targetEntity = getLevel().getBlockEntity(p2);
					if (targetEntity != null) {
						targetEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, Direction.DOWN).ifPresent(handler -> {
							if (handler != null && !(handler instanceof IFluidPipe)) {
								// 抜き取りモード
								int cap = getFluidHandler().getCapacity() - getFluidHandler().getFluidAmount();
								int amo = Math.min(cap, getFluidHandler().getFlowRate());
								FluidStack drain = handler.drain(amo, FluidAction.SIMULATE).copy();
								if (!drain.isEmpty()) {
									if (dir == Direction.UP)
										drain = DCFluidUtil.addHead(drain, 1);
									int ret = getFluidHandler().fill(drain, FluidAction.SIMULATE, dir);
									if (ret > 0) {
										getFluidHandler().fill(drain, FluidAction.EXECUTE, dir);
										handler.drain(ret, FluidAction.EXECUTE);
									}
								}
							}
						});
					} else if (targetState.getBlock() instanceof LayeredCauldronBlock cauldron) {
						if (cauldron == Blocks.WATER_CAULDRON && cauldron.isFull(targetState)) {
							FluidStack drain = new FluidStack(Fluids.WATER, 1000);
							int ret = getFluidHandler().fill(drain, FluidAction.SIMULATE, dir);
							if (ret >= 1000) {
								if (dir == Direction.UP)
									drain = DCFluidUtil.addHead(drain, 1);
								getFluidHandler().setFluid(drain);
								getLevel().setBlockAndUpdate(p2, Blocks.CAULDRON.defaultBlockState());
								getLevel().gameEvent((Entity) null, GameEvent.BLOCK_CHANGE, p2);
							}
						}
					}
				}
			}
		}

		return super.onTickProcess(level, pos, state);
	}

	public boolean updateConnect(Level level, BlockPos pos, BlockState state) {
		for (Direction dir : DCUtil.PipeScanList) {
			FaceIO facing = getFluidHandler().getFace(dir);
			BooleanProperty prop = DCState.getFacingProperty(dir);
			boolean connect = DCState.getBool(getBlockState(), prop);
			if (facing == FaceIO.NONE && connect) {
				getFluidHandler().setFace(dir, FaceIO.PIPE);
				MsgTileFaceIOToC.sendToClient((ServerLevel) level, pos, dir.get3DDataValue(), FaceIO.PIPE.getID());
			} else if (facing != FaceIO.NONE && !connect) {
				getFluidHandler().setFace(dir, FaceIO.NONE);
				MsgTileFaceIOToC.sendToClient((ServerLevel) level, pos, dir.get3DDataValue(), FaceIO.NONE.getID());
			}
		}
		return false;
	}

	// caps
	public DCHeadTank headtank = new DCHeadTank(2400, 32);

	@Override
	public DCHeadTank getFluidHandler() {
		return headtank;
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return null;
	}

}
