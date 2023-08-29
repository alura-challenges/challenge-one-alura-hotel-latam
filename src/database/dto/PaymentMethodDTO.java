package database.dto;

public enum PaymentMethodDTO {

	CREDIT ("CREDIT","Credito"),
	DEBIT ("DEBIT","Debito"),
	CASH ("CASH","Efectivo");
	
	private final String name;
	private final String spanishName;
	
	private PaymentMethodDTO(String name, String spanishName) {
		this.name=name;
		this.spanishName=spanishName;
	}
	
	public String getName(){
		return name;
	}
	
}

//public class PaymentMethodDTO {
//
//	private static final PaymentMethodDTO CREDIT = new PaymentMethodDTO("CREDITO");
//	private static final PaymentMethodDTO DEBIT = new PaymentMethodDTO("DEBITO");
//	private static final PaymentMethodDTO CASH = new PaymentMethodDTO("EFECTIVO");
//	
//	private static final Set<PaymentMethodDTO> VALUES = Set.of(CREDIT, DEBIT, CASH);
//	
//	private final String name;
//	
//	private PaymentMethodDTO(String name) {
//		this.name=name;
//	}
//	
//	public PaymentMethodDTO getByName(String name) {
//		for (PaymentMethodDTO mp: VALUES) {
//			if (mp.name.equals(name)) {
//				return mp;
//			}
//		}
//		return null;
//	}
//}



//public enum PaymentMethodDTO {
//	
//	CREDIT("Credito"),
//	DEBIT("Debito"),
//	CASH ("Efectivo");
//
//	String name;
//	
//	private PaymentMethodDTO(String name) {
//		this.name=name;
//	}
//	private static String getPaymentMethod(String name) {
//		for (PaymentMethodDTO message:PaymentMethodDTO.values()) {
//			if(message.name.equals(name)) {
//				return message.name;
//			}
//		}
//		return null;
//	}
//}