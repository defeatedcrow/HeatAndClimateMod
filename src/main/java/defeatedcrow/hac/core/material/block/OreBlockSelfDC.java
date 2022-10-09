package defeatedcrow.hac.core.material.block;

public class OreBlockSelfDC extends LayerStoneBlock {

	private int toolTier = 1;

	public OreBlockSelfDC(String s) {
		super(s);
	}

	public OreBlockSelfDC setTier(int i) {
		return this;
	}

	@Override
	public int getToolTier() {
		return toolTier;
	}

}
