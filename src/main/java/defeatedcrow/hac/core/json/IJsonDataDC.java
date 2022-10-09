package defeatedcrow.hac.core.json;

import java.util.List;
import java.util.Optional;

public interface IJsonDataDC {

	String getRegistryName();

	List<JsonModelDC> getBlockModel();

	Optional<String[]> getModelNameSuffix();

	JsonModelDC getItemModel();

	BlockType getDropType();

	public static enum BlockType {
		NORMAL,
		ENTITY_NBT,
		ITEM;
	}

}
