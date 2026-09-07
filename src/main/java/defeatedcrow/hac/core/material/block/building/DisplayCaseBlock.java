package defeatedcrow.hac.core.material.block.building;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.block.ContainerTileBlock;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.MapColor;

public class DisplayCaseBlock extends ContainerTileBlock {

	final String name;

	public DisplayCaseBlock(String s) {
		super(getProp());
		name = s;
		this.registerDefaultState(this.stateDefinition.any()
		    .setValue(DCState.FACING, Direction.NORTH)
		    .setValue(DCState.LIT_LEVEL, 0)
		    .setValue(DCState.NORTH, false)
		    .setValue(DCState.SOUTH, false)
		    .setValue(DCState.EAST, false)
		    .setValue(DCState.WEST, false)
		    .setValue(DCState.UP, false)
		    .setValue(DCState.DOWN, false)
		    .setValue(WATERLOGGED, false));
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY)
		    .strength(0.1F, 540.0F)
		    .noOcclusion();
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
		int lit = DCState.getInt(state, DCState.LIT_LEVEL);
		return lit > 0 ? lit : 0;
	}

	public static void changeLitState(Level level, BlockPos pos, int lit) {
		BlockState state = level.getBlockState(pos);
		if (state.getBlock() instanceof DisplayCaseBlock && lit != DCState.getInt(state, DCState.LIT_LEVEL)) {
			level.setBlock(pos, state.setValue(DCState.LIT_LEVEL, lit), 3);
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext cont) {
		BlockGetter level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		Direction dir = cont.getHorizontalDirection()
		    .getOpposite();
		BlockPos p1 = pos.north();
		BlockPos p2 = pos.east();
		BlockPos p3 = pos.south();
		BlockPos p4 = pos.west();
		BlockPos p5 = pos.above();
		BlockPos p6 = pos.below();
		BlockState s0 = level.getBlockState(pos);
		BlockState s1 = level.getBlockState(p1);
		BlockState s2 = level.getBlockState(p2);
		BlockState s3 = level.getBlockState(p3);
		BlockState s4 = level.getBlockState(p4);
		BlockState s5 = level.getBlockState(p5);
		BlockState s6 = level.getBlockState(p6);
		return super.getStateForPlacement(cont).setValue(DCState.FACING, dir)
		    .setValue(DCState.NORTH, connectsTo(s0, s1, level, p1, Direction.SOUTH))
		    .setValue(DCState.EAST, this.connectsTo(s0, s2, level, p2, Direction.WEST))
		    .setValue(DCState.SOUTH, this.connectsTo(s0, s3, level, p3, Direction.NORTH))
		    .setValue(DCState.WEST, this.connectsTo(s0, s4, level, p4, Direction.EAST))
		    .setValue(DCState.UP, this.connectsTo(s0, s5, level, p5, Direction.DOWN))
		    .setValue(DCState.DOWN, this.connectsTo(s0, s6, level, p6, Direction.UP));
	}

	@Override
	public BlockState updateShape(BlockState s1, Direction dir, BlockState s2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		if (dir != null) {
			return s1.setValue(DCState.getFacingProperty(dir), connectsTo(s1, s2, level, pos2, dir.getOpposite()));
		}
		return super.updateShape(s1, dir, s2, level, pos, pos2);
	}

	public boolean connectsTo(BlockState state, BlockState target, BlockGetter level, BlockPos pos, Direction dir) {
		if (target != null && target.getBlock() == this && DCState.getFace(state, DCState.FACING) != null) {
			return dir.getAxis() != DCState.getFace(state, DCState.FACING)
			    .getAxis();
		}
		return false;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.FACING, DCState.LIT_LEVEL, DCState.NORTH, DCState.SOUTH, DCState.EAST, DCState.WEST, DCState.UP, DCState.DOWN, WATERLOGGED);
	}

	@Override
	public String getRegistryName() {
		return "build/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_dummy", ImmutableMap.of("all", "dcs_climate:block/build/" + name + "_item")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:block/build/" + name + "_item"));
	}

	// colord block

	@Override
	public MagicColor getAvaiableColor(ItemStack item) {
		if (DCUtil.isEmpty(item))
			return MagicColor.NONE;
		if (item.is(TagDC.ItemTag.EXTRACT_WHITE))
			return MagicColor.WHITE;
		if (item.is(TagDC.ItemTag.EXTRACT_BLACK))
			return MagicColor.BLACK;
		return MagicColor.NONE;
	}

	@Override
	public Optional<Block> getReplaceBlock(MagicColor color) {
		//		if (color.isBlack)
		//			return Optional.of(BuildInit.DISPLAY_CASE_BLACK.get());
		//		if (color.isWhite)
		//			return Optional.of(BuildInit.DISPLAY_CASE_WHITE.get());
		return Optional.empty();
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DisplayCaseTile(pos, state);
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return level.isClientSide ? null : createTickerHelper(type, BuildInit.DISPLAY_CASE_TILE.get(), DisplayCaseTile::serverTick);
	}

}
