package defeatedcrow.hac.core.tag;

import org.jetbrains.annotations.Nullable;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BiomeTagProviderDC extends BiomeTagsProvider {

	public BiomeTagProviderDC(DataGenerator gen, @Nullable ExistingFileHelper helper) {
		super(gen, "dcs_climate", helper);
	}

	@Override
	protected void addTags() {

		tag(TagDC.BiomeTag.WHITE_BIOME).addTags(Tags.Biomes.IS_PLAINS, BiomeTags.IS_SAVANNA);
		tag(TagDC.BiomeTag.BLUE_BIOME).addTags(Tags.Biomes.IS_COLD, BiomeTags.IS_TAIGA, BiomeTags.IS_HILL);
		tag(TagDC.BiomeTag.BLACK_BIOME).addTags(Tags.Biomes.IS_SWAMP, Tags.Biomes.IS_SPOOKY, Tags.Biomes.IS_WATER);
		tag(TagDC.BiomeTag.RED_BIOME).addTags(Tags.Biomes.IS_SANDY, BiomeTags.IS_MOUNTAIN, BiomeTags.IS_BADLANDS, Tags.Biomes.IS_UNDERGROUND);
		tag(TagDC.BiomeTag.GREEN_BIOME).addTags(Tags.Biomes.IS_DENSE, BiomeTags.IS_FOREST, BiomeTags.IS_JUNGLE, Tags.Biomes.IS_LUSH);

		tag(TagDC.BiomeTag.GEN).addTags(BiomeTags.IS_OVERWORLD, BiomeTags.IS_NETHER);
	}

}
