import java.math.BigDecimal;

/*
    @author: Dinh Quang Anh
    Date   : 6/30/2023
    Project: CRUDWithTXTFile
*/
public class Product {
    private String id;
    private String name;
    private String manufacturer;
    private String series;
    private BigDecimal price;

    public Product(String id, String name, String manufacturer, String series, BigDecimal price){
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.series = series;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return
                id +
                "," + name +
                "," + manufacturer +
                "," + series +
                "," + price;
    }
}
