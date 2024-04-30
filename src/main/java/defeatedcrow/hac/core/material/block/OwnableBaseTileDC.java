package defeatedcrow.hac.core.material.block;

import java.util.UUID;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.loading.FMLLoader;

public abstract class OwnableBaseTileDC extends BlockEntity implements MenuProvider, Nameable {

	private String date = " ";
	public static final UUID EMPTY_OWNER = UUID.fromString("613A8757-2068-4EA5-8EB1-5CB9A41111BF");
	private UUID owner = EMPTY_OWNER;
	protected String ownerName = "NO OWNER";
	private Component name;
	private boolean isLocked = false;

	public OwnableBaseTileDC(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	/* NBT */

	public boolean lock(boolean lock) {
		isLocked = lock;
		DCLogger.debugInfoLog("### OwnableTile lock: " + isLocked + " ###");
		this.setChanged();
		return isLocked;
	}

	public boolean toggleLock() {
		isLocked = !isLocked;
		DCLogger.debugInfoLog("### OwnableTile toggle lock: " + isLocked + " ###");
		this.setChanged();
		return isLocked;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setOwner(UUID id) {
		this.owner = id;
		this.setChanged();
	}

	public void setOwnerName(String name) {
		ownerName = name;
	}

	public void setDate() {
		date = DCTimeHelper.getLocalizedDate();
	}

	public UUID getOwner() {
		return this.owner;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getDate() {
		return date;
	}

	public boolean hasOwner() {
		return owner != null && !owner.equals(EMPTY_OWNER);
	}

	public boolean canOpen(Player player) {
		if (!isLocked)
			return true;
		if (owner == null || owner.equals(EMPTY_OWNER))
			return true;
		return isOwner(player);
	}

	public boolean isOwner(Player player) {
		if (owner != null && !owner.equals(EMPTY_OWNER)) {
			if (player != null && !player.isSpectator() && player.getUUID().equals(owner)) // 380df991-f603-344c-a090-369bad2a924a
				return true;
		}
		if (!FMLLoader.getDist().isClient() && ClimateCore.proxy.isOP(player)) {
			return true;
		}
		return false;
	}

	public void setCustomName(Component n) {
		this.name = n;
	}

	@Override
	public Component getName() {
		return this.name != null ? this.name : this.getDefaultName();
	}

	@Override
	public Component getDisplayName() {
		return this.getName();
	}

	@Override
	@Nullable
	public Component getCustomName() {
		return this.name;
	}

	protected abstract Component getDefaultName();

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithoutMetadata();
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		loadTag(tag);
	}

	public void loadTag(CompoundTag tag) {
		if (tag.contains(TagKeyDC.OWNER_UUID))
			owner = tag.getUUID(TagKeyDC.OWNER_UUID);
		if (tag.contains("ownerName"))
			ownerName = tag.getString("ownerName");
		if (tag.contains("dateString"))
			date = tag.getString("dateString");
		isLocked = tag.getBoolean("locked");
		if (tag.contains("CustomName", 8)) {
			name = Component.Serializer.fromJson(tag.getString("CustomName"));
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		writeTag(tag);
	}

	public void writeTag(CompoundTag tag) {
		tag.putUUID(TagKeyDC.OWNER_UUID, owner);
		tag.putString("ownerName", ownerName);
		tag.putString("dateString", date);
		tag.putBoolean("locked", isLocked);
		if (this.name != null) {
			tag.putString("CustomName", Component.Serializer.toJson(this.name));
		}
	}

	@Override
	@Nullable
	public AbstractContainerMenu createMenu(int i, Inventory inv, Player player) {
		return this.canOpen(player) ? this.createMenu(i, inv) : null;
	}

	protected abstract AbstractContainerMenu createMenu(int i, Inventory inv);

}
