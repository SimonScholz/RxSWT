package io.reactivex.swt;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiService {

	public static final String API_URL = "https://api.github.com";

	public static GitHubApi getGitHubApi() {

		Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create()).build();

		return retrofit.create(GitHubApi.class);
	}
}
