package br.com.caelum.estoque.ws;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DataAdapter extends XmlAdapter<String, Date>{
	private static final String pattern = "dd/MM/yyyy";
	
	@Override
	public Date unmarshal(String dateString) throws Exception {
		return new SimpleDateFormat(pattern).parse(dateString);
	}
	
	@Override
	public String marshal(Date date) throws Exception {
		return new SimpleDateFormat(pattern).format(date);
	}
}
