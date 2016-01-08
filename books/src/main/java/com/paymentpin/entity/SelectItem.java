package com.paymentpin.entity;


public class SelectItem {

	private static final long serialVersionUID = 87678231141L;
	
	private String value;
	private String label;
	
   /* *
    * @param value Value to be delivered to the model if this
    *  item is selected by the user
    * @param label Label to be rendered for this item in the response
    */
   public SelectItem(String value, String label) {
       this.value = value;
       this.label = label;
   }

	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	

}
