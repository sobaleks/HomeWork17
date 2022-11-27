package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {

        //Задание 1
        System.out.println("введите ip адрес на проверку ");
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        Pattern pattern = Pattern.compile("[0-9]{1,3}[.][0-9]{1,3}[.][0-9]{1,3}[.][0-9]{1,3}");
        Matcher matcher = pattern.matcher(ip);
        System.out.println(ip + (matcher.matches() ? " является IP адресом" : " не является IP адресом"));

        //Задание 2
        FileInputStream fileIn = new FileInputStream("/home/aleksandr/Документы/result.txt");
        StringBuilder result = new StringBuilder();
        int i = -1;
        while ((i = fileIn.read()) != -1) {
            result.append((char) i);
        }
        fileIn.close();
        Pattern patternDoc = Pattern.compile("[0-9]{4}[-][0-9]{4}[-][0-9]{2}");
        Matcher matcherDoc = patternDoc.matcher(result);
        LinkedHashMap<String, String> res = new LinkedHashMap<>();
        int indexOfDoc = 1;
        int indexOfTel = 1;
        int indexOfMail = 1;
        while (matcherDoc.find()) {
            res.put("doc_" + indexOfDoc, matcherDoc.group());
            indexOfDoc++;
        }
        Pattern patternTel = Pattern.compile("[+][(][0-9]{2}[)][0-9]{7}");
        Matcher matcherTel = patternTel.matcher(result);
        while (matcherTel.find()) {
            res.put("numberTel_" + indexOfTel, matcherTel.group());
            indexOfTel++;
        }
        Pattern patternMail = Pattern.compile("@.+");
        Matcher matcherMail = patternMail.matcher(result);
        while (matcherMail.find()) {
            res.put("mail_" + indexOfMail, matcherMail.group());
            indexOfMail++;
        }
        System.out.println(res);
    }
}
