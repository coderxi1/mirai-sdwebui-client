package com.coderxi.sdwebui.client.util

import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KType
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

/**
 * 将 Map<String, String> 转换成指定的实体类类型
 * @param clazz 目标实体类类型
 * @return 转换后的实体类对象
 */
fun <T : Any> Map<String, String>.toEntity(clazz: KClass<T>, ignoreCase: Boolean = true): T {
    val entity = clazz.createInstance()

    clazz.memberProperties.forEach { prop ->
        val names = arrayListOf(prop.name)
        val aliasAnno = prop.javaField?.getAnnotation(Alias::class.java)
        if (aliasAnno != null) {
            names.addAll(aliasAnno.names)
        }
        for (name in names) {
            val key = this.keys.find { it.equals(name, ignoreCase) || it.equals(name, ignoreCase) }
            if (key != null) {
                val value = prop.returnType.convertString(this[key])
                prop.isAccessible = true
                val kmp = prop as KMutableProperty1<*, *>
                kmp.setter.call(entity, value)
                break
            }
        }
    }

    return entity
}

/**
 * 属性别名注解
 * @param names 属性别名列表
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Alias(vararg val names: String)

/**
 * 将字符串转换为对应类型的实例
 * @param value 待转换的字符串，可以为空
 * @return 转换后的类型实例，可能为空
 */
fun KType.convertString(value: String?): Any? {
    if (value == null) {
        return null
    }
    return when (this.classifier as KClass<*>) {
        String::class -> value
        Int::class -> value.toInt()
        Double::class -> value.toDouble()
        Float::class -> value.toFloat()
        Long::class -> value.toLong()
        Short::class -> value.toShort()
        Byte::class -> value.toByte()
        else -> throw IllegalArgumentException("Unsupported type: $classifier")
    }
}

fun <T : Any> KClass<T>.showAlias(): String {
    return this.java.declaredFields.joinToString("\n") { field ->
        val name = (field.getAnnotation(Alias::class.java))?.names?.joinToString(" / ") ?: ""
        val typeName = field.genericType.typeName
        "$name（$typeName）"
    }
}

