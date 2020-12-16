package domain;

public enum Status {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    static {
        WIN.opposite = LOSE;
        DRAW.opposite = DRAW;
        LOSE.opposite = WIN;
    }

    private final String status;
    private Status opposite;

    Status(String status) {
        this.status = status;
    }

    public Status getOpposite() {
        return opposite;
    }
}
