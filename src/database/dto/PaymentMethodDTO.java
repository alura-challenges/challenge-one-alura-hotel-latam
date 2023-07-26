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

//public class MethodPaymentDTO {
//
//	private static final MethodPaymentDTO CREDIT = new MethodPaymentDTO("CREDITO");
//	private static final MethodPaymentDTO DEBIT = new MethodPaymentDTO("DEBITO");
//	private static final MethodPaymentDTO CASH = new MethodPaymentDTO("EFECTIVO");
//	
//	private static final Set<MethodPaymentDTO> VALUES = Set.of(CREDIT, DEBIT, CASH);
//	
//	private final String name;
//	
//	private MethodPaymentDTO(String name) {
//		this.name=name;
//	}
//	
//	public MethodPaymentDTO getByName(String name) {
//		for (MethodPaymentDTO mp: VALUES) {
//			if (mp.name.equals(name)) {
//				return mp;
//			}
//		}
//		return null;
//	}
//}



//public enum MethodPaymentDTO {
//	
//	CREDIT("Credito"),
//	DEBIT("Debito"),
//	CASH ("Efectivo");
//
//	String name;
//	
//	private MethodPaymentDTO(String name) {
//		this.name=name;
//	}
//	private static String getMethodPayment(String name) {
//		for (MethodPaymentDTO message:MethodPaymentDTO.values()) {
//			if(message.name.equals(name)) {
//				return message.name;
//			}
//		}
//		return null;
//	}
//}