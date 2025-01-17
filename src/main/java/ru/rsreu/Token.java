package ru.rsreu;

public class Token {
    private final TokenType type;
    private final Object value;

    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (type == TokenType.ID) {
            return "<id," + value + ">";
        }
        return "<" + value + ">";
    }
}
