#### Heat&Climate Mod v4 Beta for Minecraft 1.19.2 ####

# 導入環境 / Introduction conditions

・Minecraft1.19.2
・MincraftForge 1.19.2-43.2.0

*Notice*

・HaCv4では、HeatAndClimateLibを必要としません。
 HaCv4 does not require HeatAndClimateLib.

・このmodはWIPであり、現在はベータバージョンです。導入前にテスト環境で試すことをおすすめします。
 This mod is WIP and is a beta version. We recommend testing in a test environment before deployment.

・マルチサーバーについては、起動確認のみ行い、作者によるデバッグテストが行われていません。
 For multi-server, only startup confirmation is performed, and no debugging test is performed by the author.

・このmodはJEIプラグインをサポートし、多くのゲーム内説明をJEIに依存しています。JEIの利用を強く勧めます。
 This mod supports JEI plugins and relies on JEI for many in-game informations. We strongly recommend using JEI.

# 配布場所 / Published links
 CurseForge: https://www.curseforge.com/minecraft/mc-mods/heat-and-climate
 Github: https://github.com/defeatedcrow/HeatAndClimateMod
 Author Wiki： http://defeatedcrow.jp/modwiki/HeatAndClimate

 # 概要 / Summary

　マインクラフトに『気候』を追加し、それを使って色々するmodです。
　また、気候や土地が持つ5色の『色』の魔法を使う、M:tGライクな魔法要素が続投されています。
 It is a mod that adds "climate" to Minecraft and uses it to do various fun things.
 In addition, M:tG-like magic elements that use the five "color" magic of the climate and land are continuing.

 主にオーバーワールドの地上部にコンテンツを多数追加し、プレイヤーの生活や冒険に役立つものを追加します。
 This mod mainly adds a lot of content to the above-ground part of the overworld to help players with their lives and adventures.
 一方、地下にはより高い効果を持つ希少な鉱物が追加されます。
 Meanwhile, rare minerals with higher effects will be added underground.
 HaCv4は、『地上で準備を整えて地下探索に挑む』というプレイスタイルの強化を目的としています。
 This mod aims to reinforce the play style of "prepare above ground and take on the challenge of underground exploration."

・『気候』について About "Climate"
　『気候』は温度・湿度・通気の3種類のパラメーターとして表され、金属や食べ物を焼いたり、生物にダメージを与えたり、作物の成長に影響したりします。
 "Climate" is expressed as three parameters: temperature, humidity and ventilation. This can burn metal and food, damage living things, and affect crop growth.

・『色』について About "Colors"
　バイオームや気候は『色』のマナと関連付けられ、バイオームに生成する『色』の鉱物や宝石に影響します。
　鉱物や宝石の色は、色の魔法のために使用されます。
 Biomes and climates are associated with "color" mana, which affects the "color" minerals and gems that generate in the biome.
 The colors of minerals and gems are used for color magic.

****************************************************************************************************

#### 遊び方 / How to play ####

◎ 気候で遊ぼう! / Climate!

●HUDについて / About HUD

