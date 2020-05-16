package controller.managers;

public interface Printer extends Manager{
    String getAllInListAsString();
    String printDetailedById(String Id);
}
