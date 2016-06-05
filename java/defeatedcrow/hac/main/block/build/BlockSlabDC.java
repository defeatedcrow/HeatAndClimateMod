package defeatedcrow.hac.main.block.build;

import net.minecraft.block.material.Material;

public class BlockSlabDC extends BlockSlabBase {

	public BlockSlabDC() {
		super(Material.GLASS, "dcs_build_slab", 1, true);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"gypsum",
				"glass" };
		return name;
	}

}
