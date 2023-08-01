package defeatedcrow.hac.food.recipe;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class ContainerRecipes {
	public static ContainerRecipes INSTANCE = new ContainerRecipes() {};

	public final List<Cont> pairs;

	private ContainerRecipes() {
		ImmutableList.Builder<Cont> list = ImmutableList.builder();
		pairs = list.build();
	}

	public record Cont(
			String name,
			Supplier<ItemLike> output,
			Supplier<Item> input,
			Supplier<TagKey<Item>> inputTag) {}

}
