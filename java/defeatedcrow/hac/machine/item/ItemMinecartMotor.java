package defeatedcrow.hac.machine.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.machine.entity.EntityMinecartMotor;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMinecartMotor extends DCItem {

	public ItemMinecartMotor() {
		super();
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, MINECARTMOTOR_DISPENSER_BEHAVIOR);
		this.setMaxStackSize(3);
	}

	@Override
	public int getMaxMeta() {
		return 0;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 1);
		String s = "items/block/flowerpot_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"white"
		};
		return s;
	}

	private static final IBehaviorDispenseItem MINECARTMOTOR_DISPENSER_BEHAVIOR = new BehaviorDefaultDispenseItem() {
		private final BehaviorDefaultDispenseItem behaviourDefaultDispenseItem = new BehaviorDefaultDispenseItem();

		/**
		 * Dispense the specified stack, play the dispense sound and spawn particles.
		 */
		@Override
		public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
			EnumFacing enumfacing = source.getBlockState().getValue(BlockDispenser.FACING);
			World world = source.getWorld();
			double d0 = source.getX() + enumfacing.getFrontOffsetX() * 1.125D;
			double d1 = Math.floor(source.getY()) + enumfacing.getFrontOffsetY();
			double d2 = source.getZ() + enumfacing.getFrontOffsetZ() * 1.125D;
			BlockPos blockpos = source.getBlockPos().offset(enumfacing);
			IBlockState iblockstate = world.getBlockState(blockpos);
			BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = iblockstate
					.getBlock() instanceof BlockRailBase
							? (BlockRailBase.EnumRailDirection) iblockstate
									.getValue(((BlockRailBase) iblockstate.getBlock()).getShapeProperty())
							: BlockRailBase.EnumRailDirection.NORTH_SOUTH;
			double d3;

			if (BlockRailBase.isRailBlock(iblockstate)) {
				if (blockrailbase$enumraildirection.isAscending()) {
					d3 = 0.6D;
				} else {
					d3 = 0.1D;
				}
			} else {
				if (iblockstate.getMaterial() != Material.AIR
						|| !BlockRailBase.isRailBlock(world.getBlockState(blockpos.down())))
					return this.behaviourDefaultDispenseItem.dispense(source, stack);

				IBlockState iblockstate1 = world.getBlockState(blockpos.down());
				BlockRailBase.EnumRailDirection blockrailbase$enumraildirection1 = iblockstate1
						.getBlock() instanceof BlockRailBase
								? (BlockRailBase.EnumRailDirection) iblockstate1
										.getValue(((BlockRailBase) iblockstate1.getBlock()).getShapeProperty())
								: BlockRailBase.EnumRailDirection.NORTH_SOUTH;

				if (enumfacing != EnumFacing.DOWN && blockrailbase$enumraildirection1.isAscending()) {
					d3 = -0.4D;
				} else {
					d3 = -0.9D;
				}
			}

			EntityMinecart entityminecart = new EntityMinecartMotor(world, d0, d1 + d3, d2);

			if (stack.hasDisplayName()) {
				entityminecart.setCustomNameTag(stack.getDisplayName());
			}

			world.spawnEntity(entityminecart);
			stack.splitStack(1);
			return stack;
		}

		/**
		 * Play the dispense sound from the specified block.
		 */
		@Override
		protected void playDispenseSound(IBlockSource source) {
			source.getWorld().playEvent(1000, source.getBlockPos(), 0);
		}
	};

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	@Override
	public EnumActionResult onItemUse2(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState iblockstate = worldIn.getBlockState(pos);

		if (!BlockRailBase.isRailBlock(iblockstate) || playerIn == null)
			return EnumActionResult.FAIL;
		else {
			if (!worldIn.isRemote) {
				BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = iblockstate
						.getBlock() instanceof BlockRailBase
								? (BlockRailBase.EnumRailDirection) iblockstate
										.getValue(((BlockRailBase) iblockstate.getBlock()).getShapeProperty())
								: BlockRailBase.EnumRailDirection.NORTH_SOUTH;
				double d0 = 0.0D;

				if (blockrailbase$enumraildirection.isAscending()) {
					d0 = 0.5D;
				}

				EntityMinecart entityminecart = new EntityMinecartMotor(worldIn, pos.getX() + 0.5D,
						pos.getY() + 0.0625D + d0, pos.getZ() + 0.5D);
				ItemStack stack = playerIn.getHeldItem(hand);

				if (stack.hasDisplayName()) {
					entityminecart.setCustomNameTag(stack.getDisplayName());
				}

				worldIn.spawnEntity(entityminecart);
				stack.splitStack(1);
			}

			return EnumActionResult.SUCCESS;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add("Placeable as an Entity");
	}

}
