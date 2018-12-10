package sp.com.senac.pi.util.control;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	private DateFormat dateFormat; 
	
	public Util() {
		this.dateFormat = new SimpleDateFormat("yyyyMMdd"); 
	}
	
	public String getStringDate(Date data){
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss"); 
		return dateFormat.format(data);
	}
	
	public String getStringDate(String data) {
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss"); 
		Date date = new Date();
        try {
        	if (! data.isEmpty()) {
        		date = this.dateFormat.parse(data);
        	}
        	
		} catch (ParseException e) {
			e.printStackTrace();
		}
        //System.out.println(dateFormat.format(date));
        return dateFormat.format(date);
	}
}
