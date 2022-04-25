import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().replaceAll("\\s", "").toUpperCase();
            System.out.println(calc(input));
    }
    public static String calc(String input){

        String operation = "";
        int result = 0;
        String output ="";

        if (input.matches("([VIX0-9]*)\\*([VIX0-9]*)")) operation = "*";
        else if (input.matches("([VIX0-9]*)/([VIX0-9]*)")) operation = "/";
        else if (input.matches("([VIX0-9]*)-([VIX0-9]*)")) operation = "-";
        else if (input.matches("([VIX0-9]*)\\+([VIX0-9]*)")) operation = "+";

        String[] value = input.split("[-+*/]");

        if(value.length>2){
            try {
                throw new RuntimeException();
            } catch (Exception e) {
                System.out.println("throws Exception: недопустимая операция");
            }
        }

        if (value[0].matches("[VIX]*") && value[1].matches("[VIX]*")
                && value[0].length()==1 && value[1].length()==1) {

            Rome rome1 = Rome.valueOf(value[0]);
            Rome rome2 = Rome.valueOf(value[1]);
            result = Calculate(rome1.getConvert(),rome2.getConvert(),operation);

            if (result > 10 && result <= 100){

                String tens = "";
                String units = "";

                Rome[] romes = Rome.values();
                for (Rome s:romes) {
                    if(s.ordinal()+1 == result%10) units = String.valueOf(s);
                }

                for (int i = 0; i < (result/10); i++){
                    tens = tens.concat("X");
                    if(i == 3) tens = "XL";
                    else if(i == 4) tens = "L";
                    else if(i == 8) tens = "XC";
                    else if(i == 9) tens = "C";
                }

                output = tens + units;
            }
            else if(result <= 10 && result > 0){

                Rome[] romes = Rome.values();
                for (Rome s:romes) {
                    if(s.ordinal()+1 == result) System.out.println(String.valueOf(s));
                }
            }
            else {
                try {
                    throw new ArithmeticException();
                } catch (Exception e) {
                    System.out.println("throws Exception: в римской системе нет отрицательных чисел");
                }
            }

        } else if (value[0].matches("[0-9]*") && value[1].matches("[0-9]*") &&
                Integer.parseInt(value[0])<=10 && Integer.parseInt(value[1])>=0) {

            result = Calculate(Integer.parseInt(value[0]),Integer.parseInt(value[1]),operation);
            output = String.valueOf(result);
        }
        else{
            try {
                throw new RuntimeException();
            } catch (Exception e) {
                System.out.println("throws Exception: некорректный ввод");
            }
        }

        return output;
    }

    public static int Calculate(int x, int y, String operator) {

        int result = 0;

        try {
            switch (operator) {
                case "-":
                    result = x - y;
                    break;
                case "+":
                    result = x + y;
                    break;
                case "*":
                    result = x * y;
                    break;
                case "/":
                    result = x / y;
                    break;
            }

        }catch (ArithmeticException e){
            System.out.println("throws Exception: деление на ноль");
        }

        return result;
    }
}