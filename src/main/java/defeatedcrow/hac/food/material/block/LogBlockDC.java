package defeatedcrow.hac.food.material.block;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.IBlockDC;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class LogBlockDC extends RotatedPillarBlock implements IBlockDC, IJsonDataDC {

	final CropTier tier;
	final CropType type;

	public LogBlockDC(CropType typeIn, CropTier tierIn) {
		super(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F).sound(SoundType.WOOD));
		type = typeIn;
		tier = tierIn;
	}

	@Override
	public String getRegistryName() {
		return "food/log_" + type.toString() + "_" + getSpeciesName(tier);
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
			new JsonModelDC("minecraft:block/cube_column", ImmutableMap.of("end", "dcs_climate:block/tree/" + type.toString() + "_" + getSpeciesName(tier) + "_log_top",
				"side", "dcs_climate:block/tree/" + type.toString() + "_" + getSpeciesName(tier) + "_log_side")),
			new JsonModelDC("minecraft:block/cube_column_horizontal", ImmutableMap.of("end", "dcs_climate:block/tree/" + type.toString() + "_" + getSpeciesName(tier) + "_log_top",
				"side", "dcs_climate:block/tree/" + type.toString() + "_" + getSpeciesName(tier) + "_log_side")));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.of(new String[] { "var", "hol" });
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_var");
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(this);
	}

	@Override
	public ItemStack getSilkyDrop() {
		return null;
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity) {
		return Lists.newArrayList();
	}

	@Override
	public ToolType getToolType() {
		return ToolType.AXE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "walnut";
		if (tier == CropTier.RARE)
			return "sweet";
		return "common";
	}

}
