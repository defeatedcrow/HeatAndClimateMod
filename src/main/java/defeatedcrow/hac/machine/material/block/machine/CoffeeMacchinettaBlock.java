package defeatedcrow.hac.machine.material.block.machine;

import javax.annotation.Nullable;

import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoffeeMacchinettaBlock extends CoffeeMakerBlock {

	protected static final VoxelShape AABB = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);

	public CoffeeMacchinettaBlock(String s) {
		super(s);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		return AABB;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new CoffeeMacchinettaTile(pos, state);
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return !level.isClientSide ? createTickerHelper(type, MachineInit.COFFEE_MACCHINETTA_TILE.get(), ProcessTileBaseDC::serverTick) : createTickerHelper(type, MachineInit.COFFEE_MACCHINETTA_TILE.get(), CoffeeMakerTile::clientTick);
	}

}
