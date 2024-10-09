package defeatedcrow.hac.machine.material.block.machine;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.network.packet.message.MsgEffectToC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class CropAspiratorBlock extends RedstoneMachineBlock {

	final String name;

	public CropAspiratorBlock(String s) {
		super(getProp());
		name = s;
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).strength(0.5F, 540.0F).noOcclusion();
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity target) {
		if (DCState.getBool(state, DCState.POWERED))
			target.hurt(DamageSource.CACTUS, 20.0F);
	}

	@Override
	public boolean triggerOn(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		Direction dir = DCState.getFace(state, DCState.DIRECTION);
		BlockEntity tile = level.getBlockEntity(pos);
		if (tile != null && tile instanceof ItemAspiratorTile aspirator) {
			Player owner = ClimateCore.proxy.getPlayer(level, aspirator.getOwnerName());
			int range = level.getBestNeighborSignal(pos);
			ItemStack held = new ItemStack(CoreInit.SCYTHE_BRASS.get());
			if (aspirator.getEnergyHandler().getEnergyStored() > 250) {
				aspirator.getEnergyHandler().consumeEnergy(250);
				MsgEffectToC.sendToClient(level, pos, 10);
				// 前方 9x9x9
				for (int y = 4; y > -4; y--) {
					BlockPos.betweenClosedStream(new AABB(pos.relative(dir, 4).above(y)).move(0.5D, 0.0D, 0.5D).inflate(4.5D, 0.0D, 4.5D))
							.filter((p) -> level.getBlockState(p) != null && !level.getBlockState(p).isAir()).forEach(p -> {
								BlockState st = level.getBlockState(p);
								if (st.getBlock() instanceof IClimateCrop crop) {
									if (crop.canHarvest(st)) {
										dropTargetItem(level, pos.relative(dir.getOpposite()), crop.getCropItems(st, 0));
										crop.afterHarvest(level, p, st);
									}
								} else if (st.getBlock() instanceof StemBlock) {

								} else if (st.is(TagDC.BlockTag.CROP_MELON) || st.is(TagDC.BlockTag.CROP_PUMPKIN)) {
									if (level.getBlockState(p.below()).getBlock() == st.getBlock()) {
										List<ItemStack> drops = ImmutableList.of(new ItemStack(st.getBlock()));
										if (dropTargetItem(level, pos.relative(dir.getOpposite()), drops)) {
											level.setBlock(p, Blocks.AIR.defaultBlockState(), 3);
										}
									}
								} else if (st.getBlock() instanceof CropBlock crop) {
									if (crop.isMaxAge(st)) {
										LootContext.Builder builder = (new LootContext.Builder(level))
												.withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(p))
												.withParameter(LootContextParams.BLOCK_STATE, st)
												.withOptionalParameter(LootContextParams.BLOCK_ENTITY, level.getBlockEntity(p))
												.withOptionalParameter(LootContextParams.THIS_ENTITY, owner)
												.withParameter(LootContextParams.TOOL, held);
										dropTargetItem(level, pos.relative(dir.getOpposite()), st.getDrops(builder));
										level.setBlock(p, crop.defaultBlockState(), 3);
									}
								} else if (st.is(TagDC.BlockTag.CROP_TALL)) {
									if (level.getBlockState(p.below()).getBlock() == st.getBlock()) {
										List<ItemStack> drops = ImmutableList.of(new ItemStack(st.getBlock()));
										dropTargetItem(level, pos.relative(dir.getOpposite()), drops);
										level.setBlock(p, Blocks.AIR.defaultBlockState(), 3);
									}
								} else if (st.getBlock() instanceof BonemealableBlock crop && st.getMaterial() != Material.GRASS) {
									if (!crop.isValidBonemealTarget(level, p, st, false)) {
										LootContext.Builder builder = (new LootContext.Builder(level))
												.withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(p))
												.withParameter(LootContextParams.BLOCK_STATE, st)
												.withOptionalParameter(LootContextParams.BLOCK_ENTITY, level.getBlockEntity(p))
												.withOptionalParameter(LootContextParams.THIS_ENTITY, owner)
												.withParameter(LootContextParams.TOOL, held);
										dropTargetItem(level, pos.relative(dir.getOpposite()), st.getDrops(builder));
										level.setBlock(p, Blocks.AIR.defaultBlockState(), 3);
									}
								}
							});
				}
			}
		}
		return true;
	}

	private boolean dropTargetItem(ServerLevel level, BlockPos pos, List<ItemStack> itemList) {
		if (itemList == null || itemList.isEmpty())
			return false;
		BlockEntity backTile = level.getBlockEntity(pos);
		boolean check = false;
		for (ItemStack target : itemList) {
			if (!DCUtil.isEmpty(target)) {
				boolean flag = backTile != null && backTile.getCapability(ForgeCapabilities.ITEM_HANDLER).map(handler -> {
					int slot = 0;
					while (slot < handler.getSlots()) {
						ItemStack ret = handler.insertItem(slot, target, false);
						if (ret.isEmpty()) {
							break;
						} else {
							slot++;
						}
					}
					return target == null || target.isEmpty();
				}).orElse(false);
				if (!flag && !level.getBlockState(pos).getMaterial().blocksMotion()) {
					ItemEntity drop = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.15D, pos.getZ() + 0.5D, target.copy());
					flag = level.addFreshEntity(drop);
				}
				if (flag) {
					check = true;
				}
			}
		}
		return check;
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
		MutableComponent tex4 = Component.translatable("dcs.tip.energy.crop_aspirator.desc").withStyle(ChatFormatting.GRAY);
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
