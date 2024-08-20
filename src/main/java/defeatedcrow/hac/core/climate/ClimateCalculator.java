package defeatedcrow.hac.core.climate;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IAirflowTile;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.climate.IClimateCalculator;
import defeatedcrow.hac.api.climate.IClimateIgnoreBlock;
import defeatedcrow.hac.api.climate.IHeatCanceler;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.api.climate.IHumidityTile;
import defeatedcrow.hac.api.event.BlockHeatTierEvent;
import defeatedcrow.hac.api.material.IPosLinkTile;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.IFluidBlock;

/**
 * 気候計算
 */
public class ClimateCalculator implements IClimateCalculator {

	@Override
	public IClimate getClimate(Level level, BlockPos pos) {
		DCHeatTier temp = ClimateAPI.calculator.getAverageTemp(level, pos);
		DCHumidity hum = ClimateAPI.calculator.getHumidity(level, pos, 1, false);
		DCAirflow air = ClimateAPI.calculator.getAirflow(level, pos);

		int code = (air.getID() << 6) + (hum.getID() << 4) + temp.getID();
		IClimate clm = ClimateAPI.helper.getClimateFromInt(code);
		return clm;
	}

	@Override
	public DCHeatTier getAverageTemp(Level level, BlockPos pos) {
		DCHeatTier temp = ClimateAPI.calculator.getMostHeat(level, pos);
		DCHeatTier cold = ClimateAPI.calculator.getMostCold(level, pos);
		// DCLogger.debugLog("Heat: " + temp.getTier() + " , Cold: " + cold.getTier());

		if (temp.getTier() > cold.getTier() && cold.getTier() < 0) {
			if (temp.getTier() < 0) {
				temp = cold;
			} else {
				temp = temp.addTier(cold.getTier());
			}
		}
		if (temp.getTier() < cold.getTier()) {
			temp = cold;
		}
		return temp;
	}

	@Override
	public DCHeatTier getMostHeat(Level level, BlockPos pos) {
		if (level == null || pos == null || !level.isLoaded(pos)) {
			return DCHeatTier.NORMAL;
		}
		DCHeatTier temp = ClimateAPI.registerBiome.getHeatTier(level, pos);
		if (temp == null) {
			temp = DCHeatTier.NORMAL;
		}
		/*
		 * biomeの気温
		 * 屋根あり: Tierが1段階Normalに近づく
		 */
		DCHeatTier hot = temp;
		if (hasRoof(level, pos)) {
			if (temp.getTier() < 0) {
				hot = temp.addTier(1);
			} else if (temp.getTier() > 0) {
				hot = temp.addTier(-1);
			}
		}

		/*
		 * PosLinkTile
		 */
		BlockEntity self = level.getBlockEntity(pos);
		if (self instanceof IPosLinkTile link) {
			if (link.getLinkType() == IPosLinkTile.Type.HEAT && link.getLinkPos() != null) {
				DCHeatTier current = getBlockHeatTier(level, pos, link.getLinkPos(), true);
				if (current != null && current.getTier() > hot.getTier()) {
					hot = current;
				}
			}
		}
		/*
		 * blockの気温
		 */
		int r = 2;
		int h1 = r;
		for (int x = pos.getX() - r; x <= pos.getX() + r; x++) {
			for (int z = pos.getZ() - r; z <= pos.getZ() + r; z++) {
				for (int y = pos.getY() - h1; y <= pos.getY() + h1; y++) {
					BlockPos up = new BlockPos(x, y + 1, z);
					BlockState upb = level.getBlockState(up);
					if (upb != null && upb.getBlock() instanceof IHeatCanceler) {
						if (((IHeatCanceler) upb.getBlock()).isActive(upb)) {
							continue;
						}
					}

					BlockPos p2 = new BlockPos(x, y, z);
					if (level.isLoaded(p2)) {
						BlockState st = level.getBlockState(p2);
						if (st != null && st.getBlock() instanceof IClimateIgnoreBlock) {
							if (((IClimateIgnoreBlock) st.getBlock()).isActive(st)) {
								continue;
							}
						}

						DCHeatTier current = getBlockHeatTier(level, pos, p2, true);
						if (current != null && current.getTier() > hot.getTier()) {
							hot = current;
						}
					}

				}
			}
		}

		return hot;
	}

