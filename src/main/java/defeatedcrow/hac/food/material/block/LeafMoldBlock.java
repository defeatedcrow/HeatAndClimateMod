package defeatedcrow.hac.food.material.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.material.IRapidCollectables;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.BlockDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.Tags;

public class LeafMoldBlock extends BlockDC implements IRapidCollectables {

	final String name;
	private String domain = "container";

	public LeafMoldBlock(String s) {
		super(getProp());
		name = s;
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.GRASS).instabreak().sound(SoundType.GRASS);
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(FoodInit.FOOD_LEAF_MOLD.get());
	}

	@Override
	public ItemStack getSilkyDrop() {
		return new ItemStack(this);
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, @Nullable BlockEntity tile) {
		List<ItemStack> ret = Lists.newArrayList();
		int luck = 2;
		int l = 0;
		if (!tool.isEmpty() && tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE) > 0) {
			l = tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
			luck += l;
		}
		ret.add(new ItemStack(FoodInit.FOOD_LEAF_MOLD.get(), 1 + DCUtil.rand.nextInt(luck)));
		return ret;
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("minecraft:block/cube_all", ImmutableMap.of("all", "dcs_climate:block/container/" + name)));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_0");
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.SHOVEL;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	/* IRapidCollectables */

	@Override
	public TagKey<Item> collectableToolTag() {
		return Tags.Items.TOOLS_SHOVELS;
	}

	@Override
	public List<ItemStack> getDropList(Level level, BlockPos pos, BlockState state, ItemStack tool) {
		List<ItemStack> ret = Lists.newArrayList();
		int luck = 2;
		int l = 0;
		if (!tool.isEmpty() && tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE) > 0) {
			l = tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
			luck += l;
		}
		ret.add(new ItemStack(FoodInit.FOOD_LEAF_MOLD.get(), 2 + DCUtil.rand.nextInt(luck)));
		return ret;
	}

	@Override
	public boolean doCollect(Level level, BlockPos pos, BlockState state, @Nullable Player player, ItemStack tool) {
		ItemEntity drop;
		int luck = 2;
		int l = 0;
		if (!tool.isEmpty() && tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE) > 0) {
			l = tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
			luck += l;
		}
		ItemStack ret = new ItemStack(FoodInit.FOOD_LEAF_MOLD.get(), 2 + DCUtil.rand.nextInt(luck));
		if (player != null) {
			drop = new ItemEntity(level, player.getX(), player.getY() + 0.15D, player.getZ(), ret);
		} else {
			drop = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.15D, pos.getZ() + 0.5D, ret);
		}
		if (drop != null && !level.isClientSide) {
			level.addFreshEntity(drop);
			level.removeBlock(pos, false);
		}
		return true;
	}

}
