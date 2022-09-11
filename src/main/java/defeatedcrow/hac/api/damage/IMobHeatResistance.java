package defeatedcrow.hac.api.damage;

import defeatedcrow.hac.api.climate.DCHeatTier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

/**
 * Mobの気候耐性を登録するためのインターフェイス
 */
public interface IMobHeatResistance {

	float getHeatResistance(ResourceLocation name, DCHeatTier temp);

	float getHeatResistance(Entity entity, DCHeatTier temp);

	/**
	 * @param name "modID", "registryName"
	 * @param heatResistance (default:2.0F)
	 * @param coldResistance (default:2.0F)
	 *        <br>
	 *        ResourceLocationからクラス名を特定して登録するメソッド
	 */
	void registerEntityResistance(ResourceLocation name, float heatResistance, float coldResistance);

	/**
	 * @param type register target EntityType
	 * @param heatResistance (default:2.0F)
	 * @param coldResistance (default:2.0F)
	 *        <br>
	 *        特定のEntityTypeに対して気候耐性を登録する。<br>
	 *        一致、または継承関係であるかを判定する。
	 * @return
	 */
	void registerEntityResistance(EntityType<?> type, float heatResistance, float coldResistance);

}
