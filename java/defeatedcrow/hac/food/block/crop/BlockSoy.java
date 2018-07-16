package defeatedcrow.hac.food.block.crop;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.cultivate.GrowingStage;
import defeatedcrow.hac.core.base.ClimateDoubleCropBase;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSoy extends ClimateDoubleCropBase implements ITexturePath {

	public BlockSoy(String s) {
		super(Material.PLANTS, s, 3);
		setSoundType(SoundType.PLANT);
		setHardness(0.0F);
		setResistance(3.0F);
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			IBlockState crop = world.getBlockState(pos);
			GrowingStage stage = this.getCurrentStage(crop);
			if (DCState.getInt(state, DCState.STAGE8) == 6) {
				player.playSound(SoundEvents.BLOCK_GRASS_PLACE, 1.0F, 1.0F);
				int f = 0;
				if (player != null && !DCUtil.isEmpty(player.getActiveItemStack())) {
					f = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getActiveItemStack());
				}
				List<ItemStack> crops = this.getCropItems(state, f);
				boolean ret = false;
				for (ItemStack item : crops) {
					EntityItem drop = new EntityItem(world);
					drop.setItem(item);
					if (player != null) {
						drop.setPosition(player.posX, player.posY + 0.15D, player.posZ);
					} else {
						drop.setPosition(pos.getX(), pos.getY() + 0.5D, pos.getZ());
					}
					if (!world.isRemote)
						world.spawnEntity(drop);
					ret = true;
				}
				if (ret) {
					IBlockState next = state.withProperty(DCState.STAGE8, 4);
					world.setBlockState(pos, next, 2);
				}
				return ret;
			}
		}
		return super.onRightClick(world, pos, state, player, hand, side, hitX, hitY, hitZ);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CROP_AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 7;
		boolean d = meta > 7;
		boolean shrub = m < 4;
		String b = "dcs_climate:blocks/crop/";
		String db = d ? "_under" : "_upper";
		return shrub ? b + "shrub_" + m : b + "soy_" + m + db;
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/crop/";
		list.add(b + "shrub_0");
		list.add(b + "shrub_1");
		list.add(b + "shrub_2");
		list.add(b + "shrub_3");
		list.add(b + "soy_4_upper");
		list.add(b + "soy_5_upper");
		list.add(b + "soy_6_upper");
		list.add(b + "soy_7_upper");
		list.add(b + "soy_4_under");
		list.add(b + "soy_5_under");
		list.add(b + "soy_6_under");
		list.add(b + "soy_7_under");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 7;
		boolean d = meta > 7;
		boolean shrub = m < 4;
		String b = "dcs_climate:blocks/crop/";
		String db = d ? "_under" : "_upper";
		return shrub ? b + "shrub_" + m : b + "soy_" + m + db;
	}

	@Override
	public ItemStack getSeedItem(IBlockState thisState) {
		return new ItemStack(FoodInit.seeds, 1, 9);
	}

	@Override
	public List<ItemStack> getCropItems(IBlockState thisState, int fortune) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		if (DCState.getInt(thisState, DCState.STAGE8) == 6) {
			list.add(new ItemStack(FoodInit.crops, 1, 12));
		} else {
			int i = 1 + rand.nextInt(1 + fortune);
			list.add(new ItemStack(FoodInit.seeds, i, 9));
		}
		return list;
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(IBlockState thisState) {
		List<DCHeatTier> ret = new ArrayList<DCHeatTier>();
		ret.add(DCHeatTier.NORMAL);
		ret.add(DCHeatTier.WARM);
		ret.add(DCHeatTier.HOT);
		ret.add(DCHeatTier.BOIL);
		return ret;
	}

	@Override
	public List<DCHumidity> getSuitableHum(IBlockState thisState) {
		List<DCHumidity> ret = new ArrayList<DCHumidity>();
		ret.add(DCHumidity.NORMAL);
		ret.add(DCHumidity.WET);
		return ret;
	}

}
