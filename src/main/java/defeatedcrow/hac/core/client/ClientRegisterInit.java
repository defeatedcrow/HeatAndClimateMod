package defeatedcrow.hac.core.client;

import defeatedcrow.hac.core.client.entity.EntityModelLoader;
import defeatedcrow.hac.core.client.entity.model.BlockCabinetModel;
import defeatedcrow.hac.core.client.entity.model.BlockChandelier2Model;
import defeatedcrow.hac.core.client.entity.model.BlockChandelier3Model;
import defeatedcrow.hac.core.client.entity.model.BlockChandelierModel;
import defeatedcrow.hac.core.client.entity.model.BlockLockerModel;
import defeatedcrow.hac.core.client.entity.model.BlockLuggageModel;
import defeatedcrow.hac.core.client.entity.model.BlockRoundChairModel;
import defeatedcrow.hac.core.client.entity.model.BlockShelfIronModel;
import defeatedcrow.hac.core.client.entity.model.BlockShelfLabModel;
import defeatedcrow.hac.core.client.entity.model.CanoeModel;
import defeatedcrow.hac.core.client.entity.model.ChairBindModel;
import defeatedcrow.hac.core.client.entity.model.CushionSquareModel;
import defeatedcrow.hac.core.client.entity.model.CutleryModel;
import defeatedcrow.hac.core.client.entity.model.FlowerPotModel;
import defeatedcrow.hac.core.client.entity.model.HammockModel;
import defeatedcrow.hac.core.client.entity.model.ModelMagicFin;
import defeatedcrow.hac.core.client.entity.model.ModelMagicWing;
import defeatedcrow.hac.core.client.entity.model.ModelThinArmor;
import defeatedcrow.hac.core.client.entity.model.VillagerChestModel;
import defeatedcrow.hac.core.client.entity.renderer.RenderCanoe;
import defeatedcrow.hac.core.client.entity.renderer.RenderChair;
import defeatedcrow.hac.core.client.entity.renderer.RenderChopsticks;
import defeatedcrow.hac.core.client.entity.renderer.RenderCushion;
import defeatedcrow.hac.core.client.entity.renderer.RenderFlowerPot;
import defeatedcrow.hac.core.client.entity.renderer.RenderFork;
import defeatedcrow.hac.core.client.entity.renderer.RenderHarpoon;
import defeatedcrow.hac.core.client.entity.renderer.RenderSpoon;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererCabinet;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererChairRound;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererChandelier;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererDisplayCase;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererDisplayDoubleShelf;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererDisplayShelf;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererHammock;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererLocker;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererLuggage;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererToolHook;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererVillagerChest;
import defeatedcrow.hac.core.client.particle.BubbleParticleDC;
import defeatedcrow.hac.core.client.particle.LeakageParticleDC;
import defeatedcrow.hac.core.client.particle.LightOrbDC;
import defeatedcrow.hac.core.client.particle.SimpleParticleDC;
import defeatedcrow.hac.core.client.particle.SmokeParticleDC;
import defeatedcrow.hac.core.client.particle.SparkleParticleDC;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.building.CabinetTile;
import defeatedcrow.hac.core.material.block.building.ChairRoundTile;
import defeatedcrow.hac.core.material.block.building.ChandelierTile;
import defeatedcrow.hac.core.material.block.building.DisplayDoubleShelfTile;
import defeatedcrow.hac.core.material.block.building.LockerTile;
import defeatedcrow.hac.core.material.block.building.LuggageTile;
import defeatedcrow.hac.core.material.block.building.VillagerChestTile;
import defeatedcrow.hac.core.material.item.tool.CanoeItem;
import defeatedcrow.hac.core.material.item.tool.CushionItem;
import defeatedcrow.hac.core.material.item.tool.CutleryChopsticksItem;
import defeatedcrow.hac.core.material.item.tool.CutleryForkItem;
import defeatedcrow.hac.core.material.item.tool.CutlerySpoonItem;
import defeatedcrow.hac.core.material.item.tool.FlowerPotItem;
import defeatedcrow.hac.core.material.item.tool.HarpoonItem;
import defeatedcrow.hac.food.client.entity.RenderBottleBeer;
import defeatedcrow.hac.food.client.entity.RenderBottleWine;
import defeatedcrow.hac.food.client.entity.RenderBreadCream;
import defeatedcrow.hac.food.client.entity.RenderBreadFlat;
import defeatedcrow.hac.food.client.entity.RenderBreadSausage;
import defeatedcrow.hac.food.client.entity.RenderBreadSquare;
import defeatedcrow.hac.food.client.entity.RenderCake;
import defeatedcrow.hac.food.client.entity.RenderCasserole;
import defeatedcrow.hac.food.client.entity.RenderChazuke;
import defeatedcrow.hac.food.client.entity.RenderCrape;
import defeatedcrow.hac.food.client.entity.RenderCurry;
import defeatedcrow.hac.food.client.entity.RenderCurryBase;
import defeatedcrow.hac.food.client.entity.RenderCurryFish;
import defeatedcrow.hac.food.client.entity.RenderCurryMeat;
import defeatedcrow.hac.food.client.entity.RenderCurryRice;
import defeatedcrow.hac.food.client.entity.RenderCurrySashimi;
import defeatedcrow.hac.food.client.entity.RenderDonut;
import defeatedcrow.hac.food.client.entity.RenderDrinkCappuccino;
import defeatedcrow.hac.food.client.entity.RenderDrinkCold;
import defeatedcrow.hac.food.client.entity.RenderDrinkCup;
import defeatedcrow.hac.food.client.entity.RenderDrinkDemitasse;
import defeatedcrow.hac.food.client.entity.RenderDumpling;
import defeatedcrow.hac.food.client.entity.RenderFoodBase;
import defeatedcrow.hac.food.client.entity.RenderFriedEgg;
import defeatedcrow.hac.food.client.entity.RenderFryCroquette;
import defeatedcrow.hac.food.client.entity.RenderFryKaraage;
import defeatedcrow.hac.food.client.entity.RenderFryTonkatsu;
import defeatedcrow.hac.food.client.entity.RenderHotPot;
import defeatedcrow.hac.food.client.entity.RenderIcecream;
import defeatedcrow.hac.food.client.entity.RenderKobachi;
import defeatedcrow.hac.food.client.entity.RenderMinidish;
import defeatedcrow.hac.food.client.entity.RenderOmelet;
import defeatedcrow.hac.food.client.entity.RenderPancake;
import defeatedcrow.hac.food.client.entity.RenderPasta;
import defeatedcrow.hac.food.client.entity.RenderPizza;
import defeatedcrow.hac.food.client.entity.RenderPlateBone;
import defeatedcrow.hac.food.client.entity.RenderPlateChicken;
import defeatedcrow.hac.food.client.entity.RenderPlateFish;
import defeatedcrow.hac.food.client.entity.RenderPlateGarlic;
import defeatedcrow.hac.food.client.entity.RenderPlateLegs;
import defeatedcrow.hac.food.client.entity.RenderPlateMeat;
import defeatedcrow.hac.food.client.entity.RenderPlatePumpkin;
import defeatedcrow.hac.food.client.entity.RenderPlateSteak;
import defeatedcrow.hac.food.client.entity.RenderPlateStuffedVegi;
import defeatedcrow.hac.food.client.entity.RenderQuesadilla;
import defeatedcrow.hac.food.client.entity.RenderRamen;
import defeatedcrow.hac.food.client.entity.RenderRicebowl;
import defeatedcrow.hac.food.client.entity.RenderRicemeal;
import defeatedcrow.hac.food.client.entity.RenderSalad;
import defeatedcrow.hac.food.client.entity.RenderSandwich;
import defeatedcrow.hac.food.client.entity.RenderSaute;
import defeatedcrow.hac.food.client.entity.RenderSoup;
import defeatedcrow.hac.food.client.entity.RenderSquareFish;
import defeatedcrow.hac.food.client.entity.RenderSquareSashimi;
import defeatedcrow.hac.food.client.entity.RenderStew;
import defeatedcrow.hac.food.client.entity.RenderStickBeef;
import defeatedcrow.hac.food.client.entity.RenderStickCorn;
import defeatedcrow.hac.food.client.entity.RenderStickFish;
import defeatedcrow.hac.food.client.entity.RenderStickMeat;
import defeatedcrow.hac.food.client.entity.RenderStickPotato;
import defeatedcrow.hac.food.client.entity.RenderStickVegi;
import defeatedcrow.hac.food.client.entity.RenderSweetpotato;
import defeatedcrow.hac.food.client.entity.RenderTaco;
import defeatedcrow.hac.food.client.entity.RenderTart;
import defeatedcrow.hac.food.client.entity.RenderTempuraFish;
import defeatedcrow.hac.food.client.entity.RenderTempuraIkaten;
import defeatedcrow.hac.food.client.entity.RenderTempuraPrawn;
import defeatedcrow.hac.food.client.entity.RenderTempuraVegi;
import defeatedcrow.hac.food.client.entity.RenderUdon;
import defeatedcrow.hac.food.client.entity.RenderWaBowl;
import defeatedcrow.hac.food.client.entity.RenderWagashi;
import defeatedcrow.hac.food.client.entity.RenderZarusoba;
import defeatedcrow.hac.food.client.model.BottleModel_Beer;
import defeatedcrow.hac.food.client.model.BottleModel_Wine;
import defeatedcrow.hac.food.client.model.BowlSoupModel;
import defeatedcrow.hac.food.client.model.BowlStewModel;
import defeatedcrow.hac.food.client.model.BreadCreamModel;
import defeatedcrow.hac.food.client.model.BreadFlatModel;
import defeatedcrow.hac.food.client.model.BreadRoundModel;
import defeatedcrow.hac.food.client.model.BreadSausageModel;
import defeatedcrow.hac.food.client.model.BreadSquareModel;
import defeatedcrow.hac.food.client.model.CakeModel;
import defeatedcrow.hac.food.client.model.CasseroleModel;
import defeatedcrow.hac.food.client.model.ChazukeModel;
import defeatedcrow.hac.food.client.model.CrapeModel;
import defeatedcrow.hac.food.client.model.DeepfryCroquetteModel;
import defeatedcrow.hac.food.client.model.DeepfryKaraageModel;
import defeatedcrow.hac.food.client.model.DeepfryTonkatsuModel;
import defeatedcrow.hac.food.client.model.DonutJellyModel;
import defeatedcrow.hac.food.client.model.DonutModel;
import defeatedcrow.hac.food.client.model.DrinkCupModel;
import defeatedcrow.hac.food.client.model.DrinkDemitasseModel;
import defeatedcrow.hac.food.client.model.DrinkGlassModel;
import defeatedcrow.hac.food.client.model.DumplingModel;
import defeatedcrow.hac.food.client.model.FriedEggModel;
import defeatedcrow.hac.food.client.model.GaletteModel;
import defeatedcrow.hac.food.client.model.GrilledCrabModel;
import defeatedcrow.hac.food.client.model.HotPotModel;
import defeatedcrow.hac.food.client.model.IcecreamFloatModel;
import defeatedcrow.hac.food.client.model.IcecreamModel;
import defeatedcrow.hac.food.client.model.IcecreamPuddingModel;
import defeatedcrow.hac.food.client.model.IcecreamSandaeModel;
import defeatedcrow.hac.food.client.model.KobachiModel;
import defeatedcrow.hac.food.client.model.LargeBowlModel;
import defeatedcrow.hac.food.client.model.LargeBowlModel_Base;
import defeatedcrow.hac.food.client.model.LargeBowlModel_Fish;
import defeatedcrow.hac.food.client.model.LargeBowlModel_Meat;
import defeatedcrow.hac.food.client.model.LargeBowlModel_Rice;
import defeatedcrow.hac.food.client.model.LargeBowlModel_Sashimi;
import defeatedcrow.hac.food.client.model.MinidishModel;
import defeatedcrow.hac.food.client.model.OmeletModel;
import defeatedcrow.hac.food.client.model.PancakeModel;
import defeatedcrow.hac.food.client.model.PastaModel;
import defeatedcrow.hac.food.client.model.PizzaModel;
import defeatedcrow.hac.food.client.model.PlateBoneMarrowModel;
import defeatedcrow.hac.food.client.model.PlateChickenModel;
import defeatedcrow.hac.food.client.model.PlateFishModel;
import defeatedcrow.hac.food.client.model.PlateGarlicModel;
import defeatedcrow.hac.food.client.model.PlateLegsModel;
import defeatedcrow.hac.food.client.model.PlateMeatModel;
import defeatedcrow.hac.food.client.model.PlatePumpkinModel;
import defeatedcrow.hac.food.client.model.PlateSteakModel;
import defeatedcrow.hac.food.client.model.PlateStuffedVegiModel;
import defeatedcrow.hac.food.client.model.QuesadillaModel;
import defeatedcrow.hac.food.client.model.RamenModel;
import defeatedcrow.hac.food.client.model.RiceModel;
import defeatedcrow.hac.food.client.model.Rice_ChaofanModel;
import defeatedcrow.hac.food.client.model.Rice_HainanChickenModel;
import defeatedcrow.hac.food.client.model.Rice_JambalayaModel;
import defeatedcrow.hac.food.client.model.Rice_JollofModel;
import defeatedcrow.hac.food.client.model.Rice_PaelliaModel;
import defeatedcrow.hac.food.client.model.SaladModel;
import defeatedcrow.hac.food.client.model.SandwichModel;
import defeatedcrow.hac.food.client.model.SausageCurryModel;
import defeatedcrow.hac.food.client.model.SausageModel;
import defeatedcrow.hac.food.client.model.SauteModel;
import defeatedcrow.hac.food.client.model.SobaModel;
import defeatedcrow.hac.food.client.model.SquareFishModel;
import defeatedcrow.hac.food.client.model.SquareSashimiModel;
import defeatedcrow.hac.food.client.model.StickBeefModel;
import defeatedcrow.hac.food.client.model.StickChickenModel;
import defeatedcrow.hac.food.client.model.StickCornModel;
import defeatedcrow.hac.food.client.model.StickFishModel;
import defeatedcrow.hac.food.client.model.StickPotatoModel;
import defeatedcrow.hac.food.client.model.StickVegiModel;
import defeatedcrow.hac.food.client.model.SweetPotatoModel;
import defeatedcrow.hac.food.client.model.TacoModel;
import defeatedcrow.hac.food.client.model.TartModel;
import defeatedcrow.hac.food.client.model.TempuraFishModel;
import defeatedcrow.hac.food.client.model.TempuraIkatenModel;
import defeatedcrow.hac.food.client.model.TempuraPrawnModel;
import defeatedcrow.hac.food.client.model.TempuraVegiModel;
import defeatedcrow.hac.food.client.model.WaBowlModel;
import defeatedcrow.hac.food.client.model.WagashiModel;
import defeatedcrow.hac.food.client.model.ZarusobaModel;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.entity.BottleBeerItem;
import defeatedcrow.hac.food.material.entity.BottleWineItem;
import defeatedcrow.hac.food.material.entity.BreadCreamItem;
import defeatedcrow.hac.food.material.entity.BreadFlatItem;
import defeatedcrow.hac.food.material.entity.BreadRoundItem;
import defeatedcrow.hac.food.material.entity.BreadSausageItem;
import defeatedcrow.hac.food.material.entity.BreadSquareItem;
import defeatedcrow.hac.food.material.entity.CakeItem;
import defeatedcrow.hac.food.material.entity.CasseroleItem;
import defeatedcrow.hac.food.material.entity.ChazukeItem;
import defeatedcrow.hac.food.material.entity.CookedSweetpotatoItem;
import defeatedcrow.hac.food.material.entity.CrapeItem;
import defeatedcrow.hac.food.material.entity.DeepfryCroquetteItem;
import defeatedcrow.hac.food.material.entity.DeepfryKaraageItem;
import defeatedcrow.hac.food.material.entity.DeepfryTonkatsuItem;
import defeatedcrow.hac.food.material.entity.DonutItem;
import defeatedcrow.hac.food.material.entity.DrinkCappuccinoItem;
import defeatedcrow.hac.food.material.entity.DrinkColdItem;
import defeatedcrow.hac.food.material.entity.DrinkCupItem;
import defeatedcrow.hac.food.material.entity.DrinkDemitasseItem;
import defeatedcrow.hac.food.material.entity.DumplingItem;
import defeatedcrow.hac.food.material.entity.FriedEggItem;
import defeatedcrow.hac.food.material.entity.IcecreamItem;
import defeatedcrow.hac.food.material.entity.KobachiItem;
import defeatedcrow.hac.food.material.entity.MinidishItem;
import defeatedcrow.hac.food.material.entity.NoodleItem;
import defeatedcrow.hac.food.material.entity.OmeletItem;
import defeatedcrow.hac.food.material.entity.PancakeItem;
import defeatedcrow.hac.food.material.entity.PastaItem;
import defeatedcrow.hac.food.material.entity.PizzaItem;
import defeatedcrow.hac.food.material.entity.PlateBeefItem;
import defeatedcrow.hac.food.material.entity.PlateBoneMarrowItem;
import defeatedcrow.hac.food.material.entity.PlateChickenItem;
import defeatedcrow.hac.food.material.entity.PlateFishItem;
import defeatedcrow.hac.food.material.entity.PlateGarlicItem;
import defeatedcrow.hac.food.material.entity.PlateLegsItem;
import defeatedcrow.hac.food.material.entity.PlateMeatItem;
import defeatedcrow.hac.food.material.entity.PlatePumpkinItem;
import defeatedcrow.hac.food.material.entity.PlateStuffedVegiItem;
import defeatedcrow.hac.food.material.entity.QuesadillaItem;
import defeatedcrow.hac.food.material.entity.RamenItem;
import defeatedcrow.hac.food.material.entity.RicemealItem;
import defeatedcrow.hac.food.material.entity.SaladItem;
import defeatedcrow.hac.food.material.entity.SandwichItem;
import defeatedcrow.hac.food.material.entity.SauteItem;
import defeatedcrow.hac.food.material.entity.SobaItem;
import defeatedcrow.hac.food.material.entity.SquareFishItem;
import defeatedcrow.hac.food.material.entity.SquareSashimiItem;
import defeatedcrow.hac.food.material.entity.StickBeefItem;
import defeatedcrow.hac.food.material.entity.StickCornItem;
import defeatedcrow.hac.food.material.entity.StickFishItem;
import defeatedcrow.hac.food.material.entity.StickMeatItem;
import defeatedcrow.hac.food.material.entity.StickPotatoItem;
import defeatedcrow.hac.food.material.entity.StickVegiItem;
import defeatedcrow.hac.food.material.entity.TacoItem;
import defeatedcrow.hac.food.material.entity.TartItem;
import defeatedcrow.hac.food.material.entity.TempuraFishItem;
import defeatedcrow.hac.food.material.entity.TempuraIkatenItem;
import defeatedcrow.hac.food.material.entity.TempuraPrawnItem;
import defeatedcrow.hac.food.material.entity.TempuraVegiItem;
import defeatedcrow.hac.food.material.entity.UdonItem;
import defeatedcrow.hac.food.material.entity.WagashiItem;
import defeatedcrow.hac.food.material.entity.ZarusobaItem;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem_Fish;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem_Meat;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem_Rice;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem_Sashimi;
import defeatedcrow.hac.food.material.entity.potfoods.HotPotItem;
import defeatedcrow.hac.food.material.entity.potfoods.PorridgeItem;
import defeatedcrow.hac.food.material.entity.potfoods.RiceBowlItem;
import defeatedcrow.hac.food.material.entity.potfoods.SoupItem;
import defeatedcrow.hac.food.material.entity.potfoods.WaBowlItem;
import defeatedcrow.hac.machine.client.entity.BlockChamberFuelModel;
import defeatedcrow.hac.machine.client.entity.BlockChamberIronModel;
import defeatedcrow.hac.machine.client.entity.CableModel;
import defeatedcrow.hac.machine.client.entity.CoffeeMacchinettaModel;
import defeatedcrow.hac.machine.client.entity.CoffeeSiphonModel;
import defeatedcrow.hac.machine.client.entity.ConveyorVerticalModel;
import defeatedcrow.hac.machine.client.entity.CookingPotModel_A;
import defeatedcrow.hac.machine.client.entity.CookingPotModel_B;
import defeatedcrow.hac.machine.client.entity.CookingPotModel_C;
import defeatedcrow.hac.machine.client.entity.CookingPotModel_FishLayer;
import defeatedcrow.hac.machine.client.entity.CookingPotModel_HotPotLayer;
import defeatedcrow.hac.machine.client.entity.CookingPotModel_Layer;
import defeatedcrow.hac.machine.client.entity.FaucetModel_A;
import defeatedcrow.hac.machine.client.entity.FaucetModel_B;
import defeatedcrow.hac.machine.client.entity.FaucetModel_C;
import defeatedcrow.hac.machine.client.entity.FaucetModel_D;
import defeatedcrow.hac.machine.client.entity.FermentationJarModel_A;
import defeatedcrow.hac.machine.client.entity.FermentationJarModel_B;
import defeatedcrow.hac.machine.client.entity.HydroTurbineModel;
import defeatedcrow.hac.machine.client.entity.IBCModel;
import defeatedcrow.hac.machine.client.entity.IntakeFanModel;
import defeatedcrow.hac.machine.client.entity.KichenBenchLabModel_A;
import defeatedcrow.hac.machine.client.entity.KichenBenchLabModel_B;
import defeatedcrow.hac.machine.client.entity.KichenBenchSUSModel;
import defeatedcrow.hac.machine.client.entity.KichenOvenModel;
import defeatedcrow.hac.machine.client.entity.KichenStoveModel;
import defeatedcrow.hac.machine.client.entity.MonitorPanelModel;
import defeatedcrow.hac.machine.client.entity.PortableCanModel;
import defeatedcrow.hac.machine.client.entity.RollCrusherModel;
import defeatedcrow.hac.machine.client.entity.SinkLabModel;
import defeatedcrow.hac.machine.client.entity.SinkSUSHalfModel;
import defeatedcrow.hac.machine.client.entity.SinkSUSModel;
import defeatedcrow.hac.machine.client.entity.StoneMillModel;
import defeatedcrow.hac.machine.client.entity.StormglassModel;
import defeatedcrow.hac.machine.client.entity.TeaPotModel_A;
import defeatedcrow.hac.machine.client.entity.TeaPotModel_B;
import defeatedcrow.hac.machine.client.entity.TeaPotModel_C;
import defeatedcrow.hac.machine.client.entity.TileRendererAluminumCable;
import defeatedcrow.hac.machine.client.entity.TileRendererChamberFuel;
import defeatedcrow.hac.machine.client.entity.TileRendererChamberIron;
import defeatedcrow.hac.machine.client.entity.TileRendererCoffeeMacchinetta;
import defeatedcrow.hac.machine.client.entity.TileRendererCoffeeSiphon;
import defeatedcrow.hac.machine.client.entity.TileRendererConveyor;
import defeatedcrow.hac.machine.client.entity.TileRendererConveyorSorter;
import defeatedcrow.hac.machine.client.entity.TileRendererConveyorVertical;
import defeatedcrow.hac.machine.client.entity.TileRendererCookingPot;
import defeatedcrow.hac.machine.client.entity.TileRendererCopperCable;
import defeatedcrow.hac.machine.client.entity.TileRendererFaucet;
import defeatedcrow.hac.machine.client.entity.TileRendererFermentationJar;
import defeatedcrow.hac.machine.client.entity.TileRendererHydroTurbine;
import defeatedcrow.hac.machine.client.entity.TileRendererIBC;
import defeatedcrow.hac.machine.client.entity.TileRendererIntakeFan;
import defeatedcrow.hac.machine.client.entity.TileRendererKichenBench;
import defeatedcrow.hac.machine.client.entity.TileRendererKichenOven;
import defeatedcrow.hac.machine.client.entity.TileRendererKichenStove;
import defeatedcrow.hac.machine.client.entity.TileRendererMeterEnergy;
import defeatedcrow.hac.machine.client.entity.TileRendererMeterTemp;
import defeatedcrow.hac.machine.client.entity.TileRendererPipeAlloy;
import defeatedcrow.hac.machine.client.entity.TileRendererPipeNickelsilver;
import defeatedcrow.hac.machine.client.entity.TileRendererPortableCan;
import defeatedcrow.hac.machine.client.entity.TileRendererRollCrusher;
import defeatedcrow.hac.machine.client.entity.TileRendererSink;
import defeatedcrow.hac.machine.client.entity.TileRendererStoneMill;
import defeatedcrow.hac.machine.client.entity.TileRendererStormglass;
import defeatedcrow.hac.machine.client.entity.TileRendererTeaPot;
import defeatedcrow.hac.machine.client.entity.TileRendererWaterBasin;
import defeatedcrow.hac.machine.client.entity.TileRendererWaterPump;
import defeatedcrow.hac.machine.client.entity.WaterPumpModel;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.machine.CookingPotTile;
import defeatedcrow.hac.machine.material.block.machine.FermentationJarTile;
import defeatedcrow.hac.machine.material.block.machine.HydroTurbineTile;
import defeatedcrow.hac.machine.material.block.machine.IntakeFanTile;
import defeatedcrow.hac.machine.material.block.machine.KichenBenchTile;
import defeatedcrow.hac.machine.material.block.machine.KichenOvenTile;
import defeatedcrow.hac.machine.material.block.machine.RollCrusherTile;
import defeatedcrow.hac.machine.material.block.machine.StoneMillTile;
import defeatedcrow.hac.machine.material.block.machine.TeaPotTile;
import defeatedcrow.hac.machine.material.block.transport.FaucetTile;
import defeatedcrow.hac.machine.material.block.transport.FluidSinkTile;
import defeatedcrow.hac.machine.material.block.transport.IBCTile;
import defeatedcrow.hac.machine.material.block.transport.PortableCanTile;
import defeatedcrow.hac.magic.client.entity.RenderBindPlant;
import defeatedcrow.hac.magic.client.entity.RenderColorArrow;
import defeatedcrow.hac.magic.client.entity.RenderCrowTurret;
import defeatedcrow.hac.magic.client.entity.RenderEmpty;
import defeatedcrow.hac.magic.client.entity.RenderLightCauldron;
import defeatedcrow.hac.magic.client.entity.RenderMagicPicture;
import defeatedcrow.hac.magic.client.entity.RenderSilkySmallBomb;
import defeatedcrow.hac.magic.client.model.CrowTurretModel;
import defeatedcrow.hac.magic.client.model.LightCauldronModel;
import defeatedcrow.hac.magic.client.model.MagicPictureModel;
import defeatedcrow.hac.magic.client.model.SilkyBombModel;
import defeatedcrow.hac.magic.material.MagicInit;
import defeatedcrow.hac.magic.material.item.entity.PictureItemBR;
import defeatedcrow.hac.magic.material.item.entity.PictureItemBW;
import defeatedcrow.hac.magic.material.item.entity.PictureItemGB;
import defeatedcrow.hac.magic.material.item.entity.PictureItemGW;
import defeatedcrow.hac.magic.material.item.entity.PictureItemRG;
import defeatedcrow.hac.magic.material.item.entity.PictureItemRU;
import defeatedcrow.hac.magic.material.item.entity.PictureItemUB;
import defeatedcrow.hac.magic.material.item.entity.PictureItemUG;
import defeatedcrow.hac.magic.material.item.entity.PictureItemWR;
import defeatedcrow.hac.magic.material.item.entity.PictureItemWU;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;

