package defeatedcrow.hac.machine.block;

public class TileHopperGold extends TileHopperFilter {

	@Override
	public int getCoolTime() {
		return 0;
	}

	@Override
	protected boolean suctionDrop() {
		return false;
	}

	@Override
	public boolean isFilterd() {
		return false;
	}

}
