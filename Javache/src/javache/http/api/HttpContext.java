package javache.http.api;

public interface HttpContext {
    HttpRequest getHttpRequest();

    HttpResponse getHttpResponse();
}
