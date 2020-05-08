package y2k.dash.shared

enum class DashletKeys(private val str: String) {
    URL("y2k.dash.dashlet.url"),
    TITLE("y2k.dash.dashlet.title"),
    MESSAGE("y2k.dash.dashlet.message"),
    POSITION("y2k.dash.dashlet.position");

    override fun toString(): String = str
}