public class ClientRegisterInit {

	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		// TESR
		event.registerLayerDefinition(ChandelierTile.CHALCEDONY.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.FLUORITE.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.JET.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.DESERTROSE.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.SERPENTINE.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.IRON.getLayerLocation(), BlockChandelier2Model::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.CANDLE.getLayerLocation(), BlockChandelier3Model::createBodyLayer);

		event.registerLayerDefinition(ChairRoundTile.BLUE.getLayerLocation(), BlockRoundChairModel::createBodyLayer);
		event.registerLayerDefinition(ChairRoundTile.BLACK.getLayerLocation(), BlockRoundChairModel::createBodyLayer);
		event.registerLayerDefinition(ChairRoundTile.RED.getLayerLocation(), BlockRoundChairModel::createBodyLayer);
		event.registerLayerDefinition(ChairRoundTile.GREEN.getLayerLocation(), BlockRoundChairModel::createBodyLayer);
		event.registerLayerDefinition(ChairRoundTile.WHITE.getLayerLocation(), BlockRoundChairModel::createBodyLayer);

		event.registerLayerDefinition(LuggageTile.NORMAL.getLayerLocation(), BlockLuggageModel::createBodyLayer);
		event.registerLayerDefinition(LuggageTile.BLUE.getLayerLocation(), BlockLuggageModel::createBodyLayer);
		event.registerLayerDefinition(LuggageTile.BLACK.getLayerLocation(), BlockLuggageModel::createBodyLayer);
		event.registerLayerDefinition(LuggageTile.RED.getLayerLocation(), BlockLuggageModel::createBodyLayer);
		event.registerLayerDefinition(LuggageTile.GREEN.getLayerLocation(), BlockLuggageModel::createBodyLayer);
		event.registerLayerDefinition(LuggageTile.WHITE.getLayerLocation(), BlockLuggageModel::createBodyLayer);

