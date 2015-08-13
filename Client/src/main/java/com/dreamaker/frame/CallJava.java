package com.dreamaker.frame;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public final class CallJava extends BrowserFunction {   
	  
    
    public CallJava (Browser arg0, String arg1) {   
        super(arg0, arg1);   
    }   

      
    @Override  
    public Object function(Object[] arg0) {  
    	System.out.println(arg0[0]);
    	TestShell aa = new TestShell();
    	aa.text1.setText(arg0[0].toString());
        return "";   
    }   

}  
