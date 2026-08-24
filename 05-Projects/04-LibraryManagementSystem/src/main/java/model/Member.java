package model;

public class Member {

    private final int id;
    private final String name;
    private final String email;
    private final String phoneNumber;

    public Member(int id,
                  String name,
                  String email,
                  String phoneNumber) {

        validateId(id);
        validateName(name);
        validateEmail(email);
        validatePhoneNumber(phoneNumber);

        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Member id must be greater than 0.");
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
    }

    private void validateEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank.");
        }

        int atIndex = email.indexOf('@');
        int lastAtIndex = email.lastIndexOf('@');

        if (atIndex == -1 || atIndex != lastAtIndex) {
            throw new IllegalArgumentException("Email must contain exactly one @.");
        }

        if (atIndex == 0) {
            throw new IllegalArgumentException("Email must contain something before @.");
        }

        int dotIndex = email.indexOf('.', atIndex + 1);

        if (dotIndex == -1) {
            throw new IllegalArgumentException("Email must contain a . after @.");
        }

        if (dotIndex == atIndex +1) {
            throw new IllegalArgumentException("There has to be something between . and @.");
        }

        if (dotIndex == email.length() - 1) {
            throw new IllegalArgumentException("The . cannot be at the end of an email.");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be blank.");
        }

        if (!phoneNumber.matches("(?=.*\\d)[0-9+ -]+")) {
            throw new IllegalArgumentException("Phone number contains invalid characters.");
        }
    }
}
