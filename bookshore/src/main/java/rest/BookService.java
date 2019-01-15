package rest;

import com.google.gson.Gson;
import com.restcompress.provider.LZF;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import domain.Book;
import org.apache.commons.codec.binary.Base64;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;
import persistence.BookMgr;

import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;

@Path("/bookShore")
public class BookService extends ResourceConfig {
    private final Gson gson = new Gson();
    private static final Logger log = Logger.getLogger(BookService.class.getName());

    public BookService() { }

    public static String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        String outStr = out.toString("UTF-8");
        return outStr;
    }

    @POST
    @Path("/addBook")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addBook(String received){
        Book book = gson.fromJson(received, Book.class);
        System.out.println("[Server add book]");
        if(book == null){
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }
        System.out.println("[Server valid model]");
        String name = book.getTitle();
        Integer pages = book.getPages();
        String ISBN = book.getISBN();

        boolean correct;
        if(name.isEmpty() || pages == 0 || ISBN.isEmpty()){
            correct = false;
        } else {
            BookMgr bookMgr = new BookMgr();
            correct = bookMgr.addBook(name, pages, ISBN);
        }
        return Response.status(200).entity(RestResponseHelper.getSuccessResponse(correct)).build();
    }

    @GET
    @Path("/allBooks")
    @Produces("application/json")
    public Response getAllBooks(){
        BookMgr bookMgr = new BookMgr();
        List<Book> books = null;
        books = bookMgr.getAllBooks();
        if (books == null || books.size() == 0){
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }
        return Response.status(200).entity(BookResponse.getAllBooks(books)).build();
    }

    @GET
    @Path("/book/{id}")
    @Produces("application/json")
    public Response getBookById(@PathParam("id") Integer id){
        BookMgr bookMgr = new BookMgr();
        Book book = null;
        book = bookMgr.getBook(id);
        if(book == null){
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }
        return Response.status(200).entity(BookResponse.getSingleBook(book)).build();
    }

    @GET
    @Path("/bookByTitle/{title}")
    @Produces("application/json")
    public Response getBookByTitle(@PathParam("title") String title){
        BookMgr bookMgr = new BookMgr();
        List<Book> books = null;
        books = bookMgr.findBooksByTitle(title);
        if (books == null){
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }
        CacheControl cc = new CacheControl();
        cc.setMaxAge(31536000);
        return Response.status(200).entity(BookResponse.getAllBooks(books)).cacheControl(cc).build();
    }
}
