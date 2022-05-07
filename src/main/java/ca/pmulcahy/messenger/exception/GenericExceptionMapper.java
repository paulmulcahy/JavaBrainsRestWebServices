package ca.pmulcahy.messenger.exception;

import ca.pmulcahy.messenger.model.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 500, "pmulcahy.ca");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
					   .entity(errorMessage)
					   .build();
	}

}
