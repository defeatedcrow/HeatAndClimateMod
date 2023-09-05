package defeatedcrow.hac.api.climate;

import net.minecraft.nbt.CompoundTag;

public interface IClimateHelper {

	/**
	 * climateを0bAABBCCCのintとして表現したものと互換性を持たせる。
	 * NBT用。
	 */
	IClimate getClimateFromInt(int i);

	IClimate getClimateFromParam(DCHeatTier heat, DCHumidity hum, DCAirflow air);

	/**
	 * NBTからIClimateに。
	 */
	IClimate getClimateFromNBT(CompoundTag nbt);

	/**
	 * NBTにIClimateをセット。
	 */
	void setClimateToNBT(CompoundTag nbt, IClimate climate);

	String getNBTKey();

	int[] getIDs(int i);

	int getClimateintFromIDs(int[] is);

	IClimate getDefaultClimate();

}