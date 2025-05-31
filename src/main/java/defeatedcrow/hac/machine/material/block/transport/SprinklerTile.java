package defeatedcrow.hac.machine.material.block.transport;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.block.OwnableBaseTileDC;
import defeatedcrow.hac.core.network.packet.message.IIntReceiver;
import defeatedcrow.hac.core.network.packet.message.MsgTileSimpleIntegerToC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCFluidUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class SprinklerTile extends OwnableBaseTileDC implements IIntReceiver {

	public SprinklerTile(BlockPos pos, BlockState state) {
		super(MachineInit.SPRINKLER_TILE.get(), pos, state);
	}

	public SprinklerTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	// tick
	public static void serverTick(Level level, BlockPos pos, BlockState state, SprinklerTile tile) {
		tile.onTickProcess(level, pos, state);
	}

	public boolean isActive(Level level, BlockPos pos, BlockState state) {
		return !DCState.getBool(state, DCState.POWERED) && !DCState.getBool(state, BlockStateProperties.WATERLOGGED);
	}

	public int power = 0;
	public int lastPower = 0;
	int count = 4;

	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
		} else {
			count = 9;
			if (this.isActive(level, pos, state)) {
				// 耕地をぬらす
				if (power > 0) {
					double r = 2D + power * 2;
					BlockPos.betweenClosedStream(new AABB(getBlockPos().below(2)).inflate(r, 1, r))
							.filter(p -> {
								return DCUtil.canEditPos(getLevel(), p);
							})
							.filter(p -> {
								return getLevel().getBlockState(p).is(TagDC.BlockTag.FARMLAND);
							}).forEach((p) -> {
								BlockState farmland = getLevel().getBlockState(p);
								if (farmland.getProperties().contains(BlockStateProperties.MOISTURE)) {
									BlockState next = farmland.setValue(BlockStateProperties.MOISTURE, Integer.valueOf(7));
									level.setBlock(p, next, 2);
								}
							});
				}

				// 水量チェック
				BlockPos p2 = this.getBlockPos().below();
				BlockEntity targetEntity = getLevel().getBlockEntity(p2);
				power = 0;
				if (targetEntity != null) {
					targetEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, Direction.UP).ifPresent(handler -> {
						if (handler != null) {
							// 抜き取りモード
							FluidStack drain = handler.drain(32, FluidAction.SIMULATE).copy();
							if (!drain.isEmpty() && drain.getFluid() == Fluids.WATER && drain.getAmount() == 32) {
								power = DCFluidUtil.getHead(drain) + 1;
								handler.drain(32, FluidAction.EXECUTE);
							}
						}
					});
				} else if (!getLevel().getFluidState(p2).isEmpty() && (getLevel().getFluidState(p2).is(Fluids.WATER) || getLevel().getFluidState(p2).is(Fluids.FLOWING_WATER))) {
					power = 1;
				}
			} else {
				power = 0;
			}

			if (lastPower != power) {
				lastPower = power;
				if (level instanceof ServerLevel)
					MsgTileSimpleIntegerToC.sendToClient((ServerLevel) level, pos, power);
			}
		}
		return false;
	}

	public static void clientTick(Level level, BlockPos pos, BlockState state, SprinklerTile tile) {
		tile.onClientProcess(level, pos, state);
	}

	public float rad = 0F;

	public boolean onClientProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
		} else {
			count = 4;
			if (this.isActive(level, pos, state) && power > 0) {
				rad += 0.2F;
				if (rad > 6F)
					rad -= 6F;
				double r = Math.toRadians(rad);
				int count = 5;
				for (int i = 0; i < 5; i++) {
					double p = 0.2D * power * (0.5D + getLevel().getRandom().nextDouble());
					double dx = p * Math.sin(rad);
					double dy = 0D;
					double dz = p * Math.cos(rad);
					Vec3 vec = Vec3.atBottomCenterOf(pos.above());
					getLevel().addParticle(ParticleTypes.SPLASH, vec.x, vec.y + 0.125D, vec.z, dx, dy, dz);
					getLevel().addParticle(ParticleTypes.SPLASH, vec.x, vec.y + 0.125D, vec.z, -dx, dy, -dz);
				}
			}
		}
		return false;

	}

	@Override
	public void receiveInteger(int i) {
		power = i;
	}

	// nbt

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		power = tag.getInt("dcs.power");
		lastPower = tag.getInt("dcs.last_power");

	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);

		tag.putInt("dcs.power", power);
		tag.putInt("dcs.last_power", lastPower);
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.sprinkler.with_owner", this.ownerName) : Component.translatable("dcs.container.sprinkler");
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return null;
	}

	// cap

	protected FluidDummy tank = new FluidDummy();

	LazyOptional<? extends IFluidHandler> fluidhandler = LazyOptional.of(() -> tank);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && capability == ForgeCapabilities.FLUID_HANDLER) {
			if (facing == Direction.DOWN)
				return fluidhandler.cast();
			else
				return LazyOptional.empty();
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		fluidhandler.invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		this.fluidhandler = LazyOptional.of(() -> tank);
	}

	public static class FluidDummy implements IFluidHandler {

		protected FluidDummy() {}

		@Override
		public int getTanks() {
			return 1;
		}

		@Override
		public @NotNull FluidStack getFluidInTank(int tank) {
			return FluidStack.EMPTY;
		}

		@Override
		public int getTankCapacity(int tank) {
			return 1000;
		}

		@Override
		public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
			return false;
		}

		@Override
		public int fill(FluidStack get, FluidAction action) {
			return 0;
		}

		@Override
		public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {
			return FluidStack.EMPTY;
		}

		@Override
		public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
			return FluidStack.EMPTY;
		}

	}

}
