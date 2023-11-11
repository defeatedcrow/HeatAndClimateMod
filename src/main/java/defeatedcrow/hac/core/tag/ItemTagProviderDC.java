package defeatedcrow.hac.core.tag;

import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemFoodDC;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
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
				if (item.getPairTag() != TagDC.ItemTag.DUMMY) {
					tag(item.getPairTag()).add(i);

					if (TagUtil.isMatch("forge", "ores", item.getPairTag())) {
						tag(Tags.Items.ORES).addTag(item.getPairTag());
					}

					if (TagUtil.isMatch("forge", "raw_materials", item.getPairTag())) {
						tag(Tags.Items.RAW_MATERIALS).addTag(item.getPairTag());
					}

					if (TagUtil.isMatch("forge", "ingots", item.getPairTag())) {
						tag(Tags.Items.INGOTS).addTag(item.getPairTag());
					}

					if (TagUtil.isMatch("forge", "dusts", item.getPairTag())) {
						tag(Tags.Items.DUSTS).addTag(item.getPairTag());
					}

					if (TagUtil.isMatch("forge", "gems", item.getPairTag())) {
						tag(Tags.Items.GEMS).addTag(item.getPairTag());
					}

					if (TagUtil.isMatch("forge", "crops", item.getPairTag())) {
						tag(Tags.Items.CROPS).addTag(item.getPairTag());
					}

					if (TagUtil.isMatch("forge", "seeds", item.getPairTag())) {
						tag(Tags.Items.SEEDS).addTag(item.getPairTag());
					}

				}

				if (item instanceof ItemFoodDC && ((ItemFoodDC) item).isRawFood()) {
					tag(TagDC.ItemTag.RAW_FOOD).add(i);
				}
			});

		// vanilla

		tag(TagDC.ItemTag.DUST_IRON).addTag(TagDC.ItemTag.DUST_MAGNETITE);

		tag(TagDC.ItemTag.GEM_GARNET).addTag(TagDC.ItemTag.GEM_ALMANDINE);
		tag(TagDC.ItemTag.GEM_PERIDOT).addTag(TagDC.ItemTag.GEM_OLIVINE);

		tag(TagDC.ItemTag.GEM_AGATES).addTags(TagDC.ItemTag.GEM_CHALCEDONY, TagDC.ItemTag.GEM_CRYSTAL,
			TagDC.ItemTag.GEM_JASPER, Tags.Items.GEMS_QUARTZ, Tags.Items.GEMS_AMETHYST);

		tag(TagDC.ItemTag.GEM_COAL).add(Items.COAL);
		tag(TagDC.ItemTag.GEM_FLINT).add(Items.FLINT);

		tag(TagDC.ItemTag.CROP_APPLE).add(Items.APPLE);
		tag(TagDC.ItemTag.CROP_COCOA).add(Items.COCOA_BEANS);
		tag(TagDC.ItemTag.CROP_BERRY).add(Items.SWEET_BERRIES);
		tag(TagDC.ItemTag.CROP_SUGAR).add(Items.SUGAR_CANE);

		tag(TagDC.ItemTag.BREAD).add(Items.BREAD);
		tag(TagDC.ItemTag.CROP_NUTS).add(Items.PUMPKIN_SEEDS, Items.SUNFLOWER);

		tag(TagDC.ItemTag.COW_MILK).add(Items.MILK_BUCKET);
		tag(TagDC.ItemTag.WATER).add(Items.WATER_BUCKET);
		tag(TagDC.ItemTag.DUST_SUGAR).add(Items.SUGAR);

		tag(TagDC.ItemTag.STICK_BAMBOO).add(Items.BAMBOO);

		tag(TagDC.ItemTag.RAW_BEEF).add(Items.BEEF);
		tag(TagDC.ItemTag.RAW_PORK).add(Items.PORKCHOP);
		tag(TagDC.ItemTag.RAW_CHICKEN).add(Items.CHICKEN);
		tag(TagDC.ItemTag.RAW_MUTTON).add(Items.MUTTON);
		tag(TagDC.ItemTag.RAW_RABBIT).add(Items.RABBIT);
		tag(TagDC.ItemTag.RAW_ROTTEN).add(Items.ROTTEN_FLESH);

		tag(TagDC.ItemTag.COOKED_BEEF).add(Items.COOKED_BEEF);
		tag(TagDC.ItemTag.COOKED_PORK).add(Items.COOKED_PORKCHOP);
		tag(TagDC.ItemTag.COOKED_CHICKEN).add(Items.COOKED_CHICKEN);
		tag(TagDC.ItemTag.COOKED_MUTTON).add(Items.COOKED_MUTTON);
		tag(TagDC.ItemTag.COOKED_RABBIT).add(Items.COOKED_RABBIT);

		tag(TagDC.ItemTag.RAW_COD).add(Items.COD);
		tag(TagDC.ItemTag.RAW_SALMON).add(Items.SALMON);

		tag(TagDC.ItemTag.COOKED_COD).add(Items.COOKED_COD);
		tag(TagDC.ItemTag.COOKED_SALMON).add(Items.COOKED_SALMON);

		tag(TagDC.ItemTag.FERTILIZER).add(Items.BONE_MEAL);

		tag(TagDC.ItemTag.MAGMA).add(Items.MAGMA_BLOCK);
		tag(TagDC.ItemTag.DRIPSTONES).add(Items.DRIPSTONE_BLOCK, Items.POINTED_DRIPSTONE);

		// magic
		tag(TagDC.ItemTag.GEM_WHITE).addTags(TagDC.ItemTag.GEM_CHALCEDONY, TagDC.ItemTag.GEM_CRYSTAL, TagDC.ItemTag.GEM_HELIODOR,
			TagDC.ItemTag.GEM_THUNDEREGG, TagDC.ItemTag.GEM_TOPAZ, Tags.Items.GEMS_DIAMOND);

		tag(TagDC.ItemTag.GEM_BLUE).addTags(TagDC.ItemTag.GEM_FLUORITE, TagDC.ItemTag.GEM_LARIMAR, TagDC.ItemTag.GEM_CELESTITE,
			TagDC.ItemTag.GEM_AQUAMARINE, TagDC.ItemTag.GEM_SAPPHIRE, Tags.Items.GEMS_LAPIS);

		tag(TagDC.ItemTag.GEM_BLACK).addTags(TagDC.ItemTag.GEM_JET, TagDC.ItemTag.GEM_VIVIANITE, TagDC.ItemTag.GEM_IOLITE,
			TagDC.ItemTag.GEM_FANG, TagDC.ItemTag.GEM_OPAL, TagDC.ItemTag.GEM_DRAGONSEYE);

		tag(TagDC.ItemTag.GEM_RED).addTags(TagDC.ItemTag.GEM_DESERTROSE, TagDC.ItemTag.GEM_JASPER, TagDC.ItemTag.GEM_ROSINCA,
			TagDC.ItemTag.GEM_ALMANDINE, TagDC.ItemTag.GEM_SPINEL, TagDC.ItemTag.GEM_RUBY);

		tag(TagDC.ItemTag.GEM_GREEN).addTags(TagDC.ItemTag.GEM_SERPENTINE, TagDC.ItemTag.GEM_MALACHITE, TagDC.ItemTag.GEM_AMAZONITE,
			TagDC.ItemTag.GEM_OLIVINE, TagDC.ItemTag.GEM_JADEITE, Tags.Items.GEMS_EMERALD);

		tag(TagDC.ItemTag.COLOR_GEMS).addTags(TagDC.ItemTag.GEM_WHITE, TagDC.ItemTag.GEM_BLUE, TagDC.ItemTag.GEM_BLACK,
			TagDC.ItemTag.GEM_RED, TagDC.ItemTag.GEM_GREEN);

		tag(TagDC.ItemTag.COLOR_DROPS).addTags(TagDC.ItemTag.DROP_WHITE, TagDC.ItemTag.DROP_BLUE, TagDC.ItemTag.DROP_BLACK, TagDC.ItemTag.DROP_RED, TagDC.ItemTag.DROP_GREEN);
		tag(TagDC.ItemTag.COLOR_EXTRACTS).addTags(TagDC.ItemTag.EXTRACT_WHITE, TagDC.ItemTag.EXTRACT_BLUE, TagDC.ItemTag.EXTRACT_BLACK, TagDC.ItemTag.EXTRACT_RED, TagDC.ItemTag.EXTRACT_GREEN);
		tag(TagDC.ItemTag.COLOR_PIGMENTS).addTags(TagDC.ItemTag.PIGMENT_WHITE, TagDC.ItemTag.PIGMENT_BLUE, TagDC.ItemTag.PIGMENT_BLACK, TagDC.ItemTag.PIGMENT_RED, TagDC.ItemTag.PIGMENT_GREEN);

		// crops
		tag(TagDC.ItemTag.CROP_WILD).add(FoodInit.CROP_AL_WILD.get(), FoodInit.CROP_AM_GOOSEFOOT.get(), FoodInit.CROP_AP_CELERY.get(), FoodInit.CROP_BR_RAPESEED.get(),
			FoodInit.CROP_CA_CHILI.get(), FoodInit.CROP_CR_OAT.get(), FoodInit.CROP_AS_ARTEMISIA.get(), FoodInit.CROP_GN_COMMON.get(), FoodInit.CROP_HB_MINT.get(),
			FoodInit.CROP_KN_SORREL.get(), FoodInit.CROP_ML_JUTE.get(), FoodInit.CROP_MO_BINDWEED.get(), FoodInit.CROP_PD_ROGERIA.get(), FoodInit.CROP_PE_GREEN.get(),
			FoodInit.CROP_RE_COMMON.get(), FoodInit.CROP_RI_ZIZANIA.get(), FoodInit.CROP_SL_NIGHTSHADE.get(), FoodInit.CROP_PL_COCONUT.get(), FoodInit.CROP_GO_CALABASH.get(),
			FoodInit.CROP_GR_WILD.get(), FoodInit.CROP_IR_CROCUS.get(), FoodInit.CROP_OR_SPIRANTHES.get(), FoodInit.CROP_RA_ANEMONE.get());

		tag(TagDC.ItemTag.CROP_COMMON).add(FoodInit.CROP_AL_ONION.get(), FoodInit.CROP_AM_GLASSWORT.get(), FoodInit.CROP_AP_FENNEL.get(), FoodInit.CROP_BR_GREEN.get(),
			FoodInit.CROP_CA_BELL.get(), FoodInit.CROP_CR_RYE.get(), FoodInit.CROP_AS_LETTUCE.get(), FoodInit.CROP_GN_CARDAMOM.get(), FoodInit.CROP_HB_BASIL.get(),
			FoodInit.CROP_KN_BUCKWHEAT.get(), FoodInit.CROP_ML_COTTON.get(), FoodInit.CROP_MO_WATER.get(), FoodInit.CROP_PD_SESAMI.get(), FoodInit.CROP_PE_GARBANZO.get(),
			FoodInit.CROP_RE_SORGHUM.get(), FoodInit.CROP_RI_SHORT.get(), FoodInit.CROP_SL_EGGPLANT.get(), FoodInit.CROP_PL_DATE.get(), FoodInit.CROP_GO_CUCUMBER.get(),
			FoodInit.CROP_GR_COMMON.get(), FoodInit.CROP_IR_SAFFRON.get(), FoodInit.CROP_OR_CYMBIDIUM.get(), FoodInit.CROP_RA_DELPHINIUM.get());

		tag(TagDC.ItemTag.CROP_RARE).add(FoodInit.CROP_AL_GARLIC.get(), FoodInit.CROP_AM_SPINACH.get(), FoodInit.CROP_AP_PARSNIP.get(), FoodInit.CROP_BR_CABAGGE.get(),
			FoodInit.CROP_CA_PAPRIKA.get(), FoodInit.CROP_CR_BARLEY.get(), FoodInit.CROP_AS_PYRETHRUM.get(), FoodInit.CROP_GN_TURMERIC.get(), FoodInit.CROP_HB_PERILLA.get(),
			FoodInit.CROP_KN_INDIGO.get(), FoodInit.CROP_ML_BLUE.get(), FoodInit.CROP_MO_POTATO.get(), FoodInit.CROP_PD_DEVILSCLAW.get(), FoodInit.CROP_PE_SOY.get(),
			FoodInit.CROP_RE_CORN.get(), FoodInit.CROP_RI_AROMA.get(), FoodInit.CROP_SL_TOMATO.get(), FoodInit.CROP_PL_OIL.get(), FoodInit.CROP_GO_CANTALOUP.get(),
			FoodInit.CROP_GR_WHITE.get(), FoodInit.CROP_IR_IRIS.get(), FoodInit.CROP_OR_VANILLA.get(), FoodInit.CROP_RA_CLEMATIS.get());

		tag(TagDC.ItemTag.CROP_EPIC).add(FoodInit.CROP_AS_FLOWER.get(), FoodInit.CROP_BR_RADISH.get(), FoodInit.CROP_HB_LAVENDER.get(), FoodInit.CROP_MO_FLOWER.get(),
			FoodInit.CROP_ML_TROPICAL.get(), FoodInit.CROP_PE_ADZUKI.get(), FoodInit.CROP_SL_LANTERN.get(), FoodInit.CROP_OR_CATTLEYA.get(), FoodInit.CROP_RA_MONKSHOOD.get());

		tag(TagDC.ItemTag.TREE_WILD).add(FoodInit.CROP_BH_COMMON.get(), FoodInit.CROP_CH_WILD.get(), FoodInit.CROP_CM_OIL.get(), FoodInit.CROP_CT_POMELO.get(),
			FoodInit.CROP_ER_HEATH.get(), FoodInit.CROP_MR_MULBERRY.get(), FoodInit.CROP_OL_ASH.get(), FoodInit.CROP_RO_RUGOSA.get(), FoodInit.CROP_SU_LACQUER.get());

		tag(TagDC.ItemTag.TREE_COMMON).add(FoodInit.CROP_BH_WALNUT.get(), FoodInit.CROP_CH_PLUM.get(), FoodInit.CROP_CM_SCHIMA.get(), FoodInit.CROP_CT_MANDARIN.get(),
			FoodInit.CROP_CN_CINNAMON.get(), FoodInit.CROP_ER_RHODODENDRON.get(), FoodInit.CROP_MR_PAPER.get(), FoodInit.CROP_OL_OLIVE.get(),
			FoodInit.CROP_RO_RASPBERRY.get(), FoodInit.CROP_SU_MANGO.get());

		tag(TagDC.ItemTag.TREE_RARE).add(FoodInit.CROP_BH_SWEET.get(), FoodInit.CROP_CH_PEACH.get(), FoodInit.CROP_CN_AVOCADO.get(), FoodInit.CROP_CM_TEA.get(),
			FoodInit.CROP_CT_LEMON.get(), FoodInit.CROP_ER_BLUEBERRY.get(), FoodInit.CROP_MR_RUBBER.get(), FoodInit.CROP_OL_OSMANTHUS.get(),
			FoodInit.CROP_RO_DAMASCHENA.get(), FoodInit.CROP_SU_CASHEW.get());

		tag(TagDC.ItemTag.TREE_EPIC).add(FoodInit.CROP_SU_PISTACHIO.get());

		tag(TagDC.ItemTag.ALL_WILD).addTags(TagDC.ItemTag.CROP_WILD, TagDC.ItemTag.TREE_WILD);
		tag(TagDC.ItemTag.ALL_COMMON).addTags(TagDC.ItemTag.CROP_COMMON, TagDC.ItemTag.TREE_COMMON);
		tag(TagDC.ItemTag.ALL_RARE).addTags(TagDC.ItemTag.CROP_RARE, TagDC.ItemTag.TREE_RARE);
		tag(TagDC.ItemTag.ALL_EPIC).addTags(TagDC.ItemTag.CROP_EPIC, TagDC.ItemTag.TREE_EPIC);
		tag(TagDC.ItemTag.ALL_CROPS).addTags(TagDC.ItemTag.ALL_WILD, TagDC.ItemTag.ALL_COMMON, TagDC.ItemTag.ALL_RARE, TagDC.ItemTag.ALL_EPIC);

		tag(TagDC.ItemTag.CROP_CEREALS).addTags(TagDC.ItemTag.CROP_OAT, TagDC.ItemTag.CROP_RYE, TagDC.ItemTag.CROP_BARLEY,
			TagDC.ItemTag.CROP_WILD_RICE, TagDC.ItemTag.CROP_RICE, TagDC.ItemTag.CROP_AROMA_RICE, Tags.Items.CROPS_WHEAT);

		tag(TagDC.ItemTag.CROP_MILLETS).addTags(TagDC.ItemTag.CROP_SORGHUM);

		tag(TagDC.ItemTag.CROP_PSEUDOCEREALS).addTags(TagDC.ItemTag.CROP_GOOSEFOOT, TagDC.ItemTag.CROP_BUCKWHEAT);

		tag(TagDC.ItemTag.CROP_GREEN_LEAFS).addTags(TagDC.ItemTag.CROP_GOOSEFOOT, TagDC.ItemTag.CROP_SPINACH, TagDC.ItemTag.CROP_NAPA,
			TagDC.ItemTag.CROP_RAPESEED, TagDC.ItemTag.CROP_CABAGGE, TagDC.ItemTag.CROP_FENNEL, TagDC.ItemTag.CROP_CELERY, TagDC.ItemTag.CROP_LETTUCE,
			TagDC.ItemTag.CROP_SORREL, TagDC.ItemTag.CROP_INDIGO, TagDC.ItemTag.CROP_JUTE, TagDC.ItemTag.CROP_WATER_SPINACH, TagDC.ItemTag.CROP_ANEMONE);

		tag(TagDC.ItemTag.CROP_BEANS).addTags(TagDC.ItemTag.CROP_GREEN_PEAS, TagDC.ItemTag.CROP_GARBANZO,
			TagDC.ItemTag.CROP_SOY, TagDC.ItemTag.CROP_ADZUKI);

		tag(TagDC.ItemTag.CROP_SPICES).addTags(TagDC.ItemTag.CROP_CHIVES, TagDC.ItemTag.CROP_GARLIC,
			TagDC.ItemTag.CROP_CHILI, TagDC.ItemTag.CROP_PAPRIKA, TagDC.ItemTag.CROP_GINGER, TagDC.ItemTag.CROP_CARDAMOM,
			TagDC.ItemTag.CROP_TURMERIC, TagDC.ItemTag.CROP_CINNAMON);

		tag(TagDC.ItemTag.CROP_HERBS).addTags(TagDC.ItemTag.CROP_CHIVES, TagDC.ItemTag.CROP_ONION, TagDC.ItemTag.CROP_GARLIC,
			TagDC.ItemTag.CROP_FENNEL, TagDC.ItemTag.CROP_CELERY, TagDC.ItemTag.CROP_PARSNIP, TagDC.ItemTag.CROP_ARTEMISIA,
			TagDC.ItemTag.CROP_MINT, TagDC.ItemTag.CROP_BASIL, TagDC.ItemTag.CROP_PERILLA, TagDC.ItemTag.CROP_LAVENDER,
			TagDC.ItemTag.CROP_HEATH, TagDC.ItemTag.CROP_SAFFRON);

		tag(TagDC.ItemTag.CROP_EDIBLE_RAW_VEGGIE).addTags(TagDC.ItemTag.CROP_CHIVES, TagDC.ItemTag.CROP_ONION,
			TagDC.ItemTag.CROP_SPINACH, TagDC.ItemTag.CROP_FENNEL, TagDC.ItemTag.CROP_CELERY, TagDC.ItemTag.CROP_LETTUCE,
			TagDC.ItemTag.CROP_RAPESEED, TagDC.ItemTag.CROP_NAPA, TagDC.ItemTag.CROP_CABAGGE, TagDC.ItemTag.CROP_RADISH,
			TagDC.ItemTag.CROP_PAPRIKA, TagDC.ItemTag.CROP_BELL, TagDC.ItemTag.CROP_SORREL, TagDC.ItemTag.CROP_INDIGO,
			TagDC.ItemTag.CROP_WATER_SPINACH, TagDC.ItemTag.CROP_CORN, TagDC.ItemTag.CROP_TOMATO, TagDC.ItemTag.CROP_CUCUMBER,
			TagDC.ItemTag.CROP_PUMPKIN, Tags.Items.CROPS_CARROT, Tags.Items.CROPS_BEETROOT);

		tag(TagDC.ItemTag.CROP_VEGETABLES).addTags(TagDC.ItemTag.CROP_CHIVES, TagDC.ItemTag.CROP_ONION,
			TagDC.ItemTag.CROP_GOOSEFOOT, TagDC.ItemTag.CROP_GLASSWORT, TagDC.ItemTag.CROP_SPINACH,
			TagDC.ItemTag.CROP_FENNEL, TagDC.ItemTag.CROP_CELERY, TagDC.ItemTag.CROP_PARSNIP, TagDC.ItemTag.CROP_LETTUCE,
			TagDC.ItemTag.CROP_RAPESEED, TagDC.ItemTag.CROP_NAPA, TagDC.ItemTag.CROP_CABAGGE, TagDC.ItemTag.CROP_RADISH,
			TagDC.ItemTag.CROP_PAPRIKA, TagDC.ItemTag.CROP_BELL, TagDC.ItemTag.CROP_SORREL, TagDC.ItemTag.CROP_INDIGO,
			TagDC.ItemTag.CROP_WATER_SPINACH, TagDC.ItemTag.CROP_SWEET_POTATO, TagDC.ItemTag.CROP_GREEN_PEAS,
			TagDC.ItemTag.CROP_GARBANZO, TagDC.ItemTag.CROP_SOY, TagDC.ItemTag.CROP_ADZUKI, TagDC.ItemTag.CROP_CORN,
			TagDC.ItemTag.MAKOMOTAKE, TagDC.ItemTag.CROP_EGGPLANT, TagDC.ItemTag.CROP_TOMATO, TagDC.ItemTag.CROP_JUTE,
			TagDC.ItemTag.CROP_CUCUMBER, TagDC.ItemTag.CROP_ANEMONE,
			Tags.Items.CROPS_CARROT, Tags.Items.CROPS_BEETROOT, Tags.Items.CROPS_POTATO, TagDC.ItemTag.CROP_PUMPKIN);

		tag(TagDC.ItemTag.CROP_FLOWERS).addTags(TagDC.ItemTag.CROP_PYRETHRUM, TagDC.ItemTag.CROP_CHRYSANTHEMUM,
			TagDC.ItemTag.CROP_LAVENDER, TagDC.ItemTag.CROP_BINDWEED, TagDC.ItemTag.CROP_MORNING_GLORY,
			TagDC.ItemTag.CROP_LANTERN, TagDC.ItemTag.CROP_BLUE_MALLOW, TagDC.ItemTag.CROP_TROPICAL, TagDC.ItemTag.CROP_CROCUS,
			TagDC.ItemTag.CROP_IRIS, TagDC.ItemTag.CROP_SPIRANTHES, TagDC.ItemTag.CROP_CYMBIDIUM, TagDC.ItemTag.CROP_CATTLEYA,
			TagDC.ItemTag.CROP_ANEMONE, TagDC.ItemTag.CROP_DELPHINIUM, TagDC.ItemTag.CROP_CLEMATIS, TagDC.ItemTag.CROP_MONKSHOOD,
			TagDC.ItemTag.CROP_HEATH, TagDC.ItemTag.CROP_RHODODENDRON, TagDC.ItemTag.CROP_OSUMANTHUS, TagDC.ItemTag.CROP_DAMASCHENA);

		tag(TagDC.ItemTag.CROP_FRUITS).addTags(TagDC.ItemTag.CROP_LANTERN, TagDC.ItemTag.CROP_WILD_GRAPE, TagDC.ItemTag.CROP_RED_GRAPE,
			TagDC.ItemTag.CROP_WHITE_GRAPE, TagDC.ItemTag.CROP_CANTALOUP, TagDC.ItemTag.CROP_CHERRY, TagDC.ItemTag.CROP_PLUM,
			TagDC.ItemTag.CROP_PEACH, TagDC.ItemTag.CROP_BLUEBERRY, TagDC.ItemTag.CROP_MULBERRY, TagDC.ItemTag.CROP_KAJI,
			TagDC.ItemTag.CROP_DATE, TagDC.ItemTag.CROP_RASPBERRY, TagDC.ItemTag.CROP_APPLE, TagDC.ItemTag.CROP_BERRY,
			TagDC.ItemTag.CROP_MANGO, TagDC.ItemTag.CROP_CASHEW);

		tag(TagDC.ItemTag.CROP_BERRY).addTags(TagDC.ItemTag.CROP_BLUEBERRY, TagDC.ItemTag.CROP_MULBERRY, TagDC.ItemTag.CROP_RASPBERRY);

		tag(TagDC.ItemTag.CROP_CITRUS).addTags(TagDC.ItemTag.CROP_POMELO, TagDC.ItemTag.CROP_MANDARIN, TagDC.ItemTag.CROP_LEMON);

		tag(TagDC.ItemTag.CROP_GRAPES).addTags(TagDC.ItemTag.CROP_WILD_GRAPE, TagDC.ItemTag.CROP_RED_GRAPE, TagDC.ItemTag.CROP_WHITE_GRAPE);

		tag(TagDC.ItemTag.CROP_STRAWS).addTags(TagDC.ItemTag.CROP_OAT, TagDC.ItemTag.CROP_RYE, TagDC.ItemTag.CROP_BARLEY,
			TagDC.ItemTag.CROP_REED, TagDC.ItemTag.CROP_SORGHUM, TagDC.ItemTag.CROP_CORN,
			TagDC.ItemTag.CROP_WILD_RICE, TagDC.ItemTag.CROP_RICE, TagDC.ItemTag.CROP_AROMA_RICE,
			TagDC.ItemTag.CROP_REED, TagDC.ItemTag.CROP_SORGHUM);

		tag(TagDC.ItemTag.CROP_STICKS).addTags(TagDC.ItemTag.CROP_GOOSEFOOT, TagDC.ItemTag.CROP_REED, TagDC.ItemTag.CROP_SORGHUM);

		tag(TagDC.ItemTag.CROP_OILS).addTags(TagDC.ItemTag.CROP_RAPESEED, TagDC.ItemTag.CROP_ROGERIA, TagDC.ItemTag.CROP_SESAMI, TagDC.ItemTag.CROP_SOY,
			TagDC.ItemTag.CROP_AVOCADO, TagDC.ItemTag.CROP_CAMELLIA, TagDC.ItemTag.CROP_OLIVE, TagDC.ItemTag.CROP_OIL_PALM);

		tag(TagDC.ItemTag.CROP_NUTS).addTags(TagDC.ItemTag.CROP_BEECH, TagDC.ItemTag.CROP_WALNUT, TagDC.ItemTag.CROP_ACORN, TagDC.ItemTag.CROP_COCONUT,
			TagDC.ItemTag.CROP_PISTACHIO, TagDC.ItemTag.CASHEW_NUTS);

		tag(TagDC.ItemTag.DUST_BREAD_GRAINS).addTags(TagDC.ItemTag.DUST_RYE, TagDC.ItemTag.DUST_WHEAT);

		tag(TagDC.ItemTag.DUST_CEREALS).addTags(TagDC.ItemTag.DUST_OAT, TagDC.ItemTag.DUST_RYE, TagDC.ItemTag.DUST_BARLEY,
			TagDC.ItemTag.DUST_ZIZANIA, TagDC.ItemTag.DUST_RICE, TagDC.ItemTag.DUST_AROMA_RICE, TagDC.ItemTag.DUST_WHEAT);

		tag(TagDC.ItemTag.DUST_MILLETS).addTags(TagDC.ItemTag.DUST_SORGHUM);

		tag(TagDC.ItemTag.DUST_PSEUDOCEREALS).addTags(TagDC.ItemTag.DUST_AMARANTH, TagDC.ItemTag.DUST_BUCKWHEAT);

		tag(TagDC.ItemTag.DUST_RICES).addTags(TagDC.ItemTag.DUST_ZIZANIA, TagDC.ItemTag.DUST_RICE, TagDC.ItemTag.DUST_AROMA_RICE);

		tag(TagDC.ItemTag.SUGARS).addTags(TagDC.ItemTag.HONEY, TagDC.ItemTag.SYRUP, TagDC.ItemTag.DUST_SUGAR);

		tag(TagDC.ItemTag.MILKS).addTags(TagDC.ItemTag.COW_MILK, TagDC.ItemTag.SOY_MILK, TagDC.ItemTag.COCONUT_MILK);

		tag(TagDC.ItemTag.SAPS).addTags(TagDC.ItemTag.SAP_SWEET, TagDC.ItemTag.SAP_RESIN, TagDC.ItemTag.SAP_LATEX, TagDC.ItemTag.SAP_LACQUER);

		tag(TagDC.ItemTag.FOOD_FAT).addTags(TagDC.ItemTag.BUTTER, TagDC.ItemTag.MARGARINE);

		tag(TagDC.ItemTag.RAW_MEAT).addTags(TagDC.ItemTag.RAW_BEEF, TagDC.ItemTag.RAW_PORK, TagDC.ItemTag.RAW_CHICKEN,
			TagDC.ItemTag.RAW_MUTTON, TagDC.ItemTag.RAW_RABBIT, TagDC.ItemTag.FROG);

		tag(TagDC.ItemTag.COOKED_MEAT).addTags(TagDC.ItemTag.COOKED_BEEF, TagDC.ItemTag.COOKED_PORK, TagDC.ItemTag.COOKED_CHICKEN,
			TagDC.ItemTag.COOKED_MUTTON, TagDC.ItemTag.COOKED_RABBIT);

		tag(TagDC.ItemTag.COOKED_FISH).addTags(TagDC.ItemTag.COOKED_COD, TagDC.ItemTag.COOKED_SALMON);

		tag(Tags.Items.BONES).addTags(TagDC.ItemTag.BONE_COW, TagDC.ItemTag.BONE_PIG, TagDC.ItemTag.BONE_CHICKEN);

		tag(TagDC.ItemTag.FERTILIZER).addTags(TagDC.ItemTag.FISH_POWDER, TagDC.ItemTag.PRESS_CAKE, TagDC.ItemTag.LEAF_MOLD, TagDC.ItemTag.FERTILIZER_ADV);

		tag(Tags.Items.STRING).addTags(TagDC.ItemTag.STRING_PLANT, TagDC.ItemTag.STRING_COTTON, TagDC.ItemTag.STRING_WOOL);
		tag(TagDC.ItemTag.CLOTHS).addTags(TagDC.ItemTag.CLOTH_PLANT, TagDC.ItemTag.CLOTH_COTTON, TagDC.ItemTag.CLOTH_WOOL);

		tag(Tags.Items.RODS_WOODEN).addTags(TagDC.ItemTag.STICK_SORGHUM);

		tag(ItemTags.ARROWS).add(MagicInit.ARROW_WHITE.get(), MagicInit.ARROW_BLUE.get(), MagicInit.ARROW_BLACK.get(),
			MagicInit.ARROW_RED.get(), MagicInit.ARROW_GREEN.get());

		tag(TagDC.ItemTag.SEED_WHITE).addTags(TagDC.ItemTag.SEED_CHIVES, TagDC.ItemTag.SEED_GOOSEFOOT, TagDC.ItemTag.SEED_ARTEMISIA,
			TagDC.ItemTag.SEED_RAPESEED, TagDC.ItemTag.SEED_CHILI, TagDC.ItemTag.SEED_SORREL);

		tag(TagDC.ItemTag.SEED_BLUE).addTags(TagDC.ItemTag.SEED_OAT, TagDC.ItemTag.SEED_MINT, TagDC.ItemTag.SEED_CROCUS, TagDC.ItemTag.SEED_ANEMONE)
			.add(FoodInit.BLOCK_BH_COMMON.get().asItem(), FoodInit.BLOCK_ER_HEATH.get().asItem(), FoodInit.BLOCK_OL_ASH.get().asItem());

		tag(TagDC.ItemTag.SEED_BLACK).addTags(TagDC.ItemTag.SEED_CELERY, TagDC.ItemTag.SEED_BINDWEED,
			TagDC.ItemTag.SEED_REED, TagDC.ItemTag.SEED_WILD_RICE, TagDC.ItemTag.SEED_NIGHTSHADE)
			.add(FoodInit.BLOCK_RO_RUGOSA.get().asItem());

		tag(TagDC.ItemTag.SEED_RED).addTags(TagDC.ItemTag.SEED_JUTE, TagDC.ItemTag.SEED_ROGERIA, TagDC.ItemTag.SEED_WILD_GRAPE)
			.add(FoodInit.BLOCK_CH_WILD.get().asItem(), FoodInit.BLOCK_CM_OIL.get().asItem(), FoodInit.BLOCK_SU_LACQUER.get().asItem())
			.add(FoodInit.BLOCK_SU_LACQUER.get().asItem());

		tag(TagDC.ItemTag.SEED_GREEN).addTags(TagDC.ItemTag.SEED_GINGER, TagDC.ItemTag.SEED_GREEN_PEAS, TagDC.ItemTag.SEED_CALABASH, TagDC.ItemTag.SEED_SPIRANTHES)
			.add(FoodInit.BLOCK_CT_POMELO.get().asItem(), FoodInit.BLOCK_CN_CAMPHOR.get().asItem(),
				FoodInit.BLOCK_MR_MULBERRY.get().asItem(), FoodInit.BLOCK_PL_COCONUT.get().asItem());

		// fishing
		tag(ItemTags.FISHES).addTags(TagDC.ItemTag.MULLET, TagDC.ItemTag.SMELT, TagDC.ItemTag.SARDINE, TagDC.ItemTag.MACKEREL, TagDC.ItemTag.CARP,
			TagDC.ItemTag.ROCKFISH, TagDC.ItemTag.GROUPER, TagDC.ItemTag.SEABREAM, TagDC.ItemTag.FLOUNDER, TagDC.ItemTag.CRAB, TagDC.ItemTag.PRAWN,
			TagDC.ItemTag.KRILL, TagDC.ItemTag.SQUID);

		tag(TagDC.ItemTag.RAW_EDIBLE_FISH).addTags(TagDC.ItemTag.RAW_COD, TagDC.ItemTag.RAW_SALMON, TagDC.ItemTag.MACKEREL, TagDC.ItemTag.SARDINE, TagDC.ItemTag.CARP,
			TagDC.ItemTag.ROCKFISH, TagDC.ItemTag.GROUPER, TagDC.ItemTag.SEABREAM, TagDC.ItemTag.FLOUNDER, TagDC.ItemTag.MULLET, TagDC.ItemTag.SMELT);

		tag(TagDC.ItemTag.RAW_ALL_FISH).addTags(TagDC.ItemTag.RAW_EDIBLE_FISH).add(Items.PUFFERFISH, Items.TROPICAL_FISH);

		tag(TagDC.ItemTag.FISH_BLUE).addTags(TagDC.ItemTag.MACKEREL, TagDC.ItemTag.SARDINE);

		tag(TagDC.ItemTag.FISH_WHITE).addTags(TagDC.ItemTag.RAW_COD, TagDC.ItemTag.RAW_SALMON, TagDC.ItemTag.ROCKFISH, TagDC.ItemTag.CARP,
			TagDC.ItemTag.GROUPER, TagDC.ItemTag.SEABREAM, TagDC.ItemTag.FLOUNDER, TagDC.ItemTag.MULLET, TagDC.ItemTag.SMELT);

		tag(TagDC.ItemTag.FISH_SASHIMI).addTags(TagDC.ItemTag.RAW_SALMON, TagDC.ItemTag.MACKEREL, TagDC.ItemTag.SARDINE,
			TagDC.ItemTag.ROCKFISH, TagDC.ItemTag.GROUPER, TagDC.ItemTag.SEABREAM, TagDC.ItemTag.FLOUNDER);

		tag(TagDC.ItemTag.FISH_WITH_ROE).addTags(TagDC.ItemTag.RAW_COD, TagDC.ItemTag.RAW_SALMON, TagDC.ItemTag.MACKEREL, TagDC.ItemTag.MULLET, TagDC.ItemTag.SMELT);

		tag(TagDC.ItemTag.FISH).add(FoodInit.FOOD_MULLET.get(), FoodInit.FOOD_SMELT.get(), FoodInit.FOOD_SARDINE.get(), FoodInit.FOOD_MACKEREL.get(),
			FoodInit.FOOD_CARP.get(), FoodInit.FOOD_ROCKFISH.get(), FoodInit.FOOD_SEABREAM.get(), FoodInit.FOOD_FLOUNDER.get(), FoodInit.FOOD_GROUPER.get(),
			FoodInit.FOOD_KRILL.get(), FoodInit.FOOD_PRAWN.get(), FoodInit.FOOD_CRAB.get(), FoodInit.FOOD_SQUID.get(),
			Items.COD, Items.SALMON, Items.PUFFERFISH, Items.TROPICAL_FISH);

		tag(TagDC.ItemTag.FISH_VANILLA).add(Items.COD, Items.SALMON, Items.PUFFERFISH, Items.TROPICAL_FISH);

		tag(TagDC.ItemTag.FISH_LOD).addTags(TagDC.ItemTag.FISH);

		tag(TagDC.ItemTag.FISH_NIGHT).add(FoodInit.FOOD_ROCKFISH.get(), FoodInit.FOOD_FLOUNDER.get(), FoodInit.FOOD_GROUPER.get(), FoodInit.FOOD_SQUID.get());

		tag(TagDC.ItemTag.FISH_DAY).add(FoodInit.FOOD_MULLET.get(), FoodInit.FOOD_SMELT.get(), FoodInit.FOOD_SARDINE.get(), FoodInit.FOOD_MACKEREL.get(),
			FoodInit.FOOD_CARP.get(), FoodInit.FOOD_SEABREAM.get(), FoodInit.FOOD_KRILL.get(), FoodInit.FOOD_PRAWN.get(), FoodInit.FOOD_CRAB.get(),
			Items.COD, Items.SALMON, Items.PUFFERFISH, Items.TROPICAL_FISH);

		tag(TagDC.ItemTag.FISH_RIVER).add(FoodInit.FOOD_MULLET.get(), FoodInit.FOOD_SMELT.get(), FoodInit.FOOD_CARP.get(), FoodInit.FOOD_PRAWN.get(),
			FoodInit.FOOD_CRAB.get(), Items.SALMON);

		tag(TagDC.ItemTag.FISH_BEACH).add(FoodInit.FOOD_MULLET.get(), FoodInit.FOOD_SARDINE.get(),
			FoodInit.FOOD_ROCKFISH.get(), FoodInit.FOOD_SEABREAM.get(), FoodInit.FOOD_FLOUNDER.get(),
			FoodInit.FOOD_CRAB.get(), FoodInit.FOOD_SQUID.get(), Items.PUFFERFISH, Items.TROPICAL_FISH);

		tag(TagDC.ItemTag.FISH_MANGROVE).add(FoodInit.FOOD_MACKEREL.get(), FoodInit.FOOD_SEABREAM.get(), FoodInit.FOOD_PRAWN.get(), FoodInit.FOOD_CRAB.get());

		tag(TagDC.ItemTag.FISH_OCEAN).add(FoodInit.FOOD_SARDINE.get(), FoodInit.FOOD_MACKEREL.get(),
			FoodInit.FOOD_ROCKFISH.get(), FoodInit.FOOD_SEABREAM.get(), FoodInit.FOOD_FLOUNDER.get(), FoodInit.FOOD_GROUPER.get(),
			FoodInit.FOOD_KRILL.get(), FoodInit.FOOD_CRAB.get(), FoodInit.FOOD_SQUID.get(),
			Items.COD, Items.PUFFERFISH, Items.TROPICAL_FISH);

		tag(TagDC.ItemTag.FISH_COLD_WATER).add(FoodInit.FOOD_SMELT.get(), FoodInit.FOOD_FLOUNDER.get(), Items.COD, Items.SALMON);

		tag(TagDC.ItemTag.FISH_TROPICAL).add(FoodInit.FOOD_MULLET.get(), FoodInit.FOOD_SARDINE.get(), FoodInit.FOOD_SEABREAM.get(), FoodInit.FOOD_GROUPER.get(),
			FoodInit.FOOD_CARP.get(), FoodInit.FOOD_PRAWN.get(), FoodInit.FOOD_SQUID.get(), Items.PUFFERFISH, Items.TROPICAL_FISH);

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

		copy(TagDC.BlockTag.ORES_COLOR, TagDC.ItemTag.ORES_COLOR);

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

		copy(TagDC.BlockTag.CONT_LEAVES, TagDC.ItemTag.CONT_LEAVES);

		copy(BlockTags.LEAVES, ItemTags.LEAVES);

		copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);

		copy(TagDC.BlockTag.CROP_PUMPKIN, TagDC.ItemTag.CROP_PUMPKIN);
		copy(TagDC.BlockTag.CROP_MELON, TagDC.ItemTag.CROP_MELON);

		copy(TagDC.BlockTag.LOG_SWEET, TagDC.ItemTag.LOG_SWEET);
		copy(TagDC.BlockTag.LOG_RESIN, TagDC.ItemTag.LOG_RESIN);
		copy(TagDC.BlockTag.LOG_LATEX, TagDC.ItemTag.LOG_LATEX);
		copy(TagDC.BlockTag.LOG_LACQUER, TagDC.ItemTag.LOG_LACQUER);
		copy(TagDC.BlockTag.LOG_SAP, TagDC.ItemTag.LOG_SAP);

		copy(TagDC.BlockTag.MAGMA, TagDC.ItemTag.MAGMA);

		copy(TagDC.BlockTag.WEED, TagDC.ItemTag.WEED);

		copy(Tags.Blocks.ORES, Tags.Items.ORES);

		// plugin
		tag(TagDC.ItemTag.CROP_BARLEY).addOptional(new ResourceLocation("biomesoplenty", "barley"));
		tag(TagDC.ItemTag.CROP_LAVENDER).addOptional(new ResourceLocation("biomesoplenty", "lavender"));
		tag(TagDC.ItemTag.CROP_LAVENDER).addOptional(new ResourceLocation("biomesoplenty", "tall_lavender"));
		tag(TagDC.ItemTag.CROP_REED).addOptional(new ResourceLocation("biomesoplenty", "reed"));
	}

}