		event.registerLayerDefinition(CabinetTile.NORMAL.getLayerLocation(), BlockCabinetModel::createBodyLayer);
		event.registerLayerDefinition(CabinetTile.BLUE.getLayerLocation(), BlockCabinetModel::createBodyLayer);
		event.registerLayerDefinition(CabinetTile.BLACK.getLayerLocation(), BlockCabinetModel::createBodyLayer);
		event.registerLayerDefinition(CabinetTile.RED.getLayerLocation(), BlockCabinetModel::createBodyLayer);
		event.registerLayerDefinition(CabinetTile.GREEN.getLayerLocation(), BlockCabinetModel::createBodyLayer);
		event.registerLayerDefinition(CabinetTile.WHITE.getLayerLocation(), BlockCabinetModel::createBodyLayer);

		event.registerLayerDefinition(LockerTile.NORMAL.getLayerLocation(), BlockLockerModel::createBodyLayer);
		event.registerLayerDefinition(LockerTile.BLUE.getLayerLocation(), BlockLockerModel::createBodyLayer);
		event.registerLayerDefinition(LockerTile.BLACK.getLayerLocation(), BlockLockerModel::createBodyLayer);
		event.registerLayerDefinition(LockerTile.RED.getLayerLocation(), BlockLockerModel::createBodyLayer);
		event.registerLayerDefinition(LockerTile.GREEN.getLayerLocation(), BlockLockerModel::createBodyLayer);
		event.registerLayerDefinition(LockerTile.WHITE.getLayerLocation(), BlockLockerModel::createBodyLayer);

		event.registerLayerDefinition(TileRendererHammock.TEX.getLayerLocation(), HammockModel::createBodyLayer);

		event.registerLayerDefinition(DisplayDoubleShelfTile.IRON.getLayerLocation(), BlockShelfIronModel::createBodyLayer);
		event.registerLayerDefinition(DisplayDoubleShelfTile.LAB.getLayerLocation(), BlockShelfLabModel::createBodyLayer);
		event.registerLayerDefinition(DisplayDoubleShelfTile.GLASS.getLayerLocation(), BlockShelfLabModel::createBodyLayer);
		event.registerLayerDefinition(DisplayDoubleShelfTile.WOOD.getLayerLocation(), BlockShelfLabModel::createBodyLayer);

		event.registerLayerDefinition(VillagerChestTile.NORMAL.getLayerLocation(), VillagerChestModel::createBodyLayer);

		event.registerLayerDefinition(TileRendererChamberIron.DATA.getLayerLocation(), BlockChamberIronModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererChamberFuel.DATA.getLayerLocation(), BlockChamberFuelModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererKichenStove.DATA.getLayerLocation(), KichenStoveModel::createBodyLayer);

		event.registerLayerDefinition(KichenOvenTile.WOOD_OFF.getLayerLocation(), KichenOvenModel::createBodyLayer);
		event.registerLayerDefinition(KichenOvenTile.WOOD_ON.getLayerLocation(), KichenOvenModel::createBodyLayer);
		event.registerLayerDefinition(KichenOvenTile.BLACK_OFF.getLayerLocation(), KichenOvenModel::createBodyLayer);
		event.registerLayerDefinition(KichenOvenTile.BLACK_ON.getLayerLocation(), KichenOvenModel::createBodyLayer);
		event.registerLayerDefinition(KichenOvenTile.LAB_OFF.getLayerLocation(), KichenOvenModel::createBodyLayer);
		event.registerLayerDefinition(KichenOvenTile.LAB_ON.getLayerLocation(), KichenOvenModel::createBodyLayer);

		event.registerLayerDefinition(KichenBenchTile.WOOD_A.getLayerLocation(), KichenBenchLabModel_A::createBodyLayer);
		event.registerLayerDefinition(KichenBenchTile.WOOD_B.getLayerLocation(), KichenBenchLabModel_B::createBodyLayer);
		event.registerLayerDefinition(KichenBenchTile.BLACK_A.getLayerLocation(), KichenBenchLabModel_A::createBodyLayer);
		event.registerLayerDefinition(KichenBenchTile.BLACK_B.getLayerLocation(), KichenBenchLabModel_B::createBodyLayer);
		event.registerLayerDefinition(KichenBenchTile.LAB_A.getLayerLocation(), KichenBenchLabModel_A::createBodyLayer);
		event.registerLayerDefinition(KichenBenchTile.LAB_B.getLayerLocation(), KichenBenchLabModel_B::createBodyLayer);
		event.registerLayerDefinition(KichenBenchTile.SUS.getLayerLocation(), KichenBenchSUSModel::createBodyLayer);

		event.registerLayerDefinition(FluidSinkTile.LAB.getLayerLocation(), SinkLabModel::createBodyLayer);
		event.registerLayerDefinition(FluidSinkTile.SUS.getLayerLocation(), SinkSUSModel::createBodyLayer);
		event.registerLayerDefinition(FluidSinkTile.SUS_HALF.getLayerLocation(), SinkSUSHalfModel::createBodyLayer);

		event.registerLayerDefinition(TileRendererConveyorVertical.DATA.getLayerLocation(), ConveyorVerticalModel::createBodyLayer);

		event.registerLayerDefinition(PortableCanTile.NORMAL.getLayerLocation(), PortableCanModel::createBodyLayer);
		event.registerLayerDefinition(PortableCanTile.WHITE.getLayerLocation(), PortableCanModel::createBodyLayer);
		event.registerLayerDefinition(PortableCanTile.BLUE.getLayerLocation(), PortableCanModel::createBodyLayer);
		event.registerLayerDefinition(PortableCanTile.BLACK.getLayerLocation(), PortableCanModel::createBodyLayer);
		event.registerLayerDefinition(PortableCanTile.RED.getLayerLocation(), PortableCanModel::createBodyLayer);
		event.registerLayerDefinition(PortableCanTile.GREEN.getLayerLocation(), PortableCanModel::createBodyLayer);

		event.registerLayerDefinition(IBCTile.NORMAL.getLayerLocation(), IBCModel::createBodyLayer);
		event.registerLayerDefinition(IBCTile.INNER.getLayerLocation(), IBCModel::createBodyLayer);

		event.registerLayerDefinition(FaucetTile.TYPE_A.getLayerLocation(), FaucetModel_A::createBodyLayer);
		event.registerLayerDefinition(FaucetTile.TYPE_B.getLayerLocation(), FaucetModel_B::createBodyLayer);
		event.registerLayerDefinition(FaucetTile.TYPE_C.getLayerLocation(), FaucetModel_C::createBodyLayer);
		event.registerLayerDefinition(FaucetTile.TYPE_D.getLayerLocation(), FaucetModel_D::createBodyLayer);

		event.registerLayerDefinition(TileRendererStormglass.TEX.getLayerLocation(), StormglassModel::createBodyLayer);

		event.registerLayerDefinition(CookingPotTile.NORMAL.getLayerLocation(), CookingPotModel_A::createBodyLayer);
		event.registerLayerDefinition(CookingPotTile.WHITE.getLayerLocation(), CookingPotModel_A::createBodyLayer);
		event.registerLayerDefinition(CookingPotTile.BLUE.getLayerLocation(), CookingPotModel_B::createBodyLayer);
		event.registerLayerDefinition(CookingPotTile.BLACK.getLayerLocation(), CookingPotModel_C::createBodyLayer);
		event.registerLayerDefinition(CookingPotTile.RED.getLayerLocation(), CookingPotModel_A::createBodyLayer);
		event.registerLayerDefinition(CookingPotTile.GREEN.getLayerLocation(), CookingPotModel_B::createBodyLayer);

		event.registerLayerDefinition(TeaPotTile.NORMAL.getLayerLocation(), TeaPotModel_A::createBodyLayer);
		event.registerLayerDefinition(TeaPotTile.WHITE.getLayerLocation(), TeaPotModel_C::createBodyLayer);
		event.registerLayerDefinition(TeaPotTile.BLUE.getLayerLocation(), TeaPotModel_B::createBodyLayer);
		event.registerLayerDefinition(TeaPotTile.BLACK.getLayerLocation(), TeaPotModel_A::createBodyLayer);
		event.registerLayerDefinition(TeaPotTile.RED.getLayerLocation(), TeaPotModel_B::createBodyLayer);
		event.registerLayerDefinition(TeaPotTile.GREEN.getLayerLocation(), TeaPotModel_B::createBodyLayer);

		event.registerLayerDefinition(TileRendererCoffeeSiphon.NORMAL.getLayerLocation(), CoffeeSiphonModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCoffeeMacchinetta.NORMAL.getLayerLocation(), CoffeeMacchinettaModel::createBodyLayer);

		event.registerLayerDefinition(FermentationJarTile.NORMAL.getLayerLocation(), FermentationJarModel_B::createBodyLayer);
		event.registerLayerDefinition(FermentationJarTile.WHITE.getLayerLocation(), FermentationJarModel_B::createBodyLayer);
		event.registerLayerDefinition(FermentationJarTile.BLUE.getLayerLocation(), FermentationJarModel_A::createBodyLayer);
		event.registerLayerDefinition(FermentationJarTile.BLACK.getLayerLocation(), FermentationJarModel_B::createBodyLayer);
		event.registerLayerDefinition(FermentationJarTile.RED.getLayerLocation(), FermentationJarModel_A::createBodyLayer);
		event.registerLayerDefinition(FermentationJarTile.GREEN.getLayerLocation(), FermentationJarModel_A::createBodyLayer);

		event.registerLayerDefinition(StoneMillTile.NORMAL.getLayerLocation(), StoneMillModel::createBodyLayer);

		event.registerLayerDefinition(RollCrusherTile.NORMAL.getLayerLocation(), RollCrusherModel::createBodyLayer);

		event.registerLayerDefinition(TileRendererMeterTemp.NORMAL.getLayerLocation(), MonitorPanelModel::createBodyLayer);

		event.registerLayerDefinition(TileRendererCopperCable.COPPER.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_INPUT.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_OUTLET.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_POWERED.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_COATED.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_COATED_INPUT.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_COATED_OUTLET.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_COATED_POWERED.getLayerLocation(), CableModel::createBodyLayer);

		event.registerLayerDefinition(TileRendererAluminumCable.ALUMINUM_COATED.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererAluminumCable.ALUMINUM_COATED_INPUT.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererAluminumCable.ALUMINUM_COATED_OUTLET.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererAluminumCable.ALUMINUM_COATED_POWERED.getLayerLocation(), CableModel::createBodyLayer);

		event.registerLayerDefinition(TileRendererPipeAlloy.BRASS.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererPipeAlloy.BRASS_INPUT.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererPipeAlloy.BRASS_OUTLET.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererPipeNickelsilver.NICKELSILVER.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererPipeNickelsilver.NICKELSILVER_INPUT.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererPipeNickelsilver.NICKELSILVER_OUTLET.getLayerLocation(), CableModel::createBodyLayer);

		event.registerLayerDefinition(HydroTurbineTile.NORMAL.getLayerLocation(), HydroTurbineModel::createBodyLayer);

		event.registerLayerDefinition(TileRendererWaterPump.TEX.getLayerLocation(), WaterPumpModel::createBodyLayer);
		event.registerLayerDefinition(IntakeFanTile.NORMAL.getLayerLocation(), IntakeFanModel::createBodyLayer);

