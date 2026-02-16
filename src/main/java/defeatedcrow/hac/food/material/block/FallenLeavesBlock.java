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
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;

public class FallenLeavesBlock extends BlockDC implements IRapidCollectables {

	final String name;
	private String domain = "food";
	protected static final VoxelShape AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 1.0D, 15.0D);

	public FallenLeavesBlock(String s) {
		super(getProp());
		name = s;
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.GRASS).instabreak().sound(SoundType.GRASS);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext col) {
		return AABB;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction face, BlockState other, LevelAccessor level, BlockPos pos, BlockPos pos2) {
		return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, face, other, level, pos, pos2);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return canSupportCenter(level, pos.below(), Direction.UP);
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
		return Lists.newArrayList();
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_thin_plate", ImmutableMap.of("all", "dcs_climate:block/tree/" + name)));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:block/tree/" + name));
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
