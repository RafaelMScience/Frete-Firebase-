package com.navegam.fepsfrete.Utils

import java.util.*

class CNPJUtil {

    companion object {

        fun isCNPJ(CNPJ: String): Boolean {

            // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
            //CNPJClean para limpar os . / e -
            val CNPJClean = CNPJ.replace(".", "")
                    .replace("/", "")
                    .replace("-", "")

            if (CNPJClean == "00000000000000" || CNPJClean == "11111111111111" ||
                    CNPJClean == "22222222222222" || CNPJClean == "33333333333333" ||
                    CNPJClean == "44444444444444" || CNPJClean == "55555555555555" ||
                    CNPJClean == "66666666666666" || CNPJClean == "77777777777777" ||
                    CNPJClean == "88888888888888" || CNPJClean == "99999999999999" ||
                    CNPJClean.length != 14)
                return false


            val dig13: Char
            val dig14: Char

            var sm: Int
            var i: Int
            var r: Int
            var num: Int
            var peso: Int


            // "try" - protege o código para eventuais erros de conversao de tipo (int)
            try {
                // Calculo do 1o. Digito Verificador
                sm = 0
                peso = 2
                i = 11
                while (i >= 0) {
                    // converte o i-ésimo caractere do CNPJ em um número:
                    // por exemplo, transforma o caractere '0' no inteiro 0
                    // (48 eh a posição de '0' na tabela ASCII)
                    num = CNPJClean[i].toInt() - 48
                    sm += num * peso
                    peso += 1
                    if (peso == 10)
                        peso = 2
                    i--
                }

                r = sm % 11

                if (r == 0 || r == 1)
                    dig13 = '0'
                else
                    dig13 = (11 - r + 48).toChar()

                // Calculo do 2o. Digito Verificador
                sm = 0
                peso = 2
                i = 12

                while (i >= 0) {

                    num = CNPJClean[i].toInt() - 48
                    sm += num * peso
                    peso += 1
                    if (peso == 10)
                        peso = 2
                    i--
                }

                r = sm % 11

                if (r == 0 || r == 1)

                    dig14 = '0'
                else
                    dig14 = (11 - r + 48).toChar()


                // Verifica se os dígitos calculados conferem com os dígitos informados.

                return !(dig13 != CNPJClean[12] || dig14 != CNPJClean[13])

            } catch (erro: InputMismatchException) {
                return false
            }

        }
    }
}
