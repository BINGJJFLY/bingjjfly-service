package com.wjz.service.factory;

public interface DO2VOFactory<D, V> {

	V convert(D domain);
}
