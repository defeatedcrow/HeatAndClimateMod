package defeatedcrow.hac.machine.block;

public class TileHopperFilterG extends TileHopperFilter {

	@Override
	public int getCoolTime() {
		return 0;
	}

	@Override
	protected boolean suctionDrop() {
		return false;
	}

}
