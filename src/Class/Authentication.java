package Class;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Authentication {
    // Class to represent a user's account details
    public static class UserAccount {
        private String ID_Unique;
        private String UserName;
        private String Password;
        private String Token; // For session management or JWT token
        private int Intentos_Fallidos; // Tracks failed login attempts
        private String EstadoCuenta; // e.g., Active, Locked, Disabled
        private LocalDateTime UltimoAcceso; // Last access timestamp

        // Constructor
        public UserAccount(String ID_Unique, String UserName, String Password) {
            this.ID_Unique = ID_Unique;
            this.UserName = UserName;
            this.Password = Password;
            this.Token = "";
            this.Intentos_Fallidos = 0;
            this.EstadoCuenta = "Active";
            this.UltimoAcceso = null;
        }




        // Getters and Setters
        public String getID_Unique() {
            return ID_Unique;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public int getIntentos_Fallidos() {
            return Intentos_Fallidos;
        }

        public void incrementIntentos_Fallidos() {
            this.Intentos_Fallidos++;
        }

        public void resetIntentos_Fallidos() {
            this.Intentos_Fallidos = 0;
        }

        public String getEstadoCuenta() {
            return EstadoCuenta;
        }

        public void setEstadoCuenta(String EstadoCuenta) {
            this.EstadoCuenta = EstadoCuenta;
        }

        public LocalDateTime getUltimoAcceso() {
            return UltimoAcceso;
        }

        public void setUltimoAcceso(LocalDateTime UltimoAcceso) {
            this.UltimoAcceso = UltimoAcceso;
        }
    }

    private Map<String, UserAccount> users; // Map to store users by their ID_Unique

    // Constructor
    public Authentication() {
        users = new HashMap<>();
    }

    // Register a new user account
    public void registerUser(String ID_Unique, String UserName, String Password) {
        if (!users.containsKey(ID_Unique)) {
            users.put(ID_Unique, new UserAccount(ID_Unique, UserName, Password));
        } else {
            System.out.println("User already registered.");
        }
    }

    // Log in a user
    public boolean login(String UserName, String Password) {
        for (UserAccount user : users.values()) {
            if (user.getUserName().equals(UserName)) {
                if (user.getPassword().equals(Password)) {
                    user.setUltimoAcceso(LocalDateTime.now());
                    user.resetIntentos_Fallidos();
                    user.setToken(generateToken());
                    System.out.println(UserName + " successfully logged in.");
                    return true;
                } else {
                    user.incrementIntentos_Fallidos();
                    if (user.getIntentos_Fallidos() >= 3) {
                        user.setEstadoCuenta("Locked");
                        System.out.println("Account locked due to too many failed attempts.");
                    } else {
                        System.out.println("Incorrect password.");
                    }
                    return false;
                }
            }
        }

        System.out.println("User not found.");
        return false;
    }



    // Log out a user
    public boolean logout(String UserName) {
        for (UserAccount user : users.values()) {
            if (user.getUserName().equals(UserName) && !user.getToken().isEmpty()) {
                user.setToken("");
                System.out.println(UserName + " successfully logged out.");
                return true;
            }
        }
        System.out.println("User not logged in.");
        return false;
    }

    // Generate a token (simple version for demo purposes)
    private String generateToken() {
        return "TOKEN_" + System.currentTimeMillis();
    }

    // Get the account status
    public String getAccountStatus(String UserName) {
        for (UserAccount user : users.values()) {
            if (user.getUserName().equals(UserName)) {
                return user.getEstadoCuenta();
            }
        }
        return "User not found.";
    }


}
