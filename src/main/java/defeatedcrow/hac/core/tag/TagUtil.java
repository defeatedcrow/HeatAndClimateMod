package defeatedcrow.hac.core.tag;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

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
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

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
		return stack.getTags().filter((tag) -> !isMatch("_ban", tag))
				.filter((tag) -> !isMatch("_but_", tag))
				.filter((tag) -> !isMatch("all", tag))
				.filter((tag) -> !isMatch("taste", tag))
				.filter((tag) -> isMatch(name, tag)).findAny();
	}

	public static Optional<TagKey<Item>> matchTag(String name, String exclusion, ItemLike item) {
		ItemStack stack = new ItemStack(item);
		return stack.getTags().filter((tag) -> exclusion.isBlank() || !isMatch(exclusion, tag)).filter((tag) -> isMatch(name, tag)).findAny();
	}

	public static Optional<TagKey<Item>> matchTag(ResourceLocation dic, ItemLike item) {
		ItemStack stack = new ItemStack(item);
		return stack.getTags().filter((tag) -> isMatch(dic, tag)).findAny();
	}

	public static Optional<TagKey<Block>> matchTag(String name, BlockState state) {
		return state.getTags().filter((tag) -> !isMatch("_ban", tag))
				.filter((tag) -> !isMatch("_but_", tag))
				.filter((tag) -> !isMatch("all", tag))
				.filter((tag) -> !isMatch("taste", tag))
				.filter((tag) -> isMatch(name, tag)).findAny();
	}

	public static Optional<TagKey<Block>> matchTag(String name, String exclusion, BlockState state) {
		return state.getTags().filter((tag) -> exclusion.isBlank() || !isMatch(exclusion, tag)).filter((tag) -> isMatch(name, tag)).findAny();
	}

	public static Optional<TagKey<Block>> matchTag(ResourceLocation dic, BlockState state) {
		return state.getTags().filter((tag) -> isMatch(dic, tag)).findAny();
	}

	public static Optional<TagKey<Biome>> matchTag(String name, Holder<Biome> biome) {
		return biome.getTagKeys().filter((tag) -> !isMatch("_ban", tag))
				.filter((tag) -> !isMatch("_but_", tag))
				.filter((tag) -> !isMatch("all", tag))
				.filter((tag) -> isMatch(name, tag)).findAny();
	}

	public static List<Fluid> getFluidList(TagKey<Fluid> tag) {
		List<Fluid> ret = Lists.newArrayList();
		ForgeRegistries.FLUIDS.iterator().forEachRemaining((f) -> {
			if (f.is(tag))
				ret.add(f);
		});
		return ret;
	}

	public static List<ItemStack> getItemList(TagKey<Item> tag) {
		List<ItemStack> ret = Lists.newArrayList();
		ForgeRegistries.ITEMS.iterator().forEachRemaining((i) -> {
			ItemStack r = new ItemStack(i);
			if (r.is(tag))
				ret.add(r);
		});
		return ret;
	}
}
