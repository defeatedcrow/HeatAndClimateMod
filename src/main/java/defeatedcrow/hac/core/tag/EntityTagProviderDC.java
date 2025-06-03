package defeatedcrow.hac.core.tag;

import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EntityTagProviderDC extends EntityTypeTagsProvider {

	public EntityTagProviderDC(DataGenerator gen, @Nullable ExistingFileHelper helper) {
		super(gen, "dcs_climate", helper);
	}

	@Override
	protected void addTags() {
		tag(EntityTypeTags.ARROWS).add(MagicInit.ARROW_WHITE_ENTITY.get(), MagicInit.ARROW_BLUE_ENTITY.get(),
				MagicInit.ARROW_BLACK_ENTITY.get(), MagicInit.ARROW_RED_ENTITY.get(), MagicInit.ARROW_GREEN_ENTITY.get());

		tag(TagDC.EntityTag.MAGIC_PICTURE).add(MagicInit.MAGIC_PICTURE_BR.get(), MagicInit.MAGIC_PICTURE_BW.get(),
				MagicInit.MAGIC_PICTURE_GB.get(), MagicInit.MAGIC_PICTURE_GW.get(), MagicInit.MAGIC_PICTURE_RG.get(),
				MagicInit.MAGIC_PICTURE_RU.get(), MagicInit.MAGIC_PICTURE_UB.get(), MagicInit.MAGIC_PICTURE_UG.get(),
				MagicInit.MAGIC_PICTURE_WU.get(), MagicInit.MAGIC_PICTURE_WR.get());

		tag(TagDC.EntityTag.SPAWN_SUPPRESSOR_BLACKLIST).addTags(Tags.EntityTypes.BOSSES);
		tag(TagDC.EntityTag.SPAWN_SUPPRESSOR_BLACKLIST).add(EntityType.ELDER_GUARDIAN, EntityType.WARDEN);
	}

}
