package defeatedcrow.hac.main.block.build;

import net.minecraft.block.material.Material;

public class BlockSlabDC extends BlockSlabBase {

	public BlockSlabDC() {
		super(Material.GLASS, "dcs_build_slab", 5, true);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"gypsum",
				"glass",
				"marble",
				"serpentine",
				"bedrock",
				"dirtbrick"
		};
		return name;
	}

}
