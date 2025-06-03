package enums;

public enum Operation {
    ADD("+"),
    SUB("–"),
    MUL("×"),
    DIV("÷"),
    SQUARE("x²"),
    SQRT("√"),
    INV("¹⁄ₓ"),
    MOD("mod");

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Operation fromSymbol(String symbol) {
        for(Operation operation : Operation.values()) {
            if(operation.getSymbol().equals(symbol)) {
                return operation;
            }
        }
        return null;
    }
}
