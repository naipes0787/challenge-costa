package ar.com.wolox.challengecosta.util;

/**
 * Clase para centralizar las constantes de la aplicación. Es final para evitar herencia.
 */
public final class Constants {

    public static final String REST_URL = "https://jsonplaceholder.typicode.com/";
    public static final String REST_USERS_URL = REST_URL + "users";
    public static final String REST_ALBUMS_URL = REST_URL + "albums";
    public static final String REST_ALBUMS_BY_USER_URL = REST_ALBUMS_URL + "?userId=";
    public static final String REST_PHOTOS_URL = REST_URL + "photos";
    public static final String REST_PHOTOS_BY_ALBUM_URL = REST_PHOTOS_URL + "?albumId=";
    public static final String REST_POSTS_URL = REST_URL + "posts";
    public static final String REST_POSTS_BY_USER_URL = REST_POSTS_URL + "?userId=";
    public static final String REST_COMMENTS_URL = REST_URL + "comments";
    public static final String REST_COMMENTS_BY_POST_URL = REST_COMMENTS_URL + "?postId=";
    public static final String REST_COMMENTS_BY_NAME_URL = REST_COMMENTS_URL + "?name=";

    /**
     * Constructor privado para evitar instanciación
     */
    private Constants() {

    }

}
