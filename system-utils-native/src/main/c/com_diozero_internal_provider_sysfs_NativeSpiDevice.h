/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_diozero_internal_provider_sysfs_NativeSpiDevice */

#ifndef _Included_com_diozero_internal_provider_sysfs_NativeSpiDevice
#define _Included_com_diozero_internal_provider_sysfs_NativeSpiDevice
#ifdef __cplusplus
extern "C" {
#endif
#undef com_diozero_internal_provider_sysfs_NativeSpiDevice_LSB_FIRST
#define com_diozero_internal_provider_sysfs_NativeSpiDevice_LSB_FIRST 0L
/*
 * Class:     com_diozero_internal_provider_sysfs_NativeSpiDevice
 * Method:    spiOpen
 * Signature: (Ljava/lang/String;BIBZ)I
 */
JNIEXPORT jint JNICALL Java_com_diozero_internal_provider_sysfs_NativeSpiDevice_spiOpen
  (JNIEnv *, jclass, jstring, jbyte, jint, jbyte, jboolean);

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeSpiDevice
 * Method:    spiConfig
 * Signature: (IBIBZ)I
 */
JNIEXPORT jint JNICALL Java_com_diozero_internal_provider_sysfs_NativeSpiDevice_spiConfig
  (JNIEnv *, jclass, jint, jbyte, jint, jbyte, jboolean);

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeSpiDevice
 * Method:    spiClose
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_diozero_internal_provider_sysfs_NativeSpiDevice_spiClose
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeSpiDevice
 * Method:    spiTransfer
 * Signature: (I[B[BIIIBZ)I
 */
JNIEXPORT jint JNICALL Java_com_diozero_internal_provider_sysfs_NativeSpiDevice_spiTransfer
  (JNIEnv *, jclass, jint, jbyteArray, jbyteArray, jint, jint, jint, jbyte, jboolean);

#ifdef __cplusplus
}
#endif
#endif
