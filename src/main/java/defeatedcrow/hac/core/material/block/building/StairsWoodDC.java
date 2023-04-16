package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.IBlockDC;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.registries.RegistryObject;

public class StairsWoodDC extends StairBlock implements IBlockDC, IJsonDataDC {

	final String name;
	private String texDir = "tree/plank_";

	public StairsWoodDC(String n, Properties prop, RegistryObject<Block> baseBlock) {
		super(() -> baseBlock.get().defaultBlockState(), prop);
		name = n;
	}

	public StairsWoodDC setTexDir(String s) {
		texDir = s;
		return this;
	}

	@Override
	public String getRegistryName() {
		return "build/stairs_" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_stairs_normal", ImmutableMap.of("all", "dcs_climate:block/" + texDir + name)),
			new JsonModelDC("dcs_climate:block/dcs_stairs_inner", ImmutableMap.of("all", "dcs_climate:block/" + texDir + name)),
			new JsonModelDC("dcs_climate:block/dcs_stairs_outer", ImmutableMap.of("all", "dcs_climate:block/" + texDir + name)));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("normal", "inner", "outer");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_normal");
	}

	@Override
	public boolean requireStateJson() {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		if (state.getBlock() instanceof IBlockDC) {
			IBlockDC block = (IBlockDC) state.getBlock();
			ServerLevel level = builder.getLevel();
			Entity breaker = builder.getParameter(LootContextParams.THIS_ENTITY);
			ItemStack tool = builder.getParameter(LootContextParams.TOOL);
			// シルクタッチの場合は処理を中段
			if (!block.getSilkyDrop().isEmpty() && !tool.isEmpty() && tool.getEnchantmentLevel(Enchantments.SILK_TOUCH) > 0) {
				ret.add(getSilkyDrop());
				return ret;
			}

			ret.addAll(super.getDrops(state, builder));

			ret.addAll(getAdditionalDrop(state, tool, breaker));
		}
		return ret;
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
		List<ItemStack> ret = Lists.newArrayList();
		return ret;
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
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
