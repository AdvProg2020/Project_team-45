package controller.userControllers;

public class UsernameIsRequestException extends Exception {
    public UsernameIsRequestException() {
        super("username is Request");
    }
}
