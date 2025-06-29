// ModelPlugin.kt
package com.yourname.m2plugin

import android.content.Context
import android.graphics.Bitmap

interface ModelPlugin {
    val name: String
    fun initialize(context: Context)
    suspend fun runInference(input: PluginInput): PluginOutput
}

data class PluginInput(
    val text: String?,
    val image: Bitmap?
)

data class PluginOutput(
    val textResult: String,
    val imageResult: Bitmap? = null
)

override fun initialize(ctx: Context) {
    interpreter = Interpreter(FileUtil.loadMappedFile(ctx, "yolov4_tiny.tflite"))
}

override suspend fun runInference(input: PluginInput): PluginOutput = withContext(Dispatchers.Default) {
    // Preprocess image → input tensor
    // Run interpreter.invoke(...)
    // Parse output boxes & class IDs
    val detected = listOf("person", "car", "dog") // mock
    val summary = "Detected ${detected.size} objects: ${detected.joinToString()}"
    PluginOutput(textResult = summary)
}
