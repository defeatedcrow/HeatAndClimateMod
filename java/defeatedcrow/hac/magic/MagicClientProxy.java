package defeatedcrow.hac.magic;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.JsonRegisterHelper;
import defeatedcrow.hac.magic.block.TileIceCluster;
import defeatedcrow.hac.magic.block.TileInfernalFlame;
import defeatedcrow.hac.magic.client.HealCircleRenderer;
import defeatedcrow.hac.magic.client.MagicCircleRenderer;
import defeatedcrow.hac.magic.client.MagicDaggerRenderer;
import defeatedcrow.hac.magic.client.ProjCircleRenderer;
import defeatedcrow.hac.magic.client.TESRIceCluster;
import defeatedcrow.hac.magic.client.TESRInfernalFlame;
import defeatedcrow.hac.magic.proj.EntityHealBarrier;
import defeatedcrow.hac.magic.proj.EntityMobBarrier;
import defeatedcrow.hac.magic.proj.EntityProjBarrier;
import defeatedcrow.hac.magic.proj.EntityProjChalB;
import defeatedcrow.hac.magic.proj.EntityProjChalR;
import defeatedcrow.hac.magic.proj.EntityProjChalW;
import defeatedcrow.hac.magic.proj.EntityProjClmD;
import defeatedcrow.hac.magic.proj.EntityProjCryC;
import defeatedcrow.hac.magic.proj.EntityProjCryL;
import defeatedcrow.hac.magic.proj.EntityProjCryM;
import defeatedcrow.hac.magic.proj.EntityProjLapC;
import defeatedcrow.hac.magic.proj.EntityProjLapM;
import defeatedcrow.hac.magic.proj.EntityProjLapS;
import defeatedcrow.hac.magic.proj.EntityProjSapB;
import defeatedcrow.hac.magic.proj.EntityProjSapR;
import defeatedcrow.hac.magic.proj.EntityProjSapW;
import defeatedcrow.hac.magic.proj.EntityProjSchB;
import defeatedcrow.hac.magic.proj.EntityProjSchC;
import defeatedcrow.hac.magic.proj.EntityProjSilver;
import defeatedcrow.hac.main.client.ClientMainProxy;

@SideOnly(Side.CLIENT)
public class MagicClientProxy {

	public static void loadEntity() {
		ClientMainProxy.registRender(EntityProjSilver.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjChalW.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjChalB.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjChalR.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjSapW.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjSapB.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjSapR.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjCryM.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjCryC.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjCryL.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjLapC.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjLapS.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjLapM.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjSchB.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjSchC.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjClmD.class, MagicDaggerRenderer.class);

		ClientMainProxy.registRender(EntityMobBarrier.class, MagicCircleRenderer.class);
		ClientMainProxy.registRender(EntityProjBarrier.class, ProjCircleRenderer.class);
		ClientMainProxy.registRender(EntityHealBarrier.class, HealCircleRenderer.class);
	}

	public static void loadTE() {
		ClientRegistry.registerTileEntity(TileIceCluster.class, "dcs_te_cluster_ice", new TESRIceCluster());
		ClientRegistry.registerTileEntity(TileInfernalFlame.class, "dcs_te_infernal_flame", new TESRInfernalFlame());
	}

	public static void regJson(JsonRegisterHelper instance) {
		// item

		instance.regSimpleItem(MagicInit.pendant, ClimateCore.PACKAGE_ID, "dcs_jewel_pendant", "equip", 10);
		instance.regSimpleItem(MagicInit.badge, ClimateCore.PACKAGE_ID, "dcs_jewel_badge", "equip", 10);
		instance.regSimpleItem(MagicInit.daggerSilver, ClimateCore.PACKAGE_ID, "dcs_dagger_silver", "magic", 0);
		instance.regSimpleItem(MagicInit.daggerMagic, ClimateCore.PACKAGE_ID, "dcs_dagger_magic", "magic", 15);

		// block
		instance.regTEBlock(MagicInit.clusterIce, ClimateCore.PACKAGE_ID, "dcs_cluster_ice", "magic", 0);
		instance.regTEBlock(MagicInit.infernalFlame, ClimateCore.PACKAGE_ID, "dcs_infernal_flame", "magic", 0);
	}

}
