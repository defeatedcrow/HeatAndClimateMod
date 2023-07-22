package defeatedcrow.hac.core.material.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

public class OreBlockGemDC extends LayerStoneBlock {

	private int toolTier = 1;
	private final RegistryObject<Item> drop;
	@Nullable
	private RegistryObject<Item> secondary;

	public OreBlockGemDC(RegistryObject<Item> item, String s) {
		super(s);
		drop = item;
	}

	public OreBlockGemDC setSecondary(RegistryObject<Item> item) {
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
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity) {
		List<ItemStack> ret = Lists.newArrayList();
		int luck = 5;
		int level = 0;
		if (!tool.isEmpty() && tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE) > 0) {
			level = tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
			luck += level * 5;
		}
		entity.level.random.nextInt(100);
		if (entity.level.random.nextInt(100) < luck) {
			int size = entity.level.random.nextInt(2 + Mth.floor(level / 2F));
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

}
