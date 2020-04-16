package y2k.dash.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Dashlet::class], version = 2)
abstract class DashletDatabase : RoomDatabase() {
    abstract fun dashletDao(): DashletDao

    companion object {
        @Volatile
        private var INSTANCE: DashletDatabase? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                    context,
                    DashletDatabase::class.java, "Dashlet"
            ).addMigrations(*Migrations.all).build()
        }
    }
}