	@Override
	public DCHeatTier getMostCold(Level level, BlockPos pos) {
		if (level == null || pos == null || !level.isLoaded(pos)) {
			return DCHeatTier.NORMAL;
		}
		DCHeatTier temp = ClimateAPI.registerBiome.getHeatTier(level, pos);
		if (temp == null) {
			temp = DCHeatTier.NORMAL;
		}
		/*
		 * biomeの気温
		 * 屋根あり: Tierが1段階Normalに近づく
		 */
		DCHeatTier cold = temp;
		if (hasRoof(level, pos)) {
			if (temp.getTier() < 0) {
				cold = cold.addTier(1);
			} else if (temp.getTier() > 0) {
				cold = cold.addTier(-1);
			}
		}

		/*
		 * PosLinkTile
		 */
		BlockEntity self = level.getBlockEntity(pos);
		if (self instanceof IPosLinkTile link) {
			if (link.getLinkType() == IPosLinkTile.Type.HEAT && link.getLinkPos() != null) {
				DCHeatTier current = getBlockHeatTier(level, pos, link.getLinkPos(), true);
				if (current != null && current.getTier() < cold.getTier()) {
					cold = current;
				}
			}
		}
		/*
		 * blockの気温
		 */
		int r = 2;
		int h1 = r;
		for (int x = pos.getX() - r; x <= pos.getX() + r; x++) {
			for (int z = pos.getZ() - r; z <= pos.getZ() + r; z++) {
				for (int y = pos.getY() - h1; y <= pos.getY() + h1; y++) {
					BlockPos up = new BlockPos(x, y + 1, z);
					BlockState upb = level.getBlockState(up);
					if (upb != null && upb.getBlock() instanceof IHeatCanceler) {
						if (((IHeatCanceler) upb.getBlock()).isActive(upb)) {
							continue;
						}
					}

					BlockPos p2 = new BlockPos(x, y, z);
					if (level.isLoaded(p2)) {
						BlockState st = level.getBlockState(p2);
						if (st != null && st.getBlock() instanceof IClimateIgnoreBlock) {
							if (((IClimateIgnoreBlock) st.getBlock()).isActive(st)) {
								continue;
							}
						}

						DCHeatTier current = getBlockHeatTier(level, pos, p2, false);
						if (current != null && current.getTier() < cold.getTier()) {
							cold = current;
						}
					}
				}
			}
		}
		return cold;
	}

	// 合計値で考える
	@Override
	public DCHumidity getHumidity(Level level, BlockPos pos, int r, boolean h) {
		if (level == null || pos == null || !level.isLoaded(pos)) {
			return DCHumidity.NORMAL;
		}

		// biomeの基礎湿度
		DCHumidity hum = ClimateAPI.registerBiome.getHumidity(level, pos);
		Biome biome = level.getBiome(pos).get();
		int ret = hum.getID() - 1;
		boolean isUnderwater = false;
		boolean hasWater = false;
		int hasAir = 0;

		// さきに水没判定をやる
		// 中心
		DCHumidity target = getBlockHumidity(level, pos, pos);
		if (target == DCHumidity.UNDERWATER)
			return DCHumidity.UNDERWATER;

		// 周囲
		for (Direction face : Direction.values()) {
			BlockPos p1 = new BlockPos(pos.getX() + face.getStepX(), pos.getY() + face.getStepY(), pos.getZ() + face.getStepZ());
			if (level.isLoaded(p1)) {
				Block block = level.getBlockState(p1).getBlock();
				if (block instanceof IClimateIgnoreBlock) {
					if (((IClimateIgnoreBlock) block).isActive(level.getBlockState(p1))) {
						continue;
					}
				}

				DCHumidity target2 = getBlockHumidity(level, pos, p1);
				if (target2 == DCHumidity.UNDERWATER) {
					hasWater = true;
				}

				FluidState f = level.getFluidState(p1);
				if (f.getType().canHydrate(f, level, p1, Blocks.FARMLAND.defaultBlockState(), p1) && f.getAmount() > 0) {
					hasWater = true;
				} else if (!level.getBlockState(p1).isFaceSturdy(level, p1, Direction.UP)) {
					hasAir++; // 3blockまでOK
				}
			}
		}

		if (hasWater && hasAir < 4) {
			return DCHumidity.UNDERWATER;
		}

		// 雨が降っている
		if (!hasRoof(level, pos)) {
			int offset = level.isRaining() && level.dimensionType().hasSkyLight() ? 1 : 0;
			ret += offset;
		}

		/*
		 * blockの値
		 */
		int h1 = h ? 0 : r;
		for (int x = pos.getX() - r; x <= pos.getX() + r; x++) {
			for (int z = pos.getZ() - r; z <= pos.getZ() + r; z++) {
				for (int y = pos.getY() - h1; y <= pos.getY() + h1; y++) {
					BlockPos p2 = new BlockPos(x, y, z);
					if (level.isLoaded(p2)) {
						BlockState st = level.getBlockState(p2);
						if (st != null && st.getBlock() instanceof IClimateIgnoreBlock) {
							if (((IClimateIgnoreBlock) st.getBlock()).isActive(st)) {
								continue;
							}
						}

						DCHumidity current = getBlockHumidity(level, pos, p2);
						if (current != null) {
							if (current == DCHumidity.DRY) {
								ret--;
							} else if (current.getID() > 1) {
								ret++;
							}
						}
					}
				}
			}
		}
		/*
		 * PosLinkTile
		 */
		BlockEntity self = level.getBlockEntity(pos);
		if (self instanceof IPosLinkTile link) {
			if (link.getLinkType() == IPosLinkTile.Type.HUM && link.getLinkPos() != null) {
				DCHumidity current = getBlockHumidity(level, pos, link.getLinkPos());
				if (current != null) {
					if (current == DCHumidity.DRY) {
						ret--;
					} else if (current.getID() > 1) {
						ret++;
					}
				}
			}
		}

		if (ret < 0) {
			return DCHumidity.DRY;
		} else {
			return ret == 0 ? DCHumidity.NORMAL : DCHumidity.WET;
		}
	}

