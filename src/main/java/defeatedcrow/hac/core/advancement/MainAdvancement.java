package defeatedcrow.hac.core.advancement;

import java.util.function.Consumer;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemInteractWithBlockTrigger.TriggerInstance;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class MainAdvancement implements Consumer<Consumer<Advancement>> {

	@Override
	public void accept(Consumer<Advancement> t) {
		Advancement v1 = Advancement.Builder.advancement().display(CoreInit.ICON_HAC.get(), Component.translatable("advancements.dcs_climate.main.root.title"),
			Component.translatable("advancements.dcs_climate.main.root.desc"),
			new ResourceLocation("dcs_climate:textures/gui/advancement/main.png"), FrameType.TASK, false, false, false)
			.addCriterion("in_overworld", PlayerTrigger.TriggerInstance.located(LocationPredicate.inDimension(Level.OVERWORLD))).save(t, "dcs_climate:main/root");

		Advancement v2 = Advancement.Builder.advancement().parent(v1).display(Blocks.OAK_LOG, Component.translatable("advancements.dcs_climate.main.wood.title"),
			Component.translatable("advancements.dcs_climate.main.wood.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("has_logs", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ItemTags.LOGS_THAT_BURN).build())).save(t, "dcs_climate:main/wood");

		Advancement a1 = Advancement.Builder.advancement().parent(v2).display(Blocks.CRAFTING_TABLE, Component.translatable("advancements.dcs_climate.main.table.title"),
			Component.translatable("advancements.dcs_climate.main.table.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("has_crafting_table", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.CRAFTING_TABLE)).save(t, "dcs_climate:main/table");

		Advancement a2 = Advancement.Builder.advancement().parent(a1).display(CoreInit.ICON_BURN.get(), Component.translatable("advancements.dcs_climate.main.damage.title"),
			Component.translatable("advancements.dcs_climate.main.damage.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("impossible", new ImpossibleTrigger.TriggerInstance()).save(t, "dcs_climate:main/damage");

		Advancement a3 = Advancement.Builder.advancement().parent(a2).display(Items.LEATHER_HELMET, Component.translatable("advancements.dcs_climate.main.wear.title"),
			Component.translatable("advancements.dcs_climate.main.wear.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("impossible", new ImpossibleTrigger.TriggerInstance()).save(t, "dcs_climate:main/wear");

		Advancement a4 = Advancement.Builder.advancement().parent(a1).display(FoodInit.CROP_AL_WILD.get(), Component.translatable("advancements.dcs_climate.main.wild_crops.title"),
			Component.translatable("advancements.dcs_climate.main.wild_crops.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("has_wild_crop", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.CROP_WILD).build())).save(t, "dcs_climate:main/wild_crops");

		Advancement a5 = Advancement.Builder.advancement().parent(a4).display(Items.BONE_MEAL, Component.translatable("advancements.dcs_climate.main.fertilizer.title"),
			Component.translatable("advancements.dcs_climate.main.fertilizer.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("use_fertilizer", TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(Blocks.FARMLAND).build()),
				ItemPredicate.Builder.item().of(TagDC.ItemTag.FERTILIZER))).save(t, "dcs_climate:main/fertilizer");

		Advancement a6 = Advancement.Builder.advancement().parent(a5).display(FoodInit.CROP_AL_GARLIC.get(), Component.translatable("advancements.dcs_climate.main.rare_crops.title"),
			Component.translatable("advancements.dcs_climate.main.rare_crops.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("has_rare_crop", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.CROP_RARE).build())).save(t, "dcs_climate:main/rare_crops");

		Advancement a7 = Advancement.Builder.advancement().parent(a6).display(FoodInit.CROP_CH_WILD.get(), Component.translatable("advancements.dcs_climate.main.tree_crops.title"),
			Component.translatable("advancements.dcs_climate.main.tree_crops.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("has_tree_crop", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(TagDC.ItemTag.TREE_WILD).build())).save(t, "dcs_climate:main/tree_crops");

		Advancement a9 = Advancement.Builder.advancement().parent(a7).display(FoodInit.CROP_BR_RADISH.get(), Component.translatable("advancements.dcs_climate.main.epic_crops.title"),
			Component.translatable("advancements.dcs_climate.main.epic_crops.desc"), null, FrameType.CHALLENGE, true, true, false)
			.addCriterion("has_epic_crop1", InventoryChangeTrigger.TriggerInstance.hasItems(FoodInit.CROP_BR_RADISH.get()))
			.addCriterion("has_epic_crop2", InventoryChangeTrigger.TriggerInstance.hasItems(FoodInit.BLOCK_HB_LAVENDER.get()))
			.addCriterion("has_epic_crop3", InventoryChangeTrigger.TriggerInstance.hasItems(FoodInit.CROP_MO_FLOWER.get()))
			.addCriterion("has_epic_crop4", InventoryChangeTrigger.TriggerInstance.hasItems(FoodInit.CROP_PE_ADZUKI.get()))
			.addCriterion("has_epic_crop5", InventoryChangeTrigger.TriggerInstance.hasItems(FoodInit.CROP_SL_LANTERN.get()))
			.requirements(RequirementsStrategy.AND).save(t, "dcs_climate:main/epic_crops");

		Advancement b1 = Advancement.Builder.advancement().parent(a6).display(FoodInit.BREAD_ROUND_RAW_ITEM.get(), Component.translatable("advancements.dcs_climate.main.bread_raw.title"),
			Component.translatable("advancements.dcs_climate.main.bread_raw.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("has_dough", InventoryChangeTrigger.TriggerInstance.hasItems(FoodInit.BREAD_ROUND_RAW_ITEM.get())).save(t, "dcs_climate:main/bread_raw");

		Advancement b2 = Advancement.Builder.advancement().parent(b1).display(FoodInit.BREAD_ROUND_BAKED_ITEM.get(), Component.translatable("advancements.dcs_climate.main.bread_bake.title"),
			Component.translatable("advancements.dcs_climate.main.bread_bake.desc"), null, FrameType.TASK, true, true, false)
			.addCriterion("has_bread", InventoryChangeTrigger.TriggerInstance.hasItems(FoodInit.BREAD_ROUND_BAKED_ITEM.get())).save(t, "dcs_climate:main/bread_bake");

		Advancement b3 = Advancement.Builder.advancement().parent(b2).display(FoodInit.STICK_CHICKEN_COOKED.get(), Component.translatable("advancements.dcs_climate.main.nether_chicken.title"),
			Component.translatable("advancements.dcs_climate.main.nether_chicken.desc"), null, FrameType.CHALLENGE, true, true, false)
			.addCriterion("impossible", new ImpossibleTrigger.TriggerInstance()).save(t, "dcs_climate:main/nether_chicken");

		CompoundTag tag = new CompoundTag();
		tag.putInt(TagKeyDC.TASTE, 2);
		Advancement b4 = Advancement.Builder.advancement().parent(b2).display(FoodInit.PLATE_GARLIC_COOKED.get(), Component.translatable("advancements.dcs_climate.main.delicious.title"),
			Component.translatable("advancements.dcs_climate.main.delicious.desc"), null, FrameType.CHALLENGE, true, true, false)
			.addCriterion("has_good_taste_food", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().hasNbt(tag).build())).save(t, "dcs_climate:main/delicious");
	}

}
