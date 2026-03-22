package defeatedcrow.hac.magic.material.block;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.building.SmallLight;
import net.minecraft.core.Direction;

public class MagicSmallLight extends SmallLight {

	public MagicSmallLight() {
		super("magic");
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.DIRECTION, Direction.DOWN).setValue(WATERLOGGED, false));
	}

	@Override
	public String getRegistryName() {
		return "magic/magic_small_light";
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/magic/magic_small_light");
	}

}
