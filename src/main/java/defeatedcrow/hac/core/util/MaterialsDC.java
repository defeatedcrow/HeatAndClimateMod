package defeatedcrow.hac.core.util;

import java.util.function.Supplier;
import java.util.stream.Stream;

import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public enum MaterialsDC implements ArmorMaterial {
	LINEN("linen", 4, new int[] { 1, 1, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, -0.1F, () -> {
		return Ingredient.of(TagDC.ItemTag.CLOTH_PLANT);
	}),
	CLOTH("cloth", 4, new int[] { 1, 1, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, -0.1F, () -> {
		return Ingredient.of(TagDC.ItemTag.CLOTH_COTTON);
	}),
	WOOL("wool", 4, new int[] { 1, 1, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, -0.1F, () -> {
		return Ingredient.of(TagDC.ItemTag.CLOTH_WOOL);
	}),
	SILK("silk", 4, new int[] { 1, 1, 2, 1 }, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, -0.1F, () -> {
		return Ingredient.of(TagDC.ItemTag.CLOTH_COTTON);
	}),
	BRONZE("bronze", 18, new int[] { 2, 4, 5, 2 }, 8, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		return Ingredient.fromValues(Stream.of(new Ingredient.TagValue(TagDC.ItemTag.INGOT_BRASS), new Ingredient.TagValue(TagDC.ItemTag.INGOT_BRONZE)));
	}),
	STEEL("steel", 30, new int[] { 3, 6, 7, 2 }, 8, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> {
		return Ingredient.of(TagDC.ItemTag.INGOT_STEEL);
	}),
	AGATE("agate", 4, new int[] { 2, 4, 5, 2 }, 18, SoundEvents.ARMOR_EQUIP_DIAMOND, 0.0F, 0.0F, () -> {
		return Ingredient.of(TagDC.ItemTag.GEM_AGATES);
	});

	private static final int[] HEALTH_PER_SLOT = new int[] { 13, 15, 16, 11 };
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final Supplier<Ingredient> repairIngredient;

	private MaterialsDC(String name, int dur, int[] amount, int enchant, SoundEvent sound, float tough, float knockback, Supplier<Ingredient> repair) {
		this.name = name;
		this.durabilityMultiplier = dur;
		this.slotProtections = amount;
		this.enchantmentValue = enchant;
		this.sound = sound;
		this.toughness = tough;
		this.knockbackResistance = knockback;
		this.repairIngredient = repair;
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlot p_40484_) {
		return HEALTH_PER_SLOT[p_40484_.getIndex()] * this.durabilityMultiplier;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlot p_40487_) {
		return this.slotProtections[p_40487_.getIndex()];
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.sound;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}
}
