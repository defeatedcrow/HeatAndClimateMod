package defeatedcrow.hac.magic.material.item.jems;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.state.BlockState;

public abstract class MagicJewelBase extends ItemDC implements IJewelCharm {

	protected final String name;
	private final MagicColor color;
	private final Rarity rarity;

	public MagicJewelBase(String n, MagicColor c, Rarity rare, TagKey<Item> pair) {
		super(new Item.Properties().tab(MagicInit.MAGIC).stacksTo(1).rarity(rare), pair);
		name = n;
		color = c;
		rarity = rare;
	}

	@Override
	public String getRegistryName() {
		return "magic/" + name;
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/magic/" + name));
	}

	/* IJewelCharm */

	@Override
	public Item getItem() {
		return this;
	}

	@Override
	abstract public CharmType getCharmType();

	@Override
	public MagicType getType() {
		return MagicType.INVENTORY_TOP;
	}

	@Override
	public MagicColor getColor() {
		return color;
	}

	@Override
	public Rarity getTier() {
		return rarity;
	}

	@Override
	public float reduceDamage(LivingEntity owner, DamageSource source, float damage, ItemStack charm) {
		return 1F;
	}

	@Override
	public boolean onDiffence(LivingEntity owner, DamageSource source, float damage, ItemStack charm) {
		return false;
	}

	@Override
	public float increaceDamage(LivingEntity owner, LivingEntity attackTarget, DamageSource source, float damage, ItemStack charm) {
		return 1F;
	}

	@Override
	public boolean onAttacking(LivingEntity owner, LivingEntity attackTarget, DamageSource source, float damage, ItemStack charm) {
		return false;
	}

	@Override
	public boolean onToolUsing(LivingEntity owner, BlockPos pos, BlockState state, ItemStack charm) {
		return false;
	}

	@Override
	public void constantEffect(LivingEntity owner, ItemStack charm) {

	}

	@Override
	public boolean onUsing(ServerPlayer owner, ItemStack charm) {
		return false;
	}

	@Override
	public boolean isActive(LivingEntity owner, ItemStack charm) {
		if (getCharmType() == CharmType.CONSTANT || getCharmType() == CharmType.SPECIAL)
			return true;
		if (owner != null && owner instanceof Player player) {
			int cost = getMagicCostEXP(charm);
			if (cost < 1 || player.totalExperience > cost) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onConsumeResource(LivingEntity owner, ItemStack charm) {
		if (owner != null && owner instanceof Player player) {
			if (isActive(player, charm)) {
				int cost = getMagicCostEXP(charm);
				player.giveExperiencePoints(-cost);
			}
		}
	}

	@Override
	public int getMagicCostEXP(ItemStack card) {
		if (ConfigCommonBuilder.INSTANCE.enMagicCost.get()) {
			int base = ConfigCommonBuilder.INSTANCE.vMagicCost.get();
			switch (rarity) {
			case EPIC:
				base *= 10;
				break;
			case RARE:
				base *= 5;
				break;
			case UNCOMMON:
				base *= 2;
				break;
			case COMMON:
			default:
				break;
			}
			return base;
		}
		return 0;
	}
}
