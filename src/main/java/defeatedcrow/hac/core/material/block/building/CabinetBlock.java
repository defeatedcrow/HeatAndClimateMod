package defeatedcrow.hac.core.material.block.building;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.block.ContainerTileBlock;
import defeatedcrow.hac.core.material.block.EntityBlockDC;
import defeatedcrow.hac.core.material.block.InventoryDC;
import defeatedcrow.hac.core.material.block.OwnableContainerBaseTileDC;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class CabinetBlock extends ContainerTileBlock {

	final String name;

	public CabinetBlock(String s) {
		super(getProp());
		name = s;
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.1F, 540.0F).noOcclusion();
	}

	@Override
	public String getRegistryName() {
		return "build/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_dummy", ImmutableMap.of("all", "dcs_climate:block/build/" + name + "_item")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:block/build/" + name + "_item"));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new CabinetTile(pos, state);
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return level.isClientSide ? createTickerHelper(type, BuildInit.CABINET_TILE.get(), CabinetTile::lidAnimateTick) : null;
	}

	// drop

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		if (state.getBlock() instanceof EntityBlockDC cont && builder != null) {
			LootContext context = builder.withParameter(LootContextParams.BLOCK_STATE, state).create(LootContextParamSets.BLOCK);
			BlockEntity tile = null;
			if (context.hasParam(LootContextParams.BLOCK_ENTITY)) {
				tile = context.getParam(LootContextParams.BLOCK_ENTITY);
			}

			ret.add(getMainDrop());
			if (tile instanceof OwnableContainerBaseTileDC base) {
				// 中身をその場に散らかす
				InventoryDC inv = base.getInventory();
				ret.addAll(inv.inv);
			}
		} else {
			ret.add(getMainDrop());
		}
		return ret;
	}

}
