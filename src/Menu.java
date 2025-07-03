import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final ConversorDeMoedas conversor;

    public Menu(ConversorDeMoedas conversor) {
        this.conversor = conversor;
    }

    public void iniciar() {
        String[] opcoes = {
                "USD para BRL (Dólar para Real)",
                "BRL para USD (Real para Dólar)",
                "EUR para USD (Euro para Dólar)",
                "USD para EUR (Dólar para Euro)",
                "BRL para EUR (Real para Euro)",
                "EUR para BRL (Euro para Real)"
        };

        while (true) {
            System.out.println("\n--- Menu de Conversão de Moedas ---");
            for (int i = 0; i < opcoes.length; i++) {
                System.out.printf("%d - %s%n", i + 1, opcoes[i]);
            }
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int escolha;
            try {
                escolha = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                continue;
            }

            if (escolha == 0) {
                System.out.println("Programa finalizado. Até mais!");
                break;
            }

            if (escolha < 1 || escolha > opcoes.length) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            System.out.print("Informe o valor para converter: ");
            double valor;
            try {
                valor = Double.parseDouble(scanner.nextLine());
                if (valor < 0) {
                    System.out.println("Valor deve ser positivo.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número decimal válido.");
                continue;
            }

            String from = "", to = "";
            switch (escolha) {
                case 1 -> { from = "USD"; to = "BRL"; }
                case 2 -> { from = "BRL"; to = "USD"; }
                case 3 -> { from = "EUR"; to = "USD"; }
                case 4 -> { from = "USD"; to = "EUR"; }
                case 5 -> { from = "BRL"; to = "EUR"; }
                case 6 -> { from = "EUR"; to = "BRL"; }
            }

            try {
                double resultado = conversor.converter(from, to, valor);
                System.out.printf("%.2f %s equivalem a %.2f %s%n", valor, from, resultado, to);
            } catch (Exception e) {
                System.out.println("Erro ao obter taxa de câmbio: " + e.getMessage());
            }
        }
    }
}
