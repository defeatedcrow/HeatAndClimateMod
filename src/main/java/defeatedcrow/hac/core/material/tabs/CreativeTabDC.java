package defeatedcrow.hac.core.material.tabs;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

// 1.20.1ではCreativeModeTabがBuilder+DeferredRegister方式になったため、
// アイテム側でタブへの表示登録を受け付けるための保持クラス
public class CreativeTabDC {

	private static final Map<Supplier<CreativeModeTab>, List<Item>> TAB_ITEMS = Maps.newHashMap();

	public static void add(Supplier<CreativeModeTab> tab, Item item) {
		TAB_ITEMS.computeIfAbsent(tab, (t) -> Lists.newArrayList()).add(item);
	}

	public static <T extends Item> T of(T item, Supplier<CreativeModeTab> tab) {
		add(tab, item);
		return item;
	}

	public static void fillTab(BuildCreativeModeTabContentsEvent event) {
		for (Map.Entry<Supplier<CreativeModeTab>, List<Item>> entry : TAB_ITEMS.entrySet()) {
			if (entry.getKey().get() == event.getTab()) {
				for (Item item : entry.getValue()) {
					event.accept(new ItemStack(item));
				}
			}
		}
		if (event.getTab() == CoreInit.MACHINE.get()) {
			ItemStack battery = new ItemStack(MachineInit.BATTERY_SMALL.get());
			CompoundTag tag = new CompoundTag();
			tag.putInt(TagKeyDC.ENERGY, 32000);
			battery.setTag(tag);
			event.accept(battery);
			ItemStack battery2 = new ItemStack(MachineInit.BATTERY_MIDDLE.get());
			CompoundTag tag2 = new CompoundTag();
			tag2.putInt(TagKeyDC.ENERGY, 128000);
			battery2.setTag(tag2);
			event.accept(battery2);
		}
	}

}
