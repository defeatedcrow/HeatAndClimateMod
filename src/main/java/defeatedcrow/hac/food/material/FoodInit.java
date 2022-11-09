package defeatedcrow.hac.food.material;

import java.util.function.Supplier;

import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate_Food;
import defeatedcrow.hac.core.material.tag.TagDC;
import defeatedcrow.hac.food.material.block.CropBlockAllium;
import defeatedcrow.hac.food.material.block.CropBlockAmaranth;
import defeatedcrow.hac.food.material.block.CropBlockApium;
import defeatedcrow.hac.food.material.block.CropBlockBrassica;
import defeatedcrow.hac.food.material.block.CropBlockCapsicum;
import defeatedcrow.hac.food.material.block.CropBlockCereals;
import defeatedcrow.hac.food.material.block.CropBlockGinger;
import defeatedcrow.hac.food.material.block.CropBlockHerb;
import defeatedcrow.hac.food.material.block.CropBlockKnotweed;
import defeatedcrow.hac.food.material.block.CropBlockMallow;
import defeatedcrow.hac.food.material.block.CropBlockMorningGlory;
import defeatedcrow.hac.food.material.block.CropBlockPeas;
import defeatedcrow.hac.food.material.block.CropBlockReed;
import defeatedcrow.hac.food.material.block.CropBlockRice;
import defeatedcrow.hac.food.material.block.CropBlockSolanum;
import defeatedcrow.hac.food.material.block.FertileBlock;
import defeatedcrow.hac.food.material.block.LeavesBeech;
import defeatedcrow.hac.food.material.block.LeavesSweet;
import defeatedcrow.hac.food.material.block.LeavesWalnut;
import defeatedcrow.hac.food.material.block.LogBlockDC;
import defeatedcrow.hac.food.material.block.PlankBlockDC;
import defeatedcrow.hac.food.material.block.SaplingBeech;
import defeatedcrow.hac.food.material.item.ItemCropDC;
import defeatedcrow.hac.food.material.item.SeedItemDC;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class FoodInit {

	public static final CreativeModeTab FOOD = new CreativeTabClimate_Food("food");

	public static void init() {}

	public static final RegistryObject<Item> CROP_AL_WILD = regCrop(CropTier.WILD, CropType.ALLIUM, TagDC.ItemTag.CROP_CHIVES);
	public static final RegistryObject<Item> CROP_AL_ONION = regCrop(CropTier.COMMON, CropType.ALLIUM, TagDC.ItemTag.CROP_ONION);
	public static final RegistryObject<Item> CROP_AL_GARLIC = regCrop(CropTier.RARE, CropType.ALLIUM, TagDC.ItemTag.CROP_GARLIC);
	public static final RegistryObject<Item> CROP_AM_GOOSEFOOT = regCrop(CropTier.WILD, CropType.AMARANTH, TagDC.ItemTag.CROP_GOOSEFOOT);
	public static final RegistryObject<Item> CROP_AM_GLASSWORT = regCrop(CropTier.COMMON, CropType.AMARANTH, TagDC.ItemTag.CROP_GLASSWORT);
	public static final RegistryObject<Item> CROP_AM_SPINACH = regCrop(CropTier.RARE, CropType.AMARANTH, TagDC.ItemTag.CROP_SPINACH);
	public static final RegistryObject<Item> CROP_AP_CELERY = regCrop(CropTier.WILD, CropType.APIUM, TagDC.ItemTag.CROP_CELERY);
	public static final RegistryObject<Item> CROP_AP_FENNEL = regCrop(CropTier.COMMON, CropType.APIUM, TagDC.ItemTag.CROP_FENNEL);
	public static final RegistryObject<Item> CROP_AP_PARSNIP = regCrop(CropTier.RARE, CropType.APIUM, TagDC.ItemTag.CROP_PARSNIP);
	public static final RegistryObject<Item> CROP_BR_RAPESEED = regCrop(CropTier.WILD, CropType.BRASSICA, TagDC.ItemTag.CROP_RAPESEED);
	public static final RegistryObject<Item> CROP_BR_GREEN = regCrop(CropTier.COMMON, CropType.BRASSICA, TagDC.ItemTag.CROP_NAPA);
	public static final RegistryObject<Item> CROP_BR_CABAGGE = regCrop(CropTier.RARE, CropType.BRASSICA, TagDC.ItemTag.CROP_CABAGGE);
	public static final RegistryObject<Item> CROP_BR_RADISH = regCrop(CropTier.EPIC, CropType.BRASSICA, TagDC.ItemTag.CROP_RADISH);
	public static final RegistryObject<Item> CROP_CA_CHILI = regCrop(CropTier.WILD, CropType.CAPSICUM, TagDC.ItemTag.CROP_CHILI);
	public static final RegistryObject<Item> CROP_CA_PAPRIKA = regCrop(CropTier.COMMON, CropType.CAPSICUM, TagDC.ItemTag.CROP_PAPRIKA);
	public static final RegistryObject<Item> CROP_CA_BELL = regCrop(CropTier.RARE, CropType.CAPSICUM, TagDC.ItemTag.CROP_BELL);
	public static final RegistryObject<Item> CROP_CR_OAT = regCrop(CropTier.WILD, CropType.CEREALS, TagDC.ItemTag.CROP_OAT);
	public static final RegistryObject<Item> CROP_CR_RYE = regCrop(CropTier.COMMON, CropType.CEREALS, TagDC.ItemTag.CROP_RYE);
	public static final RegistryObject<Item> CROP_CR_BARLEY = regCrop(CropTier.RARE, CropType.CEREALS, TagDC.ItemTag.CROP_BARLEY);
	public static final RegistryObject<Item> CROP_GN_COMMON = regCrop(CropTier.WILD, CropType.GINGER, TagDC.ItemTag.CROP_GINGER);
	public static final RegistryObject<Item> CROP_GN_CARDAMOM = regCrop(CropTier.COMMON, CropType.GINGER, TagDC.ItemTag.CROP_CARDAMOM);
	public static final RegistryObject<Item> CROP_GN_TURMERIC = regCrop(CropTier.RARE, CropType.GINGER, TagDC.ItemTag.CROP_TURMERIC);
	public static final RegistryObject<Item> CROP_HB_MINT = regCrop(CropTier.WILD, CropType.HERB, TagDC.ItemTag.CROP_MINT);
	public static final RegistryObject<Item> CROP_HB_BASIL = regCrop(CropTier.COMMON, CropType.HERB, TagDC.ItemTag.CROP_BASIL);
	public static final RegistryObject<Item> CROP_HB_PERILLA = regCrop(CropTier.RARE, CropType.HERB, TagDC.ItemTag.CROP_PERILLA);
	public static final RegistryObject<Item> CROP_HB_LAVENDER = regCrop(CropTier.EPIC, CropType.HERB, TagDC.ItemTag.CROP_LAVENDER);
	public static final RegistryObject<Item> CROP_KN_SORREL = regCrop(CropTier.WILD, CropType.KNOTWEED, TagDC.ItemTag.CROP_SORREL);
	public static final RegistryObject<Item> CROP_KN_BUCKWHEAT = regCrop(CropTier.COMMON, CropType.KNOTWEED, TagDC.ItemTag.CROP_BINDWEED);
	public static final RegistryObject<Item> CROP_KN_INDIGO = regCrop(CropTier.RARE, CropType.KNOTWEED, TagDC.ItemTag.CROP_INDIGO);
	public static final RegistryObject<Item> CROP_ML_JUTE = regCrop(CropTier.WILD, CropType.MALLOW, TagDC.ItemTag.CROP_JUTE);
	public static final RegistryObject<Item> CROP_ML_COTTON = regCrop(CropTier.COMMON, CropType.MALLOW, TagDC.ItemTag.CROP_COTTON);
	public static final RegistryObject<Item> CROP_ML_BLUE = regCrop(CropTier.RARE, CropType.MALLOW, TagDC.ItemTag.CROP_BLUE_MALLOW);
	public static final RegistryObject<Item> CROP_MO_BINDWEED = regCrop(CropTier.WILD, CropType.MORNINGGLORY, TagDC.ItemTag.CROP_BINDWEED);
	public static final RegistryObject<Item> CROP_MO_WATER = regCrop(CropTier.COMMON, CropType.MORNINGGLORY, TagDC.ItemTag.CROP_WATER_SPINACH);
	public static final RegistryObject<Item> CROP_MO_POTATO = regCrop(CropTier.RARE, CropType.MORNINGGLORY, TagDC.ItemTag.CROP_SWEET_POTATO);
	public static final RegistryObject<Item> CROP_MO_FLOWER = regCrop(CropTier.EPIC, CropType.MORNINGGLORY, TagDC.ItemTag.CROP_MORNING_GLORY);
	public static final RegistryObject<Item> CROP_PE_GREEN = regCrop(CropTier.WILD, CropType.PEAS, TagDC.ItemTag.CROP_GARBANZO);
	public static final RegistryObject<Item> CROP_PE_GARBANZO = regCrop(CropTier.COMMON, CropType.PEAS, TagDC.ItemTag.CROP_GREEN_PEAS);
	public static final RegistryObject<Item> CROP_PE_SOY = regCrop(CropTier.RARE, CropType.PEAS, TagDC.ItemTag.CROP_SOY);
	public static final RegistryObject<Item> CROP_PE_ADZUKI = regCrop(CropTier.EPIC, CropType.PEAS, TagDC.ItemTag.CROP_ADZUKI);
	public static final RegistryObject<Item> CROP_RE_COMMON = regCrop(CropTier.WILD, CropType.REED, TagDC.ItemTag.CROP_REED);
	public static final RegistryObject<Item> CROP_RE_SORGHUM = regCrop(CropTier.COMMON, CropType.REED, TagDC.ItemTag.CROP_SORGHUM);
	public static final RegistryObject<Item> CROP_RE_CORN = regCrop(CropTier.RARE, CropType.REED, TagDC.ItemTag.CROP_CORN);
	public static final RegistryObject<Item> CROP_RI_ZIZANIA = regCrop(CropTier.WILD, CropType.RICE, TagDC.ItemTag.CROP_WILD_RICE);
	public static final RegistryObject<Item> CROP_RI_SHORT = regCrop(CropTier.COMMON, CropType.RICE, TagDC.ItemTag.CROP_RICE);
	public static final RegistryObject<Item> CROP_RI_AROMA = regCrop(CropTier.RARE, CropType.RICE, TagDC.ItemTag.CROP_AROMA_RICE);
	public static final RegistryObject<Item> CROP_SL_NIGHTSHADE = regCrop(CropTier.WILD, CropType.SOLANUM, TagDC.ItemTag.CROP_NIGHTSHADE);
	public static final RegistryObject<Item> CROP_SL_EGGPLANT = regCrop(CropTier.COMMON, CropType.SOLANUM, TagDC.ItemTag.CROP_EGGPLANT);
	public static final RegistryObject<Item> CROP_SL_TOMATO = regCrop(CropTier.RARE, CropType.SOLANUM, TagDC.ItemTag.CROP_TOMATO);
	public static final RegistryObject<Item> CROP_SL_LANTERN = regCrop(CropTier.EPIC, CropType.SOLANUM, TagDC.ItemTag.CROP_LANTERN);

	public static final RegistryObject<Item> CROP_BH_COMMON = regCrop(CropTier.WILD, CropType.BEECH, null);
	public static final RegistryObject<Item> CROP_BH_WALNUT = regCrop(CropTier.COMMON, CropType.BEECH, null);
	public static final RegistryObject<Item> CROP_BH_SWEET = regCrop(CropTier.RARE, CropType.BEECH, null);

	public static final RegistryObject<Block> BLOCK_AL_WILD = regSeed(CropTier.WILD, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.WILD), TagDC.ItemTag.SEED_CHIVES);
	public static final RegistryObject<Block> BLOCK_AL_ONION = regSeed(CropTier.COMMON, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.COMMON), TagDC.ItemTag.SEED_ONION);
	public static final RegistryObject<Block> BLOCK_AL_GARLIC = regSeed(CropTier.RARE, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.RARE), TagDC.ItemTag.SEED_GARLIC);
	public static final RegistryObject<Block> BLOCK_AM_GOOSEFOOT = regSeed(CropTier.WILD, CropType.AMARANTH, () -> new CropBlockAmaranth(CropTier.WILD), TagDC.ItemTag.SEED_GOOSEFOOT);
	public static final RegistryObject<Block> BLOCK_AM_GLASSWORT = regSeed(CropTier.COMMON, CropType.AMARANTH, () -> new CropBlockAmaranth(CropTier.COMMON), TagDC.ItemTag.SEED_GLASSWORT);
	public static final RegistryObject<Block> BLOCK_AM_SPINACH = regSeed(CropTier.RARE, CropType.AMARANTH, () -> new CropBlockAmaranth(CropTier.RARE), TagDC.ItemTag.SEED_SPINACH);
	public static final RegistryObject<Block> BLOCK_AP_CELERY = regSeed(CropTier.WILD, CropType.APIUM, () -> new CropBlockApium(CropTier.WILD), TagDC.ItemTag.SEED_CELERY);
	public static final RegistryObject<Block> BLOCK_AP_FENNEL = regSeed(CropTier.COMMON, CropType.APIUM, () -> new CropBlockApium(CropTier.COMMON), TagDC.ItemTag.SEED_FENNEL);
	public static final RegistryObject<Block> BLOCK_AP_PARSNIP = regSeed(CropTier.RARE, CropType.APIUM, () -> new CropBlockApium(CropTier.RARE), TagDC.ItemTag.SEED_PARSNIP);
	public static final RegistryObject<Block> BLOCK_BR_RAPESEED = regSeed(CropTier.WILD, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.WILD), TagDC.ItemTag.SEED_RAPESEED);
	public static final RegistryObject<Block> BLOCK_BR_GREEN = regSeed(CropTier.COMMON, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.COMMON), TagDC.ItemTag.SEED_NAPA);
	public static final RegistryObject<Block> BLOCK_BR_CABAGGE = regSeed(CropTier.RARE, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.RARE), TagDC.ItemTag.SEED_CABAGGE);
	public static final RegistryObject<Block> BLOCK_BR_RADISH = regSeed(CropTier.EPIC, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.EPIC), TagDC.ItemTag.SEED_RADISH);
	public static final RegistryObject<Block> BLOCK_CA_CHILI = regSeed(CropTier.WILD, CropType.CAPSICUM, () -> new CropBlockCapsicum(CropTier.WILD), TagDC.ItemTag.SEED_CHILI);
	public static final RegistryObject<Block> BLOCK_CA_PAPRIKA = regSeed(CropTier.COMMON, CropType.CAPSICUM, () -> new CropBlockCapsicum(CropTier.COMMON), TagDC.ItemTag.SEED_PAPRIKA);
	public static final RegistryObject<Block> BLOCK_CA_BELL = regSeed(CropTier.RARE, CropType.CAPSICUM, () -> new CropBlockCapsicum(CropTier.RARE), TagDC.ItemTag.SEED_BELL);
	public static final RegistryObject<Block> BLOCK_CR_OAT = regSeed(CropTier.WILD, CropType.CEREALS, () -> new CropBlockCereals(CropTier.WILD), TagDC.ItemTag.SEED_OAT);
	public static final RegistryObject<Block> BLOCK_CR_RYE = regSeed(CropTier.COMMON, CropType.CEREALS, () -> new CropBlockCereals(CropTier.COMMON), TagDC.ItemTag.SEED_RYE);
	public static final RegistryObject<Block> BLOCK_CR_BARLEY = regSeed(CropTier.RARE, CropType.CEREALS, () -> new CropBlockCereals(CropTier.RARE), TagDC.ItemTag.SEED_BARLEY);
	public static final RegistryObject<Block> BLOCK_GN_COMMON = regSeed(CropTier.WILD, CropType.GINGER, () -> new CropBlockGinger(CropTier.WILD), TagDC.ItemTag.SEED_GINGER);
	public static final RegistryObject<Block> BLOCK_GN_CARDAMOM = regSeed(CropTier.COMMON, CropType.GINGER, () -> new CropBlockGinger(CropTier.COMMON), TagDC.ItemTag.SEED_CARDAMOM);
	public static final RegistryObject<Block> BLOCK_GN_TURMERIC = regSeed(CropTier.RARE, CropType.GINGER, () -> new CropBlockGinger(CropTier.RARE), TagDC.ItemTag.SEED_TURMERIC);
	public static final RegistryObject<Block> BLOCK_HB_MINT = regSeed(CropTier.WILD, CropType.HERB, () -> new CropBlockHerb(CropTier.WILD), TagDC.ItemTag.SEED_MINT);
	public static final RegistryObject<Block> BLOCK_HB_BASIL = regSeed(CropTier.COMMON, CropType.HERB, () -> new CropBlockHerb(CropTier.COMMON), TagDC.ItemTag.SEED_BASIL);
	public static final RegistryObject<Block> BLOCK_HB_PERILLA = regSeed(CropTier.RARE, CropType.HERB, () -> new CropBlockHerb(CropTier.RARE), TagDC.ItemTag.SEED_PERILLA);
	public static final RegistryObject<Block> BLOCK_HB_LAVENDER = regSeed(CropTier.EPIC, CropType.HERB, () -> new CropBlockHerb(CropTier.EPIC), TagDC.ItemTag.SEED_LAVENDER);
	public static final RegistryObject<Block> BLOCK_KN_SORREL = regSeed(CropTier.WILD, CropType.KNOTWEED, () -> new CropBlockKnotweed(CropTier.WILD), TagDC.ItemTag.SEED_SORREL);
	public static final RegistryObject<Block> BLOCK_KN_BUCKWHEAT = regSeed(CropTier.COMMON, CropType.KNOTWEED, () -> new CropBlockKnotweed(CropTier.COMMON), TagDC.ItemTag.SEED_BUCKWHEAT);
	public static final RegistryObject<Block> BLOCK_KN_INDIGO = regSeed(CropTier.RARE, CropType.KNOTWEED, () -> new CropBlockKnotweed(CropTier.RARE), TagDC.ItemTag.SEED_INDIGO);
	public static final RegistryObject<Block> BLOCK_ML_JUTE = regSeed(CropTier.WILD, CropType.MALLOW, () -> new CropBlockMallow(CropTier.WILD), TagDC.ItemTag.SEED_JUTE);
	public static final RegistryObject<Block> BLOCK_ML_COTTON = regSeed(CropTier.COMMON, CropType.MALLOW, () -> new CropBlockMallow(CropTier.COMMON), TagDC.ItemTag.SEED_COTTON);
	public static final RegistryObject<Block> BLOCK_ML_BLUE = regSeed(CropTier.RARE, CropType.MALLOW, () -> new CropBlockMallow(CropTier.RARE), TagDC.ItemTag.SEED_BLUE_MALLOW);
	public static final RegistryObject<Block> BLOCK_MO_BINDWEED = regSeed(CropTier.WILD, CropType.MORNINGGLORY, () -> new CropBlockMorningGlory(CropTier.WILD), TagDC.ItemTag.SEED_BINDWEED);
	public static final RegistryObject<Block> BLOCK_MO_WATER = regSeed(CropTier.COMMON, CropType.MORNINGGLORY, () -> new CropBlockMorningGlory(CropTier.COMMON), TagDC.ItemTag.SEED_WATER_SPINACH);
	public static final RegistryObject<Block> BLOCK_MO_POTATO = regSeed(CropTier.RARE, CropType.MORNINGGLORY, () -> new CropBlockMorningGlory(CropTier.RARE), TagDC.ItemTag.SEED_SWEET_POTATO);
	public static final RegistryObject<Block> BLOCK_MO_FLOWER = regSeed(CropTier.EPIC, CropType.MORNINGGLORY, () -> new CropBlockMorningGlory(CropTier.EPIC), TagDC.ItemTag.SEED_MORNING_GLORY);
	public static final RegistryObject<Block> BLOCK_PE_GREEN = regSeed(CropTier.WILD, CropType.PEAS, () -> new CropBlockPeas(CropTier.WILD), TagDC.ItemTag.SEED_GREEN_PEAS);
	public static final RegistryObject<Block> BLOCK_PE_GARBANZO = regSeed(CropTier.COMMON, CropType.PEAS, () -> new CropBlockPeas(CropTier.COMMON), TagDC.ItemTag.SEED_GARBANZO);
	public static final RegistryObject<Block> BLOCK_PE_SOY = regSeed(CropTier.RARE, CropType.PEAS, () -> new CropBlockPeas(CropTier.RARE), TagDC.ItemTag.SEED_SOY);
	public static final RegistryObject<Block> BLOCK_PE_ADZUKI = regSeed(CropTier.EPIC, CropType.PEAS, () -> new CropBlockPeas(CropTier.EPIC), TagDC.ItemTag.SEED_ADZUKI);
	public static final RegistryObject<Block> BLOCK_RE_COMMON = regSeed(CropTier.WILD, CropType.REED, () -> new CropBlockReed(CropTier.WILD), TagDC.ItemTag.SEED_REED);
	public static final RegistryObject<Block> BLOCK_RE_SORGHUM = regSeed(CropTier.COMMON, CropType.REED, () -> new CropBlockReed(CropTier.COMMON), TagDC.ItemTag.SEED_SORGHUM);
	public static final RegistryObject<Block> BLOCK_RE_CORN = regSeed(CropTier.RARE, CropType.REED, () -> new CropBlockReed(CropTier.RARE), TagDC.ItemTag.SEED_CORN);
	public static final RegistryObject<Block> BLOCK_RI_ZIZANIA = regSeed(CropTier.WILD, CropType.RICE, () -> new CropBlockRice(CropTier.WILD), TagDC.ItemTag.SEED_WILD_RICE);
	public static final RegistryObject<Block> BLOCK_RI_SHORT = regSeed(CropTier.COMMON, CropType.RICE, () -> new CropBlockRice(CropTier.COMMON), TagDC.ItemTag.SEED_RICE);
	public static final RegistryObject<Block> BLOCK_RI_AROMA = regSeed(CropTier.RARE, CropType.RICE, () -> new CropBlockRice(CropTier.RARE), TagDC.ItemTag.SEED_AROMA_RICE);
	public static final RegistryObject<Block> BLOCK_SL_NIGHTSHADE = regSeed(CropTier.WILD, CropType.SOLANUM, () -> new CropBlockSolanum(CropTier.WILD), TagDC.ItemTag.SEED_NIGHTSHADE);
	public static final RegistryObject<Block> BLOCK_SL_EGGPLANT = regSeed(CropTier.COMMON, CropType.SOLANUM, () -> new CropBlockSolanum(CropTier.COMMON), TagDC.ItemTag.SEED_EGGPLANT);
	public static final RegistryObject<Block> BLOCK_SL_TOMATO = regSeed(CropTier.RARE, CropType.SOLANUM, () -> new CropBlockSolanum(CropTier.RARE), TagDC.ItemTag.SEED_TOMATO);
	public static final RegistryObject<Block> BLOCK_SL_LANTERN = regSeed(CropTier.EPIC, CropType.SOLANUM, () -> new CropBlockSolanum(CropTier.EPIC), TagDC.ItemTag.SEED_LANTERN);

	public static final RegistryObject<Block> BLOCK_BH_COMMON = regCropBlock(CropTier.WILD, CropType.BEECH, () -> new SaplingBeech(CropTier.WILD), "sapling", null);
	public static final RegistryObject<Block> BLOCK_BH_WALNUT = regCropBlock(CropTier.COMMON, CropType.BEECH, () -> new SaplingBeech(CropTier.COMMON), "sapling", null);
	public static final RegistryObject<Block> BLOCK_BH_SWEET = regCropBlock(CropTier.RARE, CropType.BEECH, () -> new SaplingBeech(CropTier.RARE), "sapling", null);

	public static final RegistryObject<Block> LEAVES_BH_COMMON = regBlock("leaves_beech_common", () -> new LeavesBeech(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_BH_WALNUT = regBlock("leaves_beech_walnut", () -> new LeavesWalnut(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_BH_SWEET = regBlock("leaves_beech_sweet", () -> new LeavesSweet(), ItemTags.LEAVES);

	public static final RegistryObject<Block> LOG_BH_COMMON = regBlock("log_beech_common", () -> new LogBlockDC(CropType.BEECH, CropTier.WILD), ItemTags.LOGS);
	public static final RegistryObject<Block> LOG_BH_WALNUT = regBlock("log_beech_walnut", () -> new LogBlockDC(CropType.BEECH, CropTier.COMMON), ItemTags.LOGS);
	public static final RegistryObject<Block> LOG_BH_SWEET = regBlock("log_beech_sweet", () -> new LogBlockDC(CropType.BEECH, CropTier.RARE), ItemTags.LOGS);

	public static final RegistryObject<Block> PLANK_BH_COMMON = regBlock("plank_beech_common", () -> new PlankBlockDC("plank_beech_common"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_BH_WALNUT = regBlock("plank_beech_walnut", () -> new PlankBlockDC("plank_beech_walnut"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_BH_SWEET = regBlock("plank_beech_sweet", () -> new PlankBlockDC("plank_beech_sweet"), ItemTags.PLANKS);

	public static final RegistryObject<Block> FERTILE = regBlock("fertile", () -> new FertileBlock(), null);

	public static RegistryObject<Item> regCrop(CropTier tier, CropType type, TagKey<Item> tag) {
		String name = "crop_" + type.toString() + "_" + tier.toString();
		return CoreInit.ITEMS.register("food/" + name, () -> new ItemCropDC(tier, type, name, tag));
	}

	public static RegistryObject<Item> regItem(String name, Supplier<Item> item) {
		return CoreInit.ITEMS.register("food/" + name, item);
	}

	public static RegistryObject<Block> regBlock(String name, Supplier<Block> block, TagKey<Item> tag) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("food/" + name, block);
		regItem(name, () -> new BlockItemDC(name, obj.get(), new Item.Properties().tab(FOOD), tag));
		return obj;
	}

	public static RegistryObject<Block> regSeed(CropTier tier, CropType type, Supplier<Block> block, TagKey<Item> tag) {
		return regCropBlock(tier, type, block, "cropblock", tag);
	}

	public static RegistryObject<Block> regCropBlock(CropTier tier, CropType type, Supplier<Block> block, String s, TagKey<Item> tag) {
		String name = s + "_" + type.toString() + "_" + tier.toString();
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("food/" + name, block);
		regItem(name, () -> new SeedItemDC(tier, type, obj.get(), name, tag));
		return obj;
	}

}
