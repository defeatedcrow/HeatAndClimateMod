package defeatedcrow.hac.magic.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.proj.EntityFireBarrier;
import defeatedcrow.hac.magic.proj.EntityHealBarrier;
import defeatedcrow.hac.magic.proj.EntityMobBarrier;
import defeatedcrow.hac.magic.proj.EntityProjBarrier;
import defeatedcrow.hac.magic.proj.EntityProjBlackSpit;
import defeatedcrow.hac.magic.proj.EntityProjRedSpit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageMagicParticle;
import defeatedcrow.hac.main.util.MainUtil;
import defeatedcrow.hac.main.worldgen.OreGenPos;
import defeatedcrow.hac.main.worldgen.OreGenPos.OreVein;
import defeatedcrow.hac.main.worldgen.WorldGenOres3;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorCard extends DCItem {

	private final int maxMeta;

	private static String[] names = {
		"u1",
		"g1",
		"r1",
		"b1",
		"w1",
		"u2",
		"g2",
		"r2",
		"b2",
		"w2",
		"u3",
		"g3",
		"r3",
		"b3",
		"w3" };

	public ItemColorCard() {
		super();
		maxMeta = 14;
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
		return stack.getItemDamage() > 9 ? EnumRarity.RARE : stack.getItemDamage() > 4 ? EnumRarity.UNCOMMON :
				EnumRarity.COMMON;
	}

	public MagicColor getColor(int meta) {
		switch (meta) {
		case 0:
		case 5:
		case 10:
			return MagicColor.BLUE;
		case 1:
		case 6:
		case 11:
			return MagicColor.GREEN;
		case 2:
		case 7:
		case 12:
			return MagicColor.RED;
		case 3:
		case 8:
		case 13:
			return MagicColor.BLACK;
		case 4:
		case 9:
		case 14:
			return MagicColor.WHITE;
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
					onEffect_Blue1(world, player, f);
					break;
				case 1:
					onEffect_Green1(world, player, f);
					break;
				case 2:
					onEffect_Red1(world, player, f);
					break;
				case 3:
					onEffect_Black1(world, player, f);
					break;
				case 4:
					onEffect_White1(world, player, f);
					break;
				case 5:
					onEffect_Blue2(world, player, f);
					break;
				case 6:
					onEffect_Green2(world, player, f);
					break;
				case 7:
					onEffect_Red2(world, player, f);
					break;
				case 8:
					onEffect_Black2(world, player, f);
					break;
				case 9:
					onEffect_White2(world, player, f);
					break;
				case 10:
					onEffect_Blue3(world, player, f);
					break;
				case 11:
					onEffect_Green3(world, player, f);
					break;
				case 12:
					onEffect_Red3(world, player, f);
					break;
				case 13:
					onEffect_Black3(world, player, f);
					break;
				case 14:
					onEffect_White3(world, player, f);
					break;
				default:
					onEffect_Green1(world, player, f);
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

	private boolean onEffect_Blue1(World world, EntityPlayer player, float f) {
		int dim = world.provider.getDimension();
		player.setSpawnPoint(player.getPosition(), false);
		if (player.getBedLocation(dim) != null) {
			BlockPos pos = player.getBedLocation(dim);
			player.sendMessage(new TextComponentString("New Respawn Point: " + pos.getX() + ", " + pos
					.getY() + ", " + pos.getZ()));
			return true;
		}
		return false;
	}

	private boolean onEffect_Green1(World world, EntityPlayer player, float f) {
		BlockPos pos = player.getPosition();
		int r = MathHelper.floor(4 * f);
		Iterable<BlockPos> itr = BlockPos.getAllInBox(pos.add(-r, -1, -r), pos.add(r, 3, r));
		for (BlockPos p1 : itr) {
			IBlockState st = world.getBlockState(p1);
			int meta = st.getBlock().getMetaFromState(st);
			if (world.isAirBlock(p1) || st.getBlock() == Blocks.DIRT || st.getBlock() == Blocks.GRASS) {
				continue;
			}
			if (st.getBlock() instanceof IGrowable) {
				IGrowable pl = (IGrowable) st.getBlock();
				if (pl.canUseBonemeal(world, world.rand, p1, st) && pl.canGrow(world, p1, st, false)) {
					pl.grow(world, world.rand, p1, st);
					if (world instanceof WorldServer) {
						((WorldServer) world).spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, p1.getX() + 0.5D, p1
								.getY() + 0.5D, p1.getZ() + 0.5D, 8, 0.5D, 0.5D, 0.5D, 0.5D, new int[0]);
					}
				}
			}
		}

		return true;
	}

	private boolean onEffect_Red1(World world, EntityPlayer player, float f) {
		int r = MathHelper.floor(6000 * f);
		int amp = 1 + MathHelper.floor(2F * f);
		player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, r, amp));
		player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, r, amp));
		player.addPotionEffect(new PotionEffect(MobEffects.HASTE, r, amp));
		return true;
	}

	private boolean onEffect_Black1(World world, EntityPlayer player, float f) {
		int r = MathHelper.floor(8 * f);
		AxisAlignedBB aabb = new AxisAlignedBB(player.posX - r, player.posY - 2D, player.posZ - r, player.posX + r,
				player.posY + 2D, player.posZ + r);
		List<EntityMob> list = player.world.getEntitiesWithinAABB(EntityMob.class, aabb);
		if (list.isEmpty() || list.size() < 2)
			return false;
		else {
			EntityMob t1 = null;
			EntityMob t2 = null;
			for (int i = 0; i < list.size(); i++) {
				t1 = list.get(i);
				t2 = i == list.size() - 1 ? list.get(0) : list.get(i + 1);
				t1.setAttackTarget(t2);
				DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(t1.posX, t1.posY, t1.posZ));
			}
		}
		return true;
	}

	private boolean onEffect_White1(World world, EntityPlayer player, float f) {
		IBlockState cur = player.world.getBlockState(player.getPosition().up());
		IBlockState set = MainInit.lightOrb.getDefaultState();
		if (cur.getMaterial().isReplaceable() && player.world.setBlockState(player.getPosition().up(), set))
			return true;
		return true;
	}

	private boolean onEffect_Blue2(World world, EntityPlayer player, float f) {
		int r = MathHelper.floor(12000 * f);
		player.addPotionEffect(new PotionEffect(MainInit.ocean, r, 0));
		return true;
	}

	private boolean onEffect_Green2(World world, EntityPlayer player, float f) {
		int x = MathHelper.floor(player.posX);
		int y = MathHelper.floor(player.posY);
		int z = MathHelper.floor(player.posZ);
		BlockPos p = new BlockPos(x, y + 1, z);
		ChunkPos c = new ChunkPos(p);
		OreVein[] veins = OreGenPos.INSTANCE.getVeins(c.x, c.z, player.world);
		List<OreVein> list = Lists.newArrayList();
		if (veins != null) {
			for (OreVein v : veins) {
				if (v != null)
					list.add(v);
			}
		}
		if (!list.isEmpty()) {
			player.sendMessage(new TextComponentString("== Ore vein detected! =="));
			for (OreVein v : list) {
				player.sendMessage(new TextComponentString("* Type: " + v.type.name() + " *"));
				player.sendMessage(new TextComponentString(" Pos: " + v.pos.getX() + ", " + v.pos.getY() + ", " + v.pos
						.getZ()));
			}
		} else {
			player.sendMessage(new TextComponentString("== No ore vein detected in this chank =="));
		}
		return true;
	}

	private boolean onEffect_Red2(World world, EntityPlayer player, float f) {
		EntityProjRedSpit entityarrow = new EntityProjRedSpit(world, player);
		entityarrow.setExplodeRange(f);
		entityarrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
		world.spawnEntity(entityarrow);
		return true;
	}

	private boolean onEffect_Black2(World world, EntityPlayer player, float f) {
		EntityProjBlackSpit entityarrow = new EntityProjBlackSpit(world, player);
		entityarrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
		world.spawnEntity(entityarrow);
		return true;
	}

	private boolean onEffect_White2(World world, EntityPlayer player, float f) {
		int r = MathHelper.floor(12000 * f);
		player.addPotionEffect(new PotionEffect(MainInit.bird, r, 0));
		return true;
	}

	private boolean onEffect_Red3(World world, EntityPlayer player, float f) {
		EntityFireBarrier circle = new EntityFireBarrier(world);
		circle.setPosition(player.posX, player.posY, player.posZ);
		world.spawnEntity(circle);
		return true;
	}

	private boolean onEffect_Blue3(World world, EntityPlayer player, float f) {
		EntityProjBarrier circle = new EntityProjBarrier(world);
		circle.setPosition(player.posX, player.posY, player.posZ);
		world.spawnEntity(circle);
		return true;
	}

	private boolean onEffect_Green3(World world, EntityPlayer player, float f) {
		WorldGenOres3 gen = new WorldGenOres3(true);
		int x = MathHelper.floor(player.posX);
		int y = MathHelper.floor(player.posY);
		int z = MathHelper.floor(player.posZ);
		BlockPos p = new BlockPos(x, y + 1, z);
		ChunkPos c = new ChunkPos(p);
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				gen.generate(world.rand, c.x + i, c.z + j, world, world.provider.createChunkGenerator(), world
						.getChunkProvider());
			}
		}
		return true;
	}

	private boolean onEffect_Black3(World world, EntityPlayer player, float f) {
		EntityMobBarrier circle = new EntityMobBarrier(world);
		circle.setPosition(player.posX, player.posY, player.posZ);
		world.spawnEntity(circle);
		return true;
	}

	private boolean onEffect_White3(World world, EntityPlayer player, float f) {
		EntityHealBarrier circle = new EntityHealBarrier(world);
		circle.setPosition(player.posX, player.posY, player.posZ);
		world.spawnEntity(circle);
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
		tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.card." + meta));
	}

	public enum CardType {
		SELF_BUFF,
		PROJECTILE,
		AREA,
		CIRCLE;

		public static CardType getType(int meta) {
			switch (meta) {
			case 0:
			case 2:
			case 5:
			case 9:
				return SELF_BUFF;
			case 7:
			case 8:
				return PROJECTILE;
			case 1:
			case 3:
			case 4:
			case 6:
			case 11:
				return AREA;
			case 10:
			case 12:
			case 13:
			case 14:
				return CIRCLE;
			default:
				return SELF_BUFF;
			}
		}
	}

}
