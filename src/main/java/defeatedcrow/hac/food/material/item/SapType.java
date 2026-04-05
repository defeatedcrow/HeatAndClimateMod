package defeatedcrow.hac.food.material.item;

import java.util.function.Supplier;

import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public enum SapType implements StringRepresentable {

	SWEET(0, "sweet", FoodInit.SAP_SWEET, TagDC.BlockTag.LOG_SWEET), RESIN(1, "resin", FoodInit.SAP_RESIN, TagDC.BlockTag.LOG_RESIN), LATEX(2, "latex", FoodInit.SAP_LATEX, TagDC.BlockTag.LOG_LATEX), LACQUER(3, "lacquer", FoodInit.SAP_SUMAC,
	    TagDC.BlockTag.LOG_LACQUER);

	private final int id;
	private final Supplier<Item> drop;
	private final TagKey<Block> tag;
	private final String typeName;

	private SapType(int i, String n, Supplier<Item> d, TagKey<Block> t) {
		id = i;
		typeName = n;
		drop = d;
		tag = t;
	}

	public static SapType[] VALUES = { SWEET, RESIN, LATEX, LACQUER };

	public Item getDrop() {
		return drop.get();
	}

	public TagKey<Block> getTag() {
		return tag;
	}

	public static SapType getFromID(int id) {
		for (SapType type : VALUES) {
			if (id == type.id)
				return type;
		}
		return null;
	}

	public static SapType getFromName(String name) {
		for (SapType type : VALUES) {
			if (name.equalsIgnoreCase(type.typeName))
				return type;
		}
		return null;
	}

	public static SapType getFromTag(TagKey<Block> t) {
		for (SapType type : VALUES) {
			if (t.location().equals(type.tag.location()))
				return type;
		}
		return null;
	}

	public static SapType getFromItem(Item i) {
		for (SapType type : VALUES) {
			if (i == type.getDrop())
				return type;
		}
		return null;
	}

	@Override
	public String getSerializedName() {
		return typeName;
	}

	public static final EnumProperty<SapType> TYPE = EnumProperty.create("sap_type", SapType.class);

	public static SapType getFromState(BlockState state) {
		if (state != null && state.hasProperty(TYPE)) {
			return state.getValue(TYPE);
		} else {
			return null;
		}
	}
}
