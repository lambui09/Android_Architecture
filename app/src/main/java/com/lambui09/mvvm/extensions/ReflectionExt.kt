package com.lambui09.mvvm.extensions

/**
 * Get a value from a private field
 *
 * @receiver Any is a instance
 * @param name String a private field
 * @return V?
 * T: the class type contain private field name, the class maybe parent or higher levers
 * V: value type of the private field
 */
inline fun <reified T, reified V> Any.getPrivateFieldValue(name: String): V? {
    val field = T::class.java.getDeclaredField(name)
    field.isAccessible = true
    return field.get(this) as? V
}