	// Airの数をカウントして決定
	@Override
	public DCAirflow getAirflow(Level level, BlockPos pos) {
		if (level == null || pos == null || !level.isLoaded(pos)) {
			return DCAirflow.NORMAL;
		}

		// biomeの基礎通気
		DCAirflow air = ClimateAPI.registerBiome.getAirflow(level, pos);

		int count = 0; // 空気量カウント
		boolean hasWind = false;
		boolean hasBlow = false;

		// biomeベース通気 -> 屋内ではNORMALになる
		if (!hasRoof2(level, pos)) {
			if (pos.getY() > 135) {
				air = DCAirflow.WIND;
				hasWind = true;
				hasBlow = true;
			} else {
				air = DCAirflow.FLOW;
				hasWind = true;
			}
		} else if (ConfigCommonBuilder.INSTANCE.enTightDeep.get() && pos.getY() < 0) {
			air = DCAirflow.TIGHT;
		}

		/*
		 * blockの値
		 */
		for (int x = pos.getX() - 1; x <= pos.getX() + 1; x++) {
			for (int z = pos.getZ() - 1; z <= pos.getZ() + 1; z++) {
				for (int y = pos.getY() - 1; y <= pos.getY() + 1; y++) {
					BlockPos p2 = new BlockPos(x, y, z);
					if (level.isLoaded(p2)) {
						BlockState st = level.getBlockState(p2);
						if (st != null && st.getBlock() instanceof IClimateIgnoreBlock) {
							if (((IClimateIgnoreBlock) st.getBlock()).isActive(st)) {
								continue;
							}
						}

						DCAirflow current = getBlockAirflow(level, pos, p2);
						if (current == null)
							continue;
						if (current.getID() > 0) {
							if (current.getID() > 1) {
								if (current == DCAirflow.WIND) {
									hasBlow = true;
								}
								hasWind = true;
							}
							count++;
						}
					}
				}
			}
		}
		/*
		 * PosLinkTile
		 */
		BlockEntity self = level.getBlockEntity(pos);
		if (self instanceof IPosLinkTile link) {
			if (link.getLinkType() == IPosLinkTile.Type.AIR && link.getLinkPos() != null) {
				DCAirflow current = getBlockAirflow(level, pos, link.getLinkPos());
				if (current != null && current.getID() > 0) {
					air = current;
					count = 3;
				}
			}
		}

		if (hasBlow) {
			return DCAirflow.WIND;
		}
		if (count > 2) {
			DCAirflow ret = air;
			if (hasWind) {
				ret = DCAirflow.FLOW;
				if (level.isRaining() && !level.dimensionType().ultraWarm()) {
					ret = DCAirflow.getTypeByID(ret.getID() + 1);
				}
			} else {
				ret = air;
			}

			return ret;

		} else {
			return DCAirflow.TIGHT;
		}
	}

