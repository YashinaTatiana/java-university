package demo;

import dao.AccountDao;
import dao.OperationsDao;
import dao.UserDao;
import dto.*;
import exception.MobileBankException;
import model.Credentials;
import model.Operation;
import model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.AccountService;
import service.OperationService;
import service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class MobileBankApp {

    private static UserService userService;
    private static AccountService accountService;
    private static OperationService operationService;

    static final Logger logger = LogManager.getLogger(MobileBankApp.class);
    static Scanner sc = new Scanner(System.in);

    public static void initialize() {
        UserDao userDao = new UserDao();
        AccountDao accountDao = new AccountDao();
        OperationsDao operationsDao = new OperationsDao();
        userService = new UserService(userDao);
        accountService = new AccountService(accountDao);
        operationService = new OperationService(operationsDao);
    }

    public static User inputUser() throws MobileBankException {
        System.out.print("Login: ");
        String login = sc.next();
        System.out.print("Password: ");
        String password = sc.next();
        System.out.print("Telephone (10 digits): +7");
        String telephone = sc.next();
        System.out.print("Address: ");
        String address = sc.next();
        return new User(login, password, address, telephone);
    }

    public static void signUp() throws MobileBankException {
        Response response = userService.signUp(inputUser());
        if (response.getError().isEmpty()) {
            logger.info(response.getData());
        } else {
            logger.error(response.getError());
        }
    }

    public static String loginByLogin() {
        System.out.print("Input Login: ");
        String login = sc.next();
        System.out.print("Input Password: ");
        Response<String> response = userService.loginUserByLogin(new Credentials(login, sc.next()));
        if (response.getError().isEmpty()) {
            System.out.println(String.format("Hello, %s!", login));
            return response.getData();
        } else {
            logger.error(response.getError());
            return null;
        }
    }

    public static String loginByPhone() {
        System.out.println("Input Phone(10 digits) +7 : ");
        String phone = sc.next();
        System.out.println("Input Password: ");
        Response<String> response = userService.loginUserByPhone(new Credentials(phone, sc.next()));
        if (response.getError().isEmpty()) {
            logger.info("User is logged in.");
            return  response.getData();
        } else {
            logger.error(response.getError());
            return null;
        }
    }

    public static void createAccount(String token) {
        Response response;
        System.out.print("Input currency [RUB|EUR|USD]:\n");
        String accCode = sc.next();
        System.out.println("Do you want put the amount [Y/N]?");
        if (sc.next().equalsIgnoreCase("Y")) {
            System.out.print("Input amount: ");
            BigDecimal amount = new BigDecimal(sc.next());
            response = accountService.createAccount(new CreateAccountDto(token, amount, accCode));
        } else {
            response = accountService.createAccount(new CreateAccountDto(token, accCode));
        }
        if (response.getError().isEmpty()) {
            logger.info("Account is successfully created");
        } else {
            logger.error(response.getError());
        }
    }

    public static void refillAccount(String token) {
        Response<List<String>> accountsListDto = accountService.getUserAccountIds(token);
        if (!accountsListDto.getError().isEmpty()) {
            logger.error(accountsListDto.getError());
            return;
        }
        System.out.print("Input currency [RUB, USD, EUR]: ");
        String accCode = sc.next();
        System.out.print("Input amount: ");
        BigDecimal amount = sc.nextBigDecimal();
        List<String> accounts = accountsListDto.getData();
        if (accounts.isEmpty()) {
            logger.error("No any account is found!");
            System.out.println("\nPlease create the account first.");
            return;
        }
        Response<String> transactionRes;
        if (accounts.size() == 1) {
            transactionRes = operationService.refillAccount(new AccountOperationsDto(
                    token, accountsListDto.getData().get(0), amount, accCode.toUpperCase()));
        } else {
            String accountId = null;
            while(null == accountId) {
                System.out.print("You have several accounts.\n " +
                        "Please, input the last four digits: ");
                String accountNumber = sc.next();
                accountId = accountsListDto.getData().stream()
                        .filter(account -> account.endsWith(accountNumber))
                        .findFirst().orElse(null);
                }

            transactionRes = operationService.refillAccount(
                    new AccountOperationsDto(token, accountId, amount, accCode.toUpperCase()));
        }
        if (transactionRes.getError().isEmpty()) {
            logger.info("Transaction completed");
        } else {
            logger.error(transactionRes.getError());
        }
    }

    public static void transferMoneyByPhone(String token) {
        System.out.print("Input the phone number (10 digits):+7");
        String phone = sc.next();
        System.out.print("Input currency: ");
        String accCode = sc.next();
        System.out.print("Input Amount: ");
        BigDecimal amount = sc.nextBigDecimal();

        Response<List<String>> response = accountService.getUserAccountIds(token);
        if (!response.getError().isEmpty()) {
            logger.error(response.getError());
            return;
        }
        List<String> userAccounts = response.getData();
        String accountIdFrom = null;
        String accountIdTo = null;
        if (userAccounts.isEmpty()) {
            logger.error("User hasn't any linked account.");
            return;
        } else if (userAccounts.size() == 1) {
            accountIdFrom = userAccounts.get(0);
        } else {
            while (null == accountIdFrom) {
                System.out.print("You have several accounts.\n " +
                        "Please, input the last four digits: ");
                String accountNumber = sc.next();
                accountIdFrom = userAccounts.stream()
                        .filter(account -> account.endsWith(accountNumber))
                        .findFirst().orElse(null);
            }
        }
        userAccounts = accountService.getUserAccountIdsByPhone(phone).getData();
        if (userAccounts.isEmpty()) {
            System.out.println("User hasn't any linked account.");
        } else if (userAccounts.size() == 1) {
            accountIdTo = userAccounts.get(0);
        } else {
            while (null == accountIdTo) {
                System.out.print("You have several accounts.\n " +
                        "Please, input the last four digits: ");
                String accountNumber = sc.next();
                accountIdTo = userAccounts.stream()
                        .filter(account -> account.endsWith(accountNumber))
                        .findFirst().orElse(null);
            }
        }
        Response dto = operationService.moneyTransferByPhone(
                new AccountOperationsDto(token, amount, accCode.toUpperCase(), accountIdFrom, accountIdTo));
        if (dto.getError().isEmpty()) {
            logger.info("Operation successfully passed.");
        } else {
            logger.error(dto.getError());
        }
    }

    public static void getOperationInfo(String token) {
        Response<List<Operation>> res = operationService.getOperationsInfo(token);
        System.out.println("Operations: \n");
        if (res.getError().isEmpty()) {
            res.getData().forEach(x -> System.out.println(x));
        } else {
            logger.error(res.getError());
        }
    }

    public static String logout(String token) {
        Response<String> response = userService.logoutUser(token);
        if (response.getError().isEmpty()) {
            logger.info(response.getData());
        } else {
            logger.error(response.getError());
        }
        return  null;
    }
}
