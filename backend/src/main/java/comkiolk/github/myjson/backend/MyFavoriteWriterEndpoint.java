package comkiolk.github.myjson.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "myFavoriteWriterApi",
        version = "v1",
        resource = "myFavoriteWriter",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myjson.github.comkiolk",
                ownerName = "backend.myjson.github.comkiolk",
                packagePath = ""
        )
)
public class MyFavoriteWriterEndpoint {

    private static final Logger logger = Logger.getLogger(MyFavoriteWriterEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(MyFavoriteWriter.class);
    }

    /**
     * Returns the {@link MyFavoriteWriter} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code MyFavoriteWriter} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "myFavoriteWriter/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public MyFavoriteWriter get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting MyFavoriteWriter with ID: " + id);
        MyFavoriteWriter myFavoriteWriter = ofy().load().type(MyFavoriteWriter.class).id(id).now();
        if (myFavoriteWriter == null) {
            throw new NotFoundException("Could not find MyFavoriteWriter with ID: " + id);
        }
        return myFavoriteWriter;
    }

    /**
     * Inserts a new {@code MyFavoriteWriter}.
     */
    @ApiMethod(
            name = "insert",
            path = "myFavoriteWriter",
            httpMethod = ApiMethod.HttpMethod.POST)
    public MyFavoriteWriter insert(MyFavoriteWriter myFavoriteWriter) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that myFavoriteWriter.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(myFavoriteWriter).now();
        logger.info("Created MyFavoriteWriter with ID: " + myFavoriteWriter.getId());

        return ofy().load().entity(myFavoriteWriter).now();
    }

    /**
     * Updates an existing {@code MyFavoriteWriter}.
     *
     * @param id               the ID of the entity to be updated
     * @param myFavoriteWriter the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code MyFavoriteWriter}
     */
    @ApiMethod(
            name = "update",
            path = "myFavoriteWriter/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public MyFavoriteWriter update(@Named("id") Long id, MyFavoriteWriter myFavoriteWriter) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(myFavoriteWriter).now();
        logger.info("Updated MyFavoriteWriter: " + myFavoriteWriter);
        return ofy().load().entity(myFavoriteWriter).now();
    }

    /**
     * Deletes the specified {@code MyFavoriteWriter}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code MyFavoriteWriter}
     */
    @ApiMethod(
            name = "remove",
            path = "myFavoriteWriter/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(MyFavoriteWriter.class).id(id).now();
        logger.info("Deleted MyFavoriteWriter with ID: " + id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "myFavoriteWriter",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<MyFavoriteWriter> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<MyFavoriteWriter> query = ofy().load().type(MyFavoriteWriter.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<MyFavoriteWriter> queryIterator = query.iterator();
        List<MyFavoriteWriter> myFavoriteWriterList = new ArrayList<MyFavoriteWriter>(limit);
        while (queryIterator.hasNext()) {
            myFavoriteWriterList.add(queryIterator.next());
        }
        return CollectionResponse.<MyFavoriteWriter>builder().setItems(myFavoriteWriterList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(MyFavoriteWriter.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find MyFavoriteWriter with ID: " + id);
        }
    }

    @ApiMethod(name = "sayHi")
    public MyFavoriteWriterEndpoint sayHi(@Named("name") String name) {
        MyFavoriteWriterEndpoint response = new MyFavoriteWriterEndpoint();
//        response.("Hi, " + name);
//        String result = response.

        return response;
    }
}