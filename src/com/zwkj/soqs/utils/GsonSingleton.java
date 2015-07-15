package com.zwkj.soqs.utils;

import com.google.gson.Gson;

public class GsonSingleton {

	private static Gson gson;

	private GsonSingleton() {
	};

	public static Gson getGsonInstance() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}
}