　現在地の気候(Climate)は、プレイヤーのHUDに表示されます。
 The current climate is displayed on the player's HUD.

 HUDは、切り替えキー(デフォルト:左シフト）によって表示を切り替えできます。
 キー設定はコンフィグで変更可能です。
 The HUD can switch the display with the switch key (default: left-shift).
 Key settings can be changed in the config.

● HeatTier

　HeatTierは、このmodでの温度です。14段階のTierで表現されます。
 "HeatTier" is the temperature in this mod. It is defined by 14 tiers.

 バイオームの気温と、近くのブロックの持つ温度から計算されます。
 Calculated from the temperature of the biome and the temperature of nearby blocks.

 ・バイオームの場合 / About biomes

  1. バイオームの内部値(バニラの機能です)に基づき、基礎温度が決定されます。
     Base temperature is determined based on Biome's internal values (a vanilla feature).
  2. 季節による補正値、天候による補正値、夜間の気温低下値によって気温が変動します。
     The temperature fluctuates due to the influence of season, weather, and time zone correction values.
  3. 屋根の下にいると気温がNORMALに1段階近づきます。
     Staying under the roof brings the temperature one step closer to NORMAL.
　
・ ブロックの場合 / About blocks

  5x5x5範囲の熱源ブロックの温度の平均値を算出します。
  Calculate the average temperature of the heat source block in the 5x5x5 range.

  熱いブロックと、冷たいブロックは、互いの温度を相殺します。
  Hot and cold blocks offset each other's temperature.

 ・昇温ブロック / Heating Block

 温度調整のために役立つブロックが追加されています。
 HaC adds several heat source devices.

  - 加熱チャンバー / Heating Chamber

  通気がFLOW以上の場合に、燃料を消費してSMELTINGの温度を発生する熱源です。
  When the airflow is FLOW+, it consumes fuel and generates temperatures of SMELTING.
  ふいごを使用することでも、十分な通気を与えることが出来ます。
  You can also use bellows to provide sufficient Airflow.

  - レンガのかまど / Brick Stove

  通気がFLOW以上の場合に、燃料を消費してKILNの温度を発生する熱源です。
  When the airflow is FLOW+, it consumes fuel and generates temperatures of KILN.
  燃焼チャンバーより温度が低いかわりに、燃料がより長持ちします。
  It has a lower temperature and is more fuel efficient than a heated chamber.

● Humidity

 Humidityはこのmodでの湿度です。3段階のTierと、『水中』であるUNDERWATERの4段階で表されます。
 Humidity is the humidity in this mod. It is defined by 3 tier and "UNDERWATER".

 ・天候による変化 / About weather

  雨天時に、屋外の湿度が上昇します。
  Outdoor humidity increases during rainy weather.

 ・バイオームの場合 / About biomes

  WET biomes: バイオームの湿度内部値(バニラの機能です)が0.8Fより大きいか、バイオームがWETタグを持つ場合。
              If the biome's humidity internal value (which is a vanilla feature) is greater than 0.8F or the biome has a WET tag.

  DRY biomes: バイオームの湿度内部値が0.3F以下であるか、バイオームがDRYタグを持つ場合。
              If the biome's humidity internal value (which is a vanilla feature) is smaller than 0.3F the biome has a DRY or SPARSE tag.

 ・ブロックの場合 / About blocks

  3x3x3範囲のブロックの湿度の平均値を算出します。
  Calculate the average humidity of the heat source block in the 3x3x3 range.

● Airflow

 4段階のTierで定義されます。
 Defined by 4 tiers.

 ・天候による変化 / About weather

  雨天時に、屋外の通気が上昇します。
  Outdoor airflow increases during rainy weather.

 ・高度による変化 / About altitude

  Y135以上の高度では、屋外の通気が上昇します。
  At altitudes higher than Y135, outdoor airflow is increases.

 ・バイオームの場合 / About biomes

  WIND biomes: バイオームがMOUNTAINまたはHILLSタグを持つ場合。
              If the biome has a MOUNTAIN or HILLS tag.

 ・ブロックの場合 / About blocks

 　屋内はNORMALです。
  Indoors is NORMAL.

  3x3x3範囲の空気ブロックの数をカウントし、2以下の場合にTIGHTになります。
  It counts the number of air blocks in a 3x3x3 range and becomes TIGHT if it is 2 or less.


◎ 温度ダメージ / Heat and Cold Damage

 高温・低温は、プレイヤーを含むモブにダメージを与えます。
 Temperatures that are too hot or too cold will damage mobs, including the player.

 ダメージへの耐性はモブの種類によって異なります。(コンフィグで変更できます。)
 Resistance to damage depends on the type of mob. (This can be changed in the config.)

 ● ダメージの軽減 / Damage reduction

  布や革の防具は、気候ダメージを減少させます。
  Cloth and leather armor reduces climate damage.

  ・Heatダメージの軽減 / Heat damage reduction
   - 防具の耐火エンチャント Armor fire protection enchantment
   - 耐火ポーション fire resistance potion

  ・Coldダメージの軽減 / Cold damage reduction
   - HaCの耐寒ポーション cold resistance potion of HaC

 ● Hardmode WETの影響 / About WET hardmode config

 　ハードモードの"Humidity Effect"を有効化していると、プレイヤーが水に濡れる時に『ずぶ濡れ』ポーション効果を受けます。
 　『ずぶ濡れ』のとき、高温のダメージを軽減し、低温のダメージを増加させます。
  If the "Humidity Effect" in the hard mode config is enabled, the player will receive a "wet" potion effect when the player gets wet.
  When "wet", it reduces high temperature damage and increases cold temperature damage.

****************************************************************************************************

◎ HaCカスタムレシピ / HaC Custom Recipes

　HaCで追加されるカスタムレシピは、データパックで追加・編集できます。
　HaC Custom recipes can be added and edited with data packs.

　● 気候精錬 / Climate Smelting

 気候精錬は、ワールドに設置されたオブジェクトが変化するためのレシピです。
 "Climate Smelting" is a recipe for transforming objects placed in the world.

 レシピのとおりにブロックまたはエンティティを正しい気候の中に設置すると、ブロックやエンティティが変化します。
 Placing a block or entity in the correct climate as per the recipe will change the block or entity.

 これらは、あなたがHaC金属を精錬したり、肉を焼くために必要です。
 These are required for you to smelt HaC metals or grill meat.

 * 気候精錬レシピによりBlockを変化させる場合、材料となるBlockが Random Tick を持っている必要があります。
   If you add a new climate smelting recipe, the material block must be implement vanilla random tick behavior.

  ● マシンレシピ / Device Recipe

  マシンで使用されるすべてのレシピは、データパックに対応しています。
  All machine recipes can be added and edited with data packs.

****************************************************************************************************

◎ 鉱脈の生成 / Ore Veins

 5色の鉱脈と、5色のDeep鉱脈が地下に生成されます。
 5 color veins and 5 color deep veins are generated underground.

 鉱脈からは、それぞれの色の金属や宝石を入手できます。
 Ore veins provide each colored metals and gems.

  ・対応するバイオーム(Tag) / Corresponding biome (Tag)
   - White: PLAINS, SAVANNA
   - Blue: COLD, TAIGA, HILLS
   - Black: SWAMP, SPOOKY, WATER
   - Red: MOUNTAIN, SANDY, BADLANDS
   - Green: FOREST, LUSH, DENSE, JUNGLE

 鉱脈は、コンフィグファイルによって編集、削除、追加ができます。
 Veins can be edited, deleted, and added using the config file.

****************************************************************************************************

◎ 新しい農業要素 / New Agriculture

 v1.12とは大きく異なる新要素です!
 It is a new element that is very different from v1.12!

 ● 作物の栽培 / Cultivation

 　HaC作物は、適切な気候の環境に植えることで早く成長します。
 　気候が合わない環境では、成長が遅くなります。
  HaC crops grow faster when planted in the right climate.
  Growth slows down in unfavorable climates.

  ・ バニラ作物への影響 / About vanilla crops
   - WET湿度でバニラ耕地が湿ります。
     Vanilla farmland can also be wetted with WET humidity.
   - バニラ作物は、WARM ~ HOT + WETの環境で成長が促進され、COOL-の低温で成長が遅くなります。
     Vanilla crops grow faster in a WARM ~ HOT + WET environment and slower in a COOL- lower temperature.

 ・ 野生の作物を探そう / Look for wild crops!

 　それぞれのバイオームには、固有の『WILD作物』が自然生成します。
 　Different biomes generate different types of "WILD crops".

  ・WILD作物とは? / What are wild crops?
   - 一度だけ採集できます。 Can be collected only once.
   - 耕地以外のブロックの上でも成長できます。(適切な土壌は、JEIで確認できます。)
     Can grow on blocks other than farmland. (Suitable soils can be checked with JEI.)
   - 『施肥した耕地』の上に種や苗木を植えると、よりレアな作物に変異する可能性があります。
     Sowing seeds or saplings on "fertilized farmland" may mutate into a rarer crop.

 ・ 作物の変異 / Crop Mutation

 　・施肥された耕地 / Fertilized farmland
   - 耕地ブロックに肥料を与えると、『施肥された耕地』に変わります。3回まで施肥できます。
     Fertilizing a farmland block turns it into a "Fertilized Farmland". You can fertilize up to 3 times.
   - バニラの骨粉や、HaCで追加される肥料を使用できます。
     You can use vanilla bonemeal or HaC fertilizer.
   - 『緑肥』のTagを持つ作物をクワで右クリックすることでも、施肥された耕地を作ることが出来ます。
     It can also be created by right-clicking a plant with the "GREEN_MANURE" tag with a hoe.

  ・施肥された耕地にWILD作物を植えると、レア度の高い作物に変化します。
  　施肥の回数が多いと、レアな作物の出現率が少し上昇します。
   Planting WILD crops on fertilized farmland will transform them into rarer crops.
   The more often you fertilize, the more rare crops appear.

 ・ 樹木 / Trees

   樹木は、花期と収穫期が季節によって制限されます。
   Trees are seasonally restricted in their flowering and harvesting periods.

   適さない気温では、花や作物が得られなくなります。
   At unfavorable temperatures, the leaves of the tree will not bear flowers or fruits.

 ● 釣り / Fishing

  バニラの魚が釣れたときに、差し替える形でHaCの追加魚種をスポーンさせます。
  When Vanilla Fish is caught, HaC Fishes may be obtained instead.

  釣りには、バイオーム、気温、時刻が影響します。日の出・日没の時間帯は珍しい魚種が10%釣れやすくなります。
  Fishing is affected by biome, temperature, and time of day.
  During sunrise and sunset times, the chance of rare fishes increases by 10%.

  HaC魚が水中にドロップして5秒経過すると消滅し、魚が逃げます。
  魚を逃した場合、時々周囲のPlayerにLuckのポーション効果を与えます。
  HaC fish items will disappear after 5 seconds have passed after being dropped into water. The fish runs away.
  If you release a HaC fish, it will sometimes give a Luck potion effect to nearby players.

  釣りで得られる魚種はTagによって編集できます。
  Fish obtained by fishing can be edited by tag.
    -> dcs_climate:fishes

 ● HaCの食べ物 / HaC Foods

  HaCの食べ物は、スニークしながらブロックを右クリックすることで、Entityとしてワールドに設置できます。
  HaC's food can be placed in the world as an Entity by right-clicking on a block while sneaking.

  気候精錬のレシピを持つ食べ物は、適切な気候の環境に、生の状態で設置することで調理します。
  Foods with climate refinement recipes are cooked by placing them raw in an environment with the correct climate.

 ・ 食べ物の『風味』 / Flavor of food

   『風味』は、5段階の★で定義されます。
   "Flavor" is defined on a 5-point scale.

   『風味』は、プレイヤーがアイテムを食べる時の速度に影響します。
   Flavor affects the speed at which the player eats items.

   料理の風味は、材料にした食材によって変動します。
   The flavor of a dish varies depending on the ingredients.

   調味料をクラフトすると、風味が良くなります。
   Crafting seasonings improves their flavor.

 ・ 食べ物の安全性 / Unsafe Food Tag

   HaCは、プレイヤーが任意のアイテムを追加できる 『dcs_climate:unsafe_foods』 のタグを追加します。
   HaC adds "dcs_climate:unsafe_foods" tag that allows players to add any item they want.

   タグで指定したアイテムが食べ物アイテムの作成工程に混入した場合、アイテムのツールチップに『安全ではありません』と表示されます。
   If the item specified by the tag is mixed into the food crafting process, the message "UNSAFE" will appear in the food items tooltip.

   プレイヤーに食べさせたくない食材を自由に指定してください。
   Please feel free to specify the ingredients you do not want players to eat.

   * 安全ではないと表示された食べ物を食べることにデメリットはありません。フレーバーのための機能です。
   * There is no downside to eating food labeled as unsafe. This is a function for flavor.

   * バニラかまどで調理すると、表示が消えてしまいます。
   * The message disappears when cooking items in the Vanilla Furnace.

　● 色々な調理デバイス / Devices for ingredients

　・　鍋 / Cooking Pot

　　 環境の熱を利用して、食べ物を調理します。
   Cooking Pots use ambient heat to cook food.

   スニーク+右クリックでフタを開閉します。
   Sneak + right-click to toggle the pot lid.

 ・　ティーポット / Tea Pot

　　 環境の熱を利用して、飲み物を調理します。
   Tea Pots use ambient heat to make drink.

   飲み物は飲用時にポーション効果を発揮します。
   Drinks have potion effects.

 ・　壺 / Fermentation Jar

　　適切な環境(温暖な屋内)に置かれている時、食べ物を発酵させて新しい食材を得ます。
   When the jar is placed in the right environment (warm, humid, indoors), it ferments the ingredients to obtain new food.

   Input Tank に水が入っている時、花瓶のように、壺の上に花や作物の苗を設置できます。
   When the input tank has water, flowers and crops can place on top of it, like a vase.

 ・　樹液採集カップ / Spile and Cup

　　 樹液のある原木に設置すると、樹液を集めることが出来ます。
   When attached to a log with sap, it can collect sap.

   樹液のある原木は、以下のTagによって追加できます。
   Logs with sap are added by following Tag
     -> dcs_climate:logs_can_collect_sap
         - lacquer
         - latex
         - resin
         - sweet

****************************************************************************************************

◎ 色の魔法 / Color Magic

  宝石や作物から『色の魔法の素』を取り出し、魔法のアイテムの素材に使用します。
  Color extracts are extracted from crops and gems, and used as a material for magic items.

  コンフィグで魔法ハードモードが有効化されている場合、カードやアクセサリーの魔法にはコスト（EXP）を支払う必要があります。
  If Magic Hard Mode is enabled in config, player must pay a cost (EXP) to use a card or jewel.

　● 魔法の矢 / Magical Arrow

　　バニラの弓やクロスボウで撃てる魔法の矢です。
　　They are magic arrows that can be shot with vanilla bows and crossbows.

　● 魔法のカード / Magical card

　　使用することでいろいろな効果を発揮する消費アイテムです。
　　Magic cards have instant magical effects.

　● 魔法のアクセサリー / Magical Jewels

  プレイヤーインベントリの最上段に置かれているとき、効果を発揮します。
  Effect activates when placed in the top row of the player inventory.

  プレイヤー以外のモブの場合は、インベントリのどこかに置かれていれば効果があります。
  About non-player mobs, it can be anywhere in inventory instead.

  一部のジュエルはプレイヤーにのみ効果があります。
  Some jewels only affect the player.

****************************************************************************************************

◎ 電気とマシン / Electricity and Machines

 HaCv4では、マシンの動力に電気を使用します。
 HaCv4 uses electricity to power the machine.

 電気はForgeFEと互換性があります。
 Electric is compatible with ForgeFE.

 ● 電気機器の共通仕様 / Common specifications for electrical equipment

  電気機器は6面それぞれに接続状態を設定できます。接続状態はドライバーアイテムを使って変更できます。
  The connection status of electrical devices can be set for each of the six sides.
  The connection status can be changed using a screwdriver.

   - OUTLET: 送信のみ。 Outlet Only.
   - INPUT: 受信のみ。 Input Only.
   - PATH: 容量の空きが大きい方へ流れます。 The flow goes to those with more capacity.
   - NONE: 送受信の停止。 Stop sending and receiving.

   PATHモードはケーブルと充電池ブロックのみが持つモードです。
   Only cables and battery blocks have PATH mode.

  電気機器はレッドストーン信号によって停止します。また、一部のブロックはGUIにも主電源スイッチがついています。
  Electrical equipment is suspended by a redstone signal.
  Some blocks also have a main power switch on the GUI.

  電気機器ブロックは設置したプレイヤーをOwnerとして登録します。GUIロックすると、OwnerとサーバーOPのみが開けられるよう制限されます。
  The machine block registers the player who installed it as the owner.
  When the GUI is locked, only the Owner and server OP can open it.

 ● 発電 / Power Generation

 - 発電機 / Generator

   『発電機』に『動力源』ブロックを隣接させ、『動力源』から動力を与えることで電気を生み出します。
   Produce electricity by placing a "power source" block adjacent to the "generator" and applying power from the "power source".

   『発電機』は複数の『動力源』の動力を受け取れます。
   A "generator" can receive power from multiple "power sources".

   『動力源』は隣接する『発電機』のいずれか一つのみに動力を供給します。
   A "power source" supplies power to only one of the adjacent "generators".

  - バニラ避雷針 / vanilla Lightning Rod

   バニラの避雷針に落雷したとき、避雷針の下にある電気機器に 1000FE の電力を供給します。
   When a lightning rod in vanilla is struck by lightning, it supplies 1000FE to the electric machine under the lightning rod.

 ● 送電ケーブル / Cable

  電気を送るためのブロックです。
  A block for transmitting electricity.

  ケーブルのTierに応じた送電速度に制限があります。現在は　Tier1 (32FE/t) のみ実装されています。
  There is a limit to the power transmission speed depending on the cable tier.
  Currently only Tier1 (32FE/t) is implemented.

  むき出しのケーブルにはリスクがあります。ゴムで被覆することで安全に扱えます。
  Exposed cables are dangerous. Covering it with rubber makes it safe to handle.

   - 水や雨に触れると漏電が発生し、電力を消失します。 Water and rain can cause electrical leakage, resulting in power loss.
   - 活線に触れるとダメージが発生します。 Touching a live cable will cause damage.

 ● 充電池ブロック / Battery Block

  一定量の電気を貯められるブロックです。
  A block that can store electricity.

 ● 電気機器 / Electric Machines

  電気を消費して動作するマシンブロックです。
  Machines that consume electricity to operate.

  ・ 電動石臼 / Electric Stone Mill

   素材を粉砕して、加工物や副産物を得られる加工機です。
   A processing machine that crushes materials to obtain products and by-products.

  ・ 水中ポンプ / Underwater Pump

   250FEを消費して、接触している流体ブロックを回収するマシンです。
   Spend 250FE and collect adjacent fluid blocks.

   流れブロックに接しているとき、8ブロック先まで流れを辿り、源ブロックを回収します。
   When touching a flow block, follow the flow up to 8 blocks ahead and collect the source block.

   水ブロックに投入した場合、水源ブロックの回収を行わず、無限水源として扱います。その場合、最大32FE/tの速度で水を汲み上げます。
   If it's in water source, it do not collect source block. In that case, it pumps water at a rate of up to 32FE/t.

   汲み上げた流体は、真上にパイプなどをつなげた場合、水頭 6ブロック で送られます。
   If you connect a pipe directly above the pumped fluid, it will be sent with a water head of 6 blocks.

****************************************************************************************************

◎ 流体の扱い / Fluid Handling

 ● 流体タンクアイテム / Fluid Tank Items

  ・ ヒョウタンの水筒 / Calabash Water Bottle : 1000 mB

  　バケツのように水源を設置できます。
   Can place a fluid source like a bucket.

   300°C (573K) 以上の流体は入れられません。
   No fluids above 300°C (573K) are allowed.

 ● 流体タンク / Fluid Tank Blocks

 　一定量の流体を貯められるタンクです。
  Tanks that can store fluid.

  ・ ポリタンク / Portable Can : Max 18 Bucket
  ・ IBC : Max 1000 Bucket

 ● パイプ / Brass Pipe

  重力を利用して流体を輸送するブロックです。
  Transports fluid using gravity.

  流体は高さに応じた『水頭』を得て、同じ高さまで輸送することが出来ます。
  The fluid has a "water head" that corresponds to its height, and can be transported to the same height.

  6面それぞれに接続状態を設定できます。接続状態はドライバーアイテムを使って変更できます。
  The connection status of pipes can be set for each of the six sides.
  The connection status can be changed using a screwdriver.

   - OUTLET: 吐出のみ。 Outlet Only.
   - INPUT: 受け入れのみ。 Input Only.
   - PATH: 容量の空きが大きい方へ流れます。 The flow goes to those with more capacity.
   - NONE: 送受信の停止。 Stop sending and receiving.

   INPUTモードは、液体タンクや、水の入った大釜から流体を取り出すことが出来ます。
   INPUT mode takes fluid from a fluid tank or cauldron filled with water.

****************************************************************************************************

◎ アイテムの扱い / Item Handling and Inventory

 ● 特殊チェスト / Special Chest

  Owner登録機能や、ドロップ時の内容保持機能があるインベントリブロックです。
  Special chests register their owners or retain their contents when dropped.

  追加チェストは設置したプレイヤーをOwnerとして登録します。GUIロックすると、OwnerとサーバーOPのみが開けられるよう制限されます。
  The special chest registers the player who installed it as the owner.
  When the GUI is locked, only the Owner and server OP can open it.

  サーバーOPのみ、プレイヤー名をつけたネームタグを使用することで、ブロックのOwnerを強制的に上書きできます。
  The server OP can forcefully override a block's owner by using a name tag with the player's name.

   - キャリーバッグ / Luggage... Owner Register ○ : Inventory Retention ○
   - ロッカー / Locker ... Owner Register ○ : Inventory Retention ○
   - キャビネット / Cabinet ... Owner Register ○ : IInventory Retention ×

 ● 特殊ホッパー / Special Hopper

  こちらもOwner登録機能や、ドロップ時の内容保持機能があるインベントリブロックです。
  Special hoppers also register their owners or retain their contents when dropped.

  スクリュードライバーを使うと、ブロックの向きを変更できます。
  The screwdriver can change the direction of the block.

   ・ フィルター付き / Filter Hopper

    『フィルタースロット』に入れたアイテムは水平方向に、それ以外を上下方向に輸送する二叉ホッパーです。
    The filter hopper is a two-pronged hopper that transports items placed in the filter slot horizontally and other items vertically.

    フィルタースロットには常に1個のアイテムが残ります。
    One item always remains in the filter slot.

    スタック出来ないアイテムを『フィルタースロット』に入れると、それはブロックされます。
    If you put a non-stackable item into a "filter slot", it will be blocked.

   ・ 金のホッパー / Golden Hopper

    輸送速度が速いホッパーです。 1個/tick の速度でアイテムを輸送します。
    A hopper with fast transportation speed. Transports items at a rate of 1 item/tick.

****************************************************************************************************

#### 更新履歴 / Change log ####

○v4-beta7
 add: パスタ
 add: いくつかの麺料理
 add: 野菜とコーンの串焼き
 add: 椿とイジュの花
 change: 金の緑ペンダント、銀の緑リングの効果調整
 change: ケーキのモデルが大きすぎた
 change: スニーク中はEntity食べ物の上にEntity食べ物を設置できる
 change: Entity食べ物のマウスオーバーで正しい名前が表示されるように
 fix: 追加ドアのドロップアイテムを修正

○v4-beta6 (2024.5.25)
 add: カーペット付き木材
 add: 木製テーブル
 add: 木製イス
 add: ドアとトラップドア
 add: モルタルと土レンガの建材ブロック
 add: アーモンド
 add: カレー
 add: 煮込み料理
 add: 刺身
 add: ゴムと毛皮の防具
 add: 火打ち石の大鎌
 add: タグによる食材のUNSAFE指定機能
 change: いくつかの乳鉢レシピを見直した
 change: プレート料理のアイコンを描き直した
 change: 実績を増やした
 fix: アルミ階段(色)のレシピを修正
 fix: キャベツのスペルミス

○v4-beta5 (2024.5.10)
 fix: 黒白のカードのアイテム名
 fix: FuelRecipe登録時のnullエラーを修正
 fix: WTHITの対応バージョンを5.19.3に落とした

○v4-beta4 (2024.5.1)
 add: 特殊ホッパー
 add: RSインジケーター
 add: RSパイロットランプ
 add: リノリウムブロック (16色)
 add: モルタルブロック
 add: アルミの屋根のカラーバリエーション
 change: BlockEntityのインターバルを調整
 change: ネームタグを使ってサーバーOPがOwnableTileのOwnerを強制変更する機能を追加
 fix: DataPackのロード時のクラッシュ

○v4-beta3 (2024.4.15)
 add: 発電機
 add: 充電池ブロック
 add: エネルギーケーブル
 add: 液体パイプ
 add: 水中ポンプ
 add: 土半ブロック
 add: 日干しレンガ
 add: 加硫ゴム
 change: 自動石臼にはエネルギーが必要になった
 change: 地底の通気をTIGHTにするハードモードの追加
 change: 通気TIGHTのときに窒息効果が発生するハードモードを追加
 change: 鉱脈追加コンフィグの登録処理を改善
 change: 行商人に取引内容を追加
 change: 魔法アイテムに使用時のメッセージ表示を追加
 fix: 雪が自然に溶けないのを修正
 fix: 追加池の最低半径が小さすぎた
 fix: 調味料レシピのdupeを修正
 fix: リネンのパンツの浅色レシピを修正
 fix: 発酵壺GUIのプログレスバーの描画
 fix: 赤緑のカードが本来破壊できないブロックまで破壊してしまうのを修正
 fix: テクスチャがロードされていないEntityの修正(日本酒、ジュース)

○ v4-beta2 (2024.2.7)
 add: 植物肉
 add: 焼き芋
 add: 串焼き魚
 change: 食べ物に調味料をクラフトして味を良くする機能を追加
 change: HaCの料理アイテムに専用タグを用意した
 fix: ティーポットの内部エラーを修正
 fix: コバルトインゴットが作れない
 fix: ホイップクリームのレシピがない
 fix: 年の開始時の季節がうまく動作しないため、設定コンフィグを追加
 fix: 配合肥料の動作を修正

○ v4-beta1 (2014.1.30)
 add: ティーポット
 add: 飲み物
 add: スープ、タルト、キャセロール
 add: フトモモ科の樹木
 add: いくつかの食材アイテム
 add: 茶葉
 add: 蚊取り線香
 add: 魔法のバッジ
 add: マルチカラー魔法カード
 add: 色の結晶
 change: RAREとEPICの作物は、収穫時に少しだけ経験値を得られるようになった。
 change: 鉱脈生成コンフィグは、2つまで任意の色を指定できるようになった。
 change: 魔法ブーストアイテムによるカード魔法の強化
 change: アイテムタグを色々増やした
 change: 赤の魔法による爆発は、プレイヤーを巻き込まないようになった
 fix: 大鎌で羊の毛刈りができないのを修正。
 fix: 遮光・発光ガラスブロックが空のアイテムをドロップする問題の修正
 fix: 漬物が食べられない問題の修正
 fix: 生成される池のサイズを少し小さくした
 fix: HaC樹木の苗は村の内部に生成されづらくなった

○ v4-alpha13 (2023.12.8)
 add: アルファ版石臼
 add: 真鍮・鋼のツール
 add: アルミニウム製の建材
 change: 炭酸泉のレイヤーブロックを苔丸石に変えた
 change: 鉱脈生成コンフィグの登録処理を変更し、いくつも増やせるようにした
 fix: 魔法のカードの作成枚数を修正
 fix: マシンレシピの材料アイテムの空容器がOutputスロットに返却されない
 fix: 作物が海底に生えるのを修正

○ v4-alpha12 (2023.11.25)
 add: 原木の圧縮ブロック
 add: バニラ作物の圧縮ブロック
 add: 壁掛けフック
 add: ヒョウタンの水筒
 add: 花椒
 add: ご飯
 add: 少々の醸造酒
 add: 色々な食材
 change: FluidTankBlockは、アイテム状態でも液体容器として機能するようになった
 change: 鍋で料理が完成した時、鍋に中身を表示する機能を追加
 change: 緑肥、肥料アイテムをバニラのコンポストに対応させた
 fix: ピスタチオの木の成長
 fix: 大型の樹形を調整した
 fix: 村の周囲にはHaC樹木が生成されないように

○ v4-alpha11 (2023.11.12)
 add: 追加魚種
 change: 釣りEventを追加
 fix: Server起動時のクラッシュを修正
 fix: HUD時計とゲーム内時刻がズレている
 fix: マコモが2段に成長しない
 fix: GUI上でShift-clickを使用した際のクラッシュ

○ v4-alpha10 (2023.11.11)
 add: 鍋
 add: 壺
 add: 樹液採取容器
 add: 作物7系統追加(ウリ、ブドウ、アヤメ、ラン、キンポウゲ、バラ、ウルシ)
 add: ハイビスカス追加
 add: 漆塗りの木材
 add: 追加液体5種
 add: 樹液4種
 add: 家畜の追加ドロップ4種
 add: 自然生成の特殊泉4種を追加
 change: マシンレシピの追加およびデータパック対応
 change: 毒のある作物に有毒の表示を付け、食べられないようにした
 change: 一部の家畜を倒した時のドロップを追加し、Lootingレベルに対応
 change: 作物、泉の生成率を設定するコンフィグの追加
 change: COMMON作物の自然生成を有効化するコンフィグを追加
 change: ツル型、着生型の作物のベースクラス追加
 fix: クアーリーなどでブロックを破壊した時のクラッシュを修正
 fix: 一部アイテムの翻訳ミスを修正
 fix: 一部の追加原木を木材にするレシピがない
 fix: コンフィグのShow Additional Tooltipsが機能しない問題を修正

○ v4-alpha9 (2023.9.23)
 fix: サーバー起動時のクラッシュを修正

○ v4-aipha8 (2023.9.23)
 add: 加熱チャンバー、レンガのかまど
 add: ふいご
 add: キャビネット、ロッカー、キャリーケース(各6色)
 add: ポリタンク(6色)
 add: IBCタンク
 fix: 追加アイテムのツールチップから重複するTagリストを削除
 fix: 葉の圧縮ブロックのドロップアイテムを修正
 fix: HUDの温度表示がSMELTING以上のゲージを表示できないのを修正

○ v4-alpha7 (2023.8.2)
 add: 圧縮した葉ブロック
 add: 腐葉土
 change: 大鎌に作物や葉ブロックの範囲破壊機能を追加
 fix: 葉ブロックが崩壊してしまう

○ v4-alpha6 (2023.7.23)
 add: 宝石のシャンデリア
 add: 金の鎖
 add: ケーキ 4種
 fix: ブロックを採掘した際のクラッシュを修正
 fix: 金属ブロックを右クリックで回収する際の動作を修正
 fix: 季節が正常に進行しない
 fix: 葉ブロックをハサミで回収できない

○ v4-alpha5 (2023.4.17)
 add: 魔法のペンダント
 change; 鳥・魚のポーション効果にそれぞれ採掘速度上昇効果をつけた
 fix: サーバーサイドでのクラッシュを修正
 fix: 白のリネンズボンのテクスチャ

○ v4-alpha4 (2023.4.12)
 add: 布素材
 add: 服飾要素
 add: 魔法の種袋
 add: 5色の魔法のリング
 add: 上位魔法カード
 change: 銀のTierを1に下げた(KILNで精錬可能)
 change: ロジェリア(ゴマ科)がバッドランドなどの赤砂にも生成できるようにした
 change: 鋼の銛の攻撃力を上方修正した
 change: 気候精錬レシピをデータパックに対応させた
 fix: ピーマンとパプリカのテクスチャが逆になっている

○ v4-alpha3 (2023.3.22)
 add: 魔法のカードの一部
 change: レシピjsonのバージョン更新機能をつけた
 change: デバッグパスワードを設定
 change: 果樹が気候の合わない環境で枯れるように
 fix: Pedariaの作物のモデルを修正
 fix: 火打ち石の銛が使用時に消えてしまう
 fix: HUDのBiome名表示が翻訳されない
 fix: 黒の矢のDupeバグの修正
 fix: 赤の矢をエンティティに当てると爆発しないのを修正

○ v4-alpha2 (2023.3.12)
 add: 魔法の素材
 add: 魔法の矢
 add: キク科の作物
 add: 玉髄のランプ
 add: 石英ガラス
 add: 銛
 add: 宝石ふるい(クラフトツール)
 add: 実績の一部を先行で追加
 change: 風味による食べ物の食事速度により大きな差をつけた
 change: 肥料の入手手段を増やした
 change: 乳鉢と宝石ふるいのレシピを色々増やした
 change: Tagのいろいろな入れ替え
 fix: en_us言語ファイルを入れ忘れた
 fix: スイカのサラダのテクスチャ
 fix: 樹木の生成の形を少し直した
 fix: 一部作物のJEI図鑑情報が欠けていた
 fix: ジャングルなどの草木の多いバイオームで、作物の生成率が低くなっていた

○ v4-alpha1 (2023.2.26)
 - 初回公開
