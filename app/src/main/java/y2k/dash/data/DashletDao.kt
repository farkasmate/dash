package y2k.dash.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

@Dao
interface DashletDao {
    @Query("SELECT * FROM Dashlet ORDER BY position")
    fun getAll(): LiveData<List<Dashlet>>

    @Query("SELECT * FROM Dashlet WHERE position BETWEEN :start AND :end ORDER BY position")
    suspend fun getRange(start: Int, end: Int): List<Dashlet>

    @Query("SELECT COUNT(*) FROM Dashlet")
    suspend fun getNextPosition(): Int

    // TODO: IGNORE/ABORT?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dashlet: Dashlet)

    @Update
    suspend fun update(vararg dashlets: Dashlet)

//    @Delete
//    suspend fun delete(dashlet: Dashlet)

    @Query("DELETE FROM Dashlet")
    suspend fun dropDashlets()

    suspend fun swapDashlets(moving: Dashlet, other: Dashlet) {
        val from = moving.position
        val to = other.position

        moving.position = to
        other.position = from

        update(moving, other)
    }

    @Transaction
    suspend fun moveDashlet(from: Int, to: Int) {
        val start = min(from, to)
        val end = max(from, to)
        val forward = from < to

        val dashlets = getRange(start, end)

        if (dashlets.size != abs(from - to) + 1) {
            Log.e(this.toString(), "Moving wrong range of dashlets: $from..$to")
            return
        }

        dashlets.map { dashlet ->
            when {
                dashlet.position == from -> dashlet.position = to
                forward -> dashlet.position--
                else -> dashlet.position++
            }
        }

        update(*dashlets.toTypedArray())
    }
}