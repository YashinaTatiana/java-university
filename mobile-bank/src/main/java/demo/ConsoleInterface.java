package demo;

import exception.MobileBankException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class ConsoleInterface {

    static final Logger logger = LogManager.getLogger(MobileBankApp.class);
    static Scanner sc = new Scanner(System.in);

    public static String guestDialog() throws MobileBankException {
        String token = null;
        while (null == token) {
            System.out.print(
                    "\n--------------------------------" +
                            "\n-------Choose the action:-------" +
                            "\n--------------------------------" +
                            "\n1 - Sign In;" +
                            "\n2 - Login by login;" +
                            "\n3 - Login by phone;\n>>");
            String choice = sc.next();
            switch (choice) {
                case "1": {
                    MobileBankApp.signUp();
                } break;
                case "2": {
                    token = MobileBankApp.loginByLogin();
                } break;
                case "3": {
                    token = MobileBankApp.loginByPhone();
                } break;
            }
        }
        return token;
    }

    public static String authorizedDialog(String token) {
        while (token != null) {
            System.out.print(
                    "\n--------------------------------" +
                            "\n-------Choose the action:-------" +
                            "\n--------------------------------" +
                            "\n1 - Create account" +
                            "\n2 - Refill account;" +
                            "\n3 - Transfer money to account by phone number;" +
                            "\n4 - Get operations history" +
                            "\n5 - Log out" +
                            "\n6 - Exit...\n>> ");
            Scanner sc = new Scanner(System.in);
            String choice = sc.next();
            switch (choice) {
                case "1": {
                    MobileBankApp.createAccount(token);
                } break;
                case "2": {
                    MobileBankApp.refillAccount(token);
                } break;
                case "3": {
                    MobileBankApp.transferMoneyByPhone(token);
                } break;
                case "4": {
                    MobileBankApp.getOperationInfo(token);
                } break;
                case "5": {
                    token = MobileBankApp.logout(token);
                } break;
                case "6":
                default:
                    token = null;
            }
        }
        return null;
    }

    public static void run() {
        MobileBankApp.initialize();
        String token = null;
        while (true) {
            try {
                if (null == token) {
                    token = guestDialog();
                } else {
                    token = authorizedDialog(token);
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        }
    }
}
