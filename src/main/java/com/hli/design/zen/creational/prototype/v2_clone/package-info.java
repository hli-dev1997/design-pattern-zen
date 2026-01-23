/**
 * ✅ 标准实现 (Cloneable 接口)
 *
 * 核心：
 * 实现 Cloneable 接口，重写 clone() 方法。
 * 注意：Object.clone() 默认是浅拷贝 (Shallow Copy)。
 * 对于基本类型（int, long）和 String，浅拷贝没问题。
 * 但对于引用类型（List, Date, Object），浅拷贝只会拷贝引用，导致多个对象共享同一个内部对象。
 */
package com.hli.design.zen.creational.prototype.v2_clone;