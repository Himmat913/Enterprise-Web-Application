class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String message) {
        super(message);
    }
}

class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

class AccountLockedException extends Exception {
    public AccountLockedException(String message) {
        super(message);
    }
}

class LoginSystem {
    private String correctUsername = "admin";
    private String correctPassword = "pass123";
    private int maxAttempts = 3;
    private int failedAttempts = 0;
    private boolean locked = false;

    public void login(String username, String password)
            throws InvalidUsernameException, InvalidPasswordException, AccountLockedException {
        try {
            if (locked) {
                throw new AccountLockedException("Account is locked due to too many failed attempts");
            }
            if (!username.equals(correctUsername)) {
                throw new InvalidUsernameException("Invalid username");
            }
            if (!password.equals(correctPassword)) {
                throw new InvalidPasswordException("Invalid password");
            }
            System.out.println("Login successful");
            failedAttempts = 0;
        } catch (InvalidUsernameException | InvalidPasswordException e) {
            failedAttempts++;
            if (failedAttempts >= maxAttempts) {
                locked = true;
            }
            throw e;
        } finally {
            System.out.println("Login attempt processed");
        }
    }
}

public class Ex6_LoginSystem {
    public static void main(String[] args) {
        LoginSystem system = new LoginSystem();
        String[][] attempts = {
            {"admin", "wrongpass"},
            {"wronguser", "pass123"},
            {"admin", "wrongagain"},
            {"admin", "pass123"}
        };

        for (String[] attempt : attempts) {
            try {
                system.login(attempt[0], attempt[1]);
            } catch (InvalidUsernameException | InvalidPasswordException | AccountLockedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
