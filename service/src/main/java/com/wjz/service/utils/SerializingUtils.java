package com.wjz.service.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.shiro.io.SerializationException;

/**
 * <b>序列化、反序列化工具类</b>
 * <p>
 * 序列化：入参对象返回字节数组
 * <p>
 * 反序列化：入参对象为字节数组返回对象
 * <p>
 * @author iss002
 * @see {@link org.apache.shiro.io.DefaultSerializer shiro.DefaultSerializer}
 * @see {@link org.springframework.core.serializer.DefaultSerializer spring.DefaultSerializer}
 *
 */
@Deprecated
public abstract class SerializingUtils {

	public static final int BYTE_ARRAY_OUTPUT_STREAM_SIZE = 128;

	public static byte[] serialize(Object object) throws SerializationException {
		byte[] result = null;

		if (object == null) {
			return result;
		}
		if (!(object instanceof Serializable)) {
			throw new SerializationException("requires a Serializable payload " + "but received an object of type ["
					+ object.getClass().getName() + "]");
		}

		ByteArrayOutputStream byteStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			byteStream = new ByteArrayOutputStream(BYTE_ARRAY_OUTPUT_STREAM_SIZE);
			objectOutputStream = new ObjectOutputStream(byteStream);
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			result = byteStream.toByteArray();
		} catch (IOException e) {
			throw new SerializationException("serialize error, object=" + object.toString(), e);
		}

		return result;
	}

	public static Object deserialize(byte[] bytes) throws SerializationException {
		Object result = null;

		if (bytes == null || bytes.length == 0) {
			return result;
		}

		ByteArrayInputStream byteStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			byteStream = new ByteArrayInputStream(bytes);
			objectInputStream = new ObjectInputStream(byteStream);
			result = objectInputStream.readObject();
		} catch (IOException e) {
			throw new SerializationException("deserialize error", e);
		} catch (ClassNotFoundException e) {
			throw new SerializationException("deserialize error class is not found", e);
		}

		return result;
	}

}
