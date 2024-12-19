package ru.rsreu;

public enum TokenType {
    ID,         // Идентификатор (переменная)
    INT,        // Целочисленная константа
    FLOAT,      // Вещественная константа
    OPERATOR,   // Оператор (+, -, *, /)
    EQUAL,
    PARENTHESIS, // Скобки ( и )
    FUNCTION; // ФУНКЦИЯ

    @Override
    public String toString() {
        return switch (this) {
            case INT -> "целый";
            case FLOAT -> "вещественный";
            default -> "неизвестный тип";
        };
    }
}




