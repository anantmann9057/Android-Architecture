package android.architecture.api

import com.google.gson.annotations.SerializedName

data class Response(

    @field:SerializedName("id")
    var id: Int? = null,

    @field:SerializedName("title")
    var title: String? = null,

    @field:SerializedName("body")
    var body: String? = null,

    @field:SerializedName("userId")
    var userId: Int? = null
)
