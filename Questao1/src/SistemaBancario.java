import java.util.Scanner;

class ContaBancaria {
    protected String titular;
    protected double saldo;

    public ContaBancaria(String titular) {
        this.titular = titular;
        this.saldo = 0.0;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso!");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    public void exibirDados() {
        System.out.println("Titular: " + this.titular);
        System.out.println("Saldo: R$" + String.format("%.2f", this.saldo));
    }
}

class ContaCorrente extends ContaBancaria {
    private double chequeEspecial;

    public ContaCorrente(String titular) {
        super(titular);
        this.chequeEspecial = 1000.0;
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= (this.saldo + this.chequeEspecial)) {
            if (valor > this.saldo) {
                double diferenca = valor - this.saldo;
                this.chequeEspecial -= diferenca;
                this.saldo = 0;
            } else {
                this.saldo -= valor;
            }
            System.out.println("Saque de R$" + valor + " realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente, incluindo o limite de cheque especial.");
        }
    }

    public void usarChequeEspecial() {
        System.out.println("Cheque especial disponível: R$" + String.format("%.2f", this.chequeEspecial));
    }
}

class ContaPoupanca extends ContaBancaria {
    private double selic;

    public ContaPoupanca(String titular, double selic) {
        super(titular);
        this.selic = selic;
    }

    public void calcularRendimento() {
        double rendimento;
        if (selic > 8.5) {
            rendimento = 0.005 * this.saldo;
        } else {
            rendimento = 0.007 * selic * this.saldo;
        }
        this.saldo += rendimento;
        System.out.println("Rendimento calculado: R$" + String.format("%.2f", rendimento));
        System.out.println("Novo saldo: R$" + String.format("%.2f", this.saldo));
    }
}

public class SistemaBancario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Sistema Bancário!");
        System.out.print("Digite o nome do titular: ");
        String nomeTitular = scanner.nextLine();

        while (true) {
            System.out.println("\nSelecione o tipo de conta:");
            System.out.println("1. Conta Corrente");
            System.out.println("2. Conta Poupança");
            System.out.print("Escolha uma opção: ");
            String opcaoConta = scanner.nextLine();

            if (opcaoConta.equals("1")) {
                ContaCorrente contaCorrente = new ContaCorrente(nomeTitular);
                menuContaCorrente(contaCorrente, scanner);
                break;
            } else if (opcaoConta.equals("2")) {
                System.out.print("Informe a taxa Selic atual (%): ");
                double selic = scanner.nextDouble();
                scanner.nextLine();  // Consumir nova linha
                ContaPoupanca contaPoupanca = new ContaPoupanca(nomeTitular, selic);
                menuContaPoupanca(contaPoupanca, scanner);
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }

    public static void menuContaCorrente(ContaCorrente conta, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Menu Conta Corrente ---");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Usar cheque especial");
            System.out.println("4. Exibir dados da conta");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Digite o valor a ser depositado: ");
                    double deposito = scanner.nextDouble();
                    scanner.nextLine();  // Consumir nova linha
                    conta.depositar(deposito);
                    break;
                case "2":
                    System.out.print("Digite o valor a ser sacado: ");
                    double saque = scanner.nextDouble();
                    scanner.nextLine();  // Consumir nova linha
                    conta.sacar(saque);
                    break;
                case "3":
                    conta.usarChequeEspecial();
                    break;
                case "4":
                    conta.exibirDados();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public static void menuContaPoupanca(ContaPoupanca conta, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Menu Conta Poupança ---");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Calcular rendimento");
            System.out.println("4. Exibir dados da conta");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Digite o valor a ser depositado: ");
                    double deposito = scanner.nextDouble();
                    scanner.nextLine();  // Consumir nova linha
                    conta.depositar(deposito);
                    break;
                case "2":
                    System.out.print("Digite o valor a ser sacado: ");
                    double saque = scanner.nextDouble();
                    scanner.nextLine();  // Consumir nova linha
                    conta.sacar(saque);
                    break;
                case "3":
                    conta.calcularRendimento();
                    break;
                case "4":
                    conta.exibirDados();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
