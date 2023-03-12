package defeatedcrow.hac.core.advancement;

import java.util.function.Consumer;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class MagicAdvancement implements Consumer<Consumer<Advancement>> {

	@Override
	public void accept(Consumer<Advancement> t) {
		Advancement m1 = Advancement.Builder.advancement().display(MagicInit.EXTRACT_BLUE.get(), Component.translatable("advancements.dcs_climate.magic.root.title"),
			Component.translatable("advancements.dcs_climate.magic.root.desc"),
			new ResourceLocation("dcs_climate:textures/gui/advancement/magic.png"), FrameType.TASK, true, true, false)
			.addCriterion("has_drops", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.COLOR_EXTRACTS).build())).save(t, "dcs_climate:magic/magic_root");

		Advancement m2 = Advancement.Builder.advancement().parent(m1).display(MagicInit.ARROW_BLUE.get(), Component.translatable("advancements.dcs_climate.magic.arrow.title"),
			Component.translatable("advancements.dcs_climate.magic.arrow.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("has_arrow_white", InventoryChangeTrigger.TriggerInstance.hasItems(MagicInit.ARROW_WHITE.get()))
			.addCriterion("has_arrow_blue", InventoryChangeTrigger.TriggerInstance.hasItems(MagicInit.ARROW_BLUE.get()))
			.addCriterion("has_arrow_black", InventoryChangeTrigger.TriggerInstance.hasItems(MagicInit.ARROW_BLACK.get()))
			.addCriterion("has_arrow_red", InventoryChangeTrigger.TriggerInstance.hasItems(MagicInit.ARROW_RED.get()))
			.addCriterion("has_arrow_green", InventoryChangeTrigger.TriggerInstance.hasItems(MagicInit.ARROW_GREEN.get())).requirements(RequirementsStrategy.OR).save(t, "dcs_climate:magic/mana_arrow");

		Advancement m3 = Advancement.Builder.advancement().parent(m1).display(CoreInit.GEM_CELESTITE.get(), Component.translatable("advancements.dcs_climate.magic.gem.title"),
			Component.translatable("advancements.dcs_climate.magic.gem.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("has_gems", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.COLOR_GEMS).build())).save(t, "dcs_climate:magic/gem");
	}

}
