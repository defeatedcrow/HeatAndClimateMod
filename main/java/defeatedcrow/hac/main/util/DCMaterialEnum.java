package defeatedcrow.hac.main.util;

import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public enum DCMaterialEnum {
	/* (name, atk, def, tire, dur, eff, enchant, heat, cold, repair, sound, toughness) */

	// 耐火耐寒性能の高さ以外特になし
	LINEN(0, "linen", 1.0F, new int[] {
			1,
			2,
			2,
			1
	}, 0, 72, 0.0F, 20, 2.0F, 1.0F, new ItemStack(MainInit.clothes, 1, 0), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F),
	// 少し耐寒より
	CLOTH(1, "cloth", 1.0F, new int[] {
			1,
			2,
			2,
			1
	}, 0, 108, 0.0F, 25, 2.0F, 3.0F, new ItemStack(MainInit.clothes, 1, 1), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F),
	// 純金属は基本的に利用しない
	COPPER(2, "copper", 2.0F, new int[] {
			1,
			2,
			2,
			1
	}, 1, 128, 1.0F, 5, 0.5F, 0.5F, new ItemStack(MainInit.oreIngot, 1, 0), SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
	ZINC(3, "zinc", 2.0F, new int[] {
			1,
			2,
			2,
			1
	}, 1, 128, 1.0F, 5, 0.5F, 0.5F, new ItemStack(MainInit.oreIngot, 1, 1), SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
	NICKEL(4, "nickel", 2.0F, new int[] {
			1,
			2,
			2,
			1
	}, 1, 256, 1.0F, 5, 1.0F, 1.0F, new ItemStack(MainInit.oreIngot, 1, 2), SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
	// 主にアクセサリー用。実用に耐えうる金という感じ
	SILVER(5, "silver", 2.0F, new int[] {
			4,
			8,
			7,
			4
	}, 1, 256, 12.0F, 22, 1.0F, 1.0F, new ItemStack(MainInit.oreIngot, 1, 3), SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.5F),
	// 普段使い用の金属。鉄の上位互換。
	BRASS(6, "brass", 2.0F, new int[] {
			3,
			6,
			5,
			2
	}, 2, 384, 6.0F, 10, 0.25F, 0.25F, new ItemStack(MainInit.oreIngot, 1, 4), SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F),
	// 耐久値が高いが鉄より弱い
	NICKELSILVER(7, "nickelsilver", 2.0F, new int[] {
			3,
			5,
			4,
			2
	}, 2, 1200, 4.0F, 10, 0.5F, 0.5F, new ItemStack(MainInit.oreIngot, 1, 6), SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
	// 熱に弱いが他が強い
	STEEL(8, "steel", 4.0F, new int[] {
			6,
			10,
			8,
			5
	}, 3, 650, 8.0F, 5, 0.0F, 0.0F, new ItemStack(MainInit.oreIngot, 1, 5), SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F),
	// ピーキー性能
	CHALCEDONY(9, "chalcedony", 8.0F, new int[] {
			6,
			12,
			7,
			6
	}, 4, 72, 12.0F, 25, 1.0F, 1.0F, new ItemStack(MainInit.gems, 1, 0), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.0F),
	// 黒曜石が掘れないだけのダイヤ
	SAPPHIRE(10, "sapphire", 3.0F, new int[] {
			3,
			8,
			6,
			3
	}, 2, 1280, 7.0F, 18, 1.0F, 1.0F, new ItemStack(MainInit.gems, 1, 4), SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F),
	// 防御と耐久特化
	TITANIUM(11, "titanium", 8.0F, new int[] {
			7,
			12,
			10,
			6
	}, 4, 1800, 8.0F, 5, 0F, 0F, new ItemStack(MainInit.oreIngot, 1, 11), SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F),
	// 耐暑性特化で耐久高め
	SYNTHETIC(12, "synthetic", 3.0F, new int[] {
			3,
			5,
			4,
			2
	}, 1, 240, 3.0F, 15, 3.0F, 1.0F, new ItemStack(MachineInit.synthetic, 1, 1), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F),
	// ほぼ真鍮と同程度
	GARNET(13, "garnet", 2.0F, new int[] {
			3,
			6,
			5,
			2
	}, 2, 480, 6.0F, 18, 0.5F, 0.5F, new ItemStack(MainInit.gems, 1, 14), SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F),
	// 防寒着
	WOOL(14, "wool", 3.0F, new int[] {
			1,
			2,
			2,
			1
	}, 1, 72, 0.0F, 10, 1.0F, 3.0F, new ItemStack(Blocks.WOOL, 1, 0), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F),
	// 耐久の低い攻性の金属素材
	TOOLMETAL(15, "toolmetal", 15.0F, new int[] {
			6,
			10,
			8,
			5
	}, 5, 720, 12.0F, 10, 0.0F, 0.0F, new ItemStack(MainInit.oreIngot, 1, 17), SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);

	public final String name;
	public final int id;
	public final float attackDam;
	public final int[] reduceDam;
	public final int harvestTier;
	public final int duration;
	public final int armorDur;
	public final float efficiency;
	public final int enchant;
	public final float prevHeat;
	public final float prevCold;
	public final ItemStack repairItem;
	public final SoundEvent sound;
	public float toughness;

	private DCMaterialEnum(int i, String n, float atk, int[] def, int tier, int dur, float eff, int enc, float heat,
			float cold, ItemStack repair, SoundEvent se, float tough) {
		id = i;
		name = n;
		attackDam = atk;
		reduceDam = def;
		harvestTier = tier;
		duration = dur;
		armorDur = dur / 20;
		efficiency = eff;
		enchant = enc;
		prevHeat = heat;
		prevCold = cold;
		repairItem = repair;
		sound = se;
		toughness = tough;
	}
}
