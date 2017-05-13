package defeatedcrow.hac.main.item.tool;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ItemInvManju extends DCItem {

	public ItemInvManju() {
		super();
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player != null && player.isSneaking() && !world.isRemote) {
			ItemStack held = stack;
			TileEntity te = world.getTileEntity(pos);
			if (!DCUtil.isEmpty(held) && held.getItem() == this && te != null
					&& te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing)) {
				IItemHandler current = held.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
				IItemHandler target = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);

				// Itemがわの受け入れ限界
				ItemStack extract = current.insertItem(0, new ItemStack(Items.EGG, 16), true);

				if (!DCUtil.isEmpty(extract)) {
					DCLogger.debugLog("insert limit: " + extract.stackSize);
					// TEがわの取り出し限界
					int slot = -1;
					for (int i = 0; i < target.getSlots(); i++) {
						ItemStack item = target.extractItem(i, extract.stackSize, true);
						if (!DCUtil.isEmpty(extract) && extract.getItem() == Items.EGG) {
							slot = i;
							extract = item;
							break;
						}
					}

					if (slot > -1) {
						DCLogger.debugLog("target extract limit: " + extract.stackSize);
						extract = target.extractItem(slot, extract.stackSize, false);
						current.insertItem(0, extract, false);
						te.markDirty();
						player.inventory.markDirty();
					}
				}
			}
		}
		return EnumActionResult.SUCCESS;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		if (DCUtil.isEmpty(stack))
			return;

		if (stack.hasTagCompound()) {
			int count = stack.getTagCompound().getInteger("ItemCount");
			tooltip.add(TextFormatting.YELLOW.toString() + "Amount: " + count);
		}
	}

	@Override
	public int getMaxMeta() {
		return 0;
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/tool/inv_manju_item";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	/* capability */
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new InvItemWrapperDC(stack);
	}

}
