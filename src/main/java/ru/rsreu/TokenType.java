package ru.rsreu;

public enum TokenType {
    ID,         // Идентификатор (переменная)
    INT,        // Целочисленная константа
    FLOAT,      // Вещественная константа
    OPERATOR,   // Оператор (+, -, *, /)
    EQUAL,
    PARENTHESIS, // Скобки ( и )
    FUNCTION // ФУНКЦИЯ
}
