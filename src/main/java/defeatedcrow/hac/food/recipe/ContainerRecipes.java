package defeatedcrow.hac.food.recipe;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ContainerRecipes {
	public static ContainerRecipes INSTANCE = new ContainerRecipes() {};

	public final List<Cont> pairs;

	private ContainerRecipes() {
		ImmutableList.Builder<Cont> list = ImmutableList.builder();
		list.add(new Cont("log_oak", FoodInit.CONT_LOG_OAK, () -> Items.OAK_LOG.asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_birch", FoodInit.CONT_LOG_BIRCH, () -> Items.BIRCH_LOG.asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_spruce", FoodInit.CONT_LOG_SPRUCE, () -> Items.SPRUCE_LOG.asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_jangle", FoodInit.CONT_LOG_JUNGLE, () -> Items.JUNGLE_LOG.asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_acacia", FoodInit.CONT_LOG_ACACIA, () -> Items.ACACIA_LOG.asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_darkoak", FoodInit.CONT_LOG_DARKOAK, () -> Items.DARK_OAK_LOG.asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_mangrove", FoodInit.CONT_LOG_MANGROVE, () -> Items.MANGROVE_LOG.asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_crimson", FoodInit.CONT_LOG_CRIMSON, () -> Items.CRIMSON_STEM.asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_warped", FoodInit.CONT_LOG_WARPED, () -> Items.WARPED_STEM.asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_beech_common", FoodInit.CONT_LOG_BH_COMMON, () -> FoodInit.LOG_BH_COMMON.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_beech_walnut", FoodInit.CONT_LOG_BH_WALNUT, () -> FoodInit.LOG_BH_WALNUT.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_beech_sweet", FoodInit.CONT_LOG_BH_SWEET, () -> FoodInit.LOG_BH_SWEET.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_cherry_wild", FoodInit.CONT_LOG_CH_WILD, () -> FoodInit.LOG_CH_WILD.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_cinnamon_camphor", FoodInit.CONT_LOG_CN_CAMPHOR, () -> FoodInit.LOG_CN_CAMPHOR.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_cinnamon_true", FoodInit.CONT_LOG_CN_CINNAMON, () -> FoodInit.LOG_CN_CINNAMON.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_citrus_pomelo", FoodInit.CONT_LOG_CT_POMELO, () -> FoodInit.LOG_CT_POMELO.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_morus_mulberry", FoodInit.CONT_LOG_MR_MULBERRY, () -> FoodInit.LOG_MR_MULBERRY.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_morus_paper", FoodInit.CONT_LOG_MR_PAPER, () -> FoodInit.LOG_MR_PAPER.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_morus_rubber", FoodInit.CONT_LOG_MR_RUBBER, () -> FoodInit.LOG_MR_RUBBER.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_myrtle_eucalyptus", FoodInit.CONT_LOG_MY_EUCALYPTUS, () -> FoodInit.LOG_MY_EUCALYPTUS.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_olive_ash", FoodInit.CONT_LOG_OL_ASH, () -> FoodInit.LOG_OL_ASH.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_palm_coconut", FoodInit.CONT_LOG_PL_COCONUT, () -> FoodInit.LOG_PL_COCONUT.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_sumac_lacquer", FoodInit.CONT_LOG_SU_LACQUER, () -> FoodInit.LOG_SU_LACQUER.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("log_sumac_mango", FoodInit.CONT_LOG_SU_MANGO, () -> FoodInit.LOG_SU_MANGO.get().asItem(), () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("charcoal", FoodInit.CONT_LOG_CHARCOAL, () -> Items.CHARCOAL, () -> TagDC.ItemTag.DUMMY));

		list.add(new Cont("crop_apple", FoodInit.CONT_CROP_APPLE, () -> Items.APPLE, () -> TagDC.ItemTag.CROP_APPLE));
		list.add(new Cont("crop_carrot", FoodInit.CONT_CROP_CARROT, () -> Items.CARROT, () -> Tags.Items.CROPS_CARROT));
		list.add(new Cont("crop_potato", FoodInit.CONT_CROP_POTATO, () -> Items.POTATO, () -> Tags.Items.CROPS_POTATO));
		list.add(new Cont("crop_beet", FoodInit.CONT_CROP_BEET, () -> Items.BEETROOT, () -> Tags.Items.CROPS_BEETROOT));
		list.add(new Cont("crop_pumpkin", FoodInit.CONT_CROP_PUMPKIN, () -> Items.PUMPKIN, () -> TagDC.ItemTag.CROP_PUMPKIN));
		list.add(new Cont("crop_melon", FoodInit.CONT_CROP_MELON, () -> Items.MELON, () -> TagDC.ItemTag.CROP_MELON));
		list.add(new Cont("crop_cactus", FoodInit.CONT_CROP_CACTUS, () -> Items.CACTUS, () -> TagDC.ItemTag.CROP_CACTUS));
		list.add(new Cont("crop_sugarcane", FoodInit.CONT_CROP_SUGARCANE, () -> Items.SUGAR_CANE, () -> TagDC.ItemTag.CROP_SUGAR));
		list.add(new Cont("crop_cocoa", FoodInit.CONT_CROP_COCOA, () -> Items.COCOA_BEANS, () -> TagDC.ItemTag.CROP_COCOA));
		list.add(new Cont("crop_bushberry", FoodInit.CONT_CROP_BUSHBERRY, () -> Items.SWEET_BERRIES, () -> TagDC.ItemTag.DUMMY));
		list.add(new Cont("container_beeswax", FoodInit.CONT_BEESWAX, () -> FoodInit.BEESWAX.get(), () -> TagDC.ItemTag.FOOD_BEESWAX));
		list.add(new Cont("container_treewax", FoodInit.CONT_TREEWAX, () -> FoodInit.TREEWAX.get(), () -> TagDC.ItemTag.FOOD_TREEWAX));
		pairs = list.build();
	}

	public record Cont(
			String name,
			Supplier<Block> output,
			Supplier<Item> input,
			Supplier<TagKey<Item>> inputTag) {}

}
