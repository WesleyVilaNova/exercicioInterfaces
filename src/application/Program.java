package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installiment;
import services.ContractService;
import services.OnlinePaymentService;
import services.PaypalServices;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner ( System.in);
		SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");


		System.out.println("Enter contract data");
		System.out.print("Number: ");
			Integer number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
			Date date = sdf.parse(sc.next());
		System.out.print("Contract value: ");
			Double total = sc.nextDouble();
		Contract contract = new Contract(number, date, total);
		
		System.out.print("Enter number of installments: ");
		Integer instal = sc.nextInt();

		ContractService cs = new ContractService(new PaypalServices());
		cs.processContract(contract, instal);
	
		System.out.println("Installments: ");
		for ( Installiment it : contract.getInstalliment()) {
			System.out.println(it);
		}
		
		sc.close();
		
	}

}
