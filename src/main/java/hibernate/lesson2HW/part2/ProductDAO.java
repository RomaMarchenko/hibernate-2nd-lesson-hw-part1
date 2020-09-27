package hibernate.lesson2HW.part2;

import hibernate.lesson1.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {
    private static SessionFactory sessionFactory;

    private static final String FIND_PRODUCT_BY_ID = "SELECT * FROM PRODUCT WHERE ID = ?";
    private static final String FIND_PRODUCT_BY_NAME = "SELECT * FROM PRODUCT WHERE NAME = ?";
    private static final String FIND_PRODUCT_BY_CONTAINED_NAME = "SELECT * FROM PRODUCT WHERE NAME = ? OR NAME LIKE % + ' ' + ? OR NAME LIKE ? + ' ' + % OR NAME LIKE % + ' ' + ? + ' ' + %";
    private static final String FIND_PRODUCT_BY_PRICE = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?";
    private static final String FIND_PRODUCT_BY_NAME_SORTED_ASC = "SELECT * FROM PRODUCT WHERE NAME = ? ORDER BY NAME ASC";
    private static final String FIND_PRODUCT_BY_NAME_SORTED_DESC = "SELECT * FROM PRODUCT WHERE NAME = ? ORDER BY NAME DESC";
    private static final String FIND_PRODUCT_BY_PRICE_SORTED_DESC = "SELECT * FROM FROM PRODUCT WHERE PRICE BETWEEN ? AND ? ORDER BY PRICE DESC";

    public static Product findById(Long id) {
        Product product = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createSQLQuery(FIND_PRODUCT_BY_ID).addEntity(Product.class);
            query.setParameter(1, id);

            product =(Product) query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static List<Product> findByName(String name) {
        List<Product> products = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createNativeQuery(FIND_PRODUCT_BY_NAME);
            query.setParameter(1, name);

            products = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<Product> findByContainedName(String name) {
        List<Product> products = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createNativeQuery(FIND_PRODUCT_BY_CONTAINED_NAME);
            query.setParameter(1, name);
            query.setParameter(2, name);
            query.setParameter(3, name);
            query.setParameter(4, name);

            products = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<Product> findByPrice(int price, int delta) {
        List<Product> products = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createNativeQuery(FIND_PRODUCT_BY_PRICE);
            query.setParameter(1, price - delta);
            query.setParameter(2, price + delta);

            products = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<Product> findByNameSortedAsc(String name) {
        List<Product> products = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createNativeQuery(FIND_PRODUCT_BY_NAME_SORTED_ASC);
            query.setParameter(1, name);

            products = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<Product> findByNameSortedDesc(String name) {
        List<Product> products = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createNativeQuery(FIND_PRODUCT_BY_NAME_SORTED_DESC);
            query.setParameter(1, name);

            products = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<Product> findByPriceSortedDesc(int price, int delta) {
        List<Product> products = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createNativeQuery(FIND_PRODUCT_BY_PRICE_SORTED_DESC);
            query.setParameter(1, price - delta);
            query.setParameter(2, price + delta);

            products = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return products;
    }


    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