		// Entity
		event.registerLayerDefinition(HarpoonItem.FLINT.getLayerLocation(), TridentModel::createLayer);
		event.registerLayerDefinition(HarpoonItem.STEEL.getLayerLocation(), TridentModel::createLayer);

		event.registerLayerDefinition(CutleryChopsticksItem.CHOPSTICKS.getLayerLocation(), CutleryModel::createLayer);
		event.registerLayerDefinition(CutlerySpoonItem.SPOON.getLayerLocation(), CutleryModel::createLayer);
		event.registerLayerDefinition(CutleryForkItem.FORK.getLayerLocation(), CutleryModel::createLayer);

		event.registerLayerDefinition(CushionItem.LINEN.getLayerLocation(), CushionSquareModel::createBodyLayer);
		event.registerLayerDefinition(CushionItem.WHITE.getLayerLocation(), CushionSquareModel::createBodyLayer);
		event.registerLayerDefinition(CushionItem.BLUE.getLayerLocation(), CushionSquareModel::createBodyLayer);
		event.registerLayerDefinition(CushionItem.BLACK.getLayerLocation(), CushionSquareModel::createBodyLayer);
		event.registerLayerDefinition(CushionItem.RED.getLayerLocation(), CushionSquareModel::createBodyLayer);
		event.registerLayerDefinition(CushionItem.GREEN.getLayerLocation(), CushionSquareModel::createBodyLayer);

		event.registerLayerDefinition(FlowerPotItem.WHITE.getLayerLocation(), FlowerPotModel::createBodyLayer);
		event.registerLayerDefinition(FlowerPotItem.CLAY.getLayerLocation(), FlowerPotModel::createBodyLayer);

		event.registerLayerDefinition(CanoeItem.KUKUI.getLayerLocation(), CanoeModel::createCanoeLayer);

