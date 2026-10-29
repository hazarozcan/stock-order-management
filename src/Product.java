public class Product {

    public String productname;
    private String barcodenumber;
    private double costprice;
    private double saleprice;
    private int stocklevel;
    private ProductStatus productstatus;

    public Product(String productname, String barcodenumber, double costprice, double saleprice, int stocklevel) {
        this.productname = productname;
        this.barcodenumber = barcodenumber;
        this.costprice = costprice;
        this.saleprice = saleprice;
        this.stocklevel = stocklevel;
        productstatus = ProductStatus.ACTIVE;

    }

    public void increaseStock(int amount) {
        if (amount > 0) {
            this.stocklevel = this.stocklevel + amount;
        } else {
            System.out.println("You can not add negative or 0 value");
            return;
        }
    }

    public void decreaseStock(int amount) {

        if (amount > 0 && this.stocklevel >= amount) {
            this.stocklevel = this.stocklevel - amount;
        } else {
            System.out.println("Please enter a valid value");
        }
    }

    public void setBarcodenumber(String barcodenumber) {

        if (barcodenumber == null || barcodenumber.isEmpty()) {
            System.out.println("Lutfen geçerli bir değer giriniz!");
            return;
        }
        for (int i = 0; i < barcodenumber.length(); i++) {

            if (!('0' <= barcodenumber.charAt(i) && barcodenumber.charAt(i) <= '9')) {

                System.out.println("Please enter only positive integers");
                return;
            }
        }
        this.barcodenumber = barcodenumber;

    }

    public void setCostprice(double costprice) {
        this.costprice = costprice;
    }

    public void setSaleprice(double saleprice) {
        this.saleprice = saleprice;
    }

    public void setProductstatus(ProductStatus productstatus) {
        this.productstatus = productstatus;
    }

    public String getBarcodenumber() {
        return barcodenumber;
    }

    public double getCostprice() {
        return costprice;
    }

    public double getSaleprice() {
        return saleprice;
    }

    public int getStocklevel() {
        return stocklevel;
    }

    public ProductStatus getProductstatus() {
        return productstatus;
    }

}
