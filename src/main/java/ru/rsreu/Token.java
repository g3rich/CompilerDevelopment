package ru.rsreu;

public class Token {
    private TokenType type;
    private final Object value;
    private final int id;

    public Token(TokenType type, Object value, int id) {
        this.type = type;
        this.value = value;
        this.id = id;
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

    public void setType(TokenType type) {
        this.type = type;
    }

    public int getId() {
        return this.id;
    }
}
