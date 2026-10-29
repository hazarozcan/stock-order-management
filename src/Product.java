public class Product {

    public String productname;
    private String barcodenumber;
    private double costprice;
    private double saleprice;
    private int stocklevel;
    private ProductStatus productstatus;

    public Product() {

    }

    public void increaseStock(int amount) {
        if (amount >= 0) {
            this.stocklevel = this.stocklevel + amount;
        } else {
            System.out.println("You can not add negative value");
            return;
        }
    }

    public void decreaseStock(int amount) {
        this.stocklevel = this.stocklevel - amount;
        if (this.stocklevel < 0) {
            this.stocklevel = this.stocklevel - amount;
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

    public void setStocklevel(int stocklevel) {
        this.stocklevel = stocklevel;
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
