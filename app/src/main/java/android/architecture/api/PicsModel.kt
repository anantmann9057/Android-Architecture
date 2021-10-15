package android.architecture.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pics")
data class PicsModel(

    @field:SerializedName("author")
    val author: String? = "",

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("download_url")
    val downloadUrl: String? = null,

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("height")
    val height: Int? = null
)
