package y2k.dash.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DashletDao {
    @Query("SELECT * FROM Dashlet ORDER BY position")
    fun getAll(): LiveData<List<Dashlet>>

    // TODO: IGNORE/ABORT?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dashlet: Dashlet)

    @Update
    suspend fun update(dashlet: Dashlet)

//    @Delete
//    suspend fun delete(dashlet: Dashlet)

    @Query("DELETE FROM Dashlet")
    suspend fun dropDashlets()

    suspend fun swapDashlets(moving: Dashlet, other: Dashlet) {
        val from = moving.position
        val to = other.position

        moving.position = to
        other.position = from

        update(moving)
        update(other)
    }
}