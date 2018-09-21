package defeatedcrow.hac.main.block.fluid;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.fluid.DCTank;
import net.minecraft.nbt.NBTTagCompound;

public class DCSidedTank extends DCTank {
	protected EnumSide side;

	public DCSidedTank(int cap) {
		super(cap);
	}

	public void setSide(EnumSide s) {
		side = s;
	}

	public EnumSide getSide() {
		return side;
	}

	@Override
	public DCTank readFromNBT(NBTTagCompound nbt, String key) {
		if (nbt != null && nbt.hasKey("tankSide")) {
			int i = nbt.getInteger("tankSide");
			side = EnumSide.fromIndex(i);
		} else {
			side = null;
		}
		return super.readFromNBT(nbt, key);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt, String key) {
		if (side != null) {
			nbt.setInteger("tankSide", side.index);
		}
		return super.writeToNBT(nbt, key);
	}

}
