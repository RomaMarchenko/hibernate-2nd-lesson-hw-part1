package hibernate.lesson2HW.part1;

public class Demo {
    public static void main(String[] args) {
        System.out.println(ProductDAO.findById(6L));
        //System.out.println(ProductDAO.findById(2L));

        //System.out.println(ProductDAO.findByName("table new"));
        //System.out.println(ProductDAO.findByName("sfafa"));

        //System.out.println(ProductDAO.findByContainedName("table"));
        //System.out.println(ProductDAO.findByContainedName("!!"));

        //System.out.println(ProductDAO.findByPrice(100, 50));
        //System.out.println(ProductDAO.findByPrice(0, 10));

        //System.out.println(ProductDAO.findByNameSortedAsc("table new"));
        //System.out.println(ProductDAO.findByNameSortedDesc("table new"));

        //System.out.println(ProductDAO.findByPriceSortedDesc(100, 50));
        //System.out.println(ProductDAO.findByPriceSortedDesc(0, 10));
    }
}
