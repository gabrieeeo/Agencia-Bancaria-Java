package programa;

import utilitarios.Utils;

public class Conta {

	private static int contadorDeContas = 1;
	
	private int numeroConta;
	private Pessoa pessoa;
	private double saldo = 0.0;
	
	public Conta(Pessoa pessoa) {
		this.numeroConta = contadorDeContas;
		this.pessoa = pessoa;
		this.updateSaldo();
		contadorDeContas += 1;
	}
	
	public int getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	private void updateSaldo() {
        this.saldo = this.getSaldo();
    }
	
	public String toString() {
		return "\n" + 
	           "\nNúmero da conta: " + this.getNumeroConta() +
			   "\nNome: " + this.getPessoa().getNome() +
			   "\nCPF: " + this.getPessoa().getCPF() +
			   "\nEmail: " + this.getPessoa().getEmail() +
			   "\nSaldo: " + Utils.doubleToString(getSaldo()) +
			   "\n";
	}
	
	public void depositar(double valor) {
		if(valor > 0) {
			setSaldo(getSaldo() + valor);
			Utils.sendMessage("Seu depósito de " + valor + " foi realizado com sucesso!");
		}else {
			Utils.sendMessage("Não foi possível realizar o seu depósito.");
		}
	}
	
	public void sacar(double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			Utils.sendMessage("Seu saque de " + valor + " foi realizado com sucesso!");
		}else {
			Utils.sendMessage("Não foi possível realizar o seu saque.");
		}
	}
	
	public void transferir(Conta contaParaDeposito, Double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			
			contaParaDeposito.saldo = contaParaDeposito.getSaldo() + valor;
			Utils.sendMessage("Sua transferência de " + valor + " foi realizada com sucesso!");
		}else {
			Utils.sendMessage("Não foi possível realizar a sua transferência.");
		}
	}
	
}
