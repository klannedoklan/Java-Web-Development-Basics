package javache;

import javache.http.api.HttpContext;
import javache.http.api.HttpRequest;
import javache.http.api.HttpResponse;
import javache.http.enums.HttpStatus;
import javache.http.impl.HttpContextImpl;
import javache.http.impl.HttpRequestImpl;
import javache.http.impl.HttpResponseImpl;

import java.io.File;

class RequestHandler {
    private HttpContext httpContext;

    RequestHandler() {
    }

    byte[] handleRequest(String requestContent) {

        HttpRequest httpRequest = new HttpRequestImpl(requestContent);

        HttpResponse httpResponse = new HttpResponseImpl();

        this.httpContext = new HttpContextImpl(httpRequest, httpResponse);

        //return this.generateDemoResponse();

        switch (httpRequest.getMethod()){
            case "GET": handleGetRequest(httpRequest);
            break;
            case "POST": handlePostRequest(httpRequest);
            break;
            default:break;
        }
        return this.httpContext.getHttpResponse().getContent();
    }

    private void handlePostRequest(HttpRequest httpRequest) {

    }

    private void handleGetRequest(HttpRequest httpRequest) {
        switch (httpRequest.getRequestUrl()){
            case "/" : {
                this.httpContext.getHttpResponse().setStatus(HttpStatus.OK);
                this.httpContext.getHttpResponse().addHeader("Content-Type", "text/html");
                this.httpContext.getHttpResponse().addHeader("Server", WebConstants.SERVER_NAME + "/" + WebConstants.SERVER_VERSION);
                File indexFileHtml = new File ("resources/pages/index.html");
                byte [] bytearray  = new byte [(int)indexFileHtml.length()];
                this.httpContext.getHttpResponse().setContent(bytearray);

            }
        }
    }

    private byte[] generateDemoResponse() {
        httpContext.getHttpResponse().setStatus(HttpStatus.OK);

        httpContext.getHttpResponse().addHeader("Content-Type", "text/html");
        httpContext.getHttpResponse().addHeader("Server", WebConstants.SERVER_NAME + "/" + WebConstants.SERVER_VERSION);

        httpContext.getHttpResponse().setContent(("<h1>Hello from " + WebConstants.SERVER_NAME + " v. " + WebConstants.SERVER_VERSION + "</h1>").getBytes());

        return httpContext.getHttpResponse().getBytes();
    }
}
