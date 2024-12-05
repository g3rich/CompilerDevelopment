package ru.rsreu;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Lab1 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Ошибка: недостаточно аргументов.");
            return;
        }

        String mode = args[0].toUpperCase();
        String fileName = args[1].toUpperCase();
        //System.out.println(fileName);
        //System.out.println(mode);
        Validator validator = new Validator();
        String result = validator.validString(fileName);
        if (result != "valid"){
            System.out.println(result);
            return;
        }
        /*if (fileName.length() > 4){
            if (fileName.endsWith(".TXT")){
                if (Arrays.stream(bannedSymbols).anyMatch(fileName::contains)){
                    System.out.println("Ошибка: в названии недопустимы символы: <, >, :, /, \\, |, ?, *, 'пробел', &, #, ~");
                    return;
                }
            } else {
                System.out.println("Ошибка: допустимо только расширение '.txt'");
                return;
            }
        } else {
            System.out.println("Ошибка: название файла должно состоять хотя бы из 1 символа. В конце обязательно должно быть расширение '.txt'. В названии запрещено использовать: <, >, :, /, \\, |, ?, *, 'пробел'");
            return;
        }*/
        var generator = new ExpressionGenerator();
        var translator = new ExpressionTranslator();
        var lexer = new Lexer();

        switch (mode) {
            case "G":
                if (args.length != 5) {
                    System.out.println("Ошибка: неверное количество аргументов для режима генерации.");
                    return;
                }
                try {
                    int numLines = Integer.parseInt(args[2]);
                    int minOperands = Integer.parseInt(args[3]);
                    int maxOperands = Integer.parseInt(args[4]);

                    if (numLines <= 0 || minOperands <= 0 || maxOperands <= 0) {
                        System.out.println("Ошибка: количество строк и количество операндов должны быть положительными числами.");
                        return;
                    }

                    if (minOperands > maxOperands) {
                        System.out.println("Ошибка: минимальное количество операндов не может быть больше максимального.");
                        return;
                    }

                    generator.generate(args[1], numLines, minOperands, maxOperands);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: неверный формат числовых параметров.");
                }
                break;

            case "T":
                if (args.length != 3) {
                    System.out.println("Ошибка: неверное количество аргументов для режима трансляции.");
                    return;
                }
                fileName = args[2].toUpperCase();
                result =  validator.validString(fileName);
                if (result != "valid"){
                    System.out.println(result);
                    return;
                }
                try {
                    translator.translate(args[1], args[2]);
                } catch (IOException e) {
                    System.out.println("Ошибка: не удалось открыть файл для чтения или записи. Файл с таким названием отсутствует");
                }
                break;

            case "SEM": // Новый режим для семантического анализа
                if (args.length != 3) {
                    System.out.println("Ошибка: неверное количество аргументов для режима семантического анализа.");
                    return;
                }
                String semInputFile = args[1];
                String semTreeFile = args[2];
                try {
                    lexer.analyze(semInputFile, "", "");
                    SymbolTable symbolTable =  lexer.getSymbolTable();
                    Parser parser = new Parser(lexer.getTokens());
                    SyntaxNode syntaxTree = parser.parse();
                    // Выполнение семантического анализа
                    //SemanticAnalyzer analyzer = new SemanticAnalyzer();
                    //analyzer.analyze(syntaxTree, symbolTable);
                    SemanticTree semanticTree = SemanticAnalyzer.analyze(syntaxTree, symbolTable);
                    // Визуализация синтаксического дерева с учётом изменений
                    //SyntaxTreeVisualizer.visualize(syntaxTree, semTreeFile);
                    FileWriter.writeStringToFile(semTreeFile, semanticTree.printTree(semanticTree.getRoot(), 0));
                    System.out.println("Семантический анализ завершен. Модифицированное синтаксическое дерево записано в " + semTreeFile);
                } catch (IOException e) {
                    System.out.println("Ошибка: не удалось открыть файл для чтения или записи.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case "SYN":
                if (args.length != 3) {
                    System.out.println("Ошибка: неверное количество аргументов для режима лексического и синтаксического анализа.");
                    return;
                }
                String inputFile = args[1];
                String treeFile = args[2];
                try {
                    lexer.analyze(inputFile, "", "");
                    Parser parser = new Parser(lexer.getTokens());
                    //System.out.println(lexer.getTokens());
                    SyntaxNode syntaxTree = parser.parse();
                    SyntaxTreeVisualizer.visualize(syntaxTree, treeFile);
                    System.out.println("Синтаксический анализ завершен. Синтаксическое дерево записано в syntax_tree.txt");
                } catch (IOException e) {
                    System.out.println("Ошибка: не удалось открыть файл для чтения или записи.");
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                break;
            case "LEX":  // Лексический анализ
                if (args.length != 4) {
                    System.out.println("Ошибка: неверное количество аргументов для режима лексического анализа.");
                    return;
                }

                String inFile = args[1];
                String tokensFile = args[2];
                String symbolsFile = args[3];

                try {
                    lexer.analyze(inFile, tokensFile, symbolsFile);
                    System.out.println("Лексический анализ завершен. Токены записаны в " + tokensFile + ", таблица символов записана в " + symbolsFile);
                } catch (IOException e) {
                    System.out.println("Ошибка: не удалось открыть файл для чтения или записи.");
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                break;

            default:
                System.out.println("Ошибка: неверный режим работы. Используйте G (для генерации), T (для трансляции), LEX (для лексического анализа), SYN (для лексического и синтаксического анализа).");
        }
    }
}