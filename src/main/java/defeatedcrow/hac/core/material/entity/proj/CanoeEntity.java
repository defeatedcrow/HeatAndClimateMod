package defeatedcrow.hac.core.material.entity.proj;

import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class CanoeEntity extends Boat {

	public CanoeEntity(EntityType<? extends Boat> type, Level level) {
		super(type, level);
	}

	public CanoeEntity(Level level, double x, double y, double z) {
		this(CoreInit.CANOE.get(), level);
		this.setPos(x, y, z);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	@Override
	public Item getDropItem() {
		return Items.OAK_BOAT;
	}

}
