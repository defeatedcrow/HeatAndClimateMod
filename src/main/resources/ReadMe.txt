#### Heat&Climate Mod v4 Early-Alpha for Minecraft 1.19.2 ####

# 導入環境 / Introduction conditions

・Minecraft1.19.2
・MincraftForge 1.19.2-43.2.0

*Notice*

・HaCv4では、HeatAndClimateLibを必要としません。
 HaCv4 does not require HeatAndClimateLib.

・このmodはWIPであり、とても不安定なアルファバージョンです。導入前にテスト環境で試すことをおすすめします。
 This mod is WIP and is a very unstable alpha version. We recommend testing in a test environment before deployment.

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

・『気候』について About "Climate"
　『気候』は温度・湿度・通気の3種類のパラメーターとして表され、金属や食べ物を焼いたり、生物にダメージを与えたり、作物の成長に影響したりします。
 "Climate" is expressed as three parameters: temperature, humidity and ventilation. This can burn metal and food, damage living things, and affect crop growth.

・『色』について About "Colors"
　バイオームや気候は『色』のマナと関連付けられ、バイオームに生成する『色』の鉱物や宝石に影響します。
　鉱物や宝石の色は、色の魔法のために使用されます。
 Biomes and climates are associated with "color" mana, which affects the "color" minerals and gems that generate in the biome.
 The colors of minerals and gems are used for color magic.


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

● Humidity

 Humidityはこのmodでの湿度です。3段階のTierと、『水中』であるUNDERWATERの4段階で表されます。
 Humidity is the humidity in this mod. It is defined by 3 tier and "UNDERWATER".

 ・天候による変化 / About weather

  雨天時に、屋外の湿度が上昇します。
  Outdoor humidity increases during rainy weather.

 ・バイオームの場合 / About biomes

  WET biomes: バイオームの湿度内部値(バニラの機能です)が0.8Fより大きいか、バイオームがWETタグを持つ場合。
              If the biome's humidity internal value (which is a vanilla feature) is greater than 0.8F or the biome has a WET tag.

  DRY biomes: バイオームがDRYまたはSPARSEタグを持つ場合。
              If the biome has a DRY or SPARSE tag.

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

 ● 昇温ブロック / Heating Block

 気候精錬レシピのために役立つブロックが追加されています。
 HaC adds several heat source devices.

  ・加熱チャンバー / Heating Chamber

  通気がFLOW以上の場合に、燃料を消費してSMELTINGの温度を発生する熱源です。
  When the airflow is FLOW+, it consumes fuel and generates temperatures of SMELTING.
  ふいごを使用することでも、十分な通気を与えることが出来ます。
  You can also use bellows to provide sufficient Airflow.

  ・レンガのかまど / Brick Stove

  通気がFLOW以上の場合に、燃料を消費してKILNの温度を発生する熱源です。
  When the airflow is FLOW+, it consumes fuel and generates temperatures of KILN.
  燃焼チャンバーより温度が低いかわりに、燃料がより長持ちします。
  It has a lower temperature and is more fuel efficient than a heated chamber.


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

 ● 野生の作物を探そう / Look for wild crops!

 　それぞれのバイオームには、固有の『WILD作物』が自然生成します。
 　Different biomes generate different types of "WILD crops".

  ・WILD作物とは? / What are wild crops?
   - 一度だけ採集できます。 Can be collected only once.
   - 耕地以外のブロックの上でも成長できます。(適切な土壌は、JEIで確認できます。)
     Can grow on blocks other than farmland. (Suitable soils can be checked with JEI.)
   - 『施肥した耕地』の上に種や苗木を植えると、よりレアな作物に変異する可能性があります。
     Sowing seeds or saplings on "fertilized farmland" may mutate into a rarer crop.

 ● 作物の変異 / Crop Mutation

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

 ● 樹木 / Trees

   樹木は、花期と収穫期が季節によって制限されます。
   Trees are seasonally restricted in their flowering and harvesting periods.

   適さない気温では、花や作物が得られなくなります。
   At unfavorable temperatures, the leaves of the tree will not bear flowers or fruits.

◎ 釣り / Fishing

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

◎ HaCの食べ物 / HaC Foods

 HaCの食べ物は、スニークしながらブロックを右クリックすることで、Entityとしてワールドに設置できます。
 HaC's food can be placed in the world as an Entity by right-clicking on a block while sneaking.

 気候精錬のレシピを持つ食べ物は、適切な気候の環境に、生の状態で設置することで調理します。
 Foods with climate refinement recipes are cooked by placing them raw in an environment with the correct climate.

 ● 食べ物の『風味』 / Flavor of food

   『風味』は、5段階の★で定義されます。
   "Flavor" is defined on a 5-point scale.

   『風味』は、プレイヤーがアイテムを食べる時の速度に影響します。
   Flavor affects the speed at which the player eats items.

   料理の風味は、材料にした食材によって変動します。
   The flavor of a dish varies depending on the ingredients.

   * 将来的には、調味料によって風味を良くする機能が実装される予定です。
     In the future, we plan to implement seasoning to enhance the flavor.

◎ 色々な調理デバイス / Devices for ingredients

　●　鍋 / Cooking Pot

　　 環境の熱を利用して、食べ物を調理します。
   Cooking Pots use ambient heat to cook food.

   スニーク+右クリックでフタを開閉します。
   Sneak + right-click to toggle the pot lid.

 ●　壺 / Fermentation Jar

　　 適切な環境(温暖な屋内)に置かれている時、食べ物を発酵させて新しい食材を得ます。
   When the jar is placed in the right environment (warm, humid, indoors), it ferments the ingredients to obtain new food.

   Input Tank に水が入っている時、花瓶のように、壺の上に花や作物の苗を設置できます。
   When the input tank has water, flowers and crops can place on top of it, like a vase.

 ●　樹液採集カップ / Spile and Cup

　　 樹液のある原木に設置すると、樹液を集めることが出来ます。
   When attached to a log with sap, it can collect sap.

   樹液のある原木は、以下のTagによって追加できます。
   Logs with sap are added by following Tag
     -> dcs_climate:logs_can_collect_sap
         - lacquer
         - latex
         - resin
         - sweet

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


#### 更新履歴 / Change log ####

○ v4-alpha11 (2023.11.12)
 add: 追加魚種
 change: 釣りEventを追加
 fix: Server起動時のクラッシュを修正
 fix: HUD時計とゲーム内時刻がズレている

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
