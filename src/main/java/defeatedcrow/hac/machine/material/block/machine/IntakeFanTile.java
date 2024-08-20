package defeatedcrow.hac.machine.material.block.machine;

import defeatedcrow.hac.api.machine.IFluidPipe;
import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IPosLinkTile;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.machine.energy.SidedEnergyReceiver;
import defeatedcrow.hac.machine.energy.SidedEnergyTankDC;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class IntakeFanTile extends EnergyMachineBaseDC implements IRenderBlockData {

	public IntakeFanTile(BlockPos pos, BlockState state) {
		super(MachineInit.INTAKE_FAN_TILE.get(), pos, state);
	}

	public float lastRotation = 0F;
	public float rotation = 0F;
	public float speed = 0F;
	public float lastSpeed = 0F;

	public static void clientTick(Level level, BlockPos pos, BlockState state, IntakeFanTile tile) {
		tile.lastSpeed = tile.speed;
		tile.lastRotation = tile.rotation;
		if (tile.isActive(level, pos, state) && DCState.getInt(state, DCState.STAGE9) > 0) {
			tile.speed += 4.0F;
		} else {
			tile.speed -= 4.0F;
		}
		tile.speed = Mth.clamp(tile.speed, 0F, 60F);
		tile.rotation += tile.lastSpeed;
		if (tile.rotation > 360F) {
			tile.rotation -= 360F;
			tile.lastRotation -= 360F;
		}
	}

	int count = 7;

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
		} else {
			Direction dir = DCState.getFace(state, DCState.FACING);
			if (this.isActive(level, pos, state) && getEnergyHandler().getEnergyStored() > 0) {
				BlockPos backPos = pos.relative(dir.getOpposite());

				// 背後のTileに加圧空気を送る
				BlockEntity backEntity = getLevel().getBlockEntity(backPos);
				FluidStack air = new FluidStack(CoreInit.AIR.getStillFluid().get(), 200);
				boolean flag = false;
				if (getLevel().getBlockState(backPos).getMaterial() == Material.AIR) {
					flag = true;
				} else if (backEntity != null) {
					flag = backEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, dir.getOpposite()).map(handler -> {
						if (handler instanceof IFluidPipe sided) {
							if (sided.getFace(dir.getOpposite()).canReceive()) {
								return sided.fill(air, FluidAction.EXECUTE, dir.getOpposite()) > 0;
							}
						} else if (handler != null) {
							return handler.fill(air, FluidAction.EXECUTE) > 0;
						}
						return false;
					}).orElse(false);
				}
				if (flag) {
					getEnergyHandler().consumeEnergy(4);
					// マシン前方の走査 5x5x5
					BlockPos.betweenClosedStream(new AABB(pos.relative(dir, 2).below(2)).inflate(2.0D))
							.filter((p) -> {
								BlockEntity e = getLevel().getBlockEntity(p);
								return e instanceof IPosLinkTile && ((IPosLinkTile) e).getLinkType() == IPosLinkTile.Type.AIR;
							})
							.map(p -> (IPosLinkTile) getLevel().getBlockEntity(p))
							.forEach(tile -> tile.setLinkPos(pos));
				}
			}

			BlockPos front = pos.relative(dir);
			BlockPos back = pos.relative(dir.getOpposite());
			boolean b1 = level.getBlockState(front).getMaterial() == Material.AIR;
			boolean b2 = level.getBlockState(back).getMaterial() == Material.AIR
					|| (level.getBlockEntity(back) != null
							&& level.getBlockEntity(back).getCapability(ForgeCapabilities.FLUID_HANDLER).map(handler -> {
								return handler.fill(new FluidStack(CoreInit.AIR.getStillFluid().get(), 1000), FluidAction.SIMULATE) > 0;
							}).orElse(false));
			IntakeFanBlock.changePowerState(level, pos, !b1 || !b2);

			count = 7;
		}
		return super.onTickProcess(level, pos, state);
	}

	public SidedEnergyTankDC battery = new SidedEnergyReceiver(this, getMaxEnergy(), 128);

	protected int getMaxEnergy() {
		return 4000;
	}

	@Override
	public SidedEnergyTankDC getEnergyHandler() {
		return battery;
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return null;
	}

	@Override
	public boolean hasMenu() {
		return false;
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.machine.with_owner", this.ownerName) : Component.translatable("dcs.container.machine");
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		return NORMAL;
	}

	public static final EntityRenderData NORMAL = new EntityRenderData("tile/intake_fan", 1F, -0.5F);

}
