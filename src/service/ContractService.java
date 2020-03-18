package service;

import java.util.Date;
import java.util.Calendar;

import entities.Contract;
import entities.Installment;

public class ContractService {
	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, int months) {
		double basicValue = contract.getTotalValue() / months;
		for (int i = 1; i <= months; i++) {
			Date date = addMonths(contract.getDate(), i);
			double updatedValue = basicValue + onlinePaymentService.interest(basicValue, i);
			double fullValue = updatedValue + onlinePaymentService.paymentFee(updatedValue);
			contract.addInstallment(new Installment(date, fullValue));
		}
	}

	private Date addMonths(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, i);
		return cal.getTime();
	}

}
