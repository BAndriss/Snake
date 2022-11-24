package com.example.snake.scoredatamodel

import org.json.JSONObject
import java.util.*

data class ScoreModelData (
    var id: UUID? = UUID.randomUUID(),
    var name: String? = null,
    var point: Int? = null
) {

    constructor(jsonObject: JSONObject) : this(
        UUID.fromString(jsonObject.optString("id")),
        jsonObject.optString("name"),
        jsonObject.optString("point").toInt()
    )

    fun toJsonObject(): JSONObject {
        return JSONObject().put("id", id)
            .put("name", name)
            .put("point", point)
    }
}