import java.util.*;

/*
    @author: Dinh Quang Anh
    Date   : 7/4/2023
    Project: Test
*/
public class MultiThreadingExample extends Thread{
    private String threadName;

    public MultiThreadingExample(String threadName){
        this.threadName = threadName;
    }

    public void run (){
        System.err.println("Thread " + threadName + " is running");
    }

    public static void main(String[] args) {
        MultiThreadingExample thread1 = new MultiThreadingExample("thread1");
        MultiThreadingExample thread2 = new MultiThreadingExample("thread2");
        thread1.start();
        thread2.start();

        int arr[] ={3,60,35,2,45,320,5};

        System.out.println("Array Before Bubble Sort");
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        bubbleSort(arr);//sorting array elements using bubble sort

        System.out.println("Array After Bubble Sort");
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }

        Queue<String> queue = new LinkedList<>();

        // Thêm phần tử vào hàng đợi
        queue.add("Apple");
        queue.add("Banana");
        queue.add("Orange");

        // In ra các phần tử trong hàng đợi theo thứ tự FIFO
        while (!queue.isEmpty()) {
            String element = queue.poll();
            System.out.println(element);
        }

        LinkedHashMap<String, Integer> data = new LinkedHashMap<>();
        data.put("key1", 10);
        data.put("Key2", 20);

        System.out.println(data.get("key1"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap 3 so bat ky: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        int n2 = scanner.nextInt();
        scanner.nextLine();
        int n3 = scanner.nextInt();
        scanner.nextLine();
// check tam giac vuong

        if (n*n + n2*n2 == n3*n3 || n*n + n3*n3 == n2*n2 || n2*n2 + n3*n3 == n*n){
            System.out.println("Dung hinh tam giac vuong cmnr");
        } else {
            System.out.println("K phải hinh tam giac vuong dau");
        }
//      check tam giac thuong
//        if (n + n2 > n3 && n + n3 > n2 && n2 + n3 > n ){
//            System.out.println("Dung hinh tam giac cmnr");
//        } else {
//            System.out.println("K phải hinh tam giac dau");
//        }

        // check so nguyen am-duong
//        if (n >= 0){
//            System.out.println("Day la so nguyen duong");
//        } else {
//            System.out.println("day la so nguyen am");
//        }


    }

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        int temp = 0;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(arr[i] < arr[j]){
                    //swap elements
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }

            }
        }

    }
}
