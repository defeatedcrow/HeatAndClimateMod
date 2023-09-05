package defeatedcrow.hac.core.tag;

import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TagUtil {

	public static final Ingredient BRONZE_OR_BRASS = Ingredient.fromValues(Stream.of(new Ingredient.TagValue(TagDC.ItemTag.INGOT_BRASS), new Ingredient.TagValue(TagDC.ItemTag.INGOT_BRONZE)));

	public static boolean isMatch(String name, TagKey<?> tag) {
		if (tag != null && tag.location() != null) {
			return tag.location().toString().toLowerCase().contains(name);
		}
		return false;
	}

	public static boolean isMatch(String domain, String name, TagKey<?> tag) {
		if (tag != null && tag.location() != null) {
			return tag.location().toString().toLowerCase().contains(domain) && tag.location().toString().toLowerCase().contains(name);
		}
		return false;
	}

	public static boolean isMatch(ResourceLocation dic, TagKey<?> tag) {
		if (tag != null && tag.location() != null) {
			return isMatch(dic.getNamespace(), tag) && isMatch(dic.getPath(), tag);
		}
		return false;
	}

	public static Optional<TagKey<Item>> matchTag(String name, ItemLike item) {
		ItemStack stack = new ItemStack(item);
		return stack.getTags().filter((tag) -> isMatch(name, tag)).findAny();
	}

	public static Optional<TagKey<Item>> matchTag(ResourceLocation dic, ItemLike item) {
		ItemStack stack = new ItemStack(item);
		return stack.getTags().filter((tag) -> isMatch(dic, tag)).findAny();
	}

	public static Optional<TagKey<Block>> matchTag(String name, BlockState state) {
		return state.getTags().filter((tag) -> isMatch(name, tag)).findAny();
	}

	public static Optional<TagKey<Block>> matchTag(ResourceLocation dic, BlockState state) {
		return state.getTags().filter((tag) -> isMatch(dic, tag)).findAny();
	}

	public static Optional<TagKey<Biome>> matchTag(String name, Holder<Biome> biome) {
		return biome.getTagKeys().filter((tag) -> isMatch(name, tag)).findAny();
	}
}
