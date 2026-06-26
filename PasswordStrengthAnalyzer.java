import java.util.*;

public class PasswordStrengthAnalyzer {

    // List of common weak passwords
    static String[] commonPasswords = {
            "password", "123456", "123456789", "qwerty",
            "abc123", "admin", "welcome", "password123"
    };

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("     PASSWORD STRENGTH ANALYZER");
        System.out.println("======================================");

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        int score = 0;

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        // Length Check
        if (password.length() >= 12)
            score += 25;
        else if (password.length() >= 8)
            score += 15;

        // Character Checks
        for (char ch : password.toCharArray()) {

            if (Character.isUpperCase(ch))
                hasUpper = true;

            else if (Character.isLowerCase(ch))
                hasLower = true;

            else if (Character.isDigit(ch))
                hasDigit = true;

            else
                hasSpecial = true;
        }

        if (hasUpper)
            score += 15;

        if (hasLower)
            score += 15;

        if (hasDigit)
            score += 15;

        if (hasSpecial)
            score += 15;

        // Check common password
        boolean isCommon = false;

        for (String p : commonPasswords) {
            if (password.equalsIgnoreCase(p)) {
                isCommon = true;
                score -= 20;
                break;
            }
        }

        if (score < 0)
            score = 0;

        // Determine strength
        String strength;

        if (score >= 70)
            strength = "STRONG";
        else if (score >= 50)
            strength = "GOOD";
        else if (score >= 30)
            strength = "FAIR";
        else
            strength = "WEAK";

        // Display Results
        System.out.println("\n----------- RESULT -----------");

        System.out.println("Length              : " + password.length());

        System.out.println("Uppercase Letters   : " + (hasUpper ? "Yes" : "No"));

        System.out.println("Lowercase Letters   : " + (hasLower ? "Yes" : "No"));

        System.out.println("Digits              : " + (hasDigit ? "Yes" : "No"));

        System.out.println("Special Characters  : " + (hasSpecial ? "Yes" : "No"));

        System.out.println("Common Password     : " + (isCommon ? "Yes" : "No"));

        System.out.println("Score               : " + score + "/85");

        System.out.println("Strength            : " + strength);

        // Suggestions
        System.out.println("\nSuggestions:");

        boolean improved = false;

        if (password.length() < 12) {
            System.out.println("✔ Increase password length to at least 12 characters.");
            improved = true;
        }

        if (!hasUpper) {
            System.out.println("✔ Add uppercase letters (A-Z).");
            improved = true;
        }

        if (!hasLower) {
            System.out.println("✔ Add lowercase letters (a-z).");
            improved = true;
        }

        if (!hasDigit) {
            System.out.println("✔ Add numbers (0-9).");
            improved = true;
        }

        if (!hasSpecial) {
            System.out.println("✔ Add special characters (!@#$%^&*).");
            improved = true;
        }

        if (isCommon) {
            System.out.println("✔ Avoid using common passwords.");
            improved = true;
        }

        if (!improved) {
            System.out.println("Excellent! Your password is strong.");
        }

        // Suggested Strong Password
        System.out.println("\nExample Strong Password:");
        System.out.println(generatePassword());

        sc.close();
    }

    // Generates a sample strong password
    public static String generatePassword() {

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digit = "0123456789";
        String special = "@#$%&*!";

        String all = upper + lower + digit + special;

        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        sb.append(upper.charAt(random.nextInt(upper.length())));
        sb.append(lower.charAt(random.nextInt(lower.length())));
        sb.append(digit.charAt(random.nextInt(digit.length())));
        sb.append(special.charAt(random.nextInt(special.length())));

        for (int i = 4; i < 12; i++) {
            sb.append(all.charAt(random.nextInt(all.length())));
        }

        return sb.toString();
    }
}
