package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.IBlockDC;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class DoorWoodDC extends DoorBlock implements IBlockDC, IJsonDataDC {

	final String name;
	private String texDir = "build/door_";

	public DoorWoodDC(String n) {
		super(BlockBehaviour.Properties.of(Material.WOOD, Blocks.OAK_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion());
		name = n;
	}

	public DoorWoodDC setTexDir(String s) {
		texDir = s;
		return this;
	}

	@Override
	public String getRegistryName() {
		return texDir + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/door_bottom_left", ImmutableMap.of("bottom", "dcs_climate:block/" + texDir + name + "_bottom", "top",
				"dcs_climate:block/" + texDir + name + "_top")),
				new JsonModelDC("dcs_climate:block/door_bottom_left_open", ImmutableMap.of("bottom", "dcs_climate:block/" + texDir + name + "_bottom", "top",
						"dcs_climate:block/" + texDir + name + "_top")),
				new JsonModelDC("dcs_climate:block/door_bottom_right", ImmutableMap.of("bottom", "dcs_climate:block/" + texDir + name + "_bottom", "top",
						"dcs_climate:block/" + texDir + name + "_top")),
				new JsonModelDC("dcs_climate:block/door_bottom_right_open", ImmutableMap.of("bottom", "dcs_climate:block/" + texDir + name + "_bottom", "top",
						"dcs_climate:block/" + texDir + name + "_top")),
				new JsonModelDC("dcs_climate:block/door_top_left", ImmutableMap.of("bottom", "dcs_climate:block/" + texDir + name + "_bottom", "top", "dcs_climate:block/" + texDir + name + "_top")),
				new JsonModelDC("dcs_climate:block/door_top_left_open", ImmutableMap.of("bottom", "dcs_climate:block/" + texDir + name + "_bottom", "top",
						"dcs_climate:block/" + texDir + name + "_top")),
				new JsonModelDC("dcs_climate:block/door_top_right", ImmutableMap.of("bottom", "dcs_climate:block/" + texDir + name + "_bottom", "top", "dcs_climate:block/" + texDir + name + "_top")),
				new JsonModelDC("dcs_climate:block/door_top_right_open", ImmutableMap.of("bottom", "dcs_climate:block/" + texDir + name + "_bottom", "top",
						"dcs_climate:block/" + texDir + name + "_top")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("bottom_left", "bottom_left_open", "bottom_right", "bottom_right_open", "top_left", "top_left_open", "top_right", "top_right_open");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:block/build/door_" + name + "_item"));
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
		return BlockType.ITEM;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.AXE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

}
