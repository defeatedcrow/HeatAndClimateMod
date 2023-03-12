package defeatedcrow.hac.api.util;

import java.util.Optional;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class DCState {
	// int系
	public static final IntegerProperty TYPE16 = IntegerProperty.create("type16", 0, 15);
	public static final IntegerProperty TYPE8 = IntegerProperty.create("type8", 0, 7);
	public static final IntegerProperty TYPE4 = IntegerProperty.create("type4", 0, 3);

	// bool
	public static final BooleanProperty FLAG = BooleanProperty.create("flag");
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");

	// facing
	public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);
	public static final DirectionProperty DIRECTION = DirectionProperty.create("direction");
	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty WEST = BooleanProperty.create("west");
	public static final BooleanProperty UP = BooleanProperty.create("up");
	public static final BooleanProperty DOWN = BooleanProperty.create("down");

	// crop
	public static final IntegerProperty STAGE4 = IntegerProperty.create("stage4", 0, 3);
	public static final IntegerProperty STAGE5 = IntegerProperty.create("stage5", 0, 4);
	public static final IntegerProperty STAGE6 = IntegerProperty.create("stage6", 0, 5);
	public static final BooleanProperty DOUBLE = BooleanProperty.create("double");
	public static final BooleanProperty DEAD = BooleanProperty.create("dead");
	public static final BooleanProperty WILD = BooleanProperty.create("wild");

	public static final IntegerProperty FERTILE = IntegerProperty.create("fertile", 0, 3);

	public static BooleanProperty EMPTY = BooleanProperty.create("none");

	public static int getInt(BlockState state, IntegerProperty prop) {
		if (state != null && state.hasProperty(prop)) {
			return state.getValue(prop);
		} else {
			return -1;
		}
	}

	public static boolean getBool(BlockState state, BooleanProperty prop) {
		if (state != null && state.hasProperty(prop)) {
			return state.getValue(prop);
		} else {
			return false;
		}
	}

	public static Direction getFace(BlockState state, DirectionProperty prop) {
		if (state != null && state.hasProperty(prop)) {
			return state.getValue(prop);
		} else {
			return null;
		}
	}

	public static BlockState setInt(BlockState state, IntegerProperty prop, int i) {
		if (state != null) {
			Optional<Property<?>> target = getProp(state, prop);
			target.ifPresent(p -> {
				if (p instanceof IntegerProperty && ((IntegerProperty) p).getPossibleValues().contains(i)) {
					state.setValue((IntegerProperty) p, i);
				}
			});
		}
		return state;
	}

	public static BlockState setBool(BlockState state, BooleanProperty prop, boolean i) {
		if (state != null) {
			Optional<Property<?>> target = getProp(state, prop);
			target.ifPresent(p -> {
				if (p instanceof BooleanProperty && ((BooleanProperty) p).getPossibleValues().contains(i)) {
					state.setValue((BooleanProperty) p, i);
				}
			});
		}
		return state;
	}

	public static BlockState setFace(BlockState state, DirectionProperty prop, Direction i) {
		if (state != null) {
			Optional<Property<?>> target = getProp(state, prop);
			target.ifPresent(p -> {
				if (p instanceof DirectionProperty && ((DirectionProperty) p).getPossibleValues().contains(i)) {
					state.setValue((DirectionProperty) p, i);
				}
			});
		}
		return state;
	}

	public static Optional<Property<?>> getProp(BlockState state, Property<?> prop) {
		Optional<Property<?>> ret = state.getProperties().stream().filter(p -> p.getName().equals(prop.getName())).findAny();
		return ret;
	}
}
