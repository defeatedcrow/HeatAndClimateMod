package defeatedcrow.hac.core.tag;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BiomeTagProviderDC extends BiomeTagsProvider {

	public BiomeTagProviderDC(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, @Nullable ExistingFileHelper helper) {
		super(output, lookup, "dcs_climate", helper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider provider) {

		tag(TagDC.BiomeTag.WHITE_BIOME).addTags(Tags.Biomes.IS_PLAINS, Tags.Biomes.IS_SPARSE, BiomeTags.IS_SAVANNA);
		tag(TagDC.BiomeTag.BLUE_BIOME).addTags(Tags.Biomes.IS_COLD, BiomeTags.IS_TAIGA, BiomeTags.IS_HILL, Tags.Biomes.IS_CONIFEROUS);
		tag(TagDC.BiomeTag.BLACK_BIOME).addTags(Tags.Biomes.IS_SWAMP, Tags.Biomes.IS_SPOOKY, Tags.Biomes.IS_WATER, BiomeTags.IS_NETHER);
		tag(TagDC.BiomeTag.RED_BIOME).addTags(Tags.Biomes.IS_SANDY, Tags.Biomes.IS_DRY, Tags.Biomes.IS_HOT, BiomeTags.IS_MOUNTAIN, BiomeTags.IS_BADLANDS);
		tag(TagDC.BiomeTag.GREEN_BIOME).addTags(Tags.Biomes.IS_DENSE, BiomeTags.IS_FOREST, BiomeTags.IS_JUNGLE, Tags.Biomes.IS_LUSH);

		tag(TagDC.BiomeTag.GEN).addTags(BiomeTags.IS_OVERWORLD, BiomeTags.IS_NETHER);

	}

}
