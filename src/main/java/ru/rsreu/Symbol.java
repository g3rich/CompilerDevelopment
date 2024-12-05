package ru.rsreu;

import java.util.Objects;

/**
 * Класс, представляющий запись в таблице символов
 */
public class Symbol {

    /**
     * Имя переменной.
     */
    private final String name;

    /**
     * Тип переменной.
     */
    private final VariableType type;

    /**
     * Конструктор символа таблицы символов на основе названия.
     *
     * @param name название переменной.
     */
    public Symbol(String name) {
        this.name = name;
        this.type = VariableType.INT;
    }

    /**
     * Конструктор символа таблицы символов на основе названия и типа.
     *
     * @param name название переменной.
     * @param type тип пемеренной.
     */
    public Symbol(String name, VariableType type) {
        this.name = name;
        this.type = Objects.requireNonNullElse(type, VariableType.INT);

    }

    /**
     * Метод доступа к наименованию переменной.
     *
     * @return наименование переменной.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод доступа к типу переменной
     *
     * @return тип переменной.
     */
    public VariableType getType() {
        return type;
    }

    /**
     * Метод для получения строкового представления символа.
     *
     * @return строковое представление символа.
     */
    @Override
    public String toString() {
        return name + " ["+ type.toString() +"]";
    }
}

