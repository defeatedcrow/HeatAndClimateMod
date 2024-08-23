package defeatedcrow.hac.machine.material.block.transport;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec3;

public class ConveyorSmeltingBlock extends ConveyorBlockBase {

	final String name;

	public ConveyorSmeltingBlock(String s) {
		super(getProp());
		name = s;
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(2.0F, 540.0F).noOcclusion();
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		BlockState blockstate = level.getBlockState(pos.above());
		Vec3 vec3 = entity.getDeltaMovement();
		double dx = vec3.x;
		double dy = vec3.y;
		double dz = vec3.z;
		Direction dir = DCState.getFace(state, DCState.FACING);
		if (dir.getAxis() == Direction.Axis.X) {
			dx = Math.min(0.5D, dx + (0.06D * dir.getStepX()));
			dz *= 0.5D;
		} else {
			dz = Math.min(0.5D, dz + (0.06D * dir.getStepZ()));
			dx *= 0.5D;
		}

		entity.setDeltaMovement(dx, dy, dz);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ConveyorSmeltingTile(pos, state);
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
		return !level.isClientSide ? createTickerHelper(type, MachineInit.CONVEYOR_SMELTING_TILE.get(), ConveyorTile::serverTick) :
				createTickerHelper(type, MachineInit.CONVEYOR_SMELTING_TILE.get(), ConveyorTile::clientTick);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
		MutableComponent tex1 = Component.translatable("dcs.tip.conveyor.transport").withStyle(ChatFormatting.YELLOW);
		MutableComponent tex2 = Component.translatable("dcs.tip.conveyor.smelt").withStyle(ChatFormatting.YELLOW);
		MutableComponent tex3 = Component.translatable("dcs.tip.conveyor.smelt.desc").withStyle(ChatFormatting.GRAY);
		if (ClimateCore.proxy.keyShiftPushed()) {
			list.add(tex1);
			list.add(tex2);
			list.add(tex3);
		} else {
			list.add(tex1);
			list.add(tex2);
		}
		super.appendHoverText(stack, level, list, flag);
	}

}