	boolean hasRoof(Level level, BlockPos pos) {
		if (!level.isLoaded(pos)) {
			return false;
		}
		BlockPos pos2 = pos.above();
		int lim = pos.getY() + 16;
		if (!level.dimensionType().hasSkyLight()) {
			lim = pos.getY() + 12;
		}
		while (pos2.getY() < lim && pos2.getY() < level.getHeight()) {
			BlockState state = level.getBlockState(pos2);
			Block block = level.getBlockState(pos2).getBlock();
			if (!level.isEmptyBlock(pos2) && state.getMaterial().blocksMotion()) {
				return true;
			}
			pos2 = pos2.above();
		}
		return false;
	}

	boolean hasRoof2(Level level, BlockPos pos) {
		if (!level.isLoaded(pos)) {
			return false;
		}
		int count = 0;
		int lim = 16;
		if (!level.dimensionType().hasSkyLight()) {
			lim = 8;
		}
		boolean end = false;
		for (int i = 1; i <= lim; i++) {
			if (pos.getY() + i > level.getHeight())
				break;

			BlockPos p2 = pos.above(i);
			BlockState state = level.getBlockState(p2);
			Block block = level.getBlockState(p2).getBlock();
			if (!level.isEmptyBlock(p2) && state.getMaterial().blocksMotion()) {
				break;
			} else {
				count++;
			}
		}
		for (int i = 0; i <= lim; i++) {
			if (pos.getY() - i < level.getMinBuildHeight())
				break;

			BlockPos p2 = pos.below(i);
			BlockState state = level.getBlockState(p2);
			Block block = level.getBlockState(p2).getBlock();
			if (!level.isEmptyBlock(p2) && state.getMaterial().blocksMotion()) {
				break;
			} else {
				count++;
			}
		}
		return count < lim;
	}

	@Override
	public DCHeatTier getBlockHeatTier(Level level, BlockPos target, BlockPos source, boolean b) {
		BlockState state = level.getBlockState(source);
		if (state != null) {
			Block block = state.getBlock();
			DCHeatTier ret = null;
			if (ClimateAPI.registerBlock.isRegisteredHeat(state)) {
				ret = ClimateAPI.registerBlock.getHeatTier(state).orElse(DCHeatTier.NORMAL);
			} else if (block instanceof IHeatTile) {
				ret = ((IHeatTile) block).getHeatTier(level, target, source);
			} else if (block instanceof IFluidBlock) {
				Fluid type = ((IFluidBlock) block).getFluid();
				if (type != null) {
					ret = DCHeatTier.getTypeByTemperature(type.getFluidType().getTemperature());
				}
			} else if (block instanceof LiquidBlock) {
				Fluid type = ((LiquidBlock) block).getFluid();
				if (type != null) {
					ret = DCHeatTier.getTypeByTemperature(type.getFluidType().getTemperature());
				}
			}

			BlockHeatTierEvent event = new BlockHeatTierEvent(level, source, ret, b);
			ret = event.result();
			return ret;
		}
		return null;

	}

	@Override
	public DCHumidity getBlockHumidity(Level Level, BlockPos target, BlockPos source) {
		BlockState state = Level.getBlockState(source);
		if (state != null) {
			Block block = state.getBlock();
			DCHumidity ret = null;
			if (block instanceof IHumidityTile) {
				ret = ((IHumidityTile) block).getHumidity(Level, target, source);
			} else if (ClimateAPI.registerBlock.isRegisteredHum(state)) {
				ret = ClimateAPI.registerBlock.getHumidity(state).orElse(DCHumidity.NORMAL);
			} else if (block instanceof IFluidBlock) {
				Fluid type = ((IFluidBlock) block).getFluid();
				if (type != null && type.getFluidType().canHydrate(type.defaultFluidState(), Level, target, Blocks.FARMLAND.defaultBlockState(), source)) {
					ret = DCHumidity.UNDERWATER;
				}
			} else if (block instanceof LiquidBlock) {
				Fluid type = ((LiquidBlock) block).getFluid();
				if (type != null && type.getFluidType().canHydrate(type.defaultFluidState(), Level, target, Blocks.FARMLAND.defaultBlockState(), source)) {
					ret = DCHumidity.UNDERWATER;
				}
			} else if (!state.getFluidState().isEmpty()) {
				FluidState stack = state.getFluidState();
				if (stack.getFluidType().canHydrate(stack, Level, target, Blocks.FARMLAND.defaultBlockState(), source))
					ret = DCHumidity.UNDERWATER;
			}
			return ret;
		}
		return DCHumidity.NORMAL;
	}

