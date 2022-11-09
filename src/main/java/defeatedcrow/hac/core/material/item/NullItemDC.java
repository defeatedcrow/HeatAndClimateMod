package defeatedcrow.hac.core.material.item;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.core.json.JsonModelDC;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class NullItemDC extends ItemDC {

	final String name;
	private String domain = "main";

	public NullItemDC(String s) {
		super(new Item.Properties(), null);
		name = s;
	}

	public NullItemDC setDomain(String s) {
		domain = s;
		return this;
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/" + name)));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.empty();
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/" + name));
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		list.add(Component.translatable("dcs.tip.null_item1"));
		list.add(Component.translatable("dcs.tip.null_item2"));
	}

}
