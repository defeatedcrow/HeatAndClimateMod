package defeatedcrow.hac.core.util;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public enum TierDC implements Tier {
	BRASS(2, 401, 6.0F, 1.0F, 8, () -> {
		return Ingredient.of(TagDC.ItemTag.INGOT_BRASS);
	}),
	STEEL(3, 750, 8.0F, 1.0F, 6, () -> {
		return Ingredient.of(TagDC.ItemTag.INGOT_STEEL);
	}),
	AGATE(3, 32, 8.0F, 3.0F, 15, () -> {
		return Ingredient.of(TagDC.ItemTag.GEM_AGATES);
	});

	private final int level;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final LazyLoadedValue<Ingredient> repairIngredient;

	private TierDC(int lev, int use, float spd, float dam, int enc, Supplier<Ingredient> rep) {
		this.level = lev;
		this.uses = use;
		this.speed = spd;
		this.damage = dam;
		this.enchantmentValue = enc;
		this.repairIngredient = new LazyLoadedValue<>(rep);
	}

	@Override
	public int getUses() {
		return this.uses;
	}

	@Override
	public float getSpeed() {
		return this.speed;
	}

	@Override
	public float getAttackDamageBonus() {
		return this.damage;
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

	public String getTierName() {
		return this.toString().toLowerCase();
	}

	@Override
	@Nullable
	public TagKey<Block> getTag() {
		switch (this) {
		case AGATE:
			return BlockTags.NEEDS_IRON_TOOL;
		case BRASS:
			return BlockTags.NEEDS_DIAMOND_TOOL;
		case STEEL:
			return BlockTags.NEEDS_DIAMOND_TOOL;
		default:
			return BlockTags.NEEDS_IRON_TOOL;

		}
	}
}
