package y2k.dash.shared.data

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

        fun getInstance(context: Context): DashletDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DashletDatabase::class.java,
                        "Dashlet"
                ).addMigrations(*Migrations.all).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}