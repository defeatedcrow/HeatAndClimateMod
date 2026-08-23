package defeatedcrow.hac.machine.material.block.transport;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.machine.IFluidPipe;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.network.packet.message.MsgTileFaceIOToC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCFluidUtil;
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
import net.minecraft.world.level.block.entity.BlockEntityType;
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

	public FluidPipeAlloyTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	int count = 4;

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
		} else {
			count = 4;
			updateConnect(level, pos, state);
		}

		if (this.isActive(level, pos, state)) {
			// Extract
			boolean flag = false;
			List<Direction> targetDirectlist = Lists.newArrayList();
			List<Direction> targetAvaragelist = Lists.newArrayList();
			int amount = Math.min(getFluidHandler().getFluidAmount(), getFluidHandler().getFlowRate());
			for (Direction dir : DCUtil.PipeScanList) {
				FlowType type = isCollectSendTarget(dir);
				if (type == FlowType.ALL) {
					targetDirectlist.add(dir);
				}
				if (type == FlowType.AVERAGE) {
					targetAvaragelist.add(dir);
				}
			}
			// DCLogger.debugInfoLog("initial flow: " + amount);
			if (!targetDirectlist.isEmpty()) {
				int directAmount = amount / targetDirectlist.size();
				// DCLogger.debugInfoLog("step1 flow : " + directAmount);
				int count = 0;
				while (amount > 0 && count < targetDirectlist.size()) {
					Direction dir = targetDirectlist.get(count);
					int ret1 = flowAll(dir, directAmount);
					// DCLogger.debugInfoLog(dir + " : " + ret1);
					amount -= ret1;
					count++;
				}
			}
			if (!targetAvaragelist.isEmpty() && amount > 0) {
				int averageAmount = amount / targetAvaragelist.size();
				// DCLogger.debugInfoLog("step2 flow : " + averageAmount);
				int count = 0;
				while (amount > 0 && count < targetAvaragelist.size()) {
					Direction dir = targetAvaragelist.get(count);
					int ret2 = flowAverage(dir, averageAmount);
					// DCLogger.debugInfoLog(dir + " : " + ret2);
					amount -= ret2;
					count++;
				}
			}
			// DCLogger.debugInfoLog("final flow: " + amount);

			// Suction
			for (Direction dir : DCUtil.PipeScanList) {
				Direction opposite = dir.getOpposite();
				BlockPos p2 = this.getBlockPos()
				    .relative(dir);
				if (getFluidHandler().getFace(dir) == FaceIO.INPUT) {
					BlockState targetState = getLevel().getBlockState(p2);
					BlockEntity targetEntity = getLevel().getBlockEntity(p2);
					if (targetEntity != null) {
						targetEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, Direction.DOWN)
						    .ifPresent(handler -> {
							    if (handler != null && !(handler instanceof IFluidPipe)) {
								    // 抜き取りモード
								    int cap = getFluidHandler().getCapacity() - getFluidHandler().getFluidAmount();
								    int amo = Math.min(cap, getFluidHandler().getFlowRate());
								    FluidStack drain = handler.drain(amo, FluidAction.SIMULATE)
								        .copy();
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

	private static enum FlowType {
		NONE,
		AVERAGE,
		ALL;
	}

	private FlowType isCollectSendTarget(Direction dir) {
		if (!getFluidHandler().getFace(dir)
		    .canExtract())
			return FlowType.NONE;
		boolean isGas = DCFluidUtil.isGas(getFluidHandler().getFluid());
		BlockPos p2 = this.getBlockPos()
		    .relative(dir);
		BlockEntity targetEntity = getLevel().getBlockEntity(p2);
		Direction opposite = dir.getOpposite();
		if (targetEntity != null) {
			return targetEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, opposite)
			    .map(handler -> {
				    if (handler instanceof IFluidPipe pipe) {
					    if (pipe.getFace(opposite)
					        .canReceive()) {
						    if (getFluidHandler().getFace(dir) == FaceIO.OUTPUT) {
							    return FlowType.ALL;
						    } else if (pipe.getFace(opposite) == FaceIO.INPUT) {
							    return FlowType.ALL;
						    } else {
							    // PIPE to PIPE
							    int sourceCap = getFluidHandler().getCapacity() - getFluidHandler().getFluidAmount();
							    int destCap = pipe.getTankCapacity(0) - pipe.getFluidAmount();
							    if (dir == Direction.DOWN) {
								    if (isGas && sourceCap <= destCap) {
									    return FlowType.NONE;
								    } else if (isGas || destCap <= pipe.getFlowRate() && sourceCap > destCap) {
									    return FlowType.AVERAGE;
								    } else {
									    return FlowType.ALL;
								    }
							    } else if (dir == Direction.UP) {
								    if (isGas || sourceCap <= pipe.getFlowRate()) {
									    return FlowType.AVERAGE;
								    } else {
									    return FlowType.NONE;
								    }
							    } else {
								    if (destCap > sourceCap) {
									    return FlowType.AVERAGE;
								    } else {
									    return FlowType.NONE;
								    }
							    }
						    }
					    }
				    } else {
					    return FlowType.ALL;
				    }
				    return FlowType.NONE;
			    })
			    .orElse(FlowType.NONE);
		}
		return FlowType.NONE;
	}

	private int flowAll(Direction dir, int flow) {
		BlockPos p2 = this.getBlockPos()
		    .relative(dir);
		int amount = 0;
		if (getFluidHandler().getFace(dir)
		    .canExtract()) {
			BlockEntity targetEntity = getLevel().getBlockEntity(p2);
			if (targetEntity != null) {
				amount = targetEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, dir.getOpposite())
				    .map(handler -> {
					    if (handler instanceof IFluidPipe sided) {
						    if (sided.getFace(dir.getOpposite())
						        .canReceive()) {
							    int amo = Math.min(getFluidHandler().getFluidAmount(), flow);
							    FluidStack drain = getFluidHandler().drain(amo, FluidAction.SIMULATE, dir);
							    // 満タンまで流れる
							    if (!drain.isEmpty()) {
								    int ret = sided.fill(drain, FluidAction.EXECUTE, dir.getOpposite());
								    return getFluidHandler().drain(ret, FluidAction.EXECUTE, dir)
								        .getAmount();
							    }
						    }
					    } else if (handler != null) {
						    int amo = Math.min(getFluidHandler().getFluidAmount(), flow);
						    FluidStack drain = getFluidHandler().drain(amo, FluidAction.SIMULATE, dir);
						    if (!drain.isEmpty()) {
							    int ret = handler.fill(drain, FluidAction.EXECUTE);
							    return getFluidHandler().drain(ret, FluidAction.EXECUTE, dir)
							        .getAmount();
						    }
					    }
					    return 0;
				    })
				    .orElse(0);
			}
		}
		return amount;
	}

	private int flowAverage(Direction dir, int flow) {
		Direction opposite = dir.getOpposite();
		BlockPos p2 = this.getBlockPos()
		    .relative(dir);
		int amount = 0;
		BlockEntity targetEntity = getLevel().getBlockEntity(p2);
		if (targetEntity != null) {
			amount = targetEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, opposite)
			    .map(handler -> {
				    int cap1 = getFluidHandler().getCapacity() - getFluidHandler().getFluidAmount();
				    int cap2 = handler.getTankCapacity(0) - handler.getFluidInTank(0)
				        .getAmount();
				    if (handler instanceof IFluidPipe sided) {
					    // 空きが多い方から少ない方へ
					    if (cap2 > cap1) {
						    int amo = Math.min(cap2 - cap1 + 1 / 2, flow);
						    amo = Math.max(amo, 1);
						    FluidStack drain = getFluidHandler().drain(amo, FluidAction.SIMULATE, dir);
						    int ret = sided.fill(drain, FluidAction.SIMULATE, opposite);
						    if (ret > 0) {
							    sided.fill(drain, FluidAction.EXECUTE, opposite);
							    return getFluidHandler().drain(ret, FluidAction.EXECUTE, dir)
							        .getAmount();
						    }
					    }
				    } else if (handler != null) {
					    // 空きが多い方から少ない方へ
					    if (cap2 > cap1) {
						    int amo = Math.min(cap2 - cap1 + 1 / 2, flow);
						    amo = Math.max(amo, 1);
						    FluidStack drain = getFluidHandler().drain(amo, FluidAction.SIMULATE, dir);
						    int ret = handler.fill(drain, FluidAction.SIMULATE);
						    if (ret > 0) {
							    handler.fill(drain, FluidAction.EXECUTE);
							    return getFluidHandler().drain(ret, FluidAction.EXECUTE, dir)
							        .getAmount();
						    }
					    }
				    }
				    return 0;
			    })
			    .orElse(0);
		}
		return amount;
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
	public PipeTank headtank = new PipeTank(2400, 256, this);

	@Override
	public PipeTank getFluidHandler() {
		return headtank;
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return null;
	}

}
