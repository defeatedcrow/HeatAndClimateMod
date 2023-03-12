package defeatedcrow.hac.core.advancement;

import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableList;

import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AdvancementProviderDC extends AdvancementProvider {

	private final List<Consumer<Consumer<Advancement>>> tabs = ImmutableList.of(new MainAdvancement(), new MagicAdvancement(), new MachineAdvancement());

	public AdvancementProviderDC(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, helper);
	}

	@Override
	protected void registerAdvancements(Consumer<Advancement> consumer, net.minecraftforge.common.data.ExistingFileHelper fileHelper) {
		for (Consumer<Consumer<Advancement>> con : this.tabs) {
			con.accept(consumer);
		}
	}

}
