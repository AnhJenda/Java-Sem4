import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/*
    @author: Dinh Quang Anh
    Date   : 6/30/2023
    Project: CRUDWithTXTFile
*/
public class ProductManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            try {
                System.err.println("Options: ");
                System.err.println("1. View list product");
                System.err.println("2. Search product");
                System.err.println("3. Add new product");
                System.err.println("4. Update product");
                System.err.println("5. Delete product");
                System.err.println("0. End program");
                System.err.println("Enter choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        getListProduct();
                        break;
                    case 2:
                        searchProduct();
                        break;
                    case 3:
                        addNewProduct();
                        break;
                    case 4:
                        updateProduct();
                        break;
                    case 5:
                        deleteProduct();
                        break;
                    default:
                        if (choice != 0) {
                            System.out.println("Invalid choice. Please try again.");
                        }
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid number.");
            }


        } while (choice != 0);
        {
            System.out.println("Bye");
        }

        scanner.close();
    }

    private static void getListProduct() {
        List<Product> products = readProductsFromFile();
        if (products.isEmpty()) {
            System.err.println("No product in the list.");
            return;
        }
        System.err.println("Product List:");
        System.err.println("Id\tName\t\t\tManufacturer\t\tSeries\t\t\t\t\tPrice");
        for (Product product : products) {
            System.err.println(
                    product.getId() + "\t" + product.getName() + "\t" + product.getManufacturer() + "\t\t"
                            + product.getSeries() + "\t\t\t" + product.getPrice()
            );
        }
    }

    private static void searchProduct() {
        List<Product> products = readProductsFromFile();
        if (products.isEmpty()) {
            System.err.println("No product in the list.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.err.println("Enter somethings about product like name, manufacturer,...: ");
        String searchString = scanner.nextLine();
        System.err.println("Result: ");
        System.err.println("Id\tName\t\t\tManufacturer\t\t\tSeries\t\t\tPrice");
        for (Product product : products) {
            if (product.getName().toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT))
                    || product.getManufacturer().toLowerCase(Locale.ROOT).toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT))
                    || product.getSeries().toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT))) {
                System.err.println(
                        product.getId() + "\t" + product.getName() + "\t" + product.getManufacturer() + "\t"
                                + product.getSeries() + "\t" + product.getPrice()
                );
            }
        }
    }

    private static void addNewProduct() {
        Scanner scanner = new Scanner(System.in);
        String id;
        String price;
        String name;
        String manufacturer;
        String series;

        List<Product> products = readProductsFromFile();

        do {
            System.out.print("Enter product ID: ");
            id = scanner.nextLine();
            if (isProductIdExists(id, products)) {
                System.out.println("ID already exists!");
            }
            if (id.trim().length() == 0 && id.isEmpty()) {
                System.out.println("Product id can not be blank!Try again");
            }
            if (id.contains(" ")) {
                System.out.println("Product id can not contain white space");
            }
            if (id.contains(",")) {
                System.out.println("Wrong format! Product id can not contain comma");
            }
        } while (isProductIdExists(id, products) || id.isEmpty() || id.contains(" ") || id.contains(","));
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
            if (price.matches("[0-9]+(\\.[0-9]+)?")) {      //input.matches(...) quy định input vào có gì
                Double priceValue = Double.parseDouble(price);
                if (priceValue > 0) {
                    break;
                }
            }
            System.out.println("Price must be a positive number!");
        }
        while (true);

        BigDecimal priceValid = new BigDecimal(price);
        Product product = new Product(id, name, manufacturer, series, priceValid);
        writeProductToFile(product);

        System.out.println("Product added successfully.");
    }

    private static void updateProduct() {
        Scanner scanner = new Scanner(System.in);
        String price;
        String newname;
        String newmanufacturer;
        String newseries;
        String id;
        List<Product> products = readProductsFromFile();
        do {
            System.out.print("Enter ID of the product you want to update: ");
            id = scanner.nextLine();
            if (!isProductIdExists(id, products)) {
                System.out.println("Can not find product with id = " + id);
            }
        }
        while (!isProductIdExists(id, products));

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId().equals(id)) {
                do {
                    System.out.print("Enter product name: ");
                    newname = scanner.nextLine().trim();
                    if (newname.trim().length() == 0 && newname.isEmpty()) {
                        System.out.println("Product name can not be blank!Try again");
                    }
                    if (newname.contains(",")) {
                        System.out.println("Wrong format! Product name can not contain comma");
                    }
                } while (newname.trim().length() == 0 && newname.isEmpty() || newname.contains(","));

                do {
                    System.out.print("Enter manufacturer: ");
                    newmanufacturer = scanner.nextLine().trim();
                    if (newmanufacturer.trim().length() == 0 && newmanufacturer.isEmpty()) {
                        System.out.println("Product manufacturer cannot be blank!Try again");
                    }
                    if (newmanufacturer.contains(",")) {
                        System.out.println("Wrong format! manufacturer can not contain comma");
                    }
                } while (newmanufacturer.trim().length() == 0 && newmanufacturer.isEmpty() || newmanufacturer.contains(","));

                do {
                    System.out.print("Enter series: ");
                    newseries = scanner.nextLine().trim();
                    if (newseries.trim().length() == 0 && newseries.isEmpty()) {
                        System.out.println("Product series cannot be blank!Try again");
                    }
                    if (newseries.contains(",")) {
                        System.out.println("Wrong format! series can not contain comma");
                    }
                } while (newseries.trim().length() == 0 && newseries.isEmpty() || newseries.contains(","));

                do {
                    System.out.print("Enter new price: ");
                    price = scanner.nextLine();
                    if (price.matches("[0-9]+(\\.[0-9]+)?")) {
                        Double priceValue = Double.parseDouble(price);
                        if (priceValue > 0) {
                            break;
                        }
                    }
                    System.out.println("Price must be a positive number!");
                }
                while (true);

                BigDecimal priceValid = new BigDecimal(price);

                Product productUpdate = new Product(id, newname, newmanufacturer, newseries, priceValid);
                products.set(i, productUpdate);
                writeProductsToFile(products);

                System.out.println("Product updated successfully.");
            }
        }
    }

    private static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.err.println("Enter id of the product you want to delete: ");
        String id = scanner.nextLine();
        List<Product> products = readProductsFromFile();
        boolean productFound = false;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId().equals(id)) {
                productFound = true;
                products.remove(product);
                writeProductsToFile(products);
            }
        }
        if (!productFound) {
            System.err.println("Can not find product with id = " + id);
        }
    }

    private static List<Product> readProductsFromFile() {
        List<Product> productList = new ArrayList<>();
        String filePath = "C:\\Users\\Admin\\demo\\CRUDWithTXTFile\\src\\productList.txt";
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
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

                    if (!id.contains(" ") && !id.startsWith("//") && !name.trim().isEmpty() && !manufacturer.trim().isEmpty() && !series.trim().isEmpty()) {
                        Product product = new Product(id, name, manufacturer, series, price);
                        productList.add(product);
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        return productList;
    }


    private static void writeProductToFile(Product product) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Admin\\demo\\CRUDWithTXTFile\\src\\productList.txt", true))) { // append = true để cho phép viết tiếp vào file
            writer.write(product.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    private static void writeProductsToFile(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Admin\\demo\\CRUDWithTXTFile\\src\\productList.txt"))) {
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    private static boolean isProductIdExists(String id, List<Product> productList) {
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
