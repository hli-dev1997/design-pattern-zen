/**
 * 🚀 进阶 (深拷贝 Deep Copy)
 *
 * 场景：
 * 当对象内部包含引用类型（如 ArrayList, 自定义对象）时，必须使用深拷贝。
 *
 * 实现方式：
 * 1. 手动深拷贝：在 clone() 方法里，手动把内部对象也 clone 一份。
 * 2. 序列化：利用 Serializable，把对象转成二进制流再转回来（推荐，代码最少）。
 * 3. JSON 转换：转成 JSON 字符串再转回对象（性能稍差，但方便）。
 */
package com.hli.design.zen.creational.prototype.v3_deep;