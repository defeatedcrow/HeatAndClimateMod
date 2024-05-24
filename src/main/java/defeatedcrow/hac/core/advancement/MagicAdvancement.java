package defeatedcrow.hac.core.advancement;

import java.util.function.Consumer;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.EffectsChangedTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class MagicAdvancement implements Consumer<Consumer<Advancement>> {

	@Override
	public void accept(Consumer<Advancement> t) {
		Advancement m1 = Advancement.Builder.advancement().display(MagicInit.EXTRACT_BLUE.get(), Component.translatable("advancements.dcs_climate.magic.root.title"),
				Component.translatable("advancements.dcs_climate.magic.root.desc"),
				new ResourceLocation("dcs_climate:textures/gui/advancement/magic.png"), FrameType.TASK, true, true, false)
				.addCriterion("has_drops", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.COLOR_EXTRACTS).build())).save(t,
						"dcs_climate:magic/magic_root");

		Advancement m2 = Advancement.Builder.advancement().parent(m1).display(MagicInit.ARROW_BLACK.get(), Component.translatable("advancements.dcs_climate.magic.arrow.title"),
				Component.translatable("advancements.dcs_climate.magic.arrow.desc"), null, FrameType.TASK, true, true, false)
				.addCriterion("has_arrow_white", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.MAGIC_TIER1).build()))
				.save(t, "dcs_climate:magic/mana_arrow");

		Advancement m3 = Advancement.Builder.advancement().parent(m2).display(MagicInit.CARD_WU.get(), Component.translatable("advancements.dcs_climate.magic.tier2.title"),
				Component.translatable("advancements.dcs_climate.magic.tier2.desc"), null, FrameType.TASK, true, true, false)
				.addCriterion("has_magic_tier2", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.MAGIC_TIER2).build()))
				.save(t, "dcs_climate:magic/magic_tier2");

		Advancement m4 = Advancement.Builder.advancement().parent(m3).display(Items.FEATHER, Component.translatable("advancements.dcs_climate.magic.bird.title"),
				Component.translatable("advancements.dcs_climate.magic.bird.desc"), null, FrameType.CHALLENGE, true, true, false)
				.addCriterion("has_magic_tier2", EffectsChangedTrigger.TriggerInstance.hasEffects(MobEffectsPredicate.effects().and(CoreInit.BIRD.get())))
				.save(t, "dcs_climate:magic/bird");

		Advancement g1 = Advancement.Builder.advancement().parent(m1).display(CoreInit.GEM_CELESTITE.get(), Component.translatable("advancements.dcs_climate.magic.gem.title"),
				Component.translatable("advancements.dcs_climate.magic.gem.desc"), null, FrameType.TASK, true, true, false)
				.addCriterion("has_gems", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.COLOR_GEMS).build())).save(t, "dcs_climate:magic/gem");

		Advancement g2 = Advancement.Builder.advancement().parent(g1).display(MagicInit.RING_GOLD_RED.get(), Component.translatable("advancements.dcs_climate.magic.ring.title"),
				Component.translatable("advancements.dcs_climate.magic.ring.desc"), null, FrameType.TASK, true, true, false)
				.addCriterion("has_ring", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.MAGIC_RING).build())).save(t, "dcs_climate:magic/ring");

		Advancement g3 = Advancement.Builder.advancement().parent(g1).display(MagicInit.ELEMENT_GREEN.get(), Component.translatable("advancements.dcs_climate.magic.element.title"),
				Component.translatable("advancements.dcs_climate.magic.element.desc"), null, FrameType.TASK, true, true, false)
				.addCriterion("has_element", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.COLOR_ELEMENTS).build()))
				.save(t, "dcs_climate:magic/element");
	}

}
