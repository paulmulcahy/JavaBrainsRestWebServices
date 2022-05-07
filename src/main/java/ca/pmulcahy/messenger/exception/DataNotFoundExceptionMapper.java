package ca.pmulcahy.messenger.exception;

import ca.pmulcahy.messenger.model.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException exception) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404, "pmulcahy.ca");
		return Response.status(Status.NOT_FOUND)
					   .entity(errorMessage)
					   .build();
	}

}
