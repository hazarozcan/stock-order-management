public class Product {

    public String productname;
    private String barcodenumber;
    private double costprice;
    private double saleprice;
    private int stocklevel;
    private ProductStatus productstatus;

    public Product() {

    }

    public void setBarcodenumber(String barcodenumber) {

        for (int i = 0; i < barcodenumber.length(); i++) {

            if (!(barcodenumber.charAt(i) >= 'a' || barcodenumber.charAt(i) <= 'z')) {
                System.out.println("You cannot enter a negative number or char here");
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
