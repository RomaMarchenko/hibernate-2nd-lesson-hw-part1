package hibernate.lesson2;

import hibernate.Product;
import hibernate.lesson1.Product;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Product product = new Product();
        product.setName("table new");
        product.setDescription("grey & blue");
        product.setPrice(70);

        System.out.println(ProductDAO.save(product));

        Product product1 = new Product();
        product1.setId(0L);
        product1.setName("update test");
        product1.setDescription("grey & blue");
        product1.setPrice(70);

        Product product2 = new Product();
        product2.setId(4L);
        product2.setName("update test");
        product2.setDescription("grey & blue");
        product2.setPrice(80);

        Product product3 = new Product();
        product3.setId(10L);
        product3.setName("update test");
        product3.setDescription("grey & blue");
        product3.setPrice(90);

        List<Product> products = Arrays.asList(product1, product2, product3);
        //ProductDAO.saveAll(products);

        //System.out.println(ProductDAO.update(product3));
        //System.out.println(ProductDAO.delete(product3));

        //ProductDAO.updateAll(products);
        //ProductDAO.deleteAll(products);
    }
}
