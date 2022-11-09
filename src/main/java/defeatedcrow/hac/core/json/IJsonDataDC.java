package defeatedcrow.hac.core.json;

import java.util.List;
import java.util.Optional;

public interface IJsonDataDC {

	String getRegistryName();

	List<JsonModelDC> getBlockModel();

	Optional<String[]> getModelNameSuffix();

	JsonModelSimpleDC getItemModel();

	default boolean requireStateJson() {
		return true;
	}

	BlockType getDropType();

	public static enum BlockType {
		NORMAL,
		ENTITY_NBT,
		ITEM;
	}

}
