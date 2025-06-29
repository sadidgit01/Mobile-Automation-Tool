// In :model-m2-plugin, create M2Provider.kt
object M2Provider {
    fun create(context: Context): MultimodalLLM {
        return M2OfflineModel(context)
    }
}

