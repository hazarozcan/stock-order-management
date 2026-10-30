public class Product {

    private String productname;
    private String barcodenumber;
    private double costprice;
    private double saleprice;
    private int stocklevel;
    private ProductStatus productstatus;

    public Product(String productname, String barcodenumber, double costprice, double saleprice, int stocklevel) {

        setProductname(productname);
        setBarcodenumber(barcodenumber);
        setCostprice(costprice);
        setSaleprice(saleprice);
        setStocklevel(stocklevel);
        this.productstatus = ProductStatus.ACTIVE;

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
            throw new IllegalArgumentException("Please enter a valid value");
        }
        for (int i = 0; i < barcodenumber.length(); i++) {

            if (!('0' <= barcodenumber.charAt(i) && barcodenumber.charAt(i) <= '9')) {

                throw new IllegalArgumentException("Please enter only positive integers");
            }
        }
        this.barcodenumber = barcodenumber;
    }

    public void setCostprice(double costprice) {
        if (costprice > 0) {
            this.costprice = costprice;

        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setSaleprice(double saleprice) {
        if (saleprice > 0) {
            this.saleprice = saleprice;

        } else {
            throw new IllegalArgumentException();
        }
    }

    private void setStocklevel(int stocklevel) {
        if (stocklevel >= 0) {
            this.stocklevel = stocklevel;
        } else {
            throw new IllegalArgumentException("Enter a valid value");
        }

    }

    public void setProductstatus(ProductStatus productstatus) {
        if (productstatus == null) {
            throw new IllegalArgumentException("Please enter a valid value");
        } else if (this.productstatus == ProductStatus.DISCONTINUED) {
            throw new IllegalArgumentException("Status updates cannot be performed on sales have ended products!");
        } else {
            this.productstatus = productstatus;
        }

    }

    public void setProductname(String productname) {
        if (productname == null || productname.isBlank()) {
            throw new IllegalArgumentException("Please enter a valid value");

        } else {
            this.productname = productname;
        }
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

    public String getProductname() {
        return productname;
    }

}
