package defeatedcrow.hac.core.material.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public abstract class BlockDC extends Block implements IBlockDC, IJsonDataDC {

	public BlockDC(BlockBehaviour.Properties prop) {
		super(prop);
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
				BlockEntity tile = null;
				ItemStack tool = ItemStack.EMPTY;
				if (cont.hasParam(LootContextParams.TOOL) && !DCUtil.isEmpty(cont.getParamOrNull(LootContextParams.TOOL))) {
					tool = cont.getParam(LootContextParams.TOOL);
				}
				if (cont.hasParam(LootContextParams.THIS_ENTITY)) {
					breaker = cont.getParamOrNull(LootContextParams.THIS_ENTITY);
				}
				if (cont.hasParam(LootContextParams.BLOCK_ENTITY)) {
					tile = cont.getParam(LootContextParams.BLOCK_ENTITY);
				}
				// シルクタッチの場合は処理を中断
				if (!block.getSilkyDrop().isEmpty() && tool.getEnchantmentLevel(Enchantments.SILK_TOUCH) > 0) {
					ret.add(getSilkyDrop());
					return ret;
				}
				ret.addAll(getAdditionalDrop(state, tool, breaker, tile));
			}
			ret.addAll(super.getDrops(state, builder));
		}
		return ret;
	}

	public void advTooltipText(ItemStack item, @Nullable Level level, List<Component> list) {}

}
