package hibernate.lesson2HW.part1;

import hibernate.lesson1.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {
    private static SessionFactory sessionFactory;

    private static final String FIND_PRODUCT_BY_ID = "FROM Product WHERE id = :id";
    private static final String FIND_PRODUCT_BY_NAME = "FROM Product WHERE name = :name";
    private static final String FIND_PRODUCT_BY_CONTAINED_NAME = "FROM Product WHERE name = :name1 OR name LIKE :name2 OR name LIKE :name3 OR name LIKE :name4";
    private static final String FIND_PRODUCT_BY_PRICE = "FROM Product WHERE price between :lowLimit and :highLimit";
    private static final String FIND_PRODUCT_BY_NAME_SORTED_ASC = "FROM Product WHERE name = :name order by name asc";
    private static final String FIND_PRODUCT_BY_NAME_SORTED_DESC = "FROM Product WHERE name = :name order by name desc";
    private static final String FIND_PRODUCT_BY_PRICE_SORTED_DESC = "FROM Product WHERE price between :lowLimit and :highLimit order by price desc";

    public static Product findById(Long id) {
        Product product = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery(FIND_PRODUCT_BY_ID);
            query.setParameter("id", id);

            List<Product> products = query.list();

            for(Product pr : products)
                product = pr;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static List<Product> findByName(String name) {
        List<Product> products = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery(FIND_PRODUCT_BY_NAME);
            query.setParameter("name", name);

            products = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<Product> findByContainedName(String name) {
        List<Product> products = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery(FIND_PRODUCT_BY_CONTAINED_NAME);
            query.setParameter("name1", name);
            query.setParameter("name2", "% " + name);
            query.setParameter("name3", name + " %");
            query.setParameter("name4", "% " + name + " %");

            products = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<Product> findByPrice(int price, int delta) {
        List<Product> products = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery(FIND_PRODUCT_BY_PRICE);
            query.setParameter("lowLimit", price - delta);
            query.setParameter("highLimit", price + delta);

            products = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<Product> findByNameSortedAsc(String name) {
        List<Product> products = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery(FIND_PRODUCT_BY_NAME_SORTED_ASC);
            query.setParameter("name", name);

            products = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<Product> findByNameSortedDesc(String name) {
        List<Product> products = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery(FIND_PRODUCT_BY_NAME_SORTED_DESC);
            query.setParameter("name", name);

            products = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<Product> findByPriceSortedDesc(int price, int delta) {
        List<Product> products = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery(FIND_PRODUCT_BY_PRICE_SORTED_DESC);
            query.setParameter("lowLimit", price - delta);
            query.setParameter("highLimit", price + delta);

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
