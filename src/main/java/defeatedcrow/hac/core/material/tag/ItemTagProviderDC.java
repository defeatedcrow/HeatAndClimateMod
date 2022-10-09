package defeatedcrow.hac.core.material.tag;

import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagProviderDC extends ItemTagsProvider {

	public ItemTagProviderDC(DataGenerator gen, BlockTagsProvider provider, @Nullable ExistingFileHelper helper) {
		super(gen, provider, "dcs_climate", helper);
	}

	@Override
	protected void addTags() {

		copy(TagDC.BlockTag.ORES_WHITE, TagDC.ItemTag.ORES_WHITE);
		copy(TagDC.BlockTag.ORES_BLUE, TagDC.ItemTag.ORES_BLUE);
		copy(TagDC.BlockTag.ORES_BLACK, TagDC.ItemTag.ORES_BLACK);
		copy(TagDC.BlockTag.ORES_RED, TagDC.ItemTag.ORES_RED);
		copy(TagDC.BlockTag.ORES_GREEN, TagDC.ItemTag.ORES_GREEN);

		copy(TagDC.BlockTag.ORES_WHITE_DEEP, TagDC.ItemTag.ORES_WHITE_DEEP);
		copy(TagDC.BlockTag.ORES_BLUE_DEEP, TagDC.ItemTag.ORES_BLUE_DEEP);
		copy(TagDC.BlockTag.ORES_BLACK_DEEP, TagDC.ItemTag.ORES_BLACK_DEEP);
		copy(TagDC.BlockTag.ORES_RED_DEEP, TagDC.ItemTag.ORES_RED_DEEP);
		copy(TagDC.BlockTag.ORES_GREEN_DEEP, TagDC.ItemTag.ORES_GREEN_DEEP);

		copy(TagDC.BlockTag.ORES_CHALCEDONY, TagDC.ItemTag.ORES_CHALCEDONY);
		copy(TagDC.BlockTag.ORES_HELIODOR, TagDC.ItemTag.ORES_HELIODOR);
		copy(TagDC.BlockTag.ORES_TOPAZ, TagDC.ItemTag.ORES_TOPAZ);
		copy(TagDC.BlockTag.ORES_FLUORITE, TagDC.ItemTag.ORES_FLUORITE);
		copy(TagDC.BlockTag.ORES_LARIMAR, TagDC.ItemTag.ORES_LARIMAR);
		copy(TagDC.BlockTag.ORES_AQUAMARINE, TagDC.ItemTag.ORES_AQUAMARINE);
		copy(TagDC.BlockTag.ORES_JET, TagDC.ItemTag.GEM_JET);
		copy(TagDC.BlockTag.ORES_IOLITE, TagDC.ItemTag.ORES_IOLITE);
		copy(TagDC.BlockTag.ORES_OPAL, TagDC.ItemTag.ORES_OPAL);
		copy(TagDC.BlockTag.ORES_DRAGONSEYE, TagDC.ItemTag.ORES_DRAGONSEYE);
		copy(TagDC.BlockTag.ORES_DESERTROSE, TagDC.ItemTag.ORES_DESERTROSE);
		copy(TagDC.BlockTag.ORES_ROSINCA, TagDC.ItemTag.ORES_ROSINCA);
		copy(TagDC.BlockTag.ORES_SPINEL, TagDC.ItemTag.ORES_SPINEL);
		copy(TagDC.BlockTag.ORES_SERPENTINE, TagDC.ItemTag.ORES_SERPENTINE);
		copy(TagDC.BlockTag.ORES_AMAZONITE, TagDC.ItemTag.ORES_AMAZONITE);
		copy(TagDC.BlockTag.ORES_JADEITE, TagDC.ItemTag.ORES_JADEITE);
		copy(TagDC.BlockTag.ORES_SALT, TagDC.ItemTag.ORES_SALT);
		copy(TagDC.BlockTag.ORES_GUANO, TagDC.ItemTag.ORES_GUANO);
		copy(TagDC.BlockTag.ORES_SULFUR, TagDC.ItemTag.ORES_SULFUR);

		tag(Tags.Items.RAW_MATERIALS_COPPER).add(CoreInit.OREITEM_WHITE1.get());
		tag(Tags.Items.RAW_MATERIALS_GOLD).add(CoreInit.OREITEM_WHITE2.get());
		tag(TagDC.ItemTag.RAW_MOLYBDENUM).add(CoreInit.OREITEM_WHITE3.get());
		tag(TagDC.ItemTag.RAW_ZINC).add(CoreInit.OREITEM_BLUE1.get());
		tag(TagDC.ItemTag.RAW_BISMUTH).add(CoreInit.OREITEM_BLUE2.get());
		tag(TagDC.ItemTag.RAW_COBALT).add(CoreInit.OREITEM_BLUE3.get());
		tag(Tags.Items.RAW_MATERIALS_IRON).add(CoreInit.OREITEM_BLACK1.get());
		tag(TagDC.ItemTag.RAW_MAGNETITE).add(CoreInit.OREITEM_BLACK1.get());
		tag(TagDC.ItemTag.RAW_SILVER).add(CoreInit.OREITEM_BLACK2.get());
		tag(TagDC.ItemTag.RAW_TITANIUM).add(CoreInit.OREITEM_BLACK3.get());
		tag(Tags.Items.RAW_MATERIALS_IRON).add(CoreInit.OREITEM_RED1.get());
		tag(TagDC.ItemTag.RAW_ALUMINUM).add(CoreInit.OREITEM_RED2.get());
		tag(TagDC.ItemTag.RAW_MANGANESE).add(CoreInit.OREITEM_RED3.get());
		tag(TagDC.ItemTag.RAW_TIN).add(CoreInit.OREITEM_GREEN1.get());
		tag(TagDC.ItemTag.RAW_NICKEL).add(CoreInit.OREITEM_GREEN2.get());
		tag(TagDC.ItemTag.RAW_CHROMIUM).add(CoreInit.OREITEM_GREEN3.get());

		tag(TagDC.ItemTag.GEM_CHALCEDONY).add(CoreInit.GEM_CHALCEDONY.get());
		tag(TagDC.ItemTag.GEM_CRYSTAL).add(CoreInit.GEM_CRYSTAL.get());
		tag(TagDC.ItemTag.GEM_HELIODOR).add(CoreInit.GEM_HELIODOR.get());
		tag(TagDC.ItemTag.GEM_THUNDEREGG).add(CoreInit.GEM_THUNDEREGG.get());
		tag(TagDC.ItemTag.GEM_TOPAZ).add(CoreInit.GEM_TOPAZ.get());
		tag(TagDC.ItemTag.GEM_FLUORITE).add(CoreInit.GEM_FLUORITE.get());
		tag(TagDC.ItemTag.GEM_LARIMAR).add(CoreInit.GEM_LARIMAR.get());
		tag(TagDC.ItemTag.GEM_CELESTITE).add(CoreInit.GEM_CELESTITE.get());
		tag(TagDC.ItemTag.GEM_AQUAMARINE).add(CoreInit.GEM_AQUAMARINE.get());
		tag(TagDC.ItemTag.GEM_SAPPHIRE).add(CoreInit.GEM_SAPPHIRE.get());
		tag(TagDC.ItemTag.GEM_JET).add(CoreInit.GEM_JET.get());
		tag(TagDC.ItemTag.GEM_VIVIANITE).add(CoreInit.GEM_VIVIANITE.get());
		tag(TagDC.ItemTag.GEM_IOLITE).add(CoreInit.GEM_IOLITE.get());
		tag(TagDC.ItemTag.GEM_FANG).add(CoreInit.GEM_FANG.get());
		tag(TagDC.ItemTag.GEM_OPAL).add(CoreInit.GEM_OPAL.get());
		tag(TagDC.ItemTag.GEM_DRAGONSEYE).add(CoreInit.GEM_DRAGONSEYE.get());
		tag(TagDC.ItemTag.GEM_DESERTROSE).add(CoreInit.GEM_DESERTROSE.get());
		tag(TagDC.ItemTag.GEM_JASPER).add(CoreInit.GEM_JASPER.get());
		tag(TagDC.ItemTag.GEM_ROSINCA).add(CoreInit.GEM_ROSINCA.get());
		tag(TagDC.ItemTag.GEM_ALMANDINE).add(CoreInit.GEM_ALMANDINE.get());
		tag(TagDC.ItemTag.GEM_SPINEL).add(CoreInit.GEM_SPINEL.get());
		tag(TagDC.ItemTag.GEM_RUBY).add(CoreInit.GEM_RUBY.get());
		tag(TagDC.ItemTag.GEM_SERPENTINE).add(CoreInit.GEM_SERPENTINE.get());
		tag(TagDC.ItemTag.GEM_MALACHITE).add(CoreInit.GEM_MALACHITE.get());
		tag(TagDC.ItemTag.GEM_AMAZONITE).add(CoreInit.GEM_AMAZONITE.get());
		tag(TagDC.ItemTag.GEM_OLIVINE).add(CoreInit.GEM_OLIVINE.get());
		tag(TagDC.ItemTag.GEM_JADEITE).add(CoreInit.GEM_JADEITE.get());

		tag(TagDC.ItemTag.GEM_GARNET).addTag(TagDC.ItemTag.GEM_ALMANDINE);
		tag(TagDC.ItemTag.GEM_PERIDOT).addTag(TagDC.ItemTag.GEM_OLIVINE);

		tag(TagDC.ItemTag.FERTILIZER).add(Items.BONE_MEAL);

	}

}
