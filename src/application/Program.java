package application;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import service.ContractService;
import service.PaypalService;
import entities.Contract;
import entities.Installment;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter contract data");
		System.out.print("Number: ");
		int number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		Date date = sdf.parse(sc.next());
		System.out.print("Contract Value: ");
		double totalValue = sc.nextDouble();
		Contract contract = new Contract(number, date, totalValue);
		System.out.print("Enter the number of installments: ");
		int months = sc.nextInt();
		
		ContractService contractService = new ContractService(new PaypalService());
		contractService.processContract(contract, months);
		
		System.out.println("Installments");
		for (Installment x : contract.getInstallments()) {
			System.out.println(x);
		}
		
		sc.close();
	}
}
