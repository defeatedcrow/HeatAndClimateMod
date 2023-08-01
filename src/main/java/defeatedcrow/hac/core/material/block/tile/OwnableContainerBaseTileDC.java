package defeatedcrow.hac.core.material.block.tile;

import java.util.UUID;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public abstract class OwnableContainerBaseTileDC extends BlockEntity implements Container, MenuProvider, Nameable {
	private UUID owner;
	protected String ownerName = "NO OWNER";
	private String date = " ";
	public static final UUID EMPTY_OWNER = UUID.fromString("613A8757-2068-4EA5-8EB1-5CB9A41111BF");
	private Component name;

	public OwnableContainerBaseTileDC(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	/* NBT */

	public void setOwner(UUID id) {
		this.owner = id;
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
		if (owner == null || owner.equals(EMPTY_OWNER))
			return true;
		if (player == null || player.isSpectator())
			return false;
		if (FMLLoader.getDist().isClient())
			return true;
		if (player.getUUID().equals(owner))
			return true;
		if (player.getLevel().getServer().getPlayerList().isOp(player.getGameProfile()))
			return true;
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
	public void load(CompoundTag tag) {
		super.load(tag);
		this.owner = tag.getUUID(TagKeyDC.OWNER_UUID);
		ownerName = tag.getString("ownerName");
		date = tag.getString("dateString");
		if (tag.contains("CustomName", 8)) {
			this.name = Component.Serializer.fromJson(tag.getString("CustomName"));
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putUUID(TagKeyDC.OWNER_UUID, owner);
		tag.putString("ownerName", ownerName);
		tag.putString("dateString", date);
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

	private LazyOptional<?> itemHandler = LazyOptional.of(() -> createUnSidedHandler());

	protected IItemHandler createUnSidedHandler() {
		return new InvWrapper(this);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
		if (!this.remove && cap == ForgeCapabilities.ITEM_HANDLER)
			return itemHandler.cast();
		return super.getCapability(cap, side);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		itemHandler.invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		itemHandler = LazyOptional.of(() -> createUnSidedHandler());
	}

}
