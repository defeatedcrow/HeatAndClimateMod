package defeatedcrow.hac.core.advancement;

import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableList;

import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;

public class AdvancementProviderDC implements AdvancementSubProvider {

	private final List<Consumer<Consumer<Advancement>>> tabs = ImmutableList.of(new MainAdvancement(), new MagicAdvancement(), new MachineAdvancement());

	@Override
	public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer) {
		for (Consumer<Consumer<Advancement>> con : this.tabs) {
			con.accept(consumer);
		}
	}

}
