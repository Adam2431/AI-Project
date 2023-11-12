package code;

public class Request {
    public String type;
    public int amount;
    public int delay;

    public Request(String type, int amount, int delay) {
        this.type = type;
        this.amount = amount;
        this.delay = delay;
    }

    public Boolean equals(Request request) {
        return type.equals(request.type) && amount == request.amount && delay == request.delay;
    }
}
