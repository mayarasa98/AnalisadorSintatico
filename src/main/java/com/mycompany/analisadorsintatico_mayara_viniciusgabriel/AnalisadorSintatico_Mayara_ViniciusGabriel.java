/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.analisadorsintatico_mayara_viniciusgabriel;

import java.util.*;


/* Linguagem 3
    P -> x Q R S
    Q -> y z | z
    R -> w | ε
    S -> y
 */

 /* Palavras Possíveis
    xzwy
    xyzwy
    xyzy
    xzy
 */
public class AnalisadorSintatico_Mayara_ViniciusGabriel {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Definindo a estrutura da tabela de análise sintática, associando os símbolos não terminais (P, Q, R, S) a conjuntos possíveis.        
        Map<Character, HashSet<String>> tabelaAnaliseSintatica = new HashMap<Character, HashSet<String>>();

        // Defindo os conjuntos de strings para cada símbolo não terminal.
        HashSet<String> P = new HashSet<String>();
        P.add("x");
        tabelaAnaliseSintatica.put('P', P);

        HashSet<String> Q = new HashSet<String>();
        Q.add("z");
        Q.add("yz");
        tabelaAnaliseSintatica.put('Q', Q);

        HashSet<String> R = new HashSet<String>();
        R.add("w");
        R.add("");
        tabelaAnaliseSintatica.put('R', R);

        HashSet<String> S = new HashSet<String>();
        S.add("y");
        tabelaAnaliseSintatica.put('S', S);

        // Programa
        // Declarando a pilha 
        Stack<Character> pilhaExecucao;

        // Início do loop
        while (true) {
            System.out.println("Digite uma palavra (ou 'sair' para parar o programa): ");
            String palavra = sc.nextLine();
            if (palavra.equalsIgnoreCase("sair")) {
                break;
            }
            // A pilha é inicializada com os símbolos não terminais, na sequência definida pela gramática.
            pilhaExecucao = new Stack<Character>();
            pilhaExecucao.add('S');
            pilhaExecucao.add('R');
            pilhaExecucao.add('Q');
            pilhaExecucao.add('x');

            // Enquanto a pilha não estiver vazia, o while é executado.
            // Verifica se o elemento do topo da pilha é um símbolo não terminal (P, Q, R, S), através do método tabelaAnaliseSintatica.containsKey.            
            while (!pilhaExecucao.empty()) {
                if (tabelaAnaliseSintatica.containsKey(pilhaExecucao.lastElement())) {
                    // Desempilha
                    boolean desempilhou = false;
                    for (String s : tabelaAnaliseSintatica.get(pilhaExecucao.lastElement())) {
                        if (!s.equals("") && palavra.length() >= s.length() && palavra.substring(0, s.length()).equals(s)) {
                            pilhaExecucao.pop();
                            palavra = palavra.substring(s.length());
                            desempilhou = true;
                            break;
                        }
                    }

                    // Valida o vazio!
                    if (!desempilhou && tabelaAnaliseSintatica.get(pilhaExecucao.lastElement()).contains("")) {
                        pilhaExecucao.pop();
                        desempilhou = true;
                    }

                    // Verifica se o elemento no topo da pilha é um símbolo terminal (um caractere). 
                    // Se for, ele compara o caractere com o primeiro caractere da palavra e, se houver correspondência, desempilha o caractere 
                    // e consome a letra da palavra. Se não houver correspondência, o programa encerra o loop.
                    if (!desempilhou) {
                        break;
                    }
                } else {
                    if (palavra.charAt(0) == pilhaExecucao.lastElement()) {
                        pilhaExecucao.pop();
                        palavra = palavra.substring(1);
                    } else {
                        break;
                    }
                }
            }

            // Se ambos forem verdadeiros (pilhaExecucao.isEmpty() && palavra.length() == 0), o programa imprime "A cadeia é válida!", 
            // caso contrário, imprime "A cadeia não é válida!".
            System.out.println("A cadeia" + ((pilhaExecucao.isEmpty() && palavra.length() == 0) ? " " : " não ") + "é válida!");
        }
    }
}
