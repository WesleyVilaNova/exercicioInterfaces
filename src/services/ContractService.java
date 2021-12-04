package services;

import java.util.Calendar;
import java.util.Date;

import entities.Contract;
import entities.Installiment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;

	public ContractService( OnlinePaymentService onlinePaymentService ) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract ( Contract contract, int months) {
		Double basicQuota = contract.getTotalValue() / months;
		for ( int i = 1; i <= months; i++){
		Double updatedQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
		Double fullQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota);
		Date dueDate = addMonths(contract.getDate(), i);
		contract.getInstalliment().add(new Installiment(dueDate, fullQuota));
		
		
		}
	}
	
	private Date addMonths(Date date, int N) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, N);
		return calendar.getTime();
	}
}