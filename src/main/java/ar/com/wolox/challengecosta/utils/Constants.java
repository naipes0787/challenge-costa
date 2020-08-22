package ar.com.wolox.challengecosta.utils;

/**
 * Clase para centralizar las constantes de la aplicación. Es final para evitar herencia.
 */
public final class Constants {

    public static final String REST_ALBUMS_BY_USER_URL = Constants.REST_ALBUMS_URL + "?userId=";
    public static final String REST_PHOTOS_BY_ALBUM_URL = Constants.REST_ALBUMS_URL + "?albumId=";
    public static final String REST_POSTS_BY_USER_URL = Constants.REST_ALBUMS_URL + "?userId=";
    public static final String REST_COMMENTS_BY_POST_URL = Constants.REST_ALBUMS_URL + "?postId=";
    public static final String REST_COMMENTS_BY_NAME_URL = Constants.REST_ALBUMS_URL + "?name=";
    private static final String REST_URL = "https://jsonplaceholder.typicode.com/";
    public static final String REST_USERS_URL = REST_URL + "users";
    public static final String REST_ALBUMS_URL = REST_URL + "albums";
    public static final String REST_PHOTOS_URL = REST_URL + "photos";
    public static final String REST_POSTS_URL = REST_URL + "posts";
    public static final String REST_COMMENTS_URL = REST_URL + "comments";

    /**
     * Constructor privado para evitar instanciación
     */
    private Constants() {

    }

}
