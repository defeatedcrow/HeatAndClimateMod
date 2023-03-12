package defeatedcrow.hac.core.tag;

import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EntityTagProviderDC extends EntityTypeTagsProvider {

	public EntityTagProviderDC(DataGenerator gen, @Nullable ExistingFileHelper helper) {
		super(gen, "dcs_climate", helper);
	}

	@Override
	protected void addTags() {
		tag(EntityTypeTags.ARROWS).add(MagicInit.ARROW_WHITE_ENTITY.get(), MagicInit.ARROW_BLUE_ENTITY.get(),
			MagicInit.ARROW_BLACK_ENTITY.get(), MagicInit.ARROW_RED_ENTITY.get(), MagicInit.ARROW_GREEN_ENTITY.get());
	}

}
