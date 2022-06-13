package defeatedcrow.hac.magic.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageMagicParticle;
import defeatedcrow.hac.main.util.MainUtil;
import defeatedcrow.hac.main.worldgen.CaravanData;
import defeatedcrow.hac.main.worldgen.MazaiLakeGen;
import defeatedcrow.hac.main.worldgen.WorldGenCaravanBase;
import defeatedcrow.hac.main.worldgen.WorldGenHotspring;
import defeatedcrow.hac.main.worldgen.WorldGenWindmill;
import defeatedcrow.hac.main.worldgen.vein.WorldGenSkarn;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorCard4 extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"gu3",
			"gg3",
			"gr3",
			"gw3",
			"gb3"
	};

	public ItemColorCard4() {
		super();
		maxMeta = 4;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/magic/card_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	public MagicColor getColor(int meta) {
		switch (meta) {
		case 0:
			return MagicColor.BLUE;
		case 1:
			return MagicColor.GREEN;
		case 2:
			return MagicColor.RED;
		case 3:
			return MagicColor.WHITE;
		case 4:
			return MagicColor.BLACK;
		default:
			return MagicColor.NONE;
		}
	}

	/* 雪玉に似た動作をする */

	@Override
	public EnumActionResult onItemUse2(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		return EnumActionResult.FAIL;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase living, int timeLeft) {
		if (stack != null && living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			boolean flag = !player.capabilities.isCreativeMode;
			int meta = stack.getItemDamage();

			if (!world.isRemote) {
				float f = MainUtil.magicSuitEff(player);
				switch (meta) {
				case 0:
					onEffect_U(world, player, f);
					break;
				case 1:
					onEffect_G(world, player, f);
					break;
				case 2:
					onEffect_R(world, player, f);
					break;
				case 3:
					onEffect_W(world, player, f);
					break;
				case 4:
					onEffect_B(world, player, f);
					break;
				default:
					onEffect_G(world, player, f);
					break;
				}
				DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(living.posX, living.posY, living.posZ));
			}
			world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.NEUTRAL, 0.8F, 1.5F);
			if (flag) {
				DCUtil.reduceStackSize(stack, 1);
			}
			player.addStat(StatList.getObjectUseStats(this));
		}
	}

	private boolean onEffect_U(World world, EntityPlayer player, float f) {
		WorldGenWindmill gen = new WorldGenWindmill(true);
		int x = MathHelper.floor(player.posX);
		int y = MathHelper.floor(player.posY);
		int z = MathHelper.floor(player.posZ);
		gen.setForcePos(x, z);
		BlockPos p = new BlockPos(x, y + 1, z);
		ChunkPos c = new ChunkPos(p);
		gen.generate(world.rand, c.x, c.z, world, world.provider.createChunkGenerator(), world
				.getChunkProvider());
		return true;
	}

	private boolean onEffect_G(World world, EntityPlayer player, float f) {
		WorldGenSkarn skarn = new WorldGenSkarn(true);
		int x = MathHelper.floor(player.posX);
		int y = MathHelper.floor(player.posY);
		int z = MathHelper.floor(player.posZ);
		skarn.setForcePos(x, z);
		BlockPos p = new BlockPos(x, y + 1, z);
		ChunkPos c = new ChunkPos(p);
		int r = 3;
		for (int i = -r; i <= r; i++) {
			for (int j = -r; j <= r; j++) {
				skarn.generate(world.rand, c.x + i, c.z + j, world, world.provider.createChunkGenerator(), world
						.getChunkProvider());
			}
		}
		return true;
	}

	private boolean onEffect_R(World world, EntityPlayer player, float f) {
		WorldGenHotspring gen = new WorldGenHotspring().setForced();
		int x = MathHelper.floor(player.posX);
		int y = MathHelper.floor(player.posY);
		int z = MathHelper.floor(player.posZ);
		BlockPos p = new BlockPos(x, y - 1, z);
		ChunkPos c = new ChunkPos(p);
		int r = 3 + MathHelper.floor(f * 2F);
		gen.generate(world, itemRand, p, r);
		return true;
	}

	private boolean onEffect_B(World world, EntityPlayer player, float f) {
		MazaiLakeGen gen = new MazaiLakeGen().setForced();
		int x = MathHelper.floor(player.posX);
		int y = MathHelper.floor(player.posY);
		int z = MathHelper.floor(player.posZ);
		BlockPos p = new BlockPos(x, y - 1, z);
		ChunkPos c = new ChunkPos(p);
		gen.generate(world, itemRand, p);
		return true;
	}

	private boolean onEffect_W(World world, EntityPlayer player, float f) {
		int x = MathHelper.floor(player.posX);
		int y = MathHelper.floor(player.posY);
		int z = MathHelper.floor(player.posZ);
		if (y < 10 || y > 200)
			return false;
		int cx = x >> 4;
		int cz = z >> 4;
		WorldGenCaravanBase gen = new WorldGenCaravanBase();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				CaravanData data = CaravanData.getForcedData(cx + i, cz + j, y - 1, world, cx, cz);
				int num = data.partNum;
				int nx = (num % 3) - 1;
				int nz = (num / 3) - 1;
				int cx2 = cx + nx;
				int cz2 = cz + nz;

				int height = data.height;
				DCLogger.debugInfoLog("Caravanserai Forced Core" + " : " + cx2 + ", " + cz2 + " height " + height + " num" + data.partNum);
				if (num == 4) {
					gen.generateCore(data, itemRand, cx + i, cz + j, height, world);
				} else {
					gen.generatePart(data, itemRand, cx + i, cz + j, world);
				}
			}
		}
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick2(World world, EntityPlayer player, EnumHand hand) {
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
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		String s = "";
		int meta = stack.getMetadata();
		CardType type = CardType.getType(meta);
		tooltip.add(TextFormatting.BOLD.toString() + "PLAYER ONLY");
		tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.card_type." + type.toString()));
		tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
		tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.card4." + meta));
		tooltip.add(TextFormatting.RED.toString() + TextFormatting.BOLD.toString() + I18n.format("dcs.comment.card4_2"));
	}

	public enum CardType {
		SELF_BUFF,
		PROJECTILE,
		AREA,
		SPAWN;

		public static CardType getType(int meta) {
			return SPAWN;
		}
	}

}
