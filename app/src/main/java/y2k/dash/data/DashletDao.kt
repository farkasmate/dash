package y2k.dash.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DashletDao {
    @Query("SELECT * FROM Dashlet")
    fun getAll(): LiveData<List<Dashlet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dashlet: Dashlet)

    @Update
    suspend fun update(dashlet: Dashlet)

//    @Delete
//    suspend fun delete(dashlet: Dashlet)

    @Query("DELETE FROM Dashlet")
    suspend fun dropDashlets()
}