package defeatedcrow.hac.magic.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.proj.EntityProjChalB;
import defeatedcrow.hac.magic.proj.EntityProjChalR;
import defeatedcrow.hac.magic.proj.EntityProjChalW;
import defeatedcrow.hac.magic.proj.EntityProjClmD;
import defeatedcrow.hac.magic.proj.EntityProjClmM;
import defeatedcrow.hac.magic.proj.EntityProjCryC;
import defeatedcrow.hac.magic.proj.EntityProjCryL;
import defeatedcrow.hac.magic.proj.EntityProjCryM;
import defeatedcrow.hac.magic.proj.EntityProjLapC;
import defeatedcrow.hac.magic.proj.EntityProjLapM;
import defeatedcrow.hac.magic.proj.EntityProjLapS;
import defeatedcrow.hac.magic.proj.EntityProjSapB;
import defeatedcrow.hac.magic.proj.EntityProjSapR;
import defeatedcrow.hac.magic.proj.EntityProjSapW;
import defeatedcrow.hac.magic.proj.EntityProjSchB;
import defeatedcrow.hac.magic.proj.EntityProjSchC;
import defeatedcrow.hac.main.entity.EntityProjBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMagicDagger extends DCItem {

	/*
	 * chal_blue 氷属性
	 * chal_red 火属性
	 * chal_white 無属性・増幅
	 * crystal 浄化・解呪・純化
	 * sapphire 知識・魔法
	 * malachite 地属性・視力・吸収
	 * celestite 風属性・拡散
	 * clam 亜空間
	 * lapis 収穫・幸運・防御
	 * diamond 採掘・金剛
	 * schorl 雷属性・反撃
	 */
	/*
	 * 補助石として:
	 * chal(white) 単体攻撃
	 * lapis 範囲効果
	 * crystal 防御効果
	 * malachite 吸収
	 * celestite 拡散
	 * sapphire 上位魔法
	 * diamond 設置型
	 */

	private final int maxMeta;

	private final float maxCount = 128;

	private static String[] names = {
			// chal 3種
			"chal_w", /* 0:無属性ダメージ */
			"sap_w", /* 1:大爆発 */
			"chal_b", /* 2:水属性ダメージ */
			"sap_b", /* 3:ABSOLUTE */
			"chal_r", /* 4:火属性ダメージ */
			"sap_r", /* 5:INFERNO */
			// 範囲攻撃系
			"cry_mal", /* 6:グラビティ */
			"cry_cel", /* 7:レビテーション */
			"cry_sap_lap", /* 8:混乱付与 */
			// 防御系
			"lap_cry", /* 9:進入エネミー消去 */
			"lap_sch", /* 10:Proj防御壁 */
			"lap_mal", /* 11:リジェネ・デバフ解除 */
			// 補助効果
			"sch_cel_b", /* 12:落雷+天候を雷雨に */
			"sch_clm", /* 13:着弾点に移動 */
			// エリア効果
			"clm_dia_sap", /* 14:鉱石生成イベント呼び出し */
			"clm_mal_cel"
			/* 15:Biome書き換え */ };

	public ItemMagicDagger() {
		super();
		maxMeta = 15;
		this.setMaxStackSize(16);
		this.setFull3D();
	}

	/* 雪玉に似た動作をする */

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		return EnumActionResult.FAIL;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase living, int timeLeft) {
		if (stack != null && living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			boolean flag = player.capabilities.isCreativeMode;
			int metadata = stack.getItemDamage();

			if (!DCUtil.isEmpty(stack) || flag) {
				if (!world.isRemote) {
					EntityProjBase entityarrow = getEntity(metadata, world, player);
					entityarrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
					world.spawnEntity(entityarrow);
				}

				world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ,
						SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F,
						1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);

				if (!flag) {
					DCUtil.reduceStackSize(stack, 1);
				}

				player.addStat(StatList.getObjectUseStats(this));

			}
		}
	}

	private EntityProjBase getEntity(int meta, World world, EntityPlayer player) {
		int i = Math.min(meta, 15);
		switch (i) {
		case 0:
			return new EntityProjChalW(world, player);
		case 1:
			return new EntityProjSapW(world, player);
		case 2:
			return new EntityProjChalB(world, player);
		case 3:
			return new EntityProjSapB(world, player);
		case 4:
			return new EntityProjChalR(world, player);
		case 5:
			return new EntityProjSapR(world, player);
		case 6:
			return new EntityProjCryM(world, player);
		case 7:
			return new EntityProjCryC(world, player);
		case 8:
			return new EntityProjCryL(world, player);
		case 9:
			return new EntityProjLapC(world, player);
		case 10:
			return new EntityProjLapS(world, player);
		case 11:
			return new EntityProjLapM(world, player);
		case 12:
			return new EntityProjSchB(world, player);
		case 13:
			return new EntityProjSchC(world, player);
		case 14:
			return new EntityProjClmD(world, player);
		case 15:
			return new EntityProjClmM(world, player);
		default:
			return new EntityProjChalW(world, player);
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		player.setActiveHand(hand);
		return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int i = Math.min(meta, 15);
		String s = "items/magic/dagger_" + names[i];
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int j = Math.min(stack.getMetadata(), getMaxMeta());
		return super.getUnlocalizedName() + "_" + j;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		String s = "";
		int meta = stack.getMetadata();
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.translateToLocal("dcs.tip.dagger." + meta));
			if (meta == 13) {
				tooltip.add(TextFormatting.BOLD.toString() + "RETURNABLE");
			} else {
				tooltip.add(TextFormatting.BOLD.toString() + "DISPOSABLE");
			}
		} else {
			tooltip.add(TextFormatting.RESET.toString() + I18n.translateToLocal("dcs.tip.shift"));
		}
	}

}
