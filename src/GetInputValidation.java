
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class GetInputValidation {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public Integer getOption(String message, String error, int min, int max) {
        while (true) {
            try {
                System.out.println(message);
                String input = bufferedReader.readLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Input is empty!");
                    continue;
                }

                // Kiểm tra xem chuỗi có chứa ký tự không hợp lệ (khoảng trắng, tab, ký tự đặc biệt hoặc alphabet) không
                if (input.matches(".*[\\s\\t#@!$%^&*()_+{}:;'\"><>,./?\\\\|~`a-zA-Z]+.*")) {
                    if (input.matches(".*[a-zA-Z].*")) {
                        System.out.println("Input contains alphabet characters.");
                    } else {
                        System.out.println("Input contains special characters (#, @, !, $, %, ^, &, *, (, ), _, +, {, }, :, ;, ', \", <, >, ,, ., /, ?, |, ~, `)");
                    }
                    continue;
                }

                int number = Integer.parseInt(input);

                if (min <= number && number <= max) {
                    return number;
                } else {
                    System.out.println("Number not in range [" + min + " - " + max + "]");
                }

            } catch (Exception e) {
                System.out.println(error);
            }
        }
    }

    public String getString(String message, String error, String regex) {
        while (true) {
            try {
                System.out.println(message);
                String input = bufferedReader.readLine().trim();
                
                if (input.matches(regex)) {
                    return input;
                }

            } catch (IOException e) {
                System.out.println("IOException: " + e);
            }
            System.out.println(error);
        }
    }

    public Double getDouble(String message, String error, double min, double max) {
        while (true) {
            try {
                System.out.println(message);
                double number;
                String input = bufferedReader.readLine();

                number = Double.parseDouble(input);
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Number inrange [" + min + " - " + max + "]");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("IOException|NumberFormatException: " + e);
            }
        }
    }

    public boolean checkInputYN(String message) throws IOException {
        while (true) {
                System.out.print(message);
                String result = bufferedReader.readLine().trim();

                if (result.equalsIgnoreCase("Y")) {
                    return true;
                }

                if (result.equalsIgnoreCase("N")) {
                    return false;
                }
                System.err.println("Please input y/Y or n/N.");
                System.out.print("Enter again: ");
        }
    }

    public boolean checkInputUD(String message) {
        while (true) {
            try {
                System.out.print(message);
                String result = bufferedReader.readLine();
                if (result.equalsIgnoreCase("U")) {
                    return true;
                }
                if (result.equalsIgnoreCase("D")) {
                    return false;
                }
                System.err.println("Please input u/U or d/D.");
                System.out.print("Enter again: ");
            } catch (IOException e) {
                System.out.println("IOException" + e);
            }

        }
    }

    public String checkInputCourse(String message, String currentCourse) {
        String input = "";
        while (true) {
            try {
                System.out.print(message);
                input = bufferedReader.readLine();

                if (input.isEmpty()) {
                    return currentCourse; // Nếu người dùng chỉ nhấn Enter, giữ nguyên giá trị cũ
                }

                //check input course in java/ .net/ c/c++
                if (input.equalsIgnoreCase("Java")
                        || input.equalsIgnoreCase(".Net")
                        || input.equalsIgnoreCase("C/C++")) {
                    return input;
                }
                System.err.println("There are only three courses: Java, .Net, C/C++");
                System.out.print("Enter again: ");
            } catch (IOException e) {
                System.out.println("IOException" + e);
            }
        }
    }

    public String checkInputCourse(String message) {
        String input = "";
        while (true) {
            try {
                System.out.print(message);
                input = bufferedReader.readLine();

                //check input course in java/ .net/ c/c++
                if (input.equalsIgnoreCase("Java")
                        || input.equalsIgnoreCase(".Net")
                        || input.equalsIgnoreCase("C/C++")) {
                    return input;
                }
                System.err.println("There are only three courses: Java, .Net, C/C++");
                System.out.print("Enter again: ");
            } catch (IOException e) {
                System.out.println("IOException" + e);
            }
        }
    }
}
