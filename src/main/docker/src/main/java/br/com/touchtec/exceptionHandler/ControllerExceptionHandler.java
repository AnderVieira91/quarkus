package br.com.touchtec.exceptionHandler;


import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class ControllerExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        int errorStatus = WebApplicationException.class.isAssignableFrom(e.getClass()) ?
                ((WebApplicationException) e).getResponse().getStatus()
                : Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();

        return Response
                .status(errorStatus)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .entity(new ErrorMessage(e))
                .build();
    }

}
