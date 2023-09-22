package defeatedcrow.hac.core.material.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public abstract class BlockDC extends Block implements IBlockDC, IJsonDataDC {

	public BlockDC(BlockBehaviour.Properties prop) {
		super(prop);
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		if (state.getBlock() instanceof IBlockDC) {
			IBlockDC block = (IBlockDC) state.getBlock();
			ServerLevel level = builder.getLevel();
			Entity breaker = builder.getOptionalParameter(LootContextParams.THIS_ENTITY);
			ItemStack tool = builder.getOptionalParameter(LootContextParams.TOOL);
			if (DCUtil.isEmpty(tool)) {

			}
			// シルクタッチの場合は処理を中段
			if (!block.getSilkyDrop().isEmpty() && !DCUtil.isEmpty(tool) && tool.getEnchantmentLevel(Enchantments.SILK_TOUCH) > 0) {
				ret.add(getSilkyDrop());
				return ret;
			}

			ret.addAll(super.getDrops(state, builder));

			ret.addAll(getAdditionalDrop(state, tool, breaker));
		}
		return ret;
	}

	public void advTooltipText(ItemStack item, @Nullable Level level, List<Component> list) {}

}
