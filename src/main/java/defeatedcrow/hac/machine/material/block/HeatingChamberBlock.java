package defeatedcrow.hac.machine.material.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;

public class HeatingChamberBlock extends HeatSourceBlock {

	final String name;

	public HeatingChamberBlock(String s) {
		super(getProp());
		name = s;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		BlockEntity tile = level.getBlockEntity(pos);
		if (tile instanceof HeatingChamberTile t) {
			if (ClimateCore.isDebug) {
				DCLogger.debugInfoLog("### Chamber output: " + t.output.name() + " ###");
				DCLogger.debugInfoLog("### Chamber fire: " + t.currentProgress + "/" + t.totalProgress + " ###");
				DCLogger.debugInfoLog("### Chamber airflow: " + t.resultClimate().getAirflow() + " ###");
			}
		}
		return super.use(state, level, pos, player, hand, hitRes);
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).strength(0.1F, 540.0F).noOcclusion();
	}

	@Override
	public ToolType getToolType() {
		return ToolType.PICKAXE;
	}

	@Override
	public int getToolTier() {
		return 1;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new HeatingChamberTile(pos, state);
	}

	@Override
	public String getRegistryName() {
		return "machine/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_dummy", ImmutableMap.of("all", "dcs_climate:block/machine/" + name + "_item")));
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
	public boolean requireStateJson() {
		return false;
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:block/machine/" + name + "_item"));
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return !level.isClientSide ? createTickerHelper(type, MachineInit.CHAMBER_IRON_TILE.get(), ProcessTileBaseDC::serverTick) : null;
	}

}
