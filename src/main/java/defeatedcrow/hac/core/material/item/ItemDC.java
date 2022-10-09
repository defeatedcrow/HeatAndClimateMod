package defeatedcrow.hac.core.material.item;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import net.minecraft.world.item.Item;

public abstract class ItemDC extends Item implements IJsonDataDC, IItemDC {

	public ItemDC(Properties prop) {
		super(prop);
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
