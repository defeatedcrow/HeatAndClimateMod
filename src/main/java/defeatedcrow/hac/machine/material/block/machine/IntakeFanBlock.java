package defeatedcrow.hac.machine.material.block.machine;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.IAirflowTile;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IntakeFanBlock extends EnergyMachineBlock implements IAirflowTile {

	protected static final VoxelShape N_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape S_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape E_AABB = Block.box(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape W_AABB = Block.box(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

	final String name;

	public IntakeFanBlock(String s) {
		super(getProp());
		name = s;
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).strength(0.5F, 540.0F).noOcclusion();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		Direction dir = DCState.getFace(state, DCState.FACING);
		switch (dir) {
		case NORTH:
			return S_AABB;
		case SOUTH:
			return N_AABB;
		case EAST:
			return W_AABB;
		case WEST:
			return E_AABB;
		default:
			return S_AABB;
		}
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.PICKAXE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new IntakeFanTile(pos, state);
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
		return true;
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:block/machine/" + name + "_item"));
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return !level.isClientSide ? createTickerHelper(type, MachineInit.INTAKE_FAN_TILE.get(), EnergyMachineBaseDC::serverTick) : createTickerHelper(type, MachineInit.INTAKE_FAN_TILE.get(),
				IntakeFanTile::clientTick);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
		MutableComponent tex1 = Component.translatable("dcs.tip.energy.machine").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD);
		MutableComponent tex2 = Component.translatable("dcs.tip.energy.machine.desc");
		MutableComponent tex3 = Component.translatable("dcs.tip.energy.intake_fan.desc").withStyle(ChatFormatting.GRAY);
		MutableComponent tex4 = Component.translatable("dcs.tip.energy.intake_fan.desc2").withStyle(ChatFormatting.GRAY);
		if (ClimateCore.proxy.keyShiftPushed()) {
			list.add(tex1);
			list.add(tex2);
			list.add(tex3);
			list.add(tex4);
		} else {
			list.add(tex1);
			list.add(Component.translatable("dcs.tip.shift"));
		}
		super.appendHoverText(stack, level, list, flag);
	}

	@Override
	public DCAirflow getAirflow(Level level, BlockPos targrt, BlockPos source) {
		BlockState state = level.getBlockState(source);
		if (!DCState.getBool(state, DCState.FLAG) && !DCState.getBool(state, DCState.POWERED)) {
			return DCAirflow.FLOW;
		}
		return DCAirflow.NORMAL;
	}

}
