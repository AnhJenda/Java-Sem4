import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/*
    @author: Dinh Quang Anh
    Date   : 6/30/2023
    Project: CRUDWithTXTFile
*/
public class ProductManagement {

    private String filePath = "C:\\Users\\Admin\\demo\\CRUDWithTXTFile\\src\\productList.txt";

    public ProductManagement() {

    }

    public void getListProduct() {
        List<Product> products = readProductsFromFile();
        if (products.isEmpty()) {
            System.err.println("No product in the list.");
            return;
        }

        System.err.println("Product List:");
        System.out.printf("%-25s", "Id");
        System.out.printf("%-25s", "Name");
        System.out.printf("%-25s", "Manufacturer");
        System.out.printf("%-25s", "Series");
        System.out.printf("%-25s", "Price");
        System.out.println();
        for (Product product : products) {
            System.out.printf("%-25s", product.getId());
            System.out.printf("%-25s", product.getName());
            System.out.printf("%-25s", product.getManufacturer());
            System.out.printf("%-25s", product.getSeries());
            System.out.printf("%-25s", product.getPrice());
            System.out.println();
        }
    }

    public void searchProduct() {
        List<Product> products = readProductsFromFile();
        if (products.isEmpty()) {
            System.err.println("No product in the list.");
            return;
        }

        List<Product> foundedProduct = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.err.println("Enter somethings about product like name, manufacturer,...: ");
        String searchString = scanner.nextLine();

        File file = new File(filePath);

        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) { // đọc qua từng dòng
                try {
                    String[] data = line.split(","); // cắt theo dấu "," để được 1 mảng

                    String id = data[0]; // phần tử có index 0 là id
                    String name = data[1];      // phần tử có index 1 là name .....
                    String manufacturer = data[2];
                    String series = data[3];
                    BigDecimal price = new BigDecimal(data[4]);

                    if (name.toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT))
                            || manufacturer.toLowerCase(Locale.ROOT).toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT))
                            || series.toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT))
                            && !id.contains(" ") && !id.startsWith("//") && !name.trim().isEmpty() && !manufacturer.trim().isEmpty() && !series.trim().isEmpty()) {
                        Product product = new Product(id, name, manufacturer, series, price);
                        foundedProduct.add(product);
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
            System.err.println("Result: ");
            System.out.printf("%-25s", "Id ");
            System.out.printf("%-25s", "Name");
            System.out.printf("%-25s", "Producer");
            System.out.printf("%-25s", "Line Product");
            System.out.printf("%-25s", "Price");
            System.out.println();
            for (Product product : foundedProduct) {
                System.out.printf("%-25s", product.getId());
                System.out.printf("%-25s", product.getName());
                System.out.printf("%-25s", product.getManufacturer());
                System.out.printf("%-25s", product.getSeries());
                System.out.printf("%-25s", product.getPrice());
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    public void addNewProduct() {
        Scanner scanner = new Scanner(System.in);
        String price;
        String name;
        String manufacturer;
        String series;

        do {
            System.out.print("Enter product name: ");
            name = scanner.nextLine().trim();
            if (name.trim().length() == 0 && name.isEmpty()) {
                System.out.println("Product name can not be blank!Try again");
            }
            if (name.contains(",")) {
                System.out.println("Wrong format! Product name can not contain comma");
            }
        } while (name.trim().length() == 0 && name.isEmpty() || name.contains(","));

        do {
            System.out.print("Enter manufacturer: ");
            manufacturer = scanner.nextLine();
            if (manufacturer.trim().length() == 0 && manufacturer.isEmpty()) {
                System.out.println("Product manufacturer cannot be blank!Try again");
            }
            if (manufacturer.contains(",")) {
                System.out.println("Wrong format! manufacturer can not contain comma");
            }
        } while (manufacturer.trim().length() == 0 && manufacturer.isEmpty() || manufacturer.contains(","));

        do {
            System.out.print("Enter series: ");
            series = scanner.nextLine();
            if (series.trim().length() == 0 && series.isEmpty()) {
                System.out.println("Product series cannot be blank!Try again");
            }
            if (series.contains(",")) {
                System.out.println("Wrong format! series can not contain comma");
            }
        } while (series.trim().length() == 0 && series.isEmpty() || series.contains(","));
        do {
            System.out.print("Enter price: ");
            price = scanner.nextLine();
            if (price.matches("[0-9]+(\\.[0-9]+)?") && Double.parseDouble(price) > 0) {      //input.matches(...) quy định input vào có gì
                Double priceValue = Double.parseDouble(price);
                if (priceValue > 0) {
                    break;
                }
            }
            System.out.println("Price must be a positive number!");
        }
        while (true);

        BigDecimal priceValid = new BigDecimal(price);

        String generateRandomId = getRandomId(name.substring(0, 2), manufacturer.substring(0, 2));

        Product product = new Product(generateRandomId, name, manufacturer, series, priceValid);
        writeProductToFile(product);

        System.out.println("Product added successfully.");
    }

    public void updateProduct() {
        Scanner scanner = new Scanner(System.in);
        String newprice;
        String newname;
        String newmanufacturer;
        String newseries;
        String idFounded;

        do {
            System.out.print("Enter ID of the product you want to update: ");
            idFounded = scanner.nextLine();
            if (!isProductIdExists(idFounded)) {
                System.out.println("Can not find product with id = " + idFounded);
            }
        }
        while (!isProductIdExists(idFounded));

        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            String line;
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            while ((line = raf.readLine()) != null) { // đọc qua từng dòng
                try {
                    String[] data = line.split(",");    // cắt theo dấu "," để được 1 mảng
                    if (data.length == 5) {
                        String id = data[0];            // phần tử có index 0 là id
                        String name = data[1];          // phần tử có index 1 là name .....
                        String manufacturer = data[2];
                        String series = data[3];
                        BigDecimal price = new BigDecimal(data[4]);

                        if (id.equals(idFounded)) {
                            do {
                                System.out.print("Enter product name: ");
                                newname = scanner.nextLine().trim();
                                if (newname.contains(",")) {
                                    System.out.println("Wrong format! Product name can not contain comma");
                                }
                                if (newname.isEmpty() || newname == null) {
                                    newname = name;
                                }
                            } while (newname.contains(","));

                            do {
                                System.out.print("Enter manufacturer: ");
                                newmanufacturer = scanner.nextLine().trim();
                                if (newmanufacturer.isEmpty() || newmanufacturer == null) {
                                    newmanufacturer = manufacturer;
                                }
                                if (newmanufacturer.contains(",")) {
                                    System.out.println("Wrong format! manufacturer can not contain comma");
                                }
                            } while (newmanufacturer.contains(","));

                            do {
                                System.out.print("Enter series: ");
                                newseries = scanner.nextLine().trim();
                                if (newseries.trim().length() == 0 && newseries.isEmpty()) {
                                    newseries = series;
                                }
                                if (newseries.contains(",")) {
                                    System.out.println("Wrong format! series can not contain comma");
                                }
                            } while (newseries.contains(","));

                            do {
                                System.out.print("Enter new price: ");
                                newprice = scanner.nextLine();
                                if (newprice.matches("[0-9]+(\\.[0-9]+)?")) {
                                    Double priceValue = Double.parseDouble(newprice);
                                    if (priceValue > 0) {
                                        break;
                                    }
                                }
                                System.out.println("Price must be a positive number!");
                            }
                            while (true);

                            BigDecimal priceValid = new BigDecimal(newprice);

                            Product productUpdate = new Product(id, newname, newmanufacturer, newseries, priceValid);

                            // Đưa con trỏ về vị trí bắt đầu của dòng hiện tại
                            raf.seek(raf.getFilePointer() - line.length() - 2);

                            // Ghi dữ liệu mới vào chỗ tương ứng trong file
                            raf.writeBytes(productUpdate.toString());
                            if (raf.getFilePointer() != file.length()) {
                                raf.writeBytes("\r\n"); // Xuống dòng để tạo dòng mới
                            }
                            System.out.println("Update success!");
                        } else {
                            Product productUpdate = new Product(id, name, manufacturer, series, price);
                            raf.seek(raf.getFilePointer() - line.length() - 2);
                            raf.writeBytes(productUpdate.toString());
                            if (raf.getFilePointer() != file.length()) {
                                raf.writeBytes("\r\n"); // Xuống dòng để tạo dòng mới
                            }
                        }
                    } else {
                        raf.seek(raf.getFilePointer() - line.length() - 2);
                        raf.writeBytes(line);
                        if (raf.getFilePointer() != file.length()) {
                            raf.writeBytes("\r\n"); // Xuống dòng để tạo dòng mới
                        }
                    }

                } catch (Exception e) {
                    throw e;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    public void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.err.println("Enter id of the product you want to delete: ");
        String inputId = scanner.nextLine();

        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Can not delete product with id =" + inputId + "\nProduct list is empty!");
                return;
            }
            String line;
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            while ((line = raf.readLine()) != null) {
                try {
                    String[] data = line.split(",");
                    if (data.length == 5) {
                        String id = data[0];
                        String name = data[1];
                        String manufacturer = data[2];
                        String series = data[3];
                        BigDecimal price = new BigDecimal(data[4]);

                        if (id.equals(inputId)) {
                            // Đưa con trỏ về vị trí bắt đầu của dòng hiện tại
                            raf.seek(raf.getFilePointer() - line.length() - 2);

                            line = String.format("%-" + line.length() + "s", "");
                            raf.writeBytes(line);
                            if (raf.getFilePointer() != file.length()) {
                                raf.writeBytes("\r\n"); // Xuống dòng để tạo dòng mới
                            }
                        } else {
                            Product productUpdate = new Product(id, name, manufacturer, series, price);
                            raf.seek(raf.getFilePointer() - line.length() - 2);
                            raf.writeBytes(productUpdate.toString());
                            if (raf.getFilePointer() != file.length()) {
                                raf.writeBytes("\r\n"); // Xuống dòng để tạo dòng mới
                            }
                        }
                    } else {
                        raf.seek(raf.getFilePointer() - line.length() - 2);
                        raf.writeBytes(line);
                        if (raf.getFilePointer() != file.length()) {
                            raf.writeBytes("\r\n"); // Xuống dòng để tạo dòng mới
                        }
                    }


                } catch (Exception e) {
                    throw e;
                }
            }
            System.out.println("Delete success!");

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }


    private List<Product> readProductsFromFile() {
        List<Product> productList = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) { // đọc qua từng dòng
                try {
                    String[] data = line.split(",");     // cắt theo dấu "," để được 1 mảng
                    if (data.length == 5) {
                        String id = data[0];            // phần tử có index 0 là id
                        String name = data[1];          // phần tử có index 1 là name .....
                        String manufacturer = data[2];
                        String series = data[3];
                        BigDecimal price = new BigDecimal(data[4]);

                        if (!id.contains(" ") && !id.startsWith("//") && !name.trim().isEmpty() && !manufacturer.trim().isEmpty() && !series.trim().isEmpty()) {
                            Product product = new Product(id, name, manufacturer, series, price);
                            productList.add(product);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error processing line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return productList;
    }

    private void writeProductToFile(Product product) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // append = true để cho phép viết tiếp vào file
            writer.write(product.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    private boolean isProductIdExists(String id) {
        List<Product> products = this.readProductsFromFile();
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private static String getRandomId(String name, String manu) {
        int max = 20000, min = 1000;
        return name.toUpperCase() + manu.toUpperCase() + ((int) Math.floor(Math.random() * (max - min + 1) + min));
    }
}