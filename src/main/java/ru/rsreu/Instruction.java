package ru.rsreu;

import java.util.List;
import java.util.Map;

public class Instruction {
    private GenerationInstructionsEnum manual; // Тип инструкции
    private Token resToken; // Результирующий токен
    private List<Token> tokens; // Список токенов-операндов
    private final SymbolTable symbolTable;

    // Конструктор
    public Instruction(Token manual, Token res, List<Token> tokens, SymbolTable symbolTable) {
        this.manual = GenerationInstructionsFromTokenType.toGetInstruction(manual); // Преобразование типа
        this.resToken = res;
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    // Геттеры и сеттеры (если необходимы)
    public GenerationInstructionsEnum getManual() {
        return manual;
    }

    public void setManual(GenerationInstructionsEnum manual) {
        this.manual = manual;
    }

    public Token getResToken() {
        return resToken;
    }

    public void setResToken(Token resToken) {
        this.resToken = resToken;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    private String getSymbolId(Token token) {
        //System.out.println("provGEt " + token.toString());
        //System.out.println("type =  " + token.getType());
        if (/*token.getType() == TokenType.ID*/ token.toString().contains("#T")) {
            //System.out.println("getsid "+ token.getValue().toString());
            Symbol symbol = (Symbol) token.getValue();
            for (Map.Entry<Integer, Symbol> entry : symbolTable.getSymbols().entrySet()) {
                if (entry.getValue().equals(symbol)) {
                    return String.format("<id,%d>", entry.getKey());
                }
            }
        }
        return token.toString(); // Для констант возвращаем оригинальное значение
    }


    @Override
    public String toString() {
        String res = getSymbolId(resToken);
        //String res = resToken.getValue()/*.split(" - ")[0]*/;
        //System.out.println("toS INs" + res);
        //String tok0 = "";
        String tok0 = getSymbolId(tokens.get(0));
        /*if (tokens.get(0).getType() == TokenType.ID) {
            //System.out.println("prov0 "+ tokens.get(0).toString());
            if ((tokens.get(0).toString()).contains("#T")) {
                tok0 = getSymbolId(tokens.get(0));
            } else {
                tok0 = tokens.get(0).toString().split(" - ")[0];
            }
            //tok0 = getSymbolId(tokens.get(0));
        } else {
            tok0 = tokens.get(0).toString().split(" - ")[0];
        }
        /*if ((tokens.get(0).toString()).contains("#T")) {
            tok0 = getSymbolId(tokens.get(0));
        } else {
            tok0 = tokens.get(0).toString().split(" - ")[0];
        }*/
        if (tokens.size() == 1) {
            return String.format("%s %s %s", manual, res, tok0);
        } else if (tokens.size() == 2  && tokens.get(1) != null) {
            String tok1 = getSymbolId(tokens.get(1));
            //String tok1 = "";
            /*if (tokens.get(1).getType() == TokenType.ID) {
                //System.out.println("prov1 "+ tokens.get(1).toString());
                if ((tokens.get(1).toString()).contains("#T")) {
                    tok1 = getSymbolId(tokens.get(1));
                } else {
                    tok1 = tokens.get(1).toString().split(" - ")[0];
                }
            } else {
                tok1 = tokens.get(1).toString().split(" - ")[0];
            }*/
            /*if ((tokens.get(1).toString()).contains("#T")) {
                tok0 = getSymbolId(tokens.get(0));
            } else {
                tok0 = tokens.get(1).toString().split(" - ")[0];
            }*/
            return String.format("%s %s %s %s", manual, res, tok0, tok1);
        }
        return String.format("%s %s %s", manual, res, tok0);
    }
}
