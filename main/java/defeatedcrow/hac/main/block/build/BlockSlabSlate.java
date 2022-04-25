package defeatedcrow.hac.main.block.build;

import net.minecraft.block.material.Material;

public class BlockSlabSlate extends BlockSlabBase {
	public BlockSlabSlate() {
		super(Material.CLAY, "dcs_build_slab_slate", 3, true);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"gray",
				"red",
				"green",
				"brown"
		};
		return name;
	}
}
