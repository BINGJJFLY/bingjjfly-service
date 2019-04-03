package com.wjz.demo.java.map.enummap;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import sun.misc.SharedSecrets;

/**
 * 枚举型Map
 * 
 * 键值对的键类型只能是枚举类型
 *
 * @author iss002
 *
 */
public class ConstructorTest {

	@Test
	public void general() {
		EnumMap<JdbcType, String> map = new EnumMap<>(JdbcType.class);
	}
	
	/*
	 public EnumMap(Class<K> keyType) {
        this.keyType = keyType;
        $** 枚举类中的元素数组 **$
        keyUniverse = getKeyUniverse(keyType);
        vals = new Object[keyUniverse.length];
     }
	 */

	@Test
	@SuppressWarnings("restriction")
	public void enums() {
		// 根据枚举类的class获得该枚举的元素数组
		JdbcType[] types = SharedSecrets.getJavaLangAccess().getEnumConstantsShared(JdbcType.class);
	}
	
	@Test
	public void enumMap() {
		EnumMap<JdbcType, String> map = new EnumMap<>(JdbcType.class);
		EnumMap<JdbcType, String> map_2 = new EnumMap<>(map);
	}
	
	/*
	 public EnumMap(EnumMap<K, ? extends V> m) {
        keyType = m.keyType;
        keyUniverse = m.keyUniverse;
        vals = m.vals.clone();
        size = m.size;
     }
	 */
	
	@Test
	public void map() {
		Map<JdbcType, String> map = new HashMap<>();
		EnumMap<JdbcType, String> map_2 = new EnumMap<>(map);
	}
	
	/*
	 public EnumMap(Map<K, ? extends V> m) {
        if (m instanceof EnumMap) {
            EnumMap<K, ? extends V> em = (EnumMap<K, ? extends V>) m;
            keyType = em.keyType;
            keyUniverse = em.keyUniverse;
            vals = em.vals.clone();
            size = em.size;
        } else {
            if (m.isEmpty())
                throw new IllegalArgumentException("Specified map is empty");
            keyType = m.keySet().iterator().next().getDeclaringClass();
            keyUniverse = getKeyUniverse(keyType);
            vals = new Object[keyUniverse.length];
            putAll(m);
        }
     }
	 */

	enum JdbcType {
		INTEGER, VARCHAR;
	}
}
