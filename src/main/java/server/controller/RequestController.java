package server.controller;

import server.controller.managers.Manager;
import server.model.Comment;
import server.model.Market;
import server.model.product.Product;
import server.model.request.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RequestController implements Manager {
    private static final RequestController instance = new RequestController();
    private final Market market = Market.getInstance();

    // TODO : bring apply here?

    private RequestController() {
    }


    public static RequestController getInstance() {
        return instance;
    }

    public Request createRequestByRequestName(String requestName) {
        return null;
    }

    @Override
    public Request getItemById(String Id) {
        return market.getRequestById(Id);
    }

    public List<String> getAllRequestsIds() {
        return market.getAllRequests().stream().map(Request::getId).collect(Collectors.toList());
    }

    public HashMap<String, String> getRequestInfo(String requestId) {
        HashMap<String, String> information = new HashMap<>();
        Request request = market.getRequestById(requestId);
        information.put("status", request.getRequestStatus());
        information.put("type", request.getType());


        return information;
    }

    public HashMap<String, String> getRequestFieldsAndValues(String requestId) {
        return market.getRequestById(requestId).getFieldsAndValues();
    }

    public HashMap<String, String> getRequestsCommentInfo(String requestId) {
        Comment comment = ((CommentRequest) market.getRequestById(requestId)).getComment();
        HashMap<String, String> information = new HashMap<>();
        information.put("title", comment.getTitle());
        information.put("text", comment.getContent());
        return information;
    }

    public String getSellersId(String requestId) {
        Request request = market.getRequestById(requestId);
        switch (request.getType()) {
            case "seller register":
                return ((SellerRegisterRequest) request).getSeller().getUsername();
            case "add product":
                return ((AddProductRequest) request).getSeller().getUsername();
            case "remove product":
                return ((RemoveProductRequest) request).getSeller().getUsername();
            case "new comment":
                return ((CommentRequest) request).getComment().getUser().getUsername();
            default:
                return ((ProductEditionRequest) request).getSeller().getUsername();
        }
    }

    public String getOffId(String requestId) {
        Request request = market.getRequestById(requestId);
        if (request.getType().equals("add off"))
            return ((AddOffRequest) request).getOff().getId();
        else
            return ((OffEditionRequest) request).getOffId();
    }

    public String getProductId(String requestId) {
        Request request = market.getRequestById(requestId);
        switch (request.getType()) {
            case "add product":
                return ((AddProductRequest) request).getProductSellInfo().getId();
            case "remove product": {
                String productId = ((RemoveProductRequest) request).getProductId();
                return ((RemoveProductRequest) request).getSeller().getAvailableProductSellInfoById(productId).getId();
            }
            case "new comment":
                Product product = ((CommentRequest) request).getComment().getProduct();
                return product.getDefaultSellInfo().getId();
            default: {
                String productId = ((ProductEditionRequest) request).getProductId();
                return ((ProductEditionRequest) request).getSeller().getAvailableProductSellInfoById(productId).getId();
            }
        }
    }
}
