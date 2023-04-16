package defeatedcrow.hac.api.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class BlockSet {

	private final Block block;
	private final String propertyName;
	private final List<String> valueName;

	public BlockSet(Block b, String prop, List<String> val) {
		block = b;
		propertyName = prop;
		valueName = Lists.newArrayList();
		if (val != null) {
			valueName.addAll(val);
		}
	}

	public BlockSet(Block b) {
		block = b;
		propertyName = "none";
		valueName = Arrays.asList("none");
	}

	public Block getBlock() {
		return block;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public List<String> getValueName() {
		return valueName;
	}

	public boolean hasNoProperty() {
		return propertyName == null || propertyName.equals("none");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (!(obj instanceof BlockSet)) {
			return false;
		} else {
			BlockSet set = (BlockSet) obj;
			return set.getBlock() == block && set.getPropertyName().equals(propertyName) && Objects.equal(set.getValueName(), valueName);
		}
	}

	public boolean match(BlockState state) {
		if (state == null || state.getBlock() != getBlock())
			return false;
		if (hasNoProperty())
			// Property不問
			return true;
		if (!getValueName().isEmpty()) {
			Property<?> prop = getProp(state);
			for (String name : getValueName()) {
				return prop.getValue(name).stream().anyMatch(val -> val.equals(state.getValue(prop)));
			}
		}
		return false;
	}

	private static BooleanProperty EMPTY = BooleanProperty.create("none");

	private Property<?> getProp(BlockState state) {
		Optional<Property<?>> prop = state.getProperties().stream().filter(p -> p.getName().equals(getPropertyName())).findAny();
		return prop.orElse(DCState.EMPTY);
	}
}