		event.registerLayerDefinition(EntityModelLoader.BOOTS.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.45F)));
		event.registerLayerDefinition(EntityModelLoader.LEGGINS.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.4F)));
		event.registerLayerDefinition(EntityModelLoader.LEGGINS_TIGHT.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.33F)));
		event.registerLayerDefinition(EntityModelLoader.SHIRT.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.35F)));
		event.registerLayerDefinition(EntityModelLoader.JACKET.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.5F)));
		event.registerLayerDefinition(EntityModelLoader.BLOUSE.getLayerLocation(), ModelThinArmor::createBlouseMesh);
		event.registerLayerDefinition(EntityModelLoader.OVERSUITS.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.5F)));
		event.registerLayerDefinition(EntityModelLoader.SUITS.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.35F)));
		event.registerLayerDefinition(EntityModelLoader.HAT.getLayerLocation(), ModelThinArmor::createHatMesh);
		event.registerLayerDefinition(EntityModelLoader.HAIR.getLayerLocation(), ModelThinArmor::createHairMesh);
		event.registerLayerDefinition(EntityModelLoader.HAIR2.getLayerLocation(), ModelThinArmor::createHair2Mesh);
		event.registerLayerDefinition(EntityModelLoader.LONG.getLayerLocation(), ModelThinArmor::createLongMesh);
		event.registerLayerDefinition(EntityModelLoader.SKIRT.getLayerLocation(), ModelThinArmor::createSkirtMesh);
		event.registerLayerDefinition(EntityModelLoader.TUNIC.getLayerLocation(), ModelThinArmor::createTunicMesh);
		event.registerLayerDefinition(EntityModelLoader.WING.getLayerLocation(), ModelMagicWing::createWingMesh);
		event.registerLayerDefinition(EntityModelLoader.FIN.getLayerLocation(), ModelMagicFin::createFinMesh);

		event.registerLayerDefinition(RenderBindPlant.PLANT.getLayerLocation(), ChairBindModel::createBodyLayer);
		event.registerLayerDefinition(RenderLightCauldron.TEX.getLayerLocation(), LightCauldronModel::createBodyLayer);
		event.registerLayerDefinition(RenderCrowTurret.TEX.getLayerLocation(), CrowTurretModel::createBodyLayer);
		event.registerLayerDefinition(RenderSilkySmallBomb.TEX.getLayerLocation(), SilkyBombModel::createBodyLayer);
		event.registerLayerDefinition(PictureItemWU.PICTURE.getLayerLocation(), MagicPictureModel::createBodyLayer);
		event.registerLayerDefinition(PictureItemWR.PICTURE.getLayerLocation(), MagicPictureModel::createBodyLayer);
		event.registerLayerDefinition(PictureItemUB.PICTURE.getLayerLocation(), MagicPictureModel::createBodyLayer);
		event.registerLayerDefinition(PictureItemUG.PICTURE.getLayerLocation(), MagicPictureModel::createBodyLayer);
		event.registerLayerDefinition(PictureItemBR.PICTURE.getLayerLocation(), MagicPictureModel::createBodyLayer);
		event.registerLayerDefinition(PictureItemBW.PICTURE.getLayerLocation(), MagicPictureModel::createBodyLayer);
		event.registerLayerDefinition(PictureItemRG.PICTURE.getLayerLocation(), MagicPictureModel::createBodyLayer);
		event.registerLayerDefinition(PictureItemRU.PICTURE.getLayerLocation(), MagicPictureModel::createBodyLayer);
		event.registerLayerDefinition(PictureItemGW.PICTURE.getLayerLocation(), MagicPictureModel::createBodyLayer);
		event.registerLayerDefinition(PictureItemGB.PICTURE.getLayerLocation(), MagicPictureModel::createBodyLayer);

		event.registerLayerDefinition(BreadRoundItem.BREAD_ROUND_RAW.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_ROUND_BAKED.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_NUTS_RAW.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_NUTS_BAKED.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_CINNAMON_RAW.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_CINNAMON_BAKED.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_ANKO_RAW.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_ANKO_BAKED.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadSquareItem.BREAD_SQUARE_RAW.getLayerLocation(), BreadSquareModel::createBodyLayer);
		event.registerLayerDefinition(BreadSquareItem.BREAD_SQUARE_BAKED.getLayerLocation(), BreadSquareModel::createBodyLayer);
		event.registerLayerDefinition(BreadCreamItem.BREAD_CREAM_RAW.getLayerLocation(), BreadCreamModel::createBodyLayer);
		event.registerLayerDefinition(BreadCreamItem.BREAD_CREAM_BAKED.getLayerLocation(), BreadCreamModel::createBodyLayer);
		event.registerLayerDefinition(BreadSausageItem.BREAD_SAUSAGE_RAW.getLayerLocation(), BreadSausageModel::createBodyLayer);
		event.registerLayerDefinition(BreadSausageItem.BREAD_SAUSAGE_BAKED.getLayerLocation(), BreadSausageModel::createBodyLayer);
		event.registerLayerDefinition(BreadFlatItem.BREAD_FLAT_RAW.getLayerLocation(), BreadFlatModel::createBodyLayer);
		event.registerLayerDefinition(BreadFlatItem.BREAD_FLAT_BAKED.getLayerLocation(), BreadFlatModel::createBodyLayer);
		event.registerLayerDefinition(BreadFlatItem.BREAD_TORTILLA_RAW.getLayerLocation(), BreadFlatModel::createBodyLayer);
		event.registerLayerDefinition(BreadFlatItem.BREAD_TORTILLA_BAKED.getLayerLocation(), BreadFlatModel::createBodyLayer);
		event.registerLayerDefinition(BreadFlatItem.BREAD_CRAPE_BAKED.getLayerLocation(), BreadFlatModel::createBodyLayer);

		event.registerLayerDefinition(SandwichItem.FRUIT.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.MARMALADE.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.EGG.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.SALAD.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.SALMON.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.BLT.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.REUBEN.getLayerLocation(), SandwichModel::createBodyLayer);

		event.registerLayerDefinition(TacoItem.TACO_MEAT.getLayerLocation(), TacoModel::createBodyLayer);
		event.registerLayerDefinition(TacoItem.TACO_AVOCADO.getLayerLocation(), TacoModel::createBodyLayer);
		event.registerLayerDefinition(TacoItem.TACO_FISH.getLayerLocation(), TacoModel::createBodyLayer);
		event.registerLayerDefinition(QuesadillaItem.QUESADILLA.getLayerLocation(), QuesadillaModel::createBodyLayer);

		event.registerLayerDefinition(PizzaItem.PIZZA_TOMATO_RAW.getLayerLocation(), PizzaModel::createBodyLayer);
		event.registerLayerDefinition(PizzaItem.PIZZA_TOMATO_BAKED.getLayerLocation(), PizzaModel::createBodyLayer);
		event.registerLayerDefinition(PizzaItem.PIZZA_MARGHERITA_RAW.getLayerLocation(), PizzaModel::createBodyLayer);
		event.registerLayerDefinition(PizzaItem.PIZZA_MARGHERITA_BAKED.getLayerLocation(), PizzaModel::createBodyLayer);
		event.registerLayerDefinition(PizzaItem.PIZZA_ROMANA_RAW.getLayerLocation(), PizzaModel::createBodyLayer);
		event.registerLayerDefinition(PizzaItem.PIZZA_ROMANA_BAKED.getLayerLocation(), PizzaModel::createBodyLayer);
		event.registerLayerDefinition(PizzaItem.PIZZA_TONNO_RAW.getLayerLocation(), PizzaModel::createBodyLayer);
		event.registerLayerDefinition(PizzaItem.PIZZA_TONNO_BAKED.getLayerLocation(), PizzaModel::createBodyLayer);
		event.registerLayerDefinition(PizzaItem.PIZZA_SAUSAGE_RAW.getLayerLocation(), PizzaModel::createBodyLayer);
		event.registerLayerDefinition(PizzaItem.PIZZA_SAUSAGE_BAKED.getLayerLocation(), PizzaModel::createBodyLayer);
		event.registerLayerDefinition(PizzaItem.PIZZA_MENTAI_RAW.getLayerLocation(), PizzaModel::createBodyLayer);
		event.registerLayerDefinition(PizzaItem.PIZZA_MENTAI_BAKED.getLayerLocation(), PizzaModel::createBodyLayer);

		event.registerLayerDefinition(TartItem.TART_APPLE_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_APPLE_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_PEACH_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_PEACH_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_BERRY_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_BERRY_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_LEMON_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_LEMON_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_COCOA_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_COCOA_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_PISTACHIO_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_PISTACHIO_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_PISTACHIO_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_PISTACHIO_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_QUICHE_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_QUICHE_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_QUICHE_POTATO_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_QUICHE_POTATO_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_QUICHE_COD_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_QUICHE_COD_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_QUICHE_SALMON_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_QUICHE_SALMON_BAKED.getLayerLocation(), TartModel::createBodyLayer);

		event.registerLayerDefinition(StickBeefItem.BEEF_RAW.getLayerLocation(), StickBeefModel::createBodyLayer);
		event.registerLayerDefinition(StickBeefItem.BEEF_COOKED.getLayerLocation(), StickBeefModel::createBodyLayer);
		event.registerLayerDefinition(StickBeefItem.PORK_RAW.getLayerLocation(), StickBeefModel::createBodyLayer);
		event.registerLayerDefinition(StickBeefItem.PORK_COOKED.getLayerLocation(), StickBeefModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.MUTTON_RAW.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.MUTTON_COOKED.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.CHICKEN_RAW.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.CHICKEN_COOKED.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.OFFAL_RAW.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.OFFAL_COOKED.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickFishItem.FISH_RAW.getLayerLocation(), StickFishModel::createBodyLayer);
		event.registerLayerDefinition(StickFishItem.FISH_COOKED.getLayerLocation(), StickFishModel::createBodyLayer);
		event.registerLayerDefinition(StickVegiItem.VEGI_RAW.getLayerLocation(), StickVegiModel::createBodyLayer);
		event.registerLayerDefinition(StickVegiItem.VEGI_COOKED.getLayerLocation(), StickVegiModel::createBodyLayer);
		event.registerLayerDefinition(StickCornItem.CORN_RAW.getLayerLocation(), StickCornModel::createBodyLayer);
		event.registerLayerDefinition(StickCornItem.CORN_COOKED.getLayerLocation(), StickCornModel::createBodyLayer);
		event.registerLayerDefinition(StickPotatoItem.POTATO_RAW.getLayerLocation(), StickPotatoModel::createBodyLayer);
		event.registerLayerDefinition(StickPotatoItem.POTATO_COOKED.getLayerLocation(), StickPotatoModel::createBodyLayer);
		event.registerLayerDefinition(StickPotatoItem.EGGPLANT_RAW.getLayerLocation(), StickPotatoModel::createBodyLayer);
		event.registerLayerDefinition(StickPotatoItem.EGGPLANT_COOKED.getLayerLocation(), StickPotatoModel::createBodyLayer);
		event.registerLayerDefinition(StickPotatoItem.LETTUCE_RAW.getLayerLocation(), StickPotatoModel::createBodyLayer);
		event.registerLayerDefinition(StickPotatoItem.LETTUCE_COOKED.getLayerLocation(), StickPotatoModel::createBodyLayer);

		event.registerLayerDefinition(CookedSweetpotatoItem.RAW.getLayerLocation(), SweetPotatoModel::createBodyLayer);
		event.registerLayerDefinition(CookedSweetpotatoItem.COOKED.getLayerLocation(), SweetPotatoModel::createBodyLayer);

		event.registerLayerDefinition(PlateBeefItem.BEEF_RAW.getLayerLocation(), PlateSteakModel::createBodyLayer);
		event.registerLayerDefinition(PlateBeefItem.BEEF_COOKED.getLayerLocation(), PlateSteakModel::createBodyLayer);
		event.registerLayerDefinition(PlateMeatItem.MEAT_RAW.getLayerLocation(), PlateMeatModel::createBodyLayer);
		event.registerLayerDefinition(PlateMeatItem.MEAT_COOKED.getLayerLocation(), PlateMeatModel::createBodyLayer);
		event.registerLayerDefinition(PlateLegsItem.LEGS_RAW.getLayerLocation(), PlateLegsModel::createBodyLayer);
		event.registerLayerDefinition(PlateLegsItem.LEGS_COOKED.getLayerLocation(), PlateLegsModel::createBodyLayer);
		event.registerLayerDefinition(PlateGarlicItem.GARLIC_RAW.getLayerLocation(), PlateGarlicModel::createBodyLayer);
		event.registerLayerDefinition(PlateGarlicItem.GARLIC_COOKED.getLayerLocation(), PlateGarlicModel::createBodyLayer);
		event.registerLayerDefinition(PlateChickenItem.CHICKEN_BIG_RAW.getLayerLocation(), PlateChickenModel::createBodyLayer);
		event.registerLayerDefinition(PlateChickenItem.CHICKEN_BIG_COOKED.getLayerLocation(), PlateChickenModel::createBodyLayer);
		event.registerLayerDefinition(PlateFishItem.FISH_RAW.getLayerLocation(), PlateFishModel::createBodyLayer);
		event.registerLayerDefinition(PlateFishItem.FISH_COOKED.getLayerLocation(), PlateFishModel::createBodyLayer);
		event.registerLayerDefinition(PlateBoneMarrowItem.BONE_MARROW_RAW.getLayerLocation(), PlateBoneMarrowModel::createBodyLayer);
		event.registerLayerDefinition(PlateBoneMarrowItem.BONE_MARROW_COOKED.getLayerLocation(), PlateBoneMarrowModel::createBodyLayer);
		event.registerLayerDefinition(PlateStuffedVegiItem.STUFFED_BELL_RAW.getLayerLocation(), PlateStuffedVegiModel::createBodyLayer);
		event.registerLayerDefinition(PlateStuffedVegiItem.STUFFED_BELL_COOKED.getLayerLocation(), PlateStuffedVegiModel::createBodyLayer);
		event.registerLayerDefinition(PlateStuffedVegiItem.STUFFED_PAPRIKA_RAW.getLayerLocation(), PlateStuffedVegiModel::createBodyLayer);
		event.registerLayerDefinition(PlateStuffedVegiItem.STUFFED_PAPRIKA_COOKED.getLayerLocation(), PlateStuffedVegiModel::createBodyLayer);
		event.registerLayerDefinition(PlateStuffedVegiItem.POTATO_RAW.getLayerLocation(), PlateStuffedVegiModel::createBodyLayer);
		event.registerLayerDefinition(PlateStuffedVegiItem.POTATO_COOKED.getLayerLocation(), PlateStuffedVegiModel::createBodyLayer);
		event.registerLayerDefinition(PlatePumpkinItem.PUMPKIN_RAW.getLayerLocation(), PlatePumpkinModel::createBodyLayer);
		event.registerLayerDefinition(PlatePumpkinItem.PUMPKIN_COOKED.getLayerLocation(), PlatePumpkinModel::createBodyLayer);

		event.registerLayerDefinition(SaladItem.GREEN.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.POTATO.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.NUTS.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.MELON.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.CITRUS.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.TOFU.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.SALMON.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.SIMPLE.getLayerLocation(), SaladModel::createBodyLayer);

		event.registerLayerDefinition(CasseroleItem.CASSEROLE_GRATIN_SHRIMP_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_GRATIN_SHRIMP_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_GRATIN_ONION_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_GRATIN_ONION_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_SHEPHERDS_PIE_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_SHEPHERDS_PIE_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_DORIA_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_DORIA_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_JANSSONS_FRESTELESE_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_JANSSONS_FRESTELESE_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_MOUSSAKA_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_MOUSSAKA_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_PARMIGIANA_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_PARMIGIANA_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_STUFFING_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_STUFFING_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);

		event.registerLayerDefinition(PorridgeItem.PORRIDGE.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_MILK.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.MUESLI.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_SAFFRON.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_SQUID.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_BORSCH.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CONSOMME_VEGETABLE.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CREAM_MUSHROOM.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CREAM_SALMON.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CREAM_SHRIMP.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CULLEN_SKINK.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_KHARCHO.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_ERWTEN.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_LAMPREDOTTO.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_OFFAL.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_TOMYUMGOONG.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_BAKKUTTEH.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_GOULASH.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_PALMNUTS.getLayerLocation(), BowlStewModel::createBodyLayer);

		event.registerLayerDefinition(WaBowlItem.MISO_TOFU.getLayerLocation(), WaBowlModel::createBodyLayer);
		event.registerLayerDefinition(WaBowlItem.MISO_AGE.getLayerLocation(), WaBowlModel::createBodyLayer);
		event.registerLayerDefinition(WaBowlItem.MISO_EGGPLANT.getLayerLocation(), WaBowlModel::createBodyLayer);
		event.registerLayerDefinition(WaBowlItem.MISO_MUSHROOM.getLayerLocation(), WaBowlModel::createBodyLayer);
		event.registerLayerDefinition(WaBowlItem.MISO_PORK.getLayerLocation(), WaBowlModel::createBodyLayer);
		event.registerLayerDefinition(WaBowlItem.MISO_CARP.getLayerLocation(), WaBowlModel::createBodyLayer);
		event.registerLayerDefinition(WaBowlItem.SHIRUKO.getLayerLocation(), WaBowlModel::createBodyLayer);

		event.registerLayerDefinition(SoupItem.SOUP_CREAM_POTATO.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_PUMPKIN.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_CORN.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_SPINACH.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_PARSNIP.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_SHRIMP.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CHINESE_EGG.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CHINESE_CRAB.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_JUTE.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_GASPACHO.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_TARATOR.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_MINESTRONE.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CHILIBEANS.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_SORREL.getLayerLocation(), BowlSoupModel::createBodyLayer);

		event.registerLayerDefinition(HotPotItem.TOFU.getLayerLocation(), HotPotModel::createBodyLayer);
		event.registerLayerDefinition(HotPotItem.CHICKEN.getLayerLocation(), HotPotModel::createBodyLayer);
		event.registerLayerDefinition(HotPotItem.CRAB.getLayerLocation(), HotPotModel::createBodyLayer);
		event.registerLayerDefinition(HotPotItem.MALATANG.getLayerLocation(), HotPotModel::createBodyLayer);

		event.registerLayerDefinition(KobachiItem.PUMPKIN.getLayerLocation(), KobachiModel::createMainLayer);
		event.registerLayerDefinition(KobachiItem.TARO.getLayerLocation(), KobachiModel::createMainLayer);
		event.registerLayerDefinition(KobachiItem.TARO_SQUID.getLayerLocation(), KobachiModel::createMainLayer);
		event.registerLayerDefinition(KobachiItem.RADISH_SQUID.getLayerLocation(), KobachiModel::createMainLayer);
		event.registerLayerDefinition(KobachiItem.BAMBOO.getLayerLocation(), KobachiModel::createMainLayer);
		event.registerLayerDefinition(KobachiItem.POTATO.getLayerLocation(), KobachiModel::createMainLayer);
		event.registerLayerDefinition(KobachiItem.TIKUZEN.getLayerLocation(), KobachiModel::createMainLayer);
		event.registerLayerDefinition(KobachiItem.CHICKEN.getLayerLocation(), KobachiModel::createChickenLayer);
		event.registerLayerDefinition(KobachiItem.PORK.getLayerLocation(), KobachiModel::createMainLayer);
		event.registerLayerDefinition(KobachiItem.FLOWER.getLayerLocation(), KobachiModel::createMainLayer);
		event.registerLayerDefinition(KobachiItem.AGE.getLayerLocation(), KobachiModel::createMainLayer);
		event.registerLayerDefinition(KobachiItem.NAMASU.getLayerLocation(), KobachiModel::createMainLayer);
		event.registerLayerDefinition(KobachiItem.WHITE.getLayerLocation(), KobachiModel::createMainLayer);
		event.registerLayerDefinition(KobachiItem.YAKKO.getLayerLocation(), KobachiModel::createMainLayer);
		event.registerLayerDefinition(KobachiItem.NAMEROU.getLayerLocation(), KobachiModel::createPasteLayer);
		event.registerLayerDefinition(KobachiItem.TUNA_AVOCADO.getLayerLocation(), KobachiModel::createTunaLayer);
		event.registerLayerDefinition(KobachiItem.SKIPJACK_AVOCADO.getLayerLocation(), KobachiModel::createTunaLayer);
		event.registerLayerDefinition(KobachiItem.POISSON_CRU.getLayerLocation(), KobachiModel::createTunaLayer);

		event.registerLayerDefinition(MinidishItem.SAUERKRAUT.getLayerLocation(), MinidishModel::createLeavesLayer);
		event.registerLayerDefinition(MinidishItem.TSUKEMONO.getLayerLocation(), MinidishModel::createMainLayer);
		event.registerLayerDefinition(MinidishItem.KIMCHI.getLayerLocation(), MinidishModel::createLeavesLayer);
		event.registerLayerDefinition(MinidishItem.UMEBOSHI.getLayerLocation(), MinidishModel::createUmeLayer);

		event.registerLayerDefinition(FriedEggItem.FRIED_EGG.getLayerLocation(), FriedEggModel::createEggLayer);
		event.registerLayerDefinition(FriedEggItem.BACON_EGG.getLayerLocation(), FriedEggModel::createEggLayer);
		event.registerLayerDefinition(FriedEggItem.GRILLED_CRAB.getLayerLocation(), GrilledCrabModel::createBodyLayer);

		event.registerLayerDefinition(OmeletItem.BASIC.getLayerLocation(), OmeletModel::createBodyLayer);
		event.registerLayerDefinition(OmeletItem.VEGI.getLayerLocation(), OmeletModel::createBodyLayer);
		event.registerLayerDefinition(OmeletItem.SAUSAGE.getLayerLocation(), SausageModel::createBodyLayer);
		event.registerLayerDefinition(OmeletItem.SAUSAGE_CURRY.getLayerLocation(), SausageCurryModel::createBodyLayer);
		event.registerLayerDefinition(OmeletItem.GALETTE.getLayerLocation(), GaletteModel::createBodyLayer);

		event.registerLayerDefinition(SauteItem.GREEN.getLayerLocation(), SauteModel::createTofuLayer);
		event.registerLayerDefinition(SauteItem.GREEN_SQUID.getLayerLocation(), SauteModel::createNasuLayer);
		event.registerLayerDefinition(SauteItem.MABO.getLayerLocation(), SauteModel::createTofuLayer);
		event.registerLayerDefinition(SauteItem.MEAT.getLayerLocation(), SauteModel::createNasuLayer);
		event.registerLayerDefinition(SauteItem.FISH.getLayerLocation(), SauteModel::createChickenLayer);
		event.registerLayerDefinition(SauteItem.NASU.getLayerLocation(), SauteModel::createNasuLayer);
		event.registerLayerDefinition(SauteItem.PRAWN.getLayerLocation(), SauteModel::createNasuLayer);
		event.registerLayerDefinition(SauteItem.WHITE_CHICKEN.getLayerLocation(), SauteModel::createChickenLayer);
		event.registerLayerDefinition(SauteItem.HOT_CHICKEN.getLayerLocation(), SauteModel::createChickenLayer);
		event.registerLayerDefinition(SauteItem.SANBEIJI.getLayerLocation(), SauteModel::createNasuLayer);
		event.registerLayerDefinition(SauteItem.CRAB_EGG.getLayerLocation(), SauteModel::createEggLayer);
		event.registerLayerDefinition(SauteItem.CASHEW_CHICKEN.getLayerLocation(), SauteModel::createNasuLayer);

		event.registerLayerDefinition(RenderCurry.BOWL_METAL.getLayerLocation(), LargeBowlModel::createBodyLayer);
		event.registerLayerDefinition(RenderCurry.BOWL_WHITE.getLayerLocation(), LargeBowlModel::createBodyLayer);

		event.registerLayerDefinition(CurryItem.CURRY_VEGI.getLayerLocation(), LargeBowlModel_Base::createBodyLayer);
		event.registerLayerDefinition(CurryItem.CURRY_BEANS.getLayerLocation(), LargeBowlModel_Base::createBodyLayer);
		event.registerLayerDefinition(CurryItem.CURRY_SPINACH.getLayerLocation(), LargeBowlModel_Base::createBodyLayer);
		event.registerLayerDefinition(CurryItem.CURRY_BUTTER_CHICKEN.getLayerLocation(), LargeBowlModel_Base::createBodyLayer);
		event.registerLayerDefinition(CurryItem.CURRY_VINDALOO.getLayerLocation(), LargeBowlModel_Base::createBodyLayer);
		event.registerLayerDefinition(CurryItem.CURRY_BIRIYANI.getLayerLocation(), LargeBowlModel_Base::createBodyLayer);
		event.registerLayerDefinition(CurryItem.CURRY_GREEN.getLayerLocation(), LargeBowlModel_Base::createBodyLayer);
		event.registerLayerDefinition(CurryItem.CURRY_RED.getLayerLocation(), LargeBowlModel_Base::createBodyLayer);
		event.registerLayerDefinition(CurryItem.CURRY_MASSAMAN.getLayerLocation(), LargeBowlModel_Base::createBodyLayer);
		event.registerLayerDefinition(CurryItem_Fish.CURRY_FISH.getLayerLocation(), LargeBowlModel_Fish::createBodyLayer);
		event.registerLayerDefinition(CurryItem_Fish.LARGE_BOWL_ACQUA_PAZZA.getLayerLocation(), LargeBowlModel_Fish::createBodyLayer);
		event.registerLayerDefinition(CurryItem_Rice.CURRY_RICE.getLayerLocation(), LargeBowlModel_Rice::createBodyLayer);
		event.registerLayerDefinition(CurryItem_Meat.LARGE_BOWL_FISH.getLayerLocation(), LargeBowlModel_Meat::createBodyLayer);
		event.registerLayerDefinition(CurryItem_Meat.LARGE_BOWL_CABBAGE.getLayerLocation(), LargeBowlModel_Meat::createBodyLayer);
		event.registerLayerDefinition(CurryItem_Meat.LARGE_BOWL_LAMB.getLayerLocation(), LargeBowlModel_Meat::createBodyLayer);
		event.registerLayerDefinition(CurryItem_Meat.LARGE_BOWL_BEEF.getLayerLocation(), LargeBowlModel_Meat::createBodyLayer);
		event.registerLayerDefinition(CurryItem_Sashimi.LARGE_BOWL_CARPACCIO.getLayerLocation(), LargeBowlModel_Sashimi::createBodyLayer);
		event.registerLayerDefinition(CurryItem_Sashimi.LARGE_BOWL_FISH_CARPACCIO.getLayerLocation(), LargeBowlModel_Sashimi::createBodyLayer);
		event.registerLayerDefinition(CurryItem_Sashimi.LARGE_BOWL_CAPRESE.getLayerLocation(), LargeBowlModel_Sashimi::createBodyLayer);

		event.registerLayerDefinition(SquareSashimiItem.WHITE.getLayerLocation(), SquareSashimiModel::createBodyLayer);
		event.registerLayerDefinition(SquareSashimiItem.SALMON.getLayerLocation(), SquareSashimiModel::createBodyLayer);
		event.registerLayerDefinition(SquareSashimiItem.BLUE.getLayerLocation(), SquareSashimiModel::createBodyLayer);
		event.registerLayerDefinition(SquareSashimiItem.TUNA.getLayerLocation(), SquareSashimiModel::createBodyLayer);
		event.registerLayerDefinition(SquareSashimiItem.SQUID.getLayerLocation(), SquareSashimiModel::createBodyLayer);

		event.registerLayerDefinition(SquareFishItem.MEUNIERE.getLayerLocation(), SquareFishModel::createBodyLayer);
		event.registerLayerDefinition(SquareFishItem.SIMMER.getLayerLocation(), SquareFishModel::createBodyLayer);
		event.registerLayerDefinition(SquareFishItem.SALMON.getLayerLocation(), SquareFishModel::createBodyLayer);
		event.registerLayerDefinition(SquareFishItem.WHITE.getLayerLocation(), SquareFishModel::createBodyLayer);
		event.registerLayerDefinition(SquareFishItem.IWASHI.getLayerLocation(), SquareFishModel::createBodyLayer);

		event.registerLayerDefinition(TempuraVegiItem.VEGI.getLayerLocation(), TempuraVegiModel::createBodyLayer);
		event.registerLayerDefinition(TempuraPrawnItem.PRAWN.getLayerLocation(), TempuraPrawnModel::createBodyLayer);
		event.registerLayerDefinition(TempuraIkatenItem.IKATEN.getLayerLocation(), TempuraIkatenModel::createBodyLayer);
		event.registerLayerDefinition(TempuraIkatenItem.UME.getLayerLocation(), TempuraIkatenModel::createBodyLayer);
		event.registerLayerDefinition(TempuraFishItem.FISH.getLayerLocation(), TempuraFishModel::createBodyLayer);

		event.registerLayerDefinition(DeepfryTonkatsuItem.TONKATSU.getLayerLocation(), DeepfryTonkatsuModel::createBodyLayer);
		event.registerLayerDefinition(DeepfryKaraageItem.KARAAGE.getLayerLocation(), DeepfryKaraageModel::createBodyLayer);
		event.registerLayerDefinition(DeepfryCroquetteItem.POTATO.getLayerLocation(), DeepfryCroquetteModel::createBodyLayer);
		event.registerLayerDefinition(DeepfryCroquetteItem.PUMPKIN.getLayerLocation(), DeepfryCroquetteModel::createBodyLayer);
		event.registerLayerDefinition(DeepfryCroquetteItem.CORN.getLayerLocation(), DeepfryCroquetteModel::createBodyLayer);
		event.registerLayerDefinition(DeepfryCroquetteItem.CRAB.getLayerLocation(), DeepfryCroquetteModel::createBodyLayer);
		event.registerLayerDefinition(DeepfryCroquetteItem.FISH.getLayerLocation(), DeepfryCroquetteModel::createBodyLayer);

		event.registerLayerDefinition(DonutItem.SUGAR.getLayerLocation(), DonutModel::createBodyLayer);
		event.registerLayerDefinition(DonutItem.CINNAMON.getLayerLocation(), DonutModel::createBodyLayer);
		event.registerLayerDefinition(DonutItem.COCOA.getLayerLocation(), DonutModel::createBodyLayer);
		event.registerLayerDefinition(DonutItem.COCONUT.getLayerLocation(), DonutModel::createBodyLayer);
		event.registerLayerDefinition(DonutItem.BERRY.getLayerLocation(), DonutModel::createBodyLayer);
		event.registerLayerDefinition(DonutItem.MINT.getLayerLocation(), DonutModel::createBodyLayer);
		event.registerLayerDefinition(DonutItem.JELLY.getLayerLocation(), DonutJellyModel::createBodyLayer);
		event.registerLayerDefinition(DonutItem.ROSE.getLayerLocation(), DonutJellyModel::createBodyLayer);

		event.registerLayerDefinition(PancakeItem.HONEY.getLayerLocation(), PancakeModel::createBodyLayer);

		event.registerLayerDefinition(CrapeItem.SUGAR.getLayerLocation(), CrapeModel::createBodyLayer);
		event.registerLayerDefinition(CrapeItem.CREAM.getLayerLocation(), CrapeModel::createBodyLayer);
		event.registerLayerDefinition(CrapeItem.CARAMEL.getLayerLocation(), CrapeModel::createBodyLayer);
		event.registerLayerDefinition(CrapeItem.COCOA.getLayerLocation(), CrapeModel::createBodyLayer);
		event.registerLayerDefinition(CrapeItem.APPLE.getLayerLocation(), CrapeModel::createBodyLayer);
		event.registerLayerDefinition(CrapeItem.BERRY.getLayerLocation(), CrapeModel::createBodyLayer);
		event.registerLayerDefinition(CrapeItem.TUNA.getLayerLocation(), CrapeModel::createBodyLayer);

		event.registerLayerDefinition(CakeItem.BUTTER.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.BERRY.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.CHOCOLATE.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.GREENTEA.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.APPLE.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.LEMON.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.GRAPE.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.ROSE.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.CASSATA.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.BAKLAVA.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.CREAM_VANILLA.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.CREAM_MANGO.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.CREAM_SESAMI.getLayerLocation(), CakeModel::createBodyLayer);

		event.registerLayerDefinition(WagashiItem.BOTA.getLayerLocation(), WagashiModel::createBodyLayer);
		event.registerLayerDefinition(WagashiItem.KUSA.getLayerLocation(), WagashiModel::createBodyLayer);
		event.registerLayerDefinition(WagashiItem.SAKURA.getLayerLocation(), WagashiModel::createBodyLayer);
		event.registerLayerDefinition(WagashiItem.DAIHUKU.getLayerLocation(), WagashiModel::createBodyLayer);

		event.registerLayerDefinition(IcecreamItem.VANILLA.getLayerLocation(), IcecreamModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.BERRY.getLayerLocation(), IcecreamModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.COCOA.getLayerLocation(), IcecreamModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.NUTS.getLayerLocation(), IcecreamModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.WATERMELON.getLayerLocation(), IcecreamModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.MELON.getLayerLocation(), IcecreamModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.GRAPE.getLayerLocation(), IcecreamModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.CITRUS.getLayerLocation(), IcecreamModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.MANGO.getLayerLocation(), IcecreamModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.GUAVA.getLayerLocation(), IcecreamModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.SUNDAE_COCOA.getLayerLocation(), IcecreamSandaeModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.SUNDAE_AFFOGATO.getLayerLocation(), IcecreamSandaeModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.SUNDAE_PEACH.getLayerLocation(), IcecreamSandaeModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.CREAMSODA_MELON.getLayerLocation(), IcecreamFloatModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.CREAMSODA_MALLOW.getLayerLocation(), IcecreamFloatModel::createBodyLayer);
		event.registerLayerDefinition(IcecreamItem.PUDDING.getLayerLocation(), IcecreamPuddingModel::createBodyLayer);

		event.registerLayerDefinition(DumplingItem.TUBERS.getLayerLocation(), DumplingModel::createBodyLayer);
		event.registerLayerDefinition(DumplingItem.CEREALS.getLayerLocation(), DumplingModel::createBodyLayer);
		event.registerLayerDefinition(DumplingItem.BUCKWHEAT.getLayerLocation(), DumplingModel::createBodyLayer);

		event.registerLayerDefinition(RiceBowlItem.NORMAL.getLayerLocation(), RiceModel::createBodyLayer);
		event.registerLayerDefinition(RiceBowlItem.BARLEY.getLayerLocation(), RiceModel::createBodyLayer);
		event.registerLayerDefinition(RiceBowlItem.SEKI.getLayerLocation(), RiceModel::createBodyLayer);
		event.registerLayerDefinition(RiceBowlItem.NAPA.getLayerLocation(), RiceModel::createBodyLayer);
		event.registerLayerDefinition(RiceBowlItem.FISH.getLayerLocation(), RiceModel::createBodyLayer);
		event.registerLayerDefinition(RiceBowlItem.MUSHROOM.getLayerLocation(), RiceModel::createBodyLayer);
		event.registerLayerDefinition(RiceBowlItem.TAIMESHI.getLayerLocation(), RiceModel::createBodyLayer);

		event.registerLayerDefinition(ChazukeItem.UME.getLayerLocation(), ChazukeModel::createBodyLayer);
		event.registerLayerDefinition(ChazukeItem.SAKE.getLayerLocation(), ChazukeModel::createBodyLayer);
		event.registerLayerDefinition(ChazukeItem.TAI.getLayerLocation(), ChazukeModel::createBodyLayer);
		event.registerLayerDefinition(ChazukeItem.TARAKO.getLayerLocation(), ChazukeModel::createBodyLayer);

		event.registerLayerDefinition(RicemealItem.CHAOFAN.getLayerLocation(), Rice_ChaofanModel::createBodyLayer);
		event.registerLayerDefinition(RicemealItem.KETCHUP.getLayerLocation(), Rice_ChaofanModel::createBodyLayer);
		event.registerLayerDefinition(RicemealItem.OMERICE.getLayerLocation(), Rice_ChaofanModel::createBodyLayer);
		event.registerLayerDefinition(RicemealItem.HAINAN.getLayerLocation(), Rice_HainanChickenModel::createBodyLayer);
		event.registerLayerDefinition(RicemealItem.PILAV.getLayerLocation(), Rice_JambalayaModel::createBodyLayer);
		event.registerLayerDefinition(RicemealItem.JAMBALAYA.getLayerLocation(), Rice_JambalayaModel::createBodyLayer);
		event.registerLayerDefinition(RicemealItem.JOLLOF.getLayerLocation(), Rice_JollofModel::createBodyLayer);
		event.registerLayerDefinition(RicemealItem.PAELLIA.getLayerLocation(), Rice_PaelliaModel::createBodyLayer);

		event.registerLayerDefinition(PastaItem.OIL.getLayerLocation(), PastaModel::createBodyLayer);
		event.registerLayerDefinition(PastaItem.KRILL.getLayerLocation(), PastaModel::createBodyLayer);
		event.registerLayerDefinition(PastaItem.BASIL.getLayerLocation(), PastaModel::createBodyLayer);
		event.registerLayerDefinition(PastaItem.TOMATO.getLayerLocation(), PastaModel::createBodyLayer);
		event.registerLayerDefinition(PastaItem.PRAWN.getLayerLocation(), PastaModel::createBodyLayer);
		event.registerLayerDefinition(PastaItem.PUTTANESCA.getLayerLocation(), PastaModel::createBodyLayer);
		event.registerLayerDefinition(PastaItem.SQUID.getLayerLocation(), PastaModel::createBodyLayer);
		event.registerLayerDefinition(PastaItem.BEEF.getLayerLocation(), PastaModel::createBodyLayer);
		event.registerLayerDefinition(PastaItem.COD.getLayerLocation(), PastaModel::createBodyLayer);
		event.registerLayerDefinition(PastaItem.ROE.getLayerLocation(), PastaModel::createBodyLayer);
		event.registerLayerDefinition(PastaItem.KETCHUP.getLayerLocation(), PastaModel::createBodyLayer);
		event.registerLayerDefinition(PastaItem.CARBONARA.getLayerLocation(), PastaModel::createBodyLayer);

		event.registerLayerDefinition(NoodleItem.PHAT_THAI_PRAWN.getLayerLocation(), PastaModel::createBodyLayer);
		event.registerLayerDefinition(NoodleItem.PHAT_THAI_CHICKEN.getLayerLocation(), PastaModel::createBodyLayer);

		event.registerLayerDefinition(RamenItem.SHOYU.getLayerLocation(), RamenModel::createBodyLayer);
		event.registerLayerDefinition(RamenItem.MISO.getLayerLocation(), RamenModel::createBodyLayer);
		event.registerLayerDefinition(RamenItem.TONKOTSU.getLayerLocation(), RamenModel::createBodyLayer);
		event.registerLayerDefinition(RamenItem.TANTAN.getLayerLocation(), RamenModel::createBodyLayer);
		event.registerLayerDefinition(RamenItem.SESAMI.getLayerLocation(), RamenModel::createBodyLayer);
		event.registerLayerDefinition(RamenItem.BEEF.getLayerLocation(), RamenModel::createBodyLayer);
		event.registerLayerDefinition(RamenItem.OKINAWA.getLayerLocation(), RamenModel::createBodyLayer);
		event.registerLayerDefinition(RamenItem.HOUTOU.getLayerLocation(), RamenModel::createBodyLayer);
		event.registerLayerDefinition(RamenItem.REIMEN.getLayerLocation(), RamenModel::createBodyLayer);
		event.registerLayerDefinition(RamenItem.LAKSA.getLayerLocation(), RamenModel::createBodyLayer);
		event.registerLayerDefinition(RamenItem.TOMYUM.getLayerLocation(), RamenModel::createBodyLayer);

		event.registerLayerDefinition(UdonItem.SIMPLE.getLayerLocation(), SobaModel::createBodyLayer);
		event.registerLayerDefinition(UdonItem.KITSUNE.getLayerLocation(), SobaModel::createBodyLayer);
		event.registerLayerDefinition(UdonItem.TSUKIMI.getLayerLocation(), SobaModel::createBodyLayer);
		event.registerLayerDefinition(UdonItem.TENPURA.getLayerLocation(), SobaModel::createBodyLayer);

		event.registerLayerDefinition(SobaItem.SIMPLE.getLayerLocation(), SobaModel::createBodyLayer);
		event.registerLayerDefinition(SobaItem.KITSUNE.getLayerLocation(), SobaModel::createBodyLayer);
		event.registerLayerDefinition(SobaItem.TSUKIMI.getLayerLocation(), SobaModel::createBodyLayer);
		event.registerLayerDefinition(SobaItem.TENPURA.getLayerLocation(), SobaModel::createBodyLayer);

		event.registerLayerDefinition(ZarusobaItem.SIMPLE.getLayerLocation(), ZarusobaModel::createBodyLayer);
		event.registerLayerDefinition(ZarusobaItem.TEA.getLayerLocation(), ZarusobaModel::createBodyLayer);
		event.registerLayerDefinition(ZarusobaItem.SISO.getLayerLocation(), ZarusobaModel::createBodyLayer);

		event.registerLayerDefinition(BottleBeerItem.BEER.getLayerLocation(), BottleModel_Beer::createBodyLayer);
		event.registerLayerDefinition(BottleBeerItem.SAKE.getLayerLocation(), BottleModel_Beer::createBodyLayer);
		event.registerLayerDefinition(BottleWineItem.WINE.getLayerLocation(), BottleModel_Wine::createBodyLayer);
		event.registerLayerDefinition(BottleWineItem.WINE_WHITE.getLayerLocation(), BottleModel_Wine::createBodyLayer);

		event.registerLayerDefinition(DrinkColdItem.DRINK_APPLE.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_APPLE_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_BERRY.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_BERRY_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_GRAPE.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_GRAPE_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_GRAPE_WHITE.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_GRAPE_WHITE_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_GUAVA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_GUAVA_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_LEMON.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_MANDARIN.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_POMELO.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_CITRUS_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_MANGO.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_MANGO_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_MELON.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_MELON_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_VEGETABLE.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_MILK_SHAKE.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_PEACH.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_PEACH_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_PLUM.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_PLUM_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_TEA_BARLEY.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_TEA_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_TONIC.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_LASSI_PLANE.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_LASSI_MANGO.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_LASSI_CITRUS.getLayerLocation(), DrinkGlassModel::createBodyLayer);

		event.registerLayerDefinition(DrinkCupItem.TEA_APPLE.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_APPLE_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BERRY.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BERRY_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BLACK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BLACK_LEMON.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BLACK_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BLUE.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BLUE_JASMINE.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BLUE_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_CHAI.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_COFFEE.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_COFFEE_CARDAMOM.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_COFFEE_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_COCOA.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_COCOA_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_GREEN.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_GREEN_SAKURA.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_GREEN_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_HIBISCUS.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_MALLOW.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_MALLOW_LEMON.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_MINT.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_ROSEHIP.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_SAFFRON.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);

		event.registerLayerDefinition(DrinkDemitasseItem.COFFEE.getLayerLocation(), DrinkDemitasseModel::createBodyLayer);

		event.registerLayerDefinition(DrinkCappuccinoItem.LATTE.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCappuccinoItem.CAPPUCCINO.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCappuccinoItem.NUTS.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCappuccinoItem.COCOA.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCappuccinoItem.SYRUP.getLayerLocation(), DrinkCupModel::createBodyLayer);

		// pot layer
		event.registerLayerDefinition(RiceBowlItem.NORMAL_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(RiceBowlItem.BARLEY_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(RiceBowlItem.SEKI_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(RiceBowlItem.MUSHROOM_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(RiceBowlItem.TAIMESHI_LAYER.getLayerLocation(), CookingPotModel_FishLayer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_MILK_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_SAFFRON_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_SQUID_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.MUESLI_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.OAT_MILK_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);

		event.registerLayerDefinition(PorridgeItem.STEW_BORSCH_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_IRISH_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CONSOMME_VEGETABLE_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CREAM_MUSHROOM_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CREAM_SALMON_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CREAM_SHRIMP_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CULLEN_SKINK_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_KHARCHO_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_ERWTEN_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_LAMPREDOTTO_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_OFFAL_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_TOMYUMGOONG_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_TOMYUMGAI_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_TOMYUMPLA_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_BAKKUTTEH_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_PALMNUTS_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_GOULASH_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);

		event.registerLayerDefinition(WaBowlItem.MISO_TOFU_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(WaBowlItem.MISO_AGE_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(WaBowlItem.MISO_EGGPLANT_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(WaBowlItem.MISO_MUSHROOM_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(WaBowlItem.MISO_PORK_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(WaBowlItem.MISO_CARP_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(WaBowlItem.SHIRUKO_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);

		event.registerLayerDefinition(SoupItem.SOUP_LEAVES_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_LEEK_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_POTATO_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_PUMPKIN_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_CORN_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_SPINACH_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_PARSNIP_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_SHRIMP_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CHINESE_EGG_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CHINESE_CRAB_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_JUTE_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_GASPACHO_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_TARATOR_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_MINESTRONE_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CHILIBEANS_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_SORREL_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);

		event.registerLayerDefinition(HotPotItem.TOFU_LAYER.getLayerLocation(), CookingPotModel_HotPotLayer::createOvarlayLayer);
		event.registerLayerDefinition(HotPotItem.CHICKEN_LAYER.getLayerLocation(), CookingPotModel_HotPotLayer::createOvarlayLayer);
		event.registerLayerDefinition(HotPotItem.CRAB_LAYER.getLayerLocation(), CookingPotModel_HotPotLayer::createOvarlayLayer);
		event.registerLayerDefinition(HotPotItem.MALATANG_LAYER.getLayerLocation(), CookingPotModel_HotPotLayer::createOvarlayLayer);
	}

	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		// TESR
		event.registerBlockEntityRenderer(BuildInit.CHANDELIER_TILE.get(), TileRendererChandelier::new);
		event.registerBlockEntityRenderer(BuildInit.CHAIR_ROUND_TILE.get(), TileRendererChairRound::new);
		event.registerBlockEntityRenderer(BuildInit.NO_SAVE_HAMMOCK_TILE.get(), TileRendererHammock::new);
		event.registerBlockEntityRenderer(BuildInit.LUGGAGE_TILE.get(), TileRendererLuggage::new);
		event.registerBlockEntityRenderer(BuildInit.CABINET_TILE.get(), TileRendererCabinet::new);
		event.registerBlockEntityRenderer(BuildInit.LOCKER_TILE.get(), TileRendererLocker::new);
		event.registerBlockEntityRenderer(BuildInit.TOOLHOOK_TILE.get(), TileRendererToolHook::new);
		event.registerBlockEntityRenderer(BuildInit.DISPLAY_SHELF_TILE.get(), TileRendererDisplayShelf::new);
		event.registerBlockEntityRenderer(BuildInit.DISPLAY_DOUBLE_SHELF_TILE.get(), TileRendererDisplayDoubleShelf::new);
		event.registerBlockEntityRenderer(BuildInit.DISPLAY_CASE_TILE.get(), TileRendererDisplayCase::new);
		event.registerBlockEntityRenderer(BuildInit.VILLAGER_CHEST_TILE.get(), TileRendererVillagerChest::new);
		event.registerBlockEntityRenderer(MachineInit.CHAMBER_BRICK_TILE.get(), TileRendererChamberFuel::new);
		event.registerBlockEntityRenderer(MachineInit.CHAMBER_IRON_TILE.get(), TileRendererChamberIron::new);
		event.registerBlockEntityRenderer(MachineInit.KICHEN_STOVE_TILE.get(), TileRendererKichenStove::new);
		event.registerBlockEntityRenderer(MachineInit.KICHEN_OVEN_TILE.get(), TileRendererKichenOven::new);
		event.registerBlockEntityRenderer(MachineInit.KICHEN_BENCH_TILE.get(), TileRendererKichenBench::new);
		event.registerBlockEntityRenderer(MachineInit.FLUID_SINK_TILE.get(), TileRendererSink::new);
		event.registerBlockEntityRenderer(MachineInit.PORTABLE_CAN_TILE.get(), TileRendererPortableCan::new);
		event.registerBlockEntityRenderer(MachineInit.IBC_TILE.get(), TileRendererIBC::new);
		event.registerBlockEntityRenderer(MachineInit.WATER_BASIN_TILE.get(), TileRendererWaterBasin::new);
		event.registerBlockEntityRenderer(MachineInit.COOKING_POT_TILE.get(), TileRendererCookingPot::new);
		event.registerBlockEntityRenderer(MachineInit.TEA_POT_TILE.get(), TileRendererTeaPot::new);
		event.registerBlockEntityRenderer(MachineInit.FERMENTATION_JAR_TILE.get(), TileRendererFermentationJar::new);
		event.registerBlockEntityRenderer(MachineInit.MILL_TILE.get(), TileRendererStoneMill::new);
		event.registerBlockEntityRenderer(MachineInit.CRUSHER_TILE.get(), TileRendererRollCrusher::new);
		event.registerBlockEntityRenderer(MachineInit.CABLE_COPPER_TILE.get(), TileRendererCopperCable::new);
		event.registerBlockEntityRenderer(MachineInit.CABLE_ALUMINUM_TILE.get(), TileRendererAluminumCable::new);
		event.registerBlockEntityRenderer(MachineInit.PIPE_BRASS_TILE.get(), TileRendererPipeAlloy::new);
		event.registerBlockEntityRenderer(MachineInit.PIPE_NICKELSILVER_TILE.get(), TileRendererPipeNickelsilver::new);
		event.registerBlockEntityRenderer(MachineInit.WATER_PUMP_TILE.get(), TileRendererWaterPump::new);
		event.registerBlockEntityRenderer(MachineInit.STORMGLASS_TILE.get(), TileRendererStormglass::new);
		event.registerBlockEntityRenderer(MachineInit.MONITOR_TEMP_TILE.get(), TileRendererMeterTemp::new);
		event.registerBlockEntityRenderer(MachineInit.MONITOR_ENERGY_TILE.get(), TileRendererMeterEnergy::new);
		event.registerBlockEntityRenderer(MachineInit.CONVEYOR_TILE.get(), TileRendererConveyor::new);
		event.registerBlockEntityRenderer(MachineInit.CONVEYOR_VERTICAL_TILE.get(), TileRendererConveyorVertical::new);
		event.registerBlockEntityRenderer(MachineInit.CONVEYOR_SMELTING_TILE.get(), TileRendererConveyor::new);
		event.registerBlockEntityRenderer(MachineInit.CONVEYOR_DROPPER_TILE.get(), TileRendererConveyor::new);
		event.registerBlockEntityRenderer(MachineInit.CONVEYOR_FILLER_TILE.get(), TileRendererConveyor::new);
		event.registerBlockEntityRenderer(MachineInit.CONVEYOR_SORTER_TILE.get(), TileRendererConveyorSorter::new);
		event.registerBlockEntityRenderer(MachineInit.FAUCET_TILE.get(), TileRendererFaucet::new);
		event.registerBlockEntityRenderer(MachineInit.INTAKE_FAN_TILE.get(), TileRendererIntakeFan::new);
		event.registerBlockEntityRenderer(MachineInit.HYDRO_TURBINE_TILE.get(), TileRendererHydroTurbine::new);
		event.registerBlockEntityRenderer(MachineInit.COFFEE_SIPHON_TILE.get(), TileRendererCoffeeSiphon::new);
		event.registerBlockEntityRenderer(MachineInit.COFFEE_MACCHINETTA_TILE.get(), TileRendererCoffeeMacchinetta::new);

		// Entity
		event.registerEntityRenderer(CoreInit.HARPOON.get(), RenderHarpoon::new);
		event.registerEntityRenderer(CoreInit.CUTLERY_CHOPSTICKS.get(), RenderChopsticks::new);
		event.registerEntityRenderer(CoreInit.CUTLERY_SPOON.get(), RenderSpoon::new);
		event.registerEntityRenderer(CoreInit.CUTLERY_FORK.get(), RenderFork::new);
		event.registerEntityRenderer(CoreInit.CHAIR_ENTITY.get(), RenderChair::new);
		event.registerEntityRenderer(CoreInit.CANOE.get(), RenderCanoe::new);
		event.registerEntityRenderer(BuildInit.FLOWER_POT.get(), RenderFlowerPot::new);
		event.registerEntityRenderer(BuildInit.CUSHION.get(), RenderCushion::new);

		event.registerEntityRenderer(FoodInit.BREAD_ROUND.get(), RenderFoodBase::new);
		event.registerEntityRenderer(FoodInit.BREAD_SQUARE.get(), RenderBreadSquare::new);
		event.registerEntityRenderer(FoodInit.BREAD_FLAT.get(), RenderBreadFlat::new);
		event.registerEntityRenderer(FoodInit.BREAD_CREAM.get(), RenderBreadCream::new);
		event.registerEntityRenderer(FoodInit.BREAD_SAUSAGE.get(), RenderBreadSausage::new);
		event.registerEntityRenderer(FoodInit.SANDWICH.get(), RenderSandwich::new);
		event.registerEntityRenderer(FoodInit.TACO.get(), RenderTaco::new);
		event.registerEntityRenderer(FoodInit.QUESADILLA.get(), RenderQuesadilla::new);
		event.registerEntityRenderer(FoodInit.PIZZA.get(), RenderPizza::new);
		event.registerEntityRenderer(FoodInit.SWEETPOTATO.get(), RenderSweetpotato::new);
		event.registerEntityRenderer(FoodInit.STICK_BEEF.get(), RenderStickBeef::new);
		event.registerEntityRenderer(FoodInit.STICK_MEAT.get(), RenderStickMeat::new);
		event.registerEntityRenderer(FoodInit.STICK_FISH.get(), RenderStickFish::new);
		event.registerEntityRenderer(FoodInit.STICK_VEGI.get(), RenderStickVegi::new);
		event.registerEntityRenderer(FoodInit.STICK_CORN.get(), RenderStickCorn::new);
		event.registerEntityRenderer(FoodInit.STICK_POTATO.get(), RenderStickPotato::new);
		event.registerEntityRenderer(FoodInit.PLATE_STEAK.get(), RenderPlateSteak::new);
		event.registerEntityRenderer(FoodInit.PLATE_MEAT.get(), RenderPlateMeat::new);
		event.registerEntityRenderer(FoodInit.PLATE_LEGS.get(), RenderPlateLegs::new);
		event.registerEntityRenderer(FoodInit.PLATE_BIG_STEAK.get(), RenderPlateGarlic::new);
		event.registerEntityRenderer(FoodInit.PLATE_CHICKEN.get(), RenderPlateChicken::new);
		event.registerEntityRenderer(FoodInit.PLATE_FISH.get(), RenderPlateFish::new);
		event.registerEntityRenderer(FoodInit.PLATE_BONE.get(), RenderPlateBone::new);
		event.registerEntityRenderer(FoodInit.PLATE_STUFFED_VEGI.get(), RenderPlateStuffedVegi::new);
		event.registerEntityRenderer(FoodInit.PLATE_PUMPKIN.get(), RenderPlatePumpkin::new);
		event.registerEntityRenderer(FoodInit.SALAD.get(), RenderSalad::new);
		event.registerEntityRenderer(FoodInit.STEW.get(), RenderStew::new);
		event.registerEntityRenderer(FoodInit.SOUP.get(), RenderSoup::new);
		event.registerEntityRenderer(FoodInit.WA_BOWL.get(), RenderWaBowl::new);
		event.registerEntityRenderer(FoodInit.CAKE.get(), RenderCake::new);
		event.registerEntityRenderer(FoodInit.PANCAKE.get(), RenderPancake::new);
		event.registerEntityRenderer(FoodInit.WAGASHI.get(), RenderWagashi::new);
		event.registerEntityRenderer(FoodInit.DUMPLING.get(), RenderDumpling::new);
		event.registerEntityRenderer(FoodInit.RICE.get(), RenderRicebowl::new);
		event.registerEntityRenderer(FoodInit.CHAZUKE.get(), RenderChazuke::new);
		event.registerEntityRenderer(FoodInit.BOTTLE_BEERTYPE.get(), RenderBottleBeer::new);
		event.registerEntityRenderer(FoodInit.BOTTLE_WINETYPE.get(), RenderBottleWine::new);
		event.registerEntityRenderer(FoodInit.CUP.get(), RenderDrinkCup::new);
		event.registerEntityRenderer(FoodInit.GLASS.get(), RenderDrinkCold::new);
		event.registerEntityRenderer(FoodInit.CASSEROLE.get(), RenderCasserole::new);
		event.registerEntityRenderer(FoodInit.TART.get(), RenderTart::new);
		event.registerEntityRenderer(FoodInit.CURRY_BASE.get(), RenderCurryBase::new);
		event.registerEntityRenderer(FoodInit.CURRY_FISH_MODEL.get(), RenderCurryFish::new);
		event.registerEntityRenderer(FoodInit.CURRY_MEAT_MODEL.get(), RenderCurryMeat::new);
		event.registerEntityRenderer(FoodInit.CURRY_RICE_MODEL.get(), RenderCurryRice::new);
		event.registerEntityRenderer(FoodInit.CURRY_SASHIMI_MODEL.get(), RenderCurrySashimi::new);
		event.registerEntityRenderer(FoodInit.SQUARE_FISH.get(), RenderSquareFish::new);
		event.registerEntityRenderer(FoodInit.SQUARE_SASHIMI.get(), RenderSquareSashimi::new);
		event.registerEntityRenderer(FoodInit.PASTA.get(), RenderPasta::new);
		event.registerEntityRenderer(FoodInit.NOODLE.get(), RenderPasta::new);
		event.registerEntityRenderer(FoodInit.RAMEN.get(), RenderRamen::new);
		event.registerEntityRenderer(FoodInit.UDON.get(), RenderUdon::new);
		event.registerEntityRenderer(FoodInit.SOBA.get(), RenderUdon::new);
		event.registerEntityRenderer(FoodInit.TEMPURA_VEGI_MODEL.get(), RenderTempuraVegi::new);
		event.registerEntityRenderer(FoodInit.TEMPURA_PRAWN_MODEL.get(), RenderTempuraPrawn::new);
		event.registerEntityRenderer(FoodInit.TEMPURA_IKATEN_MODEL.get(), RenderTempuraIkaten::new);
		event.registerEntityRenderer(FoodInit.TEMPURA_FISH_MODEL.get(), RenderTempuraFish::new);
		event.registerEntityRenderer(FoodInit.FRY_TONKATSU.get(), RenderFryTonkatsu::new);
		event.registerEntityRenderer(FoodInit.FRY_KARAAGE.get(), RenderFryKaraage::new);
		event.registerEntityRenderer(FoodInit.FRY_CROQUETTE.get(), RenderFryCroquette::new);
		event.registerEntityRenderer(FoodInit.KOBACHI.get(), RenderKobachi::new);
		event.registerEntityRenderer(FoodInit.SAUTE.get(), RenderSaute::new);
		event.registerEntityRenderer(FoodInit.ZARUSOBA.get(), RenderZarusoba::new);
		event.registerEntityRenderer(FoodInit.DONUT.get(), RenderDonut::new);
		event.registerEntityRenderer(FoodInit.DEMITASSE.get(), RenderDrinkDemitasse::new);
		event.registerEntityRenderer(FoodInit.CAPPUCCINO.get(), RenderDrinkCappuccino::new);
		event.registerEntityRenderer(FoodInit.ICECREAM.get(), RenderIcecream::new);
		event.registerEntityRenderer(FoodInit.RICEMEAL.get(), RenderRicemeal::new);
		event.registerEntityRenderer(FoodInit.FRIED_EGG_ENTITY.get(), RenderFriedEgg::new);
		event.registerEntityRenderer(FoodInit.OMELET.get(), RenderOmelet::new);
		event.registerEntityRenderer(FoodInit.MINIDISH.get(), RenderMinidish::new);
		event.registerEntityRenderer(FoodInit.HOTPOT.get(), RenderHotPot::new);
		event.registerEntityRenderer(FoodInit.CRAPE.get(), RenderCrape::new);

		event.registerEntityRenderer(MagicInit.ARROW_WHITE_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_BLUE_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_BLACK_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_RED_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_GREEN_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_ROBBER_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_BIND_ENTITY.get(), RenderColorArrow::new);

		event.registerEntityRenderer(MagicInit.BIND_PLANT_ENTITY.get(), RenderBindPlant::new);
		event.registerEntityRenderer(MagicInit.BIND_ELECTRIC_ENTITY.get(), RenderBindPlant::new);

		event.registerEntityRenderer(MagicInit.PHOENIX_LIGHT_ENTITY.get(), RenderLightCauldron::new);
		event.registerEntityRenderer(MagicInit.SILKY_FAIRY_ENTITY.get(), RenderEmpty::new);
		event.registerEntityRenderer(MagicInit.SILKY_BOMB_ENTITY.get(), RenderSilkySmallBomb::new);
		event.registerEntityRenderer(MagicInit.CROW_TURRET.get(), RenderCrowTurret::new);
		event.registerEntityRenderer(MagicInit.MAGIC_PICTURE_WU.get(), RenderMagicPicture::new);
		event.registerEntityRenderer(MagicInit.MAGIC_PICTURE_WR.get(), RenderMagicPicture::new);
		event.registerEntityRenderer(MagicInit.MAGIC_PICTURE_UB.get(), RenderMagicPicture::new);
		event.registerEntityRenderer(MagicInit.MAGIC_PICTURE_UG.get(), RenderMagicPicture::new);
		event.registerEntityRenderer(MagicInit.MAGIC_PICTURE_BR.get(), RenderMagicPicture::new);
		event.registerEntityRenderer(MagicInit.MAGIC_PICTURE_BW.get(), RenderMagicPicture::new);
		event.registerEntityRenderer(MagicInit.MAGIC_PICTURE_RG.get(), RenderMagicPicture::new);
		event.registerEntityRenderer(MagicInit.MAGIC_PICTURE_RU.get(), RenderMagicPicture::new);
		event.registerEntityRenderer(MagicInit.MAGIC_PICTURE_GW.get(), RenderMagicPicture::new);
		event.registerEntityRenderer(MagicInit.MAGIC_PICTURE_GB.get(), RenderMagicPicture::new);
		event.registerEntityRenderer(MagicInit.SOLID_ORB.get(), ThrownItemRenderer::new);
	}

	public static void registerLayers(EntityRenderersEvent.AddLayers event) {

	}

	public static void registerParticle(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(CoreInit.SMOKE.get(), SmokeParticleDC.Provider::new);
		event.registerSpriteSet(CoreInit.SMOKE_SMALL.get(), SmokeParticleDC.ProviderSmall::new);
		event.registerSpriteSet(CoreInit.LIGHT_ORB_WHITE.get(), LightOrbDC.Provider::new);
		event.registerSpriteSet(CoreInit.LIGHT_ORB_RED.get(), LightOrbDC.Provider::new);
		event.registerSpriteSet(CoreInit.LIGHT_ORB_BLUE.get(), LightOrbDC.Provider::new);
		event.registerSpriteSet(CoreInit.LEAKAGE.get(), LeakageParticleDC.Provider::new);
		event.registerSpriteSet(CoreInit.FOOD_PARTICLE.get(), SimpleParticleDC.Provider::new);
		event.registerSpriteSet(CoreInit.SPARKLE.get(), SparkleParticleDC.Provider::new);
		event.registerSpriteSet(CoreInit.BUBBLE_BLACK.get(), BubbleParticleDC.Provider::new);
	}

	public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
		event.registerReloadListener(EntityModelLoader.INSTANCE);
	}

	public static void registerRenderTypes() {
		ItemBlockRenderTypes.setRenderLayer(CoreInit.BRINE.getStillFluid()
		    .get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(CoreInit.BRINE.getFlowingFluid()
		    .get(), RenderType.translucent());

		ItemBlockRenderTypes.setRenderLayer(CoreInit.HOTSPRING.getStillFluid()
		    .get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(CoreInit.HOTSPRING.getFlowingFluid()
		    .get(), RenderType.translucent());

		ItemBlockRenderTypes.setRenderLayer(CoreInit.SPARKLING.getStillFluid()
		    .get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(CoreInit.SPARKLING.getFlowingFluid()
		    .get(), RenderType.translucent());

		ItemProperties.register(CoreInit.HARPOON_FLINT.get(), new ResourceLocation("minecraft", "throwing"),
		    (stack, level, living, i) -> (living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0F : 0.0F));

		ItemProperties.register(CoreInit.FISHING_ROD_STEEL.get(), new ResourceLocation("minecraft", "cast"), (stack, level, living, i) -> {
			if (living == null) {
				return 0.0F;
			} else {
				boolean flag = living.getMainHandItem() == stack;
				boolean flag1 = living.getOffhandItem() == stack;
				if (living.getMainHandItem()
				    .getItem() instanceof FishingRodItem) {
					flag1 = false;
				}
				return (flag || flag1) && living instanceof Player && ((Player) living).fishing != null ? 1.0F : 0.0F;
			}
		});

		ItemProperties.register(CoreInit.ALTIMETER.get(), new ResourceLocation("minecraft", "angle"), (stack, level, liv, i) -> {
			Entity entity = liv == null ? stack.getEntityRepresentation() : liv;
			return entity == null ? 0.0F : entity.blockPosition()
			    .getY() >= 192 ? 1.0F : Mth.clamp(
			        (entity.blockPosition()
			            .getY() + 64) / 256F,
			        0F, 1F);
		});
	}
}
