package com.fiap.parquimetro.cliente.utils;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class Validacoes {

    public static boolean isValidCPF(String cpf) {

        // considera-se erro cpf's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11))
            //return new ResponseEntity<>("CPF inválido!", HttpStatus.INTERNAL_SERVER_ERROR);
            return false;

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {

            // calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            // converte o caractere do cpf em um numero por exemplo, transforma o caractere '0' no inteiro 0 (48 eh a posicao de '0' na tabela ASCII)
            for (i = 0; i < 9; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            // converte no respectivo caractere numerico
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);

            // cálculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            // verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                //return new ResponseEntity<>("CPF válido!", HttpStatus.OK);
                return true;
            else
                //return new ResponseEntity<>("CPF inválido!", HttpStatus.INTERNAL_SERVER_ERROR);
                return false;

        } catch (InputMismatchException erro) {
            // return new ResponseEntity<>("CPF inválido!", HttpStatus.INTERNAL_SERVER_ERROR);
            return false;
        }
    }

    public static boolean isValidPassword(String password) {

        // 8-16 characters password with at least one digit, at least one
        // lowercase letter, at least one uppercase letter, at least one
        // special character with no white spaces
        final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";
    
        final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);   

        // Validate a password
        if (PASSWORD_PATTERN.matcher(password).matches()) {
            // return new ResponseEntity<>("A senha é válida!", HttpStatus.OK);
            return true;
        }
        else {
            //return new ResponseEntity<>("A senha não é válida! \n Deve conter de 8 à 16 caracteres; \n 1 caractere minúsculo;" +
            //                            "1 caractere maiúsculo; \n 1 caractere numerico; \n 1 caractere especial.", HttpStatus.INTERNAL_SERVER_ERROR);
            return false;
        }
    }
}
