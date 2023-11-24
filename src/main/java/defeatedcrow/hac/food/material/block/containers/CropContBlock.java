package defeatedcrow.hac.food.material.block.containers;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.material.IRapidCollectables;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.ClimateBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.Tags;

public class CropContBlock extends ClimateBlock implements IRapidCollectables {

	final String name;
	private String domain;

	public CropContBlock(String d, String s) {
		super(getProp(), false);
		name = s;
		domain = d;
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(1.0F, 8.0F).sound(SoundType.WOOD).randomTicks();
	}

	/* BlockDC */

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
		return Lists.newArrayList();
	}

	@Override
	public String getRegistryName() {
		return "container/" + domain + "_" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		String tex = "dcs_climate:block/container/" + domain;
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_container", ImmutableMap.of("top", tex + "_t_" + name, "bottom", tex + "_b", "side", tex + "_s")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of();
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of();
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
		return ImmutableList.of(new ItemStack(this));
	}

	@Override
	public boolean doCollect(Level level, BlockPos pos, BlockState state, @Nullable Player player, ItemStack tool) {
		ItemEntity drop;
		ItemStack ret = new ItemStack(this);
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
