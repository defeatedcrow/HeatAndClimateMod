package defeatedcrow.hac.core.material.block;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

public class OreBlockGemDC extends LayerStoneBlock {

	private int toolTier = 1;
	private final Supplier<Item> drop;
	@Nullable
	private Supplier<Item> secondary;

	public OreBlockGemDC(Supplier<Item> item, String s) {
		super(s);
		drop = item;
	}

	public OreBlockGemDC setSecondary(Supplier<Item> item) {
		secondary = item;
		return this;
	}

	public OreBlockGemDC setTier(int i) {
		toolTier = i;
		return this;
	}

	@Override
	public int getToolTier() {
		return toolTier;
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(drop.get());
	}

	@Override
	public ItemStack getSilkyDrop() {
		return new ItemStack(this);
	}

	public ItemStack getSecondary() {
		return secondary == null || secondary.get() == null ? ItemStack.EMPTY : new ItemStack(secondary.get());
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, @Nullable BlockEntity tile) {
		List<ItemStack> ret = Lists.newArrayList();
		int luck = 5;
		int level = 0;
		if (!tool.isEmpty() && tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE) > 0) {
			level = tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
			luck += level * 5;
		}
		DCUtil.rand.nextInt(100);
		if (DCUtil.rand.nextInt(100) < luck) {
			int size = DCUtil.rand.nextInt(2 + Mth.floor(level / 2F));
			if (size > 0) {
				if (!getSecondary().isEmpty()) {
					ret.add(new ItemStack(getSecondary().getItem(), size));
				} else if (!getMainDrop().isEmpty()) {
					ret.add(new ItemStack(getMainDrop().getItem(), size));
				}
			}
		}

		return ret;
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
		return silkTouchLevel == 0 && getMainDrop().is(Tags.Items.GEMS) ? UniformInt.of(2, 5).sample(randomSource) : 0;
	}

}
