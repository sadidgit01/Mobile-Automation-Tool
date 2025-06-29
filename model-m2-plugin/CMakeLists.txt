cmake_minimum_required(VERSION 3.22)
project("model-m2-plugin")

add_library(
        m2-plugin
        SHARED
        src/main/cpp/llama_jni.cpp
        src/main/cpp/ggml.c
        src/main/cpp/llama.cpp
)

target_include_directories(m2-plugin PRIVATE src/main/cpp)