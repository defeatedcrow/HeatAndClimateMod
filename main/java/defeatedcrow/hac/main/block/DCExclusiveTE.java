package defeatedcrow.hac.main.block;

import java.util.UUID;

import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;

public abstract class DCExclusiveTE extends DCLockableTE {

	protected UUID owner = null;
	protected String ownerName = "NO OWNER";
	private String date = " ";

	public void setOwner(UUID id) {
		owner = id;
	}

	public void setOwnerName(String name) {
		ownerName = name;
	}

	public void setDate() {
		date = DCName.getLocalizedDate();
	}

	public UUID getOwner() {
		return owner;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getDate() {
		return date;
	}

	public boolean isOwnerOrOP(EntityPlayer player) {
		if (player == null)
			return false;
		if (owner == null)
			return true;
		if (FMLCommonHandler.instance().getMinecraftServerInstance().isSinglePlayer())
			return true;
		if (player.getDisplayNameString().equals(ownerName) || player.getUniqueID().equals(owner)) {
			return true;
		}
		if (player.getUniqueID() != null) {
			if (ClimateMain.proxy.isOP(player)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		if (tag.hasUniqueId("owner"))
			owner = tag.getUniqueId("owner");
		ownerName = tag.getString("ownerName");
		date = tag.getString("dateString");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// アイテムの書き込み
		if (owner != null)
			tag.setUniqueId("owner", owner);
		tag.setString("ownerName", ownerName);
		tag.setString("dateString", date);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// アイテムの書き込み
		if (owner != null)
			tag.setUniqueId("owner", owner);
		tag.setString("ownerName", ownerName);
		tag.setString("dateString", date);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		if (tag.hasUniqueId("owner"))
			owner = tag.getUniqueId("owner");
		ownerName = tag.getString("ownerName");
		date = tag.getString("dateString");
	}

}
