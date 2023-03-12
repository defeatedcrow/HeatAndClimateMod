package defeatedcrow.hac.core.tag;

import java.util.Optional;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class TagUtil {

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

	public static Optional<TagKey<Item>> matchTag(String name, Item item) {
		Optional<Holder<Item>> holder = ForgeRegistries.ITEMS.getHolder(item);
		return holder.map((h) -> {
			return holder.get().getTagKeys().filter((tag) -> isMatch(name, tag)).findAny();
		}).orElse(Optional.empty());
	}

	public static Optional<TagKey<Item>> matchTag(ResourceLocation dic, Item item) {
		Optional<Holder<Item>> holder = ForgeRegistries.ITEMS.getHolder(item);
		return holder.map((h) -> {
			return holder.get().getTagKeys().filter((tag) -> isMatch(dic, tag)).findAny();
		}).orElse(Optional.empty());
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
