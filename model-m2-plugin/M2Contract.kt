// In :model-m2-plugin, create M2Contract.kt
interface MultimodalLLM {
    suspend fun processInput(
        text: String? = null,
        image: Bitmap? = null,
        audio: ByteArray? = null
    ): Result<OutputResponse>

    data class OutputResponse(
        val textResponse: String,
        val confidence: Float,
        val metadata: Map<String, Any>?
    )
}