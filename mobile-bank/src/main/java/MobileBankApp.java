import dao.AccountDao;
import dao.OperationsDao;
import dao.UserDao;
import dto.*;
import exception.MobileBankException;
import model.Credentials;
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
                    UserResponseDto dto = userService.signInUser(inputUser());
                    if (null == dto.getError() || dto.getError().isEmpty()) {
                        logger.info("User is successfully created");
                    } else {
                        logger.error(dto.getError());
                    }
                } break;
                case "2": {
                    System.out.print("Input Login: ");
                    String login = sc.next();
                    System.out.print("Input Password: ");
                    UserResponseDto dto = userService.loginUserByLogin(new Credentials(login, sc.next()));
                    if (dto.getError() == null || dto.getError().isEmpty()) {
                        System.out.println(String.format("Hello, %s!", login));
                        token = dto.getResponse();
                    } else {
                        logger.error(dto.getError());
                    }
                } break;
                case "3": {
                    System.out.println("Input Phone(10 digits) +7 : ");
                    String phone = sc.next();
                    System.out.println("Input Password: ");
                    UserResponseDto dto = userService.loginUserByPhone(new Credentials(phone, sc.next()));
                    if (dto.getError() == null || dto.getError().isEmpty()) {
                       logger.info("User is logged in.");
                       token = dto.getResponse();
                    } else {
                        logger.error(dto.getError());
                    }
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
                    UserResponseDto dto;
                    System.out.print("Input currency [RUB|EUR|USD]:\n");
                    String accCode = sc.next();
                    System.out.println("Do you want put the amount [Y/N]?");
                    if (sc.next().equalsIgnoreCase("Y")) {
                        System.out.print("Input amount: ");
                        BigDecimal amount = new BigDecimal(sc.next());
                        dto = accountService.createAccount(new CreateAccountDto(token, amount, accCode));
                    } else {
                        dto = accountService.createAccount(new CreateAccountDto(token, accCode));
                    }
                    if (null == dto.getError() || dto.getError().isEmpty()) {
                        logger.info("Account is successfully created");
                    } else if (!dto.getError().isEmpty()) {
                        logger.error(dto.getError());
                    }
                } break;
                case "2": {
                    AccountsResponseDto accountsListDto = accountService.getUserAccountIds(token);
                    if (accountsListDto.getError() == null || accountsListDto.getError().isEmpty()) {
                        System.out.print("Input currency [RUB, USD, EUR]: ");
                        String accCode = sc.next();
                        System.out.print("Input amount: ");
                        BigDecimal amount = sc.nextBigDecimal();
                        OperationsResponseDto transactionRes = new OperationsResponseDto();
                        List<String> accounts = accountsListDto.getAccountList();
                        if (accounts.isEmpty()) {
                            logger.error("No any account is found!");
                            System.out.println("\nPlease create the account first.");
                        }
                        if (accountsListDto.getAccountList().size() == 1) {
                            transactionRes = operationService.refillAccount(new AccountOperationsDto(
                                    token, accountsListDto.getAccountList().get(0), amount, accCode));
                        } else {
                            String accountId = null;
                            while(null == accountId) {
                                System.out.print("You have several accounts.\n " +
                                        "Please, input the last four digits: ");
                                String accountNumber = sc.next();
                                accountId = accountsListDto.getAccountList().stream()
                                        .filter(account -> account.endsWith(accountNumber))
                                        .findFirst().orElse(null);
                            }
                            transactionRes = operationService
                                    .refillAccount(new AccountOperationsDto(token, accountId, amount, accCode));
                        }
                        if (transactionRes.getError() == null || transactionRes.getError().isEmpty()) {
                            logger.info("Transaction completed");
                        } else {
                            logger.error(transactionRes.getError());
                        }
                    } else {
                        logger.error(accountsListDto.getError());
                    }
                } break;
                case "3": {
                    System.out.print("Input the phone number (10 digits):\n+7");
                    String phone = sc.next();
                    System.out.print("Input currency: ");
                    String accCode = sc.next();
                    System.out.print("Input Amount: ");
                    BigDecimal amount = sc.nextBigDecimal();
                    List<String> userAccounts = accountService
                            .getUserAccountIds(token).getAccountList();
                    String accountIdFrom = null;
                    String accountIdTo = null;
                    if (userAccounts.isEmpty()) {
                        logger.error("User hasn't any linked account.");
                        break;
                    } else if (userAccounts.size() == 1) {
                        accountIdFrom = userAccounts.get(0);
                    } else {
                        while(null == accountIdFrom) {
                            System.out.print("You have several accounts.\n " +
                                    "Please, input the last four digits: ");
                            String accountNumber = sc.next();
                            accountIdFrom = userAccounts.stream()
                                    .filter(account -> account.endsWith(accountNumber))
                                    .findFirst().orElse(null);
                        }
                    }
                    userAccounts = accountService.getUserAccountIdsByPhone(phone).getAccountList();
                    if (userAccounts.isEmpty()) {
                        System.out.println("User hasn't any linked account.");
                    } else if (userAccounts.size() == 1) {
                        accountIdTo = userAccounts.get(0);
                    } else {
                        while(null == accountIdTo) {
                            System.out.print("You have several accounts.\n " +
                                    "Please, input the last four digits: ");
                            String accountNumber = sc.next();
                            accountIdTo = userAccounts.stream()
                                    .filter(account -> account.endsWith(accountNumber))
                                    .findFirst().orElse(null);
                        }
                    }
                    OperationsResponseDto dto =
                            operationService.moneyTransferByPhone(
                                    new AccountOperationsDto(token, amount, accCode, accountIdFrom, accountIdTo));
                    if (dto.getError() == null || dto.getError().isEmpty()) {
                        logger.info("Operation successfully passed.");
                    } else {
                        logger.error(dto.getError());
                    }
                }
                break;
                case "4": {
                    OperationsResponseDto res = operationService.getOperationsInfo(token);
                    System.out.println("Operations: \n");
                    if (res.getError() == null || res.getError().isEmpty()) {
                        res.getOperations().forEach(x -> System.out.println(x));
                    } else {
                        logger.error(res.getError());
                    }
                    break;
                }
                case "5": {
                    UserResponseDto dto = userService.logoutUser(token);
                    if (dto.getError() == null || dto.getError().isEmpty()) {
                        logger.info("User logged out...");
                        token = null;
                    } else {
                        logger.error(dto.getError());
                    }
                    break;
                }
                case "6":
                default:
                    token = null;
            }
        }
        return null;
    }

    public static void run() {
        UserDao userDao = new UserDao();
        AccountDao accountDao = new AccountDao();
        OperationsDao operationsDao = new OperationsDao();

        userService = new UserService(userDao);
        accountService = new AccountService(accountDao);
        operationService = new OperationService(operationsDao);
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
