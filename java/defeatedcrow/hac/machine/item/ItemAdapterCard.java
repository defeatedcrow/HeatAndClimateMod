package defeatedcrow.hac.machine.item;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;

public class ItemAdapterCard extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"item_input",
			"item_output",
			"fluid_input",
			"fluid_output"
	};

	public ItemAdapterCard() {
		super();
		maxMeta = names.length - 1;
		this.setMaxStackSize(1);
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
		String s = "items/misc/card_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	// enum

	public CardType getCardType(int meta) {
		if (meta == 0 || meta == 1)
			return CardType.ITEM;
		else
			return CardType.FLUID;
	}

	public AccessType getAccessType(int meta) {
		if (meta == 0 || meta == 2)
			return AccessType.INPUT;
		else
			return AccessType.OUTPUT;
	}

	public enum CardType {
		ITEM,
		FLUID;
	}

	public enum AccessType {
		INPUT,
		OUTPUT;
	}

	// NBT

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player != null && !world.isRemote && player.isSneaking()) {
			ItemStack held = player.getHeldItem(hand);
			IBlockState state = world.getBlockState(pos);
			TileEntity tile = world.getTileEntity(pos);
			if (!DCUtil.isEmpty(held) && tile != null) {
				if (getCardType(held.getItemDamage()) == CardType.ITEM
						&& tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing)) {
					String mes1 = "Stored the inventory coordinate: " + pos.getX() + ", " + pos.getY() + ", "
							+ pos.getZ();
					player.addChatMessage(new TextComponentString(mes1));

					NBTTagCompound tag = held.getTagCompound();
					if (tag == null) {
						tag = new NBTTagCompound();
					}

					tag.setInteger("card.dim", world.provider.getDimension());
					tag.setInteger("card.X", pos.getX());
					tag.setInteger("card.Y", pos.getY());
					tag.setInteger("card.Z", pos.getZ());
					tag.setInteger("card.facing", facing.getIndex());

					held.setTagCompound(tag);

					return EnumActionResult.SUCCESS;
				} else if (getCardType(held.getItemDamage()) == CardType.FLUID
						&& tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing)) {
					String mes1 = "Stored the fluidtank coordinate: " + pos.getX() + ", " + pos.getY() + ", "
							+ pos.getZ();
					player.addChatMessage(new TextComponentString(mes1));

					NBTTagCompound tag = held.getTagCompound();
					if (tag == null) {
						tag = new NBTTagCompound();
					}

					tag.setInteger("card.dim", world.provider.getDimension());
					tag.setInteger("card.X", pos.getX());
					tag.setInteger("card.Y", pos.getY());
					tag.setInteger("card.Z", pos.getZ());
					tag.setInteger("card.facing", facing.getIndex());

					held.setTagCompound(tag);

					return EnumActionResult.SUCCESS;
				}
			}
		}
		return EnumActionResult.PASS;
	}

	public BlockPos getPos(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTagCompound()) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag.hasKey("card.dim")) {
				int x = tag.getInteger("card.X");
				int y = tag.getInteger("card.Y");
				int z = tag.getInteger("card.Z");
				return new BlockPos(x, y, z);
			}
		}
		return null;
	}

	public int getDim(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTagCompound()) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag.hasKey("card.dim"))
				return tag.getInteger("card.dim");
		}
		return 0;
	}

	public EnumFacing getFacing(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTagCompound()) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag.hasKey("card.dim")) {
				int id = tag.getInteger("card.facing");
				if (id >= 0 && id < 6)
					return EnumFacing.getFront(id);
			}
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		if (!DCUtil.isEmpty(stack) && stack.hasTagCompound()) {
			int dim = getDim(stack);
			BlockPos pos = getPos(stack);
			EnumFacing face = getFacing(stack);
			if (pos != null && face != null) {
				tooltip.add(TextFormatting.BOLD.toString() + "Stored data");
				tooltip.add("Dim: " + dim);
				tooltip.add("Pos: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
				tooltip.add("Side: " + face);
			}
		} else {
			tooltip.add(TextFormatting.AQUA.toString() + "Please register target tile by right-click with sneaking.");
		}
	}

}
