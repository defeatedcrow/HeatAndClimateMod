package defeatedcrow.hac.magic.recipe;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BasicColors {

	public static BasicColors INSTANCE = new BasicColors() {};

	public final List<Color> basic;

	private BasicColors() {
		ImmutableList.Builder<Color> list1 = ImmutableList.builder();

		list1.add(new Color("white", MagicColor.WHITE, () -> MagicInit.DROP_WHITE.get(), () -> TagDC.ItemTag.DROP_WHITE));
		list1.add(new Color("blue", MagicColor.BLUE, () -> MagicInit.DROP_BLUE.get(), () -> TagDC.ItemTag.DROP_BLUE));
		list1.add(new Color("black", MagicColor.BLACK, () -> MagicInit.DROP_BLACK.get(), () -> TagDC.ItemTag.DROP_BLACK));
		list1.add(new Color("red", MagicColor.RED, () -> MagicInit.DROP_RED.get(), () -> TagDC.ItemTag.DROP_RED));
		list1.add(new Color("green", MagicColor.GREEN, () -> MagicInit.DROP_GREEN.get(), () -> TagDC.ItemTag.DROP_GREEN));

		basic = list1.build();
	}

	public record Color(
			String name,
			MagicColor color,
			Supplier<Item> drop,
			Supplier<TagKey<Item>> tag) {}

}
