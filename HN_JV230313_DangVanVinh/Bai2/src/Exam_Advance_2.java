import java.util.Scanner;
import java.util.Stack;

public class Exam_Advance_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Bước 1: Lấy số ISBN gồm 10 chữ số từ người dùng.
        System.out.print("Nhập số ISBN (10 chữ số): ");
        String isbn = scanner.nextLine();

        // Bước 2: Kiểm tra xem người dùng có nhập số có mười chữ số hay không.
        if (isbn.length() != 10) {
            System.out.println("Số ISBN phải có đúng 10 chữ số.");
            return;
        }

        // Bước 3: Sử dụng Stack để lưu trữ các chữ số và thực hiện tính tổng theo công thức.
        Stack<Integer> digitStack = new Stack<>();
        int sum = 0;

        try {
            for (int i = 0; i < 10; i++) {
                int digit = Character.getNumericValue(isbn.charAt(i));
                digitStack.push(digit);
                sum += (i + 1) * digit;
            }

            // Bước 4: Kiểm tra điều kiện và đưa ra kết luận số người dùng nhập có phải ISBN hợp lệ không.
            if (sum % 11 == 0) {
                System.out.println("ISBN hợp lệ.");
            } else {
                System.out.println("ISBN không hợp lệ.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Số ISBN phải chứa duy nhất các chữ số.");
        }
    }
}
