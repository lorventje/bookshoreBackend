package rest;

import com.google.gson.Gson;
import domain.Attachment;
import domain.Book;
import domain.Review;
import domain.Reviewer;
import persistence.AttachmentMgr;
import persistence.BookMgr;
import persistence.ReviewMgr;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Path("/bookShore")
public class ReviewService {

    private final Gson gson = new Gson();
    private static final Logger log = Logger.getLogger(ReviewService.class.getName());

    public ReviewService() {

    }

    @GET
    @Path("/getReviewsByBookId/{id}")
    @Produces("application/json")
    public Response getReviewsById(@PathParam("id") Integer id) {
        ReviewMgr reviewMGr = new ReviewMgr();
        List<Review> reviews = null;
        reviews = reviewMGr.getReviewsById(id);
        if (reviews == null) {
            return Response.accepted().status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }
        return Response.status(200).entity(ReviewResponse.getReviewsById(reviews)).build();
    }

    @POST
    @Path("/createReviewer")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createReviewer(String received){
        ReviewRest reviewRest = gson.fromJson(received, ReviewRest.class);
        System.out.println("[Server createReviewer]");
        if(reviewRest == null){
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }
        System.out.println("[Server valid model]");
        String name = reviewRest.getName();
        String email = reviewRest.getEmail();
        //List<Review> reviews = reviewRest.getReviews();
        boolean correctCreatedReviewer;
        if(name.isEmpty()){
            correctCreatedReviewer = false;
        } else {
            ReviewMgr reviewMgr = new ReviewMgr();
            correctCreatedReviewer = reviewMgr.createReviewer(name, email);
        }
        return Response.status(200).entity(RestResponseHelper.getSuccessResponse(correctCreatedReviewer)).build();
    }

    @POST
    @Path("/createReview")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createReview(String received) {
        ReviewRest reviewRest = gson.fromJson(received, ReviewRest.class);
        System.out.println("[Server createReview]");
        if (reviewRest == null) {
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }
        System.out.println("[Server valid model]");
        String bookReview = reviewRest.getBookReview();
        Date date = reviewRest.getDate();
        Reviewer reviewer = reviewRest.getReviewer();
        Book book = reviewRest.getBook();
        boolean correctCreatedReview;
        if (bookReview.isEmpty()) {
            correctCreatedReview = false;
        } else {
            ReviewMgr reviewMgr = new ReviewMgr();
            correctCreatedReview = reviewMgr.createReview(bookReview, date, reviewer, book);
        }
        return Response.status(200).entity(RestResponseHelper.getSuccessResponse(correctCreatedReview)).build();
    }

}
