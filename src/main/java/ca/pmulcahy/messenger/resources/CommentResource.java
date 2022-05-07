package ca.pmulcahy.messenger.resources;

import java.util.List;

import ca.pmulcahy.messenger.model.Comment;
import ca.pmulcahy.messenger.service.CommentService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService commentService = new CommentService();

    @GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
    	return commentService.getAllComments(messageId);
	}

    @POST
    public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
    	return commentService.addComment(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public void delete(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        commentService.removeComment(messageId, commentId);
    }
    
    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        return commentService.getComment(messageId, commentId);
    }
}
