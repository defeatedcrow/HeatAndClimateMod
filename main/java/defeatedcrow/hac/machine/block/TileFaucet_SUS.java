package defeatedcrow.hac.machine.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.core.base.ITagGetter;
import defeatedcrow.hac.main.api.IColorChangeTile;
import defeatedcrow.hac.main.client.particle.ParticleWaterDrip;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageColorID;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.client.FMLClientHandler;

public class TileFaucet_SUS extends DCTileEntity implements IColorChangeTile, ITagGetter {

	@Override
	public void updateTile() {
		super.updateTile();
		if (!world.isRemote) {
			// 水が無限に出てくる
			IBlockState state = world.getBlockState(getPos());
			if (!DCState.getBool(state, DCState.POWERED))
				return;
			else {
				TileEntity toTE = world.getTileEntity(getPos().down());
				if (toTE != null && toTE
						.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP)) {
					IFluidHandler outtank = toTE
							.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
					if (outtank != null) {
						int limit = 1000; // 1000mBずつ
						// 引き出せる量
						FluidStack get = new FluidStack(FluidRegistry.WATER, limit);
						int ret = outtank.fill(get, false);
						if (ret > 0) {
							outtank.fill(get, true);
						}
					}
				}
			}
		}
	}

	public void onTickUpdate() {
		// client
		if (world.isRemote && ClimateCore.proxy.getClientWorld() != null) {
			World wc = ClimateCore.proxy.getClientWorld();
			IBlockState state = wc.getBlockState(getPos());
			if (DCState.getBool(state, DCState.POWERED)) {
				EnumSide side = DCState.getSide(state, DCState.SIDE);
				if ((side.face.getAxis() == EnumFacing.Axis.Y && color == 2) || color == 4) {
					double dx = pos.getX() + 0.25D + wc.rand.nextFloat() * 0.5D;
					double dy = pos.getY() + 0.35D;
					double dz = pos.getZ() + 0.25D + wc.rand.nextFloat() * 0.5D;
					Particle drip = new ParticleWaterDrip.Factory()
							.createParticle(0, world, dx, dy, dz, 0D, 0D, 0D, null);
					FMLClientHandler.instance().getClient().effectRenderer.addEffect(drip);
				}
			}
		}

	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.color = tag.getInteger("Color");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("Color", this.color);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		// アイテムの書き込み
		tag.setInteger("Color", this.color);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		this.color = tag.getInteger("Color");
	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		this.writeToNBT(nbtTagCompound);
		return new SPacketUpdateTileEntity(pos, -50, nbtTagCompound);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	// color

	protected int color = 0;

	@Override
	public int getColor() {
		return color;
	}

	@Override
	public void setColor(int num) {
		color = num;
		if (color < 0 || color > 4) {
			color = 0;
		}
	}

	@Override
	public boolean rotateColor() {
		int c = color + 1;
		setColor(c);
		if (!world.isRemote) {
			DCMainPacket.INSTANCE.sendToAll(new MessageColorID(pos, color));
		}
		return true;
	}

}
