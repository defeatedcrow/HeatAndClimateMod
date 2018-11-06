package defeatedcrow.hac.main.item.tool;

import java.util.List;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTinderDC extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"tinder",
			"fire",
			"firestarter"
	};

	public ItemTinderDC() {
		super();
		maxMeta = 2;
		this.setFull3D();
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		pos = pos.offset(facing);
		if (stack.getItemDamage() != 0) {
			IBlockState target = world.getBlockState(pos);
			if (player.canPlayerEdit(pos, facing, stack)) {
				if (world.isAirBlock(pos)) {
					world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand
							.nextFloat() * 0.4F + 0.8F);
					world.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
				}
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

		ItemStack stack = player.getHeldItem(hand);
		RayTraceResult ray = this.rayTrace(world, player, true);

		if (stack.getItemDamage() != 0 || ray == null || ray.typeOfHit != RayTraceResult.Type.BLOCK) {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
		} else {
			BlockPos pos = ray.getBlockPos();
			IBlockState state = world.getBlockState(pos);
			if (isFireObject(state, pos, world)) {
				world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand
						.nextFloat() * 0.4F + 0.8F);
				stack.shrink(1);
				if (stack.isEmpty()) {
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, new ItemStack(this, 1, 1));
				} else {
					if (!player.world.isRemote) {
						EntityItem drop = new EntityItem(player.world, player.posX, player.posY, player.posZ,
								new ItemStack(this, 1, 1));
						drop.motionX = 0.0D;
						drop.motionY = 0.0D;
						drop.motionZ = 0.0D;
						player.world.spawnEntity(drop);
					}
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
				}
			} else {
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
			}
		}
	}

	private boolean isFireObject(IBlockState state, BlockPos pos, World world) {
		if (state != null) {
			if (state.getMaterial() == Material.FIRE || state.getMaterial() == Material.LAVA) {
				return true;
			} else if (world.getBlockState(pos.up()).getMaterial() == Material.FIRE) {
				return true;
			} else if (state.getBlock() == Blocks.TORCH || state.getBlock() == Blocks.LIT_FURNACE || state
					.getBlock() == Blocks.LIT_PUMPKIN) {
				return true;
			} else if (state.getBlock() instanceof IHeatTile) {
				DCHeatTier heat = ((IHeatTile) state.getBlock()).getHeatTier(world, pos, pos);
				return heat != null && heat.getTier() > DCHeatTier.BOIL.getTier();
			} else if (ClimateAPI.registerBlock.isRegisteredHeat(state.getBlock(), state.getBlock()
					.getMetaFromState(state))) {
				DCHeatTier heat = ClimateAPI.registerBlock.getHeatTier(state.getBlock(), state.getBlock()
						.getMetaFromState(state));
				return heat != null && heat.getTier() > DCHeatTier.BOIL.getTier();
			}
		}
		return false;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity,
			EnumHand hand) {
		if (entity == null || entity.world.isRemote) {
			return false;
		}

		ItemStack stack = player.getHeldItem(hand);

		if (entity.isBurning() || entity instanceof EntityBlaze) {
			if (stack.getItemDamage() == 0) {
				player.world.playSound(player, entity
						.getPosition(), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand
								.nextFloat() * 0.4F + 0.8F);
				stack.shrink(1);
				if (!player.world.isRemote) {
					EntityItem drop = new EntityItem(player.world, player.posX, player.posY, player.posZ, new ItemStack(
							this, 1, 1));
					drop.motionX = 0.0D;
					drop.motionY = 0.0D;
					drop.motionZ = 0.0D;
					player.world.spawnEntity(drop);
				}
				return true;
			}
		} else if (stack.getItemDamage() != 0) {
			player.world.playSound(player, entity
					.getPosition(), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand
							.nextFloat() * 0.4F + 0.8F);
			stack.shrink(1);
			entity.setFire(30);
			return true;
		}
		return false;
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
		String s = "items/tool/tinderitem_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, World world, List<String> tooltip) {
		if (stack != null) {
			int m = stack.getItemDamage();
			if (m == 0) {
				tooltip.add(I18n.format("dcs.tip.tinder"));
			} else if (m == 1) {
				tooltip.add(I18n.format("dcs.tip.firestarter"));
			} else if (m == 2) {
				tooltip.add(I18n.format("dcs.tip.firestarter"));
			}
		}
	}

}
