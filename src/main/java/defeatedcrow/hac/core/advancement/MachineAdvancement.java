package defeatedcrow.hac.core.advancement;

import java.util.function.Consumer;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.EnterBlockTrigger;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider.AdvancementGenerator;

public class MachineAdvancement implements AdvancementGenerator {

	@Override
	public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper helper) {

		Advancement v1 = Advancement.Builder.advancement()
		    .display(CoreInit.SCREWDRIVER.get(), Component.translatable("advancements.dcs_climate.metal.root.title"), Component.translatable("advancements.dcs_climate.metal.root.desc"),
		        ResourceLocation.fromNamespaceAndPath("dcs_climate", "textures/gui/advancement/metal.png"), FrameType.TASK, false, false, false)
		    .addCriterion("in_overworld", PlayerTrigger.TriggerInstance.located(LocationPredicate.inDimension(Level.OVERWORLD)))
		    .save(saver, "dcs_climate:metal/root");

		Advancement m0 = Advancement.Builder.advancement()
		    .parent(v1)
		    .display(Items.FLINT, Component.translatable("advancements.dcs_climate.metal.flint.title"), Component.translatable("advancements.dcs_climate.metal.flint.desc"), null, FrameType.TASK, true, true, false)
		    .addCriterion("has_flint", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FLINT))
		    .save(saver, "dcs_climate:metal/metal_flint");

		Advancement m0_2 = Advancement.Builder.advancement()
		    .parent(m0)
		    .display(CoreInit.MORTAR.get(), Component.translatable("advancements.dcs_climate.metal.mortar.title"), Component.translatable("advancements.dcs_climate.metal.mortar.desc"), null, FrameType.TASK, true, true, false)
		    .addCriterion("has_mortar", InventoryChangeTrigger.TriggerInstance.hasItems(CoreInit.MORTAR.get()))
		    .save(saver, "dcs_climate:metal/metal_mortar");

		Advancement m1 = Advancement.Builder.advancement()
		    .parent(m0_2)
		    .display(CoreInit.OREITEM_BLUE2.get(), Component.translatable("advancements.dcs_climate.metal.ore.title"), Component.translatable("advancements.dcs_climate.metal.ore.desc"), null, FrameType.TASK, true, true, false)
		    .addCriterion("has_drops", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
		        .of(TagDC.ItemTag.RAW_MATERIALS_COLOR)
		        .build()))
		    .save(saver, "dcs_climate:metal/metal_ore");

		Advancement m2 = Advancement.Builder.advancement()
		    .parent(m1)
		    .display(CoreInit.OREDUST_BLUE2.get(), Component.translatable("advancements.dcs_climate.metal.dust.title"), Component.translatable("advancements.dcs_climate.metal.dust.desc"), null, FrameType.TASK, true, true, false)
		    .addCriterion("has_dust_copper", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
		        .of(TagDC.ItemTag.DUST_COPPER)
		        .build()))
		    .addCriterion("has_dust_zinc", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
		        .of(TagDC.ItemTag.DUST_ZINC)
		        .build()))
		    .addCriterion("has_dust_magnetite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
		        .of(TagDC.ItemTag.DUST_MAGNETITE)
		        .build()))
		    .addCriterion("has_dust_tin", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
		        .of(TagDC.ItemTag.DUST_TIN)
		        .build()))
		    .addCriterion("has_dust_iron", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
		        .of(TagDC.ItemTag.DUST_IRON)
		        .build()))
		    .requirements(RequirementsStrategy.OR)
		    .save(saver, "dcs_climate:metal/dust");

		Advancement m3 = Advancement.Builder.advancement()
		    .parent(m2)
		    .display(CoreInit.METALBLOCK_BRASS.get(), Component.translatable("advancements.dcs_climate.metal.brass.title"), Component.translatable("advancements.dcs_climate.metal.brass.desc"), null, FrameType.TASK, true, true, false)
		    .addCriterion("has_brass", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
		        .of(TagDC.ItemTag.METALBLOCK_BRASS)
		        .build()))
		    .save(saver, "dcs_climate:metal/brass");

		Advancement m4 = Advancement.Builder.advancement()
		    .parent(m3)
		    .display(CoreInit.PICKAXE_BRASS.get(), Component.translatable("advancements.dcs_climate.metal.brass_tool.title"), Component.translatable("advancements.dcs_climate.metal.brass_tool.desc"), null, FrameType.TASK, true, true, false)
		    .addCriterion("has_brass_tool", InventoryChangeTrigger.TriggerInstance.hasItems(CoreInit.PICKAXE_BRASS.get()))
		    .addCriterion("has_steel_tool", InventoryChangeTrigger.TriggerInstance.hasItems(CoreInit.PICKAXE_STEEL.get()))
		    .requirements(RequirementsStrategy.OR)
		    .save(saver, "dcs_climate:metal/brass_tool");

		Advancement m5 = Advancement.Builder.advancement()
		    .parent(m3)
		    .display(MachineInit.MOTOR_TIER1.get(), Component.translatable("advancements.dcs_climate.metal.motor_t1.title"), Component.translatable("advancements.dcs_climate.metal.motor_t1.desc"), null, FrameType.TASK, true, true, false)
		    .addCriterion("has_motor_t1", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
		        .of(TagDC.ItemTag.MOTOR_T1)
		        .build()))
		    .save(saver, "dcs_climate:metal/motor_t1");

		Advancement m6 = Advancement.Builder.advancement()
		    .parent(m5)
		    .display(MachineInit.STONE_MILL.get(), Component.translatable("advancements.dcs_climate.metal.stone_mill.title"), Component.translatable("advancements.dcs_climate.metal.stone_mill.desc"), null, FrameType.TASK, true, true, false)
		    .addCriterion("place_stone_mill", EnterBlockTrigger.TriggerInstance.entersBlock((MachineInit.STONE_MILL.get())))
		    .save(saver, "dcs_climate:metal/stone_mill");

		Advancement m9 = Advancement.Builder.advancement()
		    .parent(m6)
		    .display(Items.LIGHTNING_ROD, Component.translatable("advancements.dcs_climate.metal.lightning.title"), Component.translatable("advancements.dcs_climate.metal.lightning.desc"), null, FrameType.CHALLENGE, true, true, false)
		    .addCriterion("impossible", new ImpossibleTrigger.TriggerInstance())
		    .save(saver, "dcs_climate:metal/lightning");

		Advancement m7 = Advancement.Builder.advancement()
		    .parent(m6)
		    .display(CoreInit.SCREWDRIVER.get(), Component.translatable("advancements.dcs_climate.metal.cable.title"), Component.translatable("advancements.dcs_climate.metal.cable.desc"), null, FrameType.TASK, true, true, false)
		    .addCriterion("screwdriver_to_cable", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location()
		        .setBlock(BlockPredicate.Builder.block()
		            .of(MachineInit.CABLE_COPPER.get())
		            .build()),
		        ItemPredicate.Builder.item()
		            .of(CoreInit.SCREWDRIVER.get())))
		    .save(saver, "dcs_climate:metal/cable");

		Advancement m8 = Advancement.Builder.advancement()
		    .parent(m7)
		    .display(MachineInit.CABLE_COPPER.get(), Component.translatable("advancements.dcs_climate.metal.cable_danger.title"), Component.translatable("advancements.dcs_climate.metal.cable_danger.desc"), null, FrameType.CHALLENGE, true,
		        true, false)
		    .addCriterion("impossible", new ImpossibleTrigger.TriggerInstance())
		    .save(saver, "dcs_climate:metal/cable_danger");
	}

}
