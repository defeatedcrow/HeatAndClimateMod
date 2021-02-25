package defeatedcrow.hac.main.block.build;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** 本家感圧板の継承 */
public class BlockPressurePlateDC extends BlockBasePressurePlate {

	private final boolean isPlayerOnly;

	public BlockPressurePlateDC(Material materialIn, boolean b, String name) {
		super(materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.POWERED, Boolean.valueOf(false)));
		this.isPlayerOnly = b;
		this.setUnlocalizedName(name);
	}

	@Override
	protected int getRedstoneStrength(IBlockState state) {
		return state.getValue(DCState.POWERED).booleanValue() ? 15 : 0;
	}

	@Override
	protected IBlockState setRedstoneStrength(IBlockState state, int strength) {
		return state.withProperty(DCState.POWERED, Boolean.valueOf(strength > 0));
	}

	@Override
	protected void playClickOnSound(World world, BlockPos color) {
		world.playSound((EntityPlayer) null, color, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
	}

	@Override
	protected void playClickOffSound(World world, BlockPos pos) {
		world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
	}

	@Override
	protected int computeRedstoneStrength(World world, BlockPos pos) {
		AxisAlignedBB axisalignedbb = PRESSURE_AABB.offset(pos);
		List<EntityLivingBase> list = Lists.newArrayList();

		if (isPlayerOnly) {
			list = world.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);
		} else {
			List<EntityLivingBase> check;
			check = world.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
			for (EntityLivingBase living : check) {
				if (living instanceof EntityPlayer) {
					list.add(living);
				} else if (living instanceof EntityTameable && ((EntityTameable) living).isTamed()) {
					list.add(living);
				}
			}
		}

		if (!list.isEmpty()) {
			for (Entity entity : list) {
				if (!entity.doesEntityNotTriggerPressurePlate()) {
					return 15;
				}
			}
		}

		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(DCState.POWERED, Boolean.valueOf(meta == 1));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(DCState.POWERED).booleanValue() ? 1 : 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DCState.POWERED });
	}

	public static enum Sensitivity {
		EVERYTHING,
		MOBS;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		boolean b = world.getBlockState(pos.up()).getMaterial() == Material.AIR;
		if (!b && world.getBlockState(pos.offset(face)).getMaterial() == Material.WATER)
			return true;
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		super.addInformation(stack, player, tooltip, advanced);
		tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
		int i = isPlayerOnly ? 0 : 1;
		tooltip.add(I18n.format("dcs.tip.pressure_plate_" + i));

	}
}
