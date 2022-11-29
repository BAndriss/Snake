package com.example.snake.save

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.nio.charset.StandardCharsets
import kotlin.collections.ArrayList

class SaveScoreData {

    companion object {
        private var file_name = "score.json"

        private fun JSONArray.toList(): ArrayList<JSONObject> {
            val result = ArrayList<JSONObject>()
            for (i in 0 until this.length()) {
                result.add(this.getJSONObject(i))
            }
            return result
        }

        private fun loadFile(context: Context, fileName: String): File {
            val file = File(context.filesDir, fileName)
            if (!file.exists()) {
                file.createNewFile()
            }
            return file
        }

        fun loadAllScore(context: Context): ArrayList<ScoreModelData> {
            val scoreFile = loadFile(context, file_name)
            var scoreFileContent = scoreFile.readText(StandardCharsets.UTF_8)
            scoreFileContent = scoreFileContent.ifBlank { "[ ]" }

            val scoreJson = JSONArray(scoreFileContent).toList()
            return scoreJson.map { ScoreModelData(it) } as ArrayList<ScoreModelData>
        }

        fun saveLocationModel(context: Context, scoreModelData: ScoreModelData) {
            val allScores = loadAllScore(context)
            allScores
                .filter { it.id?.equals(scoreModelData.id) ?: false }
                .forEach { allScores.remove(it) }

            allScores.add(scoreModelData)
            val jsonArray = JSONArray()
            allScores.toList().map { it.toJsonObject() }
                .forEach { jsonArray.put(it) }
            File(context.filesDir, file_name).writeText(jsonArray.toString())
        }
    }
}