package defeatedcrow.hac.core.tag;

import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FluidTagProviderDC extends FluidTagsProvider {

	public FluidTagProviderDC(DataGenerator gen, @Nullable ExistingFileHelper helper) {
		super(gen, "dcs_climate", helper);
	}

	@Override
	protected void addTags() {

		tag(TagDC.FluidTag.BRINE).add(CoreInit.BRINE.getStillFluid().get());
		tag(TagDC.FluidTag.HOT_SPRING).add(CoreInit.HOTSPRING.getStillFluid().get());
		tag(TagDC.FluidTag.SPARKLING).add(CoreInit.SPARKLING.getStillFluid().get());
		tag(TagDC.FluidTag.PLANT_OIL).add(CoreInit.PLANT_OIL.getStillFluid().get());
		tag(TagDC.FluidTag.FUEL).add(CoreInit.FUEL_OIL.getStillFluid().get());

		tag(TagDC.FluidTag.ALL_WATER).add(Fluids.WATER, CoreInit.BRINE.getStillFluid().get(),
			CoreInit.HOTSPRING.getStillFluid().get(), CoreInit.SPARKLING.getStillFluid().get());

		tag(TagDC.FluidTag.DRINK_WATER).add(Fluids.WATER, CoreInit.SPARKLING.getStillFluid().get());

		tag(TagDC.FluidTag.ALL_MILK).add(ForgeMod.MILK.get());
	}

}
