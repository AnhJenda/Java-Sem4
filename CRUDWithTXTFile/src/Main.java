import java.util.Scanner;

/*
    @author: Dinh Quang Anh
    Date   : 7/25/2023
    Project: CRUDWithTXTFile
*/
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManagement productManagement = new ProductManagement();
        int choose = 0;
        do {
            try {
                System.out.println(" \n1.List Product");
                System.out.println("2.Search Product");
                System.out.println("3.Add Product");
                System.out.println("4.Update Product");
                System.out.println("5.Delete");
                System.out.println("0.Exit\n");
                System.out.println("Choose:");
                choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1:
                        productManagement.getListProduct();
                        break;
                    case 2:
                        productManagement.searchProduct();
                        break;
                    case 3:
                        productManagement.addNewProduct();
                        break;
                    case 4:
                        productManagement.updateProduct();
                        break;
                    case 5:
                        productManagement.deleteProduct();
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid number.");
            }


        } while (choose != 0);
        System.out.println("Bye");
    }
}