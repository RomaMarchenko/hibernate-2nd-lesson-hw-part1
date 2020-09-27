package hibernate.lesson2;

import hibernate.Product;
import hibernate.lesson1.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO {

    private static SessionFactory sessionFactory;

    public static void saveAll(List<Product> products) {
        //create session/tr

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            //action
            for (Product product : products) {
                session.save(product);
            }

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
    }

    public static void updateAll(List<Product> products) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            for (Product product : products) {
                session.update(product);
            }

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
    }


    public static void deleteAll(List<Product> products) {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            for (Product product : products) {
                session.delete(product);
            }

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
    }

    public static Product save(Product product) {
        //create session/tr
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            //action
            session.save(product);

            //close session/tr
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        }
        return product;
    }

    public static Product update(Product product) {
        if (product.getId() != null) {
            Transaction tr = null;
            try (Session session = createSessionFactory().openSession()) {
                tr = session.getTransaction();
                tr.begin();

                session.update(product);

                tr.commit();
            } catch (HibernateException e) {
                System.err.println("Update is failed");
                System.err.println(e.getMessage());
                if (tr != null)
                    tr.rollback();
            }
        }
        return product;
    }

    public static Product delete(Product product) {
        if (product.getId() != null) {
            Transaction tr = null;
            try (Session session = createSessionFactory().openSession()) {
                tr = session.getTransaction();
                tr.begin();

                session.delete(product);

                tr.commit();
            } catch (HibernateException e) {
                System.err.println("Delete is failed");
                System.err.println(e.getMessage());
                if (tr != null)
                    tr.rollback();
            }
        }
        return product;
    }


    private static SessionFactory createSessionFactory() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
