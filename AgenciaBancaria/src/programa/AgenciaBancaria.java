package programa;

import java.util.ArrayList;
import java.util.Scanner;

import utilitarios.Utils;

public class AgenciaBancaria {

	static Scanner input = new Scanner(System.in);
	static ArrayList<Conta> contasBancarias;

	public static void main(String[] args) {
		contasBancarias = new ArrayList<Conta>();
		operacoes();
	}

	public static void operacoes() {
		System.out.println("\n");
		System.out.println("|   Opção 1 - Criar conta   |");
		System.out.println("|   Opção 2 - Depositar     |");
		System.out.println("|   Opção 3 - Sacar         |");
		System.out.println("|   Opção 4 - Transferir    |");
		System.out.println("|   Opção 5 - Listar        |");
		System.out.println("|   Opção 6 - Sair          |");
		System.out.println("\n");

		int operacao = input.nextInt();

		switch (operacao) {
		case 1:
			criarConta();
			break;
		case 2:
			depositar();
			break;
		case 3:
			sacar();
			break;
		case 4:
			transferir();
			break;
		case 5:
			listarContas();
			break;
		case 6:
			Utils.sendMessage("Um obrigado e até logo.");
			System.exit(0);
			break;

		default:
			Utils.sendMessage("Opção inválida!");
			operacoes();
		}

	}

	public static void criarConta() {
		Utils.sendMessage("\nNome: ");
		String nome = input.next();

		Utils.sendMessage("\nCPF: ");
		String cpf = input.next();

		Utils.sendMessage("\nEmail: ");
		String email = input.next();

		Pessoa pessoa = new Pessoa(nome, cpf, email);

		Conta conta = new Conta(pessoa);

		contasBancarias.add(conta);
		Utils.sendMessage("\nSua conta foi criada com sucesso!");
		operacoes();
	}

	private static Conta encontrarConta(int numeroConta) {
		Conta conta = null;
		if (contasBancarias.size() > 0) {
			for (Conta c : contasBancarias) {
				if (c.getNumeroConta() == numeroConta) {
					conta = c;
				}
			}
		}
		return conta;
	}

	public static void depositar() {
		Utils.sendMessage("\nNúmero da conta: ");
		int numeroConta = input.nextInt();

		Conta conta = encontrarConta(numeroConta);

		if (conta != null) {
			Utils.sendMessage("\nValor que deseja depositar: ");
			double valorDeposito = input.nextDouble();
			conta.depositar(valorDeposito);
			Utils.sendMessage(
					"\nValor de " + valorDeposito + " depositado com sucesso para a conta " + conta.getNumeroConta());
		} else {
			Utils.sendMessage("Conta não encontrada!");
		}
		operacoes();
	}

	public static void sacar() {
		Utils.sendMessage("\nNúmero da conta: ");
		int numeroConta = input.nextInt();

		Conta conta = encontrarConta(numeroConta);

		if (conta != null) {
			Utils.sendMessage("\nValor que deseja sacar: ");
			double valorDeposito = input.nextDouble();
			conta.sacar(valorDeposito);
			Utils.sendMessage(
					"\nValor de " + valorDeposito + " sacado com sucesso para a conta " + conta.getNumeroConta());
		} else {
			Utils.sendMessage("Conta não encontrada!");
		}
		operacoes();
	}

	public static void transferir() {
		Utils.sendMessage("\nNúmero da conta do remetente: ");
		int numeroContaRemetente = input.nextInt();

		Conta contaRemetente = encontrarConta(numeroContaRemetente);

		if (contaRemetente != null) {
			Utils.sendMessage("\nNúmero da conta do destinatário: ");
			int numeroContaDestinatario = input.nextInt();

			Conta contaDestinatario = encontrarConta(numeroContaDestinatario);
			if (contaDestinatario != null) {
				Utils.sendMessage("\nValor da transferência: ");
				double valor = input.nextDouble();

				contaRemetente.transferir(contaDestinatario, valor);
				Utils.sendMessage("\nValor de " + valor + " transferido com sucesso para a conta "
						+ contaDestinatario.getNumeroConta());

			}
		}
		operacoes();
	}

	public static void listarContas() {
		if (contasBancarias.size() > 0) {
			for (Conta c: contasBancarias) {
				Utils.sendMessage("\n" + c);
			}
		}else {
			Utils.sendMessage("Não há contas em nosso sistema!");
		}
		operacoes();
	}

}
