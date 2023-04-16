package defeatedcrow.hac.core.json;

import java.util.List;

import com.google.common.collect.Lists;

public interface IJsonDataDC {

	String getRegistryName();

	List<JsonModelDC> getBlockModel();

	List<String> getModelNameSuffix();

	default List<String> getStateNameSuffix() {
		return Lists.newArrayList();
	}

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
