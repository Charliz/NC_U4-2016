package myStore;

import myStore.DAO.POJO.storeCatalog;
import myStore.DAO.postgre.PostgreCatalogDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("/cat")
public class Categories {
    @GET
    @Produces("text/plain")
    public String getClichedMessage() throws Exception {
        PostgreCatalogDAO pcd = new PostgreCatalogDAO();
        List<storeCatalog> alCat = pcd.getAll();
        return pcd.getAll().toString();
    }
}
