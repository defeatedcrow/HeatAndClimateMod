package defeatedcrow.hac.core.material.block;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class AlloyDustBlockDC extends ClimateBlock {

	final String name;
	private String domain = "main";

	public AlloyDustBlockDC(String s) {
		super(getProp(), true);
		name = s;
	}

	public AlloyDustBlockDC setDomain(String s) {
		domain = s;
		return this;
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.METAL).randomTicks().strength(3.0F, 6.0F);
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
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity) {
		return Lists.newArrayList();
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("minecraft:block/cube_all", ImmutableMap.of("all", "dcs_climate:block/metal/" + name)));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.empty();
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
		return ToolType.PICKAXE;
	}

	@Override
	public int getToolTier() {
		return 1;
	}

}
