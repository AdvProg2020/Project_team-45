package client.controller.managers;

public interface Deleter extends Manager {
    boolean deleteItemById(String Id) throws Exception;
}
