package defeatedcrow.hac.core.material.tag;

import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagProviderDC extends ItemTagsProvider {

	public ItemTagProviderDC(DataGenerator gen, BlockTagsProvider provider, @Nullable ExistingFileHelper helper) {
		super(gen, provider, "dcs_climate", helper);
	}

	@Override
	protected void addTags() {

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

		tag(TagDC.ItemTag.DUST_COPPER).add(CoreInit.OREDUST_WHITE1.get());
		tag(TagDC.ItemTag.DUST_GOLD).add(CoreInit.OREDUST_WHITE2.get());
		tag(TagDC.ItemTag.DUST_MOLYBDENUM).add(CoreInit.OREDUST_WHITE3.get());
		tag(TagDC.ItemTag.DUST_ZINC).add(CoreInit.OREDUST_BLUE1.get());
		tag(TagDC.ItemTag.DUST_BISMUTH).add(CoreInit.OREDUST_BLUE2.get());
		tag(TagDC.ItemTag.DUST_COBALT).add(CoreInit.OREDUST_BLUE3.get());
		tag(TagDC.ItemTag.DUST_IRON).add(CoreInit.OREDUST_BLACK1.get());
		tag(TagDC.ItemTag.DUST_MAGNETITE).add(CoreInit.OREDUST_BLACK1.get());
		tag(TagDC.ItemTag.DUST_SILVER).add(CoreInit.OREDUST_BLACK2.get());
		tag(TagDC.ItemTag.DUST_TITANIUM).add(CoreInit.OREDUST_BLACK3.get());
		tag(TagDC.ItemTag.DUST_IRON).add(CoreInit.OREDUST_RED1.get());
		tag(TagDC.ItemTag.DUST_ALUMINUM).add(CoreInit.OREDUST_RED2.get());
		tag(TagDC.ItemTag.DUST_MANGANESE).add(CoreInit.OREDUST_RED3.get());
		tag(TagDC.ItemTag.DUST_TIN).add(CoreInit.OREDUST_GREEN1.get());
		tag(TagDC.ItemTag.DUST_NICKEL).add(CoreInit.OREDUST_GREEN2.get());
		tag(TagDC.ItemTag.DUST_CHROMIUM).add(CoreInit.OREDUST_GREEN3.get());

		tag(TagDC.ItemTag.DUST_COAL).add(CoreInit.DUST_COAL.get());
		tag(TagDC.ItemTag.DUST_NITER).add(CoreInit.DUST_NITER.get());
		tag(TagDC.ItemTag.DUST_SULFUR).add(CoreInit.DUST_SULFUR.get());

		// tag(TagDC.ItemTag.INGOT_COPPER).add(CoreInit.INGOT_WHITE1.get());
		// tag(TagDC.ItemTag.INGOT_GOLD).add(CoreInit.INGOT_WHITE2.get());
		// tag(TagDC.ItemTag.INGOT_MOLYBDENUM).add(CoreInit.INGOT_WHITE3.get());
		// tag(TagDC.ItemTag.INGOT_ZINC).add(CoreInit.INGOT_BLUE1.get());
		// tag(TagDC.ItemTag.INGOT_BISMUTH).add(CoreInit.INGOT_BLUE2.get());
		// tag(TagDC.ItemTag.INGOT_COBALT).add(CoreInit.INGOT_BLUE3.get());
		// tag(TagDC.ItemTag.INGOT_IRON).add(CoreInit.INGOT_BLACK1.get());
		// tag(TagDC.ItemTag.INGOT_MAGNETITE).add(CoreInit.INGOT_BLACK1.get());
		// tag(TagDC.ItemTag.INGOT_SILVER).add(CoreInit.INGOT_BLACK2.get());
		// tag(TagDC.ItemTag.INGOT_TITANIUM).add(CoreInit.INGOT_BLACK3.get());
		// tag(TagDC.ItemTag.INGOT_IRON).add(CoreInit.INGOT_RED1.get());
		// tag(TagDC.ItemTag.INGOT_ALUMINUM).add(CoreInit.INGOT_RED2.get());
		// tag(TagDC.ItemTag.INGOT_MANGANESE).add(CoreInit.INGOT_RED3.get());
		// tag(TagDC.ItemTag.INGOT_TIN).add(CoreInit.INGOT_GREEN1.get());
		// tag(TagDC.ItemTag.INGOT_NICKEL).add(CoreInit.INGOT_GREEN2.get());
		// tag(TagDC.ItemTag.INGOT_CHROMIUM).add(CoreInit.INGOT_GREEN3.get());

		tag(TagDC.ItemTag.INGOT_BRASS).add(CoreInit.INGOT_BRASS.get());
		tag(TagDC.ItemTag.INGOT_BRONZE).add(CoreInit.INGOT_BRONZE.get());
		tag(TagDC.ItemTag.INGOT_NICKEL_SILVER).add(CoreInit.INGOT_NICKEL_SILVER.get());
		tag(TagDC.ItemTag.INGOT_STEEL).add(CoreInit.INGOT_STEEL.get());
		tag(TagDC.ItemTag.INGOT_SUS).add(CoreInit.INGOT_SUS.get());
		tag(TagDC.ItemTag.INGOT_TOOL_STEEL).add(CoreInit.INGOT_TOOL_STEEL.get());
		tag(TagDC.ItemTag.INGOT_MAGNET).add(CoreInit.INGOT_MAGNET.get());
		tag(TagDC.ItemTag.INGOT_MANGALLOY).add(CoreInit.INGOT_MANGALLOY.get());
		tag(TagDC.ItemTag.INGOT_BSCCO).add(CoreInit.INGOT_BSCCO.get());

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
		tag(TagDC.ItemTag.GEM_SAKURA).add(CoreInit.GEM_SAKURA.get());
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

		tag(TagDC.ItemTag.GEM_SALT).add(CoreInit.GEM_SALT.get());
		tag(TagDC.ItemTag.GEM_NITER).add(CoreInit.GEM_NITER.get());
		tag(TagDC.ItemTag.GEM_SULFUR).add(CoreInit.GEM_SULFUR.get());
		tag(TagDC.ItemTag.GEM_COAL).add(Items.COAL);

		tag(TagDC.ItemTag.FERTILIZER).add(Items.BONE_MEAL);

		// foods
		tag(TagDC.ItemTag.CROP_CHIVES).add(FoodInit.CROP_AL_WILD.get());
		tag(TagDC.ItemTag.CROP_ONION).add(FoodInit.CROP_AL_ONION.get());
		tag(TagDC.ItemTag.CROP_GARLIC).add(FoodInit.CROP_AL_GARLIC.get());
		tag(TagDC.ItemTag.CROP_GOOSEFOOT).add(FoodInit.CROP_AM_GOOSEFOOT.get());
		tag(TagDC.ItemTag.CROP_GLASSWORT).add(FoodInit.CROP_AM_GLASSWORT.get());
		tag(TagDC.ItemTag.CROP_SPINACH).add(FoodInit.CROP_AM_SPINACH.get());
		tag(TagDC.ItemTag.CROP_FENNEL).add(FoodInit.CROP_AP_FENNEL.get());
		tag(TagDC.ItemTag.CROP_CELERY).add(FoodInit.CROP_AP_CELERY.get());
		tag(TagDC.ItemTag.CROP_PARSNIP).add(FoodInit.CROP_AP_PARSNIP.get());
		tag(TagDC.ItemTag.CROP_RAPESEED).add(FoodInit.CROP_BR_RAPESEED.get());
		tag(TagDC.ItemTag.CROP_NAPA).add(FoodInit.CROP_BR_GREEN.get());
		tag(TagDC.ItemTag.CROP_CABAGGE).add(FoodInit.CROP_BR_CABAGGE.get());
		tag(TagDC.ItemTag.CROP_RADISH).add(FoodInit.CROP_BR_RADISH.get());
		tag(TagDC.ItemTag.CROP_CHILI).add(FoodInit.CROP_CA_CHILI.get());
		tag(TagDC.ItemTag.CROP_PAPRIKA).add(FoodInit.CROP_CA_PAPRIKA.get());
		tag(TagDC.ItemTag.CROP_BELL).add(FoodInit.CROP_CA_BELL.get());
		tag(TagDC.ItemTag.CROP_OAT).add(FoodInit.CROP_CR_OAT.get());
		tag(TagDC.ItemTag.CROP_RYE).add(FoodInit.CROP_CR_RYE.get());
		tag(TagDC.ItemTag.CROP_BARLEY).add(FoodInit.CROP_CR_BARLEY.get());
		tag(TagDC.ItemTag.CROP_GINGER).add(FoodInit.CROP_GN_COMMON.get());
		tag(TagDC.ItemTag.CROP_CARDAMOM).add(FoodInit.CROP_GN_CARDAMOM.get());
		tag(TagDC.ItemTag.CROP_TURMERIC).add(FoodInit.CROP_GN_TURMERIC.get());
		tag(TagDC.ItemTag.CROP_MINT).add(FoodInit.CROP_HB_MINT.get());
		tag(TagDC.ItemTag.CROP_BASIL).add(FoodInit.CROP_HB_BASIL.get());
		tag(TagDC.ItemTag.CROP_PERILLA).add(FoodInit.CROP_HB_PERILLA.get());
		tag(TagDC.ItemTag.CROP_LAVENDER).add(FoodInit.CROP_HB_LAVENDER.get());
		tag(TagDC.ItemTag.CROP_SORREL).add(FoodInit.CROP_KN_SORREL.get());
		tag(TagDC.ItemTag.CROP_BUCKWHEAT).add(FoodInit.CROP_KN_BUCKWHEAT.get());
		tag(TagDC.ItemTag.CROP_INDIGO).add(FoodInit.CROP_KN_INDIGO.get());
		tag(TagDC.ItemTag.CROP_JUTE).add(FoodInit.CROP_ML_JUTE.get());
		tag(TagDC.ItemTag.CROP_COTTON).add(FoodInit.CROP_ML_COTTON.get());
		tag(TagDC.ItemTag.CROP_BLUE_MALLOW).add(FoodInit.CROP_ML_BLUE.get());
		tag(TagDC.ItemTag.CROP_BINDWEED).add(FoodInit.CROP_MO_BINDWEED.get());
		tag(TagDC.ItemTag.CROP_WATER_SPINACH).add(FoodInit.CROP_MO_WATER.get());
		tag(TagDC.ItemTag.CROP_SWEET_POTATO).add(FoodInit.CROP_MO_POTATO.get());
		tag(TagDC.ItemTag.CROP_MORNING_GLORY).add(FoodInit.CROP_MO_FLOWER.get());
		tag(TagDC.ItemTag.CROP_GREEN_PEAS).add(FoodInit.CROP_PE_GREEN.get());
		tag(TagDC.ItemTag.CROP_GARBANZO).add(FoodInit.CROP_PE_GARBANZO.get());
		tag(TagDC.ItemTag.CROP_SOY).add(FoodInit.CROP_PE_SOY.get());
		tag(TagDC.ItemTag.CROP_ADZUKI).add(FoodInit.CROP_PE_ADZUKI.get());
		tag(TagDC.ItemTag.CROP_REED).add(FoodInit.CROP_RE_COMMON.get());
		tag(TagDC.ItemTag.CROP_SORGHUM).add(FoodInit.CROP_RE_SORGHUM.get());
		tag(TagDC.ItemTag.CROP_CORN).add(FoodInit.CROP_RE_CORN.get());
		tag(TagDC.ItemTag.CROP_WILD_RICE).add(FoodInit.CROP_RI_ZIZANIA.get());
		tag(TagDC.ItemTag.CROP_RICE).add(FoodInit.CROP_RI_SHORT.get(), FoodInit.CROP_RI_AROMA.get());
		tag(TagDC.ItemTag.CROP_AROMA_RICE).add(FoodInit.CROP_RI_AROMA.get());
		tag(TagDC.ItemTag.CROP_NIGHTSHADE).add(FoodInit.CROP_SL_NIGHTSHADE.get());
		tag(TagDC.ItemTag.CROP_EGGPLANT).add(FoodInit.CROP_SL_EGGPLANT.get());
		tag(TagDC.ItemTag.CROP_TOMATO).add(FoodInit.CROP_SL_TOMATO.get());
		tag(TagDC.ItemTag.CROP_LANTERN).add(FoodInit.CROP_SL_LANTERN.get());

		tag(TagDC.ItemTag.CROP_APPLE).add(Items.APPLE);
		tag(TagDC.ItemTag.CROP_COCOA).add(Items.COCOA_BEANS);

		tag(TagDC.ItemTag.CROP_CEREALS).addTags(TagDC.ItemTag.CROP_OAT, TagDC.ItemTag.CROP_RYE, TagDC.ItemTag.CROP_BARLEY,
			TagDC.ItemTag.CROP_WILD_RICE, TagDC.ItemTag.CROP_RICE, TagDC.ItemTag.CROP_AROMA_RICE, Tags.Items.CROPS_WHEAT);

		tag(TagDC.ItemTag.CROP_MILLETS).addTag(TagDC.ItemTag.CROP_SORGHUM);

		tag(TagDC.ItemTag.CROP_PSEUDOCEREALS).addTags(TagDC.ItemTag.CROP_GOOSEFOOT, TagDC.ItemTag.CROP_BUCKWHEAT);

		tag(TagDC.ItemTag.CROP_GREEN_LEAFS).addTags(TagDC.ItemTag.CROP_GOOSEFOOT, TagDC.ItemTag.CROP_SPINACH, TagDC.ItemTag.CROP_NAPA,
			TagDC.ItemTag.CROP_RAPESEED, TagDC.ItemTag.CROP_CABAGGE, TagDC.ItemTag.CROP_FENNEL, TagDC.ItemTag.CROP_CELERY,
			TagDC.ItemTag.CROP_SORREL, TagDC.ItemTag.CROP_INDIGO, TagDC.ItemTag.CROP_JUTE, TagDC.ItemTag.CROP_WATER_SPINACH);

		tag(TagDC.ItemTag.CROP_BEANS).addTags(TagDC.ItemTag.CROP_GREEN_PEAS, TagDC.ItemTag.CROP_GARBANZO,
			TagDC.ItemTag.CROP_SOY, TagDC.ItemTag.CROP_ADZUKI);
		tag(TagDC.ItemTag.CROP_SPICES).addTags(TagDC.ItemTag.CROP_CHIVES, TagDC.ItemTag.CROP_GARLIC,
			TagDC.ItemTag.CROP_CHILI, TagDC.ItemTag.CROP_PAPRIKA, TagDC.ItemTag.CROP_GINGER, TagDC.ItemTag.CROP_CARDAMOM,
			TagDC.ItemTag.CROP_TURMERIC);

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
			TagDC.ItemTag.CROP_LANTERN, TagDC.ItemTag.SEED_BLUE_MALLOW);

		tag(TagDC.ItemTag.CROP_FLUITS).addTags(TagDC.ItemTag.CROP_APPLE);

		tag(TagDC.ItemTag.CROP_STRAWS).addTags(TagDC.ItemTag.CROP_OAT, TagDC.ItemTag.CROP_RYE, TagDC.ItemTag.CROP_BARLEY,
			TagDC.ItemTag.CROP_REED, TagDC.ItemTag.CROP_SORGHUM, TagDC.ItemTag.CROP_CORN,
			TagDC.ItemTag.CROP_WILD_RICE, TagDC.ItemTag.CROP_RICE, TagDC.ItemTag.CROP_AROMA_RICE);

		tag(TagDC.ItemTag.CROP_STICKS).addTags(TagDC.ItemTag.CROP_GOOSEFOOT, TagDC.ItemTag.CROP_REED, TagDC.ItemTag.CROP_SORGHUM);

		tag(TagDC.ItemTag.CROP_OILS).addTags(TagDC.ItemTag.CROP_RAPESEED, TagDC.ItemTag.CROP_SOY);

		tag(TagDC.ItemTag.SEED_CHIVES).add(FoodInit.BLOCK_AL_WILD.get().asItem());
		tag(TagDC.ItemTag.SEED_ONION).add(FoodInit.BLOCK_AL_ONION.get().asItem());
		tag(TagDC.ItemTag.SEED_GARLIC).add(FoodInit.BLOCK_AL_GARLIC.get().asItem());
		tag(TagDC.ItemTag.SEED_GOOSEFOOT).add(FoodInit.BLOCK_AM_GOOSEFOOT.get().asItem());
		tag(TagDC.ItemTag.SEED_GLASSWORT).add(FoodInit.BLOCK_AM_GLASSWORT.get().asItem());
		tag(TagDC.ItemTag.SEED_SPINACH).add(FoodInit.BLOCK_AM_SPINACH.get().asItem());
		tag(TagDC.ItemTag.SEED_FENNEL).add(FoodInit.BLOCK_AP_FENNEL.get().asItem());
		tag(TagDC.ItemTag.SEED_CELERY).add(FoodInit.BLOCK_AP_CELERY.get().asItem());
		tag(TagDC.ItemTag.SEED_PARSNIP).add(FoodInit.BLOCK_AP_PARSNIP.get().asItem());
		tag(TagDC.ItemTag.SEED_RAPESEED).add(FoodInit.BLOCK_BR_RAPESEED.get().asItem());
		tag(TagDC.ItemTag.SEED_NAPA).add(FoodInit.BLOCK_BR_GREEN.get().asItem());
		tag(TagDC.ItemTag.SEED_CABAGGE).add(FoodInit.BLOCK_BR_CABAGGE.get().asItem());
		tag(TagDC.ItemTag.SEED_RADISH).add(FoodInit.BLOCK_BR_RADISH.get().asItem());
		tag(TagDC.ItemTag.SEED_CHILI).add(FoodInit.BLOCK_CA_CHILI.get().asItem());
		tag(TagDC.ItemTag.SEED_PAPRIKA).add(FoodInit.BLOCK_CA_PAPRIKA.get().asItem());
		tag(TagDC.ItemTag.SEED_BELL).add(FoodInit.BLOCK_CA_BELL.get().asItem());
		tag(TagDC.ItemTag.SEED_OAT).add(FoodInit.BLOCK_CR_OAT.get().asItem());
		tag(TagDC.ItemTag.SEED_RYE).add(FoodInit.BLOCK_CR_RYE.get().asItem());
		tag(TagDC.ItemTag.SEED_BARLEY).add(FoodInit.BLOCK_CR_BARLEY.get().asItem());
		tag(TagDC.ItemTag.SEED_GINGER).add(FoodInit.BLOCK_GN_COMMON.get().asItem());
		tag(TagDC.ItemTag.SEED_CARDAMOM).add(FoodInit.BLOCK_GN_CARDAMOM.get().asItem());
		tag(TagDC.ItemTag.SEED_TURMERIC).add(FoodInit.BLOCK_GN_TURMERIC.get().asItem());
		tag(TagDC.ItemTag.SEED_MINT).add(FoodInit.BLOCK_HB_MINT.get().asItem());
		tag(TagDC.ItemTag.SEED_BASIL).add(FoodInit.BLOCK_HB_BASIL.get().asItem());
		tag(TagDC.ItemTag.SEED_PERILLA).add(FoodInit.BLOCK_HB_PERILLA.get().asItem());
		tag(TagDC.ItemTag.SEED_LAVENDER).add(FoodInit.BLOCK_HB_LAVENDER.get().asItem());
		tag(TagDC.ItemTag.SEED_SORREL).add(FoodInit.BLOCK_KN_SORREL.get().asItem());
		tag(TagDC.ItemTag.SEED_BUCKWHEAT).add(FoodInit.BLOCK_KN_BUCKWHEAT.get().asItem());
		tag(TagDC.ItemTag.SEED_INDIGO).add(FoodInit.BLOCK_KN_INDIGO.get().asItem());
		tag(TagDC.ItemTag.SEED_JUTE).add(FoodInit.BLOCK_ML_JUTE.get().asItem());
		tag(TagDC.ItemTag.SEED_COTTON).add(FoodInit.BLOCK_ML_COTTON.get().asItem());
		tag(TagDC.ItemTag.SEED_BLUE_MALLOW).add(FoodInit.BLOCK_ML_BLUE.get().asItem());
		tag(TagDC.ItemTag.SEED_BINDWEED).add(FoodInit.BLOCK_MO_BINDWEED.get().asItem());
		tag(TagDC.ItemTag.SEED_WATER_SPINACH).add(FoodInit.BLOCK_MO_WATER.get().asItem());
		tag(TagDC.ItemTag.SEED_SWEET_POTATO).add(FoodInit.BLOCK_MO_POTATO.get().asItem());
		tag(TagDC.ItemTag.SEED_MORNING_GLORY).add(FoodInit.BLOCK_MO_FLOWER.get().asItem());
		tag(TagDC.ItemTag.SEED_GREEN_PEAS).add(FoodInit.BLOCK_PE_GREEN.get().asItem());
		tag(TagDC.ItemTag.SEED_GARBANZO).add(FoodInit.BLOCK_PE_GARBANZO.get().asItem());
		tag(TagDC.ItemTag.SEED_SOY).add(FoodInit.BLOCK_PE_SOY.get().asItem());
		tag(TagDC.ItemTag.SEED_ADZUKI).add(FoodInit.BLOCK_PE_ADZUKI.get().asItem());
		tag(TagDC.ItemTag.SEED_REED).add(FoodInit.BLOCK_RE_COMMON.get().asItem());
		tag(TagDC.ItemTag.SEED_SORGHUM).add(FoodInit.BLOCK_RE_SORGHUM.get().asItem());
		tag(TagDC.ItemTag.SEED_CORN).add(FoodInit.BLOCK_RE_CORN.get().asItem());
		tag(TagDC.ItemTag.SEED_WILD_RICE).add(FoodInit.BLOCK_RI_ZIZANIA.get().asItem());
		tag(TagDC.ItemTag.SEED_RICE).add(FoodInit.BLOCK_RI_SHORT.get().asItem());
		tag(TagDC.ItemTag.SEED_AROMA_RICE).add(FoodInit.BLOCK_RI_AROMA.get().asItem());
		tag(TagDC.ItemTag.SEED_NIGHTSHADE).add(FoodInit.BLOCK_SL_NIGHTSHADE.get().asItem());
		tag(TagDC.ItemTag.SEED_EGGPLANT).add(FoodInit.BLOCK_SL_EGGPLANT.get().asItem());
		tag(TagDC.ItemTag.SEED_TOMATO).add(FoodInit.BLOCK_SL_TOMATO.get().asItem());
		tag(TagDC.ItemTag.SEED_LANTERN).add(FoodInit.BLOCK_SL_LANTERN.get().asItem());

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
		copy(TagDC.BlockTag.DUSTBLOCK_SUS, TagDC.ItemTag.DUSTBLOCK_SUS);
		copy(TagDC.BlockTag.DUSTBLOCK_TOOL_STEEL, TagDC.ItemTag.DUSTBLOCK_TOOL_STEEL);
		copy(TagDC.BlockTag.DUSTBLOCK_MAGNET, TagDC.ItemTag.DUSTBLOCK_MAGNET);
		copy(TagDC.BlockTag.DUSTBLOCK_MANGALLOY, TagDC.ItemTag.DUSTBLOCK_MANGALLOY);
		copy(TagDC.BlockTag.DUSTBLOCK_BSCCO, TagDC.ItemTag.DUSTBLOCK_BSCCO);

		copy(TagDC.BlockTag.METALBLOCK_BRASS, TagDC.ItemTag.METALBLOCK_BRASS);
		copy(TagDC.BlockTag.METALBLOCK_BRONZE, TagDC.ItemTag.METALBLOCK_BRONZE);
		copy(TagDC.BlockTag.METALBLOCK_NICKEL_SILVER, TagDC.ItemTag.METALBLOCK_NICKEL_SILVER);
		copy(TagDC.BlockTag.METALBLOCK_STEEL, TagDC.ItemTag.METALBLOCK_STEEL);
		copy(TagDC.BlockTag.METALBLOCK_SUS, TagDC.ItemTag.METALBLOCK_SUS);
		copy(TagDC.BlockTag.METALBLOCK_TOOL_STEEL, TagDC.ItemTag.METALBLOCK_TOOL_STEEL);
		copy(TagDC.BlockTag.METALBLOCK_MAGNET, TagDC.ItemTag.METALBLOCK_MAGNET);
		copy(TagDC.BlockTag.METALBLOCK_MANGALLOY, TagDC.ItemTag.METALBLOCK_MANGALLOY);
		copy(TagDC.BlockTag.METALBLOCK_BSCCO, TagDC.ItemTag.METALBLOCK_BSCCO);

		copy(TagDC.BlockTag.CROP_GREEN_MANURES, TagDC.ItemTag.CROP_GREEN_MANURES);

		copy(TagDC.BlockTag.CROP_PUMPKIN, TagDC.ItemTag.CROP_PUMPKIN);
		copy(TagDC.BlockTag.CROP_MELON, TagDC.ItemTag.CROP_MELON);

		// vanilla

		tag(ItemTags.LOGS_THAT_BURN).add(FoodInit.LOG_BH_COMMON.get().asItem(), FoodInit.LOG_BH_WALNUT.get().asItem(), FoodInit.LOG_BH_SWEET.get().asItem());

		tag(ItemTags.LEAVES).add(FoodInit.LEAVES_BH_COMMON.get().asItem(), FoodInit.LEAVES_BH_WALNUT.get().asItem(), FoodInit.LEAVES_BH_SWEET.get().asItem());

		tag(ItemTags.PLANKS).add(FoodInit.PLANK_BH_COMMON.get().asItem(), FoodInit.PLANK_BH_WALNUT.get().asItem(), FoodInit.PLANK_BH_SWEET.get().asItem());

	}

}
