package defeatedcrow.hac.machine.material.block;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.util.DCState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 汎用マシンベースクラス<br>
 * - Owner登録あり、開閉アニメーションなし<br>
 * - 内容のTick同期あり<br>
 */
public abstract class EnergyMachineBaseDC extends EnergyTileBaseDC {

	public EnergyMachineBaseDC(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	// menu

	public int lastRate = 0;
	public int currentRate = 0;

	public final ContainerData baseDataAccess = new ContainerData() {
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return EnergyMachineBaseDC.this.currentRate;
			case 1:
				return EnergyMachineBaseDC.this.lastRate;
			case 2:
				return EnergyMachineBaseDC.this.getEnergyHandler().getFace(Direction.UP).getID();
			case 3:
				return EnergyMachineBaseDC.this.getEnergyHandler().getFace(EnergyMachineBaseDC.this.getFront()).getID();
			case 4:
				return EnergyMachineBaseDC.this.getEnergyHandler().getFace(EnergyMachineBaseDC.this.getBack()).getID();
			case 5:
				return EnergyMachineBaseDC.this.getEnergyHandler().getFace(EnergyMachineBaseDC.this.getRight()).getID();
			case 6:
				return EnergyMachineBaseDC.this.getEnergyHandler().getFace(EnergyMachineBaseDC.this.getLeft()).getID();
			case 7:
				return EnergyMachineBaseDC.this.getEnergyHandler().getFace(Direction.DOWN).getID();
			case 8:
				return EnergyMachineBaseDC.this.getEnergyHandler().getEnergyStored();
			default:
				return 0;
			}
		}

		@Override
		public void set(int id, int data) {
			switch (id) {
			case 0:
				EnergyMachineBaseDC.this.currentRate = data;
				break;
			case 1:
				EnergyMachineBaseDC.this.lastRate = data;
				break;
			case 2:
				EnergyMachineBaseDC.this.getEnergyHandler().setFace(Direction.UP, FaceIO.getIO(data));
				break;
			case 3:
				EnergyMachineBaseDC.this.getEnergyHandler().setFace(EnergyMachineBaseDC.this.getFront(), FaceIO.getIO(data));
				break;
			case 4:
				EnergyMachineBaseDC.this.getEnergyHandler().setFace(EnergyMachineBaseDC.this.getBack(), FaceIO.getIO(data));
				break;
			case 5:
				EnergyMachineBaseDC.this.getEnergyHandler().setFace(EnergyMachineBaseDC.this.getRight(), FaceIO.getIO(data));
				break;
			case 6:
				EnergyMachineBaseDC.this.getEnergyHandler().setFace(EnergyMachineBaseDC.this.getLeft(), FaceIO.getIO(data));
				break;
			case 7:
				EnergyMachineBaseDC.this.getEnergyHandler().setFace(Direction.DOWN, FaceIO.getIO(data));
				break;
			case 8:
				EnergyMachineBaseDC.this.clientEnergy = data;
				break;
			}

		}

		@Override
		public int getCount() {
			return 9;
		}
	};

	// client side gui data
	public int clientEnergy = 0;

	// tick
	public static void serverTick(Level level, BlockPos pos, BlockState state, EnergyMachineBaseDC tile) {
		tile.onTickProcess(level, pos, state);
	}

	@Override
	public boolean isActive(Level level, BlockPos pos, BlockState state) {
		return !DCState.getBool(state, DCState.FLAG) && !DCState.getBool(state, DCState.POWERED);
	}

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		super.onTickProcess(level, pos, state);

		if (state.getBlock() instanceof EnergyMachineBlock) {
			int m = getStoredEnergyLevel();
			EnergyMachineBlock.changeLitState(getLevel(), getBlockPos(), m);
		}

		return false;
	}

	public Direction getFront() {
		Direction front = DCState.getFace(getBlockState(), DCState.FACING);
		return front == null ? Direction.NORTH : front;
	}

	public Direction getBack() {
		return Rotation.CLOCKWISE_180.rotate(getFront());
	}

	public Direction getRight() {
		return Rotation.CLOCKWISE_90.rotate(getFront());
	}

	public Direction getLeft() {
		return Rotation.COUNTERCLOCKWISE_90.rotate(getFront());
	}

}
