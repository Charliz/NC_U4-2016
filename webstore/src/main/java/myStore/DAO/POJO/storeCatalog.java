package myStore.DAO.POJO;

/**
 * Created by One on 01.12.2016.
 */
public class storeCatalog {
    private int id;
    private String name;

    public storeCatalog(int id, String name) {

        this.id = id;
        this.name = name;

    }
    @Override
    public String toString() {
        return String.valueOf(id) + "_" + name;
    }

}
