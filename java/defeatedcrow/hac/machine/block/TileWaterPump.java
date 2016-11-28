package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.core.fluid.DCTank;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TileWaterPump extends TileTorqueBase implements ITorqueReceiver {

	// process
	public int currentProgressTime = 0;
	public static final int MAX_PROGRESS_TIME = 128;

	private int lastBurn = 0;

	public DCTank inputT = new DCTank(5000);

	private int loadCount = 3;

	@Override
	public void updateTile() {
		super.updateTile();
		if (loadCount > 0) {
			loadCount--;
			return;
		}

		if (!worldObj.isRemote) {
			currentProgressTime += MathHelper.floor_float(prevTorque);
			int limit = currentProgressTime / 32;
			int ret = currentProgressTime % 32;
			// DCLogger.debugLog("count: " + count + ", ret " + ret);
			currentProgressTime = ret;

			// 吸う
			BlockPos next = getPos().down();
			BlockPos prev = getPos().down();
			int fill = 0;
			int count = 0;
			for (int i = 0; i < 4; i++) {
				if (count >= limit) {
					break;
				}
				if (worldObj.isAirBlock(next)) {
					for (int d = 1; d < 5; d++) {
						if (!worldObj.isAirBlock(next.down(d))) {
							next = next.down(d);
							break;
						}
					}
				}
				IBlockState state = worldObj.getBlockState(next);

				if (state != null && state.getBlock() instanceof BlockLiquid) {
					BlockLiquid liq = (BlockLiquid) state.getBlock();
					fill = this.fillLiquid(liq, next, state);
				} else if (state != null && state.getBlock() instanceof BlockFluidBase) {
					if (state.getBlock() instanceof BlockFluidFinite) {
						BlockFluidFinite flu = (BlockFluidFinite) state.getBlock();
						FluidStack get = flu.drain(worldObj, next, false);
						if (get != null && inputT.fill(get, false) > 0) {
							flu.drain(worldObj, next, true);
							fill = inputT.fill(get, true);
						}
					} else if (state.getBlock() instanceof BlockFluidClassic) {
						BlockFluidClassic flu = (BlockFluidClassic) state.getBlock();
						fill = this.fillFluid(flu, next, state);
					}
				}

				if (fill > 0) {
					EnumFacing nF = EnumFacing.DOWN;
					for (EnumFacing f2 : EnumFacing.HORIZONTALS) {
						BlockPos p2 = next.offset(f2);
						IBlockState s2 = worldObj.getBlockState(p2);
						if (s2.getBlock() instanceof BlockLiquid || s2.getBlock() instanceof BlockFluidBase) {
							if (s2.getBlock().getMetaFromState(s2) < state.getBlock().getMetaFromState(state)) {
								nF = f2;
							}
						}
					}
					next = next.offset(nF);
					count++;
				} else {
					break;
				}
			}

			// 排出
			TileEntity uTile = worldObj.getTileEntity(getPos().up());
			FluidStack fluid2 = inputT.getFluid();
			if (uTile != null
					&& uTile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
				IFluidHandler tank = uTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
						EnumFacing.DOWN);
				if (tank != null && fluid2 != null && tank.fill(fluid2, false) > 0) {
					int ext2 = tank.fill(fluid2, true);
					inputT.drain(ext2, true);
					this.markDirty();
					uTile.markDirty();
				}
			}
		}
	}

	private int fillLiquid(BlockLiquid liq, BlockPos tPos, IBlockState state) {
		int level = state.getValue(BlockLiquid.LEVEL).intValue();
		if (state.getMaterial() == Material.WATER) {
			FluidStack get = new FluidStack(FluidRegistry.WATER, 1000);
			if (inputT.fill(get, false) > 0) {
				return inputT.fill(get, true);
			}
		} else if (state.getMaterial() == Material.LAVA) {
			FluidStack get = new FluidStack(FluidRegistry.LAVA, 1000);
			if (level == 0) {
				if (inputT.fill(get, false) > 0) {
					worldObj.setBlockToAir(tPos);
					return inputT.fill(get, true);
				}
			} else {
				// 隣接チェック
				// 8ブロック先までは探る
				BlockPos next = tPos;
				int l2 = level;

				if (inputT.fill(get, false) <= 0) {
					return 0;
				}
				for (int count = 0; count < 8; count++) {
					int[] ofs = {
							0,
							0,
							0 };
					for (EnumFacing f2 : EnumFacing.VALUES) {
						if (f2 == EnumFacing.DOWN) {
							continue; // 真下方向は探らない
						}
						BlockPos p2 = next.offset(f2);
						IBlockState s2 = worldObj.getBlockState(p2);
						if (s2.getBlock() instanceof BlockLiquid && s2.getMaterial() == Material.LAVA) {
							BlockLiquid liq2 = (BlockLiquid) s2.getBlock();
							if (s2.getValue(BlockLiquid.LEVEL) == 0) {
								ofs = new int[] {
										f2.getFrontOffsetX(),
										f2.getFrontOffsetY(),
										f2.getFrontOffsetZ() };
								l2 = s2.getValue(BlockLiquid.LEVEL).intValue();
								// DCLogger.debugLog(
								// "c" + count + ", (" + ofs[0] + " " + ofs[1] + " " + ofs[2] + "),
								// source");
								break;
							} else if (s2.getValue(BlockLiquid.LEVEL) < l2) {
								ofs = new int[] {
										f2.getFrontOffsetX(),
										f2.getFrontOffsetY(),
										f2.getFrontOffsetZ() };
								l2 = s2.getValue(BlockLiquid.LEVEL).intValue();
								// DCLogger.debugLog(
								// "c" + count + ", (" + ofs[0] + " " + ofs[1] + " " + ofs[2] + "),
								// " + l2);
							} else {
								BlockPos p3 = p2.up();
								IBlockState s3 = worldObj.getBlockState(p3);
								if (s3.getBlock() instanceof BlockLiquid && s3.getMaterial() == Material.LAVA) {
									ofs = new int[] {
											f2.getFrontOffsetX(),
											f2.getFrontOffsetY() + 1,
											f2.getFrontOffsetZ() };
									l2 = s3.getValue(BlockLiquid.LEVEL);
									// DCLogger.debugLog(
									// "c" + count + ", (" + ofs[0] + " " + ofs[1] + " " + ofs[2] +
									// "), upper");
									break;
								}
							}

						}
					}
					if (ofs[0] != 0 || ofs[1] != 0 || ofs[2] != 0) {
						next = next.add(ofs[0], ofs[1], ofs[2]);
						if (worldObj.getBlockState(next).getValue(BlockLiquid.LEVEL).intValue() == 0) {
							break;
						}
					} else {
						break;
					}
				}
				if (!next.equals(tPos)) {
					IBlockState s3 = worldObj.getBlockState(next);
					if (s3.getBlock() instanceof BlockLiquid && s3.getValue(BlockLiquid.LEVEL).intValue() == 0) {
						if (inputT.fill(get, false) > 0) {
							worldObj.setBlockToAir(next);
							return inputT.fill(get, true);
						}
					}
				}
			}
		}
		return 0;
	}

	private int fillFluid(BlockFluidClassic flu, BlockPos tPos, IBlockState state) {
		int level = state.getValue(BlockFluidBase.LEVEL).intValue();
		Fluid fluid = flu.getFluid();
		boolean gas = flu.getFluid().getDensity() < 0;
		if (fluid == null) {
			return 0;
		}
		FluidStack get = new FluidStack(fluid, 1000);

		if (flu.isSourceBlock(worldObj, tPos)) {
			if (inputT.fill(get, false) > 0) {
				flu.drain(worldObj, tPos, true);
				return inputT.fill(get, true);
			} else {
				return 0;
			}
		} else {
			// 隣接チェック
			// 8ブロック先までは探る
			BlockPos next = tPos;
			int l2 = flu.getQuantaValue(worldObj, tPos);
			for (int count = 0; count < 8; count++) {
				int[] ofs = new int[3];
				for (EnumFacing f2 : EnumFacing.VALUES) {
					if (!gas && f2 == EnumFacing.DOWN) {
						continue;
					}
					BlockPos p2 = next.offset(f2);
					IBlockState s2 = worldObj.getBlockState(p2);
					if (s2.getBlock() instanceof BlockFluidClassic) {
						BlockFluidClassic flu2 = (BlockFluidClassic) s2.getBlock();
						if (flu2.getFluid() == fluid) {
							if (flu2.isSourceBlock(worldObj, p2)) {
								ofs = new int[] {
										f2.getFrontOffsetX(),
										f2.getFrontOffsetY(),
										f2.getFrontOffsetZ() };
								l2 = flu2.getQuantaValue(worldObj, p2);
								// DCLogger.debugLog(
								// "c" + count + ", (" + ofs[0] + " " + ofs[1] + " " + ofs[2] + "),
								// source");
								break;
							} else if (flu2.getQuantaValue(worldObj, p2) > l2) {
								ofs = new int[] {
										f2.getFrontOffsetX(),
										f2.getFrontOffsetY(),
										f2.getFrontOffsetZ() };
								l2 = flu2.getQuantaValue(worldObj, p2);
								// DCLogger.debugLog(
								// "c" + count + ", (" + ofs[0] + " " + ofs[1] + " " + ofs[2] + "),
								// " + l2);
							} else {
								BlockPos p3 = gas ? p2.down() : p2.up();
								IBlockState s3 = worldObj.getBlockState(p3);
								if (s3.getBlock() instanceof BlockFluidClassic) {
									BlockFluidClassic flu3 = (BlockFluidClassic) s3.getBlock();
									if (flu3.getFluid() == fluid) {
										ofs = new int[] {
												f2.getFrontOffsetX(),
												f2.getFrontOffsetY() + (gas ? -1 : 1),
												f2.getFrontOffsetZ() };
										l2 = flu3.getQuantaValue(worldObj, p3);
										// DCLogger.debugLog("c" + count + ", (" + ofs[0] + " " +
										// ofs[1] + " " + ofs[2]
										// + "), upper");
										break;
									}
								}
							}
						}
					}
				}

				if (ofs[0] != 0 || ofs[1] != 0 || ofs[2] != 0) {
					next = next.add(ofs[0], ofs[1], ofs[2]);
					if (worldObj.getBlockState(next).getBlock() instanceof BlockFluidBase
							&& worldObj.getBlockState(next).getValue(BlockFluidBase.LEVEL).intValue() == 0) {
						break;
					}
				} else {
					break;
				}
			}
			// DCLogger.debugLog("result... x:" + next.getX() + ", y:" + next.getY() + ", z:" +
			// next.getZ());
			if (!next.equals(tPos)) {
				IBlockState s4 = worldObj.getBlockState(next);
				if (s4.getBlock() instanceof BlockFluidClassic && s4.getValue(BlockFluidBase.LEVEL).intValue() == 0) {
					if (inputT.fill(get, false) > 0) {
						flu.drain(worldObj, next, true);
						return inputT.fill(get, true);
					}
				}
			}

		}
		return 0;
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side != EnumFacing.DOWN && side != EnumFacing.UP;
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite();
	}

	@Override
	public boolean canReceiveTorque(float amount, EnumFacing side) {
		if (this.currentTorque >= this.maxTorque()) {
			return false;
		}
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

	// tier
	@Override
	public float maxTorque() {
		return 128.0F;
	}

	@Override
	public float getGearTier() {
		return 64.0F;
	}

	@Override
	public float maxSpeed() {
		return 360.0F;
	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		this.currentProgressTime = tag.getInteger("BurnTime");

		inputT = inputT.readFromNBT(tag, "Tank1");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("BurnTime", this.currentProgressTime);

		inputT.writeToNBT(tag, "Tank1");
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("BurnTime", this.currentProgressTime);

		inputT.writeToNBT(tag, "Tank1");
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		this.currentProgressTime = tag.getInteger("BurnTime");

		inputT = inputT.readFromNBT(tag, "Tank1");
	}

}