	@Override
	public DCAirflow getBlockAirflow(Level level, BlockPos target, BlockPos source) {
		BlockState state = level.getBlockState(source);
		if (state != null) {
			Block block = state.getBlock();
			DCAirflow ret = null;
			if (block == Blocks.AIR) {
				ret = DCAirflow.NORMAL;
			} else if (block instanceof IAirflowTile) {
				ret = ((IAirflowTile) block).getAirflow(level, target, source);
			} else if (ClimateAPI.registerBlock.isRegisteredAir(state)) {
				ret = ClimateAPI.registerBlock.getAirflow(state).orElse(DCAirflow.TIGHT);
			} else if (!state.getMaterial().blocksMotion() && !state.getMaterial().isLiquid()) {
				ret = DCAirflow.NORMAL;
			}
			return ret;
		}
		return DCAirflow.TIGHT;
	}

	@Override
	public BlockPos getMaxHeatPos(Level Level, BlockPos pos, int r) {
		if (Level == null || pos == null || !Level.isAreaLoaded(pos, r)) {
			return null;
		}
		BlockPos ret = null;
		DCHeatTier temp = ClimateAPI.registerBiome.getHeatTier(Level, pos);
		if (temp == null) {
			temp = DCHeatTier.NORMAL;
		}
		/*
		 * biomeの気温
		 * 屋根あり: Tierが1段階Normalに近づく
		 */
		DCHeatTier hot = temp;
		if (hasRoof(Level, pos)) {
			if (temp.getTier() < 0) {
				hot = temp.addTier(1);
			} else if (temp.getTier() > 0) {
				hot = temp.addTier(-1);
			}
		}

		/*
		 * blockの気温
		 */
		if (r < 0) {
			r = 0;
		}
		for (int x = pos.getX() - r; x <= pos.getX() + r; x++) {
			for (int z = pos.getZ() - r; z <= pos.getZ() + r; z++) {
				for (int y = pos.getY() - r; y <= pos.getY() + r; y++) {
					BlockPos up = new BlockPos(x, y + 1, z);
					BlockState upb = Level.getBlockState(up);
					if (upb != null && upb.getBlock() instanceof IHeatCanceler) {
						if (((IHeatCanceler) upb.getBlock()).isActive(upb)) {
							continue;
						}
					}

					BlockPos p2 = new BlockPos(x, y, z);
					if (Level.isLoaded(p2)) {
						DCHeatTier current = getBlockHeatTier(Level, pos, p2, true);
						if (current != null) {

							if (current.getTier() > hot.getTier()) {
								hot = current;
								ret = p2;
							} else if (current.getTier() == hot.getTier()) {
								if (ret == null || p2.distSqr(pos) < ret.distSqr(pos)) {
									hot = current;
									ret = p2;
								}
							}
						}
					}
				}
			}
		}
		return ret;
	}

	@Override
	public BlockPos getMaxColdPos(Level Level, BlockPos pos, int r) {
		if (Level == null || pos == null || !Level.isAreaLoaded(pos, r)) {
			return null;
		}
		BlockPos ret = null;
		DCHeatTier temp = ClimateAPI.registerBiome.getHeatTier(Level, pos);
		if (temp == null) {
			temp = DCHeatTier.NORMAL;
		}
		/*
		 * biomeの気温
		 * 屋根あり: Tierが1段階Normalに近づく
		 */
		DCHeatTier cold = temp;
		if (hasRoof(Level, pos)) {
			if (temp.getTier() < 0) {
				cold = cold.addTier(1);
			} else if (temp.getTier() > 0) {
				cold = cold.addTier(-1);
			}
		}

		/*
		 * blockの気温
		 */
		if (r < 0) {
			r = 0;
		}
		for (int x = pos.getX() - r; x <= pos.getX() + r; x++) {
			for (int z = pos.getZ() - r; z <= pos.getZ() + r; z++) {
				for (int y = pos.getY() - r; y <= pos.getY() + r; y++) {
					BlockPos up = new BlockPos(x, y + 1, z);
					BlockState upb = Level.getBlockState(up);
					if (upb != null && upb.getBlock() instanceof IHeatCanceler) {
						if (((IHeatCanceler) upb.getBlock()).isActive(upb)) {
							continue;
						}
					}

					BlockPos p2 = new BlockPos(x, y, z);
					if (Level.isLoaded(p2)) {
						DCHeatTier current = getBlockHeatTier(Level, pos, p2, false);
						if (current != null) {

							if (current.getTier() < cold.getTier()) {
								if (ret == null || p2.distSqr(pos) < ret.distSqr(pos)) {
									cold = current;
									ret = p2;
								}
							}
						}

					}
				}
			}
		}
		return ret;
	}
}
