#include<jni.h>
#include<string.h>

jstring Java_com_example_androidpractice_ndk_ui_NdkFragment_helloWorld(JNIEnv* env, jobject obj) {
    return (*env)->NewStringUTF(env, "Hello world from C");
}