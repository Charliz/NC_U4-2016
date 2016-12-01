package myStore.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import myStore.DAO.postgre.PostgreCatalogDAO;


public class DaoFactory {

	private static DaoFactory daoFactory;
	private static String type;
	private String user;
	private String password;
	private String url;
	private String driver;
//	private static Logger log = Logger.getLogger(DaoFactory.class.getName());

	private DaoFactory() throws Exception {
		try {
			loadProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
//			log.error("Driver not found\n", e);
		}
	}

	public static DaoFactory getInstance(){
		if (null == daoFactory) {
			try {
				daoFactory = new DaoFactory();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return daoFactory;
	}

	public Connection getConnection() throws Exception {
//		log.trace("Driver manager get connection");
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new Exception("No connection to DB", e);
		}
	}

	public CatalogDAO getGroupDao() {
		if (type.equalsIgnoreCase("postgres")) {
			return new PostgreCatalogDAO();
		} else {
			return new PostgreCatalogDAO();
		}
	}
//
//	public GoodsDao getGoodsDao() {
//		if (type.equalsIgnoreCase("postgres")) {
//			return new PostgreSqlGoodsDao();
//		} else {
//			return new PostgreSqlGoodsDao();
//		}
//	}
//
//	public OrderDao getOrderDao() {
//		if (type.equalsIgnoreCase("postgres")) {
//			return new PostgreSqlOrderDao();
//		} else {
//			return new PostgreSqlOrderDao();
//		}
//	}
//
//	public CustomerDao getCustomerDao() {
//		if (type.equalsIgnoreCase("postgres")) {
//			return new PostgreSqlCustomerDao();
//		} else {
//			return new PostgreSqlCustomerDao();
//		}
//	}
//
//	public OrderLineDao getOrderLineDao() {
//		if (type.equalsIgnoreCase("postgres")) {
//			return new PostgreSqlOrderLineDao();
//		} else {
//			return new PostgreSqlOrderLineDao();
//		}
//	}

	private void loadProperties() throws Exception {
		//Properties properties = new Properties();
//		try {
		//	properties.load(DaoFactory.class.getResourceAsStream("/db.properties"));
//			type = properties.getProperty("type");
//			user = properties.getProperty("user");
//			password = properties.getProperty("password");
//			url = properties.getProperty("url");
//			driver = properties.getProperty("driver");

            type = "postgres";
            user = "user1";
            password = "123";
            url = "jdbc:postgresql://localhost:5432/onlinestore";
            driver = "org.postgresql.Driver";

//		} catch (IOException e) {
//			throw new Exception("Can't read file", e);
//		}
	}
}
