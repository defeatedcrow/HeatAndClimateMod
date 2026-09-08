package defeatedcrow.hac.core.tag;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FluidTagProviderDC extends FluidTagsProvider {

	public FluidTagProviderDC(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper helper) {
		super(output, lookupProvider,ClimateCore.MOD_ID, helper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(Provider provider) {

		tag(TagDC.FluidTag.BRINE).add(CoreInit.BRINE.getStillFluid().get());
		tag(TagDC.FluidTag.HOT_SPRING).add(CoreInit.HOTSPRING.getStillFluid().get());
		tag(TagDC.FluidTag.SPARKLING).add(CoreInit.SPARKLING.getStillFluid().get());
		tag(TagDC.FluidTag.PLANT_OIL).add(CoreInit.PLANT_OIL.getStillFluid().get());
		tag(TagDC.FluidTag.USED_PLANT_OIL).add(CoreInit.USED_PLANT_OIL.getStillFluid().get());
		tag(TagDC.FluidTag.FUEL).add(CoreInit.FUEL_OIL.getStillFluid().get());
		tag(TagDC.FluidTag.AIR).add(Fluids.EMPTY, CoreInit.AIR.getStillFluid().get());

		tag(TagDC.FluidTag.ALL_WATER).add(CoreInit.BRINE.getStillFluid().get(),
				CoreInit.HOTSPRING.getStillFluid().get(), CoreInit.SPARKLING.getStillFluid().get())
				.addTag(FluidTags.WATER);

		tag(TagDC.FluidTag.DRINK_WATER).add(CoreInit.SPARKLING.getStillFluid().get())
				.addTag(FluidTags.WATER);

		tag(TagDC.FluidTag.ALL_MILK).add(ForgeMod.MILK.get());

		tag(TagDC.FluidTag.FLAMMABLE).addTags(TagDC.FluidTag.PLANT_OIL, TagDC.FluidTag.PLANT_OIL, TagDC.FluidTag.FUEL);
	}

}
