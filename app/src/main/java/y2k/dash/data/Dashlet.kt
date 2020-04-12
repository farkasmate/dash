package y2k.dash.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dashlet(
        @PrimaryKey var url: String,
        @ColumnInfo var title: String,
        @ColumnInfo var message: String
)