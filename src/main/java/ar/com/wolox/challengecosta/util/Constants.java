package ar.com.wolox.challengecosta.util;

public final class Constants {

	public static final String REST_URL = "https://jsonplaceholder.typicode.com/";
	
	public static final String REST_USERS_URL = REST_URL + "users";
	
	public static final String REST_ALBUMS_URL = REST_URL + "albums";
	public static final String REST_ALBUMS_BY_USER_URL = REST_ALBUMS_URL + "?userId=";
	
	public static final String REST_PHOTOS_URL = REST_URL + "photos";
	public static final String REST_PHOTOS_BY_ALBUM_URL = REST_PHOTOS_URL + "?albumId=";
	
}
