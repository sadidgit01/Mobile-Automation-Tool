#include <jni.h>
#include "llama.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_llmdemo_modelm2_M2OfflineModel_runInference(
        JNIEnv* env,
        jobject thiz,
        jstring model_path,
        jstring prompt) {

    const char *path = env->GetStringUTFChars(model_path, nullptr);
    const char *input = env->GetStringUTFChars(prompt, nullptr);

    // Minimal inference
    llama_model* model = llama_load_model_from_file(path, llama_model_default_params());
    std::string output = "Output for: " + std::string(input);

    env->ReleaseStringUTFChars(model_path, path);
    env->ReleaseStringUTFChars(prompt, input);

    return env->NewStringUTF(output.c_str());
}