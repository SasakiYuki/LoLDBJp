package application.n.yuki.loldbjp.type

/**
 * Created by yuki.n on 2017/04/20.
 */
enum class ChampionType(val string: String, val japaneseName: String) {
    All("all", "すべて"),
    MAGE("Mage", "メイジ"),
    ASSASSIN("Assassin", "アサシン"),
    FIGHTER("Fighter", "ファイター"),
    MARKSMAN("Marksman", "マークスマン"),
    TANK("Tank", "タンク"),
    SUPPORT("Support", "サポート")
}