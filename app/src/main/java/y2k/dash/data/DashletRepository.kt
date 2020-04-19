package y2k.dash.data

class DashletRepository(private val dao: DashletDao) {
    fun getAll() = dao.getAll()

    suspend fun moveDashlet(from: Int, to: Int) = dao.moveDashlet(from, to)
    suspend fun update(vararg dashlets: Dashlet) = dao.update(*dashlets)
    suspend fun dropDashlets() = dao.dropDashlets()

    suspend fun insert(dashlet: Dashlet) {
        dashlet.position = dao.getNextPosition()
        dao.insert(dashlet)
    }
}