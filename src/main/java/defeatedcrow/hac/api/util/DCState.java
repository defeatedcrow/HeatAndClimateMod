package defeatedcrow.hac.api.util;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class DCState {
	// int系
	public static final IntegerProperty TYPE16 = IntegerProperty.create("type16", 0, 15);
	public static final IntegerProperty TYPE8 = IntegerProperty.create("type8", 0, 7);
	public static final IntegerProperty TYPE4 = IntegerProperty.create("type4", 0, 3);
	public static final IntegerProperty STAGE3 = IntegerProperty.create("stage4", 0, 2);

	// bool
	public static final BooleanProperty TOP = BooleanProperty.create("top");
	public static final BooleanProperty BOTTOM = BooleanProperty.create("bottom");
	public static final BooleanProperty FLAG = BooleanProperty.create("flag");
	public static final BooleanProperty LIT = BooleanProperty.create("lit");
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	public static final BooleanProperty LEFT = BooleanProperty.create("left");
	public static final BooleanProperty RIGHT = BooleanProperty.create("right");

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
	public static final IntegerProperty STAGE9 = IntegerProperty.create("stage9", 0, 8);
	public static final BooleanProperty DOUBLE = BooleanProperty.create("double");
	public static final BooleanProperty DEAD = BooleanProperty.create("dead");
	public static final BooleanProperty WILD = BooleanProperty.create("wild");
	public static final IntegerProperty DIST = IntegerProperty.create("dist", 0, 7);

	public static final IntegerProperty FERTILE = IntegerProperty.create("fertile", 0, 3);

	public static BooleanProperty EMPTY = BooleanProperty.create("none");

	public static BooleanProperty getFacingProperty(Direction dir) {
		switch (dir) {
		case DOWN:
			return DOWN;
		case EAST:
			return EAST;
		case NORTH:
			return NORTH;
		case SOUTH:
			return SOUTH;
		case UP:
			return UP;
		case WEST:
			return WEST;
		default:
			return NORTH;
		}
	}

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
		if (state != null && state.hasProperty(prop) && prop.getPossibleValues().contains(Integer.valueOf(i))) {
			return state.setValue(prop, i);
		} else {
			return state;
		}
	}

	public static BlockState setBool(BlockState state, BooleanProperty prop, boolean i) {
		if (state != null && state.hasProperty(prop) && prop.getPossibleValues().contains(Boolean.valueOf(i))) {
			return state.setValue(prop, i);
		} else {
			return state;
		}
	}

	public static BlockState setFace(BlockState state, DirectionProperty prop, Direction i) {
		if (state != null && state.hasProperty(prop) && prop.getPossibleValues().contains(i)) {
			return state.setValue(prop, i);
		} else {
			return state;
		}
	}
}
