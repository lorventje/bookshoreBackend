package rest;

import com.google.gson.Gson;
import domain.Attachment;
import domain.Book;
import persistence.AttachmentMgr;
import persistence.BookMgr;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/bookShore")
public class AttachmentService {
    private final Gson gson = new Gson();
    private static final Logger log = Logger.getLogger(BookService.class.getName());

    public AttachmentService(){

    }

    @GET
    @Path("/download/{id}")
    @Produces("application/json")
    public Response getFileById(@PathParam("id") Integer id){
        AttachmentMgr attachmentMgr = new AttachmentMgr();
        Attachment attachment = null;
        //attachment = attachmentMgr.getAttachment(id);
        //if(attachment == null){
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        //}
        //return Response.status(200).entity(RestResponse.getSingleAttachment(attachment)).build();
    }


}
