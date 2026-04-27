package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.api.util.DCState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SidedLightGlass extends SidedLightDC {

	protected static final VoxelShape N_AABB2 = Block.box(3.0D, 4.0D, 0.0D, 13.0D, 13.0D, 13.0D);
	protected static final VoxelShape S_AABB2 = Block.box(3.0D, 4.0D, 3.0D, 13.0D, 13.0D, 16.0D);
	protected static final VoxelShape E_AABB2 = Block.box(3.0D, 4.0D, 3.0D, 16.0D, 13.0D, 13.0D);
	protected static final VoxelShape W_AABB2 = Block.box(0.0D, 4.0D, 3.0D, 13.0D, 13.0D, 13.0D);
	protected static final VoxelShape U_AABB2 = Block.box(3.0D, 7.0D, 3.0D, 13.0D, 16.0D, 13.0D);
	protected static final VoxelShape D_AABB2 = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 13.0D, 13.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public SidedLightGlass(String s) {
		super(s);
	}

	public SidedLightGlass(BlockBehaviour.Properties prop, String s) {
		super(prop, s);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		Direction dir = DCState.getFace(state, DCState.DIRECTION);
		return switch (dir) {
		case NORTH -> N_AABB2;
		case SOUTH -> S_AABB2;
		case EAST -> E_AABB2;
		case WEST -> W_AABB2;
		case UP -> U_AABB2;
		default -> D_AABB2;
		};
	}

}
