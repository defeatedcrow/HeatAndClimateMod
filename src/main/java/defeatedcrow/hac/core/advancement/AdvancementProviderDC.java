package defeatedcrow.hac.core.advancement;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

public class AdvancementProviderDC extends ForgeAdvancementProvider {

	private final static List<AdvancementGenerator> tabs = ImmutableList.of(new MainAdvancement(), new MagicAdvancement(), new MachineAdvancement());

	public AdvancementProviderDC(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
		super(output, provider, helper, tabs);
	}

}
