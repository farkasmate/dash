package y2k.dash.data

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migrations {
    class StringMigration(from: Int, to: Int, private vararg val sqls: String) : Migration(from, to) {
        override fun migrate(database: SupportSQLiteDatabase) {
            sqls.forEach { sql -> database.execSQL(sql) }
        }
    }

    companion object {
        val all = arrayOf(
                StringMigration(1, 2,
                        "ALTER TABLE Dashlet ADD COLUMN position INTEGER NOT NULL DEFAULT 0",
                        "UPDATE Dashlet SET position=rowid")
        )
    }
}