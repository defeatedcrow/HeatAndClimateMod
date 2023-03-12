package defeatedcrow.hac.core.advancement;

import java.util.function.Consumer;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class MachineAdvancement implements Consumer<Consumer<Advancement>> {

	@Override
	public void accept(Consumer<Advancement> t) {
		Advancement m1 = Advancement.Builder.advancement().display(CoreInit.ORE_BLUE.get(), Component.translatable("advancements.dcs_climate.metal.root.title"),
			Component.translatable("advancements.dcs_climate.metal.root.desc"),
			new ResourceLocation("dcs_climate:textures/gui/advancement/metal.png"), FrameType.TASK, true, true, false)
			.addCriterion("has_drops", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.ORES_COLOR).build())).save(t, "dcs_climate:metal/metal_root");

		Advancement m2 = Advancement.Builder.advancement().parent(m1).display(CoreInit.MORTAR.get(), Component.translatable("advancements.dcs_climate.metal.mortar.title"),
			Component.translatable("advancements.dcs_climate.metal.mortar.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("has_dust_copper", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.DUST_COPPER).build()))
			.addCriterion("has_dust_zinc", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.DUST_ZINC).build()))
			.addCriterion("has_dust_magnetite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.DUST_MAGNETITE).build()))
			.addCriterion("has_dust_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.DUST_TIN).build()))
			.addCriterion("has_dust_iron", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.DUST_IRON).build()))
			.requirements(RequirementsStrategy.OR).save(t, "dcs_climate:metal/mortar");

		Advancement m3 = Advancement.Builder.advancement().parent(m2).display(CoreInit.METALBLOCK_BRASS.get(), Component.translatable("advancements.dcs_climate.metal.brass.title"),
			Component.translatable("advancements.dcs_climate.metal.brass.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("has_brass", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.METALBLOCK_BRASS).build())).save(t, "dcs_climate:metal/brass");
	}

}
