package ca.pmulcahy.messenger.resources;

import java.net.URI;
import java.util.List;

import ca.pmulcahy.messenger.model.Link;
import ca.pmulcahy.messenger.model.Message;
import ca.pmulcahy.messenger.resources.beans.MessageFilterBean;
import ca.pmulcahy.messenger.service.MessageService;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService = new MessageService();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
    	return messageService.getAllMessages();
	}

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
    	final Message newMessage = messageService.addMessage(message);
    	final String newId = String.valueOf(newMessage.getId());
    	final URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
    	return Response.created(uri)
    				   .entity(newMessage)
    				   .build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void delete(@PathParam("messageId") long id) {
        messageService.removeMessage(id);
    }
    
    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
        Message message = messageService.getMessage(id);
        message.addLink(getUriForSelf(uriInfo, message), "self");
        message.addLink(getUriForProfile(uriInfo, message), "profile");
        message.addLink(getUriForComments(uriInfo, message), "comments");
        return message;
    }

	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder() // http://localhost:8080/messenger/webapi
				.path(MessageResource.class) // /messages
				.path(MessageResource.class, "getCommentResource") // /comments
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();
		return uri;
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder() // http://localhost:8080/messenger/webapi
				.path(ProfileResource.class) // /profiles
				.path(message.getAuthor()) // {authorName}
				.build()
				.toString();
		return uri;
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
        					.path(Long.toString(message.getId()))
        					.build()
        					.toString();
		return uri;
	}
    
    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
    	return new CommentResource();
    }
}
