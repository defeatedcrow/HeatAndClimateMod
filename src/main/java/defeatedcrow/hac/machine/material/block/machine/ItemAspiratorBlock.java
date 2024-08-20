package defeatedcrow.hac.machine.material.block.machine;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class ItemAspiratorBlock extends RedstoneMachineBlock {

	final String name;

	public ItemAspiratorBlock(String s) {
		super(getProp());
		name = s;
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).strength(0.5F, 540.0F).noOcclusion();
	}

	@Override
	public boolean triggerOn(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		Direction dir = DCState.getFace(state, DCState.DIRECTION);
		BlockEntity tile = level.getBlockEntity(pos);
		if (tile != null && tile instanceof ItemAspiratorTile aspirator) {
			int range = level.getBestNeighborSignal(pos);
			if (aspirator.getEnergyHandler().getEnergyStored() > 250) {
				aspirator.getEnergyHandler().consumeEnergy(250);
				// 前方 9x9x9
				List<ItemEntity> itemList = level.getEntitiesOfClass(ItemEntity.class, new AABB(pos.relative(dir, 4)).inflate(4.0D), EntitySelector.ENTITY_STILL_ALIVE);
				if (!itemList.isEmpty()) {
					DCLogger.debugInfoLog("dir " + dir);
					DCLogger.debugInfoLog("list " + itemList.size());
					aspirator.getEnergyHandler().consumeEnergy(250);

					if (!itemList.isEmpty()) {
						DCLogger.debugInfoLog("list " + itemList.size());
						BlockEntity backTile = level.getBlockEntity(pos.relative(dir.getOpposite()));
						for (ItemEntity target : itemList) {
							if (target != null && target.isAlive() && !DCUtil.isEmpty(target.getItem())) {
								boolean flag = backTile != null && backTile.getCapability(ForgeCapabilities.ITEM_HANDLER).map(handler -> {
									ItemStack copy = target.getItem().copy();
									for (int j = 0; j < handler.getSlots(); j++) {
										ItemStack ret = handler.insertItem(j, copy.copy(), true);
										if (DCUtil.isEmpty(ret) || ret.getCount() != copy.getCount()) {
											ret = handler.insertItem(j, copy, false);
											target.getItem().setCount(ret.getCount());
											backTile.setChanged();
											if (target.getItem().isEmpty()) {
												target.discard();
												break;
											}
										}
									}
									return target == null || !target.isAlive() || target.getItem().isEmpty();
								}).orElse(false);
								if (!flag && !level.getBlockState(pos.relative(dir.getOpposite())).getMaterial().blocksMotion()) {
									target.moveTo(Vec3.atCenterOf(pos.relative(dir.getOpposite())));
								}
							}
						}
						itemList.clear();
						return true;
					}
				}
			}
		}
		return false;
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
		return new ItemAspiratorTile(pos, state);
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
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
		MutableComponent tex1 = Component.translatable("dcs.tip.energy.machine").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD);
		MutableComponent tex2 = Component.translatable("dcs.tip.energy.machine.desc");
		MutableComponent tex3 = Component.translatable("dcs.tip.energy.rs_signal_machine");
		MutableComponent tex4 = Component.translatable("dcs.tip.energy.item_aspirator.desc").withStyle(ChatFormatting.GRAY);
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

}
