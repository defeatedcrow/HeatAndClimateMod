package defeatedcrow.hac.api.magic;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.Event.HasResult;

@HasResult
public class SearchPlayerCharmEvent extends EntityEvent {
	final LivingEntity owner;
	final CharmType type;
	final ArrayList<ItemStack> charmList = new ArrayList<>();

	public SearchPlayerCharmEvent(LivingEntity ownerIn, CharmType typeIn, ArrayList<ItemStack> charmListIn) {
		super(ownerIn);
		owner = ownerIn;
		type = typeIn;
		charmList.addAll(charmListIn);
	}

	public List<ItemStack> result() {
		MinecraftForge.EVENT_BUS.post(this);
		return ImmutableList.copyOf(charmList);
	}

	public LivingEntity getOwnerEntity() {
		return owner;
	}

	public CharmType getCharmType() {
		return type;
	}

	public ArrayList<ItemStack> getCharmList() {
		return charmList;
	}

}
