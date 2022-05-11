package util;

/**
 *
 * @author khoahd.7621
 */
import java.util.Calendar;
import java.sql.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Tools {

    private static Scanner sc = new Scanner(System.in);
    
    public static int getAnInteger(String inputMsg) {
        do {
            try {
                System.out.print(inputMsg);
                int n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (NumberFormatException e) {
                System.err.println("Wrong format number!");
            }
        } while (true);
    }
    
    public static double getADouble(String inputMsg, String errorMsg, double min, double max) {
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        do {
            try {
                System.out.print(inputMsg);
                double n = Double.parseDouble(sc.nextLine());
                if (n <= min || n > max)
                    throw new Exception();
                return n;
            } catch (Exception e) {
                System.err.println(errorMsg);
            }
        } while (true);
    }

    public static String getAString(String inputMsg, String errorMsg) {
        do {
            try {
                System.out.print(inputMsg);
                String s = sc.nextLine().trim();
                if (s.isEmpty())
                    throw new Exception();
                return s;
            } catch (Exception e) {
                System.err.println(errorMsg);
            }
        } while (true);
    }
    
        public static String getAString(String inputMsg) {
        do {
            try {
                System.out.print(inputMsg);
                String s = sc.nextLine().trim();
                if (s.isEmpty())
                    throw new Exception();
                return s;
            } catch (Exception e) {
                System.err.println("Don't accept blank!");
            }
        } while (true);
    }
        
    public static String getAPattern(String inputMsg, String errorMsg, String format) {
        while (true) {
            System.out.print(inputMsg);
            String s = sc.nextLine().trim();
                if (s.length() == 0 || s.isEmpty() || s.matches(format) == false)
                    System.err.println(errorMsg);
                else
                    return s;
        }
    }
    
    public static boolean getYesNo(String inputMsg) {
        while (true) {
            System.out.print(inputMsg);
            String response = sc.nextLine().trim();
            if (response.length() == 0 || response.isEmpty()) {
                System.err.println("Don't accept empty or blank!");
                continue;
            }
            if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes"))
                return true;
            else if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("no"))
                return false;
            else
                System.err.println("Wrong format! Format is Yes-Y/No-N!");
        }
    }
    
    public static boolean validDate(int y, int m, int d){
        int maxd = 31;
        if ((d < 1) || (d > 31) || (m < 1) || (m > 12))
            return false;
        if ((m == 4) || (m == 6) || (m == 9) || (m == 11))
            maxd = 30;
        if (m == 2)
            if ((y % 400 == 0) || ((y % 4 == 0) && (y % 100 != 0)))
 	    	maxd = 29;
            else
        	maxd = 28; 
	return (d <= maxd);
    }
    
    public static long toDate(String ymd) {
        StringTokenizer stk = new StringTokenizer(ymd, "/|-");
        int y = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int d = Integer.parseInt(stk.nextToken());
        if (!validDate(y, m, d))
            return -1;
        Calendar cal = Calendar.getInstance();
        cal.set(y, m - 1, d);
        long t = cal.getTime().getTime();
        return t;
    }
    
    public static Date getADate(String inputMsg, String errorMsg) {
        Date d = new Date(System.currentTimeMillis());
        String regex = "^\\d{4}[-|/]\\d{1,2}[-|/]\\d{1,2}$";
        while (true) {
            System.out.print(inputMsg);
            String inputStr = sc.nextLine().trim();
            if (inputStr.matches(regex)) {
                long t = toDate(inputStr);
                if (t < 0) {
                    System.out.println(errorMsg);
                    continue;
                }
                else if ((new Date(t)).before(d)) {
                    System.out.println("Food is out of date! Please try again!");
                    continue;
                }
                else {
                    d = new Date(t);
                    return d;
                }
            }
            if (inputStr.length() == 0 || inputStr.isEmpty()) {
                System.out.println("Don't accept empty or blank!");
                continue;
            }
            System.out.println("Wrong format! The format is yyyy/m/d");
        }
    }
    
    public static Date getADate(String inputStr) {
        Date d;
        while (true) {
            long t = toDate(inputStr);
            if (t < 0) {}
            else {
                d = new Date(t);
                return d;
            }
        }
    }
}
