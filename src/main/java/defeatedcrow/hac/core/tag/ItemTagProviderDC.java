package defeatedcrow.hac.core.tag;

import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.food.material.item.ItemFoodDC;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ItemTagProviderDC extends ItemTagsProvider {

	public ItemTagProviderDC(DataGenerator gen, BlockTagsProvider provider, @Nullable ExistingFileHelper helper) {
		super(gen, provider, "dcs_climate", helper);
	}

	@Override
	protected void addTags() {

		CoreInit.ITEMS.getEntries().stream().filter(item -> item.get() instanceof IItemDC && ((IItemDC) item.get()).getPairTag() != null)
			.map(RegistryObject::get).forEach(i -> {
				IItemDC item = (IItemDC) i;
				tag(item.getPairTag()).add(i);

				if (TagUtil.isMatch("raw_materials", item.getPairTag())) {
					tag(Tags.Items.RAW_MATERIALS).addTag(item.getPairTag());
				}

				if (TagUtil.isMatch("ingots", item.getPairTag())) {
					tag(Tags.Items.INGOTS).addTag(item.getPairTag());
				}

				if (TagUtil.isMatch("dusts", item.getPairTag())) {
					tag(Tags.Items.DUSTS).addTag(item.getPairTag());
				}

				if (TagUtil.isMatch("gems", item.getPairTag())) {
					tag(Tags.Items.GEMS).addTag(item.getPairTag());
				}

				if (TagUtil.isMatch("crops", item.getPairTag())) {
					tag(Tags.Items.CROPS).addTag(item.getPairTag());
				}

				if (TagUtil.isMatch("seeds", item.getPairTag())) {
					tag(Tags.Items.SEEDS).addTag(item.getPairTag());
				}

				if (item instanceof ItemFoodDC && ((ItemFoodDC) item).isRawFood()) {
					tag(TagDC.ItemTag.RAW_FOOD).add(i);
				}
			});

		tag(TagDC.ItemTag.DUST_IRON).addTag(TagDC.ItemTag.DUST_MAGNETITE);

		tag(TagDC.ItemTag.GEM_GARNET).addTag(TagDC.ItemTag.GEM_ALMANDINE);
		tag(TagDC.ItemTag.GEM_PERIDOT).addTag(TagDC.ItemTag.GEM_OLIVINE);

		tag(TagDC.ItemTag.GEM_COAL).add(Items.COAL);
		tag(TagDC.ItemTag.GEM_FLINT).add(Items.FLINT);

		tag(TagDC.ItemTag.FERTILIZER).add(Items.BONE_MEAL);
		tag(TagDC.ItemTag.FERTILIZER).addTags(TagDC.ItemTag.FOOD_FISH_POWDER, TagDC.ItemTag.FOOD_PRESS_CAKE);

		// foods

		tag(TagDC.ItemTag.CROP_APPLE).add(Items.APPLE);
		tag(TagDC.ItemTag.CROP_COCOA).add(Items.COCOA_BEANS);
		tag(TagDC.ItemTag.FOOD_BREAD).add(Items.BREAD);

		tag(TagDC.ItemTag.CROP_CEREALS).addTags(TagDC.ItemTag.CROP_OAT, TagDC.ItemTag.CROP_RYE, TagDC.ItemTag.CROP_BARLEY,
			TagDC.ItemTag.CROP_WILD_RICE, TagDC.ItemTag.CROP_RICE, TagDC.ItemTag.CROP_AROMA_RICE, Tags.Items.CROPS_WHEAT);

		tag(TagDC.ItemTag.CROP_MILLETS).addTags(TagDC.ItemTag.CROP_GOOSEFOOT, TagDC.ItemTag.CROP_SORGHUM);

		tag(TagDC.ItemTag.CROP_PSEUDOCEREALS).addTags(TagDC.ItemTag.CROP_BUCKWHEAT);

		tag(TagDC.ItemTag.CROP_GREEN_LEAFS).addTags(TagDC.ItemTag.CROP_GOOSEFOOT, TagDC.ItemTag.CROP_SPINACH, TagDC.ItemTag.CROP_NAPA,
			TagDC.ItemTag.CROP_RAPESEED, TagDC.ItemTag.CROP_CABAGGE, TagDC.ItemTag.CROP_FENNEL, TagDC.ItemTag.CROP_CELERY,
			TagDC.ItemTag.CROP_SORREL, TagDC.ItemTag.CROP_INDIGO, TagDC.ItemTag.CROP_JUTE, TagDC.ItemTag.CROP_WATER_SPINACH);

		tag(TagDC.ItemTag.CROP_BEANS).addTags(TagDC.ItemTag.CROP_GREEN_PEAS, TagDC.ItemTag.CROP_GARBANZO,
			TagDC.ItemTag.CROP_SOY, TagDC.ItemTag.CROP_ADZUKI);

		tag(TagDC.ItemTag.CROP_SPICES).addTags(TagDC.ItemTag.CROP_CHIVES, TagDC.ItemTag.CROP_GARLIC,
			TagDC.ItemTag.CROP_CHILI, TagDC.ItemTag.CROP_PAPRIKA, TagDC.ItemTag.CROP_GINGER, TagDC.ItemTag.CROP_CARDAMOM,
			TagDC.ItemTag.CROP_TURMERIC, TagDC.ItemTag.CROP_CINNAMON);

		tag(TagDC.ItemTag.CROP_VEGETABLES).addTags(TagDC.ItemTag.CROP_CHIVES, TagDC.ItemTag.CROP_ONION, TagDC.ItemTag.CROP_GARLIC,
			TagDC.ItemTag.CROP_GOOSEFOOT, TagDC.ItemTag.CROP_GLASSWORT, TagDC.ItemTag.CROP_SPINACH,
			TagDC.ItemTag.CROP_FENNEL, TagDC.ItemTag.CROP_CELERY, TagDC.ItemTag.CROP_PARSNIP,
			TagDC.ItemTag.CROP_RAPESEED, TagDC.ItemTag.CROP_NAPA, TagDC.ItemTag.CROP_CABAGGE, TagDC.ItemTag.CROP_RADISH,
			TagDC.ItemTag.CROP_PAPRIKA, TagDC.ItemTag.CROP_BELL, TagDC.ItemTag.CROP_SORREL, TagDC.ItemTag.CROP_INDIGO,
			TagDC.ItemTag.CROP_WATER_SPINACH, TagDC.ItemTag.CROP_SWEET_POTATO, TagDC.ItemTag.CROP_GREEN_PEAS,
			TagDC.ItemTag.CROP_GARBANZO, TagDC.ItemTag.CROP_SOY, TagDC.ItemTag.CROP_ADZUKI, TagDC.ItemTag.CROP_CORN,
			TagDC.ItemTag.CROP_WILD_RICE, TagDC.ItemTag.CROP_EGGPLANT, TagDC.ItemTag.CROP_TOMATO, TagDC.ItemTag.CROP_JUTE,
			Tags.Items.CROPS_CARROT, Tags.Items.CROPS_BEETROOT, Tags.Items.CROPS_POTATO, TagDC.ItemTag.CROP_PUMPKIN);

		tag(TagDC.ItemTag.CROP_FLOWERS).addTags(TagDC.ItemTag.CROP_LAVENDER, TagDC.ItemTag.CROP_BINDWEED, TagDC.ItemTag.CROP_MORNING_GLORY,
			TagDC.ItemTag.CROP_LANTERN, TagDC.ItemTag.CROP_BLUE_MALLOW, TagDC.ItemTag.CROP_OSUMANTHUS);

		tag(TagDC.ItemTag.CROP_FLUITS).addTags(TagDC.ItemTag.CROP_LANTERN, TagDC.ItemTag.CROP_CHERRY, TagDC.ItemTag.CROP_PLUM,
			TagDC.ItemTag.CROP_PEACH, TagDC.ItemTag.CROP_POMELO, TagDC.ItemTag.CROP_MANDARIN,
			TagDC.ItemTag.CROP_LEMON, TagDC.ItemTag.CROP_DATE);

		tag(TagDC.ItemTag.CROP_STRAWS).addTags(TagDC.ItemTag.CROP_OAT, TagDC.ItemTag.CROP_RYE, TagDC.ItemTag.CROP_BARLEY,
			TagDC.ItemTag.CROP_REED, TagDC.ItemTag.CROP_SORGHUM, TagDC.ItemTag.CROP_CORN,
			TagDC.ItemTag.CROP_WILD_RICE, TagDC.ItemTag.CROP_RICE, TagDC.ItemTag.CROP_AROMA_RICE);

		tag(TagDC.ItemTag.CROP_STICKS).addTags(TagDC.ItemTag.CROP_GOOSEFOOT, TagDC.ItemTag.CROP_REED, TagDC.ItemTag.CROP_SORGHUM);

		tag(TagDC.ItemTag.CROP_OILS).addTags(TagDC.ItemTag.CROP_RAPESEED, TagDC.ItemTag.CROP_SOY, TagDC.ItemTag.CROP_AVOCADO, TagDC.ItemTag.CROP_OLIVE, TagDC.ItemTag.CROP_OIL_PALM);

		tag(TagDC.ItemTag.FOOD_BREAD_GRAINS).addTags(TagDC.ItemTag.FOOD_RYE, TagDC.ItemTag.FOOD_BARLEY, TagDC.ItemTag.FOOD_WHEAT);

		tag(TagDC.ItemTag.FOOD_CEREALS).addTags(TagDC.ItemTag.FOOD_OAT, TagDC.ItemTag.FOOD_RYE, TagDC.ItemTag.FOOD_BARLEY,
			TagDC.ItemTag.FOOD_ZIZANIA, TagDC.ItemTag.FOOD_RICE, TagDC.ItemTag.FOOD_AROMA_RICE, TagDC.ItemTag.FOOD_WHEAT);

		tag(TagDC.ItemTag.FOOD_MILLETS).addTags(TagDC.ItemTag.FOOD_SORGHUM);

		tag(TagDC.ItemTag.FOOD_PSEUDOCEREALS).addTags(TagDC.ItemTag.FOOD_BUCKWHEAT);

		tag(TagDC.ItemTag.FOOD_RICES).addTags(TagDC.ItemTag.FOOD_ZIZANIA, TagDC.ItemTag.FOOD_RICE, TagDC.ItemTag.FOOD_AROMA_RICE);

		tag(TagDC.ItemTag.FOOD_NUTS).addTags(TagDC.ItemTag.CROP_BEECH, TagDC.ItemTag.CROP_WALNUT, TagDC.ItemTag.CROP_ACORN, TagDC.ItemTag.CROP_COCONUT);

		// blocks
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
		copy(TagDC.BlockTag.ORES_JET, TagDC.ItemTag.ORES_JET);
		copy(TagDC.BlockTag.ORES_IOLITE, TagDC.ItemTag.ORES_IOLITE);
		copy(TagDC.BlockTag.ORES_OPAL, TagDC.ItemTag.ORES_OPAL);
		copy(TagDC.BlockTag.ORES_DRAGONSEYE, TagDC.ItemTag.ORES_DRAGONSEYE);
		copy(TagDC.BlockTag.ORES_DESERTROSE, TagDC.ItemTag.ORES_DESERTROSE);
		copy(TagDC.BlockTag.ORES_ROSINCA, TagDC.ItemTag.ORES_ROSINCA);
		copy(TagDC.BlockTag.ORES_SPINEL, TagDC.ItemTag.ORES_SPINEL);
		copy(TagDC.BlockTag.ORES_SERPENTINE, TagDC.ItemTag.ORES_SERPENTINE);
		copy(TagDC.BlockTag.ORES_AMAZONITE, TagDC.ItemTag.ORES_AMAZONITE);
		copy(TagDC.BlockTag.ORES_JADEITE, TagDC.ItemTag.ORES_JADEITE);

		copy(Tags.Blocks.ORES, Tags.Items.ORES);

		copy(TagDC.BlockTag.ORES_SALT, TagDC.ItemTag.ORES_SALT);
		copy(TagDC.BlockTag.ORES_NITER, TagDC.ItemTag.ORES_NITER);
		copy(TagDC.BlockTag.ORES_SULFUR, TagDC.ItemTag.ORES_SULFUR);
		copy(TagDC.BlockTag.ORES_LIME, TagDC.ItemTag.ORES_LIME);
		copy(TagDC.BlockTag.ORES_GYPSUM, TagDC.ItemTag.ORES_GYPSUM);
		copy(TagDC.BlockTag.ORES_TRAVERTINE, TagDC.ItemTag.ORES_TRAVERTINE);

		copy(TagDC.BlockTag.DUSTBLOCK_BRASS, TagDC.ItemTag.DUSTBLOCK_BRASS);
		copy(TagDC.BlockTag.DUSTBLOCK_BRONZE, TagDC.ItemTag.DUSTBLOCK_BRONZE);
		copy(TagDC.BlockTag.DUSTBLOCK_NICKEL_SILVER, TagDC.ItemTag.DUSTBLOCK_NICKEL_SILVER);
		copy(TagDC.BlockTag.DUSTBLOCK_STEEL, TagDC.ItemTag.DUSTBLOCK_STEEL);
		copy(TagDC.BlockTag.DUSTBLOCK_ALUMINUM, TagDC.ItemTag.DUSTBLOCK_ALUMINUM);
		copy(TagDC.BlockTag.DUSTBLOCK_SILVER, TagDC.ItemTag.DUSTBLOCK_SILVER);
		copy(TagDC.BlockTag.DUSTBLOCK_SUS, TagDC.ItemTag.DUSTBLOCK_SUS);
		copy(TagDC.BlockTag.DUSTBLOCK_MAGNET, TagDC.ItemTag.DUSTBLOCK_MAGNET);
		copy(TagDC.BlockTag.DUSTBLOCK_COBALT, TagDC.ItemTag.DUSTBLOCK_COBALT);
		copy(TagDC.BlockTag.DUSTBLOCK_HASTELLOY, TagDC.ItemTag.DUSTBLOCK_HASTELLOY);
		copy(TagDC.BlockTag.DUSTBLOCK_BSCCO, TagDC.ItemTag.DUSTBLOCK_BSCCO);

		copy(TagDC.BlockTag.METALBLOCK_BRASS, TagDC.ItemTag.METALBLOCK_BRASS);
		copy(TagDC.BlockTag.METALBLOCK_BRONZE, TagDC.ItemTag.METALBLOCK_BRONZE);
		copy(TagDC.BlockTag.METALBLOCK_NICKEL_SILVER, TagDC.ItemTag.METALBLOCK_NICKEL_SILVER);
		copy(TagDC.BlockTag.METALBLOCK_STEEL, TagDC.ItemTag.METALBLOCK_STEEL);
		copy(TagDC.BlockTag.METALBLOCK_ALUMINUM, TagDC.ItemTag.METALBLOCK_ALUMINUM);
		copy(TagDC.BlockTag.METALBLOCK_SILVER, TagDC.ItemTag.METALBLOCK_SILVER);
		copy(TagDC.BlockTag.METALBLOCK_SUS, TagDC.ItemTag.METALBLOCK_SUS);
		copy(TagDC.BlockTag.METALBLOCK_MAGNET, TagDC.ItemTag.METALBLOCK_MAGNET);
		copy(TagDC.BlockTag.METALBLOCK_COBALT, TagDC.ItemTag.METALBLOCK_COBALT);
		copy(TagDC.BlockTag.METALBLOCK_HASTELLOY, TagDC.ItemTag.METALBLOCK_HASTELLOY);
		copy(TagDC.BlockTag.METALBLOCK_BSCCO, TagDC.ItemTag.METALBLOCK_BSCCO);

		copy(TagDC.BlockTag.CROP_GREEN_MANURES, TagDC.ItemTag.CROP_GREEN_MANURES);

		copy(TagDC.BlockTag.CROP_PUMPKIN, TagDC.ItemTag.CROP_PUMPKIN);
		copy(TagDC.BlockTag.CROP_MELON, TagDC.ItemTag.CROP_MELON);

		// plugin
		tag(TagDC.ItemTag.CROP_BARLEY).addOptional(new ResourceLocation("biomesoplenty", "barley"));
		tag(TagDC.ItemTag.CROP_LAVENDER).addOptional(new ResourceLocation("biomesoplenty", "lavender"));
		tag(TagDC.ItemTag.CROP_LAVENDER).addOptional(new ResourceLocation("biomesoplenty", "tall_lavender"));
		tag(TagDC.ItemTag.CROP_REED).addOptional(new ResourceLocation("biomesoplenty", "reed"));
	}

}
