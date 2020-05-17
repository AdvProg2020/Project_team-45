package controller.managers;

public interface Deleter extends Printer {
    boolean deleteItemById(String Id) throws Exception;
}
