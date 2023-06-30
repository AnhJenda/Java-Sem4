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
        int choice;

        do {
            System.err.println("Options: ");
            System.err.println("1. View list product");
            System.err.println("2. Search product");
            System.err.println("3. Add new product");
            System.err.println("4. Update product");
            System.err.println("5. Delete product");
            System.err.println("0. End program");
            System.err.println("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

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
                    if (choice != 0){
                        System.out.println("Invalid choice. Please try again.");
                    }
            }
        } while (choice != 0);

        scanner.close();
    }



    private static void getListProduct(){
        List<Product> products = readProductsFromFile();
        if (products.isEmpty()){
            System.err.println("No product in the list.");
            return;
        }
        System.err.println("Product List:");
        System.err.println("Id\tName\t\t\tManufacturer\t\tSeries\t\t\t\t\tPrice");
        for(Product product : products){
            System.err.println(
                    product.getId() + "\t" + product.getName() + "\t" + product.getManufacturer() + "\t\t"
                    + product.getSeries() + "\t\t\t" + product.getPrice()
            );
        }
    }
    private static void searchProduct() {
        List<Product> products = readProductsFromFile();
        if (products.isEmpty()){
            System.err.println("No product in the list.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.err.println("Enter somethings about product like name, manufacturer,...: ");
        String searchString = scanner.nextLine();
        System.err.println("Result: ");
        System.err.println("Id\tName\t\t\tManufacturer\t\t\tSeries\t\t\tPrice");
        for (Product product : products){
            if (product.getName().toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT))
                    || product.getManufacturer().toLowerCase(Locale.ROOT).toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT))
                    || product.getSeries().toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT))){
                System.err.println(
                        product.getId() + "\t" + product.getName() + "\t" + product.getManufacturer() + "\t"
                                + product.getSeries() + "\t" + product.getPrice()
                );
            }
        }
    }

    private static void addNewProduct(){
        Scanner scanner = new Scanner(System.in);

        List<Product> products = readProductsFromFile();

        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (isProductIdExists(id, products)) {
            System.out.println("Product ID already exists. Cannot create a new product with the same ID.");
            return;
        }

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter manufacturer: ");
        String manufacturer = scanner.nextLine();

        System.out.print("Enter series: ");
        String series = scanner.nextLine();

        System.out.print("Enter price: ");
        BigDecimal price = scanner.nextBigDecimal();
        scanner.nextLine();

        Product product = new Product(id, name, manufacturer, series, price);
        writeProductToFile(product);

        System.out.println("Product added successfully.");
    }

    private static void updateProduct(){
        Scanner scanner = new Scanner(System.in);
        System.err.println("Enter the Id of the product you want to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        List<Product> products = readProductsFromFile();
        boolean productFound = false;
        for (int i = 0; i < products.size(); i++){
            Product product = products.get(i);
            if (product.getId() == id){
                productFound = true;
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();

                System.out.print("Enter new manufacturer: ");
                String newManufacturer = scanner.nextLine();

                System.out.print("Enter new series: ");
                String newSeries = scanner.nextLine();

                System.out.print("Enter new price: ");
                BigDecimal newPrice = scanner.nextBigDecimal();
                scanner.nextLine();

                Product productUpdate = new Product(id, newName, newManufacturer, newSeries, newPrice);
                products.set(i, productUpdate);
                writeProductsToFile(products);

                System.out.println("Product updated successfully.");
            }
        }
        if (!productFound){
            System.err.println("Can not find product with id = " + id);
        }
    }

    private static void deleteProduct(){
        Scanner scanner = new Scanner(System.in);
        System.err.println("Enter id of the product you want to delete: ");
        int id = scanner.nextInt();
        List<Product> products = readProductsFromFile();
        boolean productFound = false;
        for (int i = 0; i < products.size(); i++){
            Product product = products.get(i);
            if (product.getId() == id){
                productFound = true;
                products.remove(product);
                writeProductsToFile(products);
            }
        }
        if (!productFound){
            System.err.println("Can not find product with id = " + id);
        }
    }

    private static List<Product> readProductsFromFile() {
        List<Product> productList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Admin\\demo\\CRUDWithTXTFile\\src\\productList.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) { // đọc qua từng dòng
                String[] data = line.split(","); // cắt theo dấu "," để được 1 mảng
                int id = Integer.parseInt(data[0]); // mảng có index 0 là id
                String name = data[1];      // mang có index 1 là name .....
                String manufacturer = data[2];
                String series = data[3];
                BigDecimal price = new BigDecimal(data[4]);
                Product product = new Product(id, name, manufacturer, series, price);
                productList.add(product);
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
            for (Product product : products){
                writer.write(product.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    private static boolean isProductIdExists(int id, List<Product> productList) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
