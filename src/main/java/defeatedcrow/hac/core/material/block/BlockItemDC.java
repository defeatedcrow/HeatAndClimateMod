package defeatedcrow.hac.core.material.block;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.material.item.IItemDC;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public abstract class BlockItemDC extends BlockItem implements IJsonDataDC, IItemDC {

	public BlockItemDC(Block block, Properties prop) {
		super(block, prop);
	}

	@Override
	public abstract String getRegistryName();

	@Override
	public List<JsonModelDC> getBlockModel() {
		return Lists.newArrayList();
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.empty();
	}

	@Override
	public abstract JsonModelDC getItemModel();

	@Override
	public BlockType getDropType() {
		return BlockType.ITEM;
	}

}
