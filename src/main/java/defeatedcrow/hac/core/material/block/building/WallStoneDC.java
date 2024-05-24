package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.material.block.IBlockDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class WallStoneDC extends WallBlock implements IBlockDC, IJsonDataDC {

	final String name;
	private String texDir = "ore/stone_";

	public WallStoneDC(String n) {
		super(BlockBehaviour.Properties.copy(Blocks.STONE));
		name = n;
	}

	public WallStoneDC(String n, boolean noOcclusion) {
		super(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion());
		name = n;
	}

	public WallStoneDC setTexDir(String s) {
		texDir = s;
		return this;
	}

	@Override
	public String getRegistryName() {
		return "build/wall_" + name;
	}

	@Override
	public boolean requireStateJson() {
		return false;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("minecraft:block/template_wall_post", ImmutableMap.of("wall", "dcs_climate:block/" + texDir + name)),
				new JsonModelDC("minecraft:block/template_wall_side", ImmutableMap.of("wall", "dcs_climate:block/" + texDir + name)),
				new JsonModelDC("minecraft:block/template_wall_side_tall", ImmutableMap.of("wall", "dcs_climate:block/" + texDir + name)));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("post", "side", "side_tall");
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:block/wall_inventory", ImmutableMap.of("wall", "dcs_climate:block/" + texDir + name));
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		if (state == null || builder == null) {
			ret.addAll(super.getDrops(state, builder));
		} else {
			if (state.getBlock() instanceof IBlockDC) {
				LootContext cont = builder.withParameter(LootContextParams.BLOCK_STATE, state).create(LootContextParamSets.BLOCK);
				IBlockDC block = (IBlockDC) state.getBlock();
				Entity breaker = null;
				ItemStack tool = ItemStack.EMPTY;
				if (cont.hasParam(LootContextParams.TOOL) && !DCUtil.isEmpty(cont.getParamOrNull(LootContextParams.TOOL))) {
					tool = cont.getParam(LootContextParams.TOOL);
				}
				if (cont.hasParam(LootContextParams.THIS_ENTITY)) {
					breaker = cont.getParamOrNull(LootContextParams.THIS_ENTITY);
				}
				// シルクタッチの場合は処理を中断
				if (!block.getSilkyDrop().isEmpty() && tool.getEnchantmentLevel(Enchantments.SILK_TOUCH) > 0) {
					ret.add(getSilkyDrop());
					return ret;
				}
				ret.addAll(getAdditionalDrop(state, tool, breaker, null));
			}
			ret.addAll(super.getDrops(state, builder));
		}
		return ret;
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(this);
	}

	@Override
	public ItemStack getSilkyDrop() {
		return ItemStack.EMPTY;
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, @Nullable BlockEntity tile) {
		List<ItemStack> ret = Lists.newArrayList();
		return ret;
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.PICKAXE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